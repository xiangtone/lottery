package org.x;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.x.info.PageAction;
import org.x.info.PartnerInfo;
import org.x.info.PartnerOrderInfo;
import org.x.service.DaoService;
import org.x.service.PartnerService;
import org.x.utils.AES;

import com.alibaba.fastjson.JSON;
import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;
import com.iwt.yt.api.base.ReqHead;
import com.iwt.yt.api.trans.PointExchangeLotteryReq;
import com.iwt.yt.api.trans.PointExchangeLotteryReqBody;
import com.iwt.yt.plugin.ClientTransH5Service;
import com.iwt.yt.plugin.ClientTransService;
import com.iwt.yt.plugin.ClientTransServiceInterface;
import com.iwt.yt.plugin.ClientTransTestH5Service;
import com.iwt.yt.plugin.ClientTransTestWebService;
import com.iwt.yt.util.ClientH5Util;
import com.iwt.yt.util.ClientUtil;
import com.iwt.yt.util.ClientUtilInterface;
import com.iwt.yt.util.TestH5ClientUtil;
import com.iwt.yt.util.TestWebClientUtil;

public class PartnerApi {

	private static final Logger LOG = Logger.getLogger(PartnerApi.class);

	private final long serialVersionUID = 8756559814195904326L;
	private PointExchangeLotteryReqBody body = new PointExchangeLotteryReqBody();

	private PageAction pageAction;
	private String localErrorMsg; // can not do callback error message

	private String partnerId;
	private String partnerTransData;
	private String ip;
	private PartnerInfo partnerInfo;
	private PartnerOrderInfo partnerOrderInfo;
	private ClientUtilInterface clientUtil;
	private ClientTransServiceInterface clientTransService;
	private PreparedStatement ps = null;
	private Connection con = null;
	private static final int LOG_ID = 3001;
	private Long id;
	private DaoService daoService = new DaoService();
	private String partnerOrderNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PageAction getPageAction() {
		return pageAction;
	}

	public void setPageAction(PageAction pageAction) {
		this.pageAction = pageAction;
	}

	public String getLocalErrorMsg() {
		return localErrorMsg;
	}

	public void setLocalErrorMsg(String localErrorMsg) {
		this.localErrorMsg = localErrorMsg;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPartnerTransData() {
		return partnerTransData;
	}

	public void setPartnerTransData(String partnerTransData) {
		this.partnerTransData = partnerTransData;
	}

	public void process() throws Exception {
		// check parameters;
		if (checkParameters()) {
			LOG.debug(" checkParameters check ok");
			if (partnerInfo.getRealBalance() < partnerInfo.getUnitPrice() || partnerInfo.getCreditBalance() < -2000) {
				processToPartner(4003, "Balances is not enough!");
			} else {
				if (partnerInfo.getState().equals("closed")) {
					processToPartner(4004, "Status is not available !");
				} else {
					daoService.queryPartnerOrderNumberFromDb(partnerOrderInfo);
					if (partnerOrderNumber != null
							|| partnerOrderInfo.getPartnerOrderNumber().equals(partnerOrderNumber)) {
						processToPartner(4007, "partnerOrderNumber is repeated!");
					} else {
						processToYT();
						if (partnerInfo.getState().equals("web") || partnerInfo.getState().equals("h5")) {
							daoService.updateCreditBalance(partnerId);
						}
					}
				}
			}
		} else {
			LOG.debug("ip:" + ip + "  . check parameter fail .");
			return;
		}
	}

	private void processToPartner(int result, String resultDesc) throws Exception {
		pageAction = new PageAction();
		pageAction.setUrl(partnerOrderInfo.getPartnerCallbackURL());
		Map<String, String> entity = new HashMap<String, String>();
		Map<String, Object> transDataMap = new LinkedHashMap<String, Object>();
		transDataMap.put("transDateTime", "");
		transDataMap.put("appId", partnerOrderInfo.getAppId());
		transDataMap.put("partnerChannelId", partnerOrderInfo.getPartnerChannelId());
		transDataMap.put("partnerReserved", partnerOrderInfo.getPartnerReserved());
		transDataMap.put("partnerOrderNumber", partnerOrderInfo.getPartnerOrderNumber());
		transDataMap.put("orderStatus", "");
		transDataMap.put("orderNumber", "");
		transDataMap.put("result", result);
		transDataMap.put("resultDesc", resultDesc);
		transDataMap.put("issueNumber", "");
		transDataMap.put("betSuccAmount", "");
		transDataMap.put("orderAcceptTime", "");
		transDataMap.put("ticketInfoList", "");
		String transDataJson = JSON.toJSONString(transDataMap);
		if (partnerInfo.getKeyAES() != null && partnerInfo.getKeyAES().length() > 0) {
			transDataJson = AES.Encrypt(partnerTransData, partnerInfo.getKeyAES());
		}
		entity.put("partnerId", partnerId);
		entity.put("transData", transDataJson);
		pageAction.setEntity(entity);
	}

	private void processToYT() throws RsaEncryptException {
		switchPartnerState();
		String channelId = clientUtil.getChannelId();
		String transSerialNumber = UUID.randomUUID().toString().replaceAll("-", "");
		configBody();
		PointExchangeLotteryReq req = new PointExchangeLotteryReq();
		req.setHead(new ReqHead(channelId));
		req.setBody(body);
		LOG.debug(req);
		String transData = clientTransService.encryptPointExchangeLotteryReq(req);
		pageAction = new PageAction();
		pageAction.setUrl(clientUtil.getPointExchangeLotteryUrl());
		LOG.debug(pageAction.getUrl());
		Map<String, String> entity = new HashMap<String, String>();
		entity.put("channelId", channelId);
		entity.put("transSerialNumber", transSerialNumber);
		entity.put("transData", transData);
		LOG.debug(transData);
		pageAction.setEntity(entity);
		partnerOrderInfoLogInsert();
		// ThreadPool.mThreadPool
		// .execute(new PostLogInsert(req.getHead().getChannelId(),
		// req.getHead().getTransSerialNumber(), transData,
		// req.getBody().getChannelReserved(), req.getBody().getOrderNumber(),
		// req.getBody().getUserPhoneNumber(),
		// req.getBody().getTransDateTime(), req.getBody().getUserName(),
		// req.getBody().getPointMerchantId(),
		// req.getBody().getGameId(), req.getBody().getNumberSelectType(),
		// req.getBody().getBetTotalAmount(),
		// req.getBody().getPointTotalAmount(), req.getBody().get
		// betInfo.getBetDetail(), req.getBody().getCallbackURL(), ip));
	}

	private void switchPartnerState() {
		switch (partnerInfo.getState()) {
		case "web":
			clientUtil = ClientUtil.getInstance();
			clientTransService = ClientTransService.getInstance();
			body.setCallbackURL("http://api.youkala.com/ytCallback.jsp");
			break;
		case "h5":
			clientUtil = ClientH5Util.getInstance();
			clientTransService = ClientTransH5Service.getInstance();
			body.setCallbackURL("http://api.youkala.com/ytCallback.jsp");
			break;
		case "test":
			clientUtil = TestWebClientUtil.getInstance();
			clientTransService = ClientTransTestWebService.getInstance();
			body.setCallbackURL("http://api.youkala.com/ytCallbackTest.jsp");
			break;
		case "testH5":
			clientUtil = TestH5ClientUtil.getInstance();
			clientTransService = ClientTransTestH5Service.getInstance();
			body.setCallbackURL("http://api.youkala.com/ytCallbackTest.jsp");
			break;
		default:
			break;
		}
	}

	private boolean checkParameters() {
		boolean result = false;
		// check all Parameters
		result = checkAllParametersExist();
		return result;
	}

	private boolean checkAllParametersExist() {
		boolean result = false;
		// TODO need complete
		try {
			if (!(partnerId != null && partnerId.length() > 0)) {
				setLocalErrorMsg("{\"status\":\"error\",\"result\":4001,\"msg\":\"partnerId is not exist!\"}");
				return result;
			}
			partnerInfo = PartnerService.getInstance().getNameLoadingCache(partnerId);
			if (partnerInfo == null) {
				setLocalErrorMsg("{\"status\":\"error\",\"result\":4001,\"msg\":\"partnerId is not exist!\"}");
				return result;
			} else {
				processTransData();
				if (partnerOrderInfo == null) {
					setLocalErrorMsg("{\"status\":\"error\",\"result\":4002,\"msg\":\"decrypt order info failure!\"}");
					return result;
				} else {
					if (partnerOrderInfo.getPartnerCallbackURL() != null
							&& partnerOrderInfo.getPartnerCallbackURL().length() > 0) {
						// todo check match http or https
					} else {
						setLocalErrorMsg(
								"{\"status\":\"error\",\"result\":4005,\"msg\":\"Web callback URL is no currect!\"}");
						return result;
					}
				}
			}
			result = checkParnterId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private void processTransData() throws Exception {
		String json = partnerTransData;
		if (partnerInfo.getKeyAES() != null && partnerInfo.getKeyAES().length() > 0) {
			// todo decrypt
			json = AES.Decrypt(partnerTransData, partnerInfo.getKeyAES());
		}
		partnerOrderInfo = JSON.parseObject(json, PartnerOrderInfo.class);
	}

	private boolean checkParnterId() {
		boolean result = false;
		result = true;
		return result;
	}

	public static void main(String[] args)
			throws RsaEncryptException, RsaDecryptException, ClientProtocolException, IOException {
		// PartnerApi testSend = new PartnerApi();
		// testSend.sendTest();
	}

	private void configBody() {
		body.setOrderNumber(UUID.randomUUID().toString().replaceAll("-", ""));
		body.setTransDateTime(new Date());
		try {
			body.setPointTotalAmount(10);
			body.setChannelReserved("youka");
			body.setOrderNumber(Long.toString(GenerateIdService.getInstance()
					.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id")), "order", 1)));
			body.setUserPhoneNumber(partnerOrderInfo.getUserPhoneNumber());// wanghua
			body.setPointMerchantId("1200100001");
			body.setGameId("10001");
			switch (partnerOrderInfo.getNumberSelectType()) {
			case 1:
				body.setNumberSelectType(1);
				body.setBetTotalAmount(1);
				break;
			case 2:
				// 单式自选
				body.setNumberSelectType(12);
				body.setBetTotalAmount(1);
				body.setBetInfoList(partnerOrderInfo.getBetInfoList());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void partnerOrderInfoLogInsert() {
		setId(Long.parseLong(body.getOrderNumber()));
		LOG.debug(this.getPartnerId());
		LOG.debug(body.getOrderNumber());
		if (body.getNumberSelectType() == 1) {
			partnerOrderInfo.setBetInfoList(null);
		}
		partnerTransData = JSON.toJSONString(partnerOrderInfo);
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(
					"insert into `log_sync_generals` (id,logId,para01,para02,para03,para04) values (?,?,?,?,?,?)");
			int m = 1;
			ps.setLong(m++, this.getId());
			ps.setInt(m++, LOG_ID);
			ps.setString(m++, this.getPartnerId());
			ps.setString(m++, partnerTransData);
			ps.setString(m++, partnerOrderInfo.getPartnerCallbackURL());
			ps.setString(m++, partnerOrderInfo.getPartnerOrderNumber());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public String getPartnerOrderNumber() {
		return partnerOrderNumber;
	}

	public void setPartnerOrderNumber(String partnerOrderNumber) {
		this.partnerOrderNumber = partnerOrderNumber;
	}

	public PartnerInfo getPartnerInfo() {
		return partnerInfo;
	}

	public void setPartnerInfo(PartnerInfo partnerInfo) {
		this.partnerInfo = partnerInfo;
	}
}

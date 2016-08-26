package org.x;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
import org.x.service.PartnerService;

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

	public PartnerApi() throws RsaEncryptException {
		super();
	}

	public void process() throws RsaEncryptException {
		// check parameters;
		if (checkParameters()) {
			LOG.debug(" checkParameters check ok");
			processToYT();
		} else {
			LOG.debug("ip:" + ip + "  . check parameter fail .");
			return;
		}
	}

	private void processToYT() throws RsaEncryptException {
		switchPartnerState();
		String channelId = clientUtil.getChannelId();
		String transSerialNumber = UUID.randomUUID().toString().replaceAll("-", "");
		configBody();
		// LOG.debug(body.getCallbackURL());
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
			body.setCallbackURL("http://a.yt.youkala.com:38080/ytCallback.jsp");
			break;
		case "h5":
			clientUtil = ClientH5Util.getInstance();
			clientTransService = ClientTransH5Service.getInstance();
			body.setCallbackURL("http://a.yt.youkala.com:38080/ytCallback.jsp");
			break;
		case "test":
			clientUtil = TestWebClientUtil.getInstance();
			clientTransService = ClientTransTestWebService.getInstance();
			body.setCallbackURL("http://a.yt.youkala.com:38080/ytCallbackTest.jsp");
			break;
		case "testH5":
			clientUtil = TestH5ClientUtil.getInstance();
			clientTransService = ClientTransTestH5Service.getInstance();
			body.setCallbackURL("http://a.yt.youkala.com:38080/ytCallbackTest.jsp");
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
				LOG.debug(partnerOrderInfo);
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

	private void processTransData() {
		String json = partnerTransData;
		if (partnerInfo.getKeyAES() != null && partnerInfo.getKeyAES().length() > 0) {
			// todo decrypt
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

	// public String selectBetNumber(){
	// int[] redBall = new int[12];
	// int blueBall;
	// for(int i=0;i<6;i++){
	// redBall[i]=(int)Math.random()*33+1;
	// for(int j=0;j<i;j++)
	// {
	// if(redBall[i]==redBall[j])
	// {
	// redBall[i]=(int)Math.random()*33+1;
	// j=0;
	// }
	//
	// }
	// }
	// String redball
	// }

	private void configBody() {
		body.setOrderNumber(UUID.randomUUID().toString().replaceAll("-", ""));
		body.setTransDateTime(new Date());
		try {
			body.setPointTotalAmount(10);
			body.setChannelReserved("youka");
			body.setOrderNumber(Long.toString(GenerateIdService.getInstance()
					.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id")), "order", 1)));
			// body.setUserPhoneNumber("15829553521");// zhuxizhe
			// body.setUserPhoneNumber("18025314707");// fuming
			// body.setUserPhoneNumber("15285960182");// fuming guizhou CMCC
			// test
			// body.setUserPhoneNumber("13603054736");// lijiaqi
			// body.setUserPhoneNumber(inputUserPhoneNumber());
			// body.setUserPhoneNumber("13923832816");//guojining
			// body.setUserPhoneNumber("18676382886");//fengquchi
			body.setUserPhoneNumber(partnerOrderInfo.getUserPhoneNumber());// wanghua
			body.setPointMerchantId("1200100001");
			body.setGameId("10001");
			body.setNumberSelectType(1);
			body.setBetTotalAmount(1);

			// body.setNumberSelectType(12);
			// body.setBetTotalAmount(4);
			// BetInfo betInfo = new BetInfo("101", "001060607091516260113" +
			// "001060508091517180114"
			// + "001060307081418300115" + "001060213141927310116");
			// betInfoList.add(betInfo);
			// body.setBetInfoList(betInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void partnerOrderInfoLogInsert() {
		setId(Long.parseLong(body.getOrderNumber()));
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			ps = con.prepareStatement(
					"insert into `log_sync_generals` (id,logId,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			int m = 1;
			ps.setLong(m++, this.getId());
			ps.setInt(m++, LOG_ID);
			ps.setString(m++, partnerOrderInfo.getAppId());
			ps.setString(m++, this.getPartnerId());
			ps.setString(m++, partnerOrderInfo.getPartnerChannelId());
			ps.setString(m++, partnerOrderInfo.getPartnerReserved());
			ps.setString(m++, partnerOrderInfo.getPartnerOrderNumber());
			ps.setString(m++, partnerOrderInfo.getUserPhoneNumber());
			ps.setString(m++, partnerOrderInfo.getUserName());
			ps.setString(m++, partnerOrderInfo.getLotteryId());
			ps.setString(m++, partnerOrderInfo.getBetTotalAmount());
			ps.setString(m++, partnerOrderInfo.getPartnerCallbackURL());
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

}

package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;
import org.common.util.ThreadPool;
import org.x.info.PageAction;
import org.x.info.PartnerOrderInfo;

import com.alibaba.fastjson.JSON;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;
import com.iwt.yt.api.trans.PointExchangeLotteryResultReq;
import com.iwt.yt.api.trans.TicketInfo;
import com.iwt.yt.plugin.ClientTransService;
import com.iwt.yt.plugin.ClientTransServiceInterface;
import com.iwt.yt.plugin.ClientTransTestWebService;

public class DecryptWebCallback {

	private final static Logger LOG = Logger.getLogger(DecryptWebCallback.class);

	private String transData;
	private String channelId;
	private String transSerialNumber;
	private TicketInfo ticketInfo = new TicketInfo();
	private String ip;
	private PageAction pageAction = new PageAction();
	private String method;

	private PreparedStatement ps = null;
	private Connection con = null;
	private ResultSet rs = null;

	private PointExchangeLotteryResultReq result = new PointExchangeLotteryResultReq();
	private String partnerOrderInfoJson;
	private PartnerOrderInfo partnerOrderInfo;
	private String partnerCallbackURL;

	private void decrypt() throws Exception {
		LOG.debug(this);
		ClientTransServiceInterface clientTransService;
		if (method.equals("test")) {
			clientTransService = ClientTransTestWebService.getInstance();
		} else {
			clientTransService = ClientTransService.getInstance();
		}

		result = clientTransService.decryptPointExchangeLotteryResultReq(getChannelId(), getTransSerialNumber(),
				getTransData());
		LOG.debug(result);
		ThreadPool.mThreadPool.execute(new WebCallbackLogInsert(result.getHead().getChannelId(),
				result.getHead().getTransSerialNumber(), this.getTransData(), result.getBody().toString(),
				result.getBody().getChannelReserved(), result.getBody().getOrderNumber(), result.getBody().getResult(),
				result.getBody().getResultDesc(), result.getBody().getIssueNumber(),
				result.getBody().getBetSuccAmount(), ticketInfo.getBetDetail(), ip));
	}

	@SuppressWarnings("unused")
	public void process() throws Exception {
		decrypt();
		queryPartnerOrderInfoFromDb();
		transDataConfig();
	}

	private void transDataConfig() throws RsaEncryptException {
		LOG.debug(partnerOrderInfoJson);
		partnerOrderInfo = JSON.parseObject(partnerOrderInfoJson, PartnerOrderInfo.class);
		LOG.debug(partnerOrderInfo);
		pageAction = new PageAction();
		pageAction.setUrl(partnerCallbackURL);
		LOG.debug(pageAction.getUrl());
		Map<String, String> entity = new LinkedHashMap<String, String>();
		Map<String, String> transDataMap = new LinkedHashMap<String, String>();
		List<Map<String, String>> transData = new ArrayList<Map<String, String>>();
		transDataMap.put("transDateTime", result.getBody().getTransDateTime().toString());
		transDataMap.put("partnerChannelId", partnerOrderInfo.getPartnerChannelId());
		transDataMap.put("partnerReserved", partnerOrderInfo.getPartnerReserved());
		transDataMap.put("appId", partnerOrderInfo.getAppId());
		transDataMap.put("partnerOrderNumber", partnerOrderInfo.getPartnerOrderNumber());
		transDataMap.put("orderNumber", result.getBody().getOrderNumber());
		transDataMap.put("result", result.getBody().getResult() + "");
		transDataMap.put("resultDesc", result.getBody().getResultDesc());
		transDataMap.put("issueNumber", result.getBody().getIssueNumber());
		transDataMap.put("betSuccAmount", result.getBody().getBetSuccAmount() + "");
		transDataMap.put("orderAcceptTime", result.getBody().getOrderAcceptTime().toString());
		transDataMap.put("ticketInfoList", result.getBody().getTicketInfoList().toString());
		transData.add(transDataMap);
		entity.put("transData", transData.toString());
		LOG.debug(entity);
		pageAction.setEntity(entity);
	}

	private void queryPartnerOrderInfoFromDb() {
		LOG.debug(result.getBody().getOrderNumber());
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			String sql = "select * from `log_sync_generals` where id=?";
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, result.getBody().getOrderNumber());
			rs = ps.executeQuery();
			if (rs.next()) {
				LOG.debug(rs.getString("logId"));
				partnerOrderInfoJson = rs.getString("para01");
				partnerCallbackURL = rs.getString("para02");
			}
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTransSerialNumber() {
		return transSerialNumber;
	}

	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}

	public PageAction getPageAction() {
		return pageAction;
	}

	public void setPageAction(PageAction pageAction) {
		this.pageAction = pageAction;
	}

	@Override
	public String toString() {
		return "DecryptWebCallback [transData=" + transData + ", channelId=" + channelId + ", transSerialNumber="
				+ transSerialNumber + "]";
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}

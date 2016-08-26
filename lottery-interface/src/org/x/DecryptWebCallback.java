package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;
import org.common.util.ThreadPool;
import org.x.info.PageAction;

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
	private PageAction pageAction;
	private String method;

	private PreparedStatement ps = null;
	private Connection con = null;
	private ResultSet rs = null;

	PointExchangeLotteryResultReq result;

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
				result.getHead().getTransSerialNumber(), this.getTransData(), result.getBody().getChannelReserved(),
				result.getBody().getOrderNumber(), result.getBody().getResult(), result.getBody().getResultDesc(),
				result.getBody().getIssueNumber(), result.getBody().getBetSuccAmount(), ticketInfo.getBetDetail(), ip));
	}

	public void process() throws Exception {
		decrypt();
		selectPartnerOrderInfo();
	}

	public void selectPartnerOrderInfo() {
		LOG.debug(result.getBody().getOrderNumber());
		Map<String, String> entity = new HashMap<String, String>();
		List<Map<String, String>> partnerOrderInfoList = new ArrayList<Map<String, String>>();
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			String sql = "select * from `log_sync_generals` where id=?";
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, result.getBody().getOrderNumber());
			rs = ps.executeQuery();
			if (rs.next()) {
				LOG.debug(rs.getString("logId"));
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
		// pageAction.setEntity(entity);
		LOG.debug(partnerOrderInfoList);
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

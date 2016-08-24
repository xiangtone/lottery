package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.x.utils.ConnectionServiceLog;

public class WebCallbackLogInsert implements Runnable {

	private static final int LOG_ID = 3002;

	private Long id;
	private String channelId;
	private String transSerialNumber;
	private String transData;
	private String channelReserved;
	private String orderNumber;
	private int result;
	private String resultDesc;
	private String issueNumber;
	private int betSuccAmount;
	private String ticketInfo;
	private String ip;

	public WebCallbackLogInsert(String channelId, String transSerialNumber, String transData, String channelReserved,
			String orderNumber, int result, String resultDesc, String ip) {
		super();
		this.channelId = channelId;
		this.transSerialNumber = transSerialNumber;
		this.transData = transData;
		this.channelReserved = channelReserved;
		this.orderNumber = orderNumber;
		this.result = result;
		this.resultDesc = resultDesc;
		this.ip = ip;
	}

	public WebCallbackLogInsert(String channelId, String transSerialNumber, String transData, String channelReserved,
			String orderNumber, int result, String resultDesc, String issueNumber, int betSuccAmount, String ticketInfo,
			String ip) {

		super();
		this.channelId = channelId;
		this.transSerialNumber = transSerialNumber;
		this.transData = transData;
		this.channelReserved = channelReserved;
		this.orderNumber = orderNumber;
		this.result = result;
		this.resultDesc = resultDesc;
		this.issueNumber = issueNumber;
		this.betSuccAmount = betSuccAmount;
		this.ticketInfo = ticketInfo;
		this.ip = ip;

	}

	@Override
	public void run() {
		setId(GenerateIdService.getInstance().generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id")),
				"clicks", 1));
		if (this.id > 0) {
			PreparedStatement ps = null;
			Connection con = null;
			try {
				con = ConnectionService.getInstance().getConnectionForLocal();
				ps = con.prepareStatement(
						"insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10,para11) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, LOG_ID);
				ps.setString(m++, this.getChannelId());
				ps.setString(m++, this.getTransSerialNumber());
				ps.setString(m++, this.getTransData());
				ps.setString(m++, this.getChannelReserved());
				ps.setString(m++, this.getOrderNumber());
				ps.setInt(m++, this.getResult());
				ps.setString(m++, this.getResultDesc());
				ps.setString(m++, this.getIssueNumber());
				ps.setInt(m++, this.getBetSuccAmount());
				ps.setString(m++, this.getTicketInfo());
				ps.setString(m++, this.getIp());
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}

	public String getChannelReserved() {
		return channelReserved;
	}

	public void setChannelReserved(String channelReserved) {
		this.channelReserved = channelReserved;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResult(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public int getBetSuccAmount() {
		return betSuccAmount;
	}

	public void setBetSuccAmount(int betSuccAmount) {
		this.betSuccAmount = betSuccAmount;
	}

	public String getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(String ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}

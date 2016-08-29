package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.x.info.BackReqBetInfo;

public class LogInsert implements Runnable {

	private static final int LOG_ID = 3003;

	private Long id;
	private String channelId;
	private String transSerialNumber;
	private String decryptInfo;
	private String transData;
	private String businessId;
	private String orderNumber;
	private String channelReserved;
	private String issueNumber;
	private int betSuccAmount;
	private Date orderAcceptTime;
	private int result;
	private String resultDesc;
	private List<BackReqBetInfo> ticketInfoList;
	private String ip;

	public LogInsert(String channelId, String transSerialNumber, String businessId, String transData,
			String decryptInfo, String ip) {
		super();
		this.channelId = channelId;
		this.transSerialNumber = transSerialNumber;
		this.businessId = businessId;
		this.transData = transData;
		this.decryptInfo = decryptInfo;
		this.ip = ip;
	}

	public LogInsert(String channelId, String transSerialNumber, String businessId, String transData,
			String decryptInfo, String orderNumber, String channelReserved, String issueNumber, int betSuccAmount,
			Date orderAcceptTime, int result, String resultDesc, List<BackReqBetInfo> ticketInfoList, String ip) {
		// TODO Auto-generated constructor stub
		super();
		this.channelId = channelId;
		this.transSerialNumber = transSerialNumber;
		this.businessId = businessId;
		this.transData = transData;
		this.decryptInfo = decryptInfo;
		this.issueNumber = issueNumber;
		this.orderNumber = orderNumber;
		this.betSuccAmount = betSuccAmount;
		this.orderAcceptTime = orderAcceptTime;
		this.result = result;
		this.resultDesc = resultDesc;
		this.ticketInfoList = ticketInfoList;
		this.channelReserved = channelReserved;
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
						"insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10,para11,para12,para13,para14) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, LOG_ID);
				ps.setString(m++, this.getChannelId());
				ps.setString(m++, this.getTransSerialNumber());
				ps.setString(m++, this.getBusinessId());
				ps.setString(m++, this.getTransData());
				ps.setString(m++, this.getDecryptInfo());
				ps.setString(m++, this.getOrderNumber());
				ps.setString(m++, this.getChannelReserved());
				ps.setString(m++, this.getIssueNumber());
				ps.setInt(m++, this.getBetSuccAmount());
				ps.setString(m++, this.getOrderAcceptTime().toString());
				ps.setInt(m++, this.getResult());
				ps.setString(m++, this.getResultDesc());
				ps.setString(m++, this.getTicketInfoList().toString());
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

	public String getDecryptInfo() {
		return decryptInfo;
	}

	public void setDecryptInfo(String decryptInfo) {
		this.decryptInfo = decryptInfo;
	}

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getChannelReserved() {
		return channelReserved;
	}

	public void setChannelReserved(String channelReserved) {
		this.channelReserved = channelReserved;
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

	public Date getOrderAcceptTime() {
		return orderAcceptTime;
	}

	public void setOrderAcceptTime(Date orderAcceptTime) {
		this.orderAcceptTime = orderAcceptTime;
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

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public List<BackReqBetInfo> getTicketInfoList() {
		return ticketInfoList;
	}

	public void setTicketInfoList(List<BackReqBetInfo> ticketInfoList) {
		this.ticketInfoList = ticketInfoList;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}

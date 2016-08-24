package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.x.utils.ConnectionServiceLog;

import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.BetInfo;

public class PostLogInsert implements Runnable {

	private static final int LOG_ID = 3001;

	private Long id;
	private String channelId;
	private String transSerialNumber;
	private String transData;
	private String channelReserved;
	private String orderNumber;
	private Date transDateTime;
	private String userPhoneNumber;
	private String userName;
	private String pointMerchantId;
	private String gameId;
	private int numberSelectType;
	private int betTotalAmount;
	private long pointTotalAmount;
	private BetInfo betInfo;
	private String callbackURL;
	private String ip;

	public PostLogInsert(String channelId, String transSerialNumber, String transData, String channelReserved,
			String orderNumber, String userPhoneNumber, Date transDateTime, String callbackURL, String ip) {
		// TODO Auto-generated constructor stub
		super();
		this.channelId = channelId;
		this.transSerialNumber = transSerialNumber;
		this.transData = transData;
		this.channelReserved = channelReserved;
		this.orderNumber = orderNumber;
		this.userPhoneNumber = userPhoneNumber;
		this.transDateTime = transDateTime;
		this.callbackURL = callbackURL;
		this.ip = ip;

	}

	public PostLogInsert(String channelId, String transSerialNumber, String transData, String channelReserved,
			String orderNumber, String userPhoneNumber, Date transDateTime, String userName, String pointMerchantId,
			String gameId, int numberSelectType, int betTotalAmount, long pointTotalAmount, BetInfo betInfo,
			String callbackURL, String ip) {
		// TODO Auto-generated constructor stub
		super();
		this.channelId = channelId;
		this.transSerialNumber = transSerialNumber;
		this.transData = transData;
		this.channelReserved = channelReserved;
		this.orderNumber = orderNumber;
		this.userPhoneNumber = userPhoneNumber;
		this.transDateTime = transDateTime;
		this.userName = userName;
		this.pointMerchantId = pointMerchantId;
		this.gameId = gameId;
		this.numberSelectType = numberSelectType;
		this.betTotalAmount = betTotalAmount;
		this.pointTotalAmount = pointTotalAmount;
		this.callbackURL = callbackURL;
		this.betInfo = betInfo;
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
						"insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05,para06,para07,para08,para09,para10,para11,para12,para13,para14,para15,para16) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				int m = 1;
				ps.setLong(m++, this.getId());
				ps.setInt(m++, LOG_ID);
				ps.setString(m++, this.getChannelId());
				ps.setString(m++, this.getTransSerialNumber());
				ps.setString(m++, this.getTransData());
				ps.setString(m++, this.getChannelReserved());
				ps.setString(m++, this.getOrderNumber());
				ps.setString(m++, this.getUserPhoneNumber());
				ps.setString(m++, this.getUserName());
				ps.setString(m++, this.getPointMerchantId());
				ps.setString(m++, this.getGameId());
				ps.setString(m++, this.getTransDateTime().toString());
				ps.setInt(m++, this.getnumberSelectType());
				ps.setInt(m++, this.getBetTotalAmount());
				ps.setLong(m++, this.getPointTotalAmount());
				ps.setString(m++, this.getBetInfo().toString());
				ps.setString(m++, this.getCallbackURL());
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

	public Date getTransDateTime() {
		return transDateTime;
	}

	public void setTransDateTime(Date transDateTime) {
		this.transDateTime = transDateTime;
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

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPointMerchantId() {
		return pointMerchantId;
	}

	public void setPointMerchantId(String pointMerchantId) {
		this.pointMerchantId = pointMerchantId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getnumberSelectType() {
		return numberSelectType;
	}

	public void setnumberSelectType(int numberSelectType) {
		this.numberSelectType = numberSelectType;
	}

	public int getBetTotalAmount() {
		return betTotalAmount;
	}

	public void setBetTotalAmount(int betTotalAmount) {
		this.betTotalAmount = betTotalAmount;
	}

	public long getPointTotalAmount() {
		return pointTotalAmount;
	}

	public void setPointTotalAmount(long pointTotalAmount) {
		this.pointTotalAmount = pointTotalAmount;
	}

	public BetInfo getBetInfo() {
		return betInfo;
	}

	public void setBetDetail(BetInfo betInfo) {
		this.betInfo = betInfo;
	}

	public String getCallbackURL() {
		return callbackURL;
	}

	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}

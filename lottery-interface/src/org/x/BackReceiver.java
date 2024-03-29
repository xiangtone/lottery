package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;
import org.common.util.ThreadPool;
import org.x.info.BackBetBodyInfo;
import org.x.info.BackReq;
import org.x.info.BackReqBetInfo;
import org.x.info.PartnerOrderInfo;
import org.x.utils.AES;
import org.x.utils.ConnectionServiceLottery;

import com.alibaba.fastjson.JSON;

public class BackReceiver {

	private final static Logger LOG = Logger.getLogger(BackReceiver.class);

	private String info;
	private BackReq backReq;
	private String ip;
	private String aesKey;
	private String orderStatus = "test";
	private String partnerOrderInfoJson;
	private PartnerOrderInfo partnerOrderInfo;
	private String partnerId;
	private PreparedStatement ps = null;
	private Connection con = null;

	public void process() throws Exception {
		backReq = JSON.parseObject(info, BackReq.class);
		LOG.debug(backReq);
		identityEnvironment();
		String decryptInfo = AES.Decrypt(backReq.getBody(), aesKey);
		BackBetBodyInfo backBetBodyInfo = JSON.parseObject(decryptInfo, BackBetBodyInfo.class);
		LOG.debug(decryptInfo);
		LOG.debug(backReq.getHead().toString());
		switch (backReq.getHead().getBusinessId()) {
		case "3011":
			LOG.debug(backReq.getHead().getBusinessId());
			break;
		case "2011":
			LOG.debug(backReq.getHead().getBusinessId());
			List<BackReqBetInfo> betInfoList = backBetBodyInfo.getTicketInfoList();
			LOG.debug(backBetBodyInfo);
			LOG.debug(betInfoList);
			queryPartnerOrderInfoFromDb(backBetBodyInfo);
			sucessExchangeOrderInfoLogInsert(backBetBodyInfo);
			successExchangeBetsInfoLogInsert(betInfoList, backBetBodyInfo);
			break;
		default:
		}
		ThreadPool.mThreadPool.execute(new LogInsert(backReq.getHead().getChannelId(),
				backReq.getHead().getTransSerialNumber(), backReq.getHead().getBusinessId(), backReq.getBody(),
				decryptInfo, backBetBodyInfo.getOrderNumber(), backBetBodyInfo.getChannelReserved(),
				backBetBodyInfo.getIssueNumber(), backBetBodyInfo.getBetSuccAmount(),
				backBetBodyInfo.getOrderAcceptTime(), backBetBodyInfo.getResult(), backBetBodyInfo.getResultDesc(),
				backBetBodyInfo.getTicketInfoList(), ip));
	}

	private void queryPartnerOrderInfoFromDb(BackBetBodyInfo backBetBodyInfo) {
		LOG.debug(backBetBodyInfo.getOrderNumber());
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			String sql = "select * from `log_sync_generals` where id=?";
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, backBetBodyInfo.getOrderNumber());
			rs = ps.executeQuery();
			if (rs.next()) {
				partnerId = rs.getString("para01");
				partnerOrderInfoJson = rs.getString("para02");
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

	private void successExchangeBetsInfoLogInsert(List<BackReqBetInfo> betInfoList, BackBetBodyInfo backBetBodyInfo) {
		try {
			String betDetail = betInfoList.get(0).getBetDetail();
			do {
				con = ConnectionServiceLottery.getInstance().getConnectionForLottery();
				ps = con.prepareStatement(
						"insert into `tbl_success_exchange_bets` (ticketId,betDateTime,betDetail,orderNumber,insertTime,rewardlevel,rewardMoney,rewardUpdateTime) values (?,?,?,?,?,?,?,?)");
				int m = 1;
				ps.setString(m++, betInfoList.get(0).getTicketId());
				ps.setString(m++, betInfoList.get(0).getBetDateTime());
				ps.setString(m++, betDetail.substring(0, 21));
				ps.setString(m++, backBetBodyInfo.getOrderNumber());
				ps.setLong(m++, System.currentTimeMillis());
				ps.setLong(m++, 1);// rewardLevel
				ps.setInt(m++, 5);// rewardMoney
				ps.setLong(m++, 1);// rewardUpdateTime
				ps.executeUpdate();
				betDetail = betDetail.substring(21);
			} while (betDetail.length() != 0);
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

	// private void successExchangeTicketsInfoLogInsert(List<BackReqBetInfo>
	// betInfoList,
	// BackBetBodyInfo backBetBodyInfo) {
	// try {
	// con = ConnectionServiceLottery.getInstance().getConnectionForLottery();
	// ps = con.prepareStatement(
	// "insert into `tbl_success_exchange_tickets`
	// (ticketId,betDateTime,betDetail,orderNumber,insertTime,reward,rewardUpdateTime)
	// values (?,?,?,?,?,?,?)");
	// int m = 1;
	// ps.setString(m++, betInfoList.get(0).getTicketId());
	// ps.setString(m++, betInfoList.get(0).getBetDateTime());
	// ps.setString(m++, betInfoList.get(0).getBetDetail());
	// ps.setString(m++, backBetBodyInfo.getOrderNumber());
	// ps.setLong(m++, System.currentTimeMillis());
	// ps.setInt(m++, 5);// reward
	// ps.setLong(m++, 1);// rewardUpdateTime
	// ps.executeUpdate();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// if (con != null) {
	// try {
	// con.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// }

	private void sucessExchangeOrderInfoLogInsert(BackBetBodyInfo backBetBodyInfo) {
		partnerOrderInfo = JSON.parseObject(partnerOrderInfoJson, PartnerOrderInfo.class);
		// PartnerApi partnerApi = new PartnerApi();
		if (backBetBodyInfo == null) {
			LOG.error("backBetBodyInfo is null");
		} else {
			if (backBetBodyInfo.getResult() == 0) {
				try {
					con = ConnectionServiceLottery.getInstance().getConnectionForLottery();
					ps = con.prepareStatement(
							"insert into `tbl_success_exchange_orders` (orderNumber,issueNumber,betSuccAmount,orderAcceptTime,orderStatus,fromIp,insertTime,customMobile,userMobile,userName,pointMerchantId,gameId,numberSelectType,callbackURL,partnerId,partnerChannelId,partnerReserved,appId,partnerOrderNumber,partnerExchangeMethod) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					int m = 1;
					ps.setString(m++, backBetBodyInfo.getOrderNumber());
					ps.setString(m++, backBetBodyInfo.getIssueNumber());
					ps.setInt(m++, backBetBodyInfo.getBetSuccAmount());
					ps.setString(m++, backBetBodyInfo.getOrderAcceptTime().toString());
					ps.setString(m++, this.orderStatus);
					ps.setString(m++, ip);
					ps.setLong(m++, System.currentTimeMillis());
					ps.setString(m++, "");// customMobile
					ps.setString(m++, partnerOrderInfo.getUserPhoneNumber());// userMobile
					ps.setString(m++, partnerOrderInfo.getUserName());// userName
					ps.setString(m++, "");// pointMerchantId
					ps.setString(m++, "10001");// gameId
					ps.setInt(m++, partnerOrderInfo.getNumberSelectType());// numberSelectType
					ps.setString(m++, partnerOrderInfo.getPartnerCallbackURL());// callbackURL
					ps.setString(m++, partnerId);// partnerId
					ps.setString(m++, partnerOrderInfo.getPartnerChannelId());// partnerChannelId
					ps.setString(m++, partnerOrderInfo.getPartnerReserved());// partnerReserved
					ps.setString(m++, partnerOrderInfo.getAppId());
					ps.setString(m++, partnerOrderInfo.getPartnerOrderNumber());// partnerOrderNumber
					ps.setString(m++, "");// partnerExchangeMethod
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
	}

	private void identityEnvironment() {
		LOG.debug("ip:" + ip);
		switch (ip) {
		case "0:0:0:0:0:0:0:1":
			// test environment
			orderStatus = "test";
			aesKey = "54acf3110154acf3";
			break;
		case "124.205.38.84":
			// test environment
			orderStatus = "test";
			aesKey = "5118a4a8015118a4";
			break;
		case "182.48.118.176":
			// formal environment
			orderStatus = "formal";
			aesKey = "54acf3110154acf3";
			break;
		default:
			aesKey = "5118a4a8015118a4";
			break;
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

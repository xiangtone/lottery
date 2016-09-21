package org.x.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.common.util.ConnectionService;
import org.x.info.PartnerInfo;
import org.x.info.PartnerOrderInfo;
import org.x.utils.ConnectionServiceLottery;

public class DaoService {

	private final static Logger LOG = Logger.getLogger(DaoService.class);

	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;

	public PartnerInfo getTargetUrlByTitle(String id) {
		PartnerInfo result = null;
		try {
			con = ConnectionServiceLottery.getInstance().getConnectionForLottery();
			String sql = "SELECT * FROM `tbl_partners` where id=?;";
			// LOG.debug("query:" + sql);
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new PartnerInfo(rs.getString("id"), rs.getString("partnername"), rs.getString("state"),
						rs.getInt("realBalance"), rs.getInt("creditBalance"), rs.getString("method"),
						rs.getInt("unitPrice"), rs.getString("keyAES"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void queryPartnerOrderNumberFromDb(PartnerOrderInfo partnerOrderInfo) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionService.getInstance().getConnectionForLocal();
			String sql = "select * from `log_sync_generals` where para04=?";
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, partnerOrderInfo.getPartnerOrderNumber());
			rs = ps.executeQuery();
			if (rs.next()) {
				partnerOrderInfo.setPartnerOrderNumber(rs.getString("para04"));
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

	public void updateCreditBalance(String partnerId) {
		try {
			con = ConnectionServiceLottery.getInstance().getConnectionForLottery();
			String sql = "update `tbl_partners` set creditBalance=creditBalance-unitPrice where id=?";
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, partnerId);
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

	public void updateRealBalance(String partnerId) {
		try {
			con = ConnectionServiceLottery.getInstance().getConnectionForLottery();
			String sql = "update `tbl_partners` set realBalance=realBalance-unitPrice,creditBalance=realBalance where id=?";
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, partnerId);
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
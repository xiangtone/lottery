package org.x.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.x.ConnectionServiceConfig;
import org.x.info.PartnerInfo;

public class DaoService {

	private final static Logger LOG = Logger.getLogger(DaoService.class);

	public PartnerInfo getTargetUrlByTitle(String id) {
		PartnerInfo result = null;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ConnectionServiceConfig.getInstance().getConnectionForLocal();
			String sql = "SELECT * FROM `tbl_partners` where id=?;";
			LOG.debug("query");
			ps = con.prepareStatement(sql);
			int m = 1;
			ps.setString(m++, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new PartnerInfo(rs.getString("id"), rs.getString("partnername"), rs.getString("state"),
						rs.getInt("realBalance"), rs.getInt("creditBalance"), rs.getString("method"), rs.getInt("unitPrice"),
						rs.getString("keyAES"));
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

}
package org.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;

public class LogInsert implements Runnable {
  
  private static final int LOG_ID=3003;
  
  private Long id ; 
  private String channelId ; 
  private String transSerialNumber ; 
  private String decryptData ; 
  private String businessId ;

  public LogInsert(String channelId, String transSerialNumber, String decryptData , String businessId) {
    super();
    this.channelId = channelId;
    this.transSerialNumber = transSerialNumber;
    this.decryptData = decryptData;
    this.businessId = businessId;

  }


//public LogInsert(String channelId, String transSerialNumber, String transData) {
//	// TODO Auto-generated constructor stub
//	 super();
//	 this.channelId = channelId;
//	 this.transSerialNumber = transSerialNumber;
//	 this.transData = transData;
//}


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

  public String getDecryptData() {
    return decryptData;
  }

  public void setDecryptData(String decryptData) {
    this.decryptData = decryptData;
  }

  public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
  @Override
  public void run() {
    setId(GenerateIdService.getInstance().generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id")), "clicks", 1));
    if(this.id > 0){
      PreparedStatement ps = null;
      Connection con = null;
      try{
        con = ConnectionService.getInstance().getConnectionForLocal();
        ps = con.prepareStatement("insert into `log_async_generals` (id,logId,para01,para02,para03,para04,para05) values (?,?,?,?,?,?,?)");
        int m = 1;
        ps.setLong(m++, this.getId());
        ps.setInt(m++, LOG_ID);
        ps.setString(m++, this.getChannelId());
        ps.setString(m++, this.getTransSerialNumber());
        ps.setString(m++, this.getDecryptData());
        ps.setString(m++, this.getBusinessId());
        ps.executeUpdate();
      }catch(Exception e){
        // TODO Auto-generated catch block
        e.printStackTrace();
      }finally{
        if(con != null){
          try{
            con.close();
          }catch(SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }
  }
}

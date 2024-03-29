package org.x;

import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.yt.api.trans.QueryModifyBetAccountInfoUrlResultReq;
import com.iwt.yt.plugin.ClientTransService;
import com.iwt.yt.plugin.ClientTransServiceInterface;
import com.iwt.yt.plugin.ClientTransTestWebService;

public class DecryptWebQueryCallback {

	private final static Logger LOG = Logger.getLogger(DecryptWebQueryCallback.class);

	private String transData;
	private String channelId;
	private String transSerialNumber;
	private String ip;
	private String method;

	public void decrypt() throws Exception {
		LOG.debug(this);
		ClientTransServiceInterface clientTransService;
		if (method.equals("test")) {
			clientTransService = ClientTransTestWebService.getInstance();
		} else {
			clientTransService = ClientTransService.getInstance();
		}
		QueryModifyBetAccountInfoUrlResultReq result = clientTransService
				.decryptQueryModifyBetAccountInfoUrlResultReq(getChannelId(), getTransSerialNumber(), getTransData());
		LOG.debug(result);
		ThreadPool.mThreadPool.execute(new WebCallbackLogInsert(result.getHead().getChannelId(),
				result.getHead().getTransSerialNumber(), this.getTransData(), result.getBody().toString(),
				result.getBody().getChannelReserved(), result.getBody().getOrderNumber(), result.getBody().getResult(),
				result.getBody().getResultDesc(), ip));
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "DecryptWebCallback [transData=" + transData + ", channelId=" + channelId + ", transSerialNumber="
				+ transSerialNumber + "]";
	}

	public static void main(String args[]) throws RsaDecryptException {
		DecryptWebQueryCallback decryptWebQueryCallback = new DecryptWebQueryCallback();
		decryptWebQueryCallback.setTransData(
				"dF_s13NGcBmk3rXJ7-0k31IcHyDBAEe9mSDTf6-tL7haDdJF6CA7645GulRY5jDibmcz05XH3GEB4eTAfj8X09_UNlS4PP7748fU3bG5Sw763Fi8ps9_LDduYacHJ0Hbtvsvu36F95uj08_v6WgmlpMl_YGCzrEZ92XDqmCSRf1roAon-6Q_xVBDS5UtPKtjVtB6xStkrQGF8G9FHKrV6cJE4X4nerVpm0h86aLR3zrR_L3NaqKUmnZKp12-EPvtJ-ewU-dtKSmAMelwD9E2pdQ7FylIoc3-PQxs-BM7tg9SBh_XwF-d3jmMeBF_LN5e0b9OGSlhFSP8AD8_WLB0Rw");
		decryptWebQueryCallback.setChannelId("C12001");
		decryptWebQueryCallback.setTransSerialNumber("ff80808155a5e5a60155b8b640a6004e");
		QueryModifyBetAccountInfoUrlResultReq result = ClientTransService.getInstance()
				.decryptQueryModifyBetAccountInfoUrlResultReq(decryptWebQueryCallback.getChannelId(),
						decryptWebQueryCallback.getTransSerialNumber(), decryptWebQueryCallback.getTransData());
		LOG.debug(result);
	}

}

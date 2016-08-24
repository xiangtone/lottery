package org.x;

import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

import com.iwt.yt.api.trans.PointExchangeLotteryResultReq;
import com.iwt.yt.api.trans.TicketInfo;
import com.iwt.yt.plugin.ClientTransServiceInterface;
import com.iwt.yt.plugin.ClientTransTestWebService;

public class DecryptWebCallbackTest {

	private final static Logger LOG = Logger.getLogger(DecryptWebCallbackTest.class);

	private String transData;
	private String channelId;
	private String transSerialNumber;
	private TicketInfo ticketInfo = new TicketInfo();
	private String ip;

	public void decrypt() throws Exception {
		LOG.debug(this);
		ClientTransServiceInterface clientTransService = ClientTransTestWebService.getInstance();
		PointExchangeLotteryResultReq result = clientTransService.decryptPointExchangeLotteryResultReq(getChannelId(),
				getTransSerialNumber(), getTransData());
		LOG.debug(result);
		ThreadPool.mThreadPool.execute(new WebCallbackLogInsert(result.getHead().getChannelId(),
				result.getHead().getTransSerialNumber(), this.getTransData(), result.getBody().getChannelReserved(),
				result.getBody().getOrderNumber(), result.getBody().getResult(), result.getBody().getResultDesc(),
				result.getBody().getIssueNumber(), result.getBody().getBetSuccAmount(), ticketInfo.getBetDetail(), ip));
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

	@Override
	public String toString() {
		return "DecryptWebCallback [transData=" + transData + ", channelId=" + channelId + ", transSerialNumber="
				+ transSerialNumber + "]";
	}

}

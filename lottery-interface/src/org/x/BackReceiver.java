package org.x;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.x.info.BackReq;
import org.x.utils.AES;

import com.alibaba.fastjson.JSON;
import org.common.util.ThreadPool;

import org.x.LogInsert;

public class BackReceiver {

	private final static Logger LOG = Logger.getLogger(BackReceiver.class);

	private String info;
	private BackReq backReq;
	private String ip;

	public void process() throws Exception {
		backReq = JSON.parseObject(info, BackReq.class);
		LOG.debug(backReq);
		switch (ip) {
		case "124.205.38.84":
			break;
		default:
			break;
		}
		String decryptInfo = AES.Decrypt(backReq.getBody(), "54acf3110154acf3");
		LOG.debug(decryptInfo);
		LOG.debug(backReq.getHead().toString());
		switch (backReq.getHead().getBusinessId()) {
		case "3011":
			LOG.debug(backReq.getHead().getBusinessId());
			break;
		case "2011":
			LOG.debug(backReq.getHead().getBusinessId());
			break;
		default:
		}
		ThreadPool.mThreadPool
				.execute(new LogInsert(backReq.getHead().getChannelId(), backReq.getHead().getTransSerialNumber(),
						backReq.getHead().getBusinessId(), backReq.getBody(), decryptInfo, ""));
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

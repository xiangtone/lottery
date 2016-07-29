package org.x;

import org.apache.log4j.Logger;
import org.x.info.BackReq;
import org.x.utils.AES;

import com.alibaba.fastjson.JSON;

public class BackReceiver {

	private final static Logger LOG = Logger.getLogger(BackReceiver.class);

	private String info;
	private BackReq backReq;

	public void process() throws Exception {
		backReq = JSON.parseObject(info, BackReq.class);
		LOG.debug(backReq);
		LOG.debug(AES.Decrypt(backReq.getBody(), "5118a4a8015118a4"));
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

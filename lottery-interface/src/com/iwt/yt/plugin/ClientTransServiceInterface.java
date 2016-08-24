package com.iwt.yt.plugin;

import com.iwt.vasoss.common.security.exception.RsaEncryptException;
import com.iwt.yt.api.trans.PointExchangeLotteryReq;

public interface ClientTransServiceInterface {

	String encryptPointExchangeLotteryReq(PointExchangeLotteryReq req) throws RsaEncryptException;

}

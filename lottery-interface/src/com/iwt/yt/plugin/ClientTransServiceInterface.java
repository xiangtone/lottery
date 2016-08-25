package com.iwt.yt.plugin;

import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;
import com.iwt.yt.api.trans.PointExchangeLotteryReq;
import com.iwt.yt.api.trans.PointExchangeLotteryResultReq;
import com.iwt.yt.api.trans.QueryModifyBetAccountInfoUrlReq;
import com.iwt.yt.api.trans.QueryModifyBetAccountInfoUrlResultReq;

public interface ClientTransServiceInterface {

	String encryptPointExchangeLotteryReq(PointExchangeLotteryReq req) throws RsaEncryptException;

	String encryptQueryModifyBetAccountInfoUrlReq(QueryModifyBetAccountInfoUrlReq req) throws RsaEncryptException;

	PointExchangeLotteryResultReq decryptPointExchangeLotteryResultReq(String channelId, String transSerialNumber,
			String transData) throws RsaDecryptException;

	QueryModifyBetAccountInfoUrlResultReq decryptQueryModifyBetAccountInfoUrlResultReq(String channelId,
			String transSerialNumber, String transData) throws RsaDecryptException;

}

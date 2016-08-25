package com.iwt.yt.plugin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;
import com.iwt.yt.api.base.ReqHead;
import com.iwt.yt.api.trans.PointExchangeLotteryReq;
import com.iwt.yt.api.trans.PointExchangeLotteryResultReq;
import com.iwt.yt.api.trans.PointExchangeLotteryResultReqBody;
import com.iwt.yt.api.trans.QueryModifyBetAccountInfoUrlReq;
import com.iwt.yt.api.trans.QueryModifyBetAccountInfoUrlResultReq;
import com.iwt.yt.api.trans.QueryModifyBetAccountInfoUrlResultReqBody;
import com.iwt.yt.util.TestWebClientUtil;

public class ClientTransTestWebService implements ClientTransServiceInterface {

	private static ClientTransTestWebService clientTransService = new ClientTransTestWebService();
	private static TestWebClientUtil clientUtil = TestWebClientUtil.getInstance();

	/*    */
	/*    */ public static ClientTransTestWebService getInstance()
	/*    */ {
		/* 24 */ return clientTransService;
		/*    */ }

	/*    */
	/*    */ public String getChannelId()
	/*    */ {
		/* 29 */ return clientUtil.getChannelId();
		/*    */ }

	/*    */
	/*    */ public String getPointExchangeLotteryUrl()
	/*    */ {
		/* 34 */ return clientUtil.getPointExchangeLotteryUrl();
		/*    */ }

	/*    */
	/*    */ public String getQueryModifyBetAccountInfoUrl()
	/*    */ {
		/* 39 */ return clientUtil.getQueryModifyBetAccountInfoUrl();
		/*    */ }

	/*    */
	/*    */ public String encryptPointExchangeLotteryReq(PointExchangeLotteryReq pointExchangeLotteryReq)
			/*    */ throws RsaEncryptException
	/*    */ {
		/* 46 */ String channelId = pointExchangeLotteryReq.getHead().getChannelId();
		/* 47 */ String transEncryptData = JSON.toJSONString(pointExchangeLotteryReq.getBody(),
				/* 48 */ new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
		/* 49 */ return clientUtil.encrypt(channelId, transEncryptData);
		/*    */ }

	/*    */
	/*    */ public PointExchangeLotteryResultReq decryptPointExchangeLotteryResultReq(String channelId,
			String transSerialNumber, String transData)/*    */ throws RsaDecryptException
	/*    */ {
		/* 57 */ ReqHead head = new ReqHead(channelId);
		/* 58 */ head.setTransSerialNumber(transSerialNumber);
		/* 59 */ String transDecryptData = clientUtil.decrypt(channelId, transData);
		/* 60 */ PointExchangeLotteryResultReqBody body = (PointExchangeLotteryResultReqBody) JSON
				.parseObject(transDecryptData, /* 61 */ PointExchangeLotteryResultReqBody.class);
		/* 62 */ PointExchangeLotteryResultReq pointExchangeLotteryResultReq = new PointExchangeLotteryResultReq();
		/* 63 */ pointExchangeLotteryResultReq.setHead(head);
		/* 64 */ pointExchangeLotteryResultReq.setBody(body);
		/* 65 */ return pointExchangeLotteryResultReq;
		/*    */ }

	/*    */
	/*    */ public String encryptQueryModifyBetAccountInfoUrlReq(
			QueryModifyBetAccountInfoUrlReq queryModifyBetAccountInfoUrlReq)/*    */ throws RsaEncryptException
	/*    */ {
		/* 73 */ String channelId = queryModifyBetAccountInfoUrlReq.getHead().getChannelId();
		/* 74 */ String transEncryptData = JSON.toJSONString(queryModifyBetAccountInfoUrlReq.getBody(),
				/* 75 */ new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
		/* 76 */ return clientUtil.encrypt(channelId, transEncryptData);
		/*    */ }

	/*    */
	/*    */ public QueryModifyBetAccountInfoUrlResultReq decryptQueryModifyBetAccountInfoUrlResultReq(String channelId,
			String transSerialNumber, String transData)/*    */ throws RsaDecryptException
	/*    */ {
		/* 84 */ ReqHead head = new ReqHead(channelId);
		/* 85 */ head.setTransSerialNumber(transSerialNumber);
		/* 86 */ String transDecryptData = clientUtil.decrypt(channelId, transData);
		/* 87 */ QueryModifyBetAccountInfoUrlResultReqBody body = (QueryModifyBetAccountInfoUrlResultReqBody) JSON
				.parseObject(transDecryptData, /* 88 */ QueryModifyBetAccountInfoUrlResultReqBody.class);
		/* 89 */ QueryModifyBetAccountInfoUrlResultReq queryModifyBetAccountInfoUrlResultReq = new QueryModifyBetAccountInfoUrlResultReq();
		/* 90 */ queryModifyBetAccountInfoUrlResultReq.setHead(head);
		/* 91 */ queryModifyBetAccountInfoUrlResultReq.setBody(body);
		/* 92 */ return queryModifyBetAccountInfoUrlResultReq;
		/*    */ }
	/*    */ }
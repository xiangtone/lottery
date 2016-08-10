/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.serializer.SerializerFeature;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryResultReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryResultReqBody;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlResultReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlResultReqBody;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util.ClientUtil;
/*    */ import com.iwt.vasoss.common.security.exception.RsaDecryptException;
/*    */ import com.iwt.vasoss.common.security.exception.RsaEncryptException;
/*    */ 
/*    */ public class ClientTransService
/*    */ {
/* 18 */   private static ClientTransService clientTransService = new ClientTransService();
/*    */ 
/*    */   public static ClientTransService getInstance()
/*    */   {
/* 24 */     return clientTransService;
/*    */   }
/*    */ 
/*    */   public String getChannelId()
/*    */   {
/* 29 */     return ClientUtil.getInstance().getChannelId();
/*    */   }
/*    */ 
/*    */   public String getPointExchangeLotteryUrl()
/*    */   {
/* 34 */     return ClientUtil.getInstance().getPointExchangeLotteryUrl();
/*    */   }
/*    */ 
/*    */   public String getQueryModifyBetAccountInfoUrl()
/*    */   {
/* 39 */     return ClientUtil.getInstance().getQueryModifyBetAccountInfoUrl();
/*    */   }
/*    */ 
/*    */   public String encryptPointExchangeLotteryReq(PointExchangeLotteryReq pointExchangeLotteryReq)
/*    */     throws RsaEncryptException
/*    */   {
/* 46 */     String channelId = pointExchangeLotteryReq.getHead().getChannelId();
/* 47 */     String transEncryptData = JSON.toJSONString(pointExchangeLotteryReq.getBody(), 
/* 48 */       new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
/* 49 */     return ClientUtil.getInstance().encrypt(channelId, transEncryptData);
/*    */   }
/*    */ 
/*    */   public PointExchangeLotteryResultReq decryptPointExchangeLotteryResultReq(String channelId, String transSerialNumber, String transData)
/*    */     throws RsaDecryptException
/*    */   {
/* 57 */     ReqHead head = new ReqHead(channelId);
/* 58 */     head.setTransSerialNumber(transSerialNumber);
/* 59 */     String transDecryptData = ClientUtil.getInstance().decrypt(channelId, transData);
/* 60 */     PointExchangeLotteryResultReqBody body = (PointExchangeLotteryResultReqBody)JSON.parseObject(transDecryptData, 
/* 61 */       PointExchangeLotteryResultReqBody.class);
/* 62 */     PointExchangeLotteryResultReq pointExchangeLotteryResultReq = new PointExchangeLotteryResultReq();
/* 63 */     pointExchangeLotteryResultReq.setHead(head);
/* 64 */     pointExchangeLotteryResultReq.setBody(body);
/* 65 */     return pointExchangeLotteryResultReq;
/*    */   }
/*    */ 
/*    */   public String encryptQueryModifyBetAccountInfoUrlReq(QueryModifyBetAccountInfoUrlReq queryModifyBetAccountInfoUrlReq)
/*    */     throws RsaEncryptException
/*    */   {
/* 73 */     String channelId = queryModifyBetAccountInfoUrlReq.getHead().getChannelId();
/* 74 */     String transEncryptData = JSON.toJSONString(queryModifyBetAccountInfoUrlReq.getBody(), 
/* 75 */       new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
/* 76 */     return ClientUtil.getInstance().encrypt(channelId, transEncryptData);
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlResultReq decryptQueryModifyBetAccountInfoUrlResultReq(String channelId, String transSerialNumber, String transData)
/*    */     throws RsaDecryptException
/*    */   {
/* 84 */     ReqHead head = new ReqHead(channelId);
/* 85 */     head.setTransSerialNumber(transSerialNumber);
/* 86 */     String transDecryptData = ClientUtil.getInstance().decrypt(channelId, transData);
/* 87 */     QueryModifyBetAccountInfoUrlResultReqBody body = (QueryModifyBetAccountInfoUrlResultReqBody)JSON.parseObject(transDecryptData, 
/* 88 */       QueryModifyBetAccountInfoUrlResultReqBody.class);
/* 89 */     QueryModifyBetAccountInfoUrlResultReq queryModifyBetAccountInfoUrlResultReq = new QueryModifyBetAccountInfoUrlResultReq();
/* 90 */     queryModifyBetAccountInfoUrlResultReq.setHead(head);
/* 91 */     queryModifyBetAccountInfoUrlResultReq.setBody(body);
/* 92 */     return queryModifyBetAccountInfoUrlResultReq;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.ClientTransService
 * JD-Core Version:    0.6.2
 */
/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.serializer.SerializerFeature;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReqBody;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryResultReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlReqBody;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlResultReq;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util.ServerUtil;
/*    */ import com.iwt.vasoss.common.security.exception.RsaDecryptException;
/*    */ import com.iwt.vasoss.common.security.exception.RsaEncryptException;
/*    */ 
/*    */ public class ServerTransService
/*    */ {
/* 18 */   private static ServerTransService serverTransService = new ServerTransService();
/*    */ 
/*    */   public static ServerTransService getInstance()
/*    */   {
/* 24 */     return serverTransService;
/*    */   }
/*    */ 
/*    */   public String encryptPointExchangeLotteryResultReq(PointExchangeLotteryResultReq pointExchangeLotteryResultReq)
/*    */     throws RsaEncryptException
/*    */   {
/* 31 */     String channelId = pointExchangeLotteryResultReq.getHead().getChannelId();
/* 32 */     String transEncryptData = JSON.toJSONString(pointExchangeLotteryResultReq.getBody(), 
/* 33 */       new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
/* 34 */     return ServerUtil.getInstance().encrypt(channelId, transEncryptData);
/*    */   }
/*    */ 
/*    */   public PointExchangeLotteryReq decryptPointExchangeLotteryReq(String channelId, String transSerialNumber, String transData)
/*    */     throws RsaDecryptException
/*    */   {
/* 42 */     ReqHead head = new ReqHead(channelId);
/* 43 */     head.setTransSerialNumber(transSerialNumber);
/* 44 */     String transDecryptData = ServerUtil.getInstance().decrypt(channelId, transData);
/* 45 */     PointExchangeLotteryReqBody body = (PointExchangeLotteryReqBody)JSON.parseObject(transDecryptData, PointExchangeLotteryReqBody.class);
/* 46 */     PointExchangeLotteryReq pointExchangeLotteryReq = new PointExchangeLotteryReq();
/* 47 */     pointExchangeLotteryReq.setHead(head);
/* 48 */     pointExchangeLotteryReq.setBody(body);
/* 49 */     return pointExchangeLotteryReq;
/*    */   }
/*    */ 
/*    */   public String encryptQueryModifyBetAccountInfoUrlResultReq(QueryModifyBetAccountInfoUrlResultReq queryModifyBetAccountInfoUrlResultReq)
/*    */     throws RsaEncryptException
/*    */   {
/* 57 */     String channelId = queryModifyBetAccountInfoUrlResultReq.getHead().getChannelId();
/* 58 */     String transEncryptData = JSON.toJSONString(queryModifyBetAccountInfoUrlResultReq.getBody(), 
/* 59 */       new SerializerFeature[] { SerializerFeature.WriteMapNullValue });
/* 60 */     return ServerUtil.getInstance().encrypt(channelId, transEncryptData);
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlReq decryptQueryModifyBetAccountInfoUrlReq(String channelId, String transSerialNumber, String transData)
/*    */     throws RsaDecryptException
/*    */   {
/* 68 */     ReqHead head = new ReqHead(channelId);
/* 69 */     head.setTransSerialNumber(transSerialNumber);
/* 70 */     String transDecryptData = ServerUtil.getInstance().decrypt(channelId, transData);
/* 71 */     QueryModifyBetAccountInfoUrlReqBody body = (QueryModifyBetAccountInfoUrlReqBody)JSON.parseObject(transDecryptData, 
/* 72 */       QueryModifyBetAccountInfoUrlReqBody.class);
/* 73 */     QueryModifyBetAccountInfoUrlReq queryModifyBetAccountInfoUrlReq = new QueryModifyBetAccountInfoUrlReq();
/* 74 */     queryModifyBetAccountInfoUrlReq.setHead(head);
/* 75 */     queryModifyBetAccountInfoUrlReq.setBody(body);
/* 76 */     return queryModifyBetAccountInfoUrlReq;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.ServerTransService
 * JD-Core Version:    0.6.2
 */
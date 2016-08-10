/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.IMessage;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PointExchangeLotteryReq
/*    */   implements IMessage<ReqHead, PointExchangeLotteryReqBody>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ReqHead head;
/*    */   private PointExchangeLotteryReqBody body;
/*    */ 
/*    */   public PointExchangeLotteryReq()
/*    */   {
/*    */   }
/*    */ 
/*    */   public PointExchangeLotteryReq(String channelIdId, String channelReserved, String orderNumber, String userPhoneNumber, String userName, String pointMerchantId, String gameId, int numberSelectType, int betTotalAmount, long pointTotalAmount, String callbackURL, List<BetInfo> betInfoList)
/*    */   {
/* 31 */     this();
/* 32 */     this.head = new ReqHead(channelIdId);
/* 33 */     this.body = new PointExchangeLotteryReqBody(channelReserved, orderNumber, userPhoneNumber, userName, pointMerchantId, gameId, numberSelectType, betTotalAmount, pointTotalAmount, callbackURL, betInfoList);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 39 */     return "PointExchangeLotteryReq " + super.toString() + " [head=" + this.head + ", body=" + this.body + "]";
/*    */   }
/*    */ 
/*    */   public ReqHead getHead()
/*    */   {
/* 44 */     return this.head;
/*    */   }
/*    */ 
/*    */   public void setHead(ReqHead head)
/*    */   {
/* 49 */     this.head = head;
/*    */   }
/*    */ 
/*    */   public PointExchangeLotteryReqBody getBody()
/*    */   {
/* 54 */     return this.body;
/*    */   }
/*    */ 
/*    */   public void setBody(PointExchangeLotteryReqBody body)
/*    */   {
/* 59 */     this.body = body;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReq
 * JD-Core Version:    0.6.2
 */
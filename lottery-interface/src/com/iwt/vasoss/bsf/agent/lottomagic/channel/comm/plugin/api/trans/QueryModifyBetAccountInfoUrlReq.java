/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.IMessage;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
/*    */ 
/*    */ public class QueryModifyBetAccountInfoUrlReq
/*    */   implements IMessage<ReqHead, QueryModifyBetAccountInfoUrlReqBody>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ReqHead head;
/*    */   private QueryModifyBetAccountInfoUrlReqBody body;
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlReq()
/*    */   {
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlReq(String channelIdId, String orderNumber, String userPhoneNumber, String callbackURL)
/*    */   {
/* 27 */     this();
/* 28 */     this.head = new ReqHead(channelIdId);
/* 29 */     this.body = new QueryModifyBetAccountInfoUrlReqBody(orderNumber, userPhoneNumber, callbackURL);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 34 */     return "QueryModifyBetAccountInfoUrlReq " + super.toString() + " [head=" + this.head + ", body=" + this.body + "]";
/*    */   }
/*    */ 
/*    */   public ReqHead getHead()
/*    */   {
/* 39 */     return this.head;
/*    */   }
/*    */ 
/*    */   public void setHead(ReqHead head)
/*    */   {
/* 44 */     this.head = head;
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlReqBody getBody()
/*    */   {
/* 49 */     return this.body;
/*    */   }
/*    */ 
/*    */   public void setBody(QueryModifyBetAccountInfoUrlReqBody body)
/*    */   {
/* 54 */     this.body = body;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlReq
 * JD-Core Version:    0.6.2
 */
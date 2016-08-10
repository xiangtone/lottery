/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.IMessage;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
/*    */ 
/*    */ public class QueryModifyBetAccountInfoUrlResultReq
/*    */   implements IMessage<ReqHead, QueryModifyBetAccountInfoUrlResultReqBody>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ReqHead head;
/*    */   private QueryModifyBetAccountInfoUrlResultReqBody body;
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlResultReq()
/*    */   {
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlResultReq(String channelIdId, int result, String resultDesc, String orderNumber)
/*    */   {
/* 27 */     this();
/* 28 */     this.head = new ReqHead(channelIdId);
/* 29 */     this.body = new QueryModifyBetAccountInfoUrlResultReqBody(result, resultDesc, orderNumber);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 34 */     return "QueryModifyBetAccountInfoUrlResultReq " + super.toString() + " [head=" + this.head + ", body=" + this.body + "]";
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
/*    */   public QueryModifyBetAccountInfoUrlResultReqBody getBody()
/*    */   {
/* 49 */     return this.body;
/*    */   }
/*    */ 
/*    */   public void setBody(QueryModifyBetAccountInfoUrlResultReqBody body)
/*    */   {
/* 54 */     this.body = body;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlResultReq
 * JD-Core Version:    0.6.2
 */
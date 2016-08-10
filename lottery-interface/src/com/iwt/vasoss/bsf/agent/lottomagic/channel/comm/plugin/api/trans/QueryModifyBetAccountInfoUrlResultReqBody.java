/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.TransResultReqBody;
/*    */ 
/*    */ public class QueryModifyBetAccountInfoUrlResultReqBody extends TransResultReqBody
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderNumber;
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlResultReqBody()
/*    */   {
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlResultReqBody(int result, String resultDesc, String orderNumber)
/*    */   {
/* 42 */     this();
/* 43 */     this.result = result;
/* 44 */     this.resultDesc = resultDesc;
/* 45 */     this.orderNumber = orderNumber;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 50 */     return super.toString() + " [orderNumber=" + this.orderNumber + "]";
/*    */   }
/*    */ 
/*    */   public String getOrderNumber() {
/* 54 */     return this.orderNumber;
/*    */   }
/*    */ 
/*    */   public void setOrderNumber(String orderNumber) {
/* 58 */     this.orderNumber = orderNumber;
/*    */   }
/*    */ 
/*    */   public static enum Result
/*    */   {
/* 23 */     userCancel(
/* 24 */       2002);
/*    */ 
/*    */     private int value;
/*    */ 
/*    */     public int getValue() {
/* 29 */       return this.value;
/*    */     }
/*    */ 
/*    */     private Result(int value) {
/* 33 */       this.value = value;
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlResultReqBody
 * JD-Core Version:    0.6.2
 */
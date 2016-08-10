/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.TransReqBody;
/*    */ 
/*    */ public class QueryModifyBetAccountInfoUrlReqBody extends TransReqBody
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderNumber;
/*    */   private String userPhoneNumber;
/*    */   private String callbackURL;
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlReqBody()
/*    */   {
/*    */   }
/*    */ 
/*    */   public QueryModifyBetAccountInfoUrlReqBody(String orderNumber, String userPhoneNumber, String callbackURL)
/*    */   {
/* 29 */     this();
/* 30 */     this.orderNumber = orderNumber;
/* 31 */     this.userPhoneNumber = userPhoneNumber;
/* 32 */     this.callbackURL = callbackURL;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 37 */     return super.toString() + " [orderNumber=" + this.orderNumber + ", userPhoneNumber=" + this.userPhoneNumber + 
/* 38 */       ", callbackURL=" + this.callbackURL + "]";
/*    */   }
/*    */ 
/*    */   public String getOrderNumber() {
/* 42 */     return this.orderNumber;
/*    */   }
/*    */ 
/*    */   public void setOrderNumber(String orderNumber) {
/* 46 */     this.orderNumber = orderNumber;
/*    */   }
/*    */ 
/*    */   public String getUserPhoneNumber() {
/* 50 */     return this.userPhoneNumber;
/*    */   }
/*    */ 
/*    */   public void setUserPhoneNumber(String userPhoneNumber) {
/* 54 */     this.userPhoneNumber = userPhoneNumber;
/*    */   }
/*    */ 
/*    */   public String getCallbackURL() {
/* 58 */     return this.callbackURL;
/*    */   }
/*    */ 
/*    */   public void setCallbackURL(String callbackURL) {
/* 62 */     this.callbackURL = callbackURL;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.QueryModifyBetAccountInfoUrlReqBody
 * JD-Core Version:    0.6.2
 */
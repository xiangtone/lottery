/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base;
/*    */ 
/*    */ public abstract class TransResultReqBody extends TransReqBody
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected int result;
/*    */   protected String resultDesc;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 52 */     return super.toString() + " [result=" + this.result + ", resultDesc=" + this.resultDesc + "]";
/*    */   }
/*    */ 
/*    */   public int getResult() {
/* 56 */     return this.result;
/*    */   }
/*    */ 
/*    */   public void setResult(int result) {
/* 60 */     this.result = result;
/*    */   }
/*    */ 
/*    */   public String getResultDesc() {
/* 64 */     return this.resultDesc;
/*    */   }
/*    */ 
/*    */   public void setResultDesc(String resultDesc) {
/* 68 */     this.resultDesc = resultDesc;
/*    */   }
/*    */ 
/*    */   public static enum Result
/*    */   {
/* 17 */     success(0), 
/*    */ 
/* 19 */     invalidChannelId(101), 
/*    */ 
/* 21 */     dataPacketDecryptionFailed(103), 
/*    */ 
/* 23 */     dataPacketParsingError(105), 
/*    */ 
/* 25 */     unknownError(999), 
/*    */ 
/* 27 */     repeatOrderNumber(1000), 
/*    */ 
/* 29 */     notToIssueBeginTime(1001), 
/*    */ 
/* 31 */     issueEnded(1002), 
/*    */ 
/* 33 */     temporaryStopSale(1003), 
/*    */ 
/* 35 */     regionStopSaleTheGame(1004), 
/*    */ 
/* 37 */     prepayAmountBalanceLess(1005);
/*    */ 
/*    */     private int value;
/*    */ 
/*    */     public int getValue() {
/* 42 */       return this.value;
/*    */     }
/*    */ 
/*    */     private Result(int value) {
/* 46 */       this.value = value;
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.TransResultReqBody
 * JD-Core Version:    0.6.2
 */
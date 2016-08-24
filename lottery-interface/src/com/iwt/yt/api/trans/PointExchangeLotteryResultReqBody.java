/*     */ package com.iwt.yt.api.trans;
/*     */ 
/*     */ import com.alibaba.fastjson.annotation.JSONField;
import com.iwt.yt.api.base.TransResultReqBody;

/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PointExchangeLotteryResultReqBody extends TransResultReqBody
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String orderNumber;
/*     */   private String issueNumber;
/*     */   private int betSuccAmount;
/*     */ 
/*     */   @JSONField(format="yyyy-MM-dd HH:mm:ss")
/*     */   private Date orderAcceptTime;
/*     */   private List<TicketInfo> ticketInfoList;
/*     */ 
/*     */   public PointExchangeLotteryResultReqBody()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PointExchangeLotteryResultReqBody(String channelReserved, int result, String resultDesc, String orderNumber, String issueNumber, int betSuccAmount, Date orderAcceptTime, List<TicketInfo> ticketInfoList)
/*     */   {
/*  64 */     this();
/*  65 */     this.channelReserved = channelReserved;
/*  66 */     this.result = result;
/*  67 */     this.resultDesc = resultDesc;
/*  68 */     this.orderNumber = orderNumber;
/*  69 */     this.issueNumber = issueNumber;
/*  70 */     this.betSuccAmount = betSuccAmount;
/*  71 */     this.orderAcceptTime = orderAcceptTime;
/*  72 */     this.ticketInfoList = ticketInfoList;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  77 */     return super.toString() + " [orderNumber=" + this.orderNumber + ", issueNumber=" + this.issueNumber + ", betSuccAmount=" + this.betSuccAmount + ", orderAcceptTime=" + this.orderAcceptTime + ", ticketInfoList=" + this.ticketInfoList + "]";
/*     */   }
/*     */ 
/*     */   public String getOrderNumber()
/*     */   {
/*  82 */     return this.orderNumber;
/*     */   }
/*     */ 
/*     */   public void setOrderNumber(String orderNumber) {
/*  86 */     this.orderNumber = orderNumber;
/*     */   }
/*     */ 
/*     */   public String getIssueNumber() {
/*  90 */     return this.issueNumber;
/*     */   }
/*     */ 
/*     */   public void setIssueNumber(String issueNumber) {
/*  94 */     this.issueNumber = issueNumber;
/*     */   }
/*     */ 
/*     */   public int getBetSuccAmount() {
/*  98 */     return this.betSuccAmount;
/*     */   }
/*     */ 
/*     */   public void setBetSuccAmount(int betSuccAmount) {
/* 102 */     this.betSuccAmount = betSuccAmount;
/*     */   }
/*     */ 
/*     */   public Date getOrderAcceptTime() {
/* 106 */     return this.orderAcceptTime;
/*     */   }
/*     */ 
/*     */   public void setOrderAcceptTime(Date orderAcceptTime) {
/* 110 */     this.orderAcceptTime = orderAcceptTime;
/*     */   }
/*     */ 
/*     */   public List<TicketInfo> getTicketInfoList() {
/* 114 */     return this.ticketInfoList;
/*     */   }
/*     */ 
/*     */   public void setTicketInfoList(List<TicketInfo> ticketInfoList) {
/* 118 */     this.ticketInfoList = ticketInfoList;
/*     */   }
/*     */ 
/*     */   public static enum Result
/*     */   {
/*  41 */     partialSuccess(2000), 
/*     */ 
/*  43 */     orderAccepted(2001), 
/*     */ 
/*  45 */     userCancel(2002);
/*     */ 
/*     */     private int value;
/*     */ 
/*     */     public int getValue() {
/*  50 */       return this.value;
/*     */     }
/*     */ 
/*     */     private Result(int value) {
/*  54 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryResultReqBody
 * JD-Core Version:    0.6.2
 */
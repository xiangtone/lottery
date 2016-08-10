/*     */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*     */ 
/*     */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.TransReqBody;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PointExchangeLotteryReqBody extends TransReqBody
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String orderNumber;
/*     */   private String userPhoneNumber;
/*     */   private String userName;
/*     */   private String pointMerchantId;
/*     */   private String gameId;
/*     */   private int numberSelectType;
/*     */   private int betTotalAmount;
/*     */   private long pointTotalAmount;
/*     */   private String callbackURL;
/*     */   private List<BetInfo> betInfoList;
/*     */ 
/*     */   public PointExchangeLotteryReqBody()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PointExchangeLotteryReqBody(String channelReserved, String orderNumber, String userPhoneNumber, String userName, String pointMerchantId, String gameId, int numberSelectType, int betTotalAmount, long pointTotalAmount, String callbackURL, List<BetInfo> betInfoList)
/*     */   {
/*  94 */     this();
/*  95 */     this.channelReserved = channelReserved;
/*  96 */     this.orderNumber = orderNumber;
/*  97 */     this.userPhoneNumber = userPhoneNumber;
/*  98 */     this.userName = userName;
/*  99 */     this.pointMerchantId = pointMerchantId;
/* 100 */     this.gameId = gameId;
/* 101 */     this.numberSelectType = numberSelectType;
/* 102 */     this.betTotalAmount = betTotalAmount;
/* 103 */     this.pointTotalAmount = pointTotalAmount;
/* 104 */     this.callbackURL = callbackURL;
/* 105 */     this.betInfoList = betInfoList;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 110 */     return super.toString() + " [orderNumber=" + this.orderNumber + ", userPhoneNumber=" + this.userPhoneNumber + ", userName=" + this.userName + ", pointMerchantId=" + this.pointMerchantId + ", gameId=" + this.gameId + ", numberSelectType=" + this.numberSelectType + ", betTotalAmount=" + this.betTotalAmount + ", pointTotalAmount=" + this.pointTotalAmount + ", callbackURL=" + this.callbackURL + ", betInfoList=" + this.betInfoList + "]";
/*     */   }
/*     */ 
/*     */   public String getOrderNumber()
/*     */   {
/* 117 */     return this.orderNumber;
/*     */   }
/*     */ 
/*     */   public void setOrderNumber(String orderNumber) {
/* 121 */     this.orderNumber = orderNumber;
/*     */   }
/*     */ 
/*     */   public String getUserPhoneNumber() {
/* 125 */     return this.userPhoneNumber;
/*     */   }
/*     */ 
/*     */   public void setUserPhoneNumber(String userPhoneNumber) {
/* 129 */     this.userPhoneNumber = userPhoneNumber;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/* 133 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 137 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getPointMerchantId() {
/* 141 */     return this.pointMerchantId;
/*     */   }
/*     */ 
/*     */   public void setPointMerchantId(String pointMerchantId) {
/* 145 */     this.pointMerchantId = pointMerchantId;
/*     */   }
/*     */ 
/*     */   public String getGameId() {
/* 149 */     return this.gameId;
/*     */   }
/*     */ 
/*     */   public void setGameId(String gameId) {
/* 153 */     this.gameId = gameId;
/*     */   }
/*     */ 
/*     */   public int getNumberSelectType() {
/* 157 */     return this.numberSelectType;
/*     */   }
/*     */ 
/*     */   public void setNumberSelectType(int numberSelectType) {
/* 161 */     this.numberSelectType = numberSelectType;
/*     */   }
/*     */ 
/*     */   public int getBetTotalAmount() {
/* 165 */     return this.betTotalAmount;
/*     */   }
/*     */ 
/*     */   public void setBetTotalAmount(int betTotalAmount) {
/* 169 */     this.betTotalAmount = betTotalAmount;
/*     */   }
/*     */ 
/*     */   public long getPointTotalAmount() {
/* 173 */     return this.pointTotalAmount;
/*     */   }
/*     */ 
/*     */   public void setPointTotalAmount(long pointTotalAmount) {
/* 177 */     this.pointTotalAmount = pointTotalAmount;
/*     */   }
/*     */ 
/*     */   public String getCallbackURL() {
/* 181 */     return this.callbackURL;
/*     */   }
/*     */ 
/*     */   public void setCallbackURL(String callbackURL) {
/* 185 */     this.callbackURL = callbackURL;
/*     */   }
/*     */ 
/*     */   public List<BetInfo> getBetInfoList() {
/* 189 */     return this.betInfoList;
/*     */   }
/*     */ 
/*     */   public void setBetInfoList(List<BetInfo> betInfoList) {
/* 193 */     this.betInfoList = betInfoList;
/*     */   }
/*     */ 
/*     */   public static enum NumberSelectType
/*     */   {
/*  68 */     machine(1), 
/*     */ 
/*  70 */     optional(2), 
/*     */ 
/*  72 */     channelMachine(11), 
/*     */ 
/*  74 */     channelOptional(12);
/*     */ 
/*     */     private int value;
/*     */ 
/*     */     public int getValue() {
/*  79 */       return this.value;
/*     */     }
/*     */ 
/*     */     private NumberSelectType(int value) {
/*  83 */       this.value = value;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static enum GameId
/*     */   {
/*  50 */     B001("10001"), 
/*     */ 
/*  52 */     QL730("10003");
/*     */ 
/*     */     private String value;
/*     */ 
/*     */     public String getValue() {
/*  57 */       return this.value;
/*     */     }
/*     */ 
/*     */     private GameId(String value) {
/*  61 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReqBody
 * JD-Core Version:    0.6.2
 */
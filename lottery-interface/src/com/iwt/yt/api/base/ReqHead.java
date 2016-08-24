/*    */ package com.iwt.yt.api.base;
/*    */ 
/*    */ import com.iwt.vasoss.common.id.UuidHexGenerator;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ReqHead
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   public static UuidHexGenerator uuidGen = new UuidHexGenerator();
/*    */   private String channelId;
/*    */   private String transSerialNumber;
/*    */ 
/*    */   public ReqHead()
/*    */   {
/* 24 */     this.transSerialNumber = uuidGen.generate();
/*    */   }
/*    */ 
/*    */   public ReqHead(String channelId) {
/* 28 */     this();
/* 29 */     this.channelId = channelId;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 34 */     return "[channelId=" + this.channelId + ", transSerialNumber=" + this.transSerialNumber + "]";
/*    */   }
/*    */ 
/*    */   public String getChannelId() {
/* 38 */     return this.channelId;
/*    */   }
/*    */ 
/*    */   public void setChannelId(String channelId) {
/* 42 */     this.channelId = channelId;
/*    */   }
/*    */ 
/*    */   public String getTransSerialNumber() {
/* 46 */     return this.transSerialNumber;
/*    */   }
/*    */ 
/*    */   public void setTransSerialNumber(String transSerialNumber) {
/* 50 */     this.transSerialNumber = transSerialNumber;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead
 * JD-Core Version:    0.6.2
 */
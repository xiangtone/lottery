/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.alibaba.fastjson.annotation.JSONField;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class TicketInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String ticketId;
/*    */ 
/*    */   @JSONField(format="yyyy-MM-dd HH:mm:ss")
/*    */   private Date betDateTime;
/*    */   private String betDetail;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 30 */     return "[ticketId=" + this.ticketId + ", betDateTime=" + this.betDateTime + ", betDetail=" + this.betDetail + "]";
/*    */   }
/*    */ 
/*    */   public String getTicketId() {
/* 34 */     return this.ticketId;
/*    */   }
/*    */ 
/*    */   public void setTicketId(String ticketId) {
/* 38 */     this.ticketId = ticketId;
/*    */   }
/*    */ 
/*    */   public Date getBetDateTime() {
/* 42 */     return this.betDateTime;
/*    */   }
/*    */ 
/*    */   public void setBetDateTime(Date betDateTime) {
/* 46 */     this.betDateTime = betDateTime;
/*    */   }
/*    */ 
/*    */   public String getBetDetail() {
/* 50 */     return this.betDetail;
/*    */   }
/*    */ 
/*    */   public void setBetDetail(String betDetail) {
/* 54 */     this.betDetail = betDetail;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.TicketInfo
 * JD-Core Version:    0.6.2
 */
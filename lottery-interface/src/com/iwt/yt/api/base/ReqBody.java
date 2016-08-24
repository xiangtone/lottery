/*    */ package com.iwt.yt.api.base;
/*    */ 
/*    */ import com.alibaba.fastjson.annotation.JSONField;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class ReqBody
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   @JSONField(format="yyyy-MM-dd HH:mm:ss")
/*    */   protected Date transDateTime;
/*    */ 
/*    */   public ReqBody()
/*    */   {
/* 18 */     this.transDateTime = new Date();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 23 */     return " [transDateTime=" + this.transDateTime + "]";
/*    */   }
/*    */ 
/*    */   public Date getTransDateTime() {
/* 27 */     return this.transDateTime;
/*    */   }
/*    */ 
/*    */   public void setTransDateTime(Date transDateTime) {
/* 31 */     this.transDateTime = transDateTime;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqBody
 * JD-Core Version:    0.6.2
 */
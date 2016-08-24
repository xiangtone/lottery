/*    */ package com.iwt.yt.api.trans;
/*    */ 
/*    */ import java.io.Serializable;

import javax.swing.JOptionPane;
/*    */ 
/*    */ public class BetInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String betMode;
/*    */   private String betDetail;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 36 */     return "[betMode=" + this.betMode + ", betDetail=" + this.betDetail + "]";
/*    */   }
/*    */ 
/*    */   public String getBetMode() {
/* 40 */     return this.betMode;
/*    */   }
/*    */ 
/*    */   public void setBetMode(String betMode) {
/* 44 */     this.betMode = betMode;
/*    */   }
/*    */ 
/*    */   public String getBetDetail() {
/* 48 */     return this.betDetail;
/*    */   }
/*    */ 
/*    */   public void setBetDetail(String betDetail) {
/* 52 */     this.betDetail = betDetail;
/*    */   }

/*    */   public static enum BetMode
/*    */   {
/* 19 */     single("101"), 
/*    */ 
/* 21 */     multiple("102");
/*    */ 
/*    */     private String value;
/*    */ 
/*    */     public String getValue() {
/* 26 */       return this.value;
/*    */     }
/*    */ 
/*    */     private BetMode(String value) {
/* 30 */       this.value = value;
/*    */     }
/*    */   }
//			
/*    */ }


/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.BetInfo
 * JD-Core Version:    0.6.2
 */
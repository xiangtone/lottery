/*    */ package com.iwt.yt.api.base;
/*    */ 
/*    */ public abstract class TransReqBody extends ReqBody
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String channelReserved;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 13 */     return super.toString() + " [channelReserved=" + this.channelReserved + "]";
/*    */   }
/*    */ 
/*    */   public String getChannelReserved() {
/* 17 */     return this.channelReserved;
/*    */   }
/*    */ 
/*    */   public void setChannelReserved(String channelReserved) {
/* 21 */     this.channelReserved = channelReserved;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.TransReqBody
 * JD-Core Version:    0.6.2
 */
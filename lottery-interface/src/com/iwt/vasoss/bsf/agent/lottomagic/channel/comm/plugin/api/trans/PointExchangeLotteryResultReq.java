/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans;
/*    */ 
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.IMessage;
/*    */ import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PointExchangeLotteryResultReq
/*    */   implements IMessage<ReqHead, PointExchangeLotteryResultReqBody>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ReqHead head;
/*    */   private PointExchangeLotteryResultReqBody body;
/*    */ 
/*    */   public PointExchangeLotteryResultReq()
/*    */   {
/*    */   }
/*    */ 
/*    */   public PointExchangeLotteryResultReq(String channelIdId, String channelReserved, int result, String resultDesc, String orderNumber, String issueNumber, int betSuccAmount, Date orderAcceptTime, List<TicketInfo> ticketInfoList)
/*    */   {
/* 31 */     this();
/* 32 */     this.head = new ReqHead(channelIdId);
/* 33 */     this.body = new PointExchangeLotteryResultReqBody(channelReserved, result, resultDesc, orderNumber, issueNumber, betSuccAmount, orderAcceptTime, ticketInfoList);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 39 */     return "PointExchangeLotteryResultReq " + super.toString() + " [head=" + this.head + ", body=" + this.body + "]";
/*    */   }
/*    */ 
/*    */   public ReqHead getHead()
/*    */   {
/* 44 */     return this.head;
/*    */   }
/*    */ 
/*    */   public void setHead(ReqHead head)
/*    */   {
/* 49 */     this.head = head;
/*    */   }
/*    */ 
/*    */   public PointExchangeLotteryResultReqBody getBody()
/*    */   {
/* 54 */     return this.body;
/*    */   }
/*    */ 
/*    */   public void setBody(PointExchangeLotteryResultReqBody body)
/*    */   {
/* 59 */     this.body = body;
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryResultReq
 * JD-Core Version:    0.6.2
 */
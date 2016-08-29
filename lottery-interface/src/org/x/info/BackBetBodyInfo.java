package org.x.info;

import java.util.Date;
import java.util.List;

public class BackBetBodyInfo {

	private int result;
	private String issueNumber;
	private String orderNumber;
	private int betSuccAmount;
	private Date orderAcceptTime;
	private String channelReserved;
	private String resultDesc;
	private List<BackReqBetInfo> ticketInfoList;

	public String getChannelReserved() {
		return channelReserved;
	}

	public void setChannelReserved(String channelReserved) {
		this.channelReserved = channelReserved;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public int getBetSuccAmount() {
		return betSuccAmount;
	}

	public void setBetSuccAmount(int betSuccAmount) {
		this.betSuccAmount = betSuccAmount;
	}

	public Date getOrderAcceptTime() {
		return orderAcceptTime;
	}

	public void setOrderAcceptTime(Date orderAcceptTime) {
		this.orderAcceptTime = orderAcceptTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<BackReqBetInfo> getTicketInfoList() {
		return ticketInfoList;
	}

	public void setTicketInfoList(List<BackReqBetInfo> ticketInfoList) {
		this.ticketInfoList = ticketInfoList;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "BackBetBodyInfo [result=" + result + ", issueNumber=" + issueNumber + ", orderNumber=" + orderNumber
				+ ", betSuccAmount=" + betSuccAmount + ", orderAcceptTime=" + orderAcceptTime + ", channelReserved="
				+ channelReserved + ", resultDesc=" + resultDesc + ", ticketInfoList=" + ticketInfoList + "]";
	}

}

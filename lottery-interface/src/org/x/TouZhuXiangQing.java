package org.x;

import java.util.List;

import org.x.info.BackReqBetInfo;

public class TouZhuXiangQing {
	private String issueNumber;
	private List<BackReqBetInfo> ticketInfoList;
	private String orderNumber;

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public List<BackReqBetInfo> getTicketInfoList() {
		return ticketInfoList;
	}

	public void setTicketInfoList(List<BackReqBetInfo> ticketInfoList) {
		this.ticketInfoList = ticketInfoList;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}

package org.x.info;

public class BackReqBetInfo {

	private String betDateTime;
	private String betDetail;
	private String ticketId;

	@Override
	public String toString() {
		return "BetInfo [betDateTime=" + betDateTime + ", betDetail=" + betDetail + ", ticketId=" + ticketId + "]";
	}

	public String getBetDateTime() {
		return betDateTime;
	}

	public void setBetDateTime(String betDateTime) {
		this.betDateTime = betDateTime;
	}

	public String getBetDetail() {
		return betDetail;
	}

	public void setBetDetail(String betDetail) {
		this.betDetail = betDetail;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

}

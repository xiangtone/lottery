package org.x.info;

public class PartnerOrderInfo {

	private String partnerChannelId;
	private String partnerReserved;
	private String partnerOrderNumber;
	private String userPhoneNumber;
	private String userName;
	private String lotteryId;
	private String betTotalAmount;
	private String partnerCallbackURL;

	@Override
	public String toString() {
		return "PartnerOrderInfo [partnerChannelId=" + partnerChannelId + ", partnerReserved=" + partnerReserved
				+ ", partnerOrderNumber=" + partnerOrderNumber + ", userPhoneNumber=" + userPhoneNumber + ", userName="
				+ userName + ", lotteryId=" + lotteryId + ", betTotalAmount=" + betTotalAmount + ", partnerCallbackURL="
				+ partnerCallbackURL + "]";
	}

	public String getPartnerChannelId() {
		return partnerChannelId;
	}

	public void setPartnerChannelId(String partnerChannelId) {
		this.partnerChannelId = partnerChannelId;
	}

	public String getPartnerReserved() {
		return partnerReserved;
	}

	public void setPartnerReserved(String partnerReserved) {
		this.partnerReserved = partnerReserved;
	}

	public String getPartnerOrderNumber() {
		return partnerOrderNumber;
	}

	public void setPartnerOrderNumber(String partnerOrderNumber) {
		this.partnerOrderNumber = partnerOrderNumber;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getBetTotalAmount() {
		return betTotalAmount;
	}

	public void setBetTotalAmount(String betTotalAmount) {
		this.betTotalAmount = betTotalAmount;
	}

	public String getPartnerCallbackURL() {
		return partnerCallbackURL;
	}

	public void setPartnerCallbackURL(String partnerCallbackURL) {
		this.partnerCallbackURL = partnerCallbackURL;
	}

}

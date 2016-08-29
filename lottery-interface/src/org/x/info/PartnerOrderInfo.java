package org.x.info;

import java.util.ArrayList;
import java.util.List;

import com.iwt.yt.api.trans.BetInfo;

public class PartnerOrderInfo {

	private String appId;
	private String partnerChannelId;
	private String partnerReserved;
	private String partnerOrderNumber;
	private String userPhoneNumber;
	private String userName;
	private String lotteryId;
	private int numberSelectType;
	private String betTotalAmount;
	private String partnerCallbackURL;
	private List<BetInfo> betInfoList = new ArrayList<BetInfo>();

	@Override
	public String toString() {
		return "PartnerOrderInfo [appId=" + appId + ", partnerChannelId=" + partnerChannelId + ", partnerReserved="
				+ partnerReserved + ", partnerOrderNumber=" + partnerOrderNumber + ", userPhoneNumber="
				+ userPhoneNumber + ", userName=" + userName + ", lotteryId=" + lotteryId + ", numberSelectType="
				+ numberSelectType + ", betTotalAmount=" + betTotalAmount + ", partnerCallbackURL=" + partnerCallbackURL
				+ ", betInfoList=" + betInfoList + "]";
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public int getNumberSelectType() {
		return numberSelectType;
	}

	public void setNumberSelectType(int numberSelectType) {
		this.numberSelectType = numberSelectType;
	}

	public List<BetInfo> getBetInfoList() {
		return betInfoList;
	}

	public void setBetInfoList(List<BetInfo> betInfoList) {
		this.betInfoList = betInfoList;
	}

}

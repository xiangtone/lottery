package org.x.info;

public class BackReqHead {

	private String businessId;
	private String channelId;
	private String signature;
	private String transSerialNumber;

	@Override
	public String toString() {
		return "BackReqHead [businessId=" + businessId + ", channelId=" + channelId + ", signature=" + signature
				+ ", transSerialNumber=" + transSerialNumber + "]";
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTransSerialNumber() {
		return transSerialNumber;
	}

	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}

}

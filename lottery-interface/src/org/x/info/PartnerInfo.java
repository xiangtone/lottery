package org.x.info;

public class PartnerInfo {

	private String id;
	private String partnerName;
	private String state;
	private int realBalance;
	private int creditBalance;
	private String method;
	private int unitPrice;
	private String keyAES;

	@Override
	public String toString() {
		return "PartnerInfo [id=" + id + ", partnerName=" + partnerName + ", state=" + state + ", realBalance="
				+ realBalance + ", creditBalance=" + creditBalance + ", method=" + method + ", unitPrice=" + unitPrice
				+ ", keyAES=" + keyAES + "]";
	}

	public PartnerInfo(String id, String partnerName, String state, int realBalance, int creditBalance, String method,
			int unitPrice, String keyAES) {
		super();
		this.id = id;
		this.partnerName = partnerName;
		this.state = state;
		this.realBalance = realBalance;
		this.creditBalance = creditBalance;
		this.method = method;
		this.unitPrice = unitPrice;
		this.keyAES = keyAES;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRealBalance() {
		return realBalance;
	}

	public void setRealBalance(int realBalance) {
		this.realBalance = realBalance;
	}

	public int getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(int creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getKeyAES() {
		return keyAES;
	}

	public void setKeyAES(String keyAES) {
		this.keyAES = keyAES;
	}

}

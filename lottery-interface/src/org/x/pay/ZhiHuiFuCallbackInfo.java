package org.x.pay;

public class ZhiHuiFuCallbackInfo {
	private String chnl_type;
	private String aes_key;
	private String token;
	private String chnl_id;
	private String chnl_sn;

	public String getChnl_type() {
		return chnl_type;
	}

	public void setChnl_type(String chnl_type) {
		this.chnl_type = chnl_type;
	}

	public String getAes_key() {
		return aes_key;
	}

	public void setAes_key(String aes_key) {
		this.aes_key = aes_key;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getChnl_id() {
		return chnl_id;
	}

	public void setChnl_id(String chnl_id) {
		this.chnl_id = chnl_id;
	}

	public String getChnl_sn() {
		return chnl_sn;
	}

	public void setChnl_sn(String chnl_sn) {
		this.chnl_sn = chnl_sn;
	}

	@Override
	public String toString() {
		return "CallbackInfo [chnl_type=" + chnl_type + ", aes_key=" + aes_key + ", token=" + token + ", chnl_id="
				+ chnl_id + ", chnl_sn=" + chnl_sn + "]";
	}

}

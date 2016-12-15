package org.x.pay;

public class OrderCallback {
	private String pay_url;
	private String trade_cur;
	private String chnl_type;
	private String merch_id;
	private String return_msg;
	private String order_id;
	private String termnl_id;
	private String card_id;
	private String reserved;
	private String merch_trade_no;
	private String trade_amt;
	private String chnl_id;
	private String start_time;
	private String expir_time;
	private String return_code;
	private String chnl_sn;

	public String getPay_url() {
		return pay_url;
	}

	public void setPay_url(String pay_url) {
		this.pay_url = pay_url;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "OrderCallback [pay_url=" + pay_url + ", trade_cur=" + trade_cur + ", chnl_type=" + chnl_type
				+ ", merch_id=" + merch_id + ", return_msg=" + return_msg + ", order_id=" + order_id + ", termnl_id="
				+ termnl_id + ", card_id=" + card_id + ", reserved=" + reserved + ", merch_trade_no=" + merch_trade_no
				+ ", trade_amt=" + trade_amt + ", chnl_id=" + chnl_id + ", start_time=" + start_time + ", expir_time="
				+ expir_time + ", return_code=" + return_code + ", chnl_sn=" + chnl_sn + "]";
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

}

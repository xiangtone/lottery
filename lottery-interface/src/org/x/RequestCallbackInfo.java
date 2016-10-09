package org.x;

public class RequestCallbackInfo {
	private String return_msg;
	private String data;
	private String return_code;

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	@Override
	public String toString() {
		return "RequestCallbackInfo [return_msg=" + return_msg + ", data=" + data + ", return_code=" + return_code
				+ "]";
	}

}

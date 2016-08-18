package org.x.info;

public class BackReq {

	private String body;
	private BackReqHead head;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public BackReqHead getHead() {
		return head;
	}

	public void setHead(BackReqHead head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "BackReq [body=" + body + ", head=" + head + "]";
	}

}

package org.x.utils;

import java.util.Date;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.x.info.BackReq;
import org.x.info.BackReqBodyQueryOrder;
import org.x.info.BackReqHead;

import com.alibaba.fastjson.JSON;
import com.iwt.vasoss.common.security.DigestHelper;

public class TestOrderResult {

	private final static Logger LOG = Logger.getLogger(TestOrderResult.class);

	public void test() throws Exception {
		BackReqBodyQueryOrder backReqBodyQueryOrder = new BackReqBodyQueryOrder();
		backReqBodyQueryOrder.setTransDateTime(new Date());
		backReqBodyQueryOrder.setOrderNumber("1469413604412");

		String body = JSON.toJSONString(backReqBodyQueryOrder);

		LOG.debug(body);
		BackReqHead backReqHead = new BackReqHead();
		backReqHead.setBusinessId("3010");
		backReqHead.setChannelId("C12001");
		backReqHead.setSignature("null");
		backReqHead.setTransSerialNumber(UUID.randomUUID().toString().replaceAll("-", ""));

		BackReq backReq = new BackReq();
		backReq.setBody(body);
		backReq.setHead(backReqHead);

		String waitEncrypt = JSON.toJSONString(backReq);
		LOG.debug(waitEncrypt);
		waitEncrypt = waitEncrypt.replaceAll("\\\\\"", "\"");
		LOG.debug(waitEncrypt);
		waitEncrypt = waitEncrypt.replaceAll("\"null\"", "null");
		LOG.debug(waitEncrypt);
		String addHeadTail = "4028d894" + waitEncrypt + "11390000";
		LOG.debug(addHeadTail);

		LOG.debug("md5:" + DigestHelper.md5Hex(addHeadTail));
		backReqHead.setSignature(DigestHelper.md5Hex(addHeadTail));

		backReq.setBody(AES.Encrypt(body, "54acf3110154acf3"));
		String sendText = JSON.toJSONString(backReq);
		LOG.debug(sendText);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost("http://124.205.38.84:13135/lottomagic/jfhcp/doRequest");

			HttpEntity entity = EntityBuilder.create().setContentEncoding("utf-8")
					.setContentType(ContentType.APPLICATION_JSON).setText(sendText).build();
			httppost.setEntity(entity);

			LOG.debug("Executing request: " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				LOG.debug("----------------------------------------");
				LOG.debug(response.getStatusLine());
				LOG.debug(EntityUtils.toString(response.getEntity()));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	public static void main(String[] args) throws Exception {
		TestOrderResult testOrderResult = new TestOrderResult();
		testOrderResult.test();
	}

}

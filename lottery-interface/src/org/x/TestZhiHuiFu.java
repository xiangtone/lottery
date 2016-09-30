package org.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.x.utils.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ipp.order.utils.AESCoder;
import com.ipp.order.utils.CertCoder;
import com.ipp.order.utils.Utils;

public class TestZhiHuiFu {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestZhiHuiFu testZhiHuiFu = new TestZhiHuiFu();
		String callbackInfo = testZhiHuiFu.orderLogin();
		testZhiHuiFu.requestOrderNew(callbackInfo);
	}

	protected static String orderLogin() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://test.ippit.cn/orderform/iips2/order/login");
		JSONObject json = new JSONObject();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
		String timeStr = dateFormat.format(now);
		// timeStr = "20160223152525";
		json.put("chnl_type", "WEB");
		json.put("chnl_id", "12345678");
		json.put("chnl_sn", "" + System.currentTimeMillis());
		json.put("merch_id", "862900000000001");
		json.put("termnl_id", "00011071");
		List pairs = new ArrayList<NameValuePair>();
		String msg = json.toString();
		msg = Base64.encode(CertCoder.encryptByPublicKey(msg.getBytes(), "D:/server.cer"));
		System.out.println("加密" + msg);
		pairs.add(new BasicNameValuePair("msg", msg));
		byte[] r = CertCoder.sign(msg.getBytes(), "d:/client.pfx", null, "123456");
		String sign = Base64.encode(r);
		System.out.println("sign=" + sign);
		pairs.add(new BasicNameValuePair("sign", sign));
		request.setEntity(new UrlEncodedFormEntity(pairs, "GBK"));
		HttpResponse rsp = httpClient.execute(request);
		@SuppressWarnings("unused")
		StatusLine status = rsp.getStatusLine();
		String rspText = EntityUtils.toString(rsp.getEntity(), "GBK");
		System.out.println(rspText);
		int a = rspText.indexOf("msg=");
		int b = rspText.indexOf("&sign=");
		msg = rspText.substring(a + 4, b);
		sign = rspText.substring(b + 6);
		boolean bSign = CertCoder.verifySign(msg.getBytes(), Base64.decode(sign), "D:/server.cer");
		r = CertCoder.decryptByPrivateKey(Base64.decode(msg), "D:/client.pfx", null, "123456");
		String r1 = new String(r, "GBK");
		System.out.println(bSign + rspText + "\n" + r1);
		return r1;
	}

	protected static boolean requestOrderNew(String r1) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ZhiHuiFuCallbackInfo zhiHuiFuCallbackInfo = JSON.parseObject(r1, ZhiHuiFuCallbackInfo.class);
		String aesKey = zhiHuiFuCallbackInfo.getAes_key();
		String token = zhiHuiFuCallbackInfo.getToken();
		HttpPost request = new HttpPost("http://test.ippit.cn/orderform/iips2/order/request");
		JSONObject json = new JSONObject();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
		String timeStr = dateFormat.format(now);
		// timeStr = "20160223152525";
		json.put("chnl_type", "WEB");
		json.put("chnl_id", "12345678");
		json.put("chnl_sn", timeStr);
		json.put("merch_trade_no", timeStr);
		json.put("merch_id", "862900000000001");
		json.put("termnl_id", "00011071");
		json.put("trade_no", timeStr);
		json.put("trade_amt", "5");
		// json.put("pwd", "111111");
		json.put("trade_cur", "CNY");
		json.put("good_info", "test测试");
		json.put("card_id", "5882572900500000182");
		json.put("order_type", "03");
		json.put("merch_url", "http:/test.com");
		json.put("reserved", "youka");
		json.put("request_time", timeStr);
		System.out.println(json.toString());
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("token", token));
		System.out.println(pairs);
		String msg = Base64.encode(AESCoder.encrypt(json.toString().getBytes("GBK"), aesKey.getBytes()));
		pairs.add(new BasicNameValuePair("msg", msg));
		pairs.add(new BasicNameValuePair("sign", Utils.SHA1(msg + aesKey + token)));
		request.setEntity(new UrlEncodedFormEntity(pairs, "GBK"));
		HttpResponse rsp = httpClient.execute(request);
		@SuppressWarnings("unused")
		StatusLine status = rsp.getStatusLine();
		String rspText = EntityUtils.toString(rsp.getEntity(), "GBK");
		System.out.println(rspText);
		int a = rspText.indexOf("msg=");
		int b = rspText.indexOf("&sign=");
		msg = rspText.substring(a + 4, b);
		String sign = rspText.substring(b + 6);
		RequestCallbackInfo requestCallbackInfo = JSON.parseObject(msg, RequestCallbackInfo.class);
		System.out.println("message:" + msg.toString());
		String newSign = Utils.SHA1(msg + aesKey + token);
		System.out.println(msg + "\n" + sign + "\n" + newSign);
		System.out.println("requestCallbackInfo:" + requestCallbackInfo);
		String data = requestCallbackInfo.getData();
		System.out.println(data);
		String data1 = new String(AESCoder.decrypt(Base64.decode(data), aesKey.getBytes()), "GBK");
		System.out.println(data1);
		return true;
	}

}

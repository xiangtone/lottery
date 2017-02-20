package org.x.pay;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
	private String pay_url;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestZhiHuiFu testZhiHuiFu = new TestZhiHuiFu();
		String callbackInfo = testZhiHuiFu.orderLogin();
		String pay_url = testZhiHuiFu.requestOrderNew(callbackInfo);
		testZhiHuiFu.process();
	}

	public void process() throws Exception {
		String callbackInfo = orderLogin();
		this.setPay_url(requestOrderNew(callbackInfo));
	}

	protected static String orderLogin() throws Exception {
		Properties prop = new Properties();// 属性集合对象
		FileInputStream fis = new FileInputStream(
				"/data/server/apache-tomcat-8.0.36/webapps/a.yt.youkala.com/WEB-INF/classes/pay.properties");// 属性文件流
		prop.load(fis);// 将属性文件流装载到Properties对象中

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://netpay.ippit.cn/orderform/iips2/order/login");
		JSONObject json = new JSONObject();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
		String timeStr = dateFormat.format(now);
		// timeStr = "20160223152525";
		json.put("chnl_type", "WEB");
		json.put("chnl_id", "00000011");
		json.put("chnl_sn", timeStr);
		json.put("merch_id", "865840000000057");
		json.put("termnl_id", "00571002");
		List pairs = new ArrayList<NameValuePair>();
		String msg = json.toString();
		System.out.println("商户信息：" + msg);
		msg = Base64.encode(CertCoder.encryptByPublicKey(msg.getBytes(), prop.getProperty("net.url") + "/server.cer"));
		pairs.add(new BasicNameValuePair("msg", msg));
		byte[] r = CertCoder.sign(msg.getBytes(), prop.getProperty("net.url") + "/szykkj.pfx", null, "123456");
		String sign = Base64.encode(r);
		System.out.println("sign：" + sign);
		pairs.add(new BasicNameValuePair("sign", sign));
		System.out.println("pairs：" + pairs);
		request.setEntity(new UrlEncodedFormEntity(pairs, "GBK"));
		HttpResponse rsp = httpClient.execute(request);
		@SuppressWarnings("unused")
		StatusLine status = rsp.getStatusLine();
		String rspText = EntityUtils.toString(rsp.getEntity(), "GBK");
		System.out.println("rspText：" + rspText);
		int a = rspText.indexOf("msg=");
		int b = rspText.indexOf("&sign=");
		msg = rspText.substring(a + 4, b);
		sign = rspText.substring(b + 6);
		boolean bSign = CertCoder.verifySign(msg.getBytes(), Base64.decode(sign),
				prop.getProperty("net.url") + "/server.cer");
		r = CertCoder.decryptByPrivateKey(Base64.decode(msg), prop.getProperty("net.url") + "/szykkj.pfx", null,
				"123456");
		String r1 = new String(r, "GBK");
		return r1;
	}

	protected static String requestOrderNew(String r1) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ZhiHuiFuCallbackInfo zhiHuiFuCallbackInfo = JSON.parseObject(r1, ZhiHuiFuCallbackInfo.class);
		String aesKey = zhiHuiFuCallbackInfo.getAes_key();
		String token = zhiHuiFuCallbackInfo.getToken();
		HttpPost request = new HttpPost("http://netpay.ippit.cn/orderform/iips2/order/request");
		JSONObject json = new JSONObject();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
		String timeStr = dateFormat.format(now);
		json.put("chnl_type", "WEB");
		json.put("chnl_id", "00000011");
		json.put("chnl_sn", timeStr);
		json.put("merch_trade_no", timeStr);
		json.put("merch_id", "865840000000057");
		json.put("termnl_id", "00571002");
		json.put("trade_no", timeStr);
		json.put("trade_amt", "0.01");
		// json.put("pwd", "111111");
		json.put("trade_cur", "CNY");
		json.put("good_info", "积分兑换彩票");
		// json.put("card_id", "5882572900500974568");
		json.put("order_type", "00");
		json.put("merch_url", "http://a.yt.youkala.com:38080/api1.jsp");
		json.put("reserved", "youka");
		json.put("request_time", timeStr);
		System.out.println("商户信息：" + json.toString());
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("token", token));
		String msg = Base64.encode(AESCoder.encrypt(json.toString().getBytes("GBK"), aesKey.getBytes()));
		pairs.add(new BasicNameValuePair("msg", msg));
		pairs.add(new BasicNameValuePair("sign", Utils.SHA1(msg + aesKey + token)));
		request.setEntity(new UrlEncodedFormEntity(pairs, "GBK"));
		HttpResponse rsp = httpClient.execute(request);
		@SuppressWarnings("unused")
		StatusLine status = rsp.getStatusLine();
		String rspText = EntityUtils.toString(rsp.getEntity(), "GBK");
		System.out.println("rspText：" + rspText);
		int a = rspText.indexOf("msg=");
		int b = rspText.indexOf("&sign=");
		msg = rspText.substring(a + 4, b);
		String sign = rspText.substring(b + 6);
		RequestCallbackInfo requestCallbackInfo = JSON.parseObject(msg, RequestCallbackInfo.class);
		String newSign = Utils.SHA1(msg + aesKey + token);
		String data = requestCallbackInfo.getData();
		String data1 = new String(AESCoder.decrypt(Base64.decode(data), aesKey.getBytes()), "GBK");
		OrderCallback orderCallbackInfo = JSON.parseObject(data1, OrderCallback.class);
		String url = null;
		if (!json.getString("order_type").equals("00")) {
			url = orderCallbackInfo.getPay_url() + orderCallbackInfo.getOrder_id();
		} else {
			url = orderCallbackInfo.getPay_url();
		}
		return url;
	}

	public String getPay_url() {
		return pay_url;
	}

	public void setPay_url(String pay_url) {
		this.pay_url = pay_url;
	}

}

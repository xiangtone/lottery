package org.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.ipp.order.utils.AESCoder;
import com.ipp.order.utils.Utils;

public class RequestTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RequestTest requestTest = new RequestTest();
		requestTest.requestOrderNew();
	}

	protected static boolean requestOrderNew() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String aesKey = "d80fe585c247d15f22ebd8ad16999040";
		String token = "5faf3b0187464f6f2c1cd8f0ef988684";
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
		json.put("trade_amt", "10");
		// json.put("pwd", "111111");
		json.put("trade_cur", "CNY");
		json.put("good_info", "test测试");
		json.put("order_type", "00");
		json.put("merch_url", "http:/test.com");
		json.put("request_time", timeStr);
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
		System.out.println(rspText);
		int a = rspText.indexOf("msg=");
		int b = rspText.indexOf("&sign=");
		msg = rspText.substring(a + 4, b);
		Map<String, Object> masage = new LinkedHashMap<String, Object>();
		masage.put("msg", msg);
		String sign = rspText.substring(b + 6);
		JSONObject o = new JSONObject(masage);
		String newSign = Utils.SHA1(msg + aesKey + token);
		System.out.println(msg + "\n" + sign + "\n" + newSign);
		String data = o.getString("data");
		String data1 = new String(AESCoder.decrypt(Base64.decode(data), aesKey.getBytes()), "GBK");
		System.out.println(data1);
		return true;
	}

}

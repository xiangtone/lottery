package org.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.common.util.ThreadPool;

import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;
import com.iwt.yt.api.base.ReqHead;
import com.iwt.yt.api.trans.BetInfo;
import com.iwt.yt.api.trans.PointExchangeLotteryReq;
import com.iwt.yt.api.trans.PointExchangeLotteryReqBody;
import com.iwt.yt.plugin.ClientTransService;
import com.iwt.yt.util.ClientUtil;

import javax.swing.*;

public class PreparePostToWeb {

	private static final Logger LOG = Logger.getLogger(PreparePostToWeb.class);

	private final long serialVersionUID = 8756559814195904326L;
	private PointExchangeLotteryReqBody body = new PointExchangeLotteryReqBody();
	
	private List<BetInfo> betInfoList = new ArrayList<BetInfo>();
	private BetInfo betInfo = new BetInfo();

	private String channelId;
	private String transSerialNumber;
	private String pointTotalAmount;
	private String transData;
	private String transDataDecode;
	private String sendUrl;
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public PreparePostToWeb() throws RsaEncryptException {
		super();
	}

	public void process() throws RsaEncryptException {
		this.channelId = ClientUtil.getInstance().getChannelId();
		this.transSerialNumber = UUID.randomUUID().toString().replaceAll("-", "");
		configBody();
		LOG.debug(body.getCallbackURL());
		PointExchangeLotteryReq req = new PointExchangeLotteryReq();
		req.setHead(new ReqHead(channelId));
		req.setBody(body);
		LOG.debug(req);
		sendUrl = ClientUtil.getInstance().getPointExchangeLotteryUrl();
		transData = ClientTransService.getInstance().encryptPointExchangeLotteryReq(req);
		ThreadPool.mThreadPool.execute(new PostLogInsert(req.getHead().getChannelId(),
				req.getHead().getTransSerialNumber(), this.getTransData(), req.getBody().getChannelReserved(),
				req.getBody().getOrderNumber(), req.getBody().getUserPhoneNumber(), req.getBody().getTransDateTime(),
				req.getBody().getUserName(), req.getBody().getPointMerchantId(), req.getBody().getGameId(),
				req.getBody().getNumberSelectType(), req.getBody().getBetTotalAmount(),
				req.getBody().getPointTotalAmount(), betInfo, req.getBody().getCallbackURL(), ip));
	}

	public static void main(String[] args)
			throws RsaEncryptException, RsaDecryptException, ClientProtocolException, IOException {
		PreparePostToWeb testSend = new PreparePostToWeb();
		testSend.sendTest();
	}

	public String inputUserPhoneNumber() {
		String userPhoneNumber = JOptionPane.showInputDialog(null, "请输入您的手机号码：");
		while (userPhoneNumber.length() != 11) {
			JOptionPane.showMessageDialog(null, "输入错误！！！请重新输入您的手机号码！！！", "error", JOptionPane.ERROR_MESSAGE);
			userPhoneNumber = JOptionPane.showInputDialog(null, "请输入您的手机号码：");
		}
		return userPhoneNumber;
	}

	public void sendTest() throws RsaEncryptException, RsaDecryptException, ClientProtocolException, IOException {
		configBody();
		LOG.debug(body.getCallbackURL());
		sendUrl = ClientUtil.getInstance().getPointExchangeLotteryUrl();
		PointExchangeLotteryReq req = new PointExchangeLotteryReq();
		req.setHead(new ReqHead(channelId));
		req.setBody(body);
		LOG.debug(req);
		transData = ClientTransService.getInstance().encryptPointExchangeLotteryReq(req);
		LOG.debug(transData);
		String url = "http://124.205.38.84:13135/lottomagic/jfhcp/doRequest";
		HttpEntity entity = EntityBuilder.create().setContentEncoding("utf-8")
				.setContentType(ContentType.APPLICATION_JSON).setText(transData).build();
		sendPostInterface(url);
	}

	private void configBody() {
		body.setOrderNumber(UUID.randomUUID().toString().replaceAll("-", ""));
		body.setTransDateTime(new Date());
		try {
			body.setPointTotalAmount(10);
			body.setCallbackURL("http://a.yt.youkala.com:38080/ytCallback.jsp");
			body.setChannelReserved("youka");
			body.setOrderNumber(Long.toString(System.currentTimeMillis()));
			// body.setUserPhoneNumber("15829553521");// zhuxizhe
			// body.setUserPhoneNumber("18025314707");// fuming
			// body.setUserPhoneNumber("15285960182");// fuming guizhou CMCC
			// test
			// body.setUserPhoneNumber("13603054736");// lijiaqi
			// body.setUserPhoneNumber(inputUserPhoneNumber());
			// body.setUserPhoneNumber("13923832816");//guojining
			// body.setUserPhoneNumber("18676382886");//fengquchi
			// body.setUserPhoneNumber("13590100561");//wanghua
			//body.setUserPhoneNumber("17090415005");
			//body.setUserPhoneNumber("15910667914");// longxu
			body.setPointMerchantId("1200100001");
			body.setGameId("10001");
			body.setNumberSelectType(12);
			body.setBetTotalAmount(1);
			BetInfo betInfo = new BetInfo("101", "001060514252628310110");
			betInfoList.add(betInfo);
			body.setBetInfoList(betInfoList);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public String sendPostInterface(String url) throws ClientProtocolException, IOException {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("channelId", "C11000"));
			nvps.add(new BasicNameValuePair("transSerialNumber", transSerialNumber));
			nvps.add(new BasicNameValuePair("transData", transData));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			LOG.debug("Executing request: " + httpPost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				LOG.debug("----------------------------------------");
				LOG.debug(response.getStatusLine());
				result = EntityUtils.toString(response.getEntity());
				LOG.debug(result);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return result;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}

	public String getTransSerialNumber() {
		return transSerialNumber;
	}

	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}

}

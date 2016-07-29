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

import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.ClientTransService;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReq;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReqBody;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util.ClientUtil;
import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;

public class PreparePostToWeb {

	private static final Logger LOG = Logger.getLogger(PreparePostToWeb.class);

	private final long serialVersionUID = 8756559814195904326L;
	private PointExchangeLotteryReqBody body = new PointExchangeLotteryReqBody();

	private String channelId;
	private String transSerialNumber;
	private String pointTotalAmount;
	private String transData;
	private String transDataDecode;
	private String sendUrl;

	public PreparePostToWeb() throws RsaEncryptException {
		super();
		this.channelId = ClientUtil.getInstance().getChannelId();
		this.transSerialNumber = UUID.randomUUID().toString().replaceAll("-", "");
		configBody();
		LOG.debug(body.getCallbackURL());
		sendUrl = ClientUtil.getInstance().getPointExchangeLotteryUrl();
		PointExchangeLotteryReq req = new PointExchangeLotteryReq();
		req.setHead(new ReqHead(channelId));
		req.setBody(body);
		LOG.debug(req);
		transData = ClientTransService.getInstance().encryptPointExchangeLotteryReq(req);
	}

	public static void main(String[] args)
			throws RsaEncryptException, RsaDecryptException, ClientProtocolException, IOException {
		PreparePostToWeb testSend = new PreparePostToWeb();
		testSend.sendTest();
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
		HttpEntity entity = EntityBuilder.create().setContentEncoding("utf-8").setContentType(ContentType.APPLICATION_JSON)
				.setText(transData).build();
		sendPostInterface(url);
	}

	private void configBody() {
		body.setOrderNumber(UUID.randomUUID().toString().replaceAll("-", ""));
		body.setTransDateTime(new Date());
		body.setCallbackURL("http://120.24.38.160:38080/ytCallback.jsp");
		try {
			body.setPointTotalAmount(10);
			body.setCallbackURL("http://120.24.38.160:38080/ytCallback.jsp");
			body.setChannelReserved("youka");
			body.setOrderNumber(Long.toString(System.currentTimeMillis()));
			// body.setUserPhoneNumber("15829553521");// zhuxizhe
			// body.setUserPhoneNumber("18025314707");// fuming
			// body.setUserPhoneNumber("15285960182");// fuming guizhou CMCC test
			// body.setUserPhoneNumber("13603054736");// lijiaqi
			body.setUserPhoneNumber("13530274162");// longxu
			body.setPointMerchantId("1200100001");
			body.setGameId("10001");
			body.setNumberSelectType(1);
			body.setBetTotalAmount(1);
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

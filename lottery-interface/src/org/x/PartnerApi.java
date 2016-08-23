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
import org.x.info.PageAction;
import org.x.info.PartnerInfo;
import org.x.info.PartnerOrderInfo;
import org.x.service.PartnerService;

import com.alibaba.fastjson.JSON;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.ClientTransService;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.ReqHead;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.BetInfo;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReq;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.trans.PointExchangeLotteryReqBody;
import com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util.ClientUtil;
import com.iwt.vasoss.common.security.exception.RsaDecryptException;
import com.iwt.vasoss.common.security.exception.RsaEncryptException;

public class PartnerApi {

	private static final Logger LOG = Logger.getLogger(PartnerApi.class);

	private final long serialVersionUID = 8756559814195904326L;
	private PointExchangeLotteryReqBody body = new PointExchangeLotteryReqBody();

	private BetInfo betInfo = new BetInfo();

	private PageAction pageAction;
	private String localErrorMsg; // can not do callback error message
	private String partnerId;
	private String partnerTransData;
	private String ip;
	private String channelId;
	private String transSerialNumber;
	private String pointTotalAmount;
	private String transData;
	private String transDataDecode;
	private String sendUrl;
	private List<BetInfo> betInfoList = new ArrayList<BetInfo>();
	private PartnerInfo partnerInfo;
	private PartnerOrderInfo partnerOrderInfo;

	public PageAction getPageAction() {
		return pageAction;
	}

	public void setPageAction(PageAction pageAction) {
		this.pageAction = pageAction;
	}

	public String getLocalErrorMsg() {
		return localErrorMsg;
	}

	public void setLocalErrorMsg(String localErrorMsg) {
		this.localErrorMsg = localErrorMsg;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPartnerTransData() {
		return partnerTransData;
	}

	public void setPartnerTransData(String partnerTransData) {
		this.partnerTransData = partnerTransData;
	}

	public PartnerApi() throws RsaEncryptException {
		super();
	}

	public void process() {
		// check parameters;
		if (checkParameters()) {

		} else {
			LOG.debug("ip:" + ip + "  . check parameter fail .");
			return;
		}
	}

	private boolean checkParameters() {
		boolean result = false;
		// check all Parameters
		result = checkAllParametersExist();
		return result;
	}

	private boolean checkAllParametersExist() {
		boolean result = false;
		// TODO need complete
		try {
			if (!(partnerId != null && partnerId.length() > 0)) {
				setLocalErrorMsg("{\"status\":\"error\",\"result\":4001,\"msg\":\"partnerId is not exist!\"}");
				return result;
			}
			partnerInfo = PartnerService.getInstance().getNameLoadingCache(partnerId);
			LOG.debug(partnerInfo);
			if (partnerInfo != null) {
				setLocalErrorMsg("{\"status\":\"error\",\"result\":4001,\"msg\":\"partnerId is not exist!\"}");
				return result;
			} else {
				processTransData();
				if (partnerOrderInfo == null) {
					setLocalErrorMsg("{\"status\":\"error\",\"result\":4002,\"msg\":\"decrypt order info failure!\"}");
					return result;
				} else {
					if (partnerOrderInfo.getPartnerCallbackURL() != null
							&& partnerOrderInfo.getPartnerCallbackURL().length() > 0) {
						// todo check match http or https
					} else {
						setLocalErrorMsg("{\"status\":\"error\",\"result\":4005,\"msg\":\"Web callback URL is no currect!\"}");
						return result;
					}
				}
			}
			result = checkParnterId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private void processTransData() {
		String json = partnerTransData;
		if (partnerInfo.getKeyAES() != null && partnerInfo.getKeyAES().length() > 0) {
			// todo decrypt
		}
		partnerOrderInfo = JSON.parseObject(json, PartnerOrderInfo.class);
	}

	private boolean checkParnterId() {
		boolean result = false;
		return result;
	}

	public static void main(String[] args)
			throws RsaEncryptException, RsaDecryptException, ClientProtocolException, IOException {
		// PartnerApi testSend = new PartnerApi();
		// testSend.sendTest();
	}

	// public String selectBetNumber(){
	// int[] redBall = new int[12];
	// int blueBall;
	// for(int i=0;i<6;i++){
	// redBall[i]=(int)Math.random()*33+1;
	// for(int j=0;j<i;j++)
	// {
	// if(redBall[i]==redBall[j])
	// {
	// redBall[i]=(int)Math.random()*33+1;
	// j=0;
	// }
	//
	// }
	// }
	// String redball
	// }

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
		try {
			body.setPointTotalAmount(10);
			body.setCallbackURL("http://a.yt.youkala.com:38080/ytCallback.jsp");
			body.setChannelReserved("youka");
			body.setOrderNumber(Long.toString(System.currentTimeMillis()));
			// body.setUserPhoneNumber("15829553521");// zhuxizhe
			// body.setUserPhoneNumber("18025314707");// fuming
			// body.setUserPhoneNumber("15285960182");// fuming guizhou CMCC test
			// body.setUserPhoneNumber("13603054736");// lijiaqi
			// body.setUserPhoneNumber(inputUserPhoneNumber());
			// body.setUserPhoneNumber("13923832816");//guojining
			// body.setUserPhoneNumber("18676382886");//fengquchi
			body.setUserPhoneNumber("13590100561");// wanghua
			body.setPointMerchantId("1200100001");
			body.setGameId("10001");
			body.setNumberSelectType(12);
			body.setBetTotalAmount(4);
			betInfo.setBetDetail(
					"001060607091516260113" + "001060508091517180114" + "001060307081418300115" + "001060213141927310116");
			betInfo.setBetMode("101");
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

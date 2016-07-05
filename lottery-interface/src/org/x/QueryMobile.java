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

public class QueryMobile {

  private static final Logger LOG = Logger.getLogger(QueryMobile.class);

  private final long serialVersionUID = 8756559814195904326L;
  private PointExchangeLotteryReqBody body = new PointExchangeLotteryReqBody();

  private String channelId = "C12001";
  private String transSerialNumber;
  private String businessId = "3020";
  private String transData;
  private String transDataDecode;
  private String sendUrl;

  public static void main(String[] args) throws RsaEncryptException, RsaDecryptException, ClientProtocolException,
      IOException {
    QueryMobile testSend = new QueryMobile();
    testSend.sendTest();
  }

  public void sendTest() throws RsaEncryptException, RsaDecryptException, ClientProtocolException, IOException {
    transSerialNumber = UUID.randomUUID().toString().replaceAll("-", "");
    configBody();
    LOG.debug(body.getCallbackURL());
    sendUrl = ClientUtil.getInstance().getPointExchangeLotteryUrl();
    channelId = ClientUtil.getInstance().getChannelId();
    PointExchangeLotteryReq req = new PointExchangeLotteryReq();
    req.setHead(new ReqHead(channelId));
    req.setBody(body);
    LOG.debug(req);
    transData = ClientTransService.getInstance().encryptPointExchangeLotteryReq(req);
    LOG.debug(transData);
    // String url =
    // "http://124.205.38.84:8480/resources/api/receiveChannelOrderAction.action";
    String url = "http://124.205.38.84:13135/lottomagic/jfhcp/doRequest";
    HttpEntity entity = EntityBuilder.create().setContentEncoding("utf-8").setContentType(ContentType.APPLICATION_JSON)
        .setText(transData).build();
    sendPostInterface(url);

    // transDataDecode = Base64.decode(transData);

    // PointExchangeLotteryResultReq result =
    // ClientTransService.getInstance().decryptPointExchangeLotteryResultReq(
    // channelId, transSerialNumber, transData);
    // LOG.debug(result);
    // body = null;
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
      body.setUserPhoneNumber("13603054736");// lijiaqi
      body.setPointMerchantId("12001");
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

      // httpPost.setEntity(entity);
      // httpPost.setHeader("channelId", "C12001");

      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      nvps.add(new BasicNameValuePair("channelId", "C12001"));
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
}

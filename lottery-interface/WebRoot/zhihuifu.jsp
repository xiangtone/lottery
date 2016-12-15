<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import = "org.x.PartnerApi"%>
<%@ page import = "org.x.utils.AES"%>
<%@ page import="org.x.pay.TestZhiHuiFu"%>
<%@ page import="org.x.pay.OrderCallback"%>
<%@ page import = "org.x.info.PartnerInfo"%>
<%@ page import = "org.x.info.PartnerOrderInfo"%>
<%@ page import = "org.x.service.PartnerService"%>
<%@ page import = "com.alibaba.fastjson.JSON"%>
<%@ page import = "org.apache.log4j.Logger"%>
<%
	TestZhiHuiFu testZhiHuiFu = new TestZhiHuiFu();
	testZhiHuiFu.process();
	PartnerOrderInfo partnerOrderInfo = JSON.parseObject(request.getParameter("transData"),
			PartnerOrderInfo.class);
	partnerOrderInfo.getBetInfoList().get(0).setBetDetail(request.getParameter("betDetail"));
	String transData = JSON.toJSONString(partnerOrderInfo);
	PartnerApi partnerApi = new PartnerApi();
	partnerApi.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
			? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	partnerApi.setPartnerId(request.getParameter("partnerId"));
	String encryptTransData;
	PartnerInfo partnerInfo = PartnerService.getInstance().getNameLoadingCache(partnerApi.getPartnerId());
	partnerApi.setPartnerInfo(partnerInfo);
	if (partnerApi.getPartnerInfo() != null && partnerApi.getPartnerInfo().getKeyAES() != null
			&& partnerApi.getPartnerInfo().getKeyAES().length() > 0) {
		encryptTransData = AES.Encrypt(transData,
				partnerApi.getPartnerInfo().getKeyAES());
	} else {
		encryptTransData = transData;
	}

	partnerApi.setPartnerTransData(encryptTransData);

	partnerApi.process();

	application.setAttribute("partnerId", partnerApi.getPartnerId());
	application.setAttribute("transData", partnerApi.getPartnerTransData());
	
	if (partnerApi.getLocalErrorMsg() != null && partnerApi.getLocalErrorMsg().length() > 0) {
		out.print(partnerApi.getLocalErrorMsg());
		return;
	}
	if (partnerApi.getPageAction() == null) {
		out.print(" error:page action is null");
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>支付</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form id="zhihuifu" action="<%=testZhiHuiFu.getPay_url()%>"
		method="post">
			<button type="submit" id="submit">submit</button>
	</form>
	<script>document.getElementById("submit").click();</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import = "org.x.PartnerApi"%>
<%@ page import = "org.x.utils.AES"%>
<%@ page import="org.x.pay.TestZhiHuiFu"%>
<%@ page import = "org.x.info.PartnerInfo"%>
<%@ page import = "org.x.info.PartnerOrderInfo"%>
<%@ page import = "org.x.service.PartnerService"%>
<%@ page import = "com.alibaba.fastjson.JSON"%>
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

	if (partnerApi.getLocalErrorMsg() != null && partnerApi.getLocalErrorMsg().length() > 0) {
		out.print(partnerApi.getLocalErrorMsg());
		return;
	}
	if (partnerApi.getPageAction() == null) {
		out.print(" error:page action is null");
		return;
	}

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	out.println("partnerId:" + request.getParameter("partnerId"));
	out.println("<br>");
	if (partnerApi.getPartnerInfo() != null && partnerApi.getPartnerInfo().getKeyAES() != null
			&& partnerApi.getPartnerInfo().getKeyAES().length() > 0) {
		out.println("transData:" + encryptTransData);
	} else {
		out.println("transData:" + transData);
	}
	out.println("<br>");
	out.println("pay_url:" + testZhiHuiFu.getPay_url());	
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
		<input id="partnerDebug" name="partnerDebug" type="hidden"
			value="<%=request.getParameter("partnerDebug")%>"> <input
			id="partnerId" name="partnerId" type="hidden"
			value="<%=request.getParameter("partnerId")%>"> <input
			id="transData" name="transData" type="hidden"
			value='<%=request.getParameter("transData")%>'> <input
			id="betDetail" name="betDetail" type="hidden" value="">
			<button type="submit" id="submit">submit</button>
	</form>
</body>
</html>
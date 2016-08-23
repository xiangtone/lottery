<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.x.PartnerApi"%>
<%@page import="org.common.util.ThreadPool" %>
<%@page import="org.x.PostLogInsert" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
      + "/";
  PartnerApi partnerApi =  new PartnerApi();
  if (partnerApi.getPageAction()==null){
  	return;
  }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>API</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<br>
	<form id="formid"
		action="http://124.205.38.84:8480/resources/api/receiveChannelOrderAction.action"
		method="post">
<!-- 		action="http://www.lottomagic.com.cn/resources/api/receiveChannelOrderAction.action" -->
		<input name="channelId" type="hidden" value="<%=partnerApi.getChannelId()%>">
		<input name="transSerialNumber" type="hidden" value="<%=partnerApi.getTransSerialNumber()%>">
		<input name="transData" type="hidden" value="<%=partnerApi.getTransData()%>">
	</form>
</body>
<script>
//document.getElementById("formid").submit();
</script>
</html>
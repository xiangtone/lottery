<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.x.PreparePostQueryToWeb"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	PreparePostQueryToWeb preparePostQueryToWeb = new PreparePostQueryToWeb();
	preparePostQueryToWeb
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	preparePostQueryToWeb.process();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="Query UpdataInfo">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	Query userBetInfo!!!
	<br>
	<form
		action="http://www.lottomagic.com.cn/resources/api/receiveQueryBetAccountAction.action"
		method="post">
		<input name="channelId" type="hidden"
			value="<%=preparePostQueryToWeb.getChannelId()%>"> <input
			name="transSerialNumber" type="hidden"
			value="<%=preparePostQueryToWeb.getTransSerialNumber()%>"> <input
			name="transData" type="hidden"
			value="<%=preparePostQueryToWeb.getTransData()%>">
		<button type="submit">submit</button>
	</form>
</body>
</html>

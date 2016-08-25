<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.x.PreparePostToWebTest"%>
<%@page import="org.common.util.ThreadPool"%>
<%@page import="org.x.PostLogInsert"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PreparePostToWebTest preparePostToWebTest = new PreparePostToWebTest();
	preparePostToWebTest.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
			? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	preparePostToWebTest.process();
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
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	This is my JSP page. welcome!
	<br>
	<form
		action="http://124.205.38.84:8480/resources/api/receiveChannelOrderAction.action"
		method="post">
		<input name="channelId" type="hidden"
			value="<%=preparePostToWebTest.getChannelId()%>"> <input
			name="transSerialNumber" type="hidden"
			value="<%=preparePostToWebTest.getTransSerialNumber()%>"> <input
			name="transData" type="hidden"
			value="<%=preparePostToWebTest.getTransData()%>">
		<button type="submit">submit 123</button>
	</form>
</body>
</html>

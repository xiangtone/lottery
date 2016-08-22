<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.x.PreparePostToWeb"%>
<%@page import="org.common.util.ThreadPool" %>
<%@page import="org.x.PostLogInsert" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
      + "/";
  PreparePostToWeb preparePostToWeb =  new PreparePostToWeb();
  ThreadPool.mThreadPool.execute(new PostLogInsert(preparePostToWeb.getChannelId(), preparePostToWeb.getTransSerialNumber(), preparePostToWeb.getTransData()));
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
	<br>
	<form id="formid"
		action="http://124.205.38.84:8480/resources/api/receiveChannelOrderAction.action"
		method="post">
<!-- 		action="http://www.lottomagic.com.cn/resources/api/receiveChannelOrderAction.action" -->
		<input name="channelId" type="hidden" value="<%=preparePostToWeb.getChannelId()%>">
		<input name="transSerialNumber" type="hidden" value="<%=preparePostToWeb.getTransSerialNumber()%>">
		<input name="transData" type="hidden" value="<%=preparePostToWeb.getTransData()%>">
		<button type="submit">submit </button>
	</form>
</body>
<script>
document.getElementById("formid").submit();
</script>
</html>
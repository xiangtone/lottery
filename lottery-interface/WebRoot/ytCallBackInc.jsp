<%@page import="org.x.DecryptWebCallback"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="com.alibaba.fastjson.serializer.SerializerFeature"%>
<%@page import="org.x.PartnerApi"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("channelId"));
	DecryptWebCallback decryptWebCallback = new DecryptWebCallback();
	decryptWebCallback.setChannelId(request.getParameter("channelId"));
	decryptWebCallback.setTransSerialNumber(request.getParameter("transSerialNumber"));
	decryptWebCallback.setTransData(request.getParameter("transData"));
	decryptWebCallback
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP")
					: request.getRemoteAddr());
	decryptWebCallback.setMethod(pageStatus);
	decryptWebCallback.process();

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
		action="<%=decryptWebCallback.getPageAction().getUrl()%>"
		method="post">
		<%
		for(String key : decryptWebCallback.getPageAction().getEntity().keySet()){
			%>
			<input name="<%=key %>" type="hidden"
			value='<%=decryptWebCallback.getPageAction().getEntity().get(key)%>'>
			<%
		}
		%>
		<button type="submit" id="submit">submit</button>
	</form>
	<script>document.getElementById("submit").click();</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.x.PreparePostToWeb"%>
<%@page import="org.x.PartnerApi"%>
<%@page import="org.common.util.ThreadPool"%>
<%@page import="org.x.PostLogInsert"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("partnerId"));
	LOG.debug(request.getParameter("transData"));
	
	PartnerApi partnerApi = new PartnerApi();
	partnerApi.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
			? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	partnerApi.setPartnerId(request.getParameter("partnerId"));
	partnerApi.setPartnerTransData(request.getParameter("transData"));
	
	partnerApi.process();
	
	if (partnerApi.getLocalErrorMsg()!=null&&partnerApi.getLocalErrorMsg().length()>0){
		out.print(partnerApi.getLocalErrorMsg());
		return;
	}
	if (partnerApi.getPageAction()==null){
		out.print(" error:page action is null");
		return;
	}
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PreparePostToWeb preparePostToWeb = new PreparePostToWeb();
// 	ThreadPool.mThreadPool.execute(new PostLogInsert(preparePostToWeb.getChannelId(),
// 			preparePostToWeb.getTransSerialNumber(), preparePostToWeb.getTransData()));
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
		action="<%=partnerApi.getPageAction().getUrl() %>"
		method="post">
		<%
		for(String key : partnerApi.getPageAction().getEntity().keySet()){
			%>
			<input name="<%=key %>" type="hidden"
			value="<%=partnerApi.getPageAction().getEntity().get(key)%>">
			<%
		}
		%>
		<button type="submit">submit</button>
	</form>
</body>
<%
if (request.getParameter("partnerDebug")!=null&&request.getParameter("partnerDebug").equals("true")){
	out.println("partnerId:"+request.getParameter("partnerId"));
	out.println("<br>");
	out.println("transData:"+request.getParameter("transData"));	
}else{
	out.println("<script>document.getElementById(\"formid\").submit();</script>");
}
 %>
</html>
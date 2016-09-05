<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.x.PartnerApi"%>
<%
PartnerApi partnerApi = new PartnerApi();
partnerApi.setPartnerId(request.getParameter("partnerId"));
partnerApi.setPartnerTransData(request.getParameter("transData"));

partnerApi.process();

if (partnerApi.getPageAction()==null){
	out.print(" error:page action is null");
	return;
}

if (partnerApi.getCallbackErrorMsg()!=null&&partnerApi.getCallbackErrorMsg().length()>0){
	out.print(partnerApi.getPageAction().getEntity());
	out.print(" error:page action is null");
	return;
}else{
	out.println("partnerId:"+request.getParameter("partnerId"));
	out.println("<br>");
	out.println("transData:"+request.getParameter("transData"));
}


String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
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
	</form>
</body>
</html>
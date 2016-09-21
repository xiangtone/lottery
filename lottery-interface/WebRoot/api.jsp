<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.x.PartnerApi"%>
<%@page import="org.x.utils.AES"%>
<%@page import="org.x.info.PartnerInfo"%>
<%@page import="org.x.service.PartnerService"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("partnerId"));
	LOG.debug(request.getParameter("transData"));
	LOG.debug(request.getParameter("partnerInfo"));
	PartnerApi partnerApi = new PartnerApi();
	partnerApi.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
			? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	partnerApi.setPartnerId(request.getParameter("partnerId"));
	String encryptTransData;
	PartnerInfo partnerInfo = null;
	partnerApi.setPartnerInfo(partnerInfo);
	if (partnerApi.getPartnerInfo() != null && partnerApi.getPartnerInfo().getKeyAES() != null
			&& partnerApi.getPartnerInfo().getKeyAES().length() > 0) {
		encryptTransData = AES.Encrypt(request.getParameter("transData"),partnerApi.getPartnerInfo().getKeyAES());
	} else {
		encryptTransData = request.getParameter("transData");
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
	<form id="formid" action="<%=partnerApi.getPageAction().getUrl()%>"
		method="post">
		<%
			for (String key : partnerApi.getPageAction().getEntity().keySet()) {
		%>
		<input name="<%=key%>" type="hidden"
			value='<%=partnerApi.getPageAction().getEntity().get(key)%>'>
		<%
			}
		%>
		<button type="submit" id="submit">submit</button>
	</form>
</body>
<%
	if (request.getParameter("partnerDebug") != null && request.getParameter("partnerDebug").equals("true")) {
		out.println("partnerId:" + request.getParameter("partnerId"));
		out.println("<br>");

		if (partnerApi.getPartnerInfo() != null && partnerApi.getPartnerInfo().getKeyAES() != null
				&& partnerApi.getPartnerInfo().getKeyAES().length() > 0) {
			out.println("transData:" + encryptTransData);
		} else {
			out.println("transData:" + request.getParameter("transData"));
		}
	} else {
		out.println("<script>document.getElementById(\"submit\").style .visibility =\'hidden\';</script>");
		out.println("<script>document.getElementById(\"submit\").click();</script>");
	}
%>
</html>
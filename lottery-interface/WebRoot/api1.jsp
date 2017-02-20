<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "org.x.PartnerApi"%>
<%@ page import = "org.x.utils.AES"%>
<%@ page import = "org.x.info.PartnerInfo"%>
<%@ page import = "org.x.info.PartnerOrderInfo"%>
<%@ page import = "org.x.service.PartnerService"%>
<%@ page import = "com.alibaba.fastjson.JSON"%>
<%@ page import = "org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(application.getAttribute("partnerId"));
	LOG.debug(application.getAttribute("transData"));
	PartnerApi partnerApi = new PartnerApi();
	partnerApi.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
			? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	partnerApi.setPartnerId(application.getAttribute("partnerId").toString());
	PartnerInfo partnerInfo = PartnerService.getInstance().getNameLoadingCache(partnerApi.getPartnerId());
	partnerApi.setPartnerInfo(partnerInfo);
	partnerApi.setPartnerTransData(application.getAttribute("transData").toString());
	partnerApi.process();
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="api1" action="<%=partnerApi.getPageAction().getUrl()%>"
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
	<script>document.getElementById("submit").style .visibility ='hidden';</script>
	<script>document.getElementById("submit").click();</script>
</body>
</html>
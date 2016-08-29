<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="org.x.DecryptWebQueryCallback"%>
<%@ page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("channelId"));
	DecryptWebQueryCallback decryptWebQueryCallbackTest = new DecryptWebQueryCallback();
	decryptWebQueryCallbackTest.setChannelId(request.getParameter("channelId"));
	decryptWebQueryCallbackTest.setTransSerialNumber(request.getParameter("transSerialNumber"));
	decryptWebQueryCallbackTest.setTransData(request.getParameter("transData"));
	decryptWebQueryCallbackTest
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	decryptWebQueryCallbackTest.setMethod("test");
	decryptWebQueryCallbackTest.decrypt();
%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="org.x.DecryptWebQueryCallbackTest"%>
<%@ page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("channelId"));
	DecryptWebQueryCallbackTest decryptWebQueryCallbackTest = new DecryptWebQueryCallbackTest();
	decryptWebQueryCallbackTest.setChannelId(request.getParameter("channelId"));
	decryptWebQueryCallbackTest.setTransSerialNumber(request.getParameter("transSerialNumber"));
	decryptWebQueryCallbackTest.setTransData(request.getParameter("transData"));
	decryptWebQueryCallbackTest
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	decryptWebQueryCallbackTest.decrypt();
%>
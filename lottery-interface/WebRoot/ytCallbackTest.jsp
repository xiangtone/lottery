<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.x.DecryptWebCallbackTest"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("channelId"));
	DecryptWebCallbackTest decryptWebCallbackTest = new DecryptWebCallbackTest();
	decryptWebCallbackTest.setChannelId(request.getParameter("channelId"));
	decryptWebCallbackTest.setTransSerialNumber(request.getParameter("transSerialNumber"));
	decryptWebCallbackTest.setTransData(request.getParameter("transData"));
	decryptWebCallbackTest
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	decryptWebCallbackTest.decrypt();
%>
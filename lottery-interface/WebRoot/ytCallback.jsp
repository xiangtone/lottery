<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.x.DecryptWebCallback"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("channelId"));
	DecryptWebCallback decryptWebCallback = new DecryptWebCallback();
	decryptWebCallback.setChannelId(request.getParameter("channelId"));
	decryptWebCallback.setTransSerialNumber(request.getParameter("transSerialNumber"));
	decryptWebCallback.setTransData(request.getParameter("transData"));
	decryptWebCallback
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	decryptWebCallback.setMethod("formal");
	decryptWebCallback.process();	
%>
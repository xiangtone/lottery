<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="org.x.DecryptWebQueryCallback"%>
<%@ page import="org.apache.log4j.Logger"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	LOG.debug(request.getParameter("channelId"));
	DecryptWebQueryCallback decryptWebQueryCallback = new DecryptWebQueryCallback();
	decryptWebQueryCallback.setChannelId(request.getParameter("channelId"));
	decryptWebQueryCallback.setTransSerialNumber(request.getParameter("transSerialNumber"));
	decryptWebQueryCallback.setTransData(request.getParameter("transData"));
	decryptWebQueryCallback
			.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
					? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	decryptWebQueryCallback.decrypt();
%>
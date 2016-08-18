<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.x.BackReceiver"%>
<%@ include file="inc-receive-body.jsp"%>
<%
	BackReceiver backReceiver = new BackReceiver();
	backReceiver.setInfo(info);
	backReceiver.setIp(request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").length() > 0
			? request.getHeader("X-Real-IP") : request.getRemoteAddr());
	backReceiver.process();
%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.x.BackReceiver"%>
<%@ include file="inc-receive-body.jsp"%>
<%
BackReceiver backReceiver = new BackReceiver();
backReceiver.setInfo(info);
backReceiver.process();
%>
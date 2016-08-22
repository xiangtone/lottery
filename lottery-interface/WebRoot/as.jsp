<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.x.TestSend"%>
<%
	TestSend testSend = new TestSend();
	testSend.sendTest();
%>
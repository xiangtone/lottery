<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="inc-receive-body.jsp"%>
<%
out.println(request.getQueryString());
out.println(info);
%>
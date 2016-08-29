<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import = "org.x.PartnerApi" %>
<%
PartnerApi partnerApi = new PartnerApi();
out.println(request.getParameter(partnerApi.getPartnerId()));
out.println(request.getParameter("transData"));
%>
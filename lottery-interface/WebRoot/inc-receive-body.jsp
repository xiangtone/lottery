<%@page import="java.io.InputStream"%>
<%@page import="org.apache.log4j.Logger"%>
<%
Logger LOG = Logger.getLogger(this.getClass());
String info = null;
int len = 0;
int temp = 0;
InputStream is = request.getInputStream();
byte[] b = new byte[1000000];
while ((temp = is.read()) != -1) {
  b[len] = (byte) temp;
  len++;
}
is.close();
info = new String(b, 0, len, "utf-8");
LOG.debug(request.getRequestURL());
LOG.debug(request.getQueryString());
System.out.println("####notice:\n" + info);
System.out.println("####end:");
java.util.Enumeration headerNames = request.getHeaderNames();
System.out.println("####Header:\n");
while (headerNames.hasMoreElements()) {
  String headerKey = (String) headerNames.nextElement();
  System.out.println(headerKey + ":" + request.getHeader(headerKey));
}
System.out.println("####End:\n");
%>
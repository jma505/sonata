<%@ page language="java" %>
<%@ page import="org.jmanderson.sonata.CalcService, java.util.Date, java.text.SimpleDateFormat" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
<title>
Calculate Next Service Date
</title>
<html:base/>
</head>
<body bgcolor="#eee8e8">

<br><hr><br>
<%
  String nextService = "2001-01-01";
  try {
    int mileage = Integer.parseInt(request.getParameter("mileage"));
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    nextService = CalcService.calculateNextServiceDate("2001-03-09", "2002-10-24", today, 30176, mileage);
  } catch (Exception e) { }
   
  out.println("<center><h1>" + nextService + "</h1></center>");
%>
<br><hr><br>

</body>
</html:html>


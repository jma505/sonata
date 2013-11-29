<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:html locale="true">
<head>
<title><bean:message key="gaslist.title"/></title>
<html:base/>
</head>
<body bgcolor="#eee8e8">

<br>
<center>
<h1><bean:message key="gaslist.heading"/> <jsp:getProperty name="user" property="userFullName"/></h1>

<table align="center" border="0">
<tr>
<td width="100" align="center"><html:link href="logoff.do"><h3>Logoff</h3></html:link></td>
<td width="100" align="center"><html:link href="gasList.do"><h3>Return</h3></html:link></td>
</table>
</center>

<logic:present name="mpg">
<center>
Next service (<jsp:getProperty name="mpg" property="nextServiceMileage"/> mile) date is estimated as: <jsp:getProperty name="mpg" property="nextServiceDate"/><br><br>
</center>
</logic:present>

<jsp:useBean id="xmlbean" class="org.jmanderson.sonata.XMLBean" scope="session"/>
<c:import url="/XML/service.xsl" var="xslt">
</c:import>
<x:parse var="xml">
<jsp:getProperty name="xmlbean" property="xml"/>
</x:parse>
<x:transform xml="${xml}" xslt="${xslt}">
</x:transform>

</body>
</html:html>


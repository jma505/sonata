<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
<title><bean:message key="gaslist.title"/></title>
<html:base/>
</head>
<body bgcolor="#eee8e8">

<br>
<center>
<h2><bean:message key="gaslist.heading"/> <jsp:getProperty name="user" property="userFullName"/></h2>
<html:link href="logoff.do"><h4>Logoff</h4></html:link>
</center>

<center>

<br>
<center>
<table align="center" border="0">
<td align="center"><form method="post" action="editGas.do">
<input type="submit" name="add" value="Add New Entry"/>
</form></td>
<td align="center"><form method="post" action="listAll.do">
<input type="submit" name="list" value="Complete Service List"/>
</form></td></table>
</center>

<% out.print(org.jmanderson.sonata.Processor.getMPG("25", "28")); %>

</body>
</html:html>


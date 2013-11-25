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
<h2><bean:message key="gasmpg.heading"/></h2>
Next estimated service is <%= request.getAttribute("service") %><br><br>
<br>
<table align="center" border="0">
<tr>
<td width="100" align="center"><html:link href="logoff.do"><h3>Logoff</h3></html:link></td>
<td width="100" align="center"><html:link href="gaslist.do"><h3>Return</h3></html:link></td>
</table>
</center>


</body>
</html:html>


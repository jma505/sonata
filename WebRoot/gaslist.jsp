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
<font size="-1">v1.1</font>
<html:link href="logoff.do"><h4>Logoff</h4></html:link>
</center>

<center>
<h3><bean:message key="gaslist.last5.heading"/></h3>
<logic:present name="mpg">
<bean:message key="gaslist.overall.heading"/> <jsp:getProperty name="mpg" property="overall_MPG"/> - 
<bean:message key="gaslist.average.heading"/> <jsp:getProperty name="mpg" property="average_Price"/>
<br>
Next service (<jsp:getProperty name="mpg" property="nextServiceMileage"/> mile) date is estimated as: <jsp:getProperty name="mpg" property="nextServiceDate"/><br><br>
</logic:present>
</center>

<table align="center" border="0" width="606" bgcolor="#bbb8b8"><tr><td>
<table align="center" border="0" width="600" cellspacing="2" cellpadding="3" bgcolor="#eee8e8">
<tr>
  <th align="left">Date</th>
  <th align="left">Mileage</th>
  <th align="left">Gallons</th>
  <th align="left">Cost</th>
  <th align="left">Price</th>
  <th align="left">MPG</th>
  <th align="left">Description</th>
</tr>
<logic:present name="gas">
<logic:iterate id="g" name="gas" type="org.jmanderson.sonata.GasBean">
<tr>
  <td><a href="/sonata/editGas.do?gas_id=<bean:write name="g" property="gas_id"/>"><bean:write name="g" property="gas_date" filter="true"/></a></td>
  <td><bean:write name="g" property="gas_mileage"/></td>
  <td><bean:write name="g" property="gas_gallons"/></td>
  <td><bean:write name="g" property="gas_cost"/></td>
  <td><bean:write name="g" property="gas_price"/></td>
  <td><bean:write name="g" property="gas_mpg"/></td>
  <td><bean:write name="g" property="gas_desc_short"/></td>
</tr>
</logic:iterate>
</logic:present>
</table>
</table>

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

<center>
<h3><bean:message key="gaslist.service.heading"/></h3>
</center>

<table align="center" border="0" width="606" bgcolor="#bbb8b8"><tr><td>
<table align="center" border="0" width="600" cellspacing="2" cellpadding="3" bgcolor="#eee8e8">
<tr>
  <th align="left">Date</th>
  <th align="left">Mileage</th>
  <th align="left">Total Cost</th>
  <th align="left">Routine Cost</th>
  <th align="left">Repairs Cost</th>
</tr>
<logic:present name="service">
<logic:iterate id="s" name="service" type="org.jmanderson.sonata.ServiceBean">
<tr>
  <td><a href="/sonata/editService.do?service_id=<bean:write name="s" property="service_id"/>"><bean:write name="s" property="service_date" filter="true"/></a></td>
  <td><bean:write name="s" property="service_mileage"/></td>
  <td><bean:write name="s" property="service_cost"/></td>
  <td><bean:write name="s" property="service_routine_cost"/></td>
  <td><bean:write name="s" property="service_repair_cost"/></td>
</tr>
</logic:iterate>
</logic:present>
</table>
</table>

<br>
<center>
<table align="center" border="0">
<td align="center"><form method="post" action="editService.do">
<input type="submit" name="add" value="Add New Entry"/>
</form></td>


</body>
</html:html>


<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
<title>
<logic:equal name="gasForm" property="gas_id" value="0">
  <bean:message key="gasedit.add.title"/>
</logic:equal>
<logic:notEqual name="gasForm" property="gas_id" value="0">
  <bean:message key="gasedit.edit.title"/>
</logic:notEqual>
</title>
<html:base/>
</head>
<body bgcolor="#eee8e8">

<html:errors/>

<br>
<center>
<h2>
<logic:equal name="gasForm" property="gas_id" value="0">
  <bean:message key="gasedit.add.heading"/> for <jsp:getProperty name="user" property="userFullName"/>
</logic:equal>
<logic:notEqual name="gasForm" property="gas_id" value="0">
  <bean:message key="gasedit.edit.heading"/> for <jsp:getProperty name="user" property="userFullName"/>
</logic:notEqual>
</h2>
</center>

<html:form action="saveGas.do" focus="gas_date">
<html:hidden property="gas_id"/>
<html:hidden property="gas_dateD"/>
<html:hidden property="gas_mileage_prev"/>
<html:hidden property="gas_mpg"/>

<table align="center" border="0" width="606" bgcolor="#bbb8b8"><tr><td>
<table align="center" border="0" width="600" cellspacing="2" cellpadding="3" bgcolor="#eee8e8">
<tr>
  <th align="right">Date:</th>
  <td align="left"><html:text property="gas_date" size="10" maxlength="10"/>&nbsp;&nbsp;(mm/dd[/yy]) default = today</td>
</tr>
<tr>
  <th align="right">Mileage:</th>
  <td align="left"><html:text property="gas_mileage" size="6" maxlength="6"/></td>
</tr>
<tr>
  <th align="right">Gallons:</th>
  <td align="left"><html:text property="gas_gallons" size="6" maxlength="6"/>&nbsp;&nbsp;(99.999)</td>
</tr>
<tr>
  <th align="right">Cost:</th>
  <td align="left"><html:text property="gas_cost" size="6" maxlength="6"/>&nbsp;&nbsp;(999.99)</td>
</tr>
<tr>
  <th align="right">Desc:</th>
  <td align="left"><html:text property="gas_desc_short" size="20" maxlength="50"/></td>
</tr>
</table>
</table>

<br>
<center>
<html:submit><bean:message key="button.submit"/></html:submit>&nbsp;&nbsp;
<html:cancel><bean:message key="button.cancel"/></html:cancel>
<logic:notEqual name="gasForm" property="gas_id" value="0">
&nbsp;&nbsp;<html:submit property="delete"><bean:message key="button.delete"/></html:submit>
</logic:notEqual>
</center>

</html:form>

<br><hr><br>
<center>
<html:link href="logoff.do">Click here to logoff</html:link>
</center>

</body>
</html:html>


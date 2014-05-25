<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="gaslist">
  <form action="servlet/TripServlet" method="get">
  <table align="center" border="0" cellspacing="1" cellpadding="3">
    <tr>
      <th align="center"><big>Start</big></th>
      <th align="center"><big>End</big></th>
      <th align="left"><big>Date</big></th>
      <th align="right"><big>Mileage</big></th>
      <th align="right"><big>Gallons</big></th>
      <th align="right"><big>Cost</big></th>
      <th align="right"><big>MPG</big></th>
      <th align="left"><big>Description</big></th>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Calculate Trip Information"/></td>
    </tr>
    <xsl:for-each select="entry">
      <tr>
        <td align="center">
          <xsl:element name="input">
            <xsl:attribute name="type">radio</xsl:attribute>
            <xsl:attribute name="name">tripstart</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="id"/></xsl:attribute>
          </xsl:element>
        </td>
        <td align="center">
          <xsl:element name="input">
            <xsl:attribute name="type">radio</xsl:attribute>
            <xsl:attribute name="name">tripend</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="id"/></xsl:attribute>
          </xsl:element>
        </td>
        <td align="left">
          <xsl:value-of select="date"/>
        </td>
        <td align="right">
          <xsl:value-of select="mileage"/>
        </td>
        <td align="right">
          <xsl:value-of select="gallons"/>
        </td>
        <td align="right">
          $<xsl:value-of select="cost"/>
        </td>
        <td align="right">
          <xsl:value-of select="mpg"/>
        </td>
        <td align="left">
          <xsl:value-of select="short_desc"/>
        </td>
      </tr>
    </xsl:for-each>
    <tr>
      <td colspan="2"><input type="submit" value="Calculate Trip Information"/></td>
    </tr>
  </table>
  </form>
</xsl:template>

</xsl:stylesheet>


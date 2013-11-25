<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="gaslist">
  <table align="center" border="0" cellspacing="1" cellpadding="3">
    <tr>
      <th align="left"><big>Date</big></th>
      <th align="right"><big>Mileage</big></th>
      <th align="right"><big>Gallons</big></th>
      <th align="right"><big>Cost</big></th>
      <th align="right"><big>MPG</big></th>
      <th align="left"><big>Description</big></th>
    </tr>
    <xsl:for-each select="entry">
      <tr>
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
  </table>
</xsl:template>

</xsl:stylesheet>


<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="gaslist">
  <table align="center" border="0" cellspacing="1" cellpadding="3">
    <tr>
      <th align="left"><big>Date</big></th>
      <th align="right"><big>Mileage</big></th>
      <th align="right"><big>Total Cost</big></th>
      <th align="right"><big>Maintenance</big></th>
      <th align="right"><big>Repairs</big></th>
      <th align="center"><big>Scheduled?</big></th>
    </tr>
    <xsl:for-each select="service">
      <tr>
        <td align="left">
          <xsl:value-of select="date"/>
        </td>
        <td align="right">
          <xsl:value-of select="mileage"/>
        </td>
        <td align="right">
          $<xsl:value-of select="cost"/>
        </td>
        <td align="right">
          $<xsl:value-of select="routine_cost"/>
        </td>
        <td align="right">
          $<xsl:value-of select="repair_cost"/>
        </td>
        <td align="center">
          <xsl:value-of select="scheduled_service"/>
        </td>
      </tr>
    </xsl:for-each>
  </table>
</xsl:template>

</xsl:stylesheet>


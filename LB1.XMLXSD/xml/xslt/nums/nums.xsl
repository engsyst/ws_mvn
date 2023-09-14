<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:output omit-xml-declaration="yes" indent="yes" />
	
	<xsl:strip-space elements="*" />

	<xsl:template match="/">
		<table>
			<xsl:apply-templates />
		</table>
	</xsl:template>

	<xsl:template match="num[position() mod 3 = 1]">
		<tr>
			<xsl:apply-templates mode="copy"
				select=". | following-sibling::*[not(position() >2)]" />
		</tr>
	</xsl:template>

	<xsl:template match="*" mode="copy">
		<td>
			<xsl:value-of select="." />
		</td>
	</xsl:template>

	<xsl:template match="num" />
</xsl:stylesheet>
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8" indent="yes"/>
	<xsl:strip-space elements="*"/>
	<xsl:template match="content">
		<html>
		<head>
		<meta charset="UTF-8">
			<title>Content</title>
		</meta>
		</head>
		<body>
		<h1><xsl:value-of select="content/@title"/></h1>
		<table>
			<xsl:apply-templates />
		</table>
		</body>
		</html>
	</xsl:template>

	<xsl:template match="chapter"> <!-- [chapter] -->
		<tr>
			<xsl:call-template name="num" >
				<xsl:with-param name="align" >right</xsl:with-param>
			</xsl:call-template>
			<xsl:apply-templates select="title" />	
		</tr>
 		<xsl:apply-templates select="chapter" />
	</xsl:template>
	
	<xsl:template match="title">
		<td>
			<xsl:value-of select="." />
		</td>
	</xsl:template>
	
	<xsl:template name="num">
		<xsl:param name='align' />
		<xsl:element name="td">
			<xsl:attribute name="align">
				<xsl:value-of select="$align" />
			</xsl:attribute>
			<xsl:number count="chapter" format="1" level="multiple" />
		</xsl:element>
	</xsl:template>
	
</xsl:stylesheet>
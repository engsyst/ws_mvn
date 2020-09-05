<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:tns="http://demo.shop/entity"
	exclude-result-prefixes="tns">

	<xsl:strip-space elements="*"/>
	<xsl:variable name="title">Содержание</xsl:variable>

	<!-- HTML 5 define alternate system doctype for not supported XSLT parsers -->
	<!-- "about:legacy-compat" -->
	<xsl:output method="html" doctype-system="about:legacy-compat" 
	indent="yes" include-content-type="yes"/>

	<xsl:template match="/">
		<!-- Another way output HTML 5 Doctype -->
		<!-- Not all parsers support "disable-output-escaping" -->
		<!-- <xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text> -->
		<html>
		<head>
		<title><xsl:value-of select="$title"/></title>
		</head>
		<body>
			<h2><xsl:value-of select="$title"/></h2>
			<xsl:apply-templates />
		</body>
		</html>
	</xsl:template>
	<xsl:template match="tns:book">
		
		<xsl:apply-templates select="tns:title" />
		<xsl:variable name="price"><xsl:value-of select="tns:price" /></xsl:variable>
		<xsl:choose>
			<xsl:when test="$price=0.0">
				<hr color="red"/>
			</xsl:when>
			<xsl:otherwise>
				<hr />
			</xsl:otherwise>
		 </xsl:choose> 
		<p>
			<xsl:apply-templates select="tns:author" />
		</p>
	</xsl:template>
	<xsl:template match="tns:title">
		Title:
		<span style="#4a4a4a; font-weight: bold">
			<xsl:value-of select="." />
		</span>
	</xsl:template>
	<xsl:template match="tns:author">
		Author:
		<xsl:value-of select="." />
	</xsl:template>

</xsl:stylesheet>
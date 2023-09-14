<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:tns="http://demo.shop/entity"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	>
<!-- 	xsi:schemaLocation="http://demo.shop/entity books.xsd " -->

<!-- 	<xsl:namespace-alias stylesheet-prefix="tns"
		result-prefix="tns" />  -->

	<xsl:template match="/">
		<xsl:element name="tns:catalog">
			<!-- <xsl:attribute name="xmlns:xsi">http://www.w3.org/2001/XMLSchema-instance</xsl:attribute> -->
			<xsl:attribute name="xsi:schemaLocation">http://demo.shop/entity books.xsd</xsl:attribute>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="//tns:book">
		<tns:book>
			<xsl:apply-templates select="tns:title" />
			<xsl:apply-templates select="tns:author" />
		</tns:book>
	</xsl:template>
	<xsl:template match="tns:title">
			<tns:title><xsl:value-of select="." /></tns:title>
	</xsl:template>
	<xsl:template match="tns:author">
		<tns:author><xsl:value-of select="." /></tns:author>
	</xsl:template>
	
</xsl:stylesheet>
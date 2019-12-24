<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tns="http://order.nure.ua/entity">
	<xsl:output method="html" doctype-public="html"/>
	<xsl:template match="tns:orders">
		<h2>Order</h2>
		<hr/>
		<xsl:for-each select="tns:orderItem">
		<xsl:sort select="@id"/>
			<h2><xsl:number format="2"/>) id: <xsl:value-of select="@id"/></h2>
			<hr/>
			<p>Count: <xsl:value-of select="tns:count"/></p>
			<xsl:apply-templates select="tns:book"/>
		</xsl:for-each>
		<xsl:call-template name="test"/>
	</xsl:template>
	<xsl:template name="test">
	<hr/>
	<!-- <xsl:value-of select="child::book"/> -->
	</xsl:template>
	<xsl:template match="tns:book">
		<ul>
			<li><xsl:value-of select="tns:title"/></li>
			<li><xsl:for-each select="tns:author">
			<xsl:value-of select="text()"/>
			<!-- current: <xsl:value-of select="current()"/>
			last: <xsl:value-of select="last()"/> -->
			<xsl:choose>
				<xsl:when test="position() != last()">, </xsl:when>
			</xsl:choose>
			</xsl:for-each>
			</li>
			<li><xsl:value-of select="tns:price"/></li>
		</ul>
	</xsl:template>
</xsl:stylesheet>
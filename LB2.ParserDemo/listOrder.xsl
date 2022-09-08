<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tns="http://order.nure.ua/entity/order/"
	xmlns:bk="http://order.nure.ua/entity/book/"
				exclude-result-prefixes="tns bk">
	<xsl:strip-space elements="*"/>
	<xsl:output method="html" doctype-public="about:legacy-compat"
				indent="yes" media-type="text/html;charset=UTF8"/>
	<xsl:template match="/">
		<html>
			<body>
				<h1>Founded orders</h1>
				<hr/>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>
	<xsl:template name="test">
	<hr/>
	<!-- <xsl:value-of select="child::book"/> -->
	</xsl:template>

	<xsl:template match="tns:order">
		<h2>Order <xsl:value-of select="tns:orderItem[position()=1]/@id"/></h2> 
		<xsl:for-each select="tns:orderItem">
		<xsl:sort select="tns:book/@id"/>
		<h3><xsl:number format="2"/>) Count: <xsl:value-of select="tns:count"/></h3>
		<xsl:apply-templates select="tns:book"/>
		</xsl:for-each>
		<xsl:call-template name="test"/>
	
	</xsl:template>

	<xsl:template match="tns:book">
		<ul>
			<li>id: <xsl:value-of select="@id"/></li>
			<li><xsl:value-of select="bk:title"/></li>
			<li><xsl:for-each select="bk:author">
					<xsl:value-of select="."/>
					<!-- current: <xsl:value-of select="current()"/>
                    last: <xsl:value-of select="last()"/> -->
					<xsl:choose>
						<xsl:when test="position() != last()">, </xsl:when>
					</xsl:choose>
				</xsl:for-each></li>
			<li><xsl:value-of select="bk:price"/></li>
		</ul>
	</xsl:template>
</xsl:stylesheet>
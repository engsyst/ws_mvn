<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Content</title>
			</head>
			<body>
				<h2>CD Collection</h2>
				<table>
					<tr>
						<th>#</th>
						<th>title</th>
						<th>artist</th>
						<th>country</th>
						<th>company</th>
						<th>price</th>
						<th>year</th>
					</tr>
					<xsl:for-each select="//cd">
						<tr>
							<td><xsl:number />                    </td>
							<td><xsl:value-of select="title" />   </td>
							<td><xsl:value-of select="artist" />  </td>
							<td><xsl:value-of select="country" /> </td>
							<td><xsl:value-of select="company" /> </td>
							<td><xsl:value-of select="price" />   </td>
							<td><xsl:value-of select="year" />    </td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
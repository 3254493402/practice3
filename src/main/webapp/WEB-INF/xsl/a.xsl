<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html"/>

	<!-- TODO customize transformation rules 
	     syntax recommendation http://www.w3.org/TR/xslt 
	-->
	<xsl:template match="/">
		<html>
			<head>
				<title>九九乘法表-xsl</title>
			</head>
			<body>
				<form>
					<table border="1" >
						<xsl>
							<xsl:for-each select="document/a">
								<tr>
									<xsl:for-each select="eKey">
										<xsl:choose>
											<xsl:when test="kThr/@name">
											<td>
												<xsl:value-of select="kOne"/>&#215;
												<xsl:value-of select="kTwo"/>=
												<span style='color:red'>
												<xsl:value-of select="kThr"/>
												</span>
											</td>
											</xsl:when>
											<xsl:otherwise>
											<td>
												<xsl:value-of select="kOne"/>&#215;
												<xsl:value-of select="kTwo"/>=
												<xsl:value-of select="kThr"/>
											</td>
											</xsl:otherwise>
										</xsl:choose>
										
									</xsl:for-each>	
								</tr>
							</xsl:for-each>	
						</xsl>
					</table>
				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>

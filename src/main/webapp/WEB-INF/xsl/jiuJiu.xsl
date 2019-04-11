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
					<table border="1" width="100%">
						<xsl>
							<tr>
								<th style="text-align:center">eKey</th>
								<xsl:for-each select="document/eKey">
									<td>
										<xsl:value-of select="kOne"/>
									</td>
									<td>
										<xsl:value-of select="kTwo"/>
									</td>
									<td>
										<xsl:value-of select="kThr"/>
									</td>
								</xsl:for-each>
							</tr>
						</xsl>
					</table>
				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>

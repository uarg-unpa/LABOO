<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <alumnos_aprobados>
            <xsl:for-each select="calificaciones/alumno[nota &gt; 70]">
                <alumno>
                    <nombre><xsl:value-of select="nombre"/></nombre>
                    <materia><xsl:value-of select="materia"/></materia>
                    <nota><xsl:value-of select="nota"/></nota>
                </alumno>
            </xsl:for-each>
        </alumnos_aprobados>
    </xsl:template>
</xsl:stylesheet>
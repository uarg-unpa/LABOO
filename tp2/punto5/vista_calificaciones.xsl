<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Calificaciones del <xsl:value-of select="calificaciones/@fecha"/></title>
                <style>
                    table { border-collapse: collapse; width: 50%; margin-bottom: 20px; }
                    th, td { border: 1px solid black; padding: 8px; text-align: left; }
                    th { background-color: #333; color: white; }
                    /* Clases de colores para las notas */
                    .verde { background-color: #a8e6cf; }
                    .amarillo { background-color: #ffd3b6; }
                    .rojo { background-color: #ffaaa5; }
                </style>
            </head>
            <body>
                <h1>Reporte de Calificaciones - Fecha: <xsl:value-of select="calificaciones/@fecha"/></h1>
                
                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>Materia</th>
                        <th>Nota</th>
                    </tr>
                    
                    <xsl:for-each select="calificaciones/alumno">
                        <xsl:sort select="nota" data-type="number" order="descending"/>
                        
                        <xsl:variable name="colorFila">
                            <xsl:choose>
                                <xsl:when test="nota &gt; 70">verde</xsl:when>
                                <xsl:when test="nota &gt;= 40">amarillo</xsl:when>
                                <xsl:otherwise>rojo</xsl:otherwise>
                            </xsl:choose>
                        </xsl:variable>

                        <tr class="{$colorFila}">
                            <td>
                                <xsl:value-of select="nombre"/>
                                <xsl:if test="@tipo = 'recursante'"> (*)</xsl:if>
                            </td>
                            <td><xsl:value-of select="materia"/></td>
                            <td><xsl:value-of select="nota"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

                <p><i>(*) Indica alumno recursante.</i></p>

                <h3>Resumen:</h3>
                <ul>
                    <li>Total de alumnos: <xsl:value-of select="count(calificaciones/alumno)"/></li>
                    <li>Total aprobados: <xsl:value-of select="count(calificaciones/alumno[nota &gt; 70])"/></li>
                    <li>Total desaprobados: <xsl:value-of select="count(calificaciones/alumno[nota &lt;= 70])"/></li>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
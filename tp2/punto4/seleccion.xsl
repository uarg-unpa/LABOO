<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <!-- Define el tipo de salida como HTML -->
    <xsl:output method="html" indent="yes"/>

    <!-- TEMPLATE PRINCIPAL: Se aplica a la raíz del documento -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Selección Nacional Argentina</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    table { border-collapse: collapse; width: 60%; margin-bottom: 30px; }
                    th, td { border: 1px solid #dddddd; padding: 8px; text-align: left; }
                    th { background-color: #4CAF50; color: white; }
                </style>
            </head>
            <body>
                <h1>Gestión de Jugadores</h1>
                
                <h2>Equipos Registrados</h2>
                <table>
                    <tr>
                        <th>ID Equipo</th>
                        <th>Nombre</th>
                    </tr>
                    <!-- Llama al template de los equipos -->
                    <xsl:apply-templates select="seleccion/equipos/equipo"/>
                </table>

                <h2>Jugadores Convocados</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Posición</th>
                        <th>Edad</th>
                        <th>ID Equipo</th>
                    </tr>
                    <!-- Llama al template de los jugadores[cite: 1] -->
                    <xsl:apply-templates select="seleccion/jugadores/jugador"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- TEMPLATE PARA EQUIPOS: Cómo se debe transformar cada equipo[cite: 1] -->
    <xsl:template match="equipo">
        <tr>
            <!-- Extrae el valor de cada nodo[cite: 1] -->
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="nombre"/></td>
        </tr>
    </xsl:template>

    <!-- TEMPLATE PARA JUGADORES: Cómo se debe transformar cada jugador[cite: 1] -->
    <xsl:template match="jugador">
        <tr>
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="apellido"/></td>
            <td><xsl:value-of select="nombre"/></td>
            <td><xsl:value-of select="posicion"/></td>
            <td><xsl:value-of select="edad"/></td>
            <td><xsl:value-of select="equipo_id"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
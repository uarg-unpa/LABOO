package punto4;

public class Programa {
    public static void main(String[] args) {
        try {
            GeneradorHTML generador = new GeneradorHTML();
            
            System.out.println("Iniciando transformación XSLT...");
            
            generador.realizarTransformacion();
            
            System.out.println("¡Éxito! Se ha generado el archivo: salida_seleccion.html");
            
        } catch (Exception e) {
            System.out.println("Error al generar el HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
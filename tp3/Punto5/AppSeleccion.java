import java.util.ArrayList;

public class AppSeleccion {
    public static void main(String[] args) {
        ControladorSeleccion Controlador = new ControladorSeleccion();

        System.out.println("=== SISTEMA DE GESTION ===");

        // a) Crear JSON en Java (Cargamos memoria inicial)
        Controlador.cargarDatosIniciales();
        System.out.println("a) Datos iniciales generados en memoria.");

        // b) Guardar archivo
        Controlador.guardarEnArchivo();
        System.out.println("b) Archivo 'seleccion.json' guardado con éxito.");

        // c) Mostrar en consola (Lo leemos directamente del JSON generado)
        System.out.println("c) El archivo ya está listo para ser leido.");

        // d) Leer y parsear JSON
        Controlador.leerDesdeArchivo();
        System.out.println("d) Archivo leido y parseado a Objetos Java.");

        // e) Modificar datos (ABM)
        System.out.println("\ne) Aplicando ABM...");
        Controlador.agregarEquipo(new Equipo(3, "Inter Miami"));
        Controlador.agregarJugador(new Jugador(3, "Messi", "Lionel", "Delantero", 36, 3));
        System.out.println("   -> Alta: Lionel Messi (Inter Miami).");

        Controlador.actualizarEdadJugador(1, 32); // Actualiza al Dibu (ID 1)
        System.out.println("   -> Modificacion: Edad de Emiliano Martinez actualizada a 32.");

        Controlador.eliminarJugador(2); // Elimina a Dybala (ID 2)
        System.out.println("   -> Baja: Paulo Dybala eliminado.");

        // f) Guardar cambios
        Controlador.guardarEnArchivo();
        System.out.println("f) Cambios guardados en 'seleccion.json'.\n");

        // Obtenemos la lista actualizada para hacer las consultas
        ArrayList<Jugador> listaFinal = Controlador.getJugadores();

        // g) Realizar consultas (Filtro por edad)
        System.out.println("g) CONSULTAS: Jugadores mayores de 31 años:");
        for (Jugador j : listaFinal) {
            if (j.getEdad() > 31) {
                System.out.println("   - " + j.getApellido() + ", " + j.getNombre() + " (" + j.getEdad() + " años)");
            }
        }

        // h) Generar estadisticas
        System.out.println("\nh) ESTADISTICAS:");
        long sumaEdades = 0;
        long edadMax = 0;
        String jugadorMasGrande = "";

        for (Jugador j : listaFinal) {
            sumaEdades += j.getEdad();
            if (j.getEdad() > edadMax) {
                edadMax = j.getEdad();
                jugadorMasGrande = j.getApellido() + " " + j.getNombre();
            }
        }

        double promedio = listaFinal.isEmpty() ? 0 : (double) sumaEdades / listaFinal.size();

        System.out.println("   - Cantidad total de jugadores: " + listaFinal.size());
        System.out.println("   - Edad promedio: " + String.format("%.1f", promedio));
        System.out.println("   - Jugador más veterano: " + jugadorMasGrande + " (" + edadMax + " años)");
    }
}
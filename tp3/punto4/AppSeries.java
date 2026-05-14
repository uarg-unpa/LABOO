import java.util.Scanner;

public class AppSeries {
    public static void main(String[] args) {
        ControladorSeries controlador = new ControladorSeries();
        controlador.cargarDatos("series.json");
        Scanner sc = new Scanner(System.in);

        // a) Listar información
        System.out.println("--- LISTADO DE SERIES ---");
        for (Serie s : controlador.getListaSeries()) {
            System.out.println(s.getTitulo() + " (" + s.getAnio() + ") - " + s.getTemporadas() + " temporadas.");
        }

        // b) Contar actores
        System.out.println("\n--- CANTIDAD DE ACTORES ---");
        for (Serie s : controlador.getListaSeries()) {
            System.out.println(s.getTitulo() + ": " + s.getActores().size() + " actores."); // Uso de size() [cite: 872]
        }

        // c) Filtrar > 8
        System.out.println("\n--- CALIFICACIÓN MAYOR A 8 ---");
        for (Serie s : controlador.filtrarPorCalificacion(8.0)) {
            System.out.println(s.getTitulo() + " - Calificación: " + s.getCalificacion());
        }

        // d) Búsqueda avanzada
        System.out.print("\nIngrese actor a buscar: ");
        String busqueda = sc.nextLine();
        for (Serie s : controlador.buscarPorActor(busqueda)) {
            System.out.println("Encontrado en: " + s.getTitulo());
        }
        
        sc.close();
    }
}
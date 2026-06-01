import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
@SuppressWarnings("unchecked")// Omitir advertencias de tipo sin verificar al usar JSON.simple
public class GeneradorSeriesJSON {
    /** 
     * @param args
     */
    public static void main(String[] args) {
        
        // 3.a) Crear el JSON en Java
        // Objeto raíz que contendrá el array de series
        JSONObject raiz = new JSONObject();
        JSONArray listaSeries = new JSONArray();

        // --- SERIE 1 ---
        JSONObject serie1 = new JSONObject();
        serie1.put("id", 1);
        serie1.put("titulo", "Game of Thrones");
        serie1.put("creador", "David Benioff");
        serie1.put("anio_lanzamiento", 2011);
        serie1.put("cantidad_temporadas", 8);
        
        JSONArray episodios1 = new JSONArray();
        episodios1.add(10); episodios1.add(10); episodios1.add(10); 
        episodios1.add(10); episodios1.add(10); episodios1.add(10); 
        episodios1.add(7); episodios1.add(6);
        serie1.put("episodios_por_temporada", episodios1);
        
        JSONArray actores1 = new JSONArray();
        actores1.add("Sean Bean"); actores1.add("Peter Dinklage"); actores1.add("Emilia Clarke");
        serie1.put("actores_principales", actores1);
        
        JSONArray generos1 = new JSONArray();
        generos1.add("Fantasia"); generos1.add("Drama"); generos1.add("Aventura");
        serie1.put("generos", generos1);
        
        serie1.put("estado", "finalizada");
        serie1.put("calificacion", 9.2);

        // --- SERIE 2 ---
        JSONObject serie2 = new JSONObject();
        serie2.put("id", 2);
        serie2.put("titulo", "The Boys");
        serie2.put("creador", "Eric Kripke");
        serie2.put("anio_lanzamiento", 2019);
        serie2.put("cantidad_temporadas", 3);
        
        JSONArray episodios2 = new JSONArray();
        episodios2.add(8); episodios2.add(8); episodios2.add(8);
        serie2.put("episodios_por_temporada", episodios2);
        
        JSONArray actores2 = new JSONArray();
        actores2.add("Karl Urban"); actores2.add("Jack Quaid"); actores2.add("Antony Starr");
        serie2.put("actores_principales", actores2);
        
        JSONArray generos2 = new JSONArray();
        generos2.add("Accion"); generos2.add("Comedia negra"); generos2.add("Ciencia Ficcion");
        serie2.put("generos", generos2);
        
        serie2.put("estado", "en emision");
        serie2.put("calificacion", 8.7);

        // Agregar las series al array principal
        listaSeries.add(serie1);
        listaSeries.add(serie2);
        
        // Agregar el array al objeto raíz
        raiz.put("series", listaSeries);

        // 3.b) Guardar el archivo generado
        try (FileWriter file = new FileWriter("series.json")) {
            file.write(raiz.toJSONString());
            file.flush();
            System.out.println("Archivo guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al guardar el archivo.");
            e.printStackTrace();
        }

        // 3.c) Mostrar el contenido en consola
        System.out.println("\n--- Contenido del JSON ---");
        System.out.println(raiz.toJSONString());
    }
}
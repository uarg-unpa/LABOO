import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;

public class ControladorSeries {
    private ArrayList<Serie> listaSeries;

    public ControladorSeries() {
        this.listaSeries = new ArrayList<>();
    }

    /** 
     * @param ruta
     */
    public void cargarDatos(String ruta) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(ruta)) {
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray jsonArray = (JSONArray) root.get("series");

            for (Object obj : jsonArray) {
                JSONObject item = (JSONObject) obj;
                
                // Extracción con conversiones
                long id = (long) item.get("id");
                String titulo = (String) item.get("titulo");
                long anio = (long) item.get("año_lanzamiento");
                long temp = (long) item.get("cantidad_temporadas");
                
                Number califNum = (Number) item.get("calificacion");
                double calif = califNum.doubleValue();

                JSONArray actoresJson = (JSONArray) item.get("actores_principales");
                ArrayList<String> listaActores = new ArrayList<>();
                for (Object act : actoresJson) {
                    listaActores.add((String) act);
                }

                listaSeries.add(new Serie(id, titulo, anio, temp, listaActores, calif));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Serie> getListaSeries() { return listaSeries; }

    /** 
     * @param min
     * @return ArrayList<Serie>
     */
    // Lógica para filtrar (Punto 4.c)
    public ArrayList<Serie> filtrarPorCalificacion(double min) {
        ArrayList<Serie> filtradas = new ArrayList<>();
        for (Serie s : listaSeries) {
            if (s.getCalificacion() > min) filtradas.add(s);
        }
        return filtradas;
    }

    /** 
     * @param nombre
     * @return ArrayList<Serie>
     */
    // Lógica para buscar actor (Punto 4.d)
    public ArrayList<Serie> buscarPorActor(String nombre) {
        ArrayList<Serie> resultados = new ArrayList<>();
        for (Serie s : listaSeries) {
            for (String actor : s.getActores()) {
                if (actor.equalsIgnoreCase(nombre)) {
                    resultados.add(s);
                    break;
                }
            }
        }
        return resultados;
    }
}
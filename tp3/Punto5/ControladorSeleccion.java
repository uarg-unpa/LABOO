import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ControladorSeleccion {
    private ArrayList<Equipo> equipos;
    private ArrayList<Jugador> jugadores;
    private String rutaArchivo = "seleccion.json";

    public ControladorSeleccion() {
        equipos = new ArrayList<>();
        jugadores = new ArrayList<>();
    }

    // --- a) Crear JSON en Java (Carga inicial en memoria) ---
    public void cargarDatosIniciales() {
        equipos.add(new Equipo(1, "Aston Villa"));
        equipos.add(new Equipo(2, "Roma"));
        jugadores.add(new Jugador(1, "Martínez", "Emiliano", "Arquero", 31, 1));
        jugadores.add(new Jugador(2, "Dybala", "Paulo", "Delantero", 30, 2));
    }

    // --- b) y f) Guardar archivo (Convierte Listas a JSON) ---
    @SuppressWarnings("unchecked")
    public void guardarEnArchivo() {
        JSONObject baseDatos = new JSONObject();
        JSONArray eqArray = new JSONArray();
        JSONArray jugArray = new JSONArray();

        for (Equipo e : equipos) {
            JSONObject obj = new JSONObject();
            obj.put("id", e.getId());
            obj.put("nombre", e.getNombre());
            eqArray.add(obj);
        }

        for (Jugador j : jugadores) {
            JSONObject obj = new JSONObject();
            obj.put("id", j.getId());
            obj.put("apellido", j.getApellido());
            obj.put("nombre", j.getNombre());
            obj.put("posicion", j.getPosicion());
            obj.put("edad", j.getEdad());
            obj.put("equipo_id", j.getEquipoId());
            jugArray.add(obj);
        }

        baseDatos.put("equipos", eqArray);
        baseDatos.put("jugadores", jugArray);

        try (FileWriter file = new FileWriter(rutaArchivo)) {
            file.write(baseDatos.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- d) Leer y parsear JSON (Convierte JSON a Listas) ---
    public void leerDesdeArchivo() {
        equipos.clear();
        jugadores.clear();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(rutaArchivo)) {
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray eqArray = (JSONArray) root.get("equipos");
            JSONArray jugArray = (JSONArray) root.get("jugadores");

            for (Object o : eqArray) {
                JSONObject eq = (JSONObject) o;
                equipos.add(new Equipo((long) eq.get("id"), (String) eq.get("nombre")));
            }

            for (Object o : jugArray) {
                JSONObject j = (JSONObject) o;
                jugadores.add(new Jugador(
                    (long) j.get("id"), (String) j.get("apellido"), (String) j.get("nombre"),
                    (String) j.get("posicion"), (long) j.get("edad"), (long) j.get("equipo_id")
                ));
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo. Asegúrese de crearlo primero.");
        }
    }

    /** 
     * @param id
     * @param nuevaEdad
     */
    // --- e) Métodos ABM (Actualizar, Agregar, Eliminar) ---
    public void agregarEquipo(Equipo e) { equipos.add(e); }
    public void agregarJugador(Jugador j) { jugadores.add(j); }
    
    /** 
     * @param id
     * @param nuevaEdad
     */
    public void actualizarEdadJugador(long id, long nuevaEdad) {
        for (Jugador j : jugadores) {
            if (j.getId() == id) {
                j.setEdad(nuevaEdad);
                break;
            }
        }
    }

    /** 
     * @param id
     */
    public void eliminarJugador(long id) {
        jugadores.removeIf(j -> j.getId() == id);
    }

    /** 
     * @return ArrayList<Jugador>
     */
    // Getters para la vista
    public ArrayList<Jugador> getJugadores() { 
        return jugadores; 
    }
    /** 
     * @return ArrayList<Equipo>
     */
    public ArrayList<Equipo> getEquipos() { 
        return equipos; 
    }
}
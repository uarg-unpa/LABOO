public class Equipo {
    private long id;
    private String nombre;

    public Equipo(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /** 
     * @return long
     */
    public long getId() { 
        return id; 
    }
    /** 
     * @return String
     */
    public String getNombre() { 
        return nombre; 
    }
}
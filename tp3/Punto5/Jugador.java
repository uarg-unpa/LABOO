public class Jugador {
    private long id;
    private String apellido;
    private String nombre;
    private String posicion;
    private long edad;
    private long equipoId;

    public Jugador(long id, String apellido, String nombre, String posicion, long edad, long equipoId) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.equipoId = equipoId;
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
    public String getApellido() { 
        return apellido; 
    }
    /** 
     * @return String
     */
    public String getNombre() { 
        return nombre; 
    }
    /** 
     * @return String
     */
    public String getPosicion() { 
        return posicion; 
    }
    /** 
     * @return long
     */
    public long getEdad() { 
        return edad; 
    }
    /** 
     * @param edad
     */
    public void setEdad(long edad) { 
        this.edad = edad; 
    }
    /** 
     * @return long
     */
    public long getEquipoId() { 
        return equipoId; 
    }
}
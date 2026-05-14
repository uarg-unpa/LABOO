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

    public long getId() { 
        return id; 
    }
    public String getApellido() { 
        return apellido; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getPosicion() { 
        return posicion; 
    }
    public long getEdad() { 
        return edad; 
    }
    public void setEdad(long edad) { 
        this.edad = edad; 
    }
    public long getEquipoId() { 
        return equipoId; 
    }
}
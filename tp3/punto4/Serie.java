import java.util.ArrayList;

public class Serie {
    private long id;
    private String titulo;
    private long anio;
    private long temporadas;
    private ArrayList<String> actores;
    private double calificacion;

    // Constructor, Getters y Setters
    public Serie(long id, String titulo, long anio, long temporadas, ArrayList<String> actores, double calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.temporadas = temporadas;
        this.actores = actores;
        this.calificacion = calificacion;
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
    public String getTitulo() {
        return titulo; 
    }
    /** 
     * @return long
     */
    public long getAnio() { 
        return anio; 
    }
    /** 
     * @return long
     */
    public long getTemporadas() {
        return temporadas; 
    }
    /** 
     * @return ArrayList<String>
     */
    public ArrayList<String> getActores() {
        return actores;
    }
    /** 
     * @return double
     */
    public double getCalificacion() {
        return calificacion; 
    }
}
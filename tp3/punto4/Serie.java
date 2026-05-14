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
    public long getId() {
        return id; 
    }
    public String getTitulo() {
        return titulo; 
    }
    public long getAnio() { 
        return anio; 
    }
    public long getTemporadas() {
        return temporadas; 
    }
    public ArrayList<String> getActores() {
        return actores;
    }
    public double getCalificacion() {
        return calificacion; 
    }
}
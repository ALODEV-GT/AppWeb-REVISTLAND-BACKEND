package modelo.recomendacion;

public class RecomendacionM {

    private int idRevista;
    private String nombreRevista;
    private String autor;
    private double precioMensual;
    private double precioAnual;
    private int numComentarios;
    private int numLike;
    private String fechaPublicacion;

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public RecomendacionM() {
    }

    public RecomendacionM(String nombreRevista, String autor, double precioMensual, double precioAnual, int idRevista, String fechaPublicacion) {
        this.nombreRevista = nombreRevista;
        this.autor = autor;
        this.precioMensual = precioMensual;
        this.precioAnual = precioAnual;
        this.idRevista = idRevista;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }
    
    

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public double getPrecioAnual() {
        return precioAnual;
    }

    public void setPrecioAnual(double precioAnual) {
        this.precioAnual = precioAnual;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

}

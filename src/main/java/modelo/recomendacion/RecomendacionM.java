package modelo.recomendacion;

public class RecomendacionM {

    private String nombreRevista;
    private String autor;
    private double precioMensual;
    private double precioAnual;
    private int numComentarios;
    private int numLike;

    public RecomendacionM() {
    }

    public RecomendacionM(String nombreRevista, String autor, double precioMensual, double precioAnual) {
        this.nombreRevista = nombreRevista;
        this.autor = autor;
        this.precioMensual = precioMensual;
        this.precioAnual = precioAnual;
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

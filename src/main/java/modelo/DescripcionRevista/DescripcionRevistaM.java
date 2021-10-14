package modelo.DescripcionRevista;

public class DescripcionRevistaM {

    private int idRevista;
    private String nombreRevista;
    private String[] categorias;
    private String[] etiquetas;
    private String autor;
    private double precioMensual;
    private double precioAnual;
    private String descripcionRevista;

    public DescripcionRevistaM() {
    }

    public DescripcionRevistaM(int idRevista, String nombreRevista, String autor, double precioMensual, double precioAnual, String descripcionRevista) {
        this.idRevista = idRevista;
        this.nombreRevista = nombreRevista;
        this.autor = autor;
        this.precioMensual = precioMensual;
        this.precioAnual = precioAnual;
        this.descripcionRevista = descripcionRevista;
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

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
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

    public String getDescripcionRevista() {
        return descripcionRevista;
    }

    public void setDescripcionRevista(String descripcionRevista) {
        this.descripcionRevista = descripcionRevista;
    }

}

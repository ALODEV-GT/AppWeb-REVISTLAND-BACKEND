package modelo.revistasConsumidor;

public class MiRevistaConsumidorM {

    private String nombreRevista;
    private String autor;
    private String fechaSuscripcion;
    private String fechaFinalizacion;
    private String[] categorias;
    private String[] etiquetas;
    private int idRevista;

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public MiRevistaConsumidorM() {
    }

    public MiRevistaConsumidorM(String nombreRevista, String autor, String fechaSuscripcion, String fechaFinalizacion, String[] categorias, String[] etiquetas, int idRevista) {
        this.nombreRevista = nombreRevista;
        this.autor = autor;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.categorias = categorias;
        this.etiquetas = etiquetas;
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

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
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

}

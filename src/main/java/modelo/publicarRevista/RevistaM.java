package modelo.publicarRevista;

public class RevistaM {

    private int idRevista;
    private String nombre;
    private String descripcion;
    private double costoMes;
    private double costoAnio;
    private int esInteractiva;
    private int permitirInteracciones;
    private String categorias[];
    private String etiquetas[];

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

    public RevistaM() {
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoMes() {
        return costoMes;
    }

    public void setCostoMes(double costoMes) {
        this.costoMes = costoMes;
    }

    public double getCostoAnio() {
        return costoAnio;
    }

    public void setCostoAnio(double costoAnio) {
        this.costoAnio = costoAnio;
    }

    public int getEsInteractiva() {
        return esInteractiva;
    }

    public void setEsInteractiva(int esInteractiva) {
        this.esInteractiva = esInteractiva;
    }

    public int getPermitirInteracciones() {
        return permitirInteracciones;
    }

    public void setPermitirInteracciones(int permitirInteracciones) {
        this.permitirInteracciones = permitirInteracciones;
    }
}

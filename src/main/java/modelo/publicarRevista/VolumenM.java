package modelo.publicarRevista;
public class VolumenM {
    private int idVolumen;
    private int idRevista;
    private String nombreVolumen;
    private String fechaPublicacion;
    private int idArchivo;

    public VolumenM() {
    }

    public VolumenM(String nombreVolumen, String fechaPublicacion, int idArchivo) {
        this.nombreVolumen = nombreVolumen;
        this.fechaPublicacion = fechaPublicacion;
        this.idArchivo = idArchivo;
    }
    
    public int getIdVolumen() {
        return idVolumen;
    }

    public void setIdVolumen(int idVolumen) {
        this.idVolumen = idVolumen;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getNombreVolumen() {
        return nombreVolumen;
    }

    public void setNombreVolumen(String nombreVolumen) {
        this.nombreVolumen = nombreVolumen;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }
    
    
}

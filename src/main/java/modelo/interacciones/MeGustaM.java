package modelo.interacciones;

public class MeGustaM {

    private String nombreUsuario;
    private String fecha;
    private int idPulicacion;

    public MeGustaM() {
    }

    public MeGustaM(String nombreUsuario, String fecha, int idPulicacion) {
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.idPulicacion = idPulicacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdPulicacion() {
        return idPulicacion;
    }

    public void setIdPulicacion(int idPulicacion) {
        this.idPulicacion = idPulicacion;
    }

}

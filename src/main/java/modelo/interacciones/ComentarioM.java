package modelo.interacciones;

public class ComentarioM {

    private String nombreUsuario;
    private String comentario;
    private String fecha;
    private int idPulicacion;

    public ComentarioM() {
    }

    public ComentarioM(String nombreUsuario, String comentario) {
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
    }

    public ComentarioM(String nombreUsuario, String comentario, String fecha, int idPulicacion) {
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
        this.fecha = fecha;
        this.idPulicacion = idPulicacion;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}

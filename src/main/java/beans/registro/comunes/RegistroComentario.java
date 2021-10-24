package beans.registro.comunes;

public class RegistroComentario {

    private String nombreUsuario;
    private String comentario;
    private String fechaComentario;

    public RegistroComentario() {
    }

    public RegistroComentario(String nombreUsuario, String comentario, String fechaComentario) {
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
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

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

}

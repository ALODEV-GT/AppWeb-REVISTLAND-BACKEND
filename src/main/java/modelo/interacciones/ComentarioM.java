package modelo.interacciones;

public class ComentarioM {

    private String nombreUsuario;
    private String comentario;

    public ComentarioM() {
    }

    public ComentarioM(String nombreUsuario, String comentario) {
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
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

package beans.registro.comunes;

public class RegistroMeGusta {

    private String nombreUsuario;
    private String fechaMeGusta;

    public RegistroMeGusta() {
    }

    public RegistroMeGusta(String nombreUsuario, String fechaMeGusta) {
        this.nombreUsuario = nombreUsuario;
        this.fechaMeGusta = fechaMeGusta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaMeGusta() {
        return fechaMeGusta;
    }

    public void setFechaMeGusta(String fechaMeGusta) {
        this.fechaMeGusta = fechaMeGusta;
    }

}

package beans.registro.comunes;

public class RegistroSuscripcion {

    private String nombreUsuario;
    private String fechaSuscripcion;

    public RegistroSuscripcion() {
    }

    public RegistroSuscripcion(String nombreUsuario, String fechaSuscripcion) {
        this.nombreUsuario = nombreUsuario;
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

}

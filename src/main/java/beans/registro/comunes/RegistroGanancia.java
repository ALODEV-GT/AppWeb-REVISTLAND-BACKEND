package beans.registro.comunes;

public class RegistroGanancia {

    private String nombreUsuario;
    private String fechaSuscripcion;
    private double ganancia;

    public RegistroGanancia() {
    }

    public RegistroGanancia(String nombreUsuario, String fechaSuscripcion, double ganancia) {
        this.nombreUsuario = nombreUsuario;
        this.fechaSuscripcion = fechaSuscripcion;
        this.ganancia = ganancia;
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

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

}

package beans.registro.administrador;

public class RegistroGananciaRevista {

    private String nombreUsuario;
    private String fechaSuscripcion;
    private double ingreso;
    private double costo;
    private double ganancia;

    public RegistroGananciaRevista() {
    }

    public RegistroGananciaRevista(String nombreUsuario, String fechaSuscripcion, double ingreso, double costo, double ganancia) {
        this.nombreUsuario = nombreUsuario;
        this.fechaSuscripcion = fechaSuscripcion;
        this.ingreso = ingreso;
        this.costo = costo;
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

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

}

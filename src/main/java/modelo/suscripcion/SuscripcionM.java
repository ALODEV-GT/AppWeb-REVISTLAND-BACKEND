package modelo.suscripcion;

public class SuscripcionM {

    private int idTipoPago;
    private int idRevista;
    private String fechaSuscripcion;
    private String fechaFinalizacion;
    private String nombreUsuario;
    private double costoTotal;
    private double gananciaEditor;
    private double cuotaPorServicio; //Ganancia para la empresa
    private int vigente;

    public SuscripcionM() {
    }

    public SuscripcionM(int idTipoPago, int idRevista, String fechaSuscripcion, String fechaFinalizacion, String nombreUsuario, double costoTotal, double gananciaEditor, double cuotaPorServicio) {
        this.idTipoPago = idTipoPago;
        this.idRevista = idRevista;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.nombreUsuario = nombreUsuario;
        this.costoTotal = costoTotal;
        this.gananciaEditor = gananciaEditor;
        this.cuotaPorServicio = cuotaPorServicio;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getGananciaEditor() {
        return gananciaEditor;
    }

    public void setGananciaEditor(double gananciaEditor) {
        this.gananciaEditor = gananciaEditor;
    }

    public double getCuotaPorServicio() {
        return cuotaPorServicio;
    }

    public void setCuotaPorServicio(double cuotaPorServicio) {
        this.cuotaPorServicio = cuotaPorServicio;
    }

    public int getVigente() {
        return vigente;
    }

    public void setVigente(int vigente) {
        this.vigente = vigente;
    }

}

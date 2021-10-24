package beans.registro.administrador;

public class RegistroGananciaAnuncio {

    private String fechaCompra;
    private String tipoAnuncio;
    private double precioPorDia;
    private int numDias;
    private double ganancia;

    public RegistroGananciaAnuncio() {
    }

    public RegistroGananciaAnuncio(String fechaCompra, String tipoAnuncio, double precioPorDia, int numDias, double ganancia) {
        this.fechaCompra = fechaCompra;
        this.tipoAnuncio = tipoAnuncio;
        this.precioPorDia = precioPorDia;
        this.numDias = numDias;
        this.ganancia = ganancia;
    }

    public String getFichaCompra() {
        return fechaCompra;
    }

    public void setFichaCompra(String fichaCompra) {
        this.fechaCompra = fichaCompra;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public int getNumDias() {
        return numDias;
    }

    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

}

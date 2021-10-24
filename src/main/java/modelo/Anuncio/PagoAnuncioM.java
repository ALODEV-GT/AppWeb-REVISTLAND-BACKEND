package modelo.Anuncio;

public class PagoAnuncioM {

    private int idPago;
    private int idAnunciante;
    private String fechaCompra;
    private int idAnuncio;
    private double costo;

    public PagoAnuncioM() {
    }

    public PagoAnuncioM(int idAnunciante, String fechaCompra, int idAnuncio, double costo) {
        this.idAnunciante = idAnunciante;
        this.fechaCompra = fechaCompra;
        this.idAnuncio = idAnuncio;
        this.costo = costo;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}

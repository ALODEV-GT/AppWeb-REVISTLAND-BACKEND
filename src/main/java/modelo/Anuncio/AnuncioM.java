package modelo.Anuncio;

public class AnuncioM {

    private int idTipoAnuncio;
    private String nombreAnunciante;
    private String fechaCompra;
    private int cantidadDias;
    private String[] categorias;
    private String[] etiquetas;
    private int idTextoAnuncio;
    private int idArchivo;
    private int idLinkVideo;
    private int idAnuncio;

    public AnuncioM() {
    }

    public AnuncioM(int idTipoAnuncio, int idTextoAnuncio, int idArchivo, int idLinkVideo, int idAnuncio) {
        this.idTipoAnuncio = idTipoAnuncio;
        this.idTextoAnuncio = idTextoAnuncio;
        this.idArchivo = idArchivo;
        this.idLinkVideo = idLinkVideo;
        this.idAnuncio = idAnuncio;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getIdLinkVideo() {
        return idLinkVideo;
    }

    public void setIdLinkVideo(int idLinkVideo) {
        this.idLinkVideo = idLinkVideo;
    }

    public int getIdTipoAnuncio() {
        return idTipoAnuncio;
    }

    public void setIdTipoAnuncio(int idTipoAnuncio) {
        this.idTipoAnuncio = idTipoAnuncio;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public int getIdTextoAnuncio() {
        return idTextoAnuncio;
    }

    public void setIdTextoAnuncio(int idTextoAnuncio) {
        this.idTextoAnuncio = idTextoAnuncio;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

}

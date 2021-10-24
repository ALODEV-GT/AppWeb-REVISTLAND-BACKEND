package modelo.Anuncio;

public class AnuncioMostrarM {

    private int idAnuncio;
    private int idTipoAnuncio;
    private String textoAnuncio;
    private String linkVideo;
    private String imagen;

    public AnuncioMostrarM() {
    }

    public AnuncioMostrarM(int idAnuncio, int idTipoAnuncio) {
        this.idAnuncio = idAnuncio;
        this.idTipoAnuncio = idTipoAnuncio;
    }

    public int getIdTipoAnuncio() {
        return idTipoAnuncio;
    }

    public void setIdTipoAnuncio(int idTipoAnuncio) {
        this.idTipoAnuncio = idTipoAnuncio;
    }
    
    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTextoAnuncio() {
        return textoAnuncio;
    }

    public void setTextoAnuncio(String textoAnuncio) {
        this.textoAnuncio = textoAnuncio;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}

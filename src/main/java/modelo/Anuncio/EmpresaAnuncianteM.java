package modelo.Anuncio;

public class EmpresaAnuncianteM {

    private int idAnunciante;
    private String nombreAnunciante;

    public EmpresaAnuncianteM() {
    }

    public EmpresaAnuncianteM(int idAnunciante, String nombreAnunciante) {
        this.idAnunciante = idAnunciante;
        this.nombreAnunciante = nombreAnunciante;
    }

    public int getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

}

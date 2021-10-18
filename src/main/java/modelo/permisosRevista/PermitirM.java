package modelo.permisosRevista;

public class PermitirM {

    private int idRevista;
    private boolean permitir;
    private int idPublicacion;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public PermitirM() {
    }

    public PermitirM(boolean permitir, int idRevista) {
        this.permitir = permitir;
        this.idRevista = idRevista;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public boolean isPermitir() {
        return permitir;
    }

    public void setPermitir(boolean permitir) {
        this.permitir = permitir;
    }

}

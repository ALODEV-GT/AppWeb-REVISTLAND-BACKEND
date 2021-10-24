package beans.comunes;

public class DatosRevista {

    private String nombreRevista;
    private String nombreEditor;

    public DatosRevista() {
    }

    public DatosRevista(String nombreRevista, String nombreEditor) {
        this.nombreRevista = nombreRevista;
        this.nombreEditor = nombreEditor;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

}

package modelo.costoDiario;

public class RevistaCostoM {

    private String fechaPublicacion;
    private String nombreRevista;
    private String editor;
    private double costo;
    private int idCostoPorDia;

    public RevistaCostoM() {
    }

    public RevistaCostoM(String fechaPublicacion, String nombreRevista, String editor, double costo, int idCostoPorDia) {
        this.fechaPublicacion = fechaPublicacion;
        this.nombreRevista = nombreRevista;
        this.editor = editor;
        this.costo = costo;
        this.idCostoPorDia = idCostoPorDia;
    }

    public String getFechaPublicacio() {
        return fechaPublicacion;
    }

    public void setFechaPublicacio(String fechaPublicacio) {
        this.fechaPublicacion = fechaPublicacio;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getIdCostoPorDia() {
        return idCostoPorDia;
    }

    public void setIdCostoPorDia(int idCostoPorDia) {
        this.idCostoPorDia = idCostoPorDia;
    }

}

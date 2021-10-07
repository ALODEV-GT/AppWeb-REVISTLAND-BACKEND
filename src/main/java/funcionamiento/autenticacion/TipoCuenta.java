package funcionamiento.autenticacion;

public enum TipoCuenta {

    NORMAL("Normal"),
    ADIMINSTRADOR("Administrador"),
    EDITOR("Editor");

    private String nombre;

    private TipoCuenta(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}

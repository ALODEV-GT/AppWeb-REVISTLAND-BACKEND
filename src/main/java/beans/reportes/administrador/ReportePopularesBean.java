package beans.reportes.administrador;

import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroSuscripcion;
import java.util.List;

public class ReportePopularesBean {

    private DatosRevista datosRevista;
    private int numSuscripciones;
    private List<RegistroSuscripcion> suscripciones;

    public ReportePopularesBean() {
    }

    public ReportePopularesBean(DatosRevista datosRevista, int numSuscripciones, List<RegistroSuscripcion> suscripciones) {
        this.datosRevista = datosRevista;
        this.numSuscripciones = numSuscripciones;
        this.suscripciones = suscripciones;
    }

    public DatosRevista getDatosRevista() {
        return datosRevista;
    }

    public void setDatosRevista(DatosRevista datosRevista) {
        this.datosRevista = datosRevista;
    }

    public int getNumSuscripciones() {
        return numSuscripciones;
    }

    public void setNumSuscripciones(int numSuscripciones) {
        this.numSuscripciones = numSuscripciones;
    }

    public List<RegistroSuscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<RegistroSuscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }
}

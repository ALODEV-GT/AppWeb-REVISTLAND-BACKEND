package beans.reportes.editor;

import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroMeGusta;
import java.util.List;

public class ReporteRevistaMasGustadaBean {

    private DatosRevista datosRevista;
    private int numMeGustas;
    private List<RegistroMeGusta> meGustas;

    public ReporteRevistaMasGustadaBean() {
    }

    public ReporteRevistaMasGustadaBean(DatosRevista datosRevista, int numMeGustas, List<RegistroMeGusta> meGustas) {
        this.datosRevista = datosRevista;
        this.numMeGustas = numMeGustas;
        this.meGustas = meGustas;
    }

    public DatosRevista getDatosRevista() {
        return datosRevista;
    }

    public void setDatosRevista(DatosRevista datosRevista) {
        this.datosRevista = datosRevista;
    }

    public int getNumMeGustas() {
        return numMeGustas;
    }

    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }

    public List<RegistroMeGusta> getMeGustas() {
        return meGustas;
    }

    public void setMeGustas(List<RegistroMeGusta> meGustas) {
        this.meGustas = meGustas;
    }

}

package beans.reportes.administrador;

import beans.comunes.DatosRevista;
import beans.registro.administrador.RegistroGananciaRevista;
import java.util.List;

public class TablaReporteGananciaRevistaBean {

    private DatosRevista datosRevista;
    private int numSuscriptores;
    private double totalIngresos;
    private double totalCostos;
    private double totalGanancias;
    private List<RegistroGananciaRevista> registros;

    public TablaReporteGananciaRevistaBean() {
    }

    public TablaReporteGananciaRevistaBean(DatosRevista datosRevista, int numSuscriptores, double totalIngresos, double totalCostos, double totalGanancias, List<RegistroGananciaRevista> registros) {
        this.datosRevista = datosRevista;
        this.numSuscriptores = numSuscriptores;
        this.totalIngresos = totalIngresos;
        this.totalCostos = totalCostos;
        this.totalGanancias = totalGanancias;
        this.registros = registros;
    }

    public DatosRevista getDatosRevista() {
        return datosRevista;
    }

    public void setDatosRevista(DatosRevista datosRevista) {
        this.datosRevista = datosRevista;
    }

    public int getNumSuscriptores() {
        return numSuscriptores;
    }

    public void setNumSuscriptores(int numSuscriptores) {
        this.numSuscriptores = numSuscriptores;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public double getTotalCostos() {
        return totalCostos;
    }

    public void setTotalCostos(double totalCostos) {
        this.totalCostos = totalCostos;
    }

    public double getTotalGanancias() {
        return totalGanancias;
    }

    public void setTotalGanancias(double totalGanancias) {
        this.totalGanancias = totalGanancias;
    }

    public List<RegistroGananciaRevista> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroGananciaRevista> registros) {
        this.registros = registros;
    }

}

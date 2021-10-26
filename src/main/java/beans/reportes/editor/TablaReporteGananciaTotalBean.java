package beans.reportes.editor;

import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroGanancia;
import java.util.List;

public class TablaReporteGananciaTotalBean {

    private DatosRevista datosRevista;
    private double gananciaTotal;
    private List<RegistroGanancia> registrosGanancias;

    public TablaReporteGananciaTotalBean() {
    }

    public TablaReporteGananciaTotalBean(DatosRevista datosRevista, double gananciaTotal, List<RegistroGanancia> registrosGanancias) {
        this.datosRevista = datosRevista;
        this.registrosGanancias = registrosGanancias;
        this.gananciaTotal = gananciaTotal;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public DatosRevista getDatosRevista() {
        return datosRevista;
    }

    public void setDatosRevista(DatosRevista datosRevista) {
        this.datosRevista = datosRevista;
    }

    public List<RegistroGanancia> getRegistrosGanancias() {
        return registrosGanancias;
    }

    public void setRegistrosGanancias(List<RegistroGanancia> registrosGanancias) {
        this.registrosGanancias = registrosGanancias;
    }

}

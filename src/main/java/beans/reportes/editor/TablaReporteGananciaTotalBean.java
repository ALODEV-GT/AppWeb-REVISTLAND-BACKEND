package beans.reportes.editor;

import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroGanancia;
import java.util.List;

public class TablaReporteGananciaTotalBean {

    private DatosRevista datosRevista;
    List<RegistroGanancia> registrosGanancias;

    public TablaReporteGananciaTotalBean() {
    }

    public TablaReporteGananciaTotalBean(DatosRevista datosRevista, List<RegistroGanancia> registrosGanancias) {
        this.datosRevista = datosRevista;
        this.registrosGanancias = registrosGanancias;
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

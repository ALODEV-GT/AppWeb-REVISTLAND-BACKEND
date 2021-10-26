package dao.reportes.administrador;

import beans.reportes.administrador.ReporteGananciaAnuncioBean;
import beans.reportes.administrador.ReporteGananciaRevistaBean;
import beans.reportes.administrador.TablaReporteGananciaAnuncioBean;
import beans.reportes.administrador.TablaReporteGananciaRevistaBean;
import beans.reportes.administrador.ReporteGananciaTotalBean;
import java.util.ArrayList;
import java.util.List;

public class ReporteGananciaTotalDATOS {

    public List<ReporteGananciaTotalBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {

        List<ReporteGananciaTotalBean> lista = new ArrayList<>();

        List<ReporteGananciaAnuncioBean> reporteGananciasAnuncios = new ReporteGananciaAnuncioDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        List<ReporteGananciaRevistaBean> reporteGananciasRevistas = new ReporteGananciaRevistaDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        List<TablaReporteGananciaRevistaBean> tablasRevistas = reporteGananciasRevistas.get(0).getTabla();
        List<TablaReporteGananciaAnuncioBean> tablasAnuncios = reporteGananciasAnuncios.get(0).getTablas();

        double gananciaTotalRevistas = 0;
        double gananciaTotalAnuncios = 0;

        for (TablaReporteGananciaRevistaBean t : tablasRevistas) {
            gananciaTotalRevistas += t.getTotalGanancias();
        }
        for (TablaReporteGananciaAnuncioBean t : tablasAnuncios) {
            gananciaTotalAnuncios += t.getGananciaTotal();
        }
        double gananciaTotal = gananciaTotalAnuncios + gananciaTotalRevistas;

        ReporteGananciaTotalBean reporte = new ReporteGananciaTotalBean(gananciaTotalRevistas, gananciaTotalAnuncios, gananciaTotal, tablasRevistas, tablasAnuncios);
        lista.add(reporte);

        return lista;
    }

}

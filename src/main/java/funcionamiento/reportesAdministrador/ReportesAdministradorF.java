package funcionamiento.reportesAdministrador;

import beans.reportes.administrador.ReporteGananciaAnuncioBean;
import beans.reportes.administrador.ReporteGananciaRevistaBean;
import beans.reportes.administrador.ReporteGananciaTotalBean;
import beans.reportes.administrador.ReporteMasComentadaBean;
import beans.reportes.administrador.ReportePopularesBean;
import dao.reportes.administrador.ReporteGananciaAnuncioDAO;
import dao.reportes.administrador.ReporteGananciaRevistaDAO;
import dao.reportes.administrador.ReporteGananciaTotalDATOS;
import dao.reportes.administrador.ReporteMasComentadasDAO;
import dao.reportes.administrador.ReportePopularesDAO;
import funcionamiento.Funcionamiento;
import funcionamiento.reportes.GenerarReporte;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportesAdministradorF extends Funcionamiento {

    public ReportesAdministradorF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {

    }

    @Override
    public void distribuirGet() {
        String reporte = request.getParameter("reporte");
        switch (reporte) {
            case "reporteGananciasPorRevista":
                this.reporteGananciasPorRevista();
                break;
            case "reporteGananciasPorAnuncio":
                this.reporteGananciasPorAnuncio();
                break;
            case "reporteGananciasTotales":
                this.reporteGananciasTotales();
                break;
            case "reporteRevistasPopulares":
                this.reporteRevistasPopulares();
                break;
            case "reporteRevistasMasComentadas":
                this.reporteRevistasMasComentadas();
                break;
        }
    }

    private void reporteGananciasPorRevista() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        final String nombreReporteJasper = "ReporteGanaciasPorRevista.jasper";

        List<ReporteGananciaRevistaBean> resources = new ReporteGananciaRevistaDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteGananciaRevistaBean> generador = new GenerarReporte<>(this.response, false);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteGananciasPorRevista" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteGananciasPorAnuncio() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        final String nombreReporteJasper = "ReporteGananciaPorAnuncio.jasper";

        List<ReporteGananciaAnuncioBean> resources = new ReporteGananciaAnuncioDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteGananciaAnuncioBean> generador = new GenerarReporte<>(this.response, false);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteGananciasPorAnuncio" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteGananciasTotales() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        final String nombreReporteJasper = "ReporteGananciaTotal.jasper";

        List<ReporteGananciaTotalBean> resources = new ReporteGananciaTotalDATOS().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteGananciaTotalBean> generador = new GenerarReporte<>(this.response, false);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteGananciasTotales" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteRevistasPopulares() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        final String nombreReporteJasper = "ReporteRevistasPopulares.jasper";

        List<ReportePopularesBean> resources = new ReportePopularesDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReportePopularesBean> generador = new GenerarReporte<>(this.response, false);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteRevistasPopulares" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteRevistasMasComentadas() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        final String nombreReporteJasper = "ReporteMasComentadas.jasper";

        List<ReporteMasComentadaBean> resources = new ReporteMasComentadasDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteMasComentadaBean> generador = new GenerarReporte<>(this.response, false);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteRevistasMasComentadas" + fechaInicial + "A" + fechaFinal);
    }
}

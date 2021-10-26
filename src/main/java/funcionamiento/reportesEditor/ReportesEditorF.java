package funcionamiento.reportesEditor;

import beans.reportes.editor.ReporteComentariosBean;
import beans.reportes.editor.ReporteGananciaTotalBean;
import beans.reportes.editor.ReporteRevistaMasGustadaBean;
import beans.reportes.editor.ReporteSuscripcionesBean;
import com.google.gson.Gson;
import dao.reportes.editor.ReporteComentariosDAO;
import dao.reportes.editor.ReporteGananciaTotalDAO;
import dao.reportes.editor.ReporteRevistasMasGustadaDAO;
import dao.reportes.editor.ReporteSuscripcionesDAO;
import funcionamiento.Funcionamiento;
import funcionamiento.reportes.GenerarReporte;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportesEditorF extends Funcionamiento {

    public ReportesEditorF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {

    }

    @Override
    public void distribuirGet() {
        String reporte = request.getParameter("reporte");
        try {
            switch (reporte) {
                case "reporteComentarios":
                    this.reporteComentarios();
                    break;
                case "reporteSuscripciones":
                    this.reporteSuscripciones();
                    break;
                case "reporteRevistasMasGustadas":
                    this.reporteRevistasMasGustadas();
                    break;
                case "reporteGanancias":
                    this.reporteGanancias();
                    break;
                case "reporteComentariosNG":
                    this.reporteComentariosNG();
                    break;
                case "reporteSuscripcionesNG":
                    this.reporteSuscripcionesNG();
                    break;
                case "reporteRevistasMasGustadasNG":
                    this.reporteRevistasMasGustadasNG();
                    break;
                case "reporteGananciasNG":
                    this.reporteGananciasNG();
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void reporteComentariosNG() throws IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        List<ReporteComentariosBean> resources = new ReporteComentariosDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        String json = new Gson().toJson(resources);
        this.response.getWriter().append(json);
    }

    private void reporteSuscripcionesNG() throws IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        List<ReporteSuscripcionesBean> resources = new ReporteSuscripcionesDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        String json = new Gson().toJson(resources);
        this.response.getWriter().append(json);
    }

    private void reporteRevistasMasGustadasNG() throws IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        List<ReporteRevistaMasGustadaBean> resources = new ReporteRevistasMasGustadaDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        String json = new Gson().toJson(resources);
        this.response.getWriter().append(json);
    }

    private void reporteGananciasNG() throws IOException {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        List<ReporteGananciaTotalBean> resources = new ReporteGananciaTotalDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        String json = new Gson().toJson(resources);
        this.response.getWriter().append(json);
    }

    private void reporteComentarios() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        final String nombreReporteJasper = "ReporteComentariosEditor.jasper";
        List<ReporteComentariosBean> resources = new ReporteComentariosDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteComentariosBean> generador = new GenerarReporte<>(this.response, true);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteComentarios" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteSuscripciones() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        final String nombreReporteJasper = "ReporteSuscripciones.jasper";
        List<ReporteSuscripcionesBean> resources = new ReporteSuscripcionesDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteSuscripcionesBean> generador = new GenerarReporte<>(this.response, true);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteSuscripciones" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteRevistasMasGustadas() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        final String nombreReporteJasper = "ReporteRevistasMasGustadas.jasper";

        List<ReporteRevistaMasGustadaBean> resources = new ReporteRevistasMasGustadaDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteRevistaMasGustadaBean> generador = new GenerarReporte<>(this.response, true);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteRevistasMasGustadas" + fechaInicial + "A" + fechaFinal);
    }

    private void reporteGanancias() {
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        final String nombreReporteJasper = "ReporteGanancias.jasper";

        List<ReporteGananciaTotalBean> resources = new ReporteGananciaTotalDAO().obtenerDatosReporte(fechaInicial, fechaFinal);
        GenerarReporte<ReporteGananciaTotalBean> generador = new GenerarReporte<>(this.response, true);
        generador.generarReporte(nombreReporteJasper, resources, "ReporteGanancias" + fechaInicial + "A" + fechaFinal);
    }

}

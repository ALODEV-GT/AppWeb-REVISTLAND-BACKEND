package funcionamiento.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GenerarReporte<T> {

    private final HttpServletResponse response;
    private final boolean esEditor;

    public GenerarReporte(HttpServletResponse response, boolean esEditor) {
        this.response = response;
        this.esEditor = esEditor;
    }

    public void generarReporte(String nombreReporteJasper, List<T> resources, String nombreReporte) {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + nombreReporte + ".pdf");

        String basePath;
        if (esEditor) {
            basePath = "reportes/editor/";
        } else {
            basePath = "reportes/administrador/";
        }

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream(basePath + nombreReporteJasper);

        JRDataSource source = new JRBeanCollectionDataSource(resources);

        JasperPrint printer;
        try {
            printer = JasperFillManager.fillReport(compiledReport, null, source);
            JasperExportManager.exportReportToPdfStream(printer, response.getOutputStream());
        } catch (JRException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}

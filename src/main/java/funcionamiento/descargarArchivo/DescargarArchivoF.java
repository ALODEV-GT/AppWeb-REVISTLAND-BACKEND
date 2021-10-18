package funcionamiento.descargarArchivo;

import dao.publicar.ArchivoDAO;
import funcionamiento.Funcionamiento;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.archivo.ArchivoM;

public class DescargarArchivoF extends Funcionamiento {

    public DescargarArchivoF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {

    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");
        try {
            switch (accion) {
                case "descargarArchivo":
                    this.descargarArchivo();
                    break;
                default:
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void descargarArchivo() throws IOException {
        int idArchivo = Integer.valueOf(request.getParameter("idArchivo"));
        ArchivoM archivo = new ArchivoDAO().obtenerArchivo(idArchivo);
        response.setContentType("application/pdf");
       // response.setHeader("Content-Disposition", "attachment;filename=" + archivo.getNombreArchivo());
        BufferedInputStream fileStream = new BufferedInputStream(archivo.getContenido());
        int data = fileStream.read();
        while (data > -1) {
            response.getOutputStream().write(data);
            data = fileStream.read();
        }
    }

}

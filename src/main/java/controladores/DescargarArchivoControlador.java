package controladores;

import funcionamiento.descargarArchivo.DescargarArchivoF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorDescargarArchivo", urlPatterns = {"/controladorDescargarArchivo"})
public class DescargarArchivoControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DescargarArchivoF funcionamiento = new DescargarArchivoF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DescargarArchivoF funcionamiento = new DescargarArchivoF(request, response);
        funcionamiento.distribuirPost();
    }

}

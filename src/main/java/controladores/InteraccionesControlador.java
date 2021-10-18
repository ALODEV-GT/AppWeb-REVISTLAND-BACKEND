package controladores;

import funcionamiento.interacciones.InteraccionesF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorInteracciones", urlPatterns = {"/controladorInteracciones"})
public class InteraccionesControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InteraccionesF funcionamiento = new InteraccionesF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InteraccionesF funcionamiento = new InteraccionesF(request, response);
        funcionamiento.distribuirPost();
    }

}

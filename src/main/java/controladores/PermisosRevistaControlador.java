package controladores;

import funcionamiento.permisosRevista.PermisosRevistaF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorPermisosRevista", urlPatterns = {"/controladorPermisosRevista"})
public class PermisosRevistaControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PermisosRevistaF funcionamiento = new PermisosRevistaF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PermisosRevistaF funcionamiento = new PermisosRevistaF(request, response);
        funcionamiento.distribuirPost();
    }

}

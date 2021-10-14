package controladores;

import funcionamiento.descripcionRevista.DescripcionRevistaF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorDescripcionRevista", urlPatterns = {"/controladorDescripcionRevista"})
public class DescripcionRevistaControl extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DescripcionRevistaF funcionamiento = new DescripcionRevistaF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DescripcionRevistaF funcionamiento = new DescripcionRevistaF(request, response);
        funcionamiento.distribuirPost();
    }

}

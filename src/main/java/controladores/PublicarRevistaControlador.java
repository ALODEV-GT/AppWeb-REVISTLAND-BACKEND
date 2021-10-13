package controladores;

import funcionamiento.publicarRevista.PublicarRevistaF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorPublicarRevista", urlPatterns = {"/controladorPublicarRevista"})
@MultipartConfig
public class PublicarRevistaControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicarRevistaF funcionamiento = new PublicarRevistaF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicarRevistaF funcionamiento = new PublicarRevistaF(request, response);
        funcionamiento.distribuirPost();
    }
}

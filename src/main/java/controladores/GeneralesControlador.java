package controladores;

import funcionamiento.generales.GeneralesF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorGenerales", urlPatterns = {"/controladorGenerales"})
public class GeneralesControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GeneralesF funcionamiento = new GeneralesF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GeneralesF funcionamiento = new GeneralesF(request, response);
        funcionamiento.distribuirPost();
    }

}

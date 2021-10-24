package controladores;

import funcionamiento.fechaGlobal.FechaGlobalF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorFechaGlobal", urlPatterns = {"/controladorFechaGlobal"})
public class FechaGlobalControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FechaGlobalF funcionamiento = new FechaGlobalF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FechaGlobalF funcionamiento = new FechaGlobalF(request, response);
        funcionamiento.distribuirPost();
    }

}

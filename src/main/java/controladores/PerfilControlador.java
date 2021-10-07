package controladores;

import funcionamiento.perfil.PerfilF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorPerfil", urlPatterns = {"/controladorPerfil"})
public class PerfilControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PerfilF funcionamiento = new PerfilF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PerfilF funcionamiento = new PerfilF(request, response);
        funcionamiento.distribuirPost();
    }

}

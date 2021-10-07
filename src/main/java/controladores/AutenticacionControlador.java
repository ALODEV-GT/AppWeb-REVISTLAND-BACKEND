package controladores;

import funcionamiento.autenticacion.UsuarioF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorAutenticacion", urlPatterns = {"/controladorAutenticacion"})
public class AutenticacionControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioF funcionamiento = new UsuarioF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            UsuarioF funcionamiento = new UsuarioF(request, response);
            funcionamiento.distribuirPost();
    }

}

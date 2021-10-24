package controladores;

import funcionamiento.anuncios.AnunciosF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorAnuncios", urlPatterns = {"/controladorAnuncios"})
public class AnunciosControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnunciosF funcionamiento = new AnunciosF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnunciosF funcionamiento = new AnunciosF(request, response);
        funcionamiento.distribuirPost();
    }

}

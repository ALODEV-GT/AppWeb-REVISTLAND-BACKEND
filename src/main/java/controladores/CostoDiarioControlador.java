package controladores;

import funcionamiento.costoDiario.CostoDiarioF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorCostoDiario", urlPatterns = {"/controladorCostoDiario"})
public class CostoDiarioControlador extends Controlador {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CostoDiarioF funcionamiento = new CostoDiarioF(request, response);
        funcionamiento.distribuirGet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CostoDiarioF funcionamiento = new CostoDiarioF(request, response);
        funcionamiento.distribuirPost();
    }

}

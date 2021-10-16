package funcionamiento.generales;

import dao.generales.PorcentajeGananciaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeneralesF extends Funcionamiento {

    public GeneralesF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {

    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");
        try {
            switch (accion) {
                case "obtenerPorcentajeGanancia":
                    this.obtenerPorcentajeGanancia();
                    break;
                case "cambiarPorcentaje":
                    this.cambiarPorcentaje();
                    break;
                default:
                    System.out.println("NO EXISTE ESTA OPCION GET l");
            }
        } catch (IOException ex) {
            Logger.getLogger(GeneralesF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void obtenerPorcentajeGanancia() throws IOException {
        double porcentaje = new PorcentajeGananciaDAO().obtenerPorcentajeGanancia();
        String json = "{\"porcentaje\":\"" + porcentaje + "\"}";
        response.getWriter().append(json);
    }

    private void cambiarPorcentaje() {
        double porcentaje = Double.valueOf(request.getParameter("porcentaje"));
        new PorcentajeGananciaDAO().cambiarPorcentajeGanancia(porcentaje);
    }

}

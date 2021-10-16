package funcionamiento.suscripcion;

import com.google.gson.Gson;
import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.suscripcion.SuscripcionDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.revistasConsumidor.MiRevistaConsumidorM;
import modelo.suscripcion.SuscripcionM;

public class SuscripcionF extends Funcionamiento {

    public SuscripcionF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "registrarSuscripcion":
                    this.registrarSuscripcion();
                    break;
                default:
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void registrarSuscripcion() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        SuscripcionM suscripcion = new ConversorJson<SuscripcionM>().convertirJsonAObject(json, SuscripcionM.class);
        new CalculoPago().calcularPago(suscripcion);
        new SuscripcionDAO().registrarSuscripcion(suscripcion);
    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "obtenerSuscripcionesUsuario":
                    this.obtenerSuscripcionesUsuario();
                    break;
                default:

            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    private void obtenerSuscripcionesUsuario() throws IOException {
        String nombre = request.getParameter("nombre");
        List<MiRevistaConsumidorM> misRevistas = new SuscripcionDAO().listarRevistasSuscritas(nombre);
        String json = new Gson().toJson(misRevistas);
        response.getWriter().append(json);
    }
}

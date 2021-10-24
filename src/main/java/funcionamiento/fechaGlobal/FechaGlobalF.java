package funcionamiento.fechaGlobal;

import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.fechaGlobal.FechaGlobalDAO;
import funcionamiento.Funcionamiento;
import funcionamiento.costoDiario.DetalleCostoPorDia;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.fechaGlobal.FechaM;

public class FechaGlobalF extends Funcionamiento {

    public FechaGlobalF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "cambiarFecha":
                    this.cambiarFecha();
                    break;
                default:
                    System.out.println("No existe esta opcion");
            }
        } catch (IOException ex) {
            Logger.getLogger(FechaGlobalF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cambiarFecha() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        FechaM fecha = new ConversorJson<FechaM>().convertirJsonAObject(json, FechaM.class);

        //Actualizacion de costos por dia de las revistas
        DetalleCostoPorDia actualizarCostosRevistas = new DetalleCostoPorDia(LocalDate.parse(fecha.getFecha()));
        actualizarCostosRevistas.agregarRegistrosDetallePorDia();

        new FechaGlobalDAO().cambiarFecha(fecha);
    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "obtenerFecha":
                    this.obtenerFecha();
            }
        } catch (IOException ex) {
            Logger.getLogger(FechaGlobalF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtenerFecha() throws IOException {
        FechaM fecha = new FechaGlobalDAO().obtenerFecha();
        String json = new ConversorJson<FechaM>().convertirObjectAJson(fecha, FechaM.class);
        response.getWriter().append(json);
    }

}

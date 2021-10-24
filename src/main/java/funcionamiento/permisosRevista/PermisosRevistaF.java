package funcionamiento.permisosRevista;

import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.permisosRevista.PermisosRevistaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.permisosRevista.PermitirM;

public class PermisosRevistaF extends Funcionamiento {

    public PermisosRevistaF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        try {
            String accion = this.request.getParameter("accion");
            switch (accion) {
                case "cambiarValorPermitirSuscripciones":
                    this.cambiarValorPermitirSuscripciones();
                    break;
                case "cambiarValorEsInteractiva":
                    this.cambiarValorEsInteractiva();
                    break;
                default:
                    System.out.println("no existe esta opcion");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void cambiarValorPermitirSuscripciones() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        PermitirM permiso = new ConversorJson<PermitirM>().convertirJsonAObject(json, PermitirM.class);
        new PermisosRevistaDAO().cambiarValorSePuedeSuscribir(permiso);
    }

    private void cambiarValorEsInteractiva() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        PermitirM permiso = new ConversorJson<PermitirM>().convertirJsonAObject(json, PermitirM.class);
        new PermisosRevistaDAO().cambiaValorEsInteractiva(permiso);
    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "permitirSuscripciones":
                    this.permitirSuscripciones();
                    break;
                case "esInteractiva":
                    this.esInteractiva();
                    break;
                default:
                    System.out.println("no existe esta opcion");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void permitirSuscripciones() throws IOException {
        int idPublicacion = Integer.valueOf(request.getParameter("idPublicacion"));
        PermitirM permiso = new PermisosRevistaDAO().sePuedeSuscribir(idPublicacion);
        String json = new ConversorJson<PermitirM>().convertirObjectAJson(permiso, PermitirM.class);
        response.getWriter().append(json);
    }

    public void esInteractiva() throws IOException {
        int idPublicacion = Integer.valueOf(request.getParameter("idPublicacion"));
        PermitirM permiso = new PermisosRevistaDAO().esInteractiva(idPublicacion);
        String json = new ConversorJson<PermitirM>().convertirObjectAJson(permiso, PermitirM.class);
        response.getWriter().append(json);
    }

}

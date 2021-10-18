package funcionamiento.interacciones;

import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.interacciones.ComentarioDAO;
import dao.interacciones.MeGustaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.interacciones.ComentarioM;
import modelo.interacciones.MeGustaM;

public class InteraccionesF extends Funcionamiento {

    public InteraccionesF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "comentar":
                    this.comentar();
                    break;
                case "darMeGusta":
                    this.darMeGusta();
                default:
                    System.out.println("Esta opcion no existe");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void darMeGusta() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        MeGustaM meGusta = new ConversorJson<MeGustaM>().convertirJsonAObject(json, MeGustaM.class);
        MeGustaDAO meGustaDao = new MeGustaDAO();
        if (meGustaDao.existe(meGusta)) {
            meGustaDao.eliminarMeGusta(meGusta);
        } else {
            meGustaDao.insertarMeGusta(meGusta);
        }
    }

    private void comentar() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        ComentarioM comentario = new ConversorJson<ComentarioM>().convertirJsonAObject(json, ComentarioM.class);
        new ComentarioDAO().insertarComentario(comentario);
    }

    @Override
    public void distribuirGet() {

    }

}

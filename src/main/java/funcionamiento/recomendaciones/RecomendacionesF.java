package funcionamiento.recomendaciones;

import funcionamiento.Funcionamiento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecomendacionesF extends Funcionamiento {

    public RecomendacionesF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                case "obtenerRecomendaciones":
                    this.obtenerRecomendaciones();
                    break;
                default:
                    System.out.println("NO EXSITE ESTA OPCION GET Recomendaciones Foca");
            }
        } catch (IOException ex) {
            System.err.println("OCURRIO UN ERROR EN POST USUARIO");
            ex.printStackTrace(System.out);
        }
    }

    public void obtenerRecomendaciones() throws IOException {
        String nombre = request.getParameter("nombre");
        ListarRecomendaciones recomendaciones = new ListarRecomendaciones(nombre);
        String json = recomendaciones.obtenerListaEnJson();
        response.getWriter().append(json);
    }

}

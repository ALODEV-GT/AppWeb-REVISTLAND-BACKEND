package funcionamiento.perfil;

import comunes.FormatearJson;
import comunes.LeerRequest;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PerfilF extends Funcionamiento{

    public PerfilF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");
        try {
            switch (accion) {
                case "crearPerfil":
                    crearPerfil();
                    break;
                default:
                    System.out.println("NO EXISTE ESTA OPCION POST");
            }
        } catch (IOException ex) {
            System.err.println("OCURRIO UN ERROR EN POST PERFIL");
            ex.printStackTrace(System.out);
        }
    }
    
    private void crearPerfil() throws IOException{
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
    }

    @Override
    public void distribuirGet() {

    }
    
    
}

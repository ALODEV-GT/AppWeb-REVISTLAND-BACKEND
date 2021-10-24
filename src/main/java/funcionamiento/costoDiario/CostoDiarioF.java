package funcionamiento.costoDiario;

import com.google.gson.Gson;
import dao.publicar.costoPorDia.CostoPorDiaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.costoDiario.RevistaCostoM;

public class CostoDiarioF extends Funcionamiento {
    
    public CostoDiarioF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }
    
    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");
        
    }
    
    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");
        
        try {
            switch (accion) {
                case "obtenerRevistasConCosto":
                    this.obtenerRevistasConCosto();
                    break;
                case "cambiarCostoDiario":
                    this.cambiarCostoDiario();
                default:
                    System.out.println("Esta opcion no existe");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private void cambiarCostoDiario() {
        double nuevoCosto = Double.valueOf(request.getParameter("nuevoCosto"));
        int idCostoDiario = Integer.valueOf(request.getParameter("idCostoDiario"));
        new CostoPorDiaDAO().cambiarCostoPorDiaRevista(idCostoDiario, nuevoCosto);
    }
    
    private void obtenerRevistasConCosto() throws IOException {
        List<RevistaCostoM> lista = new CostoPorDiaDAO().listarRevistasConCostos();
        String json = new Gson().toJson(lista);
        response.getWriter().append(json);
    }
    
}

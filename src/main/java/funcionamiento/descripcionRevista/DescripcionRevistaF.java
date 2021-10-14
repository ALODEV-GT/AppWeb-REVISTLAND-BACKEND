package funcionamiento.descripcionRevista;

import comunes.Conversor;
import comunes.ConversorJson;
import dao.descripcionRevista.DescripcionRevistaDAO;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DescripcionRevista.DescripcionRevistaM;

public class DescripcionRevistaF extends Funcionamiento {

    public DescripcionRevistaF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                case "obtenerDescripcionRevista":
                    this.obtenerDescripcionRevista();
                    break;
                default:
                    System.out.println("NO EXSITE ESTA OPCION GET Descripcion revista");
            }
        } catch (IOException ex) {
            System.err.println("OCURRIO UN ERROR EN GET descripcion revista");
            ex.printStackTrace(System.out);
        }

    }

    public void obtenerDescripcionRevista() throws IOException {
        int idRevista = Integer.valueOf(request.getParameter("idRevista"));
        DescripcionRevistaM descripcionRevista = new DescripcionRevistaDAO().encontrar(idRevista);
        String[] etiquetas = Conversor.ListOfArray(new EtiquetaRevistaDAO().obtenerEtiquetasRevista(idRevista));
        descripcionRevista.setEtiquetas(etiquetas);
        String[] categorias = Conversor.ListOfArray(new CategoriaRevistaDAO().obtenerCetegoriasRevista(idRevista));
        descripcionRevista.setCategorias(categorias);
        String json = new ConversorJson<DescripcionRevistaM>().convertirObjectAJson(descripcionRevista, DescripcionRevistaM.class);
        response.getWriter().append(json);
    }

}

package funcionamiento.descripcionRevista;

import comunes.Conversor;
import comunes.ConversorJson;
import dao.descripcionRevista.DescripcionRevistaDAO;
import dao.interacciones.ComentarioDAO;
import dao.interacciones.MeGustaDAO;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import dao.publicar.PublicacionDAO;
import dao.publicar.RevistaDAO;
import dao.publicar.VolumenDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DescripcionRevista.DescripcionRevistaM;
import modelo.VolumenesRevistaConsumidor.RevistaVolumenM;
import modelo.interacciones.ComentarioM;
import modelo.publicarRevista.VolumenM;

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
                case "obtenerVolumenesRevista":
                    this.obtenerVolumenesRevista();
                default:
                    System.out.println("NO EXSITE ESTA OPCION GET Descripcion revista");
            }
        } catch (IOException ex) {
            System.err.println("OCURRIO UN ERROR EN GET descripcion revista");
            ex.printStackTrace(System.out);
        }
    }

    public void obtenerVolumenesRevista() throws IOException {
        System.out.println("SI ENTRE");
        int idRevista = Integer.valueOf(request.getParameter("idRevista"));
        String nombreRevista = new RevistaDAO().obtenerNombreRevista(idRevista);
        List<VolumenM> volumenes = new VolumenDAO().obtenerVolumenesRevista(idRevista);
        int idPublicacion = new PublicacionDAO().obtenerIdPublicacion(idRevista);
        int numMeGusta = new MeGustaDAO().obtenerNumeroMeGusta(idPublicacion);
        ComentarioDAO comentarioDao = new ComentarioDAO();
        int numComentarios = comentarioDao.obtenerNumeroComentarios(idPublicacion);
        List<ComentarioM> comentarios = comentarioDao.obtenerComentarios(idPublicacion);
        RevistaVolumenM revista = new RevistaVolumenM(nombreRevista, Conversor.ListToArrayVolumenM(volumenes), numComentarios, numMeGusta, Conversor.ListToArrayComentarioM(comentarios));
        String json = new ConversorJson<RevistaVolumenM>().convertirObjectAJson(revista, RevistaVolumenM.class);
        System.out.println(json);
        response.getWriter().append(json);
    }

    public void obtenerDescripcionRevista() throws IOException {
        int idRevista = Integer.valueOf(request.getParameter("idRevista"));
        DescripcionRevistaM descripcionRevista = new DescripcionRevistaDAO().encontrar(idRevista);
        String[] etiquetas = Conversor.ListToArray(new EtiquetaRevistaDAO().obtenerEtiquetasRevista(idRevista));
        descripcionRevista.setEtiquetas(etiquetas);
        String[] categorias = Conversor.ListToArray(new CategoriaRevistaDAO().obtenerCetegoriasRevista(idRevista));
        descripcionRevista.setCategorias(categorias);
        String json = new ConversorJson<DescripcionRevistaM>().convertirObjectAJson(descripcionRevista, DescripcionRevistaM.class);
        response.getWriter().append(json);
    }

}

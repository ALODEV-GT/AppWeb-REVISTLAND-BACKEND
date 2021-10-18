package funcionamiento.descripcionRevista;

import com.google.gson.Gson;
import comunes.Conversor;
import dao.interacciones.ComentarioDAO;
import dao.interacciones.MeGustaDAO;
import dao.publicar.PublicacionDAO;
import dao.publicar.RevistaDAO;
import dao.publicar.VolumenDAO;
import java.util.ArrayList;
import java.util.List;
import modelo.VolumenesRevistaConsumidor.RevistaVolumenM;
import modelo.interacciones.ComentarioM;
import modelo.publicarRevista.VolumenM;

public class ListarRevistasEditor {

    public String obtenerJsonRevistasEditor(String editor) {
        List<Integer> idRevistas = new RevistaDAO().obtenerIdRevistasEditor(editor);
        List<RevistaVolumenM> revistas = new ArrayList<>();

        for (Integer id : idRevistas) {
            String nombreRevista = new RevistaDAO().obtenerNombreRevista(id);
            List<VolumenM> volumenes = new VolumenDAO().obtenerVolumenesRevista(id);
            int idPublicacion = new PublicacionDAO().obtenerIdPublicacion(id);
            int numMeGusta = new MeGustaDAO().obtenerNumeroMeGusta(idPublicacion);
            ComentarioDAO comentarioDao = new ComentarioDAO();
            int numComentarios = comentarioDao.obtenerNumeroComentarios(idPublicacion);
            List<ComentarioM> comentarios = comentarioDao.obtenerComentarios(idPublicacion);
            RevistaVolumenM revista = new RevistaVolumenM(nombreRevista, Conversor.ListToArrayVolumenM(volumenes), numComentarios, numMeGusta, Conversor.ListToArrayComentarioM(comentarios), idPublicacion, id);
            revistas.add(revista);
        }

        String json = new Gson().toJson(revistas);
        return json;
    }
}

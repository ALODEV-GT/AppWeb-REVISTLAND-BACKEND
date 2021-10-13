package funcionamiento.recomendaciones;

import com.google.gson.Gson;
import dao.interacciones.ComentarioDAO;
import dao.interacciones.MeGustaDAO;
import dao.perfil.PreferenciasCategoriaDAO;
import dao.perfil.PreferenciasEtiquetaDAO;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import dao.publicar.PublicacionDAO;
import dao.recomendacion.RecomendacionDAO;
import dao.suscripcion.SuscripcionDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.recomendacion.RecomendacionM;

public class ListarRecomendaciones {

    private final String nombreUsuario;

    public ListarRecomendaciones(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String obtenerListaEnJson() {
        Set<Integer> IdCoincidencias = new HashSet<>();

        PreferenciasCategoriaDAO daoPC = new PreferenciasCategoriaDAO();
        PreferenciasEtiquetaDAO daoPE = new PreferenciasEtiquetaDAO();
        List<String> categorias = daoPC.obtenerCategoriasUsuario(nombreUsuario);
        List<String> etiquetas = daoPE.obtenerEtiquetasUsuario(nombreUsuario);

        for (String categoria : categorias) {
            List<Integer> idRevistas = new CategoriaRevistaDAO().listarIdRevistas(categoria);
            for (Integer id : idRevistas) {
                IdCoincidencias.add(id);
            }
        }

        for (String etiqueta : etiquetas) {
            List<Integer> idRevistas = new EtiquetaRevistaDAO().listarIdRevistas(etiqueta);
            for (Integer id : idRevistas) {
                IdCoincidencias.add(id);
            }
        }

        List<Integer> idRevistasSuscritas = new SuscripcionDAO().listarIdRevistasSuscritas(nombreUsuario);
        this.quitarIdRevistasSuscritas(IdCoincidencias, idRevistasSuscritas);

        return this.obtenerRecomendaciones(IdCoincidencias);
    }

    private void quitarIdRevistasSuscritas(Set<Integer> idCoincidencias, List<Integer> idRevistasSuscritas) {
        for (Integer i : idRevistasSuscritas) {
            idCoincidencias.remove(i);
        }
    }

    private String obtenerRecomendaciones(Set<Integer> idCoincidencias) {
        List<RecomendacionM> recomendaciones = new ArrayList<>();

        RecomendacionM recomendacion = null;
        RecomendacionDAO daoR = new RecomendacionDAO();
        PublicacionDAO daoP = new PublicacionDAO();
        ComentarioDAO daoC = new ComentarioDAO();
        MeGustaDAO daoMG = new MeGustaDAO();
        
        for (Integer id : idCoincidencias) {
            recomendacion = daoR.encontrar(id);
            int idPublicacion = daoP.obtenerIdPublicacion(id);
            int numComentarios = daoC.obtenerNumeroComentarios(idPublicacion);
            int numMeGustas  = daoMG.obtenerNumeroMeGusta(idPublicacion);
            recomendacion.setNumComentarios(numComentarios);
            recomendacion.setNumLike(numMeGustas);
            recomendaciones.add(recomendacion);
        }
        
        String json = new Gson().toJson(recomendaciones);

        return json;
    }

}

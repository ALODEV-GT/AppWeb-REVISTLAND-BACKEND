package funcionamiento.anuncios;

import com.google.gson.Gson;
import dao.anuncio.AnuncioDAO;
import dao.anuncio.CategoriaAnuncioDAO;
import dao.anuncio.EtiquetaAnuncioDAO;
import dao.interacciones.ComentarioDAO;
import dao.interacciones.MeGustaDAO;
import dao.perfil.PreferenciasCategoriaDAO;
import dao.perfil.PreferenciasEtiquetaDAO;
import dao.permisosRevista.PermisosRevistaDAO;
import dao.publicar.PublicacionDAO;
import dao.recomendacion.RecomendacionDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.Anuncio.AnuncioM;
import modelo.Anuncio.AnuncioMostrarM;

public class ListarRecomendacionesAnuncios {

    private final String nombreUsuario;

    public ListarRecomendacionesAnuncios(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String obtenerListaEnJson() {
        Set<Integer> IdCoincidencias = new HashSet<>();

        PreferenciasCategoriaDAO daoPC = new PreferenciasCategoriaDAO();
        PreferenciasEtiquetaDAO daoPE = new PreferenciasEtiquetaDAO();
        List<String> categorias = daoPC.obtenerCategoriasUsuario(nombreUsuario);
        List<String> etiquetas = daoPE.obtenerEtiquetasUsuario(nombreUsuario);

        for (String categoria : categorias) {
            List<Integer> idRevistas = new CategoriaAnuncioDAO().listarIdAnuncios(categoria);
            for (Integer id : idRevistas) {
                IdCoincidencias.add(id);
            }
        }

        for (String etiqueta : etiquetas) {
            List<Integer> idRevistas = new EtiquetaAnuncioDAO().listarIdAnuncios(etiqueta);
            for (Integer id : idRevistas) {
                IdCoincidencias.add(id);
            }
        }

        return this.obtenerRecomendaciones(IdCoincidencias);
    }

    private String obtenerRecomendaciones(Set<Integer> idCoincidencias) {
        List<AnuncioMostrarM> recomendaciones = new ArrayList<>();

        AnuncioM anuncio = null;
        AnuncioMostrarM anunciosMostrar = null;
        RecomendacionDAO daoR = new RecomendacionDAO();
        PublicacionDAO daoP = new PublicacionDAO();
        ComentarioDAO daoC = new ComentarioDAO();
        MeGustaDAO daoMG = new MeGustaDAO();
        PermisosRevistaDAO daoPermisoRevista = new PermisosRevistaDAO();

        for (Integer id : idCoincidencias) {
            anuncio = new AnuncioDAO().encontrarModeloParaMostrarAnuncio(id);
            anunciosMostrar = new AnuncioMostrarM(id, anuncio.getIdTipoAnuncio());
            switch (anuncio.getIdTipoAnuncio()) {
                case 1:
                    anunciosMostrar.setTextoAnuncio(new AnuncioDAO().encontrarTextoAnuncio(id).getTextoAnuncio());
                    break;
                case 2:
                    anunciosMostrar.setImagen(new AnuncioDAO().encontrarImagenAnuncio(id).getContenido());
                    break;
                case 3:
                    anunciosMostrar.setLinkVideo(new AnuncioDAO().encontrarVideoAnuncio(id).getTextoAnuncio());
                    break;
                default:
                    System.out.println("No existe");
            }
            recomendaciones.add(anunciosMostrar);
        }

        String json = new Gson().toJson(recomendaciones);
        return json;
    }

}

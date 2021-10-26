package funcionamiento.anuncios;

import com.google.gson.Gson;
import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.anuncio.AnuncioDAO;
import dao.anuncio.CategoriaAnuncioDAO;
import dao.anuncio.EmpresaAnuncianteDAO;
import dao.anuncio.EtiquetaAnuncioDAO;
import dao.anuncio.PagoAnuncioDAO;
import dao.anuncio.TipoAnuncioDAO;
import dao.perfil.CategoriaDAO;
import dao.perfil.EtiquetaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Anuncio.AnuncioM;
import modelo.Anuncio.PagoAnuncioM;
import modelo.Anuncio.Precio;
import modelo.Anuncio.TextoAnuncioM;
import modelo.archivo.ArchivoM;
import modelo.perfil.FotoM;

public class AnunciosF extends Funcionamiento {

    public AnunciosF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "agregarTextoAnuncio":
                    this.agregarTextoAnuncio();
                    break;
                case "agregarVideoAnuncio":
                    this.agregarVideoAnuncio();
                    break;
                case "agregarAnuncio":
                    this.agregarAnuncio();
                    break;
                case "agregarImagenAnuncio":
                    this.agregarImagenAnuncio();
                    break;
                default:
                    System.out.println("No existe esta opcion");
            }
        } catch (IOException ex) {

        }
    }

    private void agregarImagenAnuncio() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        FotoM imagen = new ConversorJson<FotoM>().convertirJsonAObject(json, FotoM.class);
        new AnuncioDAO().insertarImagenAnuncio(imagen);
        int idImagen = new AnuncioDAO().obtenerIdUltimaImagenInsertada();
        response.getWriter().append("{\"id\":\"" + idImagen + "\"}");
    }

    private void agregarVideoAnuncio() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        TextoAnuncioM textoAnuncio = new ConversorJson<TextoAnuncioM>().convertirJsonAObject(json, TextoAnuncioM.class);
        new AnuncioDAO().insertarLinkVideoAnuncio(textoAnuncio.getTextoAnuncio());
        int idLink = new AnuncioDAO().obtenerIdUltimoLinkInsertado();
        response.getWriter().append("{\"id\":\"" + idLink + "\"}");
    }

    private void agregarAnuncio() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        AnuncioM anuncio = new ConversorJson<AnuncioM>().convertirJsonAObject(json, AnuncioM.class);
        int idEmpresaAnunciante = 0;
        String nombreAnunciante = anuncio.getNombreAnunciante();
        EmpresaAnuncianteDAO daoEA = new EmpresaAnuncianteDAO();
        if (daoEA.existe(nombreAnunciante)) {
            idEmpresaAnunciante = daoEA.obtenerIdAnunciante(nombreAnunciante);
        } else {
            daoEA.insertar(nombreAnunciante);
            idEmpresaAnunciante = daoEA.obtenerIdUltimoInsertado();
        }

        AnuncioDAO daoAnuncio = new AnuncioDAO();
        daoAnuncio.insertarAnuncio(anuncio);
        int idAnuncio = daoAnuncio.obtenerIdUltimoAnuncioInsertado();

        double precioAnuncio = new TipoAnuncioDAO().obtenerPrecioTipoAnuncio(anuncio.getIdTipoAnuncio()).getPrecio();
        double costoTotal = precioAnuncio * anuncio.getCantidadDias();

        PagoAnuncioM pagoAnuncio = new PagoAnuncioM(idEmpresaAnunciante, anuncio.getFechaCompra(), idAnuncio, costoTotal);
        new PagoAnuncioDAO().insertar(pagoAnuncio);

        new CategoriaDAO().agregarCategorias(anuncio.getCategorias());
        new EtiquetaDAO().agregarEtiquetas(anuncio.getEtiquetas());

        new EtiquetaAnuncioDAO().agregarEtiquetasRevista(idAnuncio, anuncio.getEtiquetas());
        new CategoriaAnuncioDAO().agregarCategoriasRevista(idAnuncio, anuncio.getCategorias());
    }

    private void agregarTextoAnuncio() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        TextoAnuncioM textoAnuncio = new ConversorJson<TextoAnuncioM>().convertirJsonAObject(json, TextoAnuncioM.class);
        new AnuncioDAO().insertarTextoAnuncio(textoAnuncio.getTextoAnuncio());
        int idTexto = new AnuncioDAO().obtenerIdUltimoTextoInsertado();
        response.getWriter().append("{\"id\":\"" + idTexto + "\"}");
    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");

        try {
            switch (accion) {
                case "obtenerPrecioAnuncio":
                    this.obtenerPrecioAnuncio();
                    break;
                case "obtenerRecomendacionesAnuncios":
                    this.obtenerRecomendacionesAnuncios();
                    break;
                case "obtenerAnunciantes":
                    this.obtenerAnunciantes();
                default:
                    System.out.println("No existe esta opcion");
            }
        } catch (IOException ex) {
            Logger.getLogger(AnunciosF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void obtenerAnunciantes() throws IOException {
        List<String> categorias = new EmpresaAnuncianteDAO().obtenerEmpresasAnunciantes();
        this.response.getWriter().append(new Gson().toJson(categorias));
    }

    private void obtenerRecomendacionesAnuncios() throws IOException {
        String nombre = request.getParameter("nombre");
        ListarRecomendacionesAnuncios recomendaciones = new ListarRecomendacionesAnuncios(nombre);
        String json = recomendaciones.obtenerListaEnJson();
        response.getWriter().append(json);
    }

    private void obtenerPrecioAnuncio() throws IOException {
        int idTipoAnuncio = Integer.valueOf(request.getParameter("tipoAnuncio"));
        Precio precio = new TipoAnuncioDAO().obtenerPrecioTipoAnuncio(idTipoAnuncio);
        String json = new ConversorJson<Precio>().convertirObjectAJson(precio, Precio.class);
        response.getWriter().append(json);
    }

}

package funcionamiento.publicarRevista;

import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.perfil.CategoriaDAO;
import dao.perfil.EtiquetaDAO;
import dao.publicar.ArchivoDAO;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import dao.publicar.PublicacionDAO;
import dao.publicar.RevistaDAO;
import dao.publicar.VolumenDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.publicarRevista.PublicacionM;
import modelo.publicarRevista.RevistaM;
import modelo.publicarRevista.VolumenM;

public class PublicarRevistaF extends Funcionamiento {

    public PublicarRevistaF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super(request, response);
    }

    @Override
    public void distribuirPost() {
        String accion = this.request.getParameter("accion");
        try {
            switch (accion) {
                case "crearRevista":
                    crearRevista();
                    break;
                case "subirArchivoRevista":
                    subirArchivoRevista();
                    break;
                case "crearVolumen":
                    crearVolumen();
                    break;
                case "crearPublicacion":
                    crearPublicacion();
                    break;
                default:
                    System.out.println("NO EXSITE ESTA OPCION POST publicar revista");
            }
        } catch (IOException ex) {
            System.err.println("OCURRIO UN ERROR EN POST publicar revista");
            ex.printStackTrace(System.out);
        } catch (ServletException ex) {
            Logger.getLogger(PublicarRevistaF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearPublicacion() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        PublicacionM nuevaPublicacion = new ConversorJson<PublicacionM>().convertirJsonAObject(json, PublicacionM.class);
        new PublicacionDAO().insertar(nuevaPublicacion);
    }

    private void crearVolumen() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        VolumenM nuevaRevista = new ConversorJson<VolumenM>().convertirJsonAObject(json, VolumenM.class);
        new VolumenDAO().insertar(nuevaRevista);
    }

    private void subirArchivoRevista() throws IOException, ServletException {
        Part filePart = request.getPart("datafile");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileStream = filePart.getInputStream();
        ArchivoDAO dao = new ArchivoDAO();
        dao.insertar(fileStream, fileName);
        int idArchivo = dao.obtenerIdUltimoInsertado();
        response.getWriter().append("{\"id\":\"" + idArchivo + "\"}");
    }

    private void crearRevista() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);
        RevistaM revista = new ConversorJson<RevistaM>().convertirJsonAObject(json, RevistaM.class);
        RevistaDAO dao = new RevistaDAO();
        dao.insertar(revista);
        int idRevista = dao.obtenerIdUltimoInsertado();
        new CategoriaDAO().agregarCategorias(revista.getCategorias());
        new EtiquetaDAO().agregarEtiquetas(revista.getEtiquetas());
        new CategoriaRevistaDAO().agregarCategoriasRevista(idRevista, revista.getCategorias());
        new EtiquetaRevistaDAO().agregarEtiquetasRevista(idRevista, revista.getEtiquetas());
        response.getWriter().append("{\"id\":\"" + idRevista + "\"}");
    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");
        switch (accion) {
            case "eliminarVolumen":
                eliminarVolumen();
                break;
            default:
                System.out.println("NO EXSITE ESTA OPCION POST publicar revista");
        }
    }

    private void eliminarVolumen() {
        int idArchivo = Integer.valueOf(request.getParameter("idArchivo"));
        new VolumenDAO().eliminarVolumen(idArchivo);
        new ArchivoDAO().eliminarArchivo(idArchivo);
    }

}

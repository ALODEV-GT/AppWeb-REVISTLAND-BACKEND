package funcionamiento.perfil;

import com.google.gson.Gson;
import comunes.ConversorJson;
import comunes.FormatearJson;
import comunes.LeerRequest;
import dao.perfil.CategoriaDAO;
import dao.perfil.EtiquetaDAO;
import dao.perfil.PerfilDAO;
import dao.perfil.PreferenciasCategoriaDAO;
import dao.perfil.PreferenciasEtiquetaDAO;
import funcionamiento.Funcionamiento;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.perfil.PerfilM;

public class PerfilF extends Funcionamiento {

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

    private void crearPerfil() throws IOException {
        String contenido = LeerRequest.obtenerContenido(request.getReader());
        String json = FormatearJson.formatearJson(contenido);

        PerfilM perfil = new ConversorJson<PerfilM>().convertirJsonAObject(json, PerfilM.class);

        PerfilDAO dao = new PerfilDAO();
        if (dao.existe(perfil.getNombreUsuario())) {
            dao.actualizar(perfil);
            new CategoriaDAO().agregarCategorias(perfil.getCategorias());
            new EtiquetaDAO().agregarEtiquetas(perfil.getEtiquetas());
            
            PreferenciasCategoriaDAO daoPC=new PreferenciasCategoriaDAO();
            PreferenciasEtiquetaDAO daoPE = new PreferenciasEtiquetaDAO();
            daoPC.eliminarCategoriasUsuario(perfil.getNombreUsuario());
            daoPC.agregarCategoriasUsuario(perfil.getNombreUsuario(), perfil.getCategorias());
            daoPE.eliminarEtiquetasUsuario(perfil.getNombreUsuario());
            daoPE.agregarEtiquetasUsuario(perfil.getNombreUsuario(), perfil.getEtiquetas());
        } else {
            new PerfilDAO().insertar(perfil);
            new CategoriaDAO().agregarCategorias(perfil.getCategorias());
            new EtiquetaDAO().agregarEtiquetas(perfil.getEtiquetas());
            new PreferenciasCategoriaDAO().agregarCategoriasUsuario(perfil.getNombreUsuario(), perfil.getCategorias());
            new PreferenciasEtiquetaDAO().agregarEtiquetasUsuario(perfil.getNombreUsuario(), perfil.getEtiquetas());
        }

    }

    @Override
    public void distribuirGet() {
        String accion = this.request.getParameter("accion");
        try {
            switch (accion) {
                case "obtenerCategorias":
                    this.obtenerCategorias();
                    break;
                case "obtenerEtiquetas":
                    this.obtenerEtiquetas();
                    break;
                case "obtenerPerfil":
                    this.obtenerPerfil();
                    break;
                case "obtenerCategoriasUsuario":
                    this.obtenerCategoriasUsuario();
                    break;
                case "obtenerEtiquetasUsuario":
                    this.obtenerEtiquetasUsuario();
                    break;
                default:
                    System.out.println("NO EXISTE ESTA OPCION POST");
            }
        } catch (IOException ex) {
            System.err.println("OCURRIO UN ERROR EN POST PERFIL");
            ex.printStackTrace(System.out);
        }
    }

    private void obtenerPerfil() throws IOException {
        String nombre = request.getParameter("nombre");
        PerfilM perfil = new PerfilDAO().encontrar(nombre);
        String json = new ConversorJson<PerfilM>().convertirObjectAJson(perfil, PerfilM.class);
        response.getWriter().append(json);
    }

    private void obtenerCategorias() throws IOException {
        List<String> categorias = new CategoriaDAO().listar();
        this.response.getWriter().append(new Gson().toJson(categorias));
    }

    private void obtenerEtiquetas() throws IOException {
        List<String> etiquetas = new EtiquetaDAO().listar();
        this.response.getWriter().append(new Gson().toJson(etiquetas));
    }
    
    private void obtenerCategoriasUsuario() throws IOException {
        String nombre = request.getParameter("nombre");
        List<String> categorias = new PreferenciasCategoriaDAO().obtenerCategoriasUsuario(nombre);
        this.response.getWriter().append(new Gson().toJson(categorias));
    }

    private void obtenerEtiquetasUsuario() throws IOException {
        String nombre = request.getParameter("nombre");
        List<String> etiquetas = new PreferenciasEtiquetaDAO().obtenerEtiquetasUsuario(nombre);
        this.response.getWriter().append(new Gson().toJson(etiquetas));
    }
}

package dao.reportes.editor;

import ConexionDB.Conexion;
import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroComentario;
import beans.reportes.editor.ReporteComentariosBean;
import comunes.Conversor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteComentariosDAO {

    private static final String SQL_OBTENER_REVISTAS = "SELECT p.id_publicacion,p.nombre_usuario, r.nombre FROM publicacion p JOIN revista r ON(p.id_revista=r.id_revista) ORDER BY p.fecha_publicacion DESC";
    private static final String SQL_OBTENER_REGISTROS = "SELECT nombre_usuario, contenido, fecha_comentario FROM comentario  WHERE fecha_comentario BETWEEN ? AND ?  AND id_publicacion = ?";

    public List<ReporteComentariosBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReporteComentariosBean> lista = new ArrayList<>();
        ReporteComentariosBean tabla;
        DatosRevista datosRevista = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REVISTAS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPublicacion = rs.getInt("p.id_publicacion");
                String nombreRevista = rs.getString("r.nombre");
                String nombreEditor = rs.getString("p.nombre_usuario");
                List<RegistroComentario> registros = this.obtenerRegistrosComentariosPublicacion(fechaInicial, fechaFinal, idPublicacion);
                datosRevista = new DatosRevista(nombreRevista, nombreEditor);
                tabla = new ReporteComentariosBean(datosRevista, registros.size(), registros);
                lista.add(tabla);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }

    private List<RegistroComentario> obtenerRegistrosComentariosPublicacion(String fechaInicial, String fechaFinal, int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroComentario> lista = new ArrayList<>();
        RegistroComentario registro = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REGISTROS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setInt(3, idPublicacion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String comentario = rs.getString("contenido");
                String fechaComentario = rs.getString("fecha_comentario");
                registro = new RegistroComentario(nombreUsuario, comentario, Conversor.formatearFechaEnAEs(fechaComentario));
                lista.add(registro);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }
}

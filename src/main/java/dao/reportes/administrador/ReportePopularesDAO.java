package dao.reportes.administrador;

import ConexionDB.Conexion;
import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroMeGusta;
import beans.registro.comunes.RegistroSuscripcion;
import beans.reportes.administrador.ReportePopularesBean;
import beans.reportes.editor.ReporteRevistaMasGustadaBean;
import comunes.Conversor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportePopularesDAO {

    private static final String SQL_OBTENER_ID_REVISTAS_POPULARES = "SELECT id_revista, COUNT(id_revista) AS num_suscritos FROM suscripcion WHERE fecha_suscripcion BETWEEN ? AND ? GROUP BY id_revista ORDER BY num_suscritos DESC LIMIT 5";
    private static final String SQL_OBTENER_REVISTAS = "SELECT p.nombre_usuario, r.nombre FROM publicacion p JOIN revista r ON(p.id_revista=r.id_revista) WHERE r.id_revista=?";
    private static final String SQL_OBTENER_REGISTROS = "SELECT nombre_usuario, fecha_suscripcion FROM suscripcion WHERE fecha_suscripcion BETWEEN ? AND ? AND id_revista=? ORDER BY fecha_suscripcion DESC;";

    public List<ReportePopularesBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReportePopularesBean> lista = new ArrayList<>();
        ReportePopularesBean tabla;
        DatosRevista datosRevista = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_REVISTAS_POPULARES);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("id_revista");
                datosRevista = this.obtenerDatosRevista(idRevista);
                List<RegistroSuscripcion> registros = this.obtenerRegistrosSuscripcionesRevista(fechaInicial, fechaFinal, idRevista);
                tabla = new ReportePopularesBean(datosRevista, registros.size(), registros);
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

    private DatosRevista obtenerDatosRevista(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DatosRevista datosRevista = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REVISTAS);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreRevista = rs.getString("r.nombre");
                String nombreEditor = rs.getString("p.nombre_usuario");
                datosRevista = new DatosRevista(nombreRevista, nombreEditor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return datosRevista;
    }

    private List<RegistroSuscripcion> obtenerRegistrosSuscripcionesRevista(String fechaInicial, String fechaFinal, int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroSuscripcion> lista = new ArrayList<>();
        RegistroSuscripcion registro = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REGISTROS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setInt(3, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String fechaSuscripcion = rs.getString("fecha_suscripcion");
                registro = new RegistroSuscripcion(nombreUsuario, Conversor.formatearFechaEnAEs(fechaSuscripcion));
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

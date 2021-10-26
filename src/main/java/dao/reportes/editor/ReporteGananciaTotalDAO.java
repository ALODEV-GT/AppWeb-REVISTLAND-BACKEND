package dao.reportes.editor;

import ConexionDB.Conexion;
import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroGanancia;
import beans.reportes.editor.ReporteGananciaTotalBean;
import beans.reportes.editor.TablaReporteGananciaTotalBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteGananciaTotalDAO {

    private static final String SQL_OBTENER_ID_REVISTAS_EN_INTERVALO = "SELECT id_revista, COUNT(id_revista) AS cantidad_suscripciones FROM suscripcion WHERE fecha_suscripcion BETWEEN ? AND ? GROUP BY id_revista ORDER BY cantidad_suscripciones DESC;";
    private static final String SQL_OBTENER_REVISTA = "SELECT p.nombre_usuario, r.nombre FROM publicacion p JOIN revista r ON(p.id_revista=r.id_revista) WHERE r.id_revista=?";
    private static final String SQL_OBTENER_REGISTROS = "SELECT nombre_usuario, fecha_suscripcion, ganancia_editor FROM suscripcion WHERE fecha_suscripcion BETWEEN ? AND ? AND id_revista = ?";

    public List<ReporteGananciaTotalBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReporteGananciaTotalBean> lista = new ArrayList<>();
        ReporteGananciaTotalBean reporte = null;
        TablaReporteGananciaTotalBean tabla = null;
        List<TablaReporteGananciaTotalBean> tablas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_REVISTAS_EN_INTERVALO);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("id_revista");
                tabla = this.obtenerTablaReporte(fechaInicial, fechaFinal, idRevista);
                tablas.add(tabla);
            }

            double gananciaTotal = 0;
            for (TablaReporteGananciaTotalBean t : tablas) {
                gananciaTotal += t.getGananciaTotal();
            }

            reporte = new ReporteGananciaTotalBean(gananciaTotal, tablas);
            lista.add(reporte);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }

    private TablaReporteGananciaTotalBean obtenerTablaReporte(String fechaInicial, String fechaFinal, int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TablaReporteGananciaTotalBean tabla = null;
        DatosRevista datosRevista = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REVISTA);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreRevista = rs.getString("r.nombre");
                String nombreEditor = rs.getString("p.nombre_usuario");
                List<RegistroGanancia> registros = this.obtenerRegistrosComentariosPublicacion(fechaInicial, fechaFinal, idRevista);
                datosRevista = new DatosRevista(nombreRevista, nombreEditor);
                double gananciaTotal = 0;
                for (RegistroGanancia rg : registros) {
                    gananciaTotal += rg.getGanancia();
                }

                tabla = new TablaReporteGananciaTotalBean(datosRevista, gananciaTotal, registros);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return tabla;
    }

    private List<RegistroGanancia> obtenerRegistrosComentariosPublicacion(String fechaInicial, String fechaFinal, int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroGanancia> lista = new ArrayList<>();
        RegistroGanancia registro = null;

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
                double gananciaEditor = rs.getDouble("ganancia_editor");
                registro = new RegistroGanancia(nombreUsuario, fechaSuscripcion, gananciaEditor);
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

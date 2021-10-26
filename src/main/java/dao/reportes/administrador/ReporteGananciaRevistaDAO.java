package dao.reportes.administrador;

import ConexionDB.Conexion;
import beans.comunes.DatosRevista;
import beans.registro.administrador.RegistroGananciaRevista;
import beans.reportes.administrador.ReporteGananciaRevistaBean;
import beans.reportes.administrador.TablaReporteGananciaRevistaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static comunes.FuncionesBasicas.formatearDouble;

public class ReporteGananciaRevistaDAO {

    private static final String SQL_OBTENER_REVISTAS = "SELECT r.id_revista,p.nombre_usuario, r.nombre FROM publicacion p JOIN revista r ON(p.id_revista=r.id_revista) ORDER BY p.fecha_publicacion DESC";
    private static final String SQL_OBTENER_SUSCRIPCIONES = "SELECT nombre_usuario, fecha_suscripcion, cuota_por_servicio FROM suscripcion WHERE fecha_suscripcion BETWEEN ? AND ? AND id_revista=? ORDER BY fecha_suscripcion DESC";
    private static final String SQL_OBTENER_COSTOS = "SELECT cobro_por_dia FROM detalle_cobro_por_dia WHERE fecha_cobro BETWEEN ? AND ? AND id_revista=?";

    public List<ReporteGananciaRevistaBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReporteGananciaRevistaBean> lista = new ArrayList<>();
        ReporteGananciaRevistaBean reporte = null;
        TablaReporteGananciaRevistaBean tabla = null;
        List<TablaReporteGananciaRevistaBean> tablas = new ArrayList<>();
        DatosRevista datosRevista = null;
        List<RegistroGananciaRevista> registros = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REVISTAS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("r.id_revista");
                String nombreRevista = rs.getString("r.nombre");
                String nombreEditor = rs.getString("p.nombre_usuario");
                datosRevista = new DatosRevista(nombreRevista, nombreEditor);
                registros = this.obtenerRegistros(fechaInicial, fechaFinal, idRevista);
                double costo = this.obtenerCostoPorServicio(fechaInicial, fechaFinal, idRevista);
                double ingresos = 0;
                for (RegistroGananciaRevista r : registros) {
                    ingresos += r.getIngreso();
                }
                double ganancia = ingresos - costo;
                tabla = new TablaReporteGananciaRevistaBean(datosRevista, registros.size(), formatearDouble(ingresos), formatearDouble(costo), formatearDouble(ganancia), registros);
                tablas.add(tabla);
            }

            double ingresoTotal = 0;
            double costoTotal = 0;
            double gananciaTotal = 0;

            for (TablaReporteGananciaRevistaBean t : tablas) {
                ingresoTotal += t.getTotalIngresos();
                costoTotal += t.getTotalCostos();
                gananciaTotal += t.getTotalGanancias();
            }

            reporte = new ReporteGananciaRevistaBean(formatearDouble(ingresoTotal), formatearDouble(costoTotal), formatearDouble(gananciaTotal), tablas);
            lista.add(reporte);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }

    private List<RegistroGananciaRevista> obtenerRegistros(String fechaInicial, String fechaFinal, int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroGananciaRevista registro = null;
        List<RegistroGananciaRevista> registros = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_SUSCRIPCIONES);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setInt(3, idRevista);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String fechaSuscripcion = rs.getString("fecha_suscripcion");
                double ingreso = rs.getDouble("cuota_por_servicio");
                registro = new RegistroGananciaRevista(nombreUsuario, fechaSuscripcion, ingreso, 0, 0);
                registros.add(registro);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return registros;
    }

    private double obtenerCostoPorServicio(String fechaInicial, String fechaFinal, int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double totalCosto = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_COSTOS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setInt(3, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                double costo = rs.getDouble("cobro_por_dia");
                totalCosto += costo;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return totalCosto;
    }

}

package dao.publicar.costoPorDia;

import ConexionDB.Conexion;
import dao.fechaGlobal.FechaGlobalDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DetalleCostoPorDiaDAO {

    private static final String SQL_AGREGAR_DETALLE = "INSERT INTO detalle_cobro_por_dia(id_revista,fecha_cobro,cobro_por_dia) VALUES(?,?,?)";
    private static final String SQL_OBTENER_ULTIMA_FECHA = "SELECT fecha_cobro FROM detalle_cobro_por_dia WHERE id_revista=? ORDER BY fecha_cobro DESC LIMIT 1 ";

    public void agregarPrimerDetalle(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_AGREGAR_DETALLE);
            String fechaActua = new FechaGlobalDAO().obtenerFecha().getFecha();
            double costoPorDiaGlobal = new CostoPorDiaDAO().obtenerCostoPorDiaGlobal();

            stmt.setInt(1, idRevista);
            stmt.setString(2, fechaActua);
            stmt.setDouble(3, costoPorDiaGlobal);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public void agregarDetalles(List<String> fechas, int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();

            double costoPorDia = new CostoPorDiaDAO().obtenerCostoPorDiaRevista(idRevista);

            stmt = conn.prepareStatement(SQL_AGREGAR_DETALLE);
            stmt.setInt(1, idRevista);
            stmt.setDouble(3, costoPorDia);

            for (String fecha : fechas) {
                stmt.setString(2, fecha);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public LocalDate obtenerFechaUltimoDetalle(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fecha = "";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ULTIMA_FECHA);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();
            while (rs.next()) {
                fecha = rs.getString("fecha_cobro");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return LocalDate.parse(fecha);
    }

}

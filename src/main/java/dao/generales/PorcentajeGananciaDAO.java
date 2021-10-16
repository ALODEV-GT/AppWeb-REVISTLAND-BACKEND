package dao.generales;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PorcentajeGananciaDAO {

    private static final String SQL_OBTENER_PORCENTAJE = "SELECT porcentaje FROM porcentaje_ganancia";
    private static final String SQL_CAMBIAR_PORCENTAJE = "UPDATE porcentaje_ganancia SET porcentaje=?";

    public double obtenerPorcentajeGanancia() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double porcentaje = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_PORCENTAJE);
            rs = stmt.executeQuery();
            while (rs.next()) {
                porcentaje = rs.getDouble("porcentaje");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return porcentaje;
    }

    public void cambiarPorcentajeGanancia(double nuevoPorcentaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CAMBIAR_PORCENTAJE);
            stmt.setDouble(1, nuevoPorcentaje);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
}

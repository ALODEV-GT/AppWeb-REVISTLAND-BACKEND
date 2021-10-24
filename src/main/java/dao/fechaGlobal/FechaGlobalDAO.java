package dao.fechaGlobal;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.fechaGlobal.FechaM;

public class FechaGlobalDAO {

    private static final String SQL_OBTENER_FECHA = "SELECT fecha FROM fecha_global";
    private static final String SQL_CAMBIA_FECHA = "UPDATE fecha_global SET fecha=?";

    public FechaM obtenerFecha() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FechaM fecha = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_FECHA);
            rs = stmt.executeQuery();
            while (rs.next()) {
                fecha = new FechaM(rs.getString("fecha"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return fecha;
    }

    public void cambiarFecha(FechaM fecha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CAMBIA_FECHA);
            stmt.setString(1, fecha.getFecha());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
}

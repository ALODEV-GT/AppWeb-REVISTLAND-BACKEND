package dao.interacciones;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeGustaDAO {

    private static final String SQL_NUMERO_ME_GUSTA = "SELECT COUNT(*) AS numero FROM me_gusta WHERE id_publicacion=?";

    public int obtenerNumeroMeGusta(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numMeGusta = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_NUMERO_ME_GUSTA);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                numMeGusta = rs.getInt("numero");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return numMeGusta;
    }
}

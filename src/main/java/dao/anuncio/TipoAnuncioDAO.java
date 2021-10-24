package dao.anuncio;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Anuncio.Precio;

public class TipoAnuncioDAO {

    private static final String SQL_OBTENER_PRECIO = "SELECT precio FROM tipo_anuncio WHERE id_tipo_anuncio=?";

    public Precio obtenerPrecioTipoAnuncio(int idTipoAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Precio precioObj = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_PRECIO);
            stmt.setInt(1, idTipoAnuncio);
            rs = stmt.executeQuery();
            while (rs.next()) {
                double precio = rs.getDouble("precio");
                precioObj = new Precio(precio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return precioObj;
    }

}

package dao.suscripcion;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuscripcionDAO {
    private static final String SQL_ID_REVISTAS_SUSCRITAS = "SELECT id_revista FROM suscripcion WHERE nombre_usuario=?";
    
    public List<Integer> listarIdRevistasSuscritas(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ID_REVISTAS_SUSCRITAS);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_revista");
                list.add(id);
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrio un erro al listar categorias");
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }
}

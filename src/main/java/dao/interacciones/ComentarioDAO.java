package dao.interacciones;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComentarioDAO {
    private static final String SQL_NUM_COMENTARIOS = "SELECT COUNT(*) AS numero FROM comentario WHERE id_publicacion=?";

    public int obtenerNumeroComentarios(int idPublicacion){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numComentarios = 0;
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_NUM_COMENTARIOS);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();
            while (rs.next()) {                
                numComentarios = rs.getInt("numero");
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        
        return numComentarios;
    }
}

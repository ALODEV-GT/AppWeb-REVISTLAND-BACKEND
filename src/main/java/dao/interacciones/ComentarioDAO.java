package dao.interacciones;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.interacciones.ComentarioM;

public class ComentarioDAO {

    private static final String SQL_NUM_COMENTARIOS = "SELECT COUNT(*) AS numero FROM comentario WHERE id_publicacion=?";
    private static final String SQL_ENCONTRAR_COMENTARIOS = "SELECT nombre_usuario, contenido FROM comentario WHERE id_publicacion=?";

    public int obtenerNumeroComentarios(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numComentarios = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_NUM_COMENTARIOS);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                numComentarios = rs.getInt("numero");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return numComentarios;
    }

    public List<ComentarioM> obtenerComentarios(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ComentarioM> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_COMENTARIOS);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String contenido = rs.getString("contenido");

                ComentarioM comentario = new ComentarioM(nombreUsuario, contenido);
                list.add(comentario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }
}

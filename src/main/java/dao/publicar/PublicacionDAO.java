package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.publicarRevista.PublicacionM;

public class PublicacionDAO {

    private static final String SQL_INSERTAR = "INSERT INTO publicacion(fecha_publicacion,id_revista"
            + ",nombre_usuario) VALUES(?,?,?)";
    
    private static final String SQL_OBTENER_ID = "SELECT id_publicacion FROM publicacion WHERE id_revista=?";

    public void insertar(PublicacionM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setString(1, modelo.getFechaPublicacion());
            stmt.setInt(2, modelo.getIdRevista());
            stmt.setString(3, modelo.getNombreUsuario());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el volumen en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
    
    public int obtenerIdPublicacion(int idRevista){
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_publicacion");
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar id_revista en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }
}

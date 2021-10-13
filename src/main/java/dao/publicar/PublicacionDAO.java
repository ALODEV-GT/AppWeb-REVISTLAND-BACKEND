package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.publicarRevista.PublicacionM;

public class PublicacionDAO {

    private static final String SQL_INSERTAR = "INSERT INTO publicacion(fecha_publicacion,id_revista"
            + ",nombre_usuario) VALUES(?,?,?)";

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
}

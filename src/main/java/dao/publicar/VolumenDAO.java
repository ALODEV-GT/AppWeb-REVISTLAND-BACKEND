package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.publicarRevista.VolumenM;

public class VolumenDAO {

    private static final String SQL_INSERTAR = "INSERT INTO volumen(id_revista,nombre_volumen,fecha_publicacion"
            + ",id_archivo) VALUES(?,?,?,?)";

    public void insertar(VolumenM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setInt(1, modelo.getIdRevista());
            stmt.setString(2, modelo.getNombreVolumen());
            stmt.setString(3, modelo.getFechaPublicacion());
            stmt.setInt(4, modelo.getIdArchivo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el volumen en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
}

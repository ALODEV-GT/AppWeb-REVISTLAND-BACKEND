package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EtiquetaRevistaDAO {
    private static final String SQL_INSERTAR_ETIQUETA_REVISTA = "INSERT IGNORE INTO etiqueta_revista(id_revista,nombre_etiqueta) VALUES(?,?)";

    public void agregarEtiquetasRevista(int idRevista , String[] etiquetas) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_ETIQUETA_REVISTA);
            stmt.setInt(1, idRevista);

            for (String etiqueta : etiquetas) {
                stmt.setString(2, etiqueta);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Error al insertar etiqueta revista");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
}

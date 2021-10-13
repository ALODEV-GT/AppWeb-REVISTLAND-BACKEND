package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaRevistaDAO {
    private static final String SQL_INSERTAR_CATEGORIA_REVISTA = "INSERT IGNORE INTO categoria_revista(id_revista,nombre_categoria) VALUES(?,?)";

    public void agregarCategoriasRevista(int idRevista, String[] categorias) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_CATEGORIA_REVISTA);
            stmt.setInt(1, idRevista);

            for (String categoria : categorias) {
                stmt.setString(2, categoria);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Error al insertar  categoria revista");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
}

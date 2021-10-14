package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRevistaDAO {

    private static final String SQL_INSERTAR_CATEGORIA_REVISTA = "INSERT IGNORE INTO categoria_revista(id_revista,nombre_categoria) VALUES(?,?)";
    private static final String SQL_OBTENER_ID_REVISTAS = "SELECT id_revista FROM categoria_revista WHERE nombre_categoria=?";
    private static final String SQL_OBTENER_CATEGORIAS = "SELECT nombre_categoria FROM categoria_revista WHERE id_revista=?";

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

    public List<Integer> listarIdRevistas(String nombreCategoria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_REVISTAS);
            stmt.setString(1, nombreCategoria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_revista");
                list.add(id);
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrio un erro al listar categorias");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }

    public List<String> obtenerCetegoriasRevista(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> categorias = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_CATEGORIAS);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                categorias.add(rs.getString("nombre_categoria"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return categorias;
    }
}

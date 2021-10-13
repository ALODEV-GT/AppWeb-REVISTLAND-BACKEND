package dao.perfil;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreferenciasCategoriaDAO {

    private static final String SQL_INSERTAR_PREFERENCIA_CATEGORIA = "INSERT IGNORE INTO categoria_preferencia_usuario(nombre_usuario,nombre_categoria) VALUES(?,?)";
    private static final String SQL_ELIMINAR_GRUPO = "DELETE FROM categoria_preferencia_usuario WHERE nombre_usuario=?";
    private static final String SQL_LISTAR = "SELECT nombre_categoria FROM categoria_preferencia_usuario WHERE nombre_usuario=?";

    public void agregarCategoriasUsuario(String nombreUsuario, String[] categorias) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_PREFERENCIA_CATEGORIA);
            stmt.setString(1, nombreUsuario);

            for (String categoria : categorias) {
                stmt.setString(2, categoria);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public void eliminarCategoriasUsuario(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR_GRUPO);
            stmt.setString(1, nombreUsuario);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public List<String> obtenerCategoriasUsuario(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre_categoria");
                list.add(nombre);
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
}

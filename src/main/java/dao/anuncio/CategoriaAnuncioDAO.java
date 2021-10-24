package dao.anuncio;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaAnuncioDAO {

    private static final String SQL_INSERTAR_CATEGORIA_ANUNCIO = "INSERT IGNORE INTO categoria_anuncio(id_anuncio,nombre_categoria) VALUES(?,?)";
    private static final String SQL_OBTENER_ID_ANUNCIOS = "SELECT id_anuncio FROM categoria_anuncio WHERE nombre_categoria=?";
    
    public void agregarCategoriasRevista(int idAnuncio, String[] categorias) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_CATEGORIA_ANUNCIO);
            stmt.setInt(1, idAnuncio);

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
    
    public List<Integer> listarIdAnuncios(String nombreCategoria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ANUNCIOS);
            stmt.setString(1, nombreCategoria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_anuncio");
                list.add(id);
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

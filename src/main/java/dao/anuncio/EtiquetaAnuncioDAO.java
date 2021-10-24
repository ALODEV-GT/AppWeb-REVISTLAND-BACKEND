package dao.anuncio;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaAnuncioDAO {

    private static final String SQL_INSERTAR_ETIQUETA_ANUNCIO = "INSERT IGNORE INTO etiqueta_anuncio(id_anuncio,nombre_etiqueta) VALUES(?,?)";
    private static final String SQL_OBTENER_ID_ANUNCIO = "SELECT id_anuncio FROM etiqueta_anuncio WHERE nombre_etiqueta=?";
    
    public void agregarEtiquetasRevista(int idAnuncio, String[] etiquetas) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_ETIQUETA_ANUNCIO);
            stmt.setInt(1, idAnuncio);

            for (String etiqueta : etiquetas) {
                stmt.setString(2, etiqueta);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

     public List<Integer> listarIdAnuncios(String nombreEtiqueta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ANUNCIO);
            stmt.setString(1, nombreEtiqueta);
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

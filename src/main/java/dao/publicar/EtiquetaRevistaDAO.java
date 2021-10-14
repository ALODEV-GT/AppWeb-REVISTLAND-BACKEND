package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaRevistaDAO {

    private static final String SQL_INSERTAR_ETIQUETA_REVISTA = "INSERT IGNORE INTO etiqueta_revista(id_revista,nombre_etiqueta) VALUES(?,?)";
    private static final String SQL_OBTENER_ID_REVISTAS = "SELECT id_revista FROM etiqueta_revista WHERE nombre_etiqueta=?";
    private static final String SQL_OBTENER_ETIQUETAS = "SELECT nombre_etiqueta FROM etiqueta_revista WHERE id_revista=?";

    public void agregarEtiquetasRevista(int idRevista, String[] etiquetas) {
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

    public List<Integer> listarIdRevistas(String nombreEtiqueta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_REVISTAS);
            stmt.setString(1, nombreEtiqueta);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_revista");
                list.add(id);
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrio un erro al listar etiquetas");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }

    public List<String> obtenerEtiquetasRevista(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> etiquetas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ETIQUETAS);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                etiquetas.add(rs.getString("nombre_etiqueta"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return etiquetas;
    }
}

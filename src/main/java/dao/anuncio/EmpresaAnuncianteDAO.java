package dao.anuncio;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaAnuncianteDAO {

    private static final String SQL_ENCONTRAR = "SELECT id_anunciante, nombre FROM empresa_anunciante WHERE nombre=?";
    private static final String SQL_INSERTAR = "INSERT INTO empresa_anunciante(nombre) VALUES(?)";
    private static final String SQL_OBTENER_ID_ULTIMO_INSERTADO = "SELECT id_anunciante FROM empresa_anunciante ORDER BY id_anunciante DESC LIMIT 1";
    private static final String SQL_OBTENER_ID_ANUNCIANTE = "SELECT id_anunciante FROM empresa_anunciante WHERE nombre=?";

    public boolean existe(String nombreAnunciante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR);
            stmt.setString(1, nombreAnunciante);
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return existe;
    }

    public void insertar(String nombreAnunciante) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setString(1, nombreAnunciante.toUpperCase());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public int obtenerIdUltimoInsertado() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ULTIMO_INSERTADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_anunciante");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

    public int obtenerIdAnunciante(String nombreAnunciante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ANUNCIANTE);
            stmt.setString(1, nombreAnunciante);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_anunciante");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

}

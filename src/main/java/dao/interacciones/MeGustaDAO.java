package dao.interacciones;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.interacciones.MeGustaM;

public class MeGustaDAO {

    private static final String SQL_NUMERO_ME_GUSTA = "SELECT COUNT(*) AS numero FROM me_gusta WHERE id_publicacion=?";
    private static final String SQL_INSERTAR = "INSERT INTO me_gusta(id_publicacion,fecha_like,nombre_usuario) VALUES(?,?,?)";
    private static final String SQL_ELIMINAR = "DELETE FROM me_gusta WHERE id_publicacion=? AND nombre_usuario=?";
    private static final String SQL_ENCONTRAR = "SELECT id_publicacion, fecha_like, nombre_usuario FROM me_gusta WHERE id_publicacion=? AND nombre_usuario=?";

    public int obtenerNumeroMeGusta(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numMeGusta = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_NUMERO_ME_GUSTA);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                numMeGusta = rs.getInt("numero");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return numMeGusta;
    }

    public void insertarMeGusta(MeGustaM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setInt(1, modelo.getIdPulicacion());
            stmt.setString(2, modelo.getFecha());
            stmt.setString(3, modelo.getNombreUsuario());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public void eliminarMeGusta(MeGustaM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR);
            stmt.setInt(1, modelo.getIdPulicacion());
            stmt.setString(2, modelo.getNombreUsuario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public boolean existe(MeGustaM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR);
            stmt.setInt(1, modelo.getIdPulicacion());
            stmt.setString(2, modelo.getNombreUsuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return existe;
    }

}

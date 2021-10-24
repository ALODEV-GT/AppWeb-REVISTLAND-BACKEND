package dao.publicar;

import ConexionDB.Conexion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.archivo.ArchivoM;

public class ArchivoDAO {

    private static final String SQL_INSERTAR = "INSERT INTO archivo(archivo,nombre_archivo) "
            + "VALUES(?,?)";
    private static final String SQL_OBTENER_ID_ULTIMO_INSERTADO = "SELECT id_archivo FROM archivo ORDER BY id_archivo DESC LIMIT 1";
    private static final String SQL_OBTENER_ARCHIVO = "SELECT nombre_archivo, archivo FROM archivo WHERE id_archivo=?";
    private static final String SQL_ELIMINAR_ARCHIVO = "DELETE FROM archivo WHERE id_archivo=?";

    public void insertar(InputStream archivo, String nombreArchivo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setBlob(1, archivo);
            stmt.setString(2, nombreArchivo);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el archivo en la base de datos");
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
                id = rs.getInt("id_archivo");
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar id_archivo en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

    public ArchivoM obtenerArchivo(int idArchivo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArchivoM archivo = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ARCHIVO);
            stmt.setInt(1, idArchivo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreArchivo = rs.getString("nombre_archivo");
                InputStream contenido = rs.getBlob("archivo").getBinaryStream();
                archivo = new ArchivoM(nombreArchivo, contenido);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return archivo;
    }

    public void eliminarArchivo(int idArchivo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ELIMINAR_ARCHIVO);
            stmt.setInt(1, idArchivo);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

}

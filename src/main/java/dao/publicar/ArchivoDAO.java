package dao.publicar;

import ConexionDB.Conexion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchivoDAO {
    public static final String SQL_INSERTAR = "INSERT INTO archivo(archivo,nombre_archivo) "
            + "VALUES(?,?)";
    private static final String SQL_OBTENER_ID_ULTIMO_INSERTADO = "SELECT id_archivo FROM archivo ORDER BY id_archivo DESC LIMIT 1";
    
    public void insertar(InputStream archivo, String nombreArchivo){
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
}

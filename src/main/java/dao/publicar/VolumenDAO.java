package dao.publicar;

import ConexionDB.Conexion;
import comunes.Conversor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.publicarRevista.VolumenM;

public class VolumenDAO {

    private static final String SQL_INSERTAR = "INSERT INTO volumen(id_revista,nombre_volumen,fecha_publicacion"
            + ",id_archivo) VALUES(?,?,?,?)";
    private static final String SQL_ENCONTRAR_VOLUMENES = "SELECT id_volumen, id_revista, nombre_volumen, fecha_publicacion, id_archivo FROM volumen WHERE id_revista=?";

    public void insertar(VolumenM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setInt(1, modelo.getIdRevista());
            stmt.setString(2, modelo.getNombreVolumen());
            stmt.setString(3, modelo.getFechaPublicacion());
            stmt.setInt(4, modelo.getIdArchivo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el volumen en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public List<VolumenM> obtenerVolumenesRevista(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<VolumenM> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_VOLUMENES);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre_volumen");
                String fechaPublicacion = rs.getString("fecha_publicacion");
                int idArchivo = rs.getInt("id_archivo");

                VolumenM volumen = new VolumenM(nombre,Conversor.formatearFechaEnAEs(fechaPublicacion), idArchivo);
                list.add(volumen);
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

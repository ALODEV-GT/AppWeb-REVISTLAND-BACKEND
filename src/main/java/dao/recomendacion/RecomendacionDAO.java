package dao.recomendacion;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.recomendacion.RecomendacionM;

public class RecomendacionDAO {

    private static final String SQL_RECOMENDACIONES = "SELECT r.nombre, r.precio_mensual, r.precio_anual, p.nombre_usuario FROM revista r JOIN publicacion p ON(r.id_revista=p.id_revista) WHERE r.id_revista=?";

    public RecomendacionM encontrar(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RecomendacionM modelo = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_RECOMENDACIONES);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreRevista = rs.getString("r.nombre");
                Double precioMensual = rs.getDouble("r.precio_mensual");
                Double precioAnual = rs.getDouble("r.precio_anual");
                String autor = rs.getString("p.nombre_usuario");
                modelo = new RecomendacionM(nombreRevista, autor, precioMensual, precioAnual, idRevista);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar perfil en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return modelo;
    }

}

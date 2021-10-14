package dao.descripcionRevista;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.DescripcionRevista.DescripcionRevistaM;

public class DescripcionRevistaDAO {

    private static final String SQL_ENCONTRAR = "SELECT r.nombre, p.nombre_usuario, r.precio_mensual, r.precio_anual, r.descripcion FROM revista r JOIN publicacion p ON(r.id_revista=p.id_revista) WHERE r.id_revista=?";

    public DescripcionRevistaM encontrar(int idRevista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DescripcionRevistaM modelo = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreRevista = rs.getString("r.nombre");
                String autor = rs.getString("p.nombre_usuario");
                double precioMensual = rs.getDouble("r.precio_mensual");
                double precioAnual = rs.getDouble("r.precio_anual");
                String descripcion = rs.getString("r.descripcion");
                modelo = new DescripcionRevistaM(idRevista, nombreRevista, autor, precioMensual, precioAnual, descripcion);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar descripcios revista en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return modelo;
    }
}

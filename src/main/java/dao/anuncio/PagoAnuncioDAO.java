package dao.anuncio;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Anuncio.PagoAnuncioM;

public class PagoAnuncioDAO {

    private static final String SQL_INSERTAR_PAGO_ANUNCIO = "INSERT INTO pago_anuncio(id_anunciante,fecha_compra,id_anuncio,costo) VALUES(?,?,?,?)";

    public void insertar(PagoAnuncioM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_PAGO_ANUNCIO);
            stmt.setInt(1, modelo.getIdAnunciante());
            stmt.setString(2, modelo.getFechaCompra());
            stmt.setInt(3, modelo.getIdAnuncio());
            stmt.setDouble(4, modelo.getCosto());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
}

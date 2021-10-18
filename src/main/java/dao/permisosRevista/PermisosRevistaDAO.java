package dao.permisosRevista;

import ConexionDB.Conexion;
import funcionamiento.permisosRevista.EvaluarPermiso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.permisosRevista.PermitirM;

public class PermisosRevistaDAO {

    private static final String SQL_PERMITIR_SUSCRIPCION = "SELECT r.permitir_suscripciones, r.id_revista FROM revista r JOIN publicacion p ON(r.id_revista=p.id_revista) WHERE p.id_publicacion=?";
    private static final String SQL_PERMITIR_INTERACCIONES = "SELECT r.es_interactiva, r.id_revista FROM revista r JOIN publicacion p ON(r.id_revista=p.id_revista) WHERE p.id_publicacion=?";
    private static final String SQL_CAMBIAR_PERMITIR_SUSCRIPCION = "UPDATE revista r JOIN publicacion p ON(r.id_revista=p.id_revista) SET r.permitir_suscripciones=? WHERE p.id_publicacion=?";
    private static final String SQL_CAMBIAR_PERMITIR_INTERACCIONES = "UPDATE revista r JOIN publicacion p ON(r.id_revista=p.id_revista) SET r.es_interactiva=? WHERE p.id_publicacion=?";

    public PermitirM esInteractiva(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PermitirM permiso = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_PERMITIR_INTERACCIONES);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("r.id_revista");
                int esInteractiva = rs.getInt("r.es_interactiva");
                permiso = new PermitirM(EvaluarPermiso.intABoolean(esInteractiva), idRevista);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar id_revista en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return permiso;
    }

    public PermitirM sePuedeSuscribir(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PermitirM permiso = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_PERMITIR_SUSCRIPCION);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("r.id_revista");
                int esInteractiva = rs.getInt("r.permitir_suscripciones");
                permiso = new PermitirM(EvaluarPermiso.intABoolean(esInteractiva), idRevista);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return permiso;
    }

    public void cambiaValorEsInteractiva(PermitirM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CAMBIAR_PERMITIR_INTERACCIONES);
            stmt.setInt(1, EvaluarPermiso.booleanAInt(modelo.isPermitir()));
            stmt.setInt(2, modelo.getIdPublicacion());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public void cambiarValorSePuedeSuscribir(PermitirM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CAMBIAR_PERMITIR_SUSCRIPCION);
            stmt.setInt(1, EvaluarPermiso.booleanAInt(modelo.isPermitir()));
            stmt.setInt(2, modelo.getIdPublicacion());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

}

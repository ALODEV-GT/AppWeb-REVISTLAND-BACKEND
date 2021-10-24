package dao.publicar.costoPorDia;

import ConexionDB.Conexion;
import comunes.Conversor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.costoDiario.RevistaCostoM;

public class CostoPorDiaDAO {

    private static final String SQL_OBTENER_COSTO_DIA_GLOBAL = "SELECT cantidad FROM cantidad_cobro_por_dia_global";
    private static final String SQL_CAMBIAR_COSTO_DIA_GLOBAL = "UPDATE cantidad_cobro_por_dia_global SET cantidad=?";

    private static final String SQL_ASIGNAR_COSTO_POR_DIA = "INSERT INTO costo_por_dia(id_revista,costo) VALUES(?,?)";
    private static final String SQL_CAMBIA_COSTO_POR_DIA = "UPDATE costo_por_dia SET costo=? WHERE id_costo_por_dia=?";

    private static final String SQL_OBTENER_LISTA_COSTO_POR_DIA= "SELECT p.fecha_publicacion, r.nombre, p.nombre_usuario, c.costo, c.id_costo_por_dia FROM costo_por_dia c JOIN revista r ON(c.id_revista=r.id_revista) JOIN publicacion p ON(r.id_revista=p.id_revista) ORDER BY p.fecha_publicacion DESC";

    private static final String SQL_OBTENER_COSTO_DIA_REVISTA = "SELECT costo FROM costo_por_dia WHERE id_revista=?";
    public double obtenerCostoPorDiaGlobal() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double cantidad = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_COSTO_DIA_GLOBAL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cantidad = rs.getDouble("cantidad");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return cantidad;
    }

    public void cambiarCostoPorDiaGlobal(double cantidad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CAMBIAR_COSTO_DIA_GLOBAL);
            stmt.setDouble(1, cantidad);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public void asignarCostoPorDiaRevista(int idRevista, double costo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ASIGNAR_COSTO_POR_DIA);
            stmt.setInt(1, idRevista);
            stmt.setDouble(2, costo);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public void cambiarCostoPorDiaRevista(int idCostoPorDia, double nuevoCosto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CAMBIA_COSTO_POR_DIA);
            stmt.setDouble(1, nuevoCosto);
            stmt.setInt(2, idCostoPorDia);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public List<RevistaCostoM> listarRevistasConCostos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RevistaCostoM> list = new ArrayList<>();
        RevistaCostoM revistaCosto = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_LISTA_COSTO_POR_DIA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String fechaPublicacion = rs.getString("p.fecha_publicacion");
                String nombreRevista = rs.getString("r.nombre");
                String editor = rs.getString("p.nombre_usuario");
                double costo = rs.getDouble("c.costo");
                int idCostoPorDia = rs.getInt("c.id_costo_por_dia");

                revistaCosto = new RevistaCostoM(Conversor.formatearFechaEnAEs(fechaPublicacion), nombreRevista, editor, costo, idCostoPorDia);
                list.add(revistaCosto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return list;
    }
    
    public double obtenerCostoPorDiaRevista(int idRevista){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double cantidad = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_COSTO_DIA_REVISTA);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cantidad = rs.getDouble("costo");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return cantidad;
    }
}

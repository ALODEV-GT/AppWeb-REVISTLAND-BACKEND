package dao.publicar;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.publicarRevista.RevistaM;

public class RevistaDAO {

    private static final String SQL_INSERTAR = "INSERT INTO revista(nombre,descripcion,precio_mensual"
            + ",precio_anual,es_interactiva,permitir_suscripciones) VALUES(?,?,?,?,1,1)";
    private static final String SQL_OBTENER_ID_ULTIMO_INSERTADO = "SELECT id_revista FROM revista ORDER BY id_revista DESC LIMIT 1";
    private static final String SQL_OBTENER_NOMBRE_REVISTA = "SELECT nombre FROM revista WHERE id_revista=?";
    private static final String SQL_OBTENER_ID_REVISTAS_EDITOR = "SELECT r.id_revista FROM revista r JOIN publicacion p ON(r.id_revista=p.id_revista) WHERE p.nombre_usuario=?";
    private static final String SQL_OBTENER_ID_REVISTAS = "SELECT id_revista FROM revista";
    
    public void insertar(RevistaM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setString(1, modelo.getNombre());
            stmt.setString(2, modelo.getDescripcion());
            stmt.setDouble(3, modelo.getCostoMes());
            stmt.setDouble(4, modelo.getCostoAnio());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar la revista en la base de datos");
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
                id = rs.getInt("id_revista");
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar id_revista en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }
    
    public String obtenerNombreRevista(int idRevista){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nombreRevista = "";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_NOMBRE_REVISTA);
            stmt.setInt(1, idRevista);
            rs = stmt.executeQuery();

            while (rs.next()) {
                nombreRevista = rs.getString("nombre");
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar id_revista en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return nombreRevista;
    }
    
    public List<Integer> obtenerIdRevistasEditor(String editor){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_REVISTAS_EDITOR);
            stmt.setString(1, editor);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("r.id_revista");
                list.add(idRevista);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return list;
    }
    
    public List<Integer> obtenerIdRevistas(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_REVISTAS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("id_revista");
                list.add(idRevista);
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

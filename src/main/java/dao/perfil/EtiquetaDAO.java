package dao.perfil;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaDAO {
    private static final String SQL_INSERTAR = "INSERT IGNORE INTO etiqueta(nombre) VALUES(?)";
    private static final String SQL_LISTAR = "SELECT nombre FROM etiqueta";
    
    public void agregarEtiquetas(String[] etiquetas){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            
            for (String etiqueta : etiquetas) {
                stmt.setString(1, etiqueta);
                stmt.executeUpdate();
            }
            
        }catch(SQLException ex){
            System.err.println("Error al insertar etiqueta");
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(stmt);
        }
    }
    
    public List<String> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAR);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                list.add(nombre);
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrio un erro al listar etiquetas");
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }
}

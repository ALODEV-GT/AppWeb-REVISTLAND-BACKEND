package dao.reportes.editor;

import ConexionDB.Conexion;
import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroMeGusta;
import beans.reportes.editor.ReporteRevistaMasGustadaBean;
import comunes.Conversor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteRevistasMasGustadaDAO {
    
    private static final String SQL_REVISTAS_MAS_GUSTADAS = "SELECT id_publicacion, COUNT(id_publicacion) AS cantidad_like FROM me_gusta WHERE fecha_like BETWEEN ? AND ? GROUP BY id_publicacion  ORDER BY cantidad_like DESC LIMIT 5";
    private static final String SQL_OBTENER_REVISTAS = "SELECT p.nombre_usuario, r.nombre FROM publicacion p JOIN revista r ON(p.id_revista=r.id_revista) WHERE p.id_publicacion=?";
    private static final String SQL_OBTENER_REGISTROS = "SELECT nombre_usuario, fecha_like FROM me_gusta  WHERE fecha_like BETWEEN ? AND ?  AND id_publicacion = ?";
    
    public List<ReporteRevistaMasGustadaBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReporteRevistaMasGustadaBean> lista = new ArrayList<>();
        ReporteRevistaMasGustadaBean tabla;
        DatosRevista datosRevista = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REVISTAS_MAS_GUSTADAS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idPublicacion = rs.getInt("id_publicacion");
                datosRevista = this.obtenerDatosRevista(idPublicacion);
                List<RegistroMeGusta> registros = this.obtenerRegistrosMeGustaRevista(fechaInicial, fechaFinal, idPublicacion);
                tabla = new ReporteRevistaMasGustadaBean(datosRevista, registros.size(), registros);
                lista.add(tabla);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }
    
    private DatosRevista obtenerDatosRevista(int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DatosRevista datosRevista = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REVISTAS);
            stmt.setInt(1, idPublicacion);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String nombreRevista = rs.getString("r.nombre");
                String nombreEditor = rs.getString("p.nombre_usuario");
                datosRevista = new DatosRevista(nombreRevista, nombreEditor);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return datosRevista;
    }
    
    private List<RegistroMeGusta> obtenerRegistrosMeGustaRevista(String fechaInicial, String fechaFinal, int idPublicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroMeGusta> lista = new ArrayList<>();
        RegistroMeGusta registro = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REGISTROS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setInt(3, idPublicacion);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String fechaLike = rs.getString("fecha_like");
                registro = new RegistroMeGusta(nombreUsuario, Conversor.formatearFechaEnAEs(fechaLike));
                lista.add(registro);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }
}

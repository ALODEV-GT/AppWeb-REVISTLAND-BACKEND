package dao.reportes.administrador;

import ConexionDB.Conexion;
import beans.registro.administrador.RegistroGananciaAnuncio;
import beans.reportes.administrador.ReporteGananciaAnuncioBean;
import beans.reportes.administrador.TablaReporteGananciaAnuncioBean;
import static comunes.FuncionesBasicas.formatearDouble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteGananciaAnuncioDAO {
    
    private static final String SQL_OBTENER_ANUNCIANTES = "SELECT e.nombre, COUNT(e.nombre) FROM pago_anuncio p JOIN empresa_anunciante e ON(p.id_anunciante=e.id_anunciante) WHERE p.fecha_compra BETWEEN ? AND ? GROUP BY e.nombre";
    private static final String SQL_OBTENER_REGISTROS = "SELECT p.fecha_compra, t.nombre, p.costo/a.cantidad_dias AS precio_por_dia, a.cantidad_dias, p.costo FROM pago_anuncio p JOIN anuncio a ON(p.id_anuncio=a.id_anuncio) JOIN tipo_anuncio t ON(a.id_tipo_anuncio=t.id_tipo_anuncio) JOIN empresa_anunciante ea ON(p.id_anunciante=ea.id_anunciante) WHERE p.fecha_compra BETWEEN ? AND ? AND  ea.nombre=?";
    
    public List<ReporteGananciaAnuncioBean> obtenerDatosReporte(String fechaInicial, String fechaFinal) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReporteGananciaAnuncioBean> lista = new ArrayList<>();
        ReporteGananciaAnuncioBean reporte = null;
        TablaReporteGananciaAnuncioBean tabla = null;
        List<TablaReporteGananciaAnuncioBean> tablas = new ArrayList<>();
        List<RegistroGananciaAnuncio> registros = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ANUNCIANTES);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String nombreAnunciante = rs.getString("e.nombre");
                registros = this.obtenerRegistros(fechaInicial, fechaFinal, nombreAnunciante);
                double ganancia = 0;
                for (RegistroGananciaAnuncio r : registros) {
                    ganancia += r.getGanancia();
                }
                tabla = new TablaReporteGananciaAnuncioBean(nombreAnunciante, ganancia, registros.size(), registros);
                tablas.add(tabla);
            }
            
            double gananciaTotal = 0;
            
            for (TablaReporteGananciaAnuncioBean t : tablas) {
                gananciaTotal += t.getGananciaTotal();
            }
            
            reporte = new ReporteGananciaAnuncioBean(formatearDouble(gananciaTotal), tablas);
            lista.add(reporte);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return lista;
    }
    
    private List<RegistroGananciaAnuncio> obtenerRegistros(String fechaInicial, String fechaFinal, String nombreAnunciante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroGananciaAnuncio registro = null;
        List<RegistroGananciaAnuncio> registros = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_REGISTROS);
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            stmt.setString(3, nombreAnunciante);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String fechaCompra = rs.getString("p.fecha_compra");
                String tipoAnucio = rs.getString("t.nombre");
                double precioPorDia = rs.getDouble("precio_por_dia");
                int numDias = rs.getInt("a.cantidad_dias");
                double ganancia = rs.getDouble("p.costo");
                registro = new RegistroGananciaAnuncio(fechaCompra, tipoAnucio, precioPorDia, numDias, ganancia);
                registros.add(registro);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return registros;
    }
    
}

package dao.suscripcion;

import ConexionDB.Conexion;
import comunes.Conversor;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.revistasConsumidor.MiRevistaConsumidorM;
import modelo.suscripcion.SuscripcionM;

public class SuscripcionDAO {

    private static final String SQL_ID_REVISTAS_SUSCRITAS = "SELECT id_revista FROM suscripcion WHERE nombre_usuario=? & vigente=1";
    private static final String SQL_INSERTAR = "INSERT INTO suscripcion(id_tipo_pago,id_revista,fecha_suscripcion,fecha_finalizacion,nombre_usuario,costo_total,ganancia_editor,cuota_por_servicio,vigente)"
            + " VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_REVISTAS_SUSCRITAS = "SELECT r.nombre, p.nombre_usuario, s.fecha_suscripcion, s.fecha_finalizacion, r.id_revista FROM revista r JOIN publicacion p ON(r.id_revista=p.id_revista) JOIN suscripcion s ON(r.id_revista=s.id_revista) WHERE s.vigente=1 & s.nombre_usuario=?";

    public void registrarSuscripcion(SuscripcionM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setInt(1, modelo.getIdTipoPago());
            stmt.setInt(2, modelo.getIdRevista());
            stmt.setString(3, modelo.getFechaSuscripcion());
            stmt.setString(4, modelo.getFechaFinalizacion());
            stmt.setString(5, modelo.getNombreUsuario());
            stmt.setDouble(6, modelo.getCostoTotal());
            stmt.setDouble(7, modelo.getGananciaEditor());
            stmt.setDouble(8, modelo.getCuotaPorServicio());
            stmt.setInt(9, modelo.getVigente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el volumen en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public List<Integer> listarIdRevistasSuscritas(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ID_REVISTAS_SUSCRITAS);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_revista");
                list.add(id);
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrio un erro al listar categorias");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }

    public List<MiRevistaConsumidorM> listarRevistasSuscritas(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<MiRevistaConsumidorM> list = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_REVISTAS_SUSCRITAS);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idRevista = rs.getInt("r.id_revista");
                String nombreRevista = rs.getString("r.nombre");
                String autor = rs.getString("p.nombre_usuario");
                String fechaSuscripcion = rs.getString("s.fecha_suscripcion");
                String fechaFinalizacion = rs.getString("s.fecha_finalizacion");
                List<String> categorias = new CategoriaRevistaDAO().obtenerCetegoriasRevista(idRevista);
                List<String> etiquetas = new EtiquetaRevistaDAO().obtenerEtiquetasRevista(idRevista);
                MiRevistaConsumidorM revista = new MiRevistaConsumidorM(nombreRevista, autor, Conversor.formatearFechaEnAEs(fechaSuscripcion), Conversor.formatearFechaEnAEs(fechaFinalizacion), Conversor.ListToArray(categorias), Conversor.ListToArray(etiquetas), idRevista);
                list.add(revista);
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrio un erro al listar categorias");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return list;
    }
}

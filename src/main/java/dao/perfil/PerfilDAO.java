package dao.perfil;

import ConexionDB.Conexion;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.perfil.FotoM;
import modelo.perfil.PerfilM;

public class PerfilDAO {

    //Querys
    private static final String SQL_INSERTAR = "INSERT INTO perfil(nombre_usuario,hobbies,descripcion,gustos,foto) "
            + "VALUES(?,?,?,?,?)";
    private static final String SQL_ACTUALIZAR= "UPDATE perfil SET hobbies=? ,descripcion=? ,gustos=?,foto=? WHERE nombre_usuario=?";
    private static final String SQL_ENCONTRAR = "SELECT hobbies,descripcion,gustos,foto FROM perfil WHERE nombre_usuario=?";

    public void insertar(PerfilM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);
            stmt.setString(1, modelo.getNombreUsuario());
            stmt.setString(2, modelo.getHobbies());
            stmt.setString(3, modelo.getDescripcion());
            stmt.setString(4, modelo.getGustos());
            stmt.setString(5, modelo.getFoto().getContenido());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el perfil en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
    
    public void actualizar(PerfilM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ACTUALIZAR);
            stmt.setString(1, modelo.getHobbies());
            stmt.setString(2, modelo.getDescripcion());
            stmt.setString(3, modelo.getGustos());
            stmt.setString(4, modelo.getFoto().getContenido());
            stmt.setString(5, modelo.getNombreUsuario());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el perfil en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }
    
    
    
    

    public PerfilM encontrar(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PerfilM modelo = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                String hobbies = rs.getString("hobbies");
                String gustos = rs.getString("gustos");
                Blob foto = rs.getBlob("foto");
                String fotoS = new String(foto.getBytes(1l, (int) foto.length()));
                FotoM fotoM = new FotoM(hobbies, fotoS);
                modelo = new PerfilM(descripcion, hobbies, gustos, fotoM);
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

    public boolean existe(String nombreUsuario) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar perfil en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }

        return existe;
    }
}

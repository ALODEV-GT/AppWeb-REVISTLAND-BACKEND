package dao.autenticacion;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.autenticacion.UsuarioM;

public class UsuarioDAO {

    //Querys
    private static final String SQL_INSERTAR_USUARIO = "INSERT INTO usuario(nombre,contrasena,id_tipo_usuario) VALUES(?,?,?)";
    private static final String SQL_BUSCAR_NOMBRE = "SELECT nombre FROM usuario WHERE nombre=?";
    private static final String SQL_ENCONTRAR = "SELECT nombre, contrasena, id_tipo_usuario FROM usuario WHERE nombre=? && contrasena=?";
    private static final String SQL_TIENE_PEFIL = "SELECT * FROM perfil WHERE nombre_usuario=?";

    public void insertar(UsuarioM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_USUARIO);
            stmt.setString(1, modelo.getNombre());
            stmt.setString(2, modelo.getContrasena());
            stmt.setInt(3, modelo.getIdTipoCuenta());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el usuario en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public boolean existeNombre(String nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_BUSCAR_NOMBRE);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar nombre en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return existe;
    }

    public UsuarioM encontrar(UsuarioM usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getContrasena());
            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario.setIdTipoCuenta(rs.getInt("id_tipo_usuario"));
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar nombre en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return usuario;
    }

    public boolean tienePerfil(String nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean tiene = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TIENE_PEFIL);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();

            while (rs.next()) {
                tiene = true;
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar nombre en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return tiene;
    }

}

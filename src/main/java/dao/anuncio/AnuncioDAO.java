package dao.anuncio;

import ConexionDB.Conexion;
import dao.publicar.ArchivoDAO;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Anuncio.AnuncioM;
import modelo.Anuncio.TextoAnuncioM;
import modelo.archivo.ArchivoM;
import modelo.perfil.FotoM;

public class AnuncioDAO {

    private static final String SQL_INSERTAR_TEXTO_ANUNCIO = "INSERT INTO texto_anuncio(contenido) VALUES(?)";
    private static final String SQL_OBTENER_ID_ULTIMO_TEXTO_INSERTADO = "SELECT id_texto_anuncio FROM texto_anuncio ORDER BY id_texto_anuncio DESC LIMIT 1";
    private static final String SQL_OBTENER_ID_ULTIMO_ANUNCIO_INSERTADO = "SELECT id_anuncio FROM anuncio ORDER BY id_anuncio DESC LIMIT 1";
    private static final String SQL_INSERTAR_ANUNCIO_TIPO_TEXTO = "INSERT INTO anuncio(id_tipo_anuncio,id_texto_anuncio,activado,cantidad_dias) VALUES(?,?,0,?)";
    private static final String SQL_INSERTAR_ANUNCIO_TIPO_IMAGEN = "INSERT INTO anuncio(id_tipo_anuncio,id_archivo,activado,cantidad_dias) VALUES(?,?,0,?)";
    private static final String SQL_INSERTAR_ANUNCIO_TIPO_VIDEO = "INSERT INTO anuncio(id_tipo_anuncio,id_video_anuncio,activado,cantidad_dias) VALUES(?,?,0,?)";
    private static final String SQL_INSERTAR_LINK_ANUNCIO = "INSERT INTO link_video_anuncio(link) VALUES(?)";
    private static final String SQL_OBTENER_ID_ULTIMO_LINK_INSERTADO = "SELECT id_video_anuncio FROM link_video_anuncio ORDER BY id_video_anuncio DESC LIMIT 1";

    private static final String SQL_INSERTAR_IMAGEN_ANUNCIO = "INSERT INTO imagen_anuncio(imagen) VALUES(?)";
    private static final String SQL_OBTENER_ID_ULTIMA_IMAGEN_INSERTADA = "SELECT id_imagen_anuncio FROM imagen_anuncio ORDER BY id_imagen_anuncio DESC LIMIT 1";

    private static final String SQL_ENCONTRAR_ANUNCIO = "SELECT id_tipo_anuncio,id_archivo,id_texto_anuncio,id_video_anuncio FROM anuncio WHERE id_anuncio=?";

    private static final String SQL_ENCONTRAR_ID_TEXTO_ANUNCIO = "SELECT id_texto_anuncio FROM anuncio WHERE id_anuncio=?";
    private static final String SQL_ENCONTRAR_ID_IMAGEN_ANUNCIO = "SELECT id_archivo FROM anuncio WHERE id_anuncio=?";
    private static final String SQL_ENCONTRAR_ID_VIDEO_ANUNCIO = "SELECT id_video_anuncio FROM anuncio WHERE id_anuncio=?";

    private static final String SQL_ENCONTRAR_TEXTO = "SELECT contenido FROM texto_anuncio WHERE id_texto_anuncio=?";
    private static final String SQL_ENCONTRAR_VIDEO = "SELECT link FROM link_video_anuncio WHERE id_video_anuncio=?";
    private static final String SQL_ENCONTRAR_IMAGEN = "SELECT imagen FROM imagen_anuncio WHERE id_imagen_anuncio=?";

    public void insertarImagenAnuncio(FotoM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_IMAGEN_ANUNCIO);
            stmt.setString(1, modelo.getContenido());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public int obtenerIdUltimaImagenInsertada() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ULTIMA_IMAGEN_INSERTADA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_imagen_anuncio");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

    public TextoAnuncioM encontrarTextoAnuncio(int idAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TextoAnuncioM texto = null;
        int idTextoAnuncio = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_ID_TEXTO_ANUNCIO);
            stmt.setInt(1, idAnuncio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idTextoAnuncio = rs.getInt("id_texto_anuncio");
            }

            stmt = conn.prepareStatement(SQL_ENCONTRAR_TEXTO);
            stmt.setInt(1, idTextoAnuncio);
            rs = stmt.executeQuery();
            while (rs.next()) {
                texto = new TextoAnuncioM(rs.getString("contenido"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return texto;
    }

    public FotoM encontrarImagenAnuncio(int idAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FotoM foto = null;
        int idImagen = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_ID_IMAGEN_ANUNCIO);
            stmt.setInt(1, idAnuncio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idImagen = rs.getInt("id_archivo");
            }

            stmt = conn.prepareStatement(SQL_ENCONTRAR_IMAGEN);
            stmt.setInt(1, idImagen);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Blob archivo = rs.getBlob("imagen");
                String bytes = new String(archivo.getBytes(1l, (int) archivo.length()));
                foto = new FotoM("", bytes);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return foto;
    }

    public TextoAnuncioM encontrarVideoAnuncio(int idAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TextoAnuncioM link = null;
        int idVideoAnuncio = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_ID_VIDEO_ANUNCIO);
            stmt.setInt(1, idAnuncio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idVideoAnuncio = rs.getInt("id_video_anuncio");
            }

            stmt = conn.prepareStatement(SQL_ENCONTRAR_VIDEO);
            stmt.setInt(1, idVideoAnuncio);
            rs = stmt.executeQuery();
            while (rs.next()) {
                link = new TextoAnuncioM(rs.getString("link"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return link;
    }

    public void insertarTextoAnuncio(String textoAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_TEXTO_ANUNCIO);
            stmt.setString(1, textoAnuncio);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public int obtenerIdUltimoTextoInsertado() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ULTIMO_TEXTO_INSERTADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_texto_anuncio");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

    public void insertarLinkVideoAnuncio(String linkAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR_LINK_ANUNCIO);
            stmt.setString(1, linkAnuncio);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public int obtenerIdUltimoLinkInsertado() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ULTIMO_LINK_INSERTADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_video_anuncio");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

    public void insertarAnuncio(AnuncioM modelo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();

            String query = "";
            int idContenido = 0;

            switch (modelo.getIdTipoAnuncio()) {
                case 1:
                    query = SQL_INSERTAR_ANUNCIO_TIPO_TEXTO;
                    idContenido = modelo.getIdTextoAnuncio();
                    break;
                case 2:
                    query = SQL_INSERTAR_ANUNCIO_TIPO_IMAGEN;
                    idContenido = modelo.getIdArchivo();
                    break;
                case 3:
                    query = SQL_INSERTAR_ANUNCIO_TIPO_VIDEO;
                    idContenido = modelo.getIdLinkVideo();
            }

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, modelo.getIdTipoAnuncio());
            stmt.setInt(2, idContenido);
            stmt.setInt(3, modelo.getCantidadDias());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
        }
    }

    public int obtenerIdUltimoAnuncioInsertado() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_ID_ULTIMO_ANUNCIO_INSERTADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_anuncio");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return id;
    }

    public AnuncioM encontrarModeloParaMostrarAnuncio(int idAnuncio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AnuncioM anuncio = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENCONTRAR_ANUNCIO);
            stmt.setInt(1, idAnuncio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idTipoAnuncio = rs.getInt("id_tipo_anuncio");
                int idArchivo = rs.getInt("id_archivo");
                int idTextoAnuncio = rs.getInt("id_texto_anuncio");
                int idVideoAnuncio = rs.getInt("id_video_anuncio");
                anuncio = new AnuncioM(idTipoAnuncio, idTextoAnuncio, idArchivo, idVideoAnuncio, idAnuncio);
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar nombre en la base de datos");
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return anuncio;
    }

}

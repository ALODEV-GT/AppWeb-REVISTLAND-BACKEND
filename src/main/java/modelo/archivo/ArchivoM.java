package modelo.archivo;

import java.io.InputStream;

public class ArchivoM {

    private String nombreArchivo;
    InputStream contenido;

    public ArchivoM() {
    }

    public ArchivoM(String nombreArchivo, InputStream contenido) {
        this.nombreArchivo = nombreArchivo;
        this.contenido = contenido;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public InputStream getContenido() {
        return contenido;
    }

    public void setContenido(InputStream contenido) {
        this.contenido = contenido;
    }

}

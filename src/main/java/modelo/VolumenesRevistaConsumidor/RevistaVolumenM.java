package modelo.VolumenesRevistaConsumidor;

import modelo.interacciones.ComentarioM;
import modelo.publicarRevista.VolumenM;

public class RevistaVolumenM {

    private String nombreRevista;
    private VolumenM[] volumenes;
    private int numComentarios;
    private int numMeGustas;
    private ComentarioM[] comentarios;

    public RevistaVolumenM() {
    }

    public RevistaVolumenM(String nombreRevista, VolumenM[] volumenes, int numComentarios, int numMeGustas, ComentarioM[] comentarios) {
        this.nombreRevista = nombreRevista;
        this.volumenes = volumenes;
        this.numComentarios = numComentarios;
        this.numMeGustas = numMeGustas;
        this.comentarios = comentarios;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public VolumenM[] getVolumenes() {
        return volumenes;
    }

    public void setVolumenes(VolumenM[] volumenes) {
        this.volumenes = volumenes;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public int getNumMeGustas() {
        return numMeGustas;
    }

    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }

    public ComentarioM[] getComentarios() {
        return comentarios;
    }

    public void setComentarios(ComentarioM[] comentarios) {
        this.comentarios = comentarios;
    }

}

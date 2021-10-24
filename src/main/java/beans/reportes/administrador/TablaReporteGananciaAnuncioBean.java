package beans.reportes.administrador;

import beans.registro.administrador.RegistroGananciaAnuncio;
import java.util.List;

public class TablaReporteGananciaAnuncioBean {

    private String nombreAnunciante;
    private double gananciaTotal;
    private int numAnuncios;
    private List<RegistroGananciaAnuncio> registros;

    public TablaReporteGananciaAnuncioBean() {
    }

    public TablaReporteGananciaAnuncioBean(String nombreAnunciante, double gananciaTotal, int numAnuncios, List<RegistroGananciaAnuncio> registros) {
        this.nombreAnunciante = nombreAnunciante;
        this.gananciaTotal = gananciaTotal;
        this.numAnuncios = numAnuncios;
        this.registros = registros;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public int getNumAnuncios() {
        return numAnuncios;
    }

    public void setNumAnuncios(int numAnuncios) {
        this.numAnuncios = numAnuncios;
    }

    public List<RegistroGananciaAnuncio> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroGananciaAnuncio> registros) {
        this.registros = registros;
    }

}

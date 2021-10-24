package beans.reportes.administrador;

import java.util.List;

public class ReporteGananciaTotalBean {

    private double gananciaTotalRevistas;
    private double gananciaTotalAnuncios;
    private double gananciaTotal;
    private List<TablaReporteGananciaRevistaBean> registrosRevista;
    private List<TablaReporteGananciaAnuncioBean> registrosAnuncio;

    public ReporteGananciaTotalBean() {
    }

    public ReporteGananciaTotalBean(double gananciaTotalRevistas, double gananciaTotalAnuncios, double gananciaTotal, List<TablaReporteGananciaRevistaBean> registrosRevista, List<TablaReporteGananciaAnuncioBean> registrosAnuncio) {
        this.gananciaTotalRevistas = gananciaTotalRevistas;
        this.gananciaTotalAnuncios = gananciaTotalAnuncios;
        this.gananciaTotal = gananciaTotal;
        this.registrosRevista = registrosRevista;
        this.registrosAnuncio = registrosAnuncio;
    }

    public double getGananciaTotalRevistas() {
        return gananciaTotalRevistas;
    }

    public void setGananciaTotalRevistas(double gananciaTotalRevistas) {
        this.gananciaTotalRevistas = gananciaTotalRevistas;
    }

    public double getGananciaTotalAnuncios() {
        return gananciaTotalAnuncios;
    }

    public void setGananciaTotalAnuncios(double gananciaTotalAnuncios) {
        this.gananciaTotalAnuncios = gananciaTotalAnuncios;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public List<TablaReporteGananciaRevistaBean> getRegistrosRevista() {
        return registrosRevista;
    }

    public void setRegistrosRevista(List<TablaReporteGananciaRevistaBean> registrosRevista) {
        this.registrosRevista = registrosRevista;
    }

    public List<TablaReporteGananciaAnuncioBean> getRegistrosAnuncio() {
        return registrosAnuncio;
    }

    public void setRegistrosAnuncio(List<TablaReporteGananciaAnuncioBean> registrosAnuncio) {
        this.registrosAnuncio = registrosAnuncio;
    }

}

package beans.reportes.administrador;

import java.util.List;

public class ReporteGananciaAnuncioBean {

    private double gnanciaTotal;
    private List<TablaReporteGananciaAnuncioBean> tablas;

    public ReporteGananciaAnuncioBean() {
    }

    public ReporteGananciaAnuncioBean(double gnanciaTotal, List<TablaReporteGananciaAnuncioBean> tablas) {
        this.gnanciaTotal = gnanciaTotal;
        this.tablas = tablas;
    }

    public double getGnanciaTotal() {
        return gnanciaTotal;
    }

    public void setGnanciaTotal(double gnanciaTotal) {
        this.gnanciaTotal = gnanciaTotal;
    }

    public List<TablaReporteGananciaAnuncioBean> getTablas() {
        return tablas;
    }

    public void setTablas(List<TablaReporteGananciaAnuncioBean> tablas) {
        this.tablas = tablas;
    }

}

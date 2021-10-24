package beans.reportes.editor;

import java.util.List;

public class ReporteGananciaTotalBean {

    private double gananciaTotal;
    private List<TablaReporteGananciaTotalBean> tablas;

    public ReporteGananciaTotalBean() {
    }

    public ReporteGananciaTotalBean(double gananciaTotal, List<TablaReporteGananciaTotalBean> tablas) {
        this.gananciaTotal = gananciaTotal;
        this.tablas = tablas;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public List<TablaReporteGananciaTotalBean> getTablas() {
        return tablas;
    }

    public void setTablas(List<TablaReporteGananciaTotalBean> tablas) {
        this.tablas = tablas;
    }

}

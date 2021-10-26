package beans.reportes.administrador;

import java.util.List;

public class ReporteGananciaRevistaBean {

    private double ingresoTotal;
    private double costoTotal;
    private double gananciaTotal;
    private List<TablaReporteGananciaRevistaBean> tabla;

    public ReporteGananciaRevistaBean() {
    }

    public ReporteGananciaRevistaBean(double ingresoTotal, double costoTotal, double gananciaTotal, List<TablaReporteGananciaRevistaBean> tabla) {
        this.ingresoTotal = ingresoTotal;
        this.costoTotal = costoTotal;
        this.gananciaTotal = gananciaTotal;
        this.tabla = tabla;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public List<TablaReporteGananciaRevistaBean> getTabla() {
        return tabla;
    }

    public void setTabla(List<TablaReporteGananciaRevistaBean> tabla) {
        this.tabla = tabla;
    }

}

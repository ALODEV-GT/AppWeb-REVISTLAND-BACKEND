package funcionamiento.suscripcion;

import dao.generales.PorcentajeGananciaDAO;
import modelo.suscripcion.SuscripcionM;

public class CalculoPago {

    public CalculoPago() {
    }
    
    public void calcularPago(SuscripcionM modelo) {
        double costoTotal = modelo.getCostoTotal();
        modelo.setGananciaEditor(pagoEditor(costoTotal));
        modelo.setCuotaPorServicio(costoServicio(costoTotal));
    }
    
    private double pagoEditor(double costoTotal) {
        double porcentaje = new PorcentajeGananciaDAO().obtenerPorcentajeGanancia();
        porcentaje = porcentaje / 100;
        double pagoEditor = costoTotal - (costoTotal * porcentaje);
        return pagoEditor;
    }
    
    private double costoServicio(double costoTotal) {
        return costoTotal - this.pagoEditor(costoTotal);
    }
}

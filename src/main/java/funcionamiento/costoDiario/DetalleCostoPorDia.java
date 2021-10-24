package funcionamiento.costoDiario;

import dao.fechaGlobal.FechaGlobalDAO;
import dao.publicar.RevistaDAO;
import dao.publicar.costoPorDia.DetalleCostoPorDiaDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetalleCostoPorDia {

    LocalDate nuevaFecha;

    public DetalleCostoPorDia(LocalDate nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public void agregarRegistrosDetallePorDia() {
        LocalDate fechaActual = LocalDate.parse(new FechaGlobalDAO().obtenerFecha().getFecha());
        if (nuevaFecha.isAfter(fechaActual)) {
            List<Integer> idRevistas = new RevistaDAO().obtenerIdRevistas();

            DetalleCostoPorDiaDAO daoDCPD = new DetalleCostoPorDiaDAO();
            for (Integer idRevista : idRevistas) {
                LocalDate fechaUltimoRegistro = daoDCPD.obtenerFechaUltimoDetalle(idRevista);
                ArrayList<String> fechas = new ArrayList<>();
                for (LocalDate date = fechaUltimoRegistro.plusDays(1); date.isBefore(nuevaFecha); date = date.plusDays(1)) {
                    fechas.add(date.toString());
                }
                daoDCPD.agregarDetalles(fechas, idRevista);
            }
        }
    }

}

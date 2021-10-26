package beans.reportes.administrador;

import beans.comunes.DatosRevista;
import beans.registro.comunes.RegistroComentario;
import java.util.List;

public class ReporteMasComentadaBean {

    private DatosRevista datosRevista;
    private int numComentarios;
    private List<RegistroComentario> comentarios;

    public ReporteMasComentadaBean() {
    }

    public ReporteMasComentadaBean(DatosRevista datosRevista, int numComentarios, List<RegistroComentario> comentarios) {
        this.datosRevista = datosRevista;
        this.numComentarios = numComentarios;
        this.comentarios = comentarios;
    }

    public DatosRevista getDatosRevista() {
        return datosRevista;
    }

    public void setDatosRevista(DatosRevista datosRevista) {
        this.datosRevista = datosRevista;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public List<RegistroComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<RegistroComentario> comentarios) {
        this.comentarios = comentarios;
    }
}

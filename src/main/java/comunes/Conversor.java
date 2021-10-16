package comunes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import modelo.interacciones.ComentarioM;
import modelo.publicarRevista.VolumenM;

public class Conversor {

    public static String[] ListToArray(List<String> lista) {
        String[] array = new String[lista.size()];
        array = lista.toArray(array);
        return array;
    }
    
    public static VolumenM[] ListToArrayVolumenM(List<VolumenM> lista) {
        VolumenM[] array = new VolumenM[lista.size()];
        array = lista.toArray(array);
        return array;
    }
    
    public static ComentarioM[] ListToArrayComentarioM(List<ComentarioM> lista) {
        ComentarioM[] array = new ComentarioM[lista.size()];
        array = lista.toArray(array);
        return array;
    }
    
    public static String formatearFechaEnAEs(String fechaString)  throws DateTimeException{
        DateTimeFormatter formatoARecibir = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoADevolver = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatoARecibir);
        String fechaFormateada = fecha.format(formatoADevolver);
        return fechaFormateada;
    }
    
    
}

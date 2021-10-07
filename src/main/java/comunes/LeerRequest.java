package comunes;

import java.io.BufferedReader;
import java.io.IOException;

public class LeerRequest {

    public static String obtenerContenido(BufferedReader reader) {
        String contenido = "";
        String linea;
        try {

            while ((linea = reader.readLine()) != null) {
                contenido += linea;
            }

            return contenido;
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error al Leer");
            ex.printStackTrace(System.out);
        }
        return contenido;
    }
}

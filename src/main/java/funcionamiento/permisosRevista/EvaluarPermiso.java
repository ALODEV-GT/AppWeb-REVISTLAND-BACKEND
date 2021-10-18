package funcionamiento.permisosRevista;

public class EvaluarPermiso {

    public static boolean intABoolean(int numero) {
        boolean permitir = false;
        if (numero != 0) {
            permitir = true;
        }
        return permitir;
    }

    public static int booleanAInt(boolean bol) {
        int numero = 0;

        if (bol) {
            numero = 1;
        }
        return numero;
    }
}

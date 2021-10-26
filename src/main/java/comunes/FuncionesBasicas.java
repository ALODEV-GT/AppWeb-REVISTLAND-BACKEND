package comunes;

import java.text.DecimalFormat;

public class FuncionesBasicas {

    public static double formatearDouble(double numero) {
        DecimalFormat formater = new DecimalFormat("#.00");
        return Double.valueOf(formater.format(numero));
    }
}

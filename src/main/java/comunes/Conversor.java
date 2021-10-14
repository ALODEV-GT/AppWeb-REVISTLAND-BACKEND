package comunes;

import java.util.List;

public class Conversor {

    public static String[] ListOfArray(List<String> lista) {
        String[] array = new String[lista.size()];
        array = lista.toArray(array);
        return array;
    }
}

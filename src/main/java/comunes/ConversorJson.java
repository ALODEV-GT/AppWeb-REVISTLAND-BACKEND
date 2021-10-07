package comunes;

import com.google.gson.Gson;

public class ConversorJson<T> {

    private Gson gson = new Gson();

    public T convertirJsonAObject(String contenido, Class<T> tipo){
        T objeto= gson.fromJson(contenido, tipo);
        return objeto;
    }
    
    public String convertirObjectAJson(T object, Class<T> tipo){
        String json = gson.toJson(object , tipo);
        return json;
    }
}

package test;

import comunes.ConversorJson;
import modelo.autenticacion.UsuarioM;

public class test {
    public static void main(String[] args) {
        String contenido = "{\"nombre\":\"alexander\",\"contrasena\":\"contra123\"}";
        
        ConversorJson<UsuarioM> conv = new ConversorJson();
        UsuarioM us = conv.convertirJsonAObject(contenido, UsuarioM.class);

        ConversorJson<UsuarioM> conv2 = new ConversorJson<>();
        String json= conv2.convertirObjectAJson(us, UsuarioM.class);
        System.out.println("Json: " + json);
        
    }
}

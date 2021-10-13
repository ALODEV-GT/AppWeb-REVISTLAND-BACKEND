package test;

import comunes.ConversorJson;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.autenticacion.UsuarioM;

public class test {
    public static void main(String[] args) {
        
        Set<Integer> miSet = new HashSet();
        miSet.add(1);
        miSet.add(2);
        miSet.add(3);
        miSet.add(4);
        miSet.add(5);
        
        for(Integer i: miSet){
            System.out.println(i);
        }
        
        List<Integer> suscritas = new ArrayList<>();
        suscritas.add(3);
        suscritas.add(5);
        suscritas.add(8);
        suscritas.add(9);
        
        quitarRevistasSuscritas(miSet, suscritas);
        System.out.println("\n\nLimpiado\n");
        for(Integer i: miSet){
            System.out.println(i);
        }
        
    }
    
    private static void quitarRevistasSuscritas(Set<Integer> coicidencias, List<Integer> suscritas){
         for(Integer i: suscritas){
            coicidencias.remove(i);
        }
    }
}

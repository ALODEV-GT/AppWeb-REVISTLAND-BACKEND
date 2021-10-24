package test;

import comunes.ConversorJson;
import dao.publicar.CategoriaRevistaDAO;
import dao.publicar.EtiquetaRevistaDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.autenticacion.UsuarioM;

public class test {

    public static void main(String[] args) {
        
        LocalDate fechaInicial = LocalDate.of(2021, 1, 1);
        System.out.println("fechaInicial = " + fechaInicial);
        LocalDate fechaFinal = LocalDate.of(2021, 12, 31);
        System.out.println("fechaFinal = " + fechaFinal.toString());
        
//        System.out.println(fechaFinal.isAfter(fechaInicial));
//        
//        for (LocalDate date = fechaInicial.plusDays(1); date.isBefore(fechaFinal); date = date.plusDays(1)) {
//            System.out.println("date = " + date);
//        }
        
    }

//        Set<Integer> miSet = new HashSet();
//        miSet.add(1);
//        miSet.add(2);
//        miSet.add(3);
//        miSet.add(4);
//        miSet.add(5);
//        
//        for(Integer i: miSet){
//            System.out.println(i);
//        }
//        
//        List<Integer> suscritas = new ArrayList<>();
//        suscritas.add(3);
//        suscritas.add(5);
//        suscritas.add(8);
//        suscritas.add(9);
//        
//        quitarRevistasSuscritas(miSet, suscritas);
//        System.out.println("\n\nLimpiado\n");
//        for(Integer i: miSet){
//            System.out.println(i);
//        }
//        
//    }
//    
//    private static void quitarRevistasSuscritas(Set<Integer> coicidencias, List<Integer> suscritas){
//         for(Integer i: suscritas){
//            coicidencias.remove(i);
//        }
//    }
}

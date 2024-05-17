package Vista;


import Model.Player;
import Model.Players_stats;

import java.util.List;

public class Vista {
   public static void imprimirMensaje(String mensaje){
       System.out.println(mensaje);
   }

   public static void imprimirMensajeSeguido(String mensaje){
       System.out.print(mensaje);
   }
   public static void imprimirPlayerStats(List<Players_stats> lista){
       System.out.printf("%-4s%-13s%-14s%-18s","id","avg puntos","avg rebotes","avg asistencies");
       System.out.println();
       for (int i = 0; i < 50; i++) {
           System.out.print("-");
       }
       System.out.println();
       for (Players_stats player : lista) {
           System.out.printf("%-4d%-13.2f%-14.2f%-18.2f\n",player.getId_jugador(),player.getAvg_puntos(),player.getAvg_rebotes(),player.getAvg_asistencias());
       }
   }

   public static void imprimirPlayers(List<Player> lista){
       System.out.printf("%-4s%-20s%-8s%-8s%-15s","id","nom","alçada","pes","equip_actual");
       System.out.println();
       for (int i = 0; i < 55; i++) {
           System.out.print("-");
       }

       System.out.println();
       for (Player player : lista) {
           System.out.printf("%-4d%-20s%-8d%-8d%-15d\n",player.getId(),player.getNom(),player.getAlcada(),player.getPes(),player.getEquip_actual());
       }
   }

   public static <T> void recorrerLista(List<T> lista){
       for (T element : lista) {
           System.out.println(element);
       }
   }
}

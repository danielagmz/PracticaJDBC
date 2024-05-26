package Vista;



import Model.Player;
import Model.Players_stats;
import Model.PlayerMatches;
import java.util.List;

public class Vista {
   public static void imprimirMensaje(String mensaje){
       System.out.println(mensaje);
   }

   public static void imprimirMensajeSeguido(String mensaje){
       System.out.print(mensaje);
   }
   public static void imprimirPlayerStats(List<Players_stats> lista){
       System.out.printf("%-13s%-14s%-18s","avg puntos","avg rebotes","avg asistencies");
       System.out.println();
       for (int i = 0; i < 50; i++) {
           System.out.print("-");
       }
       System.out.println();
       for (Players_stats player : lista) {
           System.out.printf("%-13.2f%-14.2f%-18.2f\n",player.getAvg_puntos(),player.getAvg_rebotes(),player.getAvg_asistencias());
       }
   }

//    public static void imprimirPlayerResult(List<String> partido,List<PlayerMatches> lista){
//        System.out.printf("%-20s%-10s%-10s%-10s","partido","Puntos","Rebotes","Asistencies");
//        System.out.println();
//        for (int i = 0; i < 50; i++) {
//            System.out.print("-");
//        }
//        System.out.println();
//        for (String s : partido) {
//            for (PlayerMatches player : lista) {
//                System.out.printf(" %-20s%-10d%-10d%-10d\n",s,player.getPunts(),player.getRebots(),player.getAssist());
//            }
//        }
//    }
    public static void imprimirPlayerResult(List<String> partidos, List<PlayerMatches> lista) {
        System.out.printf("\t%-20s%-10s%-10s%-10s\n","partido","Puntos","Rebotes","Asistencies");
        for (int i = 0; i < partidos.size(); i++) {
            String partido = partidos.get(i);
            PlayerMatches player = lista.get(i);
            System.out.printf("%-5d%s %d %d %d\n",i+1, partido, player.getPunts(), player.getRebots(), player.getAssist());
        }
    }

   public static void imprimirPlayers(List<Player> lista){
       System.out.printf("%-30s%-8s%-8s","nom","alçada","pes");
       System.out.println();
       for (int i = 0; i < 55; i++) {
           System.out.print("-");
       }

       System.out.println();
       for (Player player : lista) {
           System.out.printf("%-30s%-8d%-8d\n",player.getNom(),player.getAlcada(),player.getPes());
       }
   }

   public static <T> void recorrerLista(List<T> lista){
       for (T element : lista) {
           System.out.println(element);
       }
   }

   public static void impPartidosJugados(List<String> partidos) {
       System.out.println();
       imprimirMensajeSeguido("""
               Partidos jugados
               ----------------
                   """);
       recorrerLista(partidos);
   }

   public static <T> void mostrarGenerico(T obj,boolean ln){
       if (ln){
           System.out.println(obj);
       }else{
           System.out.print(obj);
       }

   }

   public static void saltoLinea(){
       System.out.println();
   }
}

package Vista;


import Model.Player;
import Model.PlayerMatches;
import Model.Players_stats;

import java.util.List;

public class Vista {
    /**
     * imprime un mensaje por consola mensaje con un print ln
     * @param mensaje mensaje a mostrar
     */
   public static void imprimirMensaje(String mensaje){
       System.out.println(mensaje);
   }

    /**
     * imprime un mendaje con espacios
     * @param mensaje mensaje a imprimir
     */
   public static void imprimirMensajeSeguido(String mensaje){
       System.out.print(mensaje);
   }

    /**
     * imprime por consola los stats con un formato especifico
     * @param lista lista con los stats
     */
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
    public static void imprimirPlayerResult(List<String> partidos, List<PlayerMatches> lista) {
        System.out.printf("%-5s%-20s%-10s%-10s%-10s\n","","partido","Puntos","Rebotes","Asistencies");
        for (int i = 0; i < partidos.size(); i++) {
            String partido = partidos.get(i);
            PlayerMatches player = lista.get(i);
            System.out.printf("%-5d%-20s%-10d%-10d%-10d\n",i+1, partido, player.getPunts(), player.getRebots(), player.getAssist());
        }
    }

    /**
     * imprime por consola los datos basicos de los jugadores
     * @param lista lista de jugadores
     */
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

    /**
     * metodo generico para recorerr un array
     * @param lista lista a recorrer
     * @param <T> tipo generico de dato de la lista
     */
   public static <T> void recorrerLista(List<T> lista){
       for (T element : lista) {
           System.out.println(element);
       }
   }

    /**
     * imprime los partidos obtenidos con un titulo especifico
     * @param partidos lista de partidos a recorrer
     */
   public static void impPartidosJugados(List<String> partidos) {
       System.out.println();
       imprimirMensajeSeguido("""
               Partidos jugados
               ----------------
                   """);
       recorrerLista(partidos);
   }

    /**
     * mostrar un objeto generico con o sin salto de linea
     * @param obj objeto a mostrar
     * @param ln indica si tiene o no saltod e linea
     * @param <T> dato generico
     */
   public static <T> void mostrarGenerico(T obj,boolean ln){
       if (ln){
           System.out.println(obj);
       }else{
           System.out.print(obj);
       }

   }

    /**
     * salto de linea en consola
     */
   public static void saltoLinea(){
       System.out.println();
   }

    /**
     * Funcion para imprimir las opciones de equipo o jugadores
     * @param variable Parametro para indicar de si son jugadores o equipos
     * @param jugEqup Le pasamos el ArrayList de los jugadores o equipos
     */
    public static void mostrarOpciones(String variable,List<String> jugEqup) {
        System.out.println(variable + " encontrados:");
        for (int i = 0; i < jugEqup.size(); i++) {
            System.out.println((i + 1) + ". " + jugEqup.get(i));
        }
    }
    public static void mostrarOpcionesFranquicia(List<String> franquicias) {
        System.out.println("Franquicias encontradas:");
        for (int i = 0; i < franquicias.size(); i++) {
            System.out.println((i + 1) + ". " + franquicias.get(i));
        }
    }

}





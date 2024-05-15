package Generador_datos;

import megaLibreria.utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
    private static final String[] nbaTeams = {"Atlanta Hawks", "Boston Celtics", "Brooklyn Nets", "Charlotte Hornets", "Chicago Bulls", "Cleveland Cavaliers", "Dallas Mavericks", "Denver Nuggets", "Detroit Pistons", "Golden State Warriors", "Houston Rockets", "Indiana Pacers", "Los Angeles Clippers", "Los Angeles Lakers", "Memphis Grizzlies", "Miami Heat", "Milwaukee Bucks", "Minnesota Timberwolves", "New Orleans Pelicans", "New York Knicks"};
    private  static  final String[] nbaPlayers = {
            "LeBron James",
            "Stephen Curry",
            "Kevin Durant",
            "James Harden",
            "Giannis Antetokounmpo",
            "Joel Embiid",
            "Damian Lillard",
            "Paul George",
            "Russell Westbrook",
            "Kawhi Leonard",
            "Bradley Beal",
            "Jayson Tatum",
            "Nikola Jokic",
            "Donovan Mitchell",
            "Rudy Gobert",
            "Khris Middleton",
            "Bam Adebayo",
            "Jimmy Butler",
            "Trae Young",
            "Luka Doncic"
    };
    public static void main(final String[] args) {
        File f=new File("./players_matches.csv");
        try {
            PrintStream writer=new PrintStream(f);
            if (!f.createNewFile() && !f.exists()){
                throw new IOException("No se ha podido crear el archivo");
            }
            //jugadores
//            writer.println("nom;alcada;pes;equipo_actual");
            // matches
//            writer.println("visitante_id;puntos_visitante;local_id;puntos_local");
            // equipos
//            writer.println("equips");
            //  players_matches
                writer.println("id_match;id_jugador;puntos;rebotes;asistencias");
            for (int i = 0; i < 20; i++) {
                // jugadores
//                writer.printf("%s;%d;%d;%d\n",nbaPlayers[i],obtenerAltura(),obtenerPeso(),obtenerIdEquipo());
                // matches
//                writer.printf("%d;%d;%d;%d\n",obtenerIdEquipo(),obtenerPuntosEquipo(),obtenerIdEquipo(),obtenerPuntosEquipo());
                // teams
//                writer.printf("%s;\n",nbaTeams[i]);

                // players_matches
                for (int j = 1; j <= 20; j++) {
                    for (int k = 1; k <= 10; k++) {
                        writer.printf("%d;%d;%d;%d;%d\n",j,obtenerIdJugador(),obtenerRe_Ass_Punt(),obtenerRe_Ass_Punt(),obtenerRe_Ass_Punt());
                    }
                }


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private static String obtenerJugador(){
        int num= utilities.generarNumAleatori(nbaPlayers.length-1, 0);
        return nbaPlayers[num];
    }

    private static int obtenerPeso(){
        int[] nbaPlayerHeights = { 79, 80, 80, 80, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95 };
        return nbaPlayerHeights[utilities.generarNumAleatori(nbaPlayerHeights.length-1, 0)];
    }

    private static  int obtenerAltura(){
        int[] nbaPlayerWeightsKg = {110, 120, 116, 98, 97, 93, 110, 111, 81, 106, 111, 99, 85, 111, 89, 95, 112, 122, 122, 116};
        return nbaPlayerWeightsKg[utilities.generarNumAleatori(nbaPlayerWeightsKg.length-1, 0)];
    }

    private static String obtenerEquipo(){
        return nbaTeams[utilities.generarNumAleatori(nbaTeams.length-1, 0)];
    }

    private static int obtenerIdEquipo(){

        return utilities.generarNumAleatori(nbaTeams.length-1, 0);
    }
     private static int obtenerPuntosEquipo(){
         return utilities.generarNumAleatori(30, 0);
     }

     private  static  int obtenerRe_Ass_Punt(){
         return utilities.generarNumAleatori(5, 0);
     }

     private  static  int obtenerIdJugador(){
         return utilities.generarNumAleatori(20, 0);
     }




}

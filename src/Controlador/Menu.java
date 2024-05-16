package Controlador;

import Vista.Vista;
import megaLibreria.utilities;

import java.util.Scanner;


public class Menu {
    static Scanner scan = new Scanner(System.in);
    public static void menuPrincipal(){
        int opcio;

        do {

            Vista.imprimirMensaje("------------");
            Vista.imprimirMensaje("--  Menu  --");
            Vista.imprimirMensaje("------------");
            Vista.imprimirMensaje("1) Llistar tots els jugadors d'un equip");
            Vista.imprimirMensaje("2) Calcular la mitjana de punts, rebots, assistències d'un jugador");
            Vista.imprimirMensaje("3) Llistar tots els partits jugats per un equip amb el seu resultat");
            Vista.imprimirMensaje("4) Inserir un nou jugador a un equip");
            Vista.imprimirMensaje("5) Traspassar un judador a un altra equip");
            Vista.imprimirMensaje("6) Actualitzar les dades de jugadors despres del partit");
            Vista.imprimirMensaje("7) Modificar les estadístiques d’un jugador a un partit");
            Vista.imprimirMensaje("8) Retirar un jugador.");
            Vista.imprimirMensaje("9) Canviar nom franquícia d’un equip");
            Vista.imprimirMensaje("0) acabar");

            Vista.imprimirMensajeSeguido("> ");
            // funcion que se encarga de preguntar la opcion hasta que este en un rango valido
            opcio= utilities.introducirNumeroEntero(scan, 9, 0, false);

            switch (opcio){
                case 1:
                    Controlador.ListarJugadores();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                     break;
                case 8:

                    break;
                case 9:

                    break;
                case 0:
                    System.exit(1);
                    System.out.println("Fins aviat!");
                    break;
            }
            System.out.println();
        } while (true);


    }
}

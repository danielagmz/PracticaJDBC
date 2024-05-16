package Controlador;

import megaLibreria.utilities;

import java.util.Scanner;


public class Menu {
    static Scanner scan = new Scanner(System.in);
    public static void menuPrincipal(){
        int opcio;

        do {
            System.out.println("------------");
            System.out.println("--  Menu  --");
            System.out.println("------------");
            System.out.println("1) Llistar tots els jugadors d'un equip");
            System.out.println("2) Calcular la mitjana de punts, rebots, assistències d'un jugador");
            System.out.println("3) Llistar tots els partits jugats per un equip amb el seu resultat");
            System.out.println("4) Inserir un nou jugador a un equip");
            System.out.println("5) Traspassar un judador a un altra equip");
            System.out.println("6) Actualitzar les dades de jugadors despres del partit");
            System.out.println("7) Modificar les estadístiques d’un jugador a un partit");
            System.out.println("8) Retirar un jugador.");
            System.out.println("9) Canviar nom franquícia d’un equip");
            System.out.println("0) acabar");

            System.out.print("> ");
            // funcion que se encarga de preguntar la opcion hasta que este en un rango valido
            opcio= utilities.introducirNumeroEntero(scan, 9, 0, false);

            switch (opcio){
                case 1:

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

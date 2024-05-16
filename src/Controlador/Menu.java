package Controlador;

import megaLibreria.utilities;

import java.util.Scanner;


public class Menu {
    static Scanner scan = new Scanner(System.in);
    public static void menuPrincipal(){
        int opcio;

        do {
            System.out.println("-----------");
            System.out.println("-- INICI --");
            System.out.println("-----------");
            System.out.println("1) Introduir producte");
            System.out.println("2) Passar per caixa");
            System.out.println("3) Mostrar carret de compra");
            System.out.println("0) acabar");

            System.out.print("> ");
            // funcion que se encarga de preguntar el numero enterio hasta que este en un rango valido
            opcio= utilities.introducirNumeroEntero(scan, 3, 0, false);

            switch (opcio){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

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

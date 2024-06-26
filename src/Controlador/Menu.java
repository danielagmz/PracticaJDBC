package Controlador;

import Vista.Vista;
import megaLibreria.utilities;

import java.sql.SQLException;
import java.util.Scanner;


public class Menu {
    // scanner global para los dos menu
    static Scanner scan = new Scanner(System.in);
    public static void menuPrincipal() throws SQLException {
        int opcio;

        do {

            Vista.imprimirMensaje("------------");
            Vista.imprimirMensaje("--  Menu  --");
            Vista.imprimirMensaje("------------");
            Vista.imprimirMensaje("1) Llistar tots els jugadors d'un equip.");
            Vista.imprimirMensaje("2) Calcular la mitjana de punts, rebots, assistències d'un jugador.");
            Vista.imprimirMensaje("3) Llistar tots els partits jugats per un equip amb el seu resultat.");
            Vista.imprimirMensaje("4) Inserir un nou jugador a un equip.");
            Vista.imprimirMensaje("5) Traspassar un judador a un altra equip.");
            Vista.imprimirMensaje("6) Actualitzar les dades de jugadors despres del partit.");
            Vista.imprimirMensaje("7) Modificar les estadístiques d’un jugador a un partit.");
            Vista.imprimirMensaje("8) Retirar un jugador.");
            Vista.imprimirMensaje("9) Canviar nom franquícia d’un equip.");
            Vista.imprimirMensaje("0) acabar");

            Vista.imprimirMensajeSeguido("> ");
            // funcion que se encarga de preguntar la opcion hasta que este en un rango valido
            opcio= utilities.introducirNumeroEntero(scan, 9, 0, false);
            scan.nextLine();
            switch (opcio){
                case 1:
                    Controlador.listarJugadores();
                    break;
                case 2:
                    Controlador.medianaJugador();
                    break;
                case 3:
                    Controlador.resultadosPartidos();
                    break;
                case 4:
                    Controlador.insertarJugador();
                    break;
                case 5:
                    Controlador.traspas(false);
                    break;
                case 6:
                    Controlador.actualizarDatos();
                    break;
                case 7:
                    Controlador.modificarEstadisticas();
                     break;
                case 8:
                    Controlador.retirarJugador();
                    break;
                case 9:
                    Controlador.cambiarFranquicia();
                    break;
                case 0:
                    System.exit(1);
                    System.out.println("Fins aviat!");
                    break;
            }
            System.out.println();
        } while (true);


    }

    /**
     * funcion que genera un menu con un formato especifico
     * @param texto indica el titulo del menu
     * @param opciones array de las opciones que se veran en el menu
     * @return retorna la opcion seleccionada luego de verificar que sea valida
     */
    public static int confirmMenu(String texto,String[] opciones){
        int opcio;
        // utiliza funciones de "libreriaCustom" para mostrar y formatear el menu
            utilities.menu(texto, opciones);
            Vista.imprimirMensajeSeguido("> ");
            opcio = utilities.introducirNumeroEntero(scan, opciones.length,1,false);


        return opcio;
    }


}

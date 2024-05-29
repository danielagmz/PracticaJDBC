package Controlador;

import Model.Dao.*;
import Model.*;
import Vista.Vista;
import megaLibreria.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * clase que se encarga del funcionamiento del programa
 */
public class Controlador {
    /**
     * variable de input por teclado
     */
    static Scanner scan = new Scanner(System.in);

    /**
     * lista los jugadores de un equipo introducido, siempre y cuando sea correcto dicho equipo
     * @throws SQLException lanza una excepcion si hay algun problema con la conexion o si el equipo no es correcto
     */
    public static void listarJugadores() throws SQLException {
        String nom;
        List<Player> jugadores;
        System.out.print("Dime un equipo: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje ("Buscando...");
        if (verificarNombre(nom)){
            DaoPlayer player = new DaoPlayer();
            jugadores=player.listarTodos(nom);
            if (jugadores!=null){
                Vista.imprimirPlayers(jugadores);
            }else{
                Vista.imprimirMensaje("Ese equipo no existe");
                Menu.menuPrincipal();
            }

        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

    /**
     * Muestra las estadisticas de un jugador que se introduzca, siemrpe que sea correcto dicho jugador
     */
    public static void medianaJugador(){
        String nom;
        List<Players_stats> jugadores;
        Vista.imprimirMensajeSeguido ("Dime un nombre de un jugador: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje ("Buscando...");
        if (verificarNombre(nom)){
            DaoPlayer player = new DaoPlayer();
            jugadores=player.MedianasJugadores(nom);
            if (!jugadores.isEmpty()){
                Vista.imprimirPlayerStats(jugadores);
            }else{
                Vista.imprimirMensaje("Este jugador no existe");
            }
        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

    /**
     * Muestra los resultados de un equipo que se le pase, siempre que sea correcto el nombre
     */
    public static void resultadosPartidos(){
        String nom;
        List<String> partits;

        System.out.print("Dime un equipo: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje("Buscando...");

        if (verificarNombre(nom)){
            // leer los resultados de un partido usando la funcion de model que retorna una lista con ellos
            DaoMatch equipo = new DaoMatch();
            partits=equipo.ResultPartits(nom);
            // si no hay elementos en la lista partits quiere decir que no hay partidos de ese equipo y/o que no existe
            if (!partits.isEmpty()){
                Vista.impPartidosJugados(partits);
            }else{
                Vista.imprimirMensaje("No se han encontrado resultados");
            }

        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

    /**
     * funcion que verifica que el nombre no tenga numeros o simbolos
     * @param nom nombre a verificar
     * @return retorna true si no tiene caracteres especiales y false si si
     */

    private static boolean verificarNombre(String nom){
        return nom.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑčČ\\s-.]+$");
    }

    /**
     * Inserta un jugador nuevo si no esta en la base de datos, si está solicita confirmacion de traspaso
     * @throws SQLException si hay un problema con la lectura o escritura de la bd lanza una excepcion con texto descriptivo
     */
    public static void insertarJugador() throws SQLException {
        String nom;
        boolean correcto;
        DaoPlayer db=new DaoPlayer();
        Player nPlayer=new Player();
        int id_equipActual;
        String equip_actual;
        String[] opt={"1- Si ✅","2- No ❌","3- Sortir 🏃‍♂️"};

        System.out.print("Nombre del jugador: ");
        nom = scan.nextLine();

// si el nombre es un texto, y no esta en la base de datos (la select da -1),
//  se agrega al objeto player el nombre. Si no (la select no da -1), este jugador ya existe y se llama a traspasar
        try {
            if (verificarNombre(nom)){
                Vista.imprimirMensaje("Comprobando nombre...");
                if (Model.obtenerIdJugador(nom)==-1){
                    nPlayer.setNom(nom);

                    System.out.print("Altura del jugador: ");
                    nPlayer.setAlcada(utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,1,false));

                    System.out.print("Peso del jugador: ");
                    nPlayer.setPes(utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,1,false));
                    scan.nextLine();
                    do {
                        // hasta que el equipo no sea correcto no deja de preguntar
                        correcto=true;
                        try {
                            System.out.print("Equipo del jugador: ");
                            equip_actual = scan.nextLine();
                            Vista.imprimirMensaje("Comprobando equipo...");

                            if (verificarNombre(equip_actual)) {
                                id_equipActual = Model.obtenerIdEquipo(equip_actual);
                                if (id_equipActual == -1) {
                                    throw new IllegalArgumentException("Este equipo no existe");
                                } else {
                                    nPlayer.setEquip_actual(id_equipActual);
                                }

                                if (db.create(nPlayer)) {
                                    Vista.imprimirMensaje("El jugador ha sido creado y asignado al equipo");
                                } else {
                                    Vista.imprimirMensaje("Ha habido un error creando al jugador");
                                }
                            } else {
                                throw new IllegalArgumentException("El equipo no es correcto");
                            }
                        } catch (Exception e) {
                            Vista.imprimirMensaje(e.getMessage());
                            correcto=false;
                        }

                    }while (!correcto);

                } else{
                    Vista.imprimirMensaje("Este jugador ya existe en la base de datos");

                    // switch de las opciones, case 1 (si), case 2 (no), case 3 (salir al menu principal)
                    switch ( Menu.confirmMenu("desea traspasarlo?",opt)){
                        case 1:
                            // parametro puestop a true para que le pida confirmacion del nombre
                            traspas(true);

                        break;
                        case 2:
                            insertarJugador();
                        break;
                        case 3:
                            Menu.menuPrincipal();
                        break;
                    }
                }
            } else {
               throw new IllegalArgumentException("El nombre no es correcto");
            }
        } catch (IllegalArgumentException e) {
           Vista.imprimirMensaje(e.getMessage());
        }

    }

    public static void traspas(boolean traspas_equip) throws SQLException {
        String nom;
        String equipN;
        Player player = new Player();
        int equipo_nuevo;
        DaoPlayer db = new DaoPlayer();
        String[] opt={"1- Si ✅","2- No ❌","3- Sortir 🏃‍♂️"};

        // si el parametro es true quiere decir que viene desde la funcion de InsertarJugador
        if (!traspas_equip) {
            Vista.imprimirMensajeSeguido("Dime el nombre de un jugador: ");
            nom = scan.nextLine();
            Vista.imprimirMensaje ("Buscando...");
        }else{
            Vista.imprimirMensajeSeguido("Confirma el nombre: ");
            nom = scan.nextLine();
            Vista.imprimirMensaje ("Recuperando datos...");
        }


        if (verificarNombre(nom)){
            // busca el jugador en la base de datos y crea un objeto con sus atributos
            player.setId(Model.obtenerIdJugador(nom));
            player=db.read(player);
            if (player!=null){
                Vista.mostrarGenerico(player,true);
                Vista.saltoLinea();
                // menu para confirmar que se traspasa el jugador correcto
                // 1 proceso de traspaso, 2 introducir de vuelta un nuevo jugador y 3 salir
                switch (Menu.confirmMenu("És aquest el jugador?",opt)){
                    case 1:
                        Vista.imprimirMensajeSeguido("Introdueix el nou equip: ");
                        // todo verificar el nombre
                        equipN=scan.nextLine();
                        Vista.imprimirMensaje("Buscando equipo...");
                        equipo_nuevo= Model.obtenerIdEquipo(equipN);
                        Vista.imprimirMensaje("traspasando jugador ...");
                        player.setEquip_actual(equipo_nuevo);
                        // si se ha podido modificar se avisa al usuario
                        if(db.update(player)){
                            Vista.imprimirMensaje("Traspasado correctamente");
                        }else {
                            Vista.imprimirMensaje("Ha habido un error en el traspaso");
                        }
                        break;
                    case 2:
                        traspas(false);
                        break;

                    default:
                        Menu.menuPrincipal();
                }
            }else{
                Vista.imprimirMensaje("Este jugador no existe");
                Menu.menuPrincipal();
            }

        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
            Menu.menuPrincipal();
        }
    }

    public static void retirarJugador() throws SQLException {
        String nom;
        HistoricPlayers retirado;
        String nom_equip;
        String[] opt={"1- Si ✅","2- No ❌","3- Sortir 🏃‍♂️"};
        int id;
        // recuperar datos como el nomnbre y el equipo actual del jugador
        Player playerRet=new Player();
        DaoPlayer dbp = new DaoPlayer();
        // recuperar estadisticas de un jugador
        Players_stats ps=new Players_stats();
        DaoPlayerStats dbs= new DaoPlayerStats();
        // tabla para guardar las estadisticas del jugador retirado
        DaoHistoricPlayers db=new DaoHistoricPlayers();


        Vista.imprimirMensajeSeguido("Introduce el jugador que quieres retirar: ");
        nom=scan.nextLine().trim();

        if (verificarNombre(nom)){
            Vista.imprimirMensaje("Recuperando datos...");
            //a partir del id, leer y guardar un objeto de player y un objeto de stats
            id=Model.obtenerIdJugador(nom);

            playerRet.setId(id);
            ps.setId_jugador(id);

            playerRet=dbp.read(playerRet);  // datos basicos del jugador nombre,peso,altura
            ps=dbs.read(ps);        // stats del jugador


            if (ps!=null && playerRet!= null) {
                Vista.mostrarGenerico(playerRet,true);
                Vista.saltoLinea();
                // corfimacion de que el jugador es correcto
                // 1 proceso de retirar, 2 vuelta a preguntar un jugador y 3 o cualquier numero mas menu principal
                switch (Menu.confirmMenu("es este el jugador que quieres retirar?",opt)){
                    case 1:
                        // solo se hace el proceso si hay un jugador que retirar

                        Vista.imprimirMensaje("Retirando jugador...");
                        //Acceder a los datos de player stats, obtener el id, los stats y el nombre del ultimo equipo de players
                        nom_equip=Model.obtenerNombreEquipo(playerRet.getEquip_actual());
                        retirado= // ↓ id_jugador,puntos,rebotes,asistencias y nombre del equipo (campos de la tabla historico) ↓
                        new HistoricPlayers(playerRet.getId(), ps.getAvg_puntos(), ps.getAvg_rebotes(),ps.getAvg_asistencias(),nom_equip);
                        // una vez creada la entrada en la tabla de historico, eliminar el jugador de players y por si el trigger no
                        // funcionase tambien eliminar de player stats
                        if (db.create(retirado)){
                            dbs.delete(ps);
                            dbp.delete(playerRet);
                            Vista.imprimirMensaje("Jugador retirado con exito!");
                            Menu.menuPrincipal();
                        }

                    case 2:
                        retirarJugador();

                    default:
                        Menu.menuPrincipal();
                }
            } else{
                // si hubiese un problema para encontrar el jugador, ser le vuelve a preguntar
                Vista.imprimirMensaje("Este jugador no existe");
            }


        }else{
            Vista.imprimirMensaje("El nombre no es correcto");
        }

    }

    /**

     * Funcion para pedir al usuario que franquicia desea cambiar
     */
    public static void introducirFranquicia() {
        String nombre = obtenerNombreValido("Que franquicia quieres cambiar (Ej: Los Angeles Lakers): ");
        String franquicia = obtenerNombreValido("Escribe la franquicia: ");
        cambiarFranquicia(nombre, franquicia);
    }

    /**
     * Funcion para verificar el equipo y la franquicia
     * @param mensaje Es el mensaje que se va a prinatr al usuario
     * @return Retorna el nombre ya verificado
     */
    private static String obtenerNombreValido(String mensaje) {
        String nombre;
        do {
            Vista.imprimirMensajeSeguido(mensaje);
            nombre = scan.nextLine();
            if (!verificarNombre(nombre)) {
                Vista.imprimirMensaje("El nombre no es correcto");
            }
        } while (!verificarNombre(nombre));
        return nombre;
    }

    /**
     * Funcion para cambiar la franquicia a partir de la funcion de IntroducirFranquicia
     * @param nombre La franquicia que el usuario quiere cambiar
     * @param franquicia La nueva franquicia
     */
    public static void cambiarFranquicia(String nombre, String franquicia){
        int id_equipo;
        Team equipo = new Team();
        DaoTeam dbt = new DaoTeam();
        String franquiciaNueva = franquicia.substring(0,1).toUpperCase() + franquicia.substring(1).toLowerCase();
        id_equipo = Model.obtenerIdEquipoNomComplet(nombre);
        if (id_equipo != -1){
            Vista.imprimirMensaje("Actualizando franquicia...");
            equipo.setId(id_equipo);
            equipo=dbt.read(equipo);
            equipo.setFranquicia(franquiciaNueva);
            if (dbt.update(equipo)){
                Vista.imprimirMensaje("Modificado con exito");
            }else {
                Vista.imprimirMensaje("No se ha podido modificar");
            }
        } else {
            Vista.imprimirMensaje("No se ha encontrado la franquicia");
        }

    }

    /**
     * Funcion para actualizar los datos de los partidos y los jugadores mediante unos ficheros que se encunetran en la carpeta Ficheros
     */
    public static void actualizarDatos()  {
        File carpetaFicheros = new File("Ficheros");
        File f = new File("Ficheros/partidos.csv");
        File f2 = new File("Ficheros/jugadores.csv");
        List<String[]> partidos = new ArrayList<>();
        List<String[]> jugadores = new ArrayList<>();

        String linea,linea2;

        Match matchRet = new Match();
        DaoMatch dbm = new DaoMatch();

        PlayerMatches partidoJugadores = new PlayerMatches();
        DaoPlayerMatches dbp = new DaoPlayerMatches();

        if (!carpetaFicheros.exists()) {
            boolean carpetaCreada = carpetaFicheros.mkdir();
            String mensaje = carpetaCreada ? "Se ha creado la carpeta correctamente" : "No se ha podido crear la carpeta Ficheros";
            Vista.imprimirMensaje(mensaje);
        }
        try {
            Vista.imprimirMensaje("Insertando datos..");
            Scanner scan = new Scanner(f);
            Scanner scan2 = new Scanner(f2);
            while (scan.hasNextLine()) {
                linea = scan.nextLine();
                partidos.add(linea.split(";"));
            }
            while (scan2.hasNextLine()){
                linea2 = scan2.nextLine();
                jugadores.add(linea2.split(";"));
            }

            for (String[] partido : partidos) {
                matchRet.setPuntos_visitante(Integer.parseInt(partido[1]));
                matchRet.setPuntos_local(Integer.parseInt(partido[2]));
                matchRet.setId(Integer.parseInt(partido[0]));
                if (dbm.update(matchRet)){

                    Vista.imprimirMensaje("Partido "+partido[0]+" Actualizado..");
                }else {
                    Vista.imprimirMensaje("No se ha podido actualizar los datos de este partido");
                }

            }
            for (String[] jugador : jugadores) {
                partidoJugadores.setId_match(Integer.parseInt(jugador[0]));
                partidoJugadores.setId_jug(Integer.parseInt(jugador[1]));
                partidoJugadores.setPunts(Integer.parseInt(jugador[2]));
                partidoJugadores.setRebots(Integer.parseInt(jugador[3]));
                partidoJugadores.setAssist(Integer.parseInt(jugador[4]));
                if (dbp.update(partidoJugadores)){
                    Vista.imprimirMensaje("Jugador "+Model.obtenerNombreJugador(Integer.parseInt(jugador[1]))+" Actualizado..");
                }else {
                    Vista.imprimirMensaje("No se ha podido actualizar los datos de este jugador");
                }
            }
            Vista.imprimirMensaje("Se han actualizado los datos");
        } catch (FileNotFoundException e) {
            Vista.imprimirMensaje("Ha habido un problema con la lectura de los archivos");
        } catch (SQLException e) {
            Vista.imprimirMensaje("No se ha podido establecer la conexion");
        }

    }

    /**
     * Modifica las estadisticas de un jugador en un partido concreto
     * @throws SQLException lanza una excepcion personalizada si no se puede encontrar un jugador o si
     * no hay estadisticas que mostrar de un jugador
     */
    public static void modificarEstadisticas() throws SQLException {
        List<String> partidos;
        String nombre;
        int id,partido,puntos,rebotes,asis,id_partido;
        List<PlayerMatches> partidosResult;

        PlayerMatches pm = new PlayerMatches();
        DaoPlayerMatches dbm = new DaoPlayerMatches();


        do {
            Vista.imprimirMensajeSeguido("Dime el nombre de un jugador: ");
            nombre = scan.nextLine();
            if (!verificarNombre(nombre)) {
                Vista.imprimirMensaje("El nombre no es correcto");
            }
        } while (!verificarNombre(nombre));
        Vista.imprimirMensaje("Buscando resultados..");
        id = Model.obtenerIdJugador(nombre);

        if (id!=-1) {
            pm.setId_jug(id);

            partidos = Model.obtenerPartidos(id);
            partidosResult = Model.obtenerResultPartidos(nombre);

            if (!partidosResult.isEmpty() && !partidos.isEmpty()){
                Vista.imprimirPlayerResult(partidos,partidosResult);
                Vista.saltoLinea();
                Vista.imprimirMensajeSeguido("Que partido quieres modificar: ");
                partido = utilities.introducirNumeroEntero(scan,partidosResult.size(),1,false);

                id_partido = partidosResult.get(partido-1).getId_match();
                pm.setId_match(id_partido);
                    Vista.imprimirMensajeSeguido("Puntos: ");
                    puntos = utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,0,false);
                    pm.setPunts(puntos);


                    Vista.imprimirMensajeSeguido("Rebotes: ");
                    rebotes = utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,0,false);
                    pm.setRebots(rebotes);


                    Vista.imprimirMensajeSeguido("Asistencias: ");
                    asis = utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,0,false);
                    pm.setAssist(asis);


                    Vista.imprimirMensaje("Modificando...");
                    if(dbm.update(pm) ){
                        Vista.imprimirMensaje("Modificado con exito");
                    }else {
                     Vista.imprimirMensaje("no se ha podido modificar");
                    }

            }else{
               Vista.imprimirMensaje("No se han encontrado resultados");
               Menu.menuPrincipal();
            }
        } else {
            Vista.imprimirMensaje("Este jugador no existe");
            Menu.menuPrincipal();
        }
    }

}

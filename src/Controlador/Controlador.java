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
        DaoPlayer player = new DaoPlayer();
        try {
            do {
                Vista.imprimirMensajeSeguido("Dime un equipo: ");
                nom = scan.nextLine();
                if (!verificarNombre(nom)){
                    Vista.imprimirMensaje("No es correcto el nombre");
                }
            } while (!verificarNombre(nom));

            if (verificarNombre(nom)){
                Vista.imprimirMensaje ("Buscando...");
                if (Model.obtenerIdEquipo(nom) == -1) {
                    // Si no encuntra la id del jugador busca si hay alguna similitud en la base de datos
                    Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
                    Vista.imprimirMensaje ("Buscando similitudes...");
                    String opcion = buscarYSeleccionarOpcion(nom,"teams","equipos");
                    if (opcion != null){
                        Vista.imprimirMensaje ("Recuperando datos...");
                        jugadores=player.listarTodos(opcion);
                        if (jugadores!=null){
                            Vista.imprimirPlayers(jugadores);
                        }else{
                            Vista.imprimirMensaje("Ese equipo no existe");
                        }
                    }
                    Menu.menuPrincipal();
                } else {
                    Vista.imprimirMensaje ("Recuperando datos...");
                    jugadores=player.listarTodos(nom);
                    if (jugadores!=null){
                        Vista.imprimirPlayers(jugadores);
                    }else{
                        Vista.imprimirMensaje("Ese equipo no existe");
                    }
                }
            } else {
                Vista.imprimirMensaje("El nombre no es correcto");
            }

        } catch (IllegalArgumentException e){
            Vista.imprimirMensaje(e.getMessage());
        }
    }

    /**
     * Muestra las estadisticas de un jugador que se introduzca, siemrpe que sea correcto dicho jugador
     */
    public static void medianaJugador(){
        String nom;
        List<Players_stats> jugadores;
        DaoPlayer player = new DaoPlayer();
        try {
            do {
                Vista.imprimirMensajeSeguido ("Dime un nombre de un jugador: ");
                nom = scan.nextLine();
                if (!verificarNombre(nom)){
                    Vista.imprimirMensaje("El nombre no es correcto");
                }
            } while (!verificarNombre(nom));

            Vista.imprimirMensaje ("Buscando...");
            if (Model.obtenerIdJugador(nom) == -1){
                Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
                Vista.imprimirMensaje ("Buscando similitudes...");
                String opcion = buscarYSeleccionarOpcion(nom,"players","jugador");
                if (opcion != null){
                    Vista.imprimirMensaje ("Recuperando datos...");
                    jugadores=player.medianasJugadores(opcion);
                    if (jugadores!=null){
                        Vista.imprimirPlayerStats(jugadores);
                    }else{
                        Vista.imprimirMensaje("Este jugador no existe");
                    }
                }
                Menu.menuPrincipal();
            } else {
                Vista.imprimirMensaje ("Recuperando datos...");
                jugadores=player.medianasJugadores(nom);
                if (!jugadores.isEmpty()){
                    Vista.imprimirPlayerStats(jugadores);
                    Vista.saltoLinea();
                }else{
                    Vista.imprimirMensaje("Este jugador no existe");
                }
            }

        }catch (SQLException e){
            Vista.imprimirMensaje(e.getMessage());
        }
    }

    /**
     * Muestra los resultados de un equipo que se le pase, siempre que sea correcto el nombre
     */
    public static void resultadosPartidos(){
        String nom;
        List<String> partits;
        DaoMatch equipo = new DaoMatch();
        try {
            do {
                System.out.print("Dime un equipo: ");
                nom = scan.nextLine();
                if (!verificarNombre(nom)){
                    Vista.imprimirMensaje("No es correcto el nombre");
                }
            } while (!verificarNombre(nom));

            // leer los resultados de un partido usando la funcion de model que retorna una lista con ellos
            Vista.imprimirMensaje("Buscando...");
            partits=equipo.resultPartits(nom);
            // si no hay elementos en la lista partits quiere decir que no hay partidos de ese equipo y/o que no existe
            if (!partits.isEmpty()){
                Vista.impPartidosJugados(partits);
            }else{
                //Busca si hay alguna similitud en la base de datos
                Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
                Vista.imprimirMensaje ("Buscando similitudes...");
                String opcion = buscarYSeleccionarOpcion(nom,"teams","equipos");
                if (opcion != null){
                    Vista.imprimirMensaje ("Recuperando datos...");
                    partits=equipo.resultPartits(opcion);
                    if (partits!=null){
                        Vista.impPartidosJugados(partits);
                    }
                }
                Menu.menuPrincipal();
            }
        } catch (SQLException e){
            Vista.imprimirMensaje("Ha habido un problema con la conexion");
        }
    }

    /**
     * funcion que verifica que el nombre no tenga numeros o simbolos
     * @param nom nombre a verificar
     * @return retorna true si no tiene caracteres especiales y false si si
     */

    private static boolean verificarNombre(String nom){
        boolean correcto= nom.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑčČ\\s-.]+$");

        if (nom.isEmpty() || nom.isBlank()){
            correcto=false;
        }
        return correcto;
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
                    // Si no encuntra la id del jugador busca si hay alguna similitud en la base de datos
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
        String opcio;
        DaoPlayer db = new DaoPlayer();
        String[] opt={"1- Si ✅","2- No ❌","3- Sortir 🏃‍♂️"};

        // si el parametro es true quiere decir que viene desde la funcion de InsertarJugador
        if (!traspas_equip) {
            do {
                Vista.imprimirMensajeSeguido("Dime el nombre de un jugador: ");
                nom = scan.nextLine();
                if (!verificarNombre(nom)){
                    Vista.imprimirMensaje("El nombre no es correcto");
                }
            } while (!verificarNombre(nom));

            Vista.imprimirMensaje ("Buscando...");
        }else{
            Vista.imprimirMensajeSeguido("Confirma el nombre: ");
            nom = scan.nextLine();
            Vista.imprimirMensaje ("Recuperando datos...");
        }


        if (verificarNombre(nom)){
            // busca el jugador en la base de datos y crea un objeto con sus atributos
            int id_jugador = Model.obtenerIdJugador(nom);
            if (id_jugador == -1){
                // Si no encuntra la id del jugador busca si hay alguna similitud en la base de datos
                Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
                Vista.imprimirMensaje ("Buscando similitudes...");
                String opcion = buscarYSeleccionarOpcion(nom,"players","jugadores");
                if (opcion != null){

                    Vista.imprimirMensaje("Recuperando datos del jugador...");
                    player.setId(Model.obtenerIdJugador(opcion));
                    player=db.read(player);

                    Vista.mostrarGenerico(player,true);
                    Vista.saltoLinea();

                    switch (Menu.confirmMenu("És aquest el jugador?",opt)){
                        case 1:
                            do{
                                Vista.imprimirMensajeSeguido("Introdueix el nou equip: ");
                                equipN=scan.nextLine();

                                Vista.imprimirMensaje("Buscando equipo...");
                                equipo_nuevo= Model.obtenerIdEquipo(equipN);
                                if (equipo_nuevo==-1){
                                   Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
                                    Vista.imprimirMensaje("Buscando similitudes...");
                                   opcio= buscarYSeleccionarOpcion(equipN,"teams","equipos");
                                   equipo_nuevo=Model.obtenerIdEquipo(opcio);
                                   if (equipo_nuevo==-1){
                                       Vista.imprimirMensaje("Este equipo no existe");
                                   }
                                }
                            } while (equipo_nuevo==-1);

                            Vista.imprimirMensaje("Traspasando jugador...");
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
                }
                Menu.menuPrincipal();
            } else {
                Vista.imprimirMensaje("Recuperando datos del jugador...");
                player.setId(id_jugador);
                player = db.read(player);
                Vista.mostrarGenerico(player, true);
                Vista.saltoLinea();
                // menu para confirmar que se traspasa el jugador correcto
                // 1 proceso de traspaso, 2 introducir de vuelta un nuevo jugador y 3 salir
                switch (Menu.confirmMenu("És aquest el jugador?", opt)) {
                    case 1:
                        do {
                            Vista.imprimirMensajeSeguido("Introdueix el nou equip: ");
                            equipN = scan.nextLine();

                            Vista.imprimirMensaje("Buscando equipo...");
                            equipo_nuevo = Model.obtenerIdEquipo(equipN);

                            if (equipo_nuevo==-1){
                                Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
                                Vista.imprimirMensaje("Buscando similitudes...");
                                opcio= buscarYSeleccionarOpcion(equipN,"teams","equipos");
                                equipo_nuevo=Model.obtenerIdEquipo(opcio);
                                if (equipo_nuevo==-1){
                                    Vista.imprimirMensaje("Este equipo no existe");
                                    Vista.saltoLinea();
                                }
                            }
                        } while (equipo_nuevo==-1);
                        Vista.saltoLinea();
                        Vista.imprimirMensaje("Traspasando jugador...");
                        player.setEquip_actual(equipo_nuevo);

                        // si se ha podido modificar se avisa al usuario
                        if (db.update(player)) {
                            Vista.imprimirMensaje("Traspasado correctamente");
                        } else {
                            Vista.imprimirMensaje("Ha habido un error en el traspaso");
                        }
                        break;
                    case 2:
                        traspas(false);
                        break;

                    default:
                        Menu.menuPrincipal();
                }
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

        do {
            Vista.imprimirMensajeSeguido("Introduce el jugador que quieres retirar: ");
            nom=scan.nextLine().trim();
            if (!verificarNombre(nom)){
                Vista.imprimirMensaje("El nombre no es correcto");
            }
        } while (!verificarNombre(nom));

        Vista.imprimirMensaje("Recuperando datos...");
        if (Model.obtenerIdJugador(nom) == -1) {
            // Si no encuntra la id del jugador busca si hay alguna similitud en la base de datos
            Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
            Vista.imprimirMensaje("Buscando similitudes...");
            String opcion = buscarYSeleccionarOpcion(nom, "players", "jugadores");
            if (opcion != null){
                id = Model.obtenerIdJugador(opcion);
                playerRet.setId(id);
                ps.setId_jugador(id);

                playerRet = dbp.read(playerRet);  // datos basicos del jugador nombre,peso,altura
                ps = dbs.read(ps);        // stats del jugador

                Vista.mostrarGenerico(playerRet, true);
                Vista.saltoLinea();
                // corfimacion de que el jugador es correcto
                // 1 proceso de retirar, 2 vuelta a preguntar un jugador y 3 o cualquier numero mas menu principal
                switch (Menu.confirmMenu("es este el jugador que quieres retirar?", opt)) {
                    case 1:
                        // solo se hace el proceso si hay un jugador que retirar
                        if (ps != null && playerRet != null) {
                            Vista.imprimirMensaje("Retirando jugador...");
                            //Acceder a los datos de player stats, obtener el id, los stats y el nombre del ultimo equipo de players
                            nom_equip = Model.obtenerNombreEquipo(playerRet.getEquip_actual());
                            retirado = // ↓ id_jugador,puntos,rebotes,asistencias y nombre del equipo (campos de la tabla historico) ↓
                                    new HistoricPlayers(playerRet.getId(), ps.getAvg_puntos(), ps.getAvg_rebotes(), ps.getAvg_asistencias(), nom_equip);
                            // una vez creada la entrada en la tabla de historico, eliminar el jugador de players y por si el trigger no
                            // funcionase tambien eliminar de player stats
                            if (db.create(retirado)) {
                                dbs.delete(ps);
                                dbp.delete(playerRet);
                                Vista.imprimirMensaje("Jugador retirado con exito!");
                                Menu.menuPrincipal();
                            }
                        } else {
                            // si por cualquier otra cosa hubiese algun otro problema para encontrar el jugador, ser le vuelve a preguntar
                            Vista.imprimirMensaje("Ha habido un problema al recuperar los datos de este jugador");
                            Vista.imprimirMensaje("Intentalo de nuevo");

                            retirarJugador();
                        }
                    case 2:
                        retirarJugador();

                    default:
                        Menu.menuPrincipal();
                }
            }
            Menu.menuPrincipal();
        } else {
            //a partir del id, leer y guardar un objeto de player y un objeto de stats
            id = Model.obtenerIdJugador(nom);

            playerRet.setId(id);
            ps.setId_jugador(id);

            playerRet = dbp.read(playerRet);  // datos basicos del jugador nombre,peso,altura
            ps = dbs.read(ps);        // stats del jugador

            Vista.mostrarGenerico(playerRet, true);
            Vista.saltoLinea();
            // corfimacion de que el jugador es correcto
            // 1 proceso de retirar, 2 vuelta a preguntar un jugador y 3 o cualquier numero mas menu principal
            switch (Menu.confirmMenu("es este el jugador que quieres retirar?", opt)) {
                case 1:
                    // solo se hace el proceso si hay un jugador que retirar
                    if (ps != null && playerRet != null) {
                        Vista.imprimirMensaje("Retirando jugador...");
                        //Acceder a los datos de player stats, obtener el id, los stats y el nombre del ultimo equipo de players
                        nom_equip = Model.obtenerNombreEquipo(playerRet.getEquip_actual());
                        retirado = // ↓ id_jugador,puntos,rebotes,asistencias y nombre del equipo (campos de la tabla historico) ↓
                                new HistoricPlayers(playerRet.getId(), ps.getAvg_puntos(), ps.getAvg_rebotes(), ps.getAvg_asistencias(), nom_equip);
                        // una vez creada la entrada en la tabla de historico, eliminar el jugador de players y por si el trigger no
                        // funcionase tambien eliminar de player stats
                        if (db.create(retirado)) {
                            dbs.delete(ps);
                            dbp.delete(playerRet);
                            Vista.imprimirMensaje("Jugador retirado con exito!");
                            Menu.menuPrincipal();
                        }
                    } else {
                        // si por cualquier otra cosa hubiese algun otro problema para encontrar el jugador, ser le vuelve a preguntar
                        Vista.imprimirMensaje("Ha habido un problema al recuperar los datos de este jugador");
                        Vista.imprimirMensaje("Intentalo de nuevo");

                        retirarJugador();
                    }
                case 2:
                    retirarJugador();

                default:
                    Menu.menuPrincipal();
            }
        }

    }


    /**
     * Funcion para cambiar la franquicia a partir de la funcion de IntroducirFranquicia
     */
    public static void cambiarFranquicia() throws SQLException {
        int id_equipo;
        Team equipo = new Team();
        DaoTeam dbt = new DaoTeam();
        String nombre,franquicia;
        do {
            Vista.imprimirMensajeSeguido("Que franquicia quieres cambiar (Ej: Los Angeles Lakers): ");
            nombre = scan.nextLine();
            if (!verificarNombre(nombre)) {
                Vista.imprimirMensaje("El nombre no es correcto");
            }
        } while (!verificarNombre(nombre));

        Vista.imprimirMensaje("Buscando...");
        id_equipo = Model.obtenerIdEquipoNomComplet(nombre);
        if (id_equipo == -1){
            // Si no encuntra la id del jugador busca si hay alguna similitud en la base de datos
            Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
            Vista.imprimirMensaje ("Buscando similitudes...");
            String opcion = buscarYSeleccionarFranquicia(nombre);
            if (opcion != null){
                do {
                    Vista.imprimirMensajeSeguido("Escribe la franquicia: ");
                    franquicia = scan.nextLine();
                    if (!verificarNombre(franquicia)) {
                        Vista.imprimirMensaje("El nombre no es correcto");
                    }
                } while (!verificarNombre(franquicia));
                // hacemos que la primera letra sea mayuscula
                String franquiciaNueva = franquicia.substring(0,1).toUpperCase() + franquicia.substring(1).toLowerCase();
                Vista.imprimirMensaje("Actualizando franquicia...");
                equipo.setId(Model.obtenerIdEquipoNomComplet(opcion));
                equipo=dbt.read(equipo);
                equipo.setFranquicia(franquiciaNueva);
                if (dbt.update(equipo)){
                    Vista.imprimirMensaje("Modificado con exito");

                }else {
                    Vista.imprimirMensaje("No se ha podido modificar");
                }
                Menu.menuPrincipal();
            }
            Menu.menuPrincipal();
        } else {
            do {
                Vista.imprimirMensajeSeguido("Escribe la franquicia: ");
                franquicia = scan.nextLine();
                if (!verificarNombre(franquicia)) {
                    Vista.imprimirMensaje("El nombre no es correcto");
                }
            } while (!verificarNombre(franquicia));
            // hacemos que la primera letra sea mayuscula
            String franquiciaNueva = franquicia.substring(0,1).toUpperCase() + franquicia.substring(1).toLowerCase();
            Vista.imprimirMensaje("Actualizando franquicia...");
            equipo.setId(id_equipo);
            equipo=dbt.read(equipo);
            equipo.setFranquicia(franquiciaNueva);
            if (dbt.update(equipo)){
                Vista.imprimirMensaje("Modificado con exito");

            }else {
                Vista.imprimirMensaje("No se ha podido modificar");
            }
            Menu.menuPrincipal();
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
     */
    public static void modificarEstadisticas(){
        List<String> partidos;
        String nombre;
        int id,partido,puntos,rebotes,asis,id_partido;
        List<PlayerMatches> partidosResult;

        PlayerMatches pm = new PlayerMatches();
        DaoPlayerMatches dbm = new DaoPlayerMatches();

       try {
           do {
               Vista.imprimirMensajeSeguido("Dime el nombre de un jugador: ");
               nombre = scan.nextLine();
               if (!verificarNombre(nombre)) {
                   Vista.imprimirMensaje("El nombre no es correcto");
               }
           } while (!verificarNombre(nombre));
           Vista.imprimirMensaje("Buscando resultados..");
           id = Model.obtenerIdJugador(nombre);

           if (id == -1) {
               // Si no encuntra la id del jugador busca si hay alguna similitud en la base de datos
               Vista.imprimirMensaje("No se han encontrado coincidencias exactas");
               Vista.imprimirMensaje("Buscando similitudes...");
               String opcion = buscarYSeleccionarOpcion(nombre,"players","jugadores");
               if (opcion != null){
                   Vista.imprimirMensaje("Recuperando datos...");
                   id = Model.obtenerIdJugador(opcion);
                   pm.setId_jug(id);
                   partidos = Model.obtenerPartidos(id);
                   partidosResult = Model.obtenerResultPartidos(opcion);

                   if (!partidosResult.isEmpty() && !partidos.isEmpty()){
                       Vista.imprimirPlayerResult(partidos,partidosResult);
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
                       scan.nextLine();
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
               }
               Menu.menuPrincipal();
           } else {
               Vista.imprimirMensaje("Recuperando datos...");
               pm.setId_jug(id);
               partidos = Model.obtenerPartidos(id);
               partidosResult = Model.obtenerResultPartidos(nombre);

               if (!partidosResult.isEmpty() && !partidos.isEmpty()){
                   Vista.imprimirPlayerResult(partidos,partidosResult);
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
                   scan.nextLine();
                   pm.setAssist(asis);


                   Vista.imprimirMensaje("Modificando...");
                   if(dbm.update(pm) ){
                       Vista.imprimirMensaje("Modificado con exito");
                       Menu.menuPrincipal();
                   }else {
                       Vista.imprimirMensaje("no se ha podido modificar");
                   }

               }else{
                  Vista.imprimirMensaje("No se han encontrado resultados");
               }
           }
       } catch (SQLException e){
           Vista.imprimirMensaje("Ha habido un problema con la conexion");
       }
    }

    /**
     * Funcion para buscar un jugador o equipos similares a lo que el usuario ha introducido
     * @param pal_clave Palabra que servira para hacer la busqueda generica en la base de datos
     * @param tabla Tabla donde la funcion hará la select
     * @param variable Este parametro serviara para imprimir por pantalla si es equipos o jugadores
     * @return Retorna la opcion que el usuario ha escogido cuando se le muestran las similitudes
     */
    public static String buscarYSeleccionarOpcion(String pal_clave ,String tabla,String variable) {
        List<String> opciones = Model.buscadorBD(pal_clave ,tabla);
        if (opciones.isEmpty()) {
            Vista.imprimirMensaje("No se encontraron " +variable+ " con ese nombre.");
            return null;
        } else {
            Vista.mostrarOpciones(variable,opciones);
            Vista.imprimirMensajeSeguido("Seleccione el número que desea: ");
            int choice = utilities.introducirNumeroEntero(scan,opciones.size(),1,false);
            if (choice > 0 && choice <= opciones.size()) {
                String seleccion = opciones.get(choice - 1);
                Vista.imprimirMensaje("Has seleccionado: " + seleccion);
                return seleccion;
            } else {
                Vista.imprimirMensaje("Selección no válida.");
                return null;
            }
        }
    }

    /**
     * Funcion para buscar similitudes con las franquicias
     * @param pal_clave Palabra que servira para hacer la busqueda generica en la base de datos
     * @return Retorna la opcion que el usuario ha escogido cuando se le muestran las similitudes
     */
    public static String buscarYSeleccionarFranquicia(String pal_clave) {
        List<String> opciones = Model.buscadorFranquiciaBD(pal_clave);
        if (opciones.isEmpty()) {
            Vista.imprimirMensaje("No se ha encontrado una franquicia con ese nombre.");
            return null;
        } else {
            Vista.mostrarOpcionesFranquicia(opciones);
            Vista.imprimirMensajeSeguido("Seleccione el número que desea:");
            int choice = utilities.introducirNumeroEntero(scan,opciones.size(),1,false);
            scan.nextLine();
            if (choice > 0 && choice <= opciones.size()) {
                String seleccion = opciones.get(choice - 1);
                Vista.imprimirMensaje("Has seleccionado: " + seleccion);
                return seleccion;
            } else {
                Vista.imprimirMensaje("Selección no válida.");
                return null;
            }
        }
    }

}

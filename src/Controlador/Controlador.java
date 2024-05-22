package Controlador;

import Model.Dao.DaoHistoricPlayers;
import Model.Dao.DaoMatch;
import Model.Dao.DaoPlayer;
import Model.Dao.DaoPlayerStats;
import Model.Model;
import Model.Player;
import Vista.Vista;
import megaLibreria.utilities;
import Model.HistoricPlayers;
import Model.Players_stats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    public static void ListarJugadores() throws SQLException {
        String nom;
        System.out.print("Dime un equipo: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje ("Buscando...");
        if (VerificarNombre(nom)){
            DaoPlayer player = new DaoPlayer();
            Vista.imprimirPlayers(player.listarTodos(nom));
        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

    public static void MedianaJugador(){
        String nom;
        Vista.imprimirMensajeSeguido ("Dime un nombre de un jugador: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje ("Buscando...");
        if (VerificarNombre(nom)){
            DaoPlayer player = new DaoPlayer();
            Vista.imprimirPlayerStats(player.MedianasJugadores(nom));
        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

    public static void ResultadosPartidos(){
        String nom;
        System.out.print("Dime un equipo: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje("Buscando...");
        if (VerificarNombre(nom)){
            DaoMatch equipo = new DaoMatch();
            Vista.impPartidosJugados(equipo.ResultPartits(nom));
        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }
    private static boolean Verificar(String nom){
        do{
            nom = scan.nextLine();
        }while (!nom.matches("^[a-zA-Z]+([-' ][a-zA-Z]+)*$"));

        return false;
    }
    private static boolean VerificarNombre(String nom){
        return nom.matches("^[a-zA-Z]+([-' ][a-zA-Z]+)*$");
    }
    public static boolean ComprobarId(int id,String tabla){
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = Conexion.connection();
            if (con != null){
                    statement = con.prepareStatement("SELECT * FROM " + tabla+" WHERE id=?");
                    statement.setInt(1,id);
                    ResultSet resultSet = statement.executeQuery();

                return resultSet.next();

            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(statement);
        }
        return false;
    }
// todo borrar la rama desastre y recordar a chris hacer pull del main
    // todo refactor para verificaciones
    public static void InsertarJugador() throws SQLException {
        String nom;
        boolean correcto=true;
        DaoPlayer db=new DaoPlayer();
        Player nPlayer=new Player();
        int id_equipActual;
        String equip_actual;
        String[] opt={"1- Si ✅","2- No ❌","3- Sortir 🏃‍♂️"};

        System.out.print("Nombre del jugador: ");
        nom = scan.nextLine();

// todo si el nombre es un texto, y no esta en la base de datos (la select da -1),
//  se agrega al objeto player el nombre. Si no (la select no da -1), este jugador ya existe y se llama a traspasar
        try {
            if (VerificarNombre(nom)){
                Vista.imprimirMensaje("Comprobando nombre...");
                if (Model.obtenerIdJugador(nom)==-1){
                    nPlayer.setNom(nom);

                    System.out.print("Altura del jugador: ");
                    nPlayer.setAlcada(utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,1,false));

                    System.out.print("Peso del jugador: ");
                    nPlayer.setPes(utilities.introducirNumeroEntero(scan,Integer.MAX_VALUE,1,false));
                    scan.nextLine();
                    System.out.print("Equipo del jugador: ");
                    equip_actual = scan.nextLine();
                    Vista.imprimirMensaje("Comprobando equipo...");
                    if (VerificarNombre(equip_actual)){
                        id_equipActual= Model.obtenerIdEquipo(equip_actual);
                        if (id_equipActual==-1){
                            throw new IllegalArgumentException("Este equipo no existe");
                        }else{
                            nPlayer.setEquip_actual(id_equipActual);
                        }
                        if (db.create(nPlayer)) {
                            Vista.imprimirMensaje("El jugador ha sido creado y asignado al equipo");
                        } else {
                            Vista.imprimirMensaje("Ha habido un error creando al jugador");
                        }
                    }else{
                        throw new IllegalArgumentException("El equipo no es correcto");
                    }

                } else{
                    Vista.imprimirMensaje("Este jugador ya existe en la base de datos");

                    // todo switch de las opciones, case 1 (si), case 2 (no), case 3 (salir al menu principal)
                    switch ( Menu.confirmMenu("desea traspasarlo?",opt)){
                        case 1:
                            traspas(true);
                    // todo si se ejecuta traspaso le pedia nuevamente el nombre, lo he solucionado con un "confirme el nombre"
                        break;
                        case 2:
                            InsertarJugador();
                        break;
                        case 3:
                            Menu.menuPrincipal();
                        break;
                    }
                }
            } else {
                //todo aqui tendria que volverlo a preguntar
               throw new IllegalArgumentException("El nombre no es correcto");
            }
            // todo arreglar y que pidan las cosas varias veces el nombre o el equipo, probablemente funcion
        } catch (IllegalArgumentException e) {
           
        }

    }


    public static void traspas(boolean traspas_equip) throws SQLException {
        String nom;
        String equipN;
        Player player = new Player();
        int equipo_nuevo;
        DaoPlayer db = new DaoPlayer();
        String[] opt={"1- Si ✅","2- No ❌","3- Sortir 🏃‍♂️"};


        if (!traspas_equip) {
            Vista.imprimirMensajeSeguido("Dime el nombre de un jugador: ");
            nom = scan.nextLine();
            Vista.imprimirMensaje ("Buscando...");
        }else{
            Vista.imprimirMensajeSeguido("Confirma el nombre: ");
            nom = scan.nextLine();
            Vista.imprimirMensaje ("Recuperando datos...");
        }


        if (VerificarNombre(nom)){
            //todo probar lo del update con el where
            player.setId(Model.obtenerIdJugador(nom));
            player=db.read(player);
            Vista.mostrarGenerico(player,true);
            Vista.saltoLinea();
            switch (Menu.confirmMenu("És aquest el jugador?",opt)){
                case 1:
                    Vista.imprimirMensajeSeguido("Introdueix el nou equip: ");
                    // todo verificar el nombre
                    equipN=scan.nextLine();
                    Vista.imprimirMensaje("Buscando equipo...");
                    equipo_nuevo= Model.obtenerIdEquipo(equipN);
                    Vista.imprimirMensaje("traspasando jugador ...");
                    player.setEquip_actual(equipo_nuevo);
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

        } else {
            throw new IllegalArgumentException("El nombre no es correcto");
        }
    }

    public static void retirarJugador() throws SQLException {
        String nom;
        HistoricPlayers retirado;
        String nom_equip;
        Player playerRet=new Player();
        DaoPlayer dbp = new DaoPlayer();

        Players_stats ps=new Players_stats();
        DaoPlayerStats dbs= new DaoPlayerStats();

        DaoHistoricPlayers db=new DaoHistoricPlayers();


        Vista.imprimirMensajeSeguido("Introduce el jugador que quieres retirar: ");
        nom=scan.nextLine().trim();

        if (VerificarNombre(nom)){
            Vista.imprimirMensaje("Guardando datos...");
            //todo a partir del id, leer y guardar un objeto de player y un objeto de stats
            playerRet.setId(Model.obtenerIdJugador(nom));
            playerRet=dbp.read(playerRet);
            ps.setId_jugador(Model.obtenerIdJugador(nom));
            ps=dbs.read(ps);
            Vista.imprimirMensaje("Retirando jugador...");
            //todo acceder a los datos de player stats, obtener el id, los stats y el nombre del ultimo equipo de players
            nom_equip=Model.obtenerNombreEquipo(playerRet.getEquip_actual());
            retirado=new HistoricPlayers(playerRet.getId(), ps.getAvg_puntos(), ps.getAvg_rebotes(),ps.getAvg_asistencias(),nom_equip);

           if (db.create(retirado)){
               dbs.delete(ps);
               dbp.delete(playerRet);
               Vista.imprimirMensaje("Jugador retirado con exito!");
           }


        }

    }

}

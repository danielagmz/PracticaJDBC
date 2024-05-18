package Controlador;

import Model.Dao.DaoMatch;
import Model.Dao.DaoPlayer;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    public static void ListarJugadores(){
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
            Vista.recorrerLista(equipo.ResultPartits(nom));
        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }
    private static boolean VerificarId(String id){
        return id.matches("(\\\\d+)");
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

    public static void InsertarJugador(){
        String nom;
        int altura,peso,equip_actual;
        System.out.print("Nombre del jugador: ");
        nom = scan.nextLine();
        System.out.print("Altura del jugador: ");
        altura = scan.nextInt();
        System.out.print("Peso del jugador: ");
        peso = scan.nextInt();
        System.out.println("Equipo del jugador: ");
        equip_actual = scan.nextInt();
        if (VerificarNombre(nom)){

        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

}

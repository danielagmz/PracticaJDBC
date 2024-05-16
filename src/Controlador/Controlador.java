package Controlador;

import Model.Dao.DaoPlayer;
import Model.Player;
import Vista.Vista;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    public static void ListarJugadores(){
        String nom;
        System.out.print("Dime un equipo: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje ("Buscando...");
        if (VerificarNombre(nom)){
            DaoPlayer player = new DaoPlayer();
            Vista.recorrerLista(player.listarTodos(nom));
        } else {
            Vista.imprimirMensaje("El nombre no es correcto");
        }
    }

    public static void MedianaJugador(){
        String nom;
        System.out.print("Dime un nombre de un jugador: ");
        nom = scan.nextLine();
        Vista.imprimirMensaje ("Buscando...");
        if (VerificarNombre(nom)){
            DaoPlayer player = new DaoPlayer();
            Vista.recorrerLista(player.MedianasJugadores(nom));
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

}

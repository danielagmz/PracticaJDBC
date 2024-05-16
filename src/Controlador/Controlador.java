package Controlador;

import Model.Dao.DaoPlayer;

import java.sql.*;
import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    public static void ListarJugadores(){
        String nom;
        System.out.print("Dime un equipo: ");
        nom = scan.nextLine();
        if (VerificarId(nom)){
            DaoPlayer player = new DaoPlayer();
            player.listarTodos(nom);
        }
    }
    private static boolean VerificarId(String id){
        return id.matches("(\\\\d+)");
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

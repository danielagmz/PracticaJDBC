package Controlador;


import Vista.Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    public static Connection connection(){

        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba","perepi","pastanaga");
        } catch (SQLException e) {
            Vista.imprimirMensaje("Ha habido un problema con la ruta de la conexion");
        }

        return null;
    }
    public static void close(Connection con) {
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                Vista.imprimirMensaje(e.getMessage());
            }
        }
    }
    public static void close(PreparedStatement smt){
        if (smt != null) {
            try {
                smt.close();
            } catch (SQLException e) {
                Vista.imprimirMensaje(e.getMessage());
            }
        }
    }

}

package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    public static void connection(){

        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba","perepi","pastanaga");
            Statement statement= connection.createStatement();

//            ResultSet resultSet= statement.executeQuery("select * from players");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

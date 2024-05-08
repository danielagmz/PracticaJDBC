import java.sql.*;

public class Model {
    public static void connection(){

        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba","perepi","pastanaga");
            Statement statement= connection.createStatement();

            ResultSet resultSet= statement.executeQuery("select * from players");
            while(resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


}

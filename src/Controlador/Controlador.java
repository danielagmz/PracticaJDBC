package Controlador;

import java.sql.*;

public class Controlador {
    private static void VerificarId(int id){

    }
    private static boolean ComprobarId(int id,String tabla){
        Connection con = null;
        try {
            con = Conexion.connection();
            if (con != null){
                Statement statement = con.createStatement();

                    // Create and execute a SELECT SQL statement.
                    String selectSql = "SELECT * from" + tabla;
                    ResultSet resultSet = statement.executeQuery(selectSql);
                    if (resultSet == null){
                        return false;
                }
                    // Print results from select statement
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    }
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
        }


return false;
    }

}

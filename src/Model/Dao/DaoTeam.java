package Model.Dao;
import Controlador.Conexion;
import Model.Match;
import Model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTeam implements DAODB<Team> {
    @Override
    public boolean create(Team team) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
           con = Conexion.connection();
           if (con != null){
               smt = con.prepareStatement("INSERT INTO teams(nom) VALUES(?)");
               smt.setString(1, team.getNombre());
               int rows = smt.executeUpdate();
               return rows > 0;
           } else {
               throw new SQLException("No se ha podido establecer la conexion");
           }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return false;
    }

    @Override
    public Team read(Team team) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT * FROM teams WHERE id=?");
                smt.setInt(1,team.getId());

                ResultSet mResult = smt.executeQuery();
                if (mResult.next()){
                    Team m = new Team();
                    m.setId(mResult.getInt(1));
                    m.setNombre(mResult.getString(2));

                    return m;
                }
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return null;
    }

    @Override
    public boolean update(Team team) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("UPDATE teams SET nom=? WHERE id=?");
                smt.setString(1,team.getNombre());
                smt.setInt(2,team.getId());

                int rows = smt.executeUpdate();
                return  rows > 0;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return false;
    }

    @Override
    public boolean delete(Team team) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("DELETE FROM teams WHERE id=?");
                smt.setInt(1,team.getId());
                int rows =smt.executeUpdate();
                if (rows > 0){
                    return true;
                }
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return false;
    }


    public List<Team> listarTodos() {
        return null;
    }
}

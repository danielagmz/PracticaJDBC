package Model.Dao;

import Controlador.Conexion;
import Model.Player;
import Model.PlayerMatches;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPlayerMatches implements DAODB<PlayerMatches>{

    @Override
    public boolean create(PlayerMatches playerMatches) {
       return false;
    }

    @Override
    public PlayerMatches read(PlayerMatches pm) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT * FROM players_matches WHERE id_match=? AND id_jugador=?");
                smt.setInt(1,pm.getId_match());
                smt.setInt(2,pm.getId_jug());

                ResultSet mResult = smt.executeQuery();
                if (mResult.next()){
                    PlayerMatches m = new PlayerMatches();
                    m.setId_match(mResult.getInt(1));
                    m.setId_jug(mResult.getInt(2));
                    m.setPunts(mResult.getInt(3));
                    m.setRebots(mResult.getInt(5));
                    m.setAssist(mResult.getInt(4));
                    return m;
                }
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return null;
    }

    @Override
    public boolean update(PlayerMatches pm) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("UPDATE players_matches SET punts=?,rebots=?,assistencies=? WHERE id_match=? AND id_jugador=?");
                smt.setInt(1,pm.getPunts());
                smt.setInt(2,pm.getRebots());
                smt.setInt(3,pm.getAssist());
                smt.setInt(4,pm.getId_match());
                smt.setInt(5,pm.getId_jug());
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
    public boolean delete(PlayerMatches playerMatches) {
        return false;
    }
}

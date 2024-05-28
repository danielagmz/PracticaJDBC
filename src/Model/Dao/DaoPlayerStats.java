package Model.Dao;

import Controlador.Conexion;
import Model.Players_stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPlayerStats implements DAODB<Players_stats>{

    @Override
    public boolean create(Players_stats playersStats) {
        return false;
    }

    @Override
    public Players_stats read(Players_stats ps) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT * FROM player_stats WHERE id_jugador=?");
                smt.setInt(1,ps.getId_jugador());

                ResultSet mResult = smt.executeQuery();
                if (mResult.next()){
                    Players_stats m = new Players_stats();
                    m.setId_jugador(mResult.getInt(1));
                    m.setAvg_puntos(mResult.getInt(2));
                    m.setAvg_rebotes(mResult.getInt(3));
                    m.setAvg_asistencias(mResult.getInt(4));
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
    public boolean update(Players_stats playersStats) {
        return false;
    }

    @Override
    public boolean delete(Players_stats ps) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("DELETE FROM player_stats WHERE id_jugador=?");
                smt.setInt(1,ps.getId_jugador());
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
}

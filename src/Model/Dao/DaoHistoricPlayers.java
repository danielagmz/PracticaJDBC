package Model.Dao;

import Controlador.Conexion;
import Model.HistoricPlayers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoHistoricPlayers implements DAODB<HistoricPlayers>{


    @Override
    public boolean create(HistoricPlayers hp) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("INSERT INTO historic_players(id,punts,rebots,assistencies,ultim_equip) VALUES(?,?,?,?,?)");
                smt.setInt(1,hp.getId_jugador());
                smt.setFloat(2,hp.getPunts());
                smt.setFloat(3,hp.getRebots());
                smt.setFloat(4,hp.getAssist());
                smt.setString(5,hp.getUltim_equip());
                int rows = smt.executeUpdate();
                return rows > 0;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return false;
    }

    @Override
    public HistoricPlayers read(HistoricPlayers historicPlayers) {
        return null;
    }

    @Override
    public boolean update(HistoricPlayers historicPlayers) {
        return false;
    }

    @Override
    public boolean delete(HistoricPlayers historicPlayers) {
        return false;
    }
}

package Model.Dao;

import Controlador.Conexion;
import Model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPlayer implements DAODB<Player>{
    @Override
    public boolean create(Player player) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("INSERT INTO players(nom,alcada,pes,equip_actual) VALUES(?,?,?,?)");
                smt.setString(1,player.getNom());
                smt.setInt(2,player.getAlcada());
                smt.setInt(3,player.getPes());
                smt.setInt(4,player.getEquip_actual());
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
    public Player read(Player player) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT * FROM players WHERE id=?");
                smt.setInt(1,player.getId());

                ResultSet mResult = smt.executeQuery();
                if (mResult.next()){
                    Player m = new Player();
                    m.setId(mResult.getInt(1));
                    m.setNom(mResult.getString(2));
                    m.setAlcada(mResult.getInt(3));
                    m.setPes(mResult.getInt(4));
                    m.setEquip_actual(mResult.getInt(5));
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
    public boolean update(Player player) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("UPDATE players SET nom=?, alcada=?, pes=?, equip_actual=?");
                smt.setString(1, player.getNom());
                smt.setInt(2,player.getAlcada());
                smt.setInt(3,player.getPes());
                smt.setInt(4,player.getEquip_actual());

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
    public boolean delete(Player player) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("DELETE FROM players WHERE id=?");
                smt.setInt(1,player.getId());
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

    @Override
    public List<Player> listarTodos() {
        return null;
    }
}

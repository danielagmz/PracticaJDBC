package Model.Dao;

import Controlador.Conexion;
import Model.Player;
import Model.Players_stats;
import Vista.Vista;

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

    public List<Players_stats> MedianasJugadores(String nom){
        List<Players_stats> jugadores = new ArrayList<>();
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT id FROM players WHERE nom=?");
                smt.setString(1,nom);
                ResultSet id_jugador = smt.executeQuery();
                if (id_jugador.next()){
                    smt = con.prepareStatement("SELECT * FROM player_stats WHERE id_jugador=?");
                    smt.setInt(1,id_jugador.getInt(1));
                    ResultSet jugadors_media = smt.executeQuery();
                    while (jugadors_media.next()){
                        Players_stats p = new Players_stats();
                        p.setId_jugador(jugadors_media.getInt(1));
                        p.setAvg_puntos(jugadors_media.getFloat(2));
                        p.setAvg_rebotes(jugadors_media.getFloat(3));
                        p.setAvg_asistencias(jugadors_media.getFloat(4));
                        jugadores.add(p);
                    }
                } else {
                    Vista.imprimirMensaje("Ha ocurrido un error al buscar las medianas del jugador");
                }
                return jugadores;
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


    public List<Player> listarTodos(String nom) {
        List<Player> jugadores = new ArrayList<>();
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT id FROM teams WHERE nom=?");
                smt.setString(1,nom);
                ResultSet id_equipo = smt.executeQuery();
                if (id_equipo.next()){
                    smt = con.prepareStatement("SELECT * FROM players WHERE equipo_actual=?");
                    smt.setInt(1,id_equipo.getInt("id"));
                    ResultSet jugadors_team = smt.executeQuery();
                    while (jugadors_team.next()){
                        Player p = new Player();
                        p.setId(jugadors_team.getInt(1));
                        p.setNom(jugadors_team.getString(2));
                        p.setAlcada(jugadors_team.getInt(3));
                        p.setPes(jugadors_team.getInt(4));
                        p.setEquip_actual(jugadors_team.getInt(5));
                        jugadores.add(p);
                    }
                } else {
                    Vista.imprimirMensaje("Ha ocurrido un error al buscar los jugadores de ese equipo");
                }
                return jugadores;
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
}

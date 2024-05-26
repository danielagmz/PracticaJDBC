package Model;

import Controlador.Conexion;
import Controlador.Controlador;
import Vista.Vista;

import javax.print.attribute.standard.MediaSize;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public static int obtenerIdEquipo(String nombre) throws SQLException {
        int id_equipo = -1;

        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id FROM teams WHERE nom=?");
                smt.setString(1, nombre);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    id_equipo = resultado.getInt(1);
                }
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new SQLException("Ha ocurrido un error al buscar este equipo");
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        // retorna menos uno si no se encuentran registros
        return id_equipo;
    }

    public static int obtenerIdJugador(String nombre) throws SQLException {
        int id_jug = -1;

        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id FROM players WHERE nom=?");
                smt.setString(1, nombre);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    id_jug = resultado.getInt(1);
                }
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new SQLException("Ha ocurrido un error al buscar este jugador");
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        // retorna menos uno si no se encuentran registros
        return id_jug;
    }

    public static String obtenerNombreEquipo(int id) throws SQLException {
        String nombre = null;
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT nom FROM teams WHERE id=?");
                smt.setInt(1, id);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    nombre = resultado.getString(1);
                }
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            Vista.imprimirMensaje("Ha ocurrido un error al buscar este equipo");
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        // retorna menos uno si no se encuentran registros
        return nombre;
    }
    public static int obtenerIdEquipoNomComplet(String nombre){
        Connection con = null;
        PreparedStatement smt = null;
        int id = 0;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT id FROM teams WHERE nom_complet=?");
                smt.setString(1,nombre);
                ResultSet id_team = smt.executeQuery();
                if (id_team.next()){
                    id = id_team.getInt(1);
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
        return id;
    }


    public static List<PlayerMatches> obtenerResultPartidos(String nombre) {
        Connection con = null;
        PreparedStatement smt = null;
        List<PlayerMatches> resultados = new ArrayList<>();
        int id_jugador;
        try {
            id_jugador = obtenerIdJugador(nombre);
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id_match,punts,rebots,assistencies FROM players_matches WHERE id_jugador=?");
                smt.setInt(1, id_jugador);
                ResultSet jugadors = smt.executeQuery();
                while (jugadors.next()) {
                    PlayerMatches p = new PlayerMatches();
                    p.setId_match(jugadors.getInt(1));
                    p.setPunts(jugadors.getInt(2));
                    p.setRebots(jugadors.getInt(3));
                    p.setAssist(jugadors.getInt(4));
                    resultados.add(p);
                }

                return resultados;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e) {
            Vista.imprimirMensaje(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
      return resultados;
    }


    public static List<String> obtenerPartidos(int id_jugador){
        List<String> partidos = new ArrayList<>();
        Connection con;
        PreparedStatement smt;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("CALL PartidosJugadores(?)");
                smt.setInt(1,id_jugador);
                ResultSet partido = smt.executeQuery();
                while (partido.next()){
                    String col1 = partido.getString(1);
                    partidos.add(col1);
                }
                return partidos;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            Vista.imprimirMensaje(e.getMessage());
        }
        return partidos;
    }

}



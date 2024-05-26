package Model;

import Controlador.Conexion;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {

    public static int obtenerIdEquipo(String nombre) throws SQLException {
        int id_equipo=-1;

        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id FROM teams WHERE nom=?");
                smt.setString(1, nombre);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    id_equipo=resultado.getInt(1);
                }
            } else {
                throw new SQLException();
            }
        }catch (SQLException e){
            throw new SQLException("Ha ocurrido un error al buscar este equipo");
        }
        finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        // retorna menos uno si no se encuentran registros
        return id_equipo;
    }

    public static int obtenerIdJugador(String nombre) throws SQLException {
        int id_jug=-1;

        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id FROM players WHERE nom=?");
                smt.setString(1, nombre);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    id_jug=resultado.getInt(1);
                }
            } else {
                throw new SQLException();
            }
        }catch (SQLException e){
            throw new SQLException("Ha ocurrido un error al buscar este jugador");
        }
        finally {
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
                    nombre=resultado.getString(1);
                }
            } else {
                throw new SQLException();
            }
        }catch (SQLException e){
            Vista.imprimirMensaje("Ha ocurrido un error al buscar este equipo");
        }
        finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        // retorna menos uno si no se encuentran registros
        return nombre;
    }

}

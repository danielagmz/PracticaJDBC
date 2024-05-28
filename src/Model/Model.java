package Model;

import Controlador.Conexion;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    /**
     * funcion que se conecta a la bd y obtiene el id del equipo a partir del nombre
     * @param nombre nombre del equipo
     * @return retorna -1 si no se encuentra el equipo en cuestion
     * @throws SQLException si ha habido un error con la conexion en la bd retorna un error personalizado
     */
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


    /**
     * funcion que se conecta a la bd y obtiene el id de un jugador a partir de su nombre
     * @param nombre nombre del jugador
     * @return retorna -1 si no se encuentra o el id si si
     * @throws SQLException lana un error personalzado si no se puede conectar a la BD
     */

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

    public static String obtenerNombreJugador(int id_jugador) throws SQLException {
        String nom = "";

        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("SELECT nom FROM players WHERE id=?");
                smt.setInt(1, id_jugador);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    nom = resultado.getString(1);
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
        return nom;
    }

    /**
     * funcion inversa a la de obtener el id de un equipo
     * @param id id del equipo
     * @return retorna el nombre del equipo o null si no se encuentra
     * @throws SQLException lanza una excepcion personalizada si no se puede conectar
     */
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
        int id = -1;
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


    /**
     * funcion que retorna los datos de la tabla de player-matches
     * @param nombre indica el nombre del jugador cuyos datos seran buscados
     * @return retona la lista de los partidos que ha jugado ese jugador concreto
     */
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
                ResultSet resultado = smt.executeQuery();
                while (resultado.next()) {
                    PlayerMatches p = new PlayerMatches();
                    p.setId_match(resultado.getInt(1));
                    p.setPunts(resultado.getInt(2));
                    p.setRebots(resultado.getInt(3));
                    p.setAssist(resultado.getInt(4));
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

    /**
     * Obtiene los partidos en un formato concreto haciendo uso de un procedure en la BD
     * este procedure hace un select concat de cada partido-jugador ex -> lakers-nets
     * @param id_jugador indica el id del jugador cuyos partidos seran obtenidos
     * @return retorna una lista de stings con el concat de cada partido [visitante-local,visitante-local...]
     */
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
                    //  en una columna visitante-local
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

    /**
     * Funcion para buscar la palabra clave que el usuario a introducido en la base de datos
     * @param pal_clave Palabra que servira para hacer la busqueda generica en la base de datos
     * @param tabla Tabla donde la funcion hará la select
     * @return Retorna un ArrayList con todos los equipos o jugadores que tengan similitud con pal_clave
     */
    public static List<String> buscadorBD(String pal_clave , String tabla) {
        List<String> selecciones = new ArrayList<>();
        String sql = "SELECT nom FROM "+ tabla +" WHERE nom LIKE ?";
        try (Connection conn = Conexion.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + pal_clave  + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                selecciones.add(rs.getString(1));
            }
        } catch (SQLException e) {
            Vista.imprimirMensaje(e.getMessage());
        }
        return selecciones;
    }
}



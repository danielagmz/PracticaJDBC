package Model.Dao;

import Controlador.Conexion;
import Model.Match;
import Vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMatch implements DAODB<Match>{
    // funciones crud para la escritura en la tabla match
    @Override
    public boolean create(Match match) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null) {
                smt = con.prepareStatement("INSERT INTO matches(id_visitante,punts_visitant,id_local,punts_local) VALUES(?,?,?,?);");
                smt.setInt(1, match.getVisitante_id());
                smt.setInt(2, match.getPuntos_visitante());
                smt.setInt(3, match.getLocal_id());
                smt.setInt(4, match.getPuntos_local());
                int rows = smt.executeUpdate();
                return rows > 0;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }

        } catch (SQLException e) {
            Vista.imprimirMensaje(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }


        return false;
    }

    @Override
    public Match read(Match match) {
        Connection con = null;
        PreparedStatement smt = null;


        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("SELECT * FROM matches WHERE id=?");
                smt.setInt(1,match.getId());
                // executeQuery porque no se trata de una instrucción DDl
                ResultSet mResult= smt.executeQuery();
                if(mResult.next()){
                    Match m= new Match();
                    m.setId(mResult.getInt(1));
                    m.setVisitante_id(mResult.getInt(2));
                    m.setPuntos_visitante(mResult.getInt(3));
                    m.setLocal_id(mResult.getInt(4));
                    m.setPuntos_local(mResult.getInt(5));

                    return m;
                }

            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            Vista.imprimirMensaje(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }


        return null;
    }

    @Override
    public boolean update(Match match) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("UPDATE matches SET id_visitante=?, punts_visitant=?, id_local=?, punts_local=? WHERE id=?");
                smt.setInt(1,match.getVisitante_id());
                smt.setInt(2,match.getPuntos_visitante());
                smt.setInt(3,match.getLocal_id());
                smt.setInt(4,match.getPuntos_local());
                smt.setInt(5,match.getId());
                // si en la base de datos no se modifico nada el execute update retorna 0;
                int rows = smt.executeUpdate();
                return  rows > 0;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return false;
    }

    @Override
    public boolean delete(Match match) {
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                smt = con.prepareStatement("DELETE FROM matches WHERE id=?");
                smt.setInt(1,match.getId());
                // update porque se trata de una modificacion en la base de datos
                int rows =smt.executeUpdate();
                if (rows > 0){
                    return true;
                }
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
          Vista.imprimirMensaje(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }
        return false;
    }

    public List<String> resultPartits(String nom){
        List<String> partidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = Conexion.connection();
            if (con != null){
                // partidos es un procedure dentro de la base de datos que ejecuta un select con un concat
                // en un formato concreto ex → lakers - nets: 103 - 20
                smt = con.prepareStatement("CALL Partidos(?)");
                smt.setString(1,nom);
                ResultSet resultado = smt.executeQuery();
                while (resultado.next()){
                    // retorna una sola columna con todos los partidos en el formato correcto y se guarda en
                    // la lista a retornar
                    String partido = resultado.getString(1);
                    partidos.add(partido);
                }
                return partidos;
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

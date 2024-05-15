package Model.Dao;

import Controlador.Conexion;
import Model.Match;
import com.sun.source.tree.IfTree;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoMatch implements DAODB<Match>{
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
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(con);
            Conexion.close(smt);
        }


        return false;
    }

    @Override
    public ArrayList<Match> read(Match match) {

        return null;
    }

    @Override
    public boolean update(Match match) {
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
    public List<Match> listarTodos() {
        return null;
    }
}

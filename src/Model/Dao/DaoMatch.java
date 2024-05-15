package Model.Dao;

import Controlador.Conexion;
import Model.Match;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DaoMatch implements DAODB<Match>{
    @Override
    public boolean create(Match match) {
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
        return false;
    }

    @Override
    public List<Match> listarTodos() {
        return null;
    }
}

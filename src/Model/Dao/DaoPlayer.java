package Model.Dao;

import Model.Player;

import java.util.ArrayList;
import java.util.List;

public class DaoPlayer implements DAODB<Player>{
    @Override
    public boolean create(Player player) {
        return false;
    }

    @Override
    public ArrayList<Player> read(Player player) {
        return null;
    }

    @Override
    public boolean update(Player player) {
        return false;
    }

    @Override
    public boolean delete(Player player) {
        return false;
    }

    @Override
    public List<Player> listarTodos() {
        return null;
    }
}

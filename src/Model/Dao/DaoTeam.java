package Model.Dao;
import Model.Team;
import java.util.ArrayList;
import java.util.List;

public class DaoTeam implements DAODB<Team> {
    @Override
    public boolean create(Team team) {
        return false;
    }

    @Override
    public ArrayList<Team> read(Team team) {
        return null;
    }

    @Override
    public boolean update(Team team) {
        return false;
    }

    @Override
    public boolean delete(Team team) {
        return false;
    }

    @Override
    public List<Team> listarTodos() {
        return null;
    }
}

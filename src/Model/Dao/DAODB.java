package Model.Dao;

import java.util.ArrayList;
import java.util.List;

public interface DAODB<T> {
    boolean create (T t);
    T read(T t);
    boolean update (T t);
    boolean delete(T t);

    List<T> listarTodos();
}

package Model.Dao;

public interface DAODB<T> {
    boolean create (T t);
    T read(T t);
    boolean update (T t);
    boolean delete(T t);


}

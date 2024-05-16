import Controlador.Controlador;
import Model.Dao.DaoMatch;
import Model.Match;
import Vista.Vista;

public class prueba {
    public static void main(final String[] args) {
        Match m1 = new Match(1,2,2,3);

//        Match m2 = new Match();
//        m2.setId(21);

        Match m3=new Match();
        m3.setId(21);

        DaoMatch db=new DaoMatch();

//        if (db.create(m1)){
//            Vista.imprimirMensaje("creado: "+m1);
//        }else {
//            Vista.imprimirMensaje("no creado: "+m1);
//        }

//        if (db.delete(m2)){
//            Vista.imprimirMensaje("Registro eliminado "+ m2);
//        }else{
//            Vista.imprimirMensaje(m2+ " no se ha podido eliminar");
//        }
        System.out.println("obteniendo...");
        Match a=db.read(m3);
        if (a!=null) {
            System.out.println(a);
        }else{
            System.out.println("error");
        }








    }
}

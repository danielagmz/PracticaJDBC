import Controlador.Menu;
import Vista.Vista;

public class Main {
    public static void main(final String[] args) {
        Vista.imprimirMensaje("BENVINGUT");
        try{
            Menu.menuPrincipal();
        }catch (Exception e){
            Vista.imprimirMensaje(e.getMessage());
        }

    }
}

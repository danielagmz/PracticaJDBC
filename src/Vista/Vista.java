package Vista;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Vista {
   public static void imprimirMensaje(String mensaje){
       System.out.println(mensaje);
   }

   public static void imprimirMensajeSeguido(String mensaje){
       System.out.print(mensaje);
   }

    private static void mostrarArbre(JSONObject jsonObject, String prefix) {

        for (Object key : jsonObject.keySet()) {
            System.out.println(prefix + key);
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                mostrarArbre((JSONObject) value, prefix + "\t");
            } else if (value instanceof JSONArray array) {
                for (Object item : array) {
                    mostrarArbre((JSONObject) item, prefix + "\t");
                }
            } else {
                System.out.println(prefix + "\t" + value);
            }
        }
    }
}

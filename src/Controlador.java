import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.net.URLConnection;
//import nba_api.stats.endpoints.TeamInfoCommon;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Controlador {

    public static void main(final String[] args) throws IOException, ParseException, URISyntaxException {
//        URL url = new URI("https://stats.nba.com/stats/playerindex?Country=Spain").toURL();
//        URLConnection urlcon = url.openConnection();
//        urlcon.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
//        JSONParser parser=new JSONParser();
//
//        Object obj = parser.parse(new InputStreamReader(urlcon.getInputStream()));
//        JSONObject jsonObject=((JSONObject) obj);

//      mostrarArbre(jsonObject,"¬");
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

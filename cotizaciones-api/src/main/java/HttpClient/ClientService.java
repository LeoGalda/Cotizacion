package HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


/**
 *
 * @author leogalda
 */
public class ClientService {    
    URL url;
    HttpURLConnection connection;
    StringBuilder content;
    
    public ClientService(){
        this.connection = null;        
    }

    public JSONObject doGet(String url) throws Exception {
        this.url = new URL(url);
        this.agregarDatosDeConeccionGET();
        if(this.connection.getResponseCode() != 200){
            throw  new Exception("Fallo la coneccion : HTTP error code :"+ this.connection.getResponseCode());
        }
        this.obtenerDatos();              
        return new JSONObject(content.toString());        
    }

    private void agregarDatosDeConeccionGET() throws Exception {
        this.connection = (HttpURLConnection) this.url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.addRequestProperty("User-Agent", "Mozilla");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
    }

    private void obtenerDatos() throws Exception{
        try (BufferedReader input = new BufferedReader(new InputStreamReader(this.connection.getInputStream()))) {
            String line;
            this.content = new StringBuilder();
            while ((line = input.readLine()) != null) {                
                this.content.append(line);                
            }            
        } finally {
            this.connection.disconnect();
        }
    }
}

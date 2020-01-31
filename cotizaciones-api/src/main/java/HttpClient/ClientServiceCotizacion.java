package HttpClient;

import org.json.JSONObject;

/**
 *
 * @author leogalda
 */
public class ClientServiceCotizacion extends ClientService{        
    private String key;
    private String url;
    
    public ClientServiceCotizacion(String moneda) {
        this.url = "http://api.cambio.today/v1/quotes/"+moneda+"/ARS/json";        
        this.key = "1507|1FL~3eLSEXid42YTB1HLm73i5pOLDMUk";
    }
    
    public JSONObject doRequest() throws Exception{
        return this.doGet(this.armarUrlCompleta());        
    }            
    
    public String armarUrlCompleta(){
        return this.getUrl() +"?"+this.getParametroKey();
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getKey() {
        return key;
    }

    private String getParametroKey() {
        return "key="+this.getKey();
    }
    
}

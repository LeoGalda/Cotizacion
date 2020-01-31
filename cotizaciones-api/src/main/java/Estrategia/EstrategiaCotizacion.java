package Estrategia;

import HttpClient.ClientServiceCotizacion;
import Util.Log;
import javax.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author leogalda
 */
public abstract class EstrategiaCotizacion {

    protected ClientServiceCotizacion clientCotizacion;

    public EstrategiaCotizacion(String moneda) {
        this.clientCotizacion = new ClientServiceCotizacion(moneda);
    }

    public double solicitarCotizacion(String codigoMoneda) {
        try {
            return this.obtenerValorCotizacion(this.clientCotizacion.doRequest());
        } catch (Exception exc) {
            Log.saveError(exc.getMessage());
            return 0;
        }
    }

    public abstract String getNombreMoneda();
    public abstract String getCodigoMoneda();
    public abstract JsonObject obtenerJsonCotizacion();
    

    public ClientServiceCotizacion getClient() {
        return clientCotizacion;
    }

    public void setClient(ClientServiceCotizacion client) {
        this.clientCotizacion = client;
    }

    private double obtenerValorCotizacion(JSONObject response) {   
       return response.getJSONObject("result").optDouble("value");
    }
}

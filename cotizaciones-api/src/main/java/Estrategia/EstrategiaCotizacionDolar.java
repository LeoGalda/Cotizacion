package Estrategia;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author leogalda
 */
public class EstrategiaCotizacionDolar extends EstrategiaCotizacion{
    
    public EstrategiaCotizacionDolar(){        
        super("USD");
    }
    
    public JsonObject obtenerJsonCotizacion() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("moneda", this.getNombreMoneda());
        builder.add("precio", this.getCotizacionActual());
        return builder.build();
    }
    
    public String getNombreMoneda() {
        return "dolar";
    }
    
    public String getCodigoMoneda() {
        return "USD";
    }

    private double getCotizacionActual() {
       return this.solicitarCotizacion(this.getCodigoMoneda());
    }
}

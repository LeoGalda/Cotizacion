package Estrategia;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author leogalda
 */
public class EstrategiaCotizacionEuro extends EstrategiaCotizacion{
    
    public EstrategiaCotizacionEuro(){        
        super("EUR");
    }
    
    public JsonObject obtenerJsonCotizacion() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("moneda", this.getNombreMoneda());
        builder.add("precio", this.getCotizacionActual());
        return builder.build();
    }
    
    public String getNombreMoneda() {
        return "euro";
    }
    
    public String getCodigoMoneda() {
        return "EUR";
    }

    private double getCotizacionActual() {
       return this.solicitarCotizacion(this.getCodigoMoneda());
    } 
    
}

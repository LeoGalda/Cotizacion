package Estrategia;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author leogalda
 */
public class EstrategiaCotizacionReal extends EstrategiaCotizacion{
    
    public EstrategiaCotizacionReal(){        
        super("BRL");
    }
    
    public JsonObject obtenerJsonCotizacion() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("moneda", this.getNombreMoneda());
        builder.add("precio", this.getCotizacionActual());
        return builder.build();
    }
    
    public String getNombreMoneda() {
        return "real";
    }
    
    public String getCodigoMoneda() {
        return "BRL";
    }

    private double getCotizacionActual() {
       return this.solicitarCotizacion(this.getCodigoMoneda());
    }
}

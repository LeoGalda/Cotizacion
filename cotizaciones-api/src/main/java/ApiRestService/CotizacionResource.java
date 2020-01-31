/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiRestService;

import Estrategia.EstrategiaCotizacion;
import Estrategia.EstrategiaCotizacionDolar;
import Estrategia.EstrategiaCotizacionEuro;
import Estrategia.EstrategiaCotizacionReal;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author leogalda
 */
@Path("cotizacion")
public class CotizacionResource {

    EstrategiaCotizacion estrategia;

    public CotizacionResource() {
        this.estrategia = new EstrategiaCotizacionDolar();
    }

    @GET
    @Path("/dolar")
    public Response getCotizacionDolarJson() {
        this.setEstrategia(new EstrategiaCotizacionDolar());
        JsonObject response = this.estrategia.obtenerJsonCotizacion();
        return this.generateResponse(response);
    }

    @GET
    @Path("/euro")
    public Response getCotizacionEuroJson() {
        this.setEstrategia(new EstrategiaCotizacionEuro());
        JsonObject response = this.estrategia.obtenerJsonCotizacion();
        return this.generateResponse(response);
    }

    @GET
    @Path("/real")
    public Response getCotizacionRealJson() {
        this.setEstrategia(new EstrategiaCotizacionReal());
        JsonObject response = this.estrategia.obtenerJsonCotizacion();
        return this.generateResponse(response);
    }
    
    private Response generateResponse(JsonObject response) {
        return Response.ok(response.toString())
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET")
                .build();
    }

    public EstrategiaCotizacion getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(EstrategiaCotizacion estrategia) {
        this.estrategia = estrategia;
    }
}

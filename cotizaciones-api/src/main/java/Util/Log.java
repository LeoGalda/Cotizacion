
package Util;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author leogalda
 */
public class Log {
    private final static Logger LOGGER = Logger.getLogger("log.cotizacion");
    
    public static void saveError(String msg){
        LOGGER.log(Level.SEVERE, msg);
    }
}

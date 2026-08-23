/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import ve.gob.dem.fasdem.persit.PerOpcion;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;



/**
 * @author marcenrl
 * 
 */

public class ExpOpcion {

	

    public static ArrayList buscarListaporUsuario(String usua) throws NamingException, SQLException, PersonalNotFoundException  {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexionSec();
    	    PerOpcion p = new PerOpcion(c);
    	    return p.buscarListaporUsuario(usua);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    
  
    
    
    
    
}

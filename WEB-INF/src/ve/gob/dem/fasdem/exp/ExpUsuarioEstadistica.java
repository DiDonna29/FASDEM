/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import ve.gob.dem.fasdem.persit.PerUsuarioEstadistica;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpUsuarioEstadistica {

	
    public static ArrayList buscarListaPorTipoFechaEstadistica(String estatus,String tipo,String fechaEstadistica,String fechaEstadisticaHasta) throws NamingException, SQLException, PersonalNotFoundException  {
	Connection c = null;
	try {
	    c = Conexion.getConexion();
	    PerUsuarioEstadistica p = new PerUsuarioEstadistica(c);
	    return p.buscarListaPorTipoFechaEstadistica(estatus, tipo, fechaEstadistica,fechaEstadisticaHasta);
	} finally {
	    Conexion.closeConexion(c);
	}
    }

    
}

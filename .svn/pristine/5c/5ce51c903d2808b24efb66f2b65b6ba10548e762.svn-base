/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.pagos;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.EstatusHojaRuta;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.TipoHojaRuta;
import ve.gob.dem.fasdem.persit.PerTipoHojaRuta;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusHojaRuta;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpTipoHojaRuta {
   
	 public static TipoHojaRuta buscarpoID(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoHojaRuta ps = new PerTipoHojaRuta(cnx);
	    	    return ps.buscarporId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static ArrayList buscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoHojaRuta ps = new PerTipoHojaRuta(cnx);
	    	    return ps.ListarBuscar() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
  
	 
	
	 
	 
	 
}

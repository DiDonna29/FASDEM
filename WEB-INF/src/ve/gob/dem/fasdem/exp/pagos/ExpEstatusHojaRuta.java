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
import ve.gob.dem.fasdem.persit.pagos.PerEstatusHojaRuta;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpEstatusHojaRuta {
   
	 public static EstatusHojaRuta buscarpoID(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEstatusHojaRuta ps = new PerEstatusHojaRuta(cnx);
	    	    return ps.buscarPorId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static ArrayList BuscarListaEstatusHojaRuta() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEstatusHojaRuta ps = new PerEstatusHojaRuta(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
  
	 
	 
	 public static ArrayList BuscarListaEstatusPreordenCambiar() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerEstatusPreOrden ps = new PerEstatusPreOrden(cnx);
	    	    return ps.buscarListaCambiar();
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 
}

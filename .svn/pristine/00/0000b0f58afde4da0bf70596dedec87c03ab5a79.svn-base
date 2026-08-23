/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.persit.administrador.PerBanco;
import ve.gob.dem.fasdem.persit.administrador.PerTipoTramite;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpTipoTramite {
   
 
	 
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoTramite ps = new PerTipoTramite(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 
  
}

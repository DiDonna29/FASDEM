/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.persit.administrador.PerSiniestro;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpSiniestro {
   

	 
	 public static void modificarEstatus(String id_siniestro, String anio) throws Exception  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerSiniestro ps = new PerSiniestro(cnx);
	    	    ps.modificarEstatus(id_siniestro,anio);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
  
}

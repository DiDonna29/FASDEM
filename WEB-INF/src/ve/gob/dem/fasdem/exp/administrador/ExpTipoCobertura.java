/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.TipoCobertura;
import ve.gob.dem.fasdem.persit.administrador.PerBanco;
import ve.gob.dem.fasdem.persit.administrador.PerCobertura;
import ve.gob.dem.fasdem.persit.administrador.PerTipoCobertura;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpTipoCobertura {
   
 
	 
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoCobertura ps = new PerTipoCobertura(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static TipoCobertura buscarPorId(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoCobertura ps = new PerTipoCobertura(cnx);
	    	    return ps.buscarPorId(id) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
  
}

/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
 
import ve.gob.dem.fasdem.bean.Ciudad;
import ve.gob.dem.fasdem.persit.PerCiudad;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpCiudad {

	
 

 
    public static ArrayList ListarBuscar() throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerCiudad p = new PerCiudad(c);
    	    return p.ListarBuscar();
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    public static ArrayList ListarBuscarporEstado(int id) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerCiudad p = new PerCiudad(c);
    	    return p.ListarBuscarPorEstado(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
 
    
    public static Ciudad BuscarPorid(int id) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerCiudad p = new PerCiudad(c);
    	    return p.buscarporId(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    
    
   
    
    
}

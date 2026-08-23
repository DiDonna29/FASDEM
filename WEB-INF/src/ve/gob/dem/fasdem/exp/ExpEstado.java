/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Estado;
import ve.gob.dem.fasdem.persit.PerClinica;
import ve.gob.dem.fasdem.persit.PerEstado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpEstado {

	
 

 
    public static ArrayList ListarBuscar() throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerEstado p = new PerEstado(c);
    	    return p.ListarBuscar();
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
 
    
    public static Estado BuscarPorid(int id) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerEstado p = new PerEstado(c);
    	    return p.buscarporId(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    public static Estado BuscarPoridDependencia(int id) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerEstado p = new PerEstado(c);
    	    return p.buscarporIdDependencia(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    
   
    
    
}

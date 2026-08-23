/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.extension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.Autoridades;
import ve.gob.dem.fasdem.bean.Autorizacion;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.persit.extension.PerAutoridad;
import ve.gob.dem.fasdem.persit.extension.PerAutorizacion;
import ve.gob.dem.fasdem.persit.pagos.PerPreOrdenPago;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpAutoridades {

     public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerAutoridad p = new PerAutoridad(c);
    	    return p.buscarLista();
    	} finally {
    	    Conexion.closeConexion(c);
    	}
     }
     
     
     public static Autoridades BuscarPorId(int id) throws NamingException, SQLException, PersonalNotFoundException {
     	Connection c = null;
     	try {
     	    c = Conexion.getConexion();
     	    PerAutoridad p = new PerAutoridad(c);
     	    return p.buscarPorId(id);
     	} finally {
     	    Conexion.closeConexion(c);
     	}
      }
      
     
     
     

}

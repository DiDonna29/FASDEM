/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.pagos;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.fasdem.persit.pagos.PerTipoEmpleado;
import ve.gob.dem.fasdem.persit.pagos.PerTipoProveedor;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpTipoProveedor {
   
	 public static TipoProveedor buscarporID(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoProveedor ps = new PerTipoProveedor(cnx);
	    	    return ps.buscarPorId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoProveedor ps = new PerTipoProveedor(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
  
	 
	 public static ArrayList BuscarRetencion(int mes, int id, int periodo) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoProveedor ps = new PerTipoProveedor(cnx);
	    	    return ps.buscarRetencion(mes, id, periodo) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
}

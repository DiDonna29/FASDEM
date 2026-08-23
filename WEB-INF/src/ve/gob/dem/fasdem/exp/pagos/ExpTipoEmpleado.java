/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.pagos;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.persit.pagos.PerDetalleFacturaPago;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.fasdem.persit.pagos.PerTipoEmpleado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpTipoEmpleado {
   
	 public static TipoEmpleado buscarporID(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoEmpleado ps = new PerTipoEmpleado(cnx);
	    	    return ps.buscarPorId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerTipoEmpleado ps = new PerTipoEmpleado(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 
		public static void modificarTipoEmpleado(int id_siniestro, int anio,int id_tipo) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
			Connection cnx = null;
			try {
				cnx = Conexion.getConexion();
				PerTipoEmpleado po = new PerTipoEmpleado(cnx);
				po.modificarTipoEmpleado(id_siniestro, anio,id_tipo);
			} finally {
				if (cnx != null)
					Conexion.closeConexion(cnx);
			}
		}
	 
	 
	 
	 
  
}

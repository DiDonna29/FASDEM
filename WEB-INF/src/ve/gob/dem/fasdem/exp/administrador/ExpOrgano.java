/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.persit.administrador.PerEspecialidad;
import ve.gob.dem.fasdem.persit.administrador.PerOrgano;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.fasdem.persit.pagos.PerTipoEmpleado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpOrgano {
   
	 public static Organo buscarporID(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerOrgano ps = new PerOrgano(cnx);
	    	    return ps.buscarPorId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static Organo buscarporDescripcion(String descripcion) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerOrgano ps = new PerOrgano(cnx);
	    	    return ps.buscarPorDescripcion(descripcion);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static void crearOrgano(Organo organo) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerOrgano ps = new PerOrgano(cnx);
	    	    ps.crearOrgano(organo);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static void modificarOrgano(Organo organo) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerOrgano ps = new PerOrgano(cnx);
	    	    ps.modificarOrgano(organo);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerOrgano ps = new PerOrgano(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 
  
}

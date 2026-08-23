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
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.persit.administrador.PerEspecialidad;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.fasdem.persit.pagos.PerTipoEmpleado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpEspecialidad {
   
	 public static Especialidad buscarporID(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEspecialidad ps = new PerEspecialidad(cnx);
	    	    return ps.buscarPorId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
 
	 public static void crearEspecialidad(Especialidad especialidad) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEspecialidad ps = new PerEspecialidad(cnx);
	    	    ps.crearEspecialidad(especialidad);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static Especialidad buscarPorDescripcion(String descripcion) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEspecialidad ps = new PerEspecialidad(cnx);
	    	    return ps.buscarPorDescripcion(descripcion);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
 

	 
	 
	 public static void modificarEspecialidad(Especialidad especialidad) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEspecialidad ps = new PerEspecialidad(cnx);
	    	    ps.modificarEspecialidad(especialidad);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerEspecialidad ps = new PerEspecialidad(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 
  
}

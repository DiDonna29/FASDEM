/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.persit.administrador.PerCausaIngreso;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpCausaIngreso {
   
 
	 
	 
	 
	 public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    return ps.buscarLista() ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static ArrayList buscarPorEsp(int id_especialidad) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    return ps.buscarPorEsp(id_especialidad) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 public static ArrayList buscarPorEspOrg(int id_especialidad,int id_organo) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    return ps.buscarPorEspOrg( id_especialidad, id_organo) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 public static ArrayList buscarPorEspOrgPat(int id_especialidad,int id_organo, int id_patologia) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    return ps.buscarPorEspOrgPat( id_especialidad, id_organo,  id_patologia) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 public static ArrayList buscarPorEspOrgPatTrat(int id_especialidad,int id_organo, int id_patologia, int tratamiento) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    return ps.buscarPorEspOrgPatTrat( id_especialidad, id_organo, id_patologia, tratamiento) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static boolean verificarPorEspOrgPatTrat(int id_especialidad,int id_organo, int id_patologia, int tratamiento) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    return ps.verificarPorEspOrgPatTrat( id_especialidad, id_organo, id_patologia, tratamiento) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static void crearCausaIngreso(int id_especialidad,int id_organo, int id_patologia, int tratamiento) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    ps.crearCausaIngreso(id_especialidad, id_organo,  id_patologia,  tratamiento);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static void modificarCausaIngreso(int id) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCausaIngreso ps = new PerCausaIngreso(cnx);
	    	    ps.modificarCausaIngreso(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
  
}

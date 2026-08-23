/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;


import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.persit.PerPersona;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

public class ExpPersona {
   
	 public static Persona buscarporCedula(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexionBienestar();
	    		PerPersona pp = new PerPersona(cnx);
	    	    return pp.buscarporCedula(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
 
	 public static boolean ExistePersona(String cedula,int id_dependencia) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexionBienestar();
	    		PerPersona pp = new PerPersona(cnx);
	    	    return pp.ExistePersona(cedula,id_dependencia);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static boolean ExistePersona(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexionBienestar();
	    		PerPersona pp = new PerPersona(cnx);
	    	    return pp.ExistePersona(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static boolean ExistePersonaCuenta(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexionBienestar();
	    		PerPersona pp = new PerPersona(cnx);
	    	    return pp.ExistePersonaCuenta(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static boolean ExistePersonaCuentaSigefirrhh(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerPersona pp = new PerPersona(cnx);
	    	    return pp.ExistePersonaCuentaSigefirrhh(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 
}

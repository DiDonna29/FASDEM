/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.persit.PerClinica;
import ve.gob.dem.fasdem.persit.administrador.PerBanco;
import ve.gob.dem.fasdem.persit.administrador.PerCobertura;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpCobertura {
   
 
	 
	

    public static ArrayList buscarCoberturaTipoProvID(int id_cobertura) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
		 Connection cnx = null;
    	try {
    		cnx = Conexion.getConexion();
    		PerCobertura ps = new PerCobertura(cnx);
    	    return ps.buscarCoberturaTipoProvID(id_cobertura);
    	} finally {
    	    Conexion.closeConexion(cnx);
    	}
        }

	
	
	 public static ArrayList BuscarListaPoliza(int id_poliza) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    return ps.buscarPorIdPoliza(id_poliza) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static Cobertura buscarporCoberturaID(int id_cobertura) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    return ps.buscarCobertura(id_cobertura) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
 

	 
	 public static Cobertura buscarporPolizaTipoCobertura(int id_poliza, int id_tipo_cobertura) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    return ps.buscarporPolizaTipoCobertura(id_poliza, id_tipo_cobertura) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
 
	 
	 
	 
	 public static boolean existeCobertura(String id_tipo_cobertura, String id_poliza) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    return ps.ExisteCobertura(id_tipo_cobertura, id_poliza) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static void creaCobertura(Cobertura cobertura) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    ps.crearCobertura(cobertura) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static void modificarCobertura(Cobertura cobertura) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    ps.modificarCobertura(cobertura) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static void modificarEstatus(Cobertura cobertura) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    ps.modificarEstatus(cobertura) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static void crearCoberturaTipoProvCre(int id_cobertura,String [] listTipoProv ) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    ps.crearCoberturaTipoProvCre(id_cobertura,listTipoProv) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
 
	 
	 public static void crearCoberturaTipoProv(int id_cobertura,String [] listTipoProv ) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCobertura ps = new PerCobertura(cnx);
	    	    ps.crearCoberturaTipoProv(id_cobertura,listTipoProv) ;
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

}

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
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.persit.administrador.PerCuenta;
import ve.gob.dem.fasdem.persit.administrador.PerEspecialidad;
import ve.gob.dem.fasdem.persit.administrador.PerOrgano;
import ve.gob.dem.fasdem.persit.pagos.PerEstatusPreOrden;
import ve.gob.dem.fasdem.persit.pagos.PerTipoEmpleado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class ExpCuenta {
   
	 public static Cuenta buscarCuentaBeneficiario(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    return ps.buscarCuentaBeneficiario(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static boolean ExisteCuentaBeneficiario(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    return ps.ExisteCuentaBeneficiario(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static void crearCuentaBeneficiario(Cuenta cuenta, String cedula) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    ps.crearCuentaBeneficiario(cuenta,cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static void crearCuentaBeneficiarioN(CuentaBenef cuenta, String cedula) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    ps.crearCuentaBeneficiarioB(cuenta,cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static void modificarCuenta(int id) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    ps.modificarCuenta(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }


	 public static Cuenta buscarCuentaProveedor(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    return ps.buscarCuentaProveedor(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static int buscarIdProveedor(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    return ps.buscarIdProveedor(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 
	 public static boolean ExisteCuentaProveedor(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    return ps.ExisteCuentaProveedor(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 public static void crearCuentaProveedor(Cuenta cuenta, int id) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    ps.crearCuentaProveedor(cuenta,id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }

	 
	 public static void modificarCuentaProveedor(int id) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    		PerCuenta ps = new PerCuenta(cnx);
	    	    ps.modificarCuentaProveedor(id);
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

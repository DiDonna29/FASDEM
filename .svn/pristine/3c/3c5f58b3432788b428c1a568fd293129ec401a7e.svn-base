/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.administrador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.persit.administrador.PerPoliza;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

public class ExpPoliza {
	public static Poliza buscarporID(int id) throws NamingException, SQLException, PersonalNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.buscarPorId(id);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static Poliza buscarActiva() throws NamingException, SQLException, PersonalNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.buscarActiva();
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static void crearPoliza(Poliza poliza) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			ps.crearPoliza(poliza);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	
	public static Poliza buscarPoliza(Poliza poliza) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.buscarPoliza(poliza);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	
	
	public static void modificarPoliza(Poliza poliza) throws NamingException, SQLException, PersonalNotFoundException, IOException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			ps.modificarPoliza(poliza);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static boolean validarFechaPolizaInicio(Poliza poliza) throws NamingException, SQLException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.validarFechaPolizaInicio(poliza);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static boolean validarFechaPolizaFin(Poliza poliza) throws SQLException, PersonalNotFoundException, NamingException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.validarFechaPolizaFin(poliza);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static boolean validarNombrePoliza(Poliza poliza) throws SQLException, PersonalNotFoundException, NamingException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.validarNombrePoliza(poliza);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList BuscarLista() throws NamingException, SQLException, PersonalNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPoliza ps = new PerPoliza(cnx);
			return ps.buscarLista();
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
}

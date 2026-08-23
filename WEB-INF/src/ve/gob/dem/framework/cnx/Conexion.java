package ve.gob.dem.framework.cnx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Conexion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1215172483036524897L;
	static protected Log log = LogFactory.getLog(Conexion.class);

	public Conexion() {
		super();
	}

	public static Connection getConexion() throws NamingException, SQLException {
		InitialContext jndiContext = new InitialContext();
		DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/fasdem");
		return source.getConnection();
	}

	public static Connection getConexionSec() throws NamingException, SQLException {
		InitialContext jndiContext = new InitialContext();
		DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/seguridad");
		return source.getConnection();
	}

	public static Connection getConexionBienestar() throws NamingException, SQLException {
		InitialContext jndiContext = new InitialContext();
		DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/bienestar");
		return source.getConnection();
	}
	
	public static Connection getConexionServmedico() throws NamingException, SQLException {
		InitialContext jndiContext = new InitialContext();
		DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/servmedico");
		return source.getConnection();
	}

	public static Connection getConexionTesoreria() throws NamingException, SQLException {
		InitialContext jndiContext = new InitialContext();
		DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/sys_tesoreria");
		return source.getConnection();
	}

	public static void closeConexion(Connection argConexion) throws SQLException {
		argConexion.close();
		argConexion = null;
	}

	public static Connection getConexionGlobal() throws NamingException, SQLException {
		InitialContext jndiContext = new InitialContext();
		DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/sys_global");
		return source.getConnection();
	}
}

package ve.gob.dem.framework.seguridad.persistencia;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.seguridad.bean.Accion;
import ve.gob.dem.framework.seguridad.exp.ExpTipoAccion;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;


@SuppressWarnings("rawtypes")
public class PerAccion implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -8987903647103331036L;
	static protected Logger log = Logger.getLogger(PerAccion.class);
	private Connection conexion = null;

	// Constructor que recibe la conexi�n
	public PerAccion(Connection cnx) {
		this.conexion = cnx;
	}

	@SuppressWarnings("unchecked")
	private ArrayList buscar(String args) throws SQLException, PersonalNotFoundException, NamingException, IOException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList arreglo = new ArrayList(0);
		Accion tptemp = null;
		// Aqui hago lo necesario para buscar la informaci�n
		String sql = "Select * from TRAZA_ACCION ";
		if (args != null) {
			sql = sql + args;
		}
		log.info("SQL ACCION " + sql);
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tptemp = new Accion();
				tptemp.setIdAccion(rs.getString("ID_ACCION"));
				tptemp.setDescripcion(rs.getString("DESCRIPCION"));
				tptemp.setFecha(rs.getDate("FECHA_ACCION"));
				try {
					tptemp.setUsuario(ExpUsuario.buscarDatosUsuarioPorAcceso(rs.getString("ID_ACCESO")));
				} catch (Exception e) {
					log.info("Carga de usuario en traza ", e);
				}
				tptemp.setTipo(ExpTipoAccion.buscarPorId(rs.getString("ID_TIPO")));
				tptemp.setIdentificador("IDENTIFICADOR");
				arreglo.add(tptemp);
			}
			// no consiguio ninguno, no dio error pero no devolvio nada
			if (arreglo.size() == 0) {
				throw new PersonalNotFoundException(GenericAction.KEY_NOTFOUND);
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
		return arreglo;
	}

	public Accion buscarPorId(String id) throws PersonalNotFoundException, SQLException, NamingException, IOException {
		String clausula = "where ID_ACCION = " + id;
		return (Accion) this.buscar(clausula).get(0);
	}

	public ArrayList buscarLista() throws PersonalNotFoundException, SQLException, NamingException, IOException {
		return this.buscar(null);
	}

	public void incluirAccion(Accion p) throws SQLException{
		PreparedStatement sentencia = null;
		String sql = "INSERT INTO TRAZA_ACCION (IDENTIFICADOR,ID_ACCESO,DESCRIPCION,ID_TIPO) VALUES (?,?,?,?)";
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, p.getIdentificador()); // IDENTIFICADOR
			sentencia.setString(2, p.getUsuario().getAcceso()); // ID_ACCESO
			sentencia.setString(3, p.getDescripcion()); // DESCRIPCION
			sentencia.setString(4, p.getTipo().getId()); // ID_TIPO
			sentencia.executeUpdate();
		} finally {
			try {
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				throw new SQLException("Error Cerrando SQL" + e.getMessage());
			}
		}
	}

	public ArrayList buscarPorTipoIdentificador(String idTipo, String id) throws PersonalNotFoundException, SQLException, NamingException, IOException{
		String clausula = "where identificador in (" + id + ") and id_tipo in (" + idTipo + ") ORDER BY fecha_accion";
		return this.buscar(clausula);
	}

	public ArrayList buscarPorIdentificador(String id) throws PersonalNotFoundException, SQLException, NamingException, IOException {
		String clausula = " where identificador = '" + id + "'";
		return this.buscar(clausula);
	}
	
	public ArrayList buscarPorTipoIdentificador(String sql) throws PersonalNotFoundException, SQLException, NamingException, IOException{
		String clausula = "WHERE "+ sql + " ORDER BY fecha_accion DESC";
		return this.buscar(clausula);
	}	
}

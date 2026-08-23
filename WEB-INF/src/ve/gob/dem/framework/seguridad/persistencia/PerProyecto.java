package ve.gob.dem.framework.seguridad.persistencia;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.seguridad.bean.Proyecto;

@SuppressWarnings("rawtypes")
public class PerProyecto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4707290345349768545L;
	private Connection conexion = null;

	// Constructor que recibe la conexi�n
	public PerProyecto(Connection cnx) {
		this.conexion = cnx;
	}

	@SuppressWarnings("unchecked")
	private ArrayList buscar(String args) throws PersonalNotFoundException, SQLException {
		// IF ARGS!= NULL;
		// "Select * from tabla" + args
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList arreglo = new ArrayList(0);
		Proyecto tptemp = null;
		// Aqui hago lo necesario para buscar la informaci�n
		String sql = "Select * from PROYECTO ";
		if (args != null) {
			sql = sql + args;
		}
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// tptemp = new
				// Proyecto(rs.getString("ID_SITUACION"),rs.getString("DESCRIP_SITUACION"));
				tptemp = new Proyecto();
				tptemp.setId(rs.getInt("IPROYID"));
				tptemp.setPaginahome(rs.getString("SPROYPAGINAINDEX"));
				tptemp.setPaginalogin(rs.getString("SPROYPAGINALOGIN"));
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

	public Proyecto buscarPorId(int id) throws SQLException, PersonalNotFoundException {
		String clausula = "where IPROYID = " + id;
		return (Proyecto) this.buscar(clausula).get(0);
	}

	public ArrayList buscarLista() throws SQLException, PersonalNotFoundException {
		return this.buscar(null);
	}
}

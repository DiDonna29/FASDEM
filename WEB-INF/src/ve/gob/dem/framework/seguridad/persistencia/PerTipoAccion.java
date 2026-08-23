package ve.gob.dem.framework.seguridad.persistencia;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.SingletonValores;
import ve.gob.dem.framework.seguridad.bean.TipoAccion;

@SuppressWarnings("rawtypes")
public class PerTipoAccion implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = -2251182599068852360L;
	private Connection conexion = null;

	// Constructor que recibe la conexi�n
	public PerTipoAccion(Connection cnx) {
		this.conexion = cnx;
	}

	@SuppressWarnings("unchecked")
	private ArrayList buscar(String args, boolean completo) throws SQLException, PersonalNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList arreglo = new ArrayList(0);
		TipoAccion tptemp = null;
		// Aqui hago lo necesario para buscar la informaci�n
		String sql = "Select * from TRAZA_TIPO ";
		// tptemp = new TipoAccion();
		// arreglo.add(tptemp);
		if (args != null) {
			sql = sql + args;
		}
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tptemp = new TipoAccion();
				tptemp.setId(rs.getString("ID_TIPO"));
				tptemp.setDescripcion(rs.getString("DESCRIPCION_TIPO").toUpperCase());
				tptemp.setProyecto(rs.getInt("IDPROY"));
				if (completo == true) {
					tptemp.setNombreCampo(rs.getString("NOMBRE_CAMPO"));
					tptemp.setNombreTabla(rs.getString("NOMBRE_TABLA"));
				}
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

	public TipoAccion buscarPorId(String id) throws IOException, PersonalNotFoundException, SQLException {
		String clausula = "where ID_TIPO = " + id + " and IDPROY = " + SingletonValores.getSingleton().getProperty(GenericAction.KEY_PROYECTO);
		return (TipoAccion) this.buscar(clausula, false).get(0);
	}

	public ArrayList buscarLista() throws PersonalNotFoundException, SQLException {
		return this.buscar(null, false);
	}
}

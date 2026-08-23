package ve.gob.dem.framework.seguridad.persistencia;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.seguridad.bean.Nodo;

@SuppressWarnings("rawtypes")
public class PerNodo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5604475945638812813L;
	static Logger log = Logger.getLogger(PerNodo.class);
	private Connection conexion = null;

	// Constructor que recibe la conexi�n
	public PerNodo(Connection cnx) {
		this.conexion = cnx;
	}

	@SuppressWarnings("unchecked")
	private ArrayList buscar(String args, boolean conHijos) throws SQLException, PersonalNotFoundException {
		// IF ARGS!= NULL;
		// "Select * from tabla" + args
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList arreglo = new ArrayList(0);
		Nodo tptemp = null;
		// Aqui hago lo necesario para buscar la informaci�n
		String sql = "Select * from MAESTRA_NODOS_USUARIO ";
		if (args != null) {
			sql = sql + args;
		}
		sql = sql + " order by inodoorden";
		log.info("sql nodos " + sql);
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// tptemp = new
				// Proyecto(rs.getString("ID_SITUACION"),rs.getString("DESCRIP_SITUACION"));
				tptemp = new Nodo();
				tptemp.setId(rs.getInt("INODOID"));
				tptemp.setIdPadre(rs.getInt("INODOPADRE"));
				tptemp.setImagen(rs.getString("SNODOIMAGEN"));
				tptemp.setNodoalt(rs.getString("SNODOALT"));
				tptemp.setNodotipo(rs.getString("SNODOTIPO"));
				tptemp.setPagina(rs.getString("SNODOPAGINA"));
				tptemp.setEtiqueta(rs.getString("SNODOETIQUETA"));
				if (conHijos) {
					tptemp.setHijos(this.buscarPrmisosPorIdPadre(rs.getInt("IPROYID"), rs.getString("SUSUALOGIN"), rs.getInt("INODOID")));
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

	public Nodo buscarPorId(int id) throws SQLException, PersonalNotFoundException {
		String clausula = "where IPROYID = " + id;
		return (Nodo) this.buscar(clausula, false).get(0);
	}

	public ArrayList buscarTodos(String usuario, int proyecto) throws SQLException, PersonalNotFoundException {
		String clausula = "where IPROYID = " + proyecto + " and susualogin = '" + usuario + "' and INODOPADRE =0";
		return this.buscar(clausula, true);
	}

	public ArrayList buscarLista() throws SQLException, PersonalNotFoundException {
		return this.buscar(null, true);
	}

	public ArrayList buscarPrmisosPorIdPadre(int id_proy, String login, int id_nodo_padre) throws SQLException, PersonalNotFoundException {
		return this.buscar(" where IPROYID = " + id_proy + " and susualogin = '" + login + "' and inodopadre=" + id_nodo_padre + " and deshabilitado=0", false);
	}
}

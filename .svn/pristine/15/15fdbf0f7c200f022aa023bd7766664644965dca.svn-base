package ve.gob.dem.framework.seguridad.persistencia;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Errores;
import ve.gob.dem.framework.recursos.SingletonValores;
import ve.gob.dem.framework.seguridad.bean.Usuario;

@SuppressWarnings("rawtypes")
public class PerUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058373844552562854L;
	static Logger log = Logger.getLogger(PerUsuario.class);
	private Connection conexion = null;

	// Constructor que recibe la conexi�n
	public PerUsuario(Connection cnx) {
		this.conexion = cnx;
	}

	@SuppressWarnings("unchecked")
	private ArrayList buscar(String args, boolean completo)
			throws SQLException, NumberFormatException, IOException,
			PersonalNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList arreglo = new ArrayList(0);
		Usuario tptemp = null;
		// PerDependencia pd = new PerDependencia();
		// Aqui hago lo necesario para buscar la información
		// String sql =
		// "SELECT USUARIOS.*, USUARIO_ROL_APP.COD_ROL FROM USUARIOS INNER JOIN USUARIO_ROL_APP ON USUARIOS.LOGIN=USUARIO_ROL_APP.LOGIN ";
		String sql = "SELECT USUARIOS.*, USUARIO_ROL_APP.COD_ROL, ROL.DESCRIPCION FROM USUARIOS INNER JOIN USUARIO_ROL_APP ON USUARIOS.LOGIN=USUARIO_ROL_APP.LOGIN LEFT JOIN ROL ON USUARIO_ROL_APP.COD_ROL=ROL.COD_ROL ";
		if (args != null) {
			sql = sql + args;
		}
		// log.info("SQL USUARIOS= "+sql);
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tptemp = new Usuario();
				tptemp.setApellido(rs.getString("APELLIDOS").toUpperCase());
				tptemp.setCedula(rs.getString("CEDULA"));
				tptemp.setLogin(rs.getString("LOGIN"));
				tptemp.setNombre(rs.getString("NOMBRES").toUpperCase());
				// tptemp.setUserRolApp(rs.getString("COD_ROL"));
				// tptemp.setUserRolAppDescripcion(rs.getString("DESCRIPCION").toUpperCase());
				tptemp.setValido(true);
				if (completo == true) {
					PerNodo perNodo = new PerNodo(conexion);
					tptemp.setPermisos(perNodo.buscarTodos(tptemp.getLogin(),
							Integer.valueOf(SingletonValores.getSingleton()
									.getProperty(GenericAction.KEY_PROYECTO))));
					PerProyecto perProyecto = new PerProyecto(conexion);
					tptemp.setProyecto(perProyecto.buscarPorId(Integer
							.valueOf(SingletonValores.getSingleton()
									.getProperty(GenericAction.KEY_PROYECTO))));
					tptemp.setIdDependencia((Integer) this.buscarDetalle(
							tptemp,
							Integer.valueOf(SingletonValores.getSingleton()
									.getProperty(GenericAction.KEY_PROYECTO)),
							GenericAction.DETALLE_DEPENDENCIA).get(0));
					// tptemp.setDependencia(pd.search((Integer)
					// this.buscarDetalle(tptemp,
					// Integer.valueOf(SingletonValores.getSingleton().getProperty(GenericAction.KEY_PROYECTO)),
					// GenericAction.DETALLE_DEPENDENCIA).get(0)));
					/*
					 * try { tptemp.setDetalle(this.buscarDetalle(tptemp,
					 * Integer
					 * .valueOf(SingletonValores.getSingleton().getProperty
					 * (GenericAction.KEY_PROYECTO)),
					 * GenericAction.DETALLE_TRIBUNAL)); } catch (Exception e) {
					 * ArrayList al = new ArrayList(); al.add(new
					 * Integer("0000")); tptemp.setDetalle(al); }
					 */
				}
				arreglo.add(tptemp);
			}
			// no consiguio ninguno, no dio error pero no devolvio nada
			if (arreglo.size() == 0) {
				throw new PersonalNotFoundException(GenericAction.KEY_NOTFOUND);
			}
			return arreglo;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				log.warn("warm", e);
			}
		}
	}

	private Usuario buscarDatosPersonales(String args) throws SQLException,
			NumberFormatException, IOException, PersonalNotFoundException {
		Statement stmt = null;
		ResultSet rs = null;

		Usuario usu = null;
		String sql = "SELECT * FROM USUARIOS ";
		if (args != null) {
			sql = sql + args;
		}
		// log.info("SQL USUARIOS= "+sql);
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				usu = new Usuario();
				usu.setApellido(rs.getString("APELLIDOS").toUpperCase());
				usu.setCedula(rs.getString("CEDULA"));
				usu.setLogin(rs.getString("LOGIN"));
				usu.setNombre(rs.getString("NOMBRES").toUpperCase());
				usu.setIdDependencia((Integer) this.buscarDetalle(
						usu,
						Integer.valueOf(SingletonValores.getSingleton()
								.getProperty(GenericAction.KEY_PROYECTO)),
						GenericAction.DETALLE_DEPENDENCIA).get(0));
				if (rs.getString("USUARIO") == null
						|| "".equals(rs.getString("USUARIO"))) {
					usu.setDescripcion_usuario("VERIFIQUE EL NOMBRE DE USUARIO");
				} else {

					usu.setDescripcion_usuario(rs.getString("USUARIO")
							.toUpperCase());
				}

			}
			// no consiguio ninguno, no dio error pero no devolvio nada
			if (usu == null) {
				throw new PersonalNotFoundException(GenericAction.KEY_NOTFOUND);
			}
			return usu;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				log.warn("warm", e);
			}
		}
	}

	@SuppressWarnings("unused")
	private Integer buscarEntidad(Usuario usu, int proyecto)
			throws PersonalNotFoundException, SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		Integer entidad = 0;
		// Aqui hago lo necesario para buscar la
		// informaciï¿½n
		String sql = "Select ENTIDAD, ID_TIPO_DETALLE from DETALLE where IPROYID = "
				+ proyecto + " and SUSUALOGIN ='" + usu.getLogin() + "'";
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				entidad = new Integer(rs.getString("ENTIDAD"));
			}
			if (entidad == 0) {
				throw new PersonalNotFoundException(new Errores(
						Errores.ERR_ENTIDAD_NOFOUND,
						Errores.buscarDescripcion(Errores.ERR_ENTIDAD_NOFOUND),
						"PerUsuario",
						"buscarEntidad(String login,int proyecto)"));
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entidad;
	}

	@SuppressWarnings("unchecked")
	private ArrayList buscarDetalle(Usuario usu, int proyecto, int tipoDetalle)
			throws PersonalNotFoundException, SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList entidades = new ArrayList();
		// Aqui hago lo necesario para buscar la
		// informaciï¿½n
		String sql = "Select ENTIDAD from DETALLE where IPROYID = " + proyecto
				+ " and SUSUALOGIN ='" + usu.getLogin()
				+ "' and id_tipo_detalle=" + tipoDetalle;
		log.info("SQL renyyyyyyyyyyyyyyyyyyyyyyyyyyyS= "+sql);
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				entidades.add(Integer.valueOf(rs.getString("ENTIDAD")));
			}
			if (entidades.size() == 0) {
				throw new PersonalNotFoundException(new Errores(
						Errores.ERR_ENTIDAD_NOFOUND,
						Errores.buscarDescripcion(Errores.ERR_ENTIDAD_NOFOUND),
						"PerUsuario",
						"buscarEntidad(String login,int proyecto)"));
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entidades;
	}

	public Usuario buscarDatosP(String login) throws NumberFormatException,
			SQLException, IOException, PersonalNotFoundException {
		String clausula = "where LOGIN = '" + login + "'";
		return this.buscarDatosPersonales(clausula);
	}

	public Usuario buscarUsuario(String login) throws NumberFormatException,
			SQLException, IOException, PersonalNotFoundException {
		String clausula = "where LOGIN = '" + login + "'";
		return (Usuario) this.buscar(clausula, true).get(0);
	}

	public Usuario buscarDatosUsuario(String login)
			throws NumberFormatException, SQLException, IOException,
			PersonalNotFoundException {
		String clausula = "where LOGIN = '" + login + "'";
		return (Usuario) this.buscar(clausula, false).get(0);
	}

	public Usuario buscarDatosUsuarioPorAcceso(String id_acceso)
			throws NumberFormatException, SQLException, IOException,
			PersonalNotFoundException {
		String clausula = " where ID_ACCESO = '" + id_acceso + "'";
		String cedula = buscarUsuarioPorAcceso(clausula);
		clausula = "where CEDULA = '" + cedula + "'";
		Usuario usu = (Usuario) this.buscar(clausula, false).get(0);
		usu.setAcceso(id_acceso);
		return usu;
	}

	public void incluirAcceso(String idacceso, String cedula, String maquina)
			throws SQLException, IOException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO TRAZA_ACCESO (ID_ACCESO,CEDULA,COD_APLIC,DIRECCION)"
				+ " VALUES (?,?,?,?)";
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1, idacceso); // ACCESO
			stmt.setString(2, cedula); // CEDULA
			stmt.setString(
					3,
					SingletonValores.getSingleton().getProperty(
							GenericAction.KEY_APLICACION)); // COD_APLIC
			stmt.setString(4, maquina); // DIRECCION
			stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				log.warn("warm", e);
			}
		}
	}

	private String buscarUsuarioPorAcceso(String args) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String resultado = null;
		String sql = "Select cedula from TRAZA_ACCESO ";
		if (args != null) {
			sql = sql + args;
		}
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultado = rs.getString("cedula");
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				log.warn("warm", e);
			}
		}
		return resultado;
	}

	public Usuario IniciarSesion(String login, String maquina)
			throws NumberFormatException, SQLException, IOException,
			PersonalNotFoundException {
		String clausula = "WHERE USUARIO_ROL_APP.COD_APLICACION='"
				+ String.valueOf(SingletonValores.getSingleton().getProperty(
						GenericAction.KEY_APLICACION))
				+ "' AND USUARIOS.LOGIN = '" + login + "'";
		Usuario usu = (Usuario) this.buscar(clausula, true).get(0);
		usu.setAcceso(PerIdAcceso.getInstance().getIDAcceso(conexion));
		this.incluirAcceso(usu.getAcceso(), usu.getCedula(), maquina);
		return (usu);
	}

}

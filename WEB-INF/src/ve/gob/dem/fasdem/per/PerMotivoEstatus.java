package ve.gob.dem.fasdem.per;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

import com.ibatis.sqlmap.client.SqlMapClient;
@SuppressWarnings("rawtypes")
public class PerMotivoEstatus extends PerGeneric {
	static protected Logger log = Logger.getLogger(PerMotivoEstatus.class);
	private static final String T_TABLA = "MotivoEstatus.";
	private SqlMapClient cnx = null;

	public PerMotivoEstatus() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	
	public List list() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat(KEY_LIST), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	@SuppressWarnings("unchecked")
	public List listBySiniestro(HashMap map) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		Usuario usu = null;
		list = cnx.queryForList(T_TABLA.concat("listBySiniestro"), map);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				usu = new Usuario();
				MotivoEstatus me = (MotivoEstatus) list.get(i);
				try {
					usu = ExpUsuario.buscarDatosP(me.getLoginUsuario());
					me.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
				} catch (Exception e) {
				}
				list.set(i, me);
			}
			return list;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}

	public MotivoEstatus search(HashMap map) throws PersonalNotFoundException, SQLException, NumberFormatException, IOException, NamingException {
		MotivoEstatus obj;
		Usuario usu = new Usuario();

		obj = (MotivoEstatus) cnx.queryForObject(T_TABLA.concat("search"), map);
		if (obj != null) {
			usu = ExpUsuario.buscarDatosP(obj.getLoginUsuario());
			obj.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
			return obj;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}

	public MotivoEstatus searchByEstatus(HashMap map) throws PersonalNotFoundException, SQLException, NumberFormatException, IOException, NamingException {
		MotivoEstatus obj;
		Usuario usu = new Usuario();

		obj = (MotivoEstatus) cnx.queryForObject(T_TABLA.concat("searchByEstatus"), map);
		if (obj != null) {
			usu = ExpUsuario.buscarDatosP(obj.getIdUsuario());
			obj.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
			return obj;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}

	public void insert(MotivoEstatus me) throws SQLException {
		try {
			finalizaTodosEstatus(me.getIdSiniestro());
		} catch (Exception e) {
			log.error("error", e);
		}
		cnx.insert(T_TABLA.concat(KEY_INSERT), me);
	}

	public void update(MotivoEstatus me) throws SQLException {
		cnx.insert(T_TABLA.concat(KEY_UPDATE), me);
	}

	public void finalizarEstatus2(int id) throws SQLException {
		cnx.update(T_TABLA.concat("finalizarEstatus"), id);
	}

	public void finalizaTodosEstatus(int id) throws SQLException {
		cnx.update(T_TABLA.concat("finalizaTodosEstatus"), id);
	}
}
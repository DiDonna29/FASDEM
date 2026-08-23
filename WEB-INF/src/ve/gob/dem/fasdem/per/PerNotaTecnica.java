package ve.gob.dem.fasdem.per;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.NotaTecnica;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerNotaTecnica extends PerGeneric {
	private static final String T_TABLA = "NotaTecnica.";
	private SqlMapClient cnx = null;

	public PerNotaTecnica() {
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

	public List listBySiniestro(HashMap map) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		Usuario usu = null;
		list = cnx.queryForList(T_TABLA.concat("listBySiniestro"), map);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				usu = new Usuario();
				NotaTecnica nt = (NotaTecnica) list.get(i);
				try {
					usu = ExpUsuario.buscarDatosP(nt.getLoginUsuario());
					nt.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
				} catch (Exception e) {
				}
				list.set(i, nt);
			}
			return list;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}

	public NotaTecnica search(HashMap map) throws PersonalNotFoundException, SQLException, NumberFormatException, IOException, NamingException {
		NotaTecnica obj;
		Usuario usu = new Usuario();
		ExpUsuario expU = new ExpUsuario();
		obj = (NotaTecnica) cnx.queryForObject(T_TABLA.concat("search"), map);
		if (obj != null) {
			usu = expU.buscarDatosP(obj.getLoginUsuario());
			obj.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
			return obj;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}

	public int insert(NotaTecnica nt) throws SQLException {
		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), nt);
	}
	
	public int insertMedica(NotaTecnica nt) throws SQLException {
		return (Integer) cnx.insert(T_TABLA.concat("insertMedica"), nt);
	}
	
	public List listBySiniestroNotaMedica(HashMap map) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		Usuario usu = null;
		list = cnx.queryForList(T_TABLA.concat("listBySiniestroNotaMedica"), map);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				usu = new Usuario();
				NotaTecnica nt = (NotaTecnica) list.get(i);
				try {
					usu = ExpUsuario.buscarDatosP(nt.getLoginUsuario());
					nt.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
				} catch (Exception e) {
				}
				list.set(i, nt);
			}
			return list;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}
	
	public NotaTecnica searchMedica(HashMap map) throws PersonalNotFoundException, SQLException, NumberFormatException, IOException, NamingException {
		NotaTecnica obj;
		Usuario usu = new Usuario();
		ExpUsuario expU = new ExpUsuario();
		obj = (NotaTecnica) cnx.queryForObject(T_TABLA.concat("searchMedica"), map);
		if (obj != null) {
			usu = expU.buscarDatosP(obj.getLoginUsuario());
			obj.setDesUsuario(usu.getNombre().toUpperCase() + " " + usu.getApellido().toUpperCase());
			return obj;
		} else {
			throw new PersonalNotFoundException(" ");
		}
	}
}
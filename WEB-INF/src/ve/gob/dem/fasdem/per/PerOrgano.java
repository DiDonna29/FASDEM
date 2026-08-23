package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerOrgano extends PerGeneric {

	private static final String T_TABLA = "Organo.";
	private SqlMapClient cnx = null;

	public PerOrgano() {
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

	public Organo buscar(int id) throws PersonalNotFoundException, SQLException {
		Organo obj;
		obj = (Organo) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public Organo porIdPatologiaOrganoTratamiento(int IdPatologiaOrganoTratamiento) throws PersonalNotFoundException, SQLException {
		Organo obj;
		obj = (Organo) cnx.queryForObject(T_TABLA.concat("porIdPatologiaOrganoTratamiento"), IdPatologiaOrganoTratamiento);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List porEspecialidad(int id) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porEspecialidad"), id);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
}
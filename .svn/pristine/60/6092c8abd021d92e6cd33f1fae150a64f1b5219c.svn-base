package ve.gob.dem.fasdem.per;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.Patologias;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerPatologias extends PerGeneric {

	private static final String T_TABLA = "Patologias.";
	private SqlMapClient cnx = null;

	public PerPatologias() {
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

	public Patologias buscar(int id) throws PersonalNotFoundException, SQLException {
		Patologias obj;
		obj = (Patologias) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List porOrganoEspecialidad(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porOrganoEspecialidad"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	
	public void updatePatologia(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updatePatologia"), mapa);
	}
	
	public int insert(HashMap mapa) throws SQLException {

		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);

	}
	
	public Patologias search(int id) throws PersonalNotFoundException, SQLException {
		Patologias obj;
		obj = (Patologias) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	
	
}
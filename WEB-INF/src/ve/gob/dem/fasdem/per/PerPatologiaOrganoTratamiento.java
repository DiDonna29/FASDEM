package ve.gob.dem.fasdem.per;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.PatologiaOrganoTratamiento;
import ve.gob.dem.fasdem.bean.Patologias;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerPatologiaOrganoTratamiento extends PerGeneric {

	private static final String T_TABLA = "PatologiaOrganoTratamiento.";
	private SqlMapClient cnx = null;

	public PerPatologiaOrganoTratamiento() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}
 
	public List list(HashMap m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat(KEY_LIST), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	
	public PatologiaOrganoTratamiento search(int id) throws PersonalNotFoundException, SQLException {
		PatologiaOrganoTratamiento obj;
		obj = (PatologiaOrganoTratamiento) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	
	
}
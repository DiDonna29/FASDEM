package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.Tratamiento;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTratamiento extends PerGeneric {

	private static final String T_TABLA = "Tratamiento.";
	private SqlMapClient cnx = null;

	public PerTratamiento() {
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

	public Tratamiento buscar(int id) throws PersonalNotFoundException, SQLException {
		Tratamiento obj;
		obj = (Tratamiento) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public List porEspOrgPat(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porEspOrgPat"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}	
	public List porDescripcion(String descripcion) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porDescripcion"), descripcion);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}	
	public List porEspOrgPat2(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porEspOrgPat2"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public void updateTratamiento(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateTratamiento"), mapa);
	}
	public int insert(HashMap mapa) throws SQLException {

		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);

	}
}
package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.fasdem.bean.TipoGasto;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTipoGasto extends PerGeneric {

	private static final String T_TABLA = "TipoGasto.";
	private SqlMapClient cnx = null;

	public PerTipoGasto() {
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

	public TipoEnfermedad buscar(int id) throws PersonalNotFoundException, SQLException {
		TipoEnfermedad obj;
		obj = (TipoEnfermedad) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public void updateTipoGasto(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateTipoGasto"), mapa);
	}
	
	public void insert(HashMap mapa) throws SQLException {

		cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);

	}
	
	
	public TipoGasto search(int id) throws PersonalNotFoundException, SQLException {
		TipoGasto obj;
		obj = (TipoGasto) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	
	
}
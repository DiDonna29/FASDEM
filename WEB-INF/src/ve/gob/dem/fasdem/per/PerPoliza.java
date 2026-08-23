package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerPoliza extends PerGeneric {

	private static final String T_TABLA = "Poliza.";
	private SqlMapClient cnx = null;

	public PerPoliza() {
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

	public Poliza search(int id) throws PersonalNotFoundException, SQLException {
		Poliza obj;
		obj = (Poliza) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public Poliza searchActivo() throws PersonalNotFoundException, SQLException {
		Poliza obj;
		obj = (Poliza) cnx.queryForObject(T_TABLA.concat("searchActivo"), null);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

}
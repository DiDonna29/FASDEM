package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.Dependencia;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerDependencia extends PerGeneric {

	private static final String T_TABLA = "Dependencia.";
	private SqlMapClient cnx = null;

	public PerDependencia() {
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

	public Dependencia search(int id) throws PersonalNotFoundException, SQLException {
		Dependencia obj;
		obj = (Dependencia) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	


}
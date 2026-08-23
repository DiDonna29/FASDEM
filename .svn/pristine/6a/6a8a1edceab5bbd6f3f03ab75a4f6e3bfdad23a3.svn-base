package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTipoEmpleado extends PerGeneric {

	private static final String T_TABLA = "TipoEmpleado.";
	private SqlMapClient cnx = null;

	public PerTipoEmpleado() {
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

	public TipoEmpleado search(int  id) throws PersonalNotFoundException, SQLException {
		TipoEmpleado obj;
		obj = (TipoEmpleado) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}


}
package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTipoTratamiento extends PerGeneric {

	private static final String T_TABLA = "TipoTratamiento.";
	private SqlMapClient cnx = null;

	public PerTipoTratamiento() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public List searchTipoTratamiento() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat(KEY_LIST), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
}
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

public class PerImpuesto extends PerGeneric{
	
	private static final String T_TABLA = "Impuesto.";
	private SqlMapClient cnx = null;

	public PerImpuesto() {
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

}

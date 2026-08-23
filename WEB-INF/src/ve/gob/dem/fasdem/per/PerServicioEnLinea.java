package ve.gob.dem.fasdem.per;

import java.sql.SQLException;

import ve.gob.dem.fasdem.bean.ServicioEnLinea;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerServicioEnLinea extends PerGeneric {

	private static final String T_TABLA = "ServicioEnLinea.";
	private SqlMapClient cnx = null;

	public PerServicioEnLinea() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}
	
	public ServicioEnLinea search(int id) throws PersonalNotFoundException, SQLException {
		ServicioEnLinea obj;
		obj = (ServicioEnLinea) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	

}
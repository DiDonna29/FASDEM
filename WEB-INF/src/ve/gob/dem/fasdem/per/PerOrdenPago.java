package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ve.gob.dem.fasdem.bean.OrdenPago;

import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


public class PerOrdenPago extends PerGeneric{
	private static final String T_TABLA = "OrdenPago.";
	private SqlMapClient cnx = null;
	
	public PerOrdenPago() {
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
	
	public OrdenPago busca_siniestro(int id) throws PersonalNotFoundException, SQLException {
		OrdenPago obj;
		obj = (OrdenPago) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

/*	public int insert(HashMap mapa) throws SQLException {

		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);

	}*/

}


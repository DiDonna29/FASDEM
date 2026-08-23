package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.fasdem.bean.TipoTramite;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTipoTramite extends PerGeneric {

	private static final String T_TABLA = "TipoTramite.";
	private SqlMapClient cnx = null;

	public PerTipoTramite() {
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

	public TipoTramite buscar(int id) throws PersonalNotFoundException, SQLException {
		TipoTramite obj;
		obj = (TipoTramite) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public List searchTipoTramite() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat(KEY_LIST), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	public List listByTipoCobertura(int id) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listByTipoCobertura"), id);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}


}
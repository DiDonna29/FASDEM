package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTipoSiniestro extends PerGeneric {

	private static final String T_TABLA = "TipoSiniestro.";
	private SqlMapClient cnx = null;

	public PerTipoSiniestro() {
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

	public TipoProveedor buscar(int id) throws PersonalNotFoundException, SQLException {
		TipoProveedor obj;
		obj = (TipoProveedor) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public List listByTipoTramite(int id) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listByTipoTramite"), id);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}


}
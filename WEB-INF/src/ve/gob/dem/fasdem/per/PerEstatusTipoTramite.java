package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.Dependencia;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerEstatusTipoTramite extends PerGeneric {

	private static final String T_TABLA = "EstatusTipoTramite.";
	private SqlMapClient cnx = null;

	public PerEstatusTipoTramite() {
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

	public EstatusTipoTramite searchByEstatusTipoTramite(HashMap map) throws PersonalNotFoundException, SQLException {
		EstatusTipoTramite obj;
		obj = (EstatusTipoTramite) cnx.queryForObject(T_TABLA.concat("searchByEstatusTipoTramite"), map);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	


}
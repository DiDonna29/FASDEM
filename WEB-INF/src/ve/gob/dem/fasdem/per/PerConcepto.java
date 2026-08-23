package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.dem.fasdem.bean.Concepto;
import ve.gob.dem.fasdem.bean.Proveedor;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerConcepto extends PerGeneric {

	private static final String T_TABLA = "Proveedor.";
	private SqlMapClient cnx = null;

	public PerConcepto() {
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

	public Concepto buscar(int id) throws PersonalNotFoundException, SQLException {
		Concepto obj;
		obj = (Concepto) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
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
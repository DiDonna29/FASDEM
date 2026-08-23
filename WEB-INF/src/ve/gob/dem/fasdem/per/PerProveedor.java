package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Proveedor;
import ve.gob.dem.fasdem.bean.TipoGasto;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerProveedor extends PerGeneric {

	private static final String T_TABLA = "Proveedor.";
	private SqlMapClient cnx = null;

	public PerProveedor() {
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

	public Proveedor buscar(int id) throws PersonalNotFoundException, SQLException {
		Proveedor obj;
		obj = (Proveedor) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public Proveedor porRif(String id) throws PersonalNotFoundException, SQLException {
		Proveedor obj;
		obj = (Proveedor) cnx.queryForObject(T_TABLA.concat("porRif"), id);
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
	public List porNombre(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porNombre"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public List porNombreMod(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porNombreMod"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	
	public List porCombo(int id) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porCombo"), id);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	
	

	public void updateProveedor(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateProveedor"), mapa);
	}
	
	public int insert(HashMap mapa) throws SQLException {

		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);

	}
	public Proveedor search(int id) throws PersonalNotFoundException, SQLException {
		Proveedor obj;
		obj = (Proveedor) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	
}
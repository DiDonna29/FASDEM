package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.DetalleFactura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerDetalleFactura extends PerGeneric {
	private static final String T_TABLA = "DetalleFactura.";
	private SqlMapClient cnx = null;

	public PerDetalleFactura() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public DetalleFactura search(Mapa m) throws PersonalNotFoundException, SQLException {
		DetalleFactura obj;
		obj = (DetalleFactura) cnx.queryForObject(T_TABLA.concat("search"), m);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List<DetalleFactura> searchByFactura(Mapa mapa) throws PersonalNotFoundException, SQLException {
		Log.info("el mapama e s"+mapa);
		List<DetalleFactura> list = new ArrayList<DetalleFactura>();
		list = cnx.queryForList(T_TABLA.concat("searchByFactura"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	public List searchByFacturaIva(Mapa mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchByFacturaIva"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public void delete(int id) throws SQLException {
		cnx.delete(T_TABLA.concat("delete"), id);
	}

	public void deleteIva(Mapa m) throws SQLException {
		cnx.delete(T_TABLA.concat("deleteIva"), m);
	}

	public int insert(HashMap mapa) throws SQLException {
		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);
	}

	public void insertIva(HashMap mapa) throws SQLException {
		cnx.delete(T_TABLA.concat("deleteIva"), mapa);
		this.insert(mapa);
	}
}
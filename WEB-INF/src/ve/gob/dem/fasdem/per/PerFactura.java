package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerFactura extends PerGeneric {
	private static final String T_TABLA = "Factura.";
	private SqlMapClient cnx = null;

	public PerFactura() {
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

	public List listDetallesFactura(HashMap mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listDetallesFactura"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Factura search(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Factura obj;
		obj = (Factura) cnx.queryForObject(T_TABLA.concat("search"), mapa);
		Log.info("objeto " + obj);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Factura searchTrue(int id) throws PersonalNotFoundException, SQLException {
		Factura obj;
		obj = (Factura) cnx.queryForObject(T_TABLA.concat("searchTrue"), id);
		Log.info("objeto " + obj);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Factura searchDetalle(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Factura obj;
		obj = (Factura) cnx.queryForObject(T_TABLA.concat("searchDetalle"), mapa);
		Log.info("objeto " + obj);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Factura searchIdSiniestro(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Factura obj;
		obj = (Factura) cnx.queryForObject(T_TABLA.concat("searchIdSiniestro"), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public List listSearchIdSiniestro(HashMap mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchIdSiniestro"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listSearchDetalle(HashMap mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchDetalle"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Factura searchIdSiniestroIdTipoGasto(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Factura obj;
		obj = (Factura) cnx.queryForObject(T_TABLA.concat("searchIdSiniestroIdTipoGasto"), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listByFacturaRembolso(int id) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listFacturaRembolso"), id);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Factura buscar(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Factura obj;
		obj = (Factura) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), mapa);
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

	public List porNombre(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("porNombre"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List validaFactura(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("validaFactura"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	
	
	
	public int insert(HashMap mapa) throws SQLException {
		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);
	}




	public int insertDetalle(HashMap mapa) throws SQLException {
		return (Integer) cnx.insert(T_TABLA.concat("insertDetalle"), mapa);
	}
	




	public void updateDetalleFactura(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateDetalleFactura"), mapa);
	}

	public void updateFactura(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateFactura"), mapa);
	}
	
	public void delFactura(HashMap m) throws SQLException {
		cnx.delete(T_TABLA.concat("delFactura"), m);
	}
	public void updateTotalFactura(HashMap mapa) throws SQLException {
		cnx.delete(T_TABLA.concat("updateTotalFactura"), mapa);
	}
	
	
}
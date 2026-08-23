package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.SiniestroPreOrdenPago;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.fasdem.bean.Mapa;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerSiniestroPreOrdenPago extends PerGeneric {

	private static final String T_TABLA = "SiniestroPreOrdenPago.";
	private SqlMapClient cnx = null;

	public PerSiniestroPreOrdenPago() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public SiniestroPreOrdenPago searchPreOrden(String codigoPreOrden) throws PersonalNotFoundException, SQLException {
		SiniestroPreOrdenPago obj;
		obj = (SiniestroPreOrdenPago) cnx.queryForObject(T_TABLA.concat("searchPreOrden"), codigoPreOrden);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	

}
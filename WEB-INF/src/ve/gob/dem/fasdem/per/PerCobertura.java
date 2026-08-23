package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerCobertura extends PerGeneric {
	private static final String T_TABLA = "Cobertura.";
	private SqlMapClient cnx = null;

	public PerCobertura() {
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

	public double search(int id) throws PersonalNotFoundException, SQLException {
		Double obj;
		obj = (Double) cnx.queryForObject(T_TABLA.concat("searchCobertura"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Cobertura searchCobertura1(int id) throws PersonalNotFoundException, SQLException {
		Cobertura obj;
		obj = (Cobertura) cnx.queryForObject(T_TABLA.concat("searchCobertura1"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public double CoberturaPatologia(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Double obj;
		obj = (Double) cnx.queryForObject(T_TABLA.concat("CoberturaPatologia"), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Cobertura searchById(int id) throws PersonalNotFoundException, SQLException {
		Cobertura obj;
		obj = (Cobertura) cnx.queryForObject(T_TABLA.concat("search"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Cobertura searchByPolizaTipoCobertura(Cobertura c) throws PersonalNotFoundException, SQLException {
		Cobertura obj;
		obj = (Cobertura) cnx.queryForObject(T_TABLA.concat("searchByPolizaTipoCobertura"), c);
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

	public List listByTipoTramiteRangoFecha(HashMap m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listByTipoTramiteRangoFecha"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listByCedula(HashMap mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listByCedula"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listDesgloseCobertura(HashMap mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listDesgloseCobertura"), mapa);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public double coberturaAsegurado(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Double obj;
		obj = (Double) cnx.queryForObject(T_TABLA.concat("coberturaTipo"), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public void updateCobertura(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateCobertura"), mapa);
	}

	public List listByTipoTramiteModificar(HashMap m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listByTipoTramiteModificar"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	@SuppressWarnings("unchecked")
	public List<Cobertura> listCoberturaByCedula(HashMap<String, Object> m) throws PersonalNotFoundException, SQLException {
		List<Cobertura> list = new ArrayList<Cobertura>();
		list = cnx.queryForList(T_TABLA.concat("listCoberturaByCedula"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List<Cobertura> listCoberturaByPatologia(HashMap<String, Object> m) throws PersonalNotFoundException, SQLException {
		List<Cobertura> list = new ArrayList<Cobertura>();
		list = cnx.queryForList(T_TABLA.concat("listCoberturaByPatologia"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
}
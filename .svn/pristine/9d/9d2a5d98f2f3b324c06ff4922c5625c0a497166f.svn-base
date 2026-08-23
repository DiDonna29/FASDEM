package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.persit.administrador.CargaThread;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerSiniestro extends PerGeneric {
	private static final String T_TABLA = "Siniestro.";
	private static SqlMapClient cnx = null;
	protected static Logger log = Logger.getLogger(PerSiniestro.class);

	public PerSiniestro() {
		cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public List list() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat(KEY_LIST), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List searchCedulaBeneficiario(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchCedulaBeneficiario"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	public int searchCedulaBeneficiarioAps(HashMap mapa) throws PersonalNotFoundException, SQLException {
		int obj;
		obj = (Integer) cnx.queryForObject(T_TABLA.concat("searchCedulaBeneficiarioAps"), mapa);
		if (obj != 0)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Siniestro search(Mapa m) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), m);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	
	}

	public Siniestro searchBySiniestroAnio(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchBySiniestroAnio"), mapa);
		Log.info("objeto " + obj);
		if (obj != null)
			return obj;
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

	public void updateLiquidacion(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateLiquidacion"), mapa);
	}
	
	public void updateEstatus(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateEstatus"), mapa);
	}
	public void updateBandeja(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateBandeja"), mapa);
	}

	public void anularCartaAval(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("anularCartaAval"), mapa);
	}

	public void updateLiquidacionEmergencia(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateLiquidacionEmergencia"), mapa);
	}

	public void updatePreorden(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updatePreorden"), mapa);
	}

	public void update(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("update"), mapa);
	}

	public void activarCartaAval(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("activarCartaAval"), mapa);
	}

	public void egresarCartaAval(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("egresarCartaAval"), mapa);
	}

	public void justificarEstatus(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("justificarEstatus"), mapa);
	}

	public void updateAps(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateAps"), mapa);
	}

	public void updateRmbls(HashMap mapa) throws SQLException {
		cnx.update(T_TABLA.concat("updateRmbls"), mapa);
	}

	/*
	 * public List buscarTipoEmpleado(Mapa m) throws PersonalNotFoundException,
	 * SQLException { List list = new ArrayList(); list =
	 * cnx.queryForList(T_TABLA.concat("buscarTipoEmpleado"), m); if
	 * (list.size() != 0) return list; else throw new
	 * PersonalNotFoundException(" "); }
	 */
	public Siniestro searchNumero(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchNumero"), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Siniestro searchEmergencia(String subCodigo) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchEmergencia"), subCodigo);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Siniestro searchAval(String subCodigo) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchAval"), subCodigo);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Siniestro searchPadre(Mapa m) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchPadre"), m);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public Siniestro searchNumeroR(String SubCodigo) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchNumeroR"), SubCodigo);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	/*
	 * public List searchNumeroPreOrden(String codigoPreOrden) throws
	 * PersonalNotFoundException, SQLException { List list = new ArrayList();
	 * list = cnx.queryForList(T_TABLA.concat("searchNumeroPreOrden"),
	 * codigoPreOrden); if (list.size() != 0) return list; else throw new
	 * PersonalNotFoundException(" "); }
	 */
	public Siniestro buscarPorCedula(int cedula) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("buscarPorCedula"), cedula);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	/*
	 * public Siniestro buscarPorCedulaBeneficiario(int cedula) throws
	 * PersonalNotFoundException, SQLException { Siniestro obj; obj =
	 * (Siniestro)
	 * cnx.queryForObject(T_TABLA.concat("buscarCedulaBeneficiario"), cedula);
	 * if (obj != null) return obj; else throw new
	 * PersonalNotFoundException(" "); }
	 */
	/*
	 * public List sql_buscar_idProveedor(Mapa m) throws
	 * PersonalNotFoundException, SQLException { List list = new ArrayList();
	 * list = cnx.queryForList(T_TABLA.concat("sql_buscar_idProveedor"), m); if
	 * (list.size() != 0) return list; else throw new
	 * PersonalNotFoundException(" "); }
	 */
	public List searchMultiple(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchMultiple"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	

	public List searchMultipleConsulta(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchMultipleConsulta"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listarPorSiniestros(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listarPorSiniestros"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List searchMultipleByStatus(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchMultipleByStatus"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List searchMultipleByMultipleStatus(Mapa m) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchMultipleByMultipleStatus"), m);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Siniestro searchByCodigo(Mapa m) throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("searchByCodigo"), m);
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


	
	
	public static synchronized int insert(Mapa mapa)  {
		try {
			log.info("entro en el 1er try");
			return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);
		} catch (SQLException e) {
			log.info("entro en el 1er catch");
			e.printStackTrace();
			try {
				log.info("entro en el 2do try");
				return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), mapa);
			} catch (SQLException e1) {
				log.info("entro en el 2do catch");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	public List listarPorEstatus(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listarPorEstatus"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	public List bandeja(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("bandeja"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public List listarPorEstatusLiquidacion(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listarPorEstatusLiquidacion"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listarPorEstatusTodos(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listarPorEstatusTodos"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List listarPorRecepcionFactura(HashMap hm) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listarPorRecepcionFactura"), hm);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	
	
	
	public boolean estatusEditable(int id) throws PersonalNotFoundException, SQLException {
		Boolean obj;
		obj = (Boolean) cnx.queryForObject(T_TABLA.concat("estatusEditable"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List cpdHeathcheckBdEstatus() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("cpd_heathcheck_bdestatus"), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List cpdHeathcheckMemory() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("cpd_heathcheck_bdestatus"), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public Siniestro cpdHeathcheckIntance() throws PersonalNotFoundException, SQLException {
		Siniestro obj;
		obj = (Siniestro) cnx.queryForObject(T_TABLA.concat("cpd_heathcheck_intance"),null);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
}
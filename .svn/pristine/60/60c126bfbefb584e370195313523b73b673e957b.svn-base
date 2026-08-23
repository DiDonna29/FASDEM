package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Consulta;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Recipe;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.cnx.ServMedicoSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerConsulta extends PerGeneric {

	private static final String T_TABLA = "Consulta.";
	private SqlMapClient cnx = null;

	public PerConsulta() {
		this.cnx = ServMedicoSqlConfig.getSqlMapInstance();
	}


	public List searchFuncionario(String cedula) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("searchFuncionario"), cedula);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
	public Consulta searchFunc(String cedula) throws PersonalNotFoundException, SQLException {

		Consulta obj;
		obj = (Consulta) cnx.queryForObject(T_TABLA.concat("searchFuncionario"), cedula);
		Log.info("objeto " + obj);
		if (obj != null)

			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
		
	public Consulta searchBene(String cedula) throws PersonalNotFoundException, SQLException {

		Consulta obj;
		obj = (Consulta) cnx.queryForObject(T_TABLA.concat("searchBeneficiario"), cedula);
		Log.info("objeto " + obj);
		if (obj != null)

			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
public List searchBeneficiario(String cedula) throws PersonalNotFoundException, SQLException {
	List list = new ArrayList();
	list = cnx.queryForList(T_TABLA.concat("searchBeneficiario"), cedula);
	if (list.size() != 0)
		return list;
	else
		throw new PersonalNotFoundException(" ");
}
	
}
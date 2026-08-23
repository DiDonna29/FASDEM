package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Medicamento;
import ve.gob.dem.fasdem.bean.Recipe;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.cnx.ServMedicoSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerMedicamento extends PerGeneric {

	private static final String T_TABLA = "Medicamento.";
	private SqlMapClient cnx = null;

	public PerMedicamento() {
		this.cnx = ServMedicoSqlConfig.getSqlMapInstance();
	}


	

/*public Medicamento searchMedicamentos(String recipes) throws PersonalNotFoundException, SQLException {
	
	Medicamento obj;
	obj = (Medicamento) cnx.queryForObject(T_TABLA.concat("searchMedicamentos"), recipes);
	
	if (obj != null)
		
		return obj;
	else
		throw new PersonalNotFoundException(" ");
}*/

public List searchListMedicamentos(String id) throws PersonalNotFoundException, SQLException {
	List list = new ArrayList();
	list = cnx.queryForList(T_TABLA.concat("searchRecipes"), id);
	if (list.size() != 0)
		return list;
	else
		throw new PersonalNotFoundException(" ");
}
public Medicamento searchmedica(int id) throws PersonalNotFoundException, SQLException {
	
	Medicamento obj;
	obj = (Medicamento) cnx.queryForObject(T_TABLA.concat("searchRecipes"), id);
	
	if (obj != null)
		
		return obj;
	else
		throw new PersonalNotFoundException(" ");
}


}
package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Recipe;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.cnx.ServMedicoSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerRecipe extends PerGeneric {

	private static final String T_TABLA = "Recipe.";
	private SqlMapClient cnx = null;

	public PerRecipe() {
		this.cnx = ServMedicoSqlConfig.getSqlMapInstance();
	}

public Recipe searchRecipes(int consulta) throws PersonalNotFoundException, SQLException {
	
	Recipe obj;
	obj = (Recipe) cnx.queryForObject(T_TABLA.concat("searchRecipes"), consulta);
	
	if (obj != null)
		
		return obj;
	else
		throw new PersonalNotFoundException(" ");
}
public List searchList(String id) throws PersonalNotFoundException, SQLException {
	List list = new ArrayList();
	list = cnx.queryForList(T_TABLA.concat("searchRecipes"), id);
	if (list.size() != 0)
		return list;
	else
		throw new PersonalNotFoundException(" ");
}



}
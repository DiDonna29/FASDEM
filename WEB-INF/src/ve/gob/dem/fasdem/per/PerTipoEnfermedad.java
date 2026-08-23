package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerTipoEnfermedad extends PerGeneric {

	private static final String T_TABLA = "TipoEnfermedad.";
	private SqlMapClient cnx = null;

	public PerTipoEnfermedad() {
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

	public TipoEnfermedad buscar(int id) throws PersonalNotFoundException, SQLException {
		TipoEnfermedad obj;
		obj = (TipoEnfermedad) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public TipoEnfermedad searchMedicinas(int id) throws PersonalNotFoundException, SQLException {
		TipoEnfermedad obj;
		obj = (TipoEnfermedad) cnx.queryForObject(T_TABLA.concat("searchMedicinas"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public TipoEnfermedad searchMedicinasTexto(HashMap mapa) throws PersonalNotFoundException, SQLException {
		TipoEnfermedad obj;
		obj = (TipoEnfermedad) cnx.queryForObject(T_TABLA.concat("searchMedicinasTexto"), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public int searchAguda(HashMap mapa) throws PersonalNotFoundException, SQLException {

		Integer obj;
		obj = (Integer) cnx.queryForObject(T_TABLA.concat("searchAguda"), mapa);
		if (obj != null)

			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	public int searchCronica(HashMap mapa) throws PersonalNotFoundException, SQLException {

		Integer obj;
		obj = (Integer) cnx.queryForObject(T_TABLA.concat("searchCronica"), mapa);
		if (obj != null)

			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

//	public String searchmesCronica(String miFecha) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public TipoEnfermedad searchmesCronica(String miFecha) throws PersonalNotFoundException, SQLException {
		TipoEnfermedad obj;
		obj = (TipoEnfermedad) cnx.queryForObject(T_TABLA.concat("searchmesCronica"), miFecha);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
//	public String searchmesCronica(String miFecha) throws PersonalNotFoundException, SQLException {
//
//		String obj;
//		obj = (String) cnx.queryForObject(T_TABLA.concat("searchmesCronica"), miFecha);
//		if (obj != null)
//
//			return obj;
//		else
//			throw new PersonalNotFoundException(" ");
//	}

	

	

}
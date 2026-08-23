package ve.gob.dem.fasdem.per;

import java.sql.SQLException;

import ve.gob.dem.fasdem.bean.Foto;
import ve.gob.dem.framework.cnx.ServMedicoSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerFoto extends PerGeneric {

	private static final String T_TABLA = "Foto.";
	private SqlMapClient cnx = null;

	public PerFoto() {
		this.cnx = ServMedicoSqlConfig.getSqlMapInstance();
	}
	
	public Foto search(int id) throws PersonalNotFoundException, SQLException {
		Foto obj;
		obj = (Foto) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	
	

}
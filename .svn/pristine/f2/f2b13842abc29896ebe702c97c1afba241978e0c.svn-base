package ve.gob.dem.fasdem.per;

import java.sql.SQLException;

import ve.gob.dem.fasdem.bean.PdfOrdenMedicina;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerPdfOrdenMedicina extends PerGeneric {
	private static final String T_TABLA = "PdfOrdenMedicina.";
	private SqlMapClient cnx = null;

	public PerPdfOrdenMedicina() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public PdfOrdenMedicina search(String codigo) throws PersonalNotFoundException, SQLException {
		PdfOrdenMedicina obj;
		obj = (PdfOrdenMedicina) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), codigo);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public void insert(PdfOrdenMedicina pom) throws SQLException {
		cnx.insert(T_TABLA.concat(KEY_INSERT), pom);
	}

}
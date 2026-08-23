package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.framework.cnx.BienestarSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerPersona extends PerGeneric {
	private static final String T_TABLA = "Persona.";
	private SqlMapClient cnx = null;

	public PerPersona() {
		this.cnx = BienestarSqlConfig.getSqlMapInstance();
	}

	public Persona buscar(String id) throws PersonalNotFoundException, SQLException{
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscar"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public Persona buscarBeneficiario(HashMap<String, String> hm) throws PersonalNotFoundException, SQLException{
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscarBeneficiario"), hm);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public Persona buscarBeneficiario1(HashMap<String, String> hm) throws PersonalNotFoundException, SQLException{
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscarBeneficiario1"), hm);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public Persona buscar1(String id) throws PersonalNotFoundException, SQLException{
		
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscar1"),id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
public Persona buscar2(HashMap m) throws PersonalNotFoundException, SQLException{
		
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscar1"),m);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Persona buscarPorTitular(String id) throws PersonalNotFoundException, SQLException {
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscarPorTitular"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public Persona buscarSinCarga(int id) throws PersonalNotFoundException, SQLException {
		Persona obj;
		obj = (Persona) cnx.queryForObject(T_TABLA.concat("buscarSinCarga"), id);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public List buscarCargaFamiliar() throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("buscarCargaFamiliar"), null);
		if (list.size() != 0)
			return list;
		else
			throw new PersonalNotFoundException(" ");
	}
}
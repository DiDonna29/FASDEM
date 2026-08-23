package ve.gob.dem.fasdem.per;

import java.sql.SQLException;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;

import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerCuenta extends PerGeneric {
	private static final String T_TABLA = "Cuenta.";
	private static final String T_TABLAB = "CuentaBenef.";
	private SqlMapClient cnx = null;

	public PerCuenta() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public Cuenta search(String cedula) throws PersonalNotFoundException, SQLException {
		Cuenta obj;
		obj = (Cuenta) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), cedula);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	public CuentaBenef searchNoSgfrh(String cedula) throws PersonalNotFoundException, SQLException {
		CuentaBenef obj;
		obj = (CuentaBenef) cnx.queryForObject(T_TABLA.concat(KEY_SEARCHSF), cedula);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
	/* 
	 * public Cuenta searchCuenta(String cedula) throws
	 * PersonalNotFoundException, SQLException {
	 * 
	 * Cuenta obj; obj = (Cuenta)
	 * cnx.queryForObject(T_TABLA.concat("searchCuenta"), cedula);
	 * Log.info("objeto "+obj); if (obj != null)
	 * 
	 * return obj; else throw new PersonalNotFoundException(" "); }
	 */
	/*
	 * public CuentaSf searchCuenta(int id) throws PersonalNotFoundException,
	 * SQLException {
	 * 
	 * CuentaSf obj; obj = (CuentaSf)
	 * cnx.queryForObject(T_TABLA.concat("searchCuenta"), id);
	 * 
	 * if (obj != null)
	 * 
	 * return obj; else throw new PersonalNotFoundException(" "); }
	 */
	
	public CuentaBenef searchB(String cedula) throws PersonalNotFoundException, SQLException {
		CuentaBenef obj;
		obj = (CuentaBenef) cnx.queryForObject(T_TABLAB.concat(KEY_SEARCH), cedula);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}
}
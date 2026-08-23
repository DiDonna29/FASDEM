/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class Cuenta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2321369502413815847L;
	private int cedula;
	private String cuenta;
	private String tipoCuenta;
	private String codBanco;
	private String nombreBanco;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the codBanco
	 */
	public String getCodBanco() {
		return codBanco;
	}

	/**
	 * @param codBanco
	 *            the codBanco to set
	 */
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	/**
	 * @return the nombreBanco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * @param nombreBanco
	 *            the nombreBanco to set
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

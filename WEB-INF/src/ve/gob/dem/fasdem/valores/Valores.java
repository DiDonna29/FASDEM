/**
 * 02/04/2011 15:11:21
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.valores;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class Valores implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6432950043054169755L;
	private double iva;
	private double islr;
	private double timbreFiscal;
	private int    tipoGastoIva;

	/**
	 * 
	 */
	public Valores() {
		super();
	}

	/**
	 * @return the iva
	 */
	public double getIva() {
		return iva;
	}

	/**
	 * @param iva
	 *            the iva to set
	 */
	public void setIva(double iva) {
		this.iva = iva;
	}

	/**
	 * @param iva
	 *            the iva to set
	 */
	public void setIva(String iva) {
		this.iva = Double.parseDouble(iva);
	}

	/**
	 * @return the islr
	 */
	public double getIslr() {
		return islr;
	}

	/**
	 * @param islr
	 *            the islr to set
	 */
	public void setIslr(double islr) {
		this.islr = islr;
	}

	public void setIslr(String islr) {
		this.islr = Double.parseDouble(islr);
	}

	/**
	 * @return the timbreFiscal
	 */
	public double getTimbreFiscal() {
		return timbreFiscal;
	}

	/**
	 * @param timbreFiscal
	 *            the timbreFiscal to set
	 */
	public void setTimbreFiscal(double timbreFiscal) {
		this.timbreFiscal = timbreFiscal;
	}

	public void setTimbreFiscal(String timbreFiscal) {
		this.timbreFiscal = Double.parseDouble(timbreFiscal);
	}

	/**
	 * @return the tipoGastoIva
	 */
	public int getTipoGastoIva() {
		return tipoGastoIva;
	}

	/**
	 * @param tipoGastoIva the tipoGastoIva to set
	 */
	public void setTipoGastoIva(String tipoGastoIva) {
		this.tipoGastoIva = Integer.parseInt(tipoGastoIva);
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

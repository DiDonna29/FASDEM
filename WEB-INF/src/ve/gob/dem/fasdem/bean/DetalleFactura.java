/**
 * 23/03/2011 18:38:18
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class DetalleFactura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idFactura;
	private TipoGasto tipoGasto;
	private double monto;
	private int anioSiniestro;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idFactura
	 */
	public int getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura
	 *            the idFactura to set
	 */
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the tipoGasto
	 */
	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	/**
	 * @param tipoGasto
	 *            the tipoGasto to set
	 */
	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	/**
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * @return the anioSiniestro
	 */
	public int getAnioSiniestro() {
		return anioSiniestro;
	}

	/**
	 * @param anioSiniestro
	 *            the anioSiniestro to set
	 */
	public void setAnioSiniestro(int anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

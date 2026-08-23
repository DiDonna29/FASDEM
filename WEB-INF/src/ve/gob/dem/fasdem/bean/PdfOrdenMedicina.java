package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

public class PdfOrdenMedicina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8255552759720553877L;
	private int id;
	private String codigoValidacion;
	private byte[] pdf;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

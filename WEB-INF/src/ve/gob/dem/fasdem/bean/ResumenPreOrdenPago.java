/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class ResumenPreOrdenPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4160599083231009724L;
	
	private String Proveedor;
	
	private Double MONTO_LIQUIDADO;
	private Double MONTO_ISRL;
	private Double MONTO_IVA;
	private Double MONTO_TIMBRE;
	private Double MONTO_IVA_PAGAR;
	private int TIPO_EMPLEADO;
	public String getProveedor() {
		return Proveedor;
	}
	public void setProveedor(String proveedor) {
		Proveedor = proveedor;
	}
	public Double getMONTO_LIQUIDADO() {
		return MONTO_LIQUIDADO;
	}
	public void setMONTO_LIQUIDADO(Double mONTOLIQUIDADO) {
		MONTO_LIQUIDADO = mONTOLIQUIDADO;
	}
	public Double getMONTO_ISRL() {
		return MONTO_ISRL;
	}
	public void setMONTO_ISRL(Double mONTOISRL) {
		MONTO_ISRL = mONTOISRL;
	}
	public Double getMONTO_IVA() {
		return MONTO_IVA;
	}
	public void setMONTO_IVA(Double mONTOIVA) {
		MONTO_IVA = mONTOIVA;
	}
	public Double getMONTO_TIMBRE() {
		return MONTO_TIMBRE;
	}
	public void setMONTO_TIMBRE(Double mONTOTIMBRE) {
		MONTO_TIMBRE = mONTOTIMBRE;
	}
	public int getTIPO_EMPLEADO() {
		return TIPO_EMPLEADO;
	}
	public void setTIPO_EMPLEADO(int tIPOEMPLEADO) {
		TIPO_EMPLEADO = tIPOEMPLEADO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getMONTO_IVA_PAGAR() {
		return MONTO_IVA_PAGAR;
	}
	public void setMONTO_IVA_PAGAR(Double mONTO_IVA_PAGAR) {
		MONTO_IVA_PAGAR = mONTO_IVA_PAGAR;
	}

	
}

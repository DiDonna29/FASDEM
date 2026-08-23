/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExpedientePago implements Serializable {


	private static final long serialVersionUID = 4744199970294151250L;

	
	private int id;
	private String aniomes;
	private String anio;
	private String codigo_preorden;
	private Double monto;
	private HojaRuta hoja;
	private String causado;
	private EstatusHojaRuta status;
	private String Observacion;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAniomes() {
		return aniomes;
	}
	public void setAniomes(String aniomes) {
		this.aniomes = aniomes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getCodigo_preorden() {
		return codigo_preorden;
	}
	public void setCodigo_preorden(String codigo_preorden) {
		this.codigo_preorden = codigo_preorden;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public HojaRuta getHoja() {
		return hoja;
	}
	public void setHoja(HojaRuta hoja) {
		this.hoja = hoja;
	}
	public String getCausado() {
		return causado;
	}
	public void setCausado(String causado) {
		this.causado = causado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public EstatusHojaRuta getStatus() {
		return status;
	}
	public void setStatus(EstatusHojaRuta status) {
		this.status = status;
	}
	public String getObservacion() {
		return Observacion;
	}
	public void setObservacion(String observacion) {
		Observacion = observacion;
	}
	
}

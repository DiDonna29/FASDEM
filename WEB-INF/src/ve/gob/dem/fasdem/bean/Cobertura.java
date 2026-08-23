/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("rawtypes")
public class Cobertura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6065060996574139847L;
	private int id;
	private Poliza poliza;
	private TipoCobertura tipoCobertura;
	private double monto;
	private double montoAgotada;
	private double montoDisponible;
	private boolean porPatologia;
	private String patologia;
	private List desgloseCobertura;
	private boolean isActivo;
	private String descripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}

	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getMontoAgotada() {
		return montoAgotada;
	}

	public void setMontoAgotada(double montoAgotada) {
		this.montoAgotada = montoAgotada;
	}

	public double getMontoDisponible() {
		return montoDisponible;
	}

	public void setMontoDisponible(double montoDisponible) {
		this.montoDisponible = montoDisponible;
	}

	public void setDesgloseCobertura(List desgloseCobertura) {
		this.desgloseCobertura = desgloseCobertura;
	}

	public List getDesgloseCobertura() {
		return desgloseCobertura;
	}

	public boolean isPorPatologia() {
		return porPatologia;
	}

	public void setPorPatologia(boolean porPatologia) {
		this.porPatologia = porPatologia;
	}

	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

	public boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @param id
	 * @param poliza
	 * @param tipoCobertura
	 * @param monto
	 * @param montoAgotada
	 * @param montoDisponible
	 * @param porPatologia
	 * @param patologia
	 * @param desgloseCobertura
	 * @param isActivo
	 * @param descripcion
	 */
	public Cobertura() {
		super();

		this.poliza = new Poliza();
		this.tipoCobertura = new TipoCobertura();

	}

}

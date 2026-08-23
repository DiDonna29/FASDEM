/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author marcenrl
 * 
 */
public class NotaTecnica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2043294326639773011L;
	private int id;
	private int idSiniestro;
	private int anioSiniestro;
	private String observacion;
	private Date fecha;
	private String desUsuario;
	private String loginUsuario;
	private int idDependencia;

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
	 * @return the idSiniestro
	 */
	public int getIdSiniestro() {
		return idSiniestro;
	}

	/**
	 * @param idSiniestro
	 *            the idSiniestro to set
	 */
	public void setIdSiniestro(int idSiniestro) {
		this.idSiniestro = idSiniestro;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the loginUsuario
	 */
	

	/**
	 * @return the desUsuario
	 */
	

	/**
	 * @param desUsuario
	 *            the desUsuario to set
	 */
	

	public void setAnioSiniestro(int anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}

	public int getAnioSiniestro() {
		return anioSiniestro;
	}

	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}

	public int getIdDependencia() {
		return idDependencia;
	}

	public void setDesUsuario(String desUsuario) {
		this.desUsuario = desUsuario;
	}

	public String getDesUsuario() {
		return desUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}
}

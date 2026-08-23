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
public class MotivoEstatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4736978122465360482L;
	private int id;
	private String descripcion;
	private int idSiniestro;
	private int idDependencia;
	private int idEstatus;
	private int idProveedor;
	private String idUsuario;
	private String loginUsuario;
	private String desUsuario;
	private Date fechaInicio;
	private Date fechaFin;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @return the idDependencia
	 */
	public int getIdDependencia() {
		return idDependencia;
	}

	/**
	 * @param idDependencia
	 *            the idDependencia to set
	 */
	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the loginUsuario
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}

	/**
	 * @param loginUsuario
	 *            the loginUsuario to set
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	/**
	 * @return the desUsuario
	 */
	public String getDesUsuario() {
		return desUsuario;
	}

	/**
	 * @param desUsuario
	 *            the desUsuario to set
	 */
	public void setDesUsuario(String desUsuario) {
		this.desUsuario = desUsuario;
	}

	/**
	 * @return the idEstatus
	 */
	public int getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus
	 *            the idEstatus to set
	 */
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the idProveedor
	 */
	public int getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor
	 *            the idProveedor to set
	 */
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
		
}

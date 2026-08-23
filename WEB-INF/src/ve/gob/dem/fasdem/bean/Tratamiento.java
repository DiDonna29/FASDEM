/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class Tratamiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7413734644905688983L;
	/**
	 * 
	 */

	private int id;
	private String descripcion;
	private Patologias patologia;
	private String desEspecialidad;
	private String desOrgano;
	private String desPatologia;
	private String desTratamiento;
	
	

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

	public Patologias getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologias patologia) {
		this.patologia = patologia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the desEspecialidad
	 */
	public String getDesEspecialidad() {
		return desEspecialidad;
	}

	/**
	 * @param desEspecialidad the desEspecialidad to set
	 */
	public void setDesEspecialidad(String desEspecialidad) {
		this.desEspecialidad = desEspecialidad;
	}

	/**
	 * @return the desOrgano
	 */
	public String getDesOrgano() {
		return desOrgano;
	}

	/**
	 * @param desOrgano the desOrgano to set
	 */
	public void setDesOrgano(String desOrgano) {
		this.desOrgano = desOrgano;
	}

	/**
	 * @return the desPatologia
	 */
	public String getDesPatologia() {
		return desPatologia;
	}

	/**
	 * @param desPatologia the desPatologia to set
	 */
	public void setDesPatologia(String desPatologia) {
		this.desPatologia = desPatologia;
	}

	/**
	 * @return the desTratamiento
	 */
	public String getDesTratamiento() {
		return desTratamiento;
	}

	/**
	 * @param desTratamiento the desTratamiento to set
	 */
	public void setDesTratamiento(String desTratamiento) {
		this.desTratamiento = desTratamiento;
	}




}

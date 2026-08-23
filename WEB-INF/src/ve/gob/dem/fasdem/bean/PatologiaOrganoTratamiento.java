package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

public class PatologiaOrganoTratamiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1095620002349557460L;
	private int id;
	private Especialidad especialidad;
	private Organo organo;
	private Patologias patologia;
	private Tratamiento tratamiento;
	private String strEspecialidad;
	private String strOrgano;;
	private String strPatologia;
	private String strTratamiento;
	private boolean isActivo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	
	public Patologias getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologias patologia) {
		this.patologia = patologia;
	}

	public Organo getOrgano() {
		return organo;
	}

	public void setOrgano(Organo organo) {
		this.organo = organo;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * @return the strEspecialidad
	 */
	public String getStrEspecialidad() {
		return strEspecialidad;
	}

	/**
	 * @param strEspecialidad
	 *            the strEspecialidad to set
	 */
	public void setStrEspecialidad(String strEspecialidad) {
		this.strEspecialidad = strEspecialidad;
	}

	/**
	 * @return the strOrgano
	 */
	public String getStrOrgano() {
		return strOrgano;
	}

	/**
	 * @param strOrgano
	 *            the strOrgano to set
	 */
	public void setStrOrgano(String strOrgano) {
		this.strOrgano = strOrgano;
	}

	/**
	 * @return the strPatologia
	 */
	public String getStrPatologia() {
		return strPatologia;
	}

	/**
	 * @param strPatologia
	 *            the strPatologia to set
	 */
	public void setStrPatologia(String strPatologia) {
		this.strPatologia = strPatologia;
	}

	/**
	 * @return the strTratamiento
	 */
	public String getStrTratamiento() {
		return strTratamiento;
	}

	/**
	 * @param strTratamiento
	 *            the strTratamiento to set
	 */
	public void setStrTratamiento(String strTratamiento) {
		this.strTratamiento = strTratamiento;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

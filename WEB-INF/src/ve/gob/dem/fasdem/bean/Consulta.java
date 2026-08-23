/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("rawtypes")
public class Consulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1440465184722789177L;
	private int cedula;
	private int codigoBienestar;
	private String nombres;
	private String apellidos;
	private Date fechaRegistro;
	private String observaciones;
	private String especialidad;
	private int idConsulta;
	private List recipes;
	private int idPersona;

	

	public List getRecipes() {
		return recipes;
	}

	public void setRecipes(List recipes) {
		this.recipes = recipes;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public int getCodigoBienestar() {
		return codigoBienestar;
	}

	public void setCodigoBienestar(int codigoBienestar) {
		this.codigoBienestar = codigoBienestar;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}

/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jfree.util.Log;

import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("rawtypes")
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6487450153161630131L;
	/**
	 * 
	 */
	private String cedula;
	private String cedulaTitular;
	private String nombres;
	private String apellidos;
	// private String cuenta;
	private String tipoEmpleado;
	private Date fechaNacimiento;
	private int edad;
	private String sexo;
	private String estadoCivil;
	private String cargo;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private String telefono;
	private String estado;
	private String estatus;
	private int id_parentesco;
	private String parentesco;
	private String dependencia;
	private String tipoPersona;
	private boolean empleado;
	private String tipoCedRif;
	private String correoElectronico;
	private String direccion;
	private Persona beneficiario;
	private List<Persona> cargaFamiliar;

	private List coberturas;
	private Cuenta cuentaNomina;
	private int idTipoEmpleado;

	/*
	 * private double cobert; private double coberturaps; private double
	 * coberturadisponible;
	 */
	/*
	 * private String primerNombreBeneficiario; private String
	 * segundoNombreBeneficiario; private String primerApellidoBeneficiario;
	 * private String segundoApellidoBeneficiario; private String
	 * cedulaBeneficiario; private String cedulaFuncionario; private int
	 * edadBeneficiario; private String sexoBeneficiario; private Date
	 * fechaNacimientoBeneficiario;
	 */
	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the cedulaTitular
	 */
	public String getCedulaTitular() {
		return cedulaTitular;
	}

	/**
	 * @param cedulaTitular
	 *            the cedulaTitular to set
	 */
	public void setCedulaTitular(String cedulaTitular) {
		this.cedulaTitular = cedulaTitular;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos
	 *            the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the tipoEmpleado
	 */
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	/**
	 * @param tipoEmpleado
	 *            the tipoEmpleado to set
	 */
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/*
	 * public String getCuenta() { return cuenta; }
	 * 
	 * public void setCuenta(String cuenta) { this.cuenta = cuenta; }
	 */
	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		try {
			this.edad = calculaEdad(Utilidad.DateToString(fechaNacimiento, "dd/MM/yyyy"));
		} catch (PersonalException e) {
			Log.error("error calculando edad", e);
		}
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso
	 *            the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the parentesco
	 */
	public String getParentesco() {
		return parentesco;
	}

	/**
	 * @param parentesco
	 *            the parentesco to set
	 */
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	/**
	 * @return the dependencia
	 */
	public String getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia
	 *            the dependencia to set
	 */
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * @param tipoPersona
	 *            the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * @return the empleado
	 */
	public boolean isEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 *            the empleado to set
	 */
	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the tipoCedRif
	 */
	public String getTipoCedRif() {
		return tipoCedRif;
	}

	/**
	 * @param tipoCedRif
	 *            the tipoCedRif to set
	 */
	public void setTipoCedRif(String tipoCedRif) {
		this.tipoCedRif = tipoCedRif;
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico
	 *            the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the beneficiario
	 */
	public Persona getBeneficiario() {
		return beneficiario;
	}

	/**
	 * @param beneficiario
	 *            the beneficiario to set
	 */
	public void setBeneficiario(Persona beneficiario) {
		this.beneficiario = beneficiario;
	}

	/**
	 * @return the cargaFamiliar
	 */
	public List<Persona> getCargaFamiliar() {
		return cargaFamiliar;
	}

	/**
	 * @param cargaFamiliar
	 *            the cargaFamiliar to set
	 */
	public void setCargaFamiliar(List<Persona> cargaFamiliar) {
		this.cargaFamiliar = cargaFamiliar;
	}

	/**
	 * @return the coberturas
	 */
	public List getCoberturas() {
		return coberturas;
	}

	/**
	 * @param coberturas
	 *            the coberturas to set
	 */
	public void setCoberturas(List coberturas) {
		this.coberturas = coberturas;
	}

	public int calculaEdad(String fecha_nac) { // fecha_nac debe tener el
		// formato dd/MM/yyyy
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaActual);
		String[] dat1 = fecha_nac.split("/");
		String[] dat2 = hoy.split("/");
		int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
		int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		if (mes < 0) {
			anos = anos - 1;
		} else if (mes == 0) {
			int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
			if (dia > 0) {
				anos = anos - 1;
			}
		}
		return anos;
	}

	/**
	 * @return the cuentaNomina
	 */
	public Cuenta getCuentaNomina() {
		return cuentaNomina;
	}

	/**
	 * @param cuentaNomina
	 *            the cuentaNomina to set
	 */
	public void setCuentaNomina(Cuenta cuentaNomina) {
		this.cuentaNomina = cuentaNomina;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId_parentesco(int id_parentesco) {
		this.id_parentesco = id_parentesco;
	}

	public int getId_parentesco() {
		return id_parentesco;
	}

	public int getIdTipoEmpleado() {
		return idTipoEmpleado;
	}

	public void setIdTipoEmpleado(int idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	
}

/*
 * creado el 16/06/2003
 * autor lmontanez
 */
package ve.gob.dem.framework.exception;

import ve.gob.dem.framework.recursos.Errores;


/**
 * Esta clase se debe dispara cuando ocurre una excepci�n general.
 * @author lmontanez
 */
public class PersonalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9043422525414009673L;
	private Errores error = null;
	/**
	 * Constructor
	 * 
	 */
	public PersonalException() {
		super();
		error = new Errores();
	}

	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalException(String argDescripcion) {
		super(argDescripcion);
		error = new Errores(argDescripcion);
	}

	/**
	 * Constructor
	 * @param argCodigo Codigo del error.
	 * @param argMensaje Mensaje del error.
	 */
	public PersonalException(String argCodigo, String argMensaje) {
		error = new Errores(argCodigo, argMensaje);
	
	}
	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalException(Throwable arg0) {
		error = new Errores(arg0.getMessage());
	}
	
	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalException(Errores argError) {
		super(argError.getMensaje());
		error = argError;
	}

	/**
	 * @return
	 */
	public Errores getError() {
		return error;
	}

	/**
	 * @param errores
	 */
	public void setError(Errores errores) {
		error = errores;
	}
}

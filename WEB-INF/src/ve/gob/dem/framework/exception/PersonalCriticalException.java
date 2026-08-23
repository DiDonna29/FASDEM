/*
 * creado el 16/06/2003
 * autor lmontanez
 */
package ve.gob.dem.framework.exception;

import ve.gob.dem.framework.recursos.Errores;


/**
 * Esta clase se debe disparar cuando ocurre un error critico e inmediatamente
 * se debe salir del sistema o cerrar la sesi�n actual del usuario.
 * @author lmontanez
 */
public class PersonalCriticalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -505524516341318540L;
	private Errores error = null;
	/**
	 * Constructor
	 * 
	 */
	public PersonalCriticalException() {
		super();
		error = new Errores();
	}

	/**
	 * Constructor
	 * @param argDescripcion
	 */
	public PersonalCriticalException(String argDescripcion) {
		super(argDescripcion);
		error = new Errores(argDescripcion);
	}
	
	/**
	 * Constructor
	 * @param argCodigo Codigo del error.
	 * @param argMensaje Mensaje del error.
	 */
	public PersonalCriticalException(String argCodigo, String argMensaje) {
		super(argMensaje);
		error = new Errores(argCodigo, argMensaje);
	
	}
	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalCriticalException(Throwable arg0) {
		error = new Errores(arg0.getMessage());
	}
	
	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalCriticalException(Errores argError) {
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

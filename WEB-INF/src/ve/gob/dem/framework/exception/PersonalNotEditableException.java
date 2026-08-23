/*
 * creado el 16/06/2003
 * autor lmontanez
 */
package ve.gob.dem.framework.exception;

import ve.gob.dem.framework.recursos.Errores;


/**
 * Esta excepci�n se debe disparar cuando nos se consigue lo que
 * se esta buscando.
 * @author lmontanez
 */
public class PersonalNotEditableException extends Exception {
	/**
     * 
     */
    private static final long serialVersionUID = 611090814852784613L;
	/**
	 * 
	 */

	private Errores error = null;
	/**
	 * Constructor
	 * 
	 */
	public PersonalNotEditableException() {
		super();
		error = new Errores();
	}

	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalNotEditableException(String argDescripcion) {
		super(argDescripcion);
		error = new Errores(argDescripcion);
	}
	
	/**
	 * Constructor
	 * @param argCodigo Codigo del error.
	 * @param argMensaje Mensaje del error.
	 */
	public PersonalNotEditableException(String argCodigo, String argMensaje) {
		error = new Errores(argCodigo, argMensaje);
	
	}
	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalNotEditableException(Throwable arg0) {
		error = new Errores(arg0.getMessage());
	}
   /**
	* Constructor
	* @param arg0
	*/
   public PersonalNotEditableException(Errores arg0) {
		super(arg0.getMensaje());
		error = arg0;
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

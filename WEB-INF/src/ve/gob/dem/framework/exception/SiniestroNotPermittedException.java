package ve.gob.dem.framework.exception;

import java.io.Serializable;

import ve.gob.dem.framework.recursos.Errores;

public class SiniestroNotPermittedException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275302609349622915L;
	private Errores error = null;
	/**
	 * Constructor
	 * 
	 */
	public SiniestroNotPermittedException() {
		super();
		error = new Errores();
	}

	/**
	 * Constructor
	 * @param arg0
	 */
	public SiniestroNotPermittedException(String argDescripcion) {
		super(argDescripcion);
		error = new Errores(argDescripcion);
	}
	
	/**
	 * Constructor
	 * @param argCodigo Codigo del error.
	 * @param argMensaje Mensaje del error.
	 */
	public SiniestroNotPermittedException(String argCodigo, String argMensaje) {
		error = new Errores(argCodigo, argMensaje);
	
	}
	/**
	 * Constructor
	 * @param arg0
	 */
	public SiniestroNotPermittedException(Throwable arg0) {
		error = new Errores(arg0.getMessage());
	}
   /**
	* Constructor
	* @param arg0
	*/
   public SiniestroNotPermittedException(Errores arg0) {
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

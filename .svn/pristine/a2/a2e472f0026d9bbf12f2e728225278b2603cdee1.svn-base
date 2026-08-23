package ve.gob.dem.framework.exception;

import java.io.Serializable;

import ve.gob.dem.framework.recursos.Errores;

public class MontoSiniestroExcedidoException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3946077920422689379L;
	private Errores error = null;
	/**
	 * Constructor
	 * 
	 */
	public MontoSiniestroExcedidoException() {
		super();
		error = new Errores();
	}

	/**
	 * Constructor
	 * @param arg0
	 */
	public MontoSiniestroExcedidoException(String argDescripcion) {
		super(argDescripcion);
		error = new Errores(argDescripcion);
	}
	
	/**
	 * Constructor
	 * @param argCodigo Codigo del error.
	 * @param argMensaje Mensaje del error.
	 */
	public MontoSiniestroExcedidoException(String argCodigo, String argMensaje) {
		error = new Errores(argCodigo, argMensaje);
	
	}
	/**
	 * Constructor
	 * @param arg0
	 */
	public MontoSiniestroExcedidoException(Throwable arg0) {
		error = new Errores(arg0.getMessage());
	}
   /**
	* Constructor
	* @param arg0
	*/
   public MontoSiniestroExcedidoException(Errores arg0) {
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

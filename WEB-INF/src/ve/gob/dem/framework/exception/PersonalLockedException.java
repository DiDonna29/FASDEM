
package ve.gob.dem.framework.exception;

import ve.gob.dem.framework.recursos.Errores;



public class PersonalLockedException extends Exception {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	/**
     * 
     */

	/**
	 * 
	 */

	private Errores error = null;
	/**
	 * Constructor
	 * 
	 */
	public PersonalLockedException() {
		super();
		error = new Errores();
	}

	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalLockedException(String argDescripcion) {
		super(argDescripcion);
		error = new Errores(argDescripcion);
	}
	
	/**
	 * Constructor
	 * @param argCodigo Codigo del error.
	 * @param argMensaje Mensaje del error.
	 */
	public PersonalLockedException(String argCodigo, String argMensaje) {
		error = new Errores(argCodigo, argMensaje);
	
	}
	/**
	 * Constructor
	 * @param arg0
	 */
	public PersonalLockedException(Throwable arg0) {
		error = new Errores(arg0.getMessage());
	}
   /**
	* Constructor
	* @param arg0
	*/
   public PersonalLockedException(Errores arg0) {
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

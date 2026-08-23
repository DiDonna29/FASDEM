/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;


/**
 * @author marcenrl
 *
 */
public class UsuarioEstadistica implements Serializable{
   
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7682043265441447207L;
	private String login;
    private String nombre;
    private String cantidadAtendidos;
    private String tiempoPromedio;
	
    
    public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCantidadAtendidos() {
		return cantidadAtendidos;
	}
	public void setCantidadAtendidos(String cantidadAtendidos) {
		this.cantidadAtendidos = cantidadAtendidos;
	}
	public String getTiempoPromedio() {
		return tiempoPromedio;
	}
	public void setTiempoPromedio(String tiempoPromedio) {
		this.tiempoPromedio = tiempoPromedio;
	}
   

}

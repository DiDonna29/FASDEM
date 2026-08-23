/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class TipoCobertura implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1632834247569374500L;
	private int id;
    private String descripcion;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

	/**
	 * @param id
	 * @param descripcion
	 */
	public TipoCobertura() {
		super();
		this.id = -1;
	}
    
    
}

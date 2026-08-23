/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class Dependencia implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5989131924811732006L;
	/**
	 * 
	 */
	
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


}

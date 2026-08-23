/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class Ciudad implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 156107822534828880L;
	/**
	 * 
	 */
	private int id;
    private String descripcion;
	private int id_estado;

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

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public int getId_estado() {
		return id_estado;
	}
}

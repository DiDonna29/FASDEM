/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author marcenrl
 * 
 */
public class Poliza implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8988426025512720398L;
	private int id;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private String fechaInicioString;
    private String fechaFinString;
    private boolean activo;

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

    public Date getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
	return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    public String getFechaInicioString() {
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}

	public boolean isActivo() {
	return activo;
    }

    public void setActivo(boolean activo) {
	this.activo = activo;
    }
}

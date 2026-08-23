/*
 * Created on 20-abr-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.seguridad.bean;

import java.io.Serializable;

/**
 * @author hvazquez
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TipoAccion implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1859949527557617881L;
    private String id;
    private String descripcion;
    private int proyecto;
    private String nombreTabla;
    private String nombreCampo;
    private int cantidad = 0;

    public TipoAccion() {
    }

    public void setId(String valor) {
	id = valor;
    }

    public void setDescripcion(String valor) {
	descripcion = valor;
    }

    /**
     * @return
     */
    public String getDescripcion() {
	return descripcion;
    }

    /**
     * @return
     */
    public String getId() {
	return id;
    }

    /**
     * @return
     */
    public String getNombreCampo() {
	return nombreCampo;
    }

    /**
     * @return
     */
    public String getNombreTabla() {
	return nombreTabla;
    }

    /**
     * @return
     */
    public int getProyecto() {
	return proyecto;
    }

    /**
     * @param string
     */
    public void setNombreCampo(String string) {
	nombreCampo = string;
    }

    /**
     * @param string
     */
    public void setNombreTabla(String string) {
	nombreTabla = string;
    }

    /**
     * @param i
     */
    public void setProyecto(int i) {
	proyecto = i;
    }

    /**
     * @return
     */
    public int getCantidad() {
	return cantidad;
    }

    /**
     * @param i
     */
    public void setCantidad(int i) {
	cantidad = i;
    }
}

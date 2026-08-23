/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 * 
 */
public class Proveedor implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7900348587445098046L;
	private int id;
    private String identificador;
    private String descripcion;
    private TipoProveedor tipoProveedor;
    private boolean activo;
    private Cuenta cuentaBancaria;
    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getIdentificador() {
	return identificador;
    }

    public void setIdentificador(String identificador) {
	this.identificador = identificador;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public TipoProveedor getTipoProveedor() {
	return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
	this.tipoProveedor = tipoProveedor;
    }

    public boolean isActivo() {
	return activo;
    }

    public void setActivo(boolean activo) {
	this.activo = activo;
    }

    
    public Cuenta getCuentaBancaria() {
        return cuentaBancaria;
    }

    
    public void setCuentaBancaria(Cuenta cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
    
    
}

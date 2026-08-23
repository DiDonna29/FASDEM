/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl
 *
 */
public class Clinica implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 2130080585866133432L;
	private int id;
    private String rif;
    private String nombre;
    private String direccion;
    private String telefono;
    private String cuenta;
    private String contacto;
    private int id_estado;
    private boolean isActivo;
    private String tipo;
    private Cuenta objcuenta;
    private TipoProveedor tipoProveedor;
    private boolean razonable;
    private String servicio;
    private int id_ciudad;
    
    public String getRif() {
        return rif;
    }
    
    public void setRif(String rif) {
        this.rif = rif;
    }

    public Cuenta getObjcuenta() {
        return objcuenta;
    }
    
    public void setObjcuenta(Cuenta objcuenta) {
        this.objcuenta = objcuenta;
    }

    public boolean getIsActivo() {
        return isActivo;
    }
    
    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
    }

    public boolean getRazonable() {
        return razonable;
    }
    
    public void setRazonable(boolean razonable) {
        this.razonable = razonable;
    }
    
    public String getContacto() {
        return contacto;
    }
    
    public void setContacto(String Contacto) {
        this.contacto = Contacto;
    }

    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String Telefono) {
        this.telefono = Telefono;
    }
    public String getCuenta() {
        return cuenta;
    }
    
    public void setCuenta(String Cuenta) {
        this.cuenta = Cuenta;
    }

    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getServicio() {
		return servicio;
	}

	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}

	public int getId_ciudad() {
		return id_ciudad;
	}


}

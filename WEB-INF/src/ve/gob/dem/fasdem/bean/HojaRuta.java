/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ve.gob.dem.framework.seguridad.bean.Usuario;




public class HojaRuta implements Serializable {

	private static final long serialVersionUID = 4744199970294151250L;
	
	private int id;
	private String numero;
	private String descripcion;
	private Date fecha;
	private int cantidad;
	private EstatusHojaRuta status;
	private Usuario analista;
	private int	periodo_fiscal;
	private TipoHojaRuta tipo;
	private int secuencia_hoja;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public EstatusHojaRuta getStatus() {
		return status;
	}

	public void setStatus(EstatusHojaRuta status) {
		this.status = status;
	}

	public Usuario getAnalista() {
		return analista;
	}

	public void setAnalista(Usuario analista) {
		this.analista = analista;
	}

	public int getPeriodo_fiscal() {
		return periodo_fiscal;
	}

	public void setPeriodo_fiscal(int periodo_fiscal) {
		this.periodo_fiscal = periodo_fiscal;
	}

	public TipoHojaRuta getTipo() {
		return tipo;
	}

	public void setTipo(TipoHojaRuta tipo) {
		this.tipo = tipo;
	}

	public int getSecuencia_hoja() {
		return secuencia_hoja;
	}

	public void setSecuencia_hoja(int secuencia_hoja) {
		this.secuencia_hoja = secuencia_hoja;
	}
	
}

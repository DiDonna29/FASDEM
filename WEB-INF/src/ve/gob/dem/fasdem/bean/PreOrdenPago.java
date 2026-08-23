/**01/12/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author marcenrl
 * 
 */
public class PreOrdenPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4744199970294151250L;
	/**
	 * 
	 */
	
	private int id;
	private String aniomes;
	private String codigo_preorden;
	private Date fecha_preorden;
	private Date fecha_orden;
	private Date fecha_pagado;
	private String nro_orden;
	private Clinica proveedor;
	private TipoEmpleado tipo_empleado;
	private int tipo_preorden;
	private int anioPreorden;
	private int secuenciaPreOrden;
	private String cod_completo;
	private Double unidad_tributaria;
	private int aplicaTimbre;
	private int id_dependencia;
	private String usuario;
	private Persona titular;
	private Double retencion_iva;
	private boolean aplica_isrl;
	private HojaRuta hoja;
	private String causado;
	private Double monto;
	private String montotxt;
	private Double monto401;
	private Double monto407;
	private int cantidad;
	private int iva;
	private int mtoisr;
	private int mtotmf;

	
	
	
	private EstatusPreOrden estatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAniomes() {
		return aniomes;
	}
	public void setAniomes(String aniomes) {
		this.aniomes = aniomes;
	}
	public String getCodigo_preorden() {
		return codigo_preorden;
	}
	public void setCodigo_preorden(String codigoPreorden) {
		codigo_preorden = codigoPreorden;
	}
	public Date getFecha_preorden() {
		return fecha_preorden;
	}
	public void setFecha_preorden(Date fechaPreorden) {
		fecha_preorden = fechaPreorden;
	}
	public Date getFecha_orden() {
		return fecha_orden;
	}
	public void setFecha_orden(Date fechaOrden) {
		fecha_orden = fechaOrden;
	}
	public Date getFecha_pagado() {
		return fecha_pagado;
	}
	public void setFecha_pagado(Date fechaPagado) {
		fecha_pagado = fechaPagado;
	}
	public String getNro_orden() {
		return nro_orden;
	}
	public void setNro_orden(String nroOrden) {
		nro_orden = nroOrden;
	}
	public Clinica getProveedor() {
		return proveedor;
	}
	public void setProveedor(Clinica proveedor) {
		this.proveedor = proveedor;
	}
	public EstatusPreOrden getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusPreOrden estatus) {
		this.estatus = estatus;
	}
	public TipoEmpleado getTipo_empleado() {
		return tipo_empleado;
	}
	public void setTipo_empleado(TipoEmpleado tipoEmpleado) {
		tipo_empleado = tipoEmpleado;
	}
	public int getAnioPreorden() {
		return anioPreorden;
	}
	public void setAnioPreorden(int anioPreorden) {
		this.anioPreorden = anioPreorden;
	}
	public int getSecuenciaPreOrden() {
		return secuenciaPreOrden;
	}
	public void setSecuenciaPreOrden(int secuenciaPreOrden) {
		this.secuenciaPreOrden = secuenciaPreOrden;
	}
	public String getCod_completo() {
		return cod_completo;
	}
	public void setCod_completo(String codCompleto) {
		cod_completo = codCompleto;
	}
	public Double getUnidad_tributaria() {
		return unidad_tributaria;
	}
	public void setUnidad_tributaria(Double unidadTributaria) {
		unidad_tributaria = unidadTributaria;
	}
	public int getAplicaTimbre() {
		return aplicaTimbre;
	}
	public void setAplicaTimbre(int aplicaTimbre) {
		this.aplicaTimbre = aplicaTimbre;
	}
	public int getTipo_preorden() {
		return tipo_preorden;
	}
	public void setTipo_preorden(int tipoPreorden) {
		tipo_preorden = tipoPreorden;
	}
	public int getId_dependencia() {
		return id_dependencia;
	}
	public void setId_dependencia(int idDependencia) {
		id_dependencia = idDependencia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Persona getTitular() {
		return titular;
	}
	public void setTitular(Persona titular) {
		this.titular = titular;
	}
	public Double getRetencion_iva() {
		return retencion_iva;
	}
	public void setRetencion_iva(Double retencionIva) {
		retencion_iva = retencionIva;
	}
	public boolean isAplica_isrl() {
		return aplica_isrl;
	}
	public void setAplica_isrl(boolean aplicaIsrl) {
		aplica_isrl = aplicaIsrl;
	}
	public HojaRuta getHoja() {
		return hoja;
	}
	public void setHoja(HojaRuta hoja) {
		this.hoja = hoja;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCausado() {
		return causado;
	}
	public void setCausado(String causado) {
		this.causado = causado;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getMontotxt() {
		return montotxt;
	}
	public void setMontotxt(String montotxt) {
		this.montotxt = montotxt;
	}
	public Double getMonto401() {
		return monto401;
	}
	public void setMonto401(Double monto401) {
		this.monto401 = monto401;
	}
	public Double getMonto407() {
		return monto407;
	}
	public void setMonto407(Double monto407) {
		this.monto407 = monto407;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public int getMtoisr() {
		return mtoisr;
	}
	public void setMtoisr(int mtoisr) {
		this.mtoisr = mtoisr;
	}
	public int getMtotmf() {
		return mtotmf;
	}
	public void setMtotmf(int mtotmf) {
		this.mtotmf = mtotmf;
	}

	
	
	
	
	
}

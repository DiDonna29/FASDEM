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
public class DetalleFacturaPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4160599083231009724L;
	private int id_siniestro;
	private String aniomes;
	private String codigo_siniestro;
	private String sub_codigo_siniestro;
	private int id_factura;
	private String nro_factura;
	private Date Fecha_factura;
	private Double Monto_Factura;
	private Double base_imponible_isrl;
	private Double base_imponible_timbre;
	private Double porcentaje_isrl;
	private Double porcentaje_timbre;
	private Double porcentaje_iva;
	private Double monto_isrl;
	private Double monto_timbre;
	private Clinica proveedor;
	private TipoEmpleado TipoEmpleado;
	private String PreOrdendePago;
	private String id_estatus;
	private Persona titular;
	private String tipoTramiteSiniestro;
	private String estatusSiniestro;
	private String tipoSiniestro;
	private String anioSiniestro;
	private String codSiniestroCompl;
	
	
	
	public int getId_siniestro() {
		return id_siniestro;
	}
	public void setId_siniestro(int idSiniestro) {
		id_siniestro = idSiniestro;
	}
	public String getAniomes() {
		return aniomes;
	}
	public void setAniomes(String aniomes) {
		this.aniomes = aniomes;
	}
	public String getCodigo_siniestro() {
		return codigo_siniestro;
	}
	public void setCodigo_siniestro(String codigoSiniestro) {
		codigo_siniestro = codigoSiniestro;
	}
	public String getSub_codigo_siniestro() {
		return sub_codigo_siniestro;
	}
	public void setSub_codigo_siniestro(String subCodigoSiniestro) {
		sub_codigo_siniestro = subCodigoSiniestro;
	}
	public int getId_factura() {
		return id_factura;
	}
	public void setId_factura(int idFactura) {
		id_factura = idFactura;
	}
	public String getNro_factura() {
		return nro_factura;
	}
	public void setNro_factura(String nroFactura) {
		nro_factura = nroFactura;
	}
	public Date getFecha_factura() {
		return Fecha_factura;
	}
	public void setFecha_factura(Date fechaFactura) {
		Fecha_factura = fechaFactura;
	}
	public Double getMonto_Factura() {
		return Monto_Factura;
	}
	public void setMonto_Factura(Double montoFactura) {
		Monto_Factura = montoFactura;
	}
	public Double getBase_imponible_isrl() {
		return base_imponible_isrl;
	}
	public void setBase_imponible_isrl(Double baseImponibleIsrl) {
		base_imponible_isrl = baseImponibleIsrl;
	}
	public Double getBase_imponible_timbre() {
		return base_imponible_timbre;
	}
	public void setBase_imponible_timbre(Double baseImponibleTimbre) {
		base_imponible_timbre = baseImponibleTimbre;
	}
	
	public Double getMonto_isrl() {
		return monto_isrl;
	}
	public void setMonto_isrl(Double montoIsrl) {
		monto_isrl = montoIsrl;
	}
	public Double getMonto_timbre() {
		return monto_timbre;
	}
	public void setMonto_timbre(Double montoTimbre) {
		monto_timbre = montoTimbre;
	}
	public Clinica getProveedor() {
		return proveedor;
	}
	public void setProveedor(Clinica proveedor) {
		this.proveedor = proveedor;
	}
	public TipoEmpleado getTipoEmpleado() {
		return TipoEmpleado;
	}
	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}
	public String getPreOrdendePago() {
		return PreOrdendePago;
	}
	public void setPreOrdendePago(String preOrdendePago) {
		PreOrdendePago = preOrdendePago;
	}
	public String getId_estatus() {
		return id_estatus;
	}
	public void setId_estatus(String idEstatus) {
		id_estatus = idEstatus;
	}
	public Double getPorcentaje_isrl() {
		return porcentaje_isrl;
	}
	public void setPorcentaje_isrl(Double porcentajeIsrl) {
		porcentaje_isrl = porcentajeIsrl;
	}
	public Double getPorcentaje_timbre() {
		return porcentaje_timbre;
	}
	public void setPorcentaje_timbre(Double porcentajeTimbre) {
		porcentaje_timbre = porcentajeTimbre;
	}
	public Double getPorcentaje_iva() {
		return porcentaje_iva;
	}
	public void setPorcentaje_iva(Double porcentajeIva) {
		porcentaje_iva = porcentajeIva;
	}
	public Persona getTitular() {
		return titular;
	}
	public void setTitular(Persona titular) {
		this.titular = titular;
	}
	public void setTipoTramiteSiniestro(String tipoTramiteSiniestro) {
		this.tipoTramiteSiniestro = tipoTramiteSiniestro;
	}
	public String getTipoTramiteSiniestro() {
		return tipoTramiteSiniestro;
	}
	public void setEstatusSiniestro(String estatusSiniestro) {
		this.estatusSiniestro = estatusSiniestro;
	}
	public String getEstatusSiniestro() {
		return estatusSiniestro;
	}
	public void setTipoSiniestro(String tipoSiniestro) {
		this.tipoSiniestro = tipoSiniestro;
	}
	public String getTipoSiniestro() {
		return tipoSiniestro;
	}
	public String getAnioSiniestro() {
		return anioSiniestro;
	}
	public void setAnioSiniestro(String anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}
	public String getCodSiniestroCompl() {
		return codSiniestroCompl;
	}
	public void setCodSiniestroCompl(String codSiniestroCompl) {
		this.codSiniestroCompl = codSiniestroCompl;
	}
	
	


	
	
}

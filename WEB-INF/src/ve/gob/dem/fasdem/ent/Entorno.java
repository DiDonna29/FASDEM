/**
 * 
 */
package ve.gob.dem.fasdem.ent;

import java.io.Serializable;

/**
 * @author marcenrl
 */
public class Entorno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6823758328198113641L;
	public static final int MOD_ALL = 99;
	public static final int MOD_NINGUNO = 98;
	public static final int MOD_BUSCA_BENEFICIARIO = 1;
	public static final int MOD_APS_NUEVO = 2;
	public static final int MOD_CONSULTA_SINIESTRO = 3;
	public static final int MOD_MEDICINAS_NUEVO = 4;
	public static final int MOD_BUSCA_PAGO = 5;
	public static final int MOD_REEMBOLSO_NUEVO = 6;
	public static final int MOD_CAUSA_INGRESO = 7;
	public static final int MOD_REEMBOLSO = 8;
	public static final int MOD_MEDICINAS_CONSULTA = 9;
	public static final int MOD_CARGA_FACTURA = 10;
	public static final int MOD_BUSCAR_PROVEEDOR = 11;
	public static final int MOD_PAGO_NUEVO = 12;
	public static final int MOD_PAGO_NUEVO_ASEGURADO = 13;
	public static final int MOD_MEDICINAS_CONSULTA_LIQUIDACION = 14;
	public static final int MOD_MEDICINAS_LIQUIDACION = 15;
	public static final int MOD_MEDICINAS_CONSULTA_EDITA = 16;
	public static final int MOD_MEDICINAS_EDITA = 17;
	public static final int MOD_REEMBOLSO_LIQUIDACION = 18;
	public static final int MOD_REEMBOLSO_LIQUIDACION_RESULT = 19;
	public static final int MOD_APS_CONSULTA = 20;
	public static final int MOD_CARGA_PREORDEN = 21;
	public static final int MOD_BUSCAR_SINIESTRO = 22;
	public static final int MOD_APS_EDICION = 23;
	public static final int MOD_EDITA_PAGO = 24;
	public static final int MOD_EDITA_PAGO_PREORDEN = 25;
	public static final int MOD_CARTA_AVAL_NUEVO = 26;
	public static final int MOD_EDITA_PATOLOGIA = 27;
	public static final int MOD_EDITA_TRATAMIENTO = 28;
	public static final int MOD_REEMBOLSOS_CONSULTA = 29;
	public static final int MOD_EDITA_PROVEEDOR = 30;
	public static final int MOD_EDITA_ESPECIALIDAD = 31;
	public static final int MOD_EDITA_TIPO_GASTO = 32;
	public static final int MOD_EDITA_COBERTURA = 33;
	public static final int MOD_REEMBOLSO_CONSULTA_EDITA = 34;
	public static final int MOD_EMERGENCIA_NUEVO = 35;
	public static final int MOD_NUEVO_NOTA_TECNICA = 36;
	public static final int MOD_CARTA_AVAL_EDICION = 37;
	public static final int MOD_EMERGENCIA_CONSULTA = 38;
	public static final int MOD_EMERGENCIA_CONSULTA_LIQUIDACION = 39;
	public static final int MOD_EMERGENCIA_LIQUIDACION = 40;
	public static final int MOD_EMERGENCIA_EDICION = 41;
	public static final int MOD_CARTA_AVAL_CONSULTA = 42;
	public static final int MOD_CARTA_AVAL_LIQUIDACION = 43;
	public static final int MOD_CARTA_AVAL_CONSULTA_LIQUIDACION = 44;
	public static final int MOD_REPORTE_USUARIO = 45;
	public static final int MOD_CARTA_AVAL_EGRESAR = 46;
	public static final int MOD_INGRESA_TIPO_GASTO = 47;
	public static final int MOD_APS_CAMBIO_ESTATUS = 48;
	public static final int MOD_APS_LIQUIDAR = 49;
	public static final int MOD_DETALLE_FACTURA = 50;
	public static final int MOD_AGREGA_TIPO_GASTO = 51;
	public static final int MOD_CUENTA_NOMINA = 52;
	// public static final int MOD_DETALLE_FACTURA_CARTA = 53;
	public static final int MOD_INGRESA_COBERTURA = 54;
	public static final int MOD_INGRESA_ESPECIALIDAD = 55;
	public static final int MOD_REEMBOLSOS_EDITA = 56;
	public static final int MOD_INGRESA_PATOLOGIA = 57;
	public static final int MOD_AGREGA_ESPECIALIDAD = 58;
	public static final int MOD_BUSCA_BENEFICIARIO_CONSULTA = 59;
	public static final int MOD_REEMBOLSO_LIQUIDAR = 60;
	public static final int MOD_REEMBOLSO_EDICION = 61;
	public static final int MOD_LIQUIDACION_SINIESTROS = 62;
	public static final int MOD_EMERGENCIA_NUEVO_SECUELA = 63;
	public static final int MOD_ADD_NOTAMEDICA = 64;
	public static final int MOD_CARTA_AVAL_REVERSO = 65;
	public static final int MOD_CARGA_FACTURA_BUSCASINIESTRO = 66;
	public static final int MOD_BUSCAR_REEMBOLSO = 67;
	public static final int MOD_CONSULTA_COBERTURA = 68;
	public static final int MOD_REPORTE_ESTADISTICAS = 69;
	public static final int MOD_BUSCAR_PROVEEDOR_P = 70;
	public static final int MOD_EMERGENCIA_NUEVO_DECLARAR = 71;
	public static final int MOD_CARGAR_FACTURA = 72;
	private boolean cedula;
	private boolean id;
	private boolean idSiniestro;
	private boolean idFactura;
	private boolean nombres;
	private boolean nombreApellido;
	private boolean apellidos;
	private boolean fechaOcurrencia;
	private boolean fechaNotificacion;
	private boolean tipoProveedor;
	private boolean tipoProveedorP;
	private boolean causaIngreso;
	private boolean tipoEnfermedad;
	private boolean tipoSiniestro;
	private boolean observacion;
	private boolean observacionMedicinas;
	private boolean observacionNoRequerida;
	private boolean organo;
	private boolean OrdenPago;
	private boolean especialidad;
	private boolean patologias;
	private boolean tratamiento;
	private boolean ubicacionFisica;
	private boolean montoPagado;
	private boolean montoLiquidado;
	private boolean montoAmparado;
	private boolean montoNoAmparado;
	private boolean montoApagar;
	private boolean controlFactura;
	private boolean montoProntoPago;
	private boolean numeroOrdenPago;
	private boolean numeroFactura;
	private boolean porcentajeIva;
	private boolean listIva;
	private boolean numeroRemesa;
	private boolean descuentoDeducible;
	private boolean fechaCreado;
	private boolean fechaLiquidado;
	private boolean fechaFactura;
	private boolean fechaAprobado;
	private boolean fechaRecepcionFactura;
	private boolean UltimaModificacion;
	private boolean fechaRemesa;
	private boolean fechaCheque;
	private boolean numeroCheque;
	private boolean tipoDocumento;
	private boolean tipoGasto;
	private boolean cantFacturas;
	private boolean presupInicial;
	private boolean tgastosClinicos;
	private boolean thonoMedicos;
	private boolean totalFacturado;
	private boolean totalAliquidar;
	private boolean totalMontoNoAmparado;
	private boolean numeroSiniestro;
	private boolean subCodigo;
	private boolean codigo;
	private boolean rif;
	private boolean codigoetiqueta;
	private boolean codigoPreOrden;
	private boolean tipoCobertura;
	private boolean tipoEnfermedad2;
	private boolean idOrdenPago;
	private boolean datosPersonales;
	private boolean datosSiniestro;
	private boolean fechaInicio;
	private boolean fechaFin;
	private boolean proveedor;
	/*creo la variable*/
	private boolean proveedor_combo;
	/*****************/
	private boolean sexo;
	private boolean fechaNacimiento;
	private boolean parentesco;
	private boolean edad;
	private boolean idProveedor;
	private boolean idTipoProveedor;
	private boolean tipoEmpleado;
	private boolean montoFactura;
	private boolean fechaLiquidacion;
	private boolean numeroConceptoRetencion;
	private boolean diasTerapiaIntensiva;
	private boolean monto;
	private boolean estatus;
	private boolean suma;
	private boolean cobertura;
	private boolean detalleServMedico;
	private boolean fechaUltimaModificacion;
	private boolean archivos;
	private boolean adjuntos;
	private boolean detalleCobertura;
	private boolean montoHonorariosMedicos;
	private boolean montoGastosClinicos;
	private boolean montoHonorariosMedicosAmparado;
	private boolean montoGastosClinicosAmparado;
	private boolean montoHonorariosMedicosNoAmparado;
	private boolean montoGastosClinicosNoAmparado;
	private boolean montoExamenesPreoperatorios;
	private boolean montoMaterialMedicoConIva;
	private boolean montoMaterialMedicoSinIva;
	private boolean montoCalculadoAmparado;
	private boolean montoCalculado;
	private boolean boton;
	private boolean montoPresupuestado;
	private boolean montoNegociado;
	private boolean fechaIngreso;
	private boolean fechaEgreso;
	private boolean fechaEgresoRequerida;
	private boolean montoFuneraria;
	private boolean montoFunerariaNoAmparado;
	private boolean montoExamenesEspeciales;
	private boolean montoExamenesEspecialesNoAmparado;
	private boolean montoAmbulancia;
	private boolean montoAmbulanciaNoAmparado;
	private boolean vida;
	private boolean vidaNoAmparado;
	private boolean citaPostOperatorio;
	private boolean citaPreOperatorio;
	private boolean montoCalculadoNoAmparado;
	private int counter;
	private boolean tipoTratamiento;
	private boolean criterioBusqueda;
	private boolean descripcion;
	private boolean numeroControl;
	private boolean detallePresupuesto;
	private boolean detallePresupuestoEdit;
	private boolean detallePresupuestoEmergencia;
	private boolean detallePresupuestoEditEmergencia;
	private boolean anioSiniestro;
	private boolean tituloItems;
	private boolean anio;
	private boolean detalleSiniestroEgreso;
	private boolean detalleSiniestroPadre;
	private boolean listTipoTramite;
	private boolean cuentas;
	private boolean montosPresupuesto;
	private boolean tipoProveedorRmbls;
	private boolean notaTecnica;
	private boolean printNotaTecnica;
	private boolean notaMedica;
	private boolean verNotaMedica;
	private boolean rechazado;
	private boolean coberturaRangoFecha;
	private boolean anioBusqueda;
	private boolean poliza;
	private boolean alMenosDos;
	private boolean fechaProveedor;
	private boolean mes;
	
	

	/**
	 * @return the mes
	 */
	public boolean isMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(boolean mes) {
		this.mes = mes;
	}

	public boolean isTipoProveedorRmbls() {
		return tipoProveedorRmbls;
	}

	public void setTipoProveedorRmbls(boolean tipoProveedorRmbls) {
		this.tipoProveedorRmbls = tipoProveedorRmbls;
	}

	public boolean isCuentas() {
		return cuentas;
	}

	public void setCuentas(boolean cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @param ordenPago
	 *            the ordenPago to set
	 */
	public Entorno(int i) {
		switch (i) {
		case MOD_BUSCA_BENEFICIARIO:
			setCedula(true);
			setBoton(true);
			break;
		case MOD_CONSULTA_SINIESTRO:
			setCedula(true);
			setNombreApellido(true);
			setAnioBusqueda(true);
			setCodigo(true);
			setRif(true);
			setFechaProveedor(true);
			setListTipoTramite(true);
			setAlMenosDos(true);
			setBoton(true);
			break;
		case MOD_LIQUIDACION_SINIESTROS:
			// setListTipoTramite(true);
			setAnioSiniestro(true);
			break;
		case MOD_BUSCA_BENEFICIARIO_CONSULTA:
			setBoton(true);
			break;
		case MOD_APS_NUEVO:
			setCobertura(true);
			setDatosPersonales(true);
			setFechaOcurrencia(true);
			setFechaNotificacion(true);
			setTipoProveedor(true);
			setCausaIngreso(true);
			setTipoEnfermedad(true);
			setTipoSiniestro(true);
			setObservacion(true);
			setProveedor(true);
			setTipoTratamiento(true);
			setMonto(true);
			setArchivos(true);
			setBoton(true);
			break;
		case MOD_CARGA_FACTURA:
			setFechaFactura(true);
			setNumeroFactura(true);
			setControlFactura(true);
			setMontoFactura(true);
			setMontoAmparado(true);
			setMontoNoAmparado(true);
			setTipoGasto(true);
			setBoton(true);
			break;
		case MOD_REEMBOLSO:
			setCuentas(true);
			setDatosPersonales(true);
			// setTipoEnfermedad(true);
			// setCobertura(true);
			setCoberturaRangoFecha(true);
			setTipoSiniestro(true);
			setCausaIngreso(true);
			setTipoProveedor(true);
			// setTipoProveedorRmbls(true);
			setMontoFactura(true);
			// setObservacion(true);
			setObservacionNoRequerida(true);
			setEstatus(true);
			setFechaOcurrencia(true);
			setFechaNotificacion(true);
			// setRechazado(true);
			setBoton(true);
			break;
		case MOD_EMERGENCIA_NUEVO_DECLARAR:
			setDatosPersonales(true);
			setCoberturaRangoFecha(true);
			setTipoSiniestro(true);
			setCausaIngreso(true);
			setTipoProveedor(true);
			setObservacionNoRequerida(true);
			setProveedor(true);
			setFechaOcurrencia(true);
			setFechaEgreso(true);
			setMontoPresupuestado(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			setTipoTratamiento(true);
			setCitaPostOperatorio(true);
			break;
		case MOD_REEMBOLSO_LIQUIDACION:
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_MEDICINAS_NUEVO:
			setCobertura(true);
			setDetalleServMedico(true);
			setObservacion(true);
			setDatosPersonales(true);
			//setTipoProveedor(true);
			//setProveedor(true);
			setTipoEnfermedad(true);
			setCausaIngreso(true);
			setBoton(true);
			break;
		case MOD_MEDICINAS_LIQUIDACION:
			setAnioSiniestro(true);
			setNumeroFactura(true);
			setControlFactura(true);
			setFechaRecepcionFactura(true);
			setMontoFactura(true);
			setMontoAmparado(true);
			setFechaFactura(true);
			setObservacion(true);
			setTipoProveedor(true);
			//setProveedor(true);
			setProveedor_combo(true);
			setBoton(true);
			break;
		case MOD_EMERGENCIA_LIQUIDACION:
			setNumeroFactura(true);
			setControlFactura(true);
			setFechaRecepcionFactura(true);
			setFechaFactura(true);
			setBoton(true);
			break;
		case MOD_CARTA_AVAL_LIQUIDACION:
			setNumeroFactura(true);
			setControlFactura(true);
			setFechaRecepcionFactura(true);
			setFechaFactura(true);
			setBoton(true);
			break;
		case MOD_MEDICINAS_EDITA:
			setAnioSiniestro(true);
			setDatosPersonales(true);
			setObservacion(true);
			setCobertura(true);
			//setTipoProveedor(true);
			//setProveedor(true);
			setTipoEnfermedad(true);
			setCausaIngreso(true);
			setEstatus(true);
			setId(true);
			setAdjuntos(true);
			setNotaTecnica(true);
			setPrintNotaTecnica(true);
			setVerNotaMedica(true);
			// setBoton(true);
			break;
		case MOD_REEMBOLSOS_EDITA:
			setCobertura(true);
			setFechaOcurrencia(true);
			setFechaNotificacion(true);
			setTipoSiniestro(true);
			// setObservacion(true);
			setObservacionNoRequerida(true);
			// setTipoEnfermedad(true);
			setCausaIngreso(true);
			setMonto(true);
			setEstatus(true);
			setObservacion(true);
			setBoton(true);
			break;
		case MOD_MEDICINAS_CONSULTA:
			setBoton(true);
			break;
		case MOD_REEMBOLSOS_CONSULTA:
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_REEMBOLSO_CONSULTA_EDITA:
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_MEDICINAS_CONSULTA_LIQUIDACION:
			setAnioSiniestro(true);
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_EMERGENCIA_CONSULTA_LIQUIDACION:
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_CARTA_AVAL_CONSULTA_LIQUIDACION:
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_MEDICINAS_CONSULTA_EDITA:
			setSubCodigo(true);
			setBoton(true);
			break;
		case MOD_BUSCA_PAGO:
			setCodigoPreOrden(true);
			setBoton(true);
			break;
		case MOD_EDITA_PAGO_PREORDEN:
			setEstatus(true);
			setBoton(true);
			break;
		case MOD_EDITA_PAGO:
			setCodigoPreOrden(true);
			setBoton(true);
			break;
		case MOD_CAUSA_INGRESO:
			setEspecialidad(true);
			setOrgano(true);
			setPatologias(true);
			setTratamiento(true);
			setBoton(true);
			break;
		case MOD_BUSCAR_PROVEEDOR:
			setNombres(true);
			setTipoProveedor(true);
//			setListTipoTramite(true);
			break;
		case MOD_BUSCAR_PROVEEDOR_P:
			setNombres(true);
			setTipoProveedorP(true);
//			setListTipoTramite(true);
			break;
		case MOD_PAGO_NUEVO:
			setTipoProveedor(true);
			setProveedor(true);
			setFechaInicio(true);
			setFechaFin(true);
			setBoton(true);
			break;
		case MOD_PAGO_NUEVO_ASEGURADO:
			setTipoEmpleado(true);
			setTipoProveedor(true);
			setFechaInicio(true);
			setFechaFin(true);
			setBoton(true);
			break;
		case MOD_APS_CONSULTA:
			setBoton(true);
			break;
		case MOD_EMERGENCIA_CONSULTA:
			setBoton(true);
			break;
		case MOD_BUSCAR_SINIESTRO:
			setAnioBusqueda(true);
			setAnioSiniestro(true);
			setCodigo(true);
			setBoton(true);
			break;
		case MOD_BUSCAR_REEMBOLSO:
			setAnioSiniestro(true);
			setCodigo(true);
			setBoton(true);
			break;
		case MOD_APS_EDICION:
			setAnioSiniestro(true);
			setId(true);
			setCobertura(true);
			setCodigoetiqueta(true);
			setFechaOcurrencia(true);
			setFechaNotificacion(true);
			setTipoSiniestro(true);
			setTipoEnfermedad(true);
			setTipoProveedor(true);
			setIdProveedor(true);
			setProveedor(true);
			setCausaIngreso(true);
			setObservacion(true);
			setMonto(true);
			setAdjuntos(true);
			setNotaTecnica(true);
			setPrintNotaTecnica(true);
			setVerNotaMedica(true);
			setBoton(true);
			break;
		case MOD_REEMBOLSO_EDICION:
			setAnioSiniestro(true);
			setId(true);
			setCoberturaRangoFecha(true);
			// setDatosPersonales(true);
			setFechaOcurrencia(true);
			setFechaNotificacion(true);
			// setTipoProveedorRmbls(true);
			setTipoProveedor(true);
			setTipoSiniestro(true);
			setObservacionNoRequerida(true);
			setCausaIngreso(true);
			setMonto(true);
			setEstatus(true);
			// setBoton(true);
			break;
		case MOD_CARGA_PREORDEN:
			setTgastosClinicos(true);
			setThonoMedicos(true);
			setTotalAliquidar(true);
			setBoton(true);
			break;
		case MOD_NINGUNO:
			break;
		case MOD_ALL:
			setCedula(true);
			setNombres(true);
			setApellidos(true);
			break;
		case MOD_CARTA_AVAL_NUEVO:
			// setTituloItems(true);
			setDetalleSiniestroPadre(true);
			setCobertura(true);
			setEstatus(true);
			setDatosPersonales(true);
			setTipoProveedor(true);
			setCausaIngreso(true);
			setTipoSiniestro(true);
			setObservacionNoRequerida(true);
			setProveedor(true);
			setMontoPresupuestado(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			// setDetallePresupuesto(true);
			setCitaPostOperatorio(true);
			// setCitaPreOperatorio(true);
			setTipoTratamiento(true);
			break;
		case MOD_CARTA_AVAL_EDICION:
			//setTituloItems(true);
			setDetalleSiniestroPadre(true);
			setDatosPersonales(true);
			setCobertura(true);
			setTipoProveedor(true);
			setCausaIngreso(true);
			setTipoSiniestro(true);
			setObservacionNoRequerida(true);
			setProveedor(true);
			setTipoTratamiento(true);
			setEstatus(true);
			setId(true);
			setAnioSiniestro(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			setCitaPostOperatorio(true);
			// setCitaPreOperatorio(true);
			setCodigoetiqueta(true);
			setFechaNotificacion(true);
			setIdProveedor(true);
			setAdjuntos(true);
			setNotaTecnica(true);
			setPrintNotaTecnica(true);
			setVerNotaMedica(true);
			break;
		case MOD_CARTA_AVAL_CONSULTA:
			setCriterioBusqueda(true);
			setAnioBusqueda(true);
			setBoton(true);
			break;
		case MOD_EMERGENCIA_EDICION:
			setAnioSiniestro(true);
			setDatosPersonales(true);
			setDetalleSiniestroPadre(true);
			setCobertura(true);
			setFechaIngreso(true);
			setFechaEgreso(true);
			setTipoProveedor(true);
			setCausaIngreso(true);
			setTipoSiniestro(true);
			setObservacionNoRequerida(true);
			setProveedor(true);
			// setDetallePresupuestoEditEmergencia(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			setTipoTratamiento(true);
			setEstatus(true);
			setId(true);
			setCodigoetiqueta(true);
			setCitaPostOperatorio(true);
			setIdProveedor(true);
			setAdjuntos(true);
			setNotaTecnica(true);
			setPrintNotaTecnica(true);
			setVerNotaMedica(true);
			break;
		case MOD_EMERGENCIA_NUEVO:
			setCobertura(true);
			setDatosPersonales(true);
			setDetalleSiniestroPadre(true);
			setTipoProveedor(true);
			setCausaIngreso(true);
			setTipoSiniestro(true);
			setObservacionNoRequerida(true);
			setProveedor(true);
			setMontoPresupuestado(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			// setDetallePresupuestoEmergencia(true);
			setFechaIngreso(true);
			setFechaEgreso(true);
			setTipoTratamiento(true);
			setCitaPostOperatorio(true);
		case MOD_EMERGENCIA_NUEVO_SECUELA:
			setDatosPersonales(true);
			setTipoProveedor(true);
			setTipoSiniestro(true);
			setObservacionNoRequerida(true);
			setProveedor(true);
			setMontoPresupuestado(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			// setDetallePresupuestoEmergencia(true);
			setFechaIngreso(true);
			setFechaEgreso(true);
			setTipoTratamiento(true);
			setCitaPostOperatorio(true);
			break;
		case MOD_EDITA_PATOLOGIA:
			setPatologias(true);
			break;
		case MOD_EDITA_TRATAMIENTO:
			setTratamiento(true);
			setBoton(true);
			break;
		case MOD_EDITA_PROVEEDOR:
			setTipoProveedor(true);
			setProveedor(true);
			break;
		case MOD_EDITA_ESPECIALIDAD:
			setEspecialidad(true);
			break;
		case MOD_EDITA_TIPO_GASTO:
			setTipoGasto(true);
			break;
		case MOD_INGRESA_ESPECIALIDAD:
			setDescripcion(true);
			setBoton(false);
			break;
		case MOD_INGRESA_PATOLOGIA:
			setDescripcion(true);
			setBoton(false);
			break;
		case MOD_INGRESA_TIPO_GASTO:
			setDescripcion(true);
			setBoton(false);
			break;
		case MOD_AGREGA_TIPO_GASTO:
			setDescripcion(true);
			setBoton(true);
			break;
		case MOD_AGREGA_ESPECIALIDAD:
			setDescripcion(true);
			setBoton(true);
			break;
		case MOD_EDITA_COBERTURA:
			setCobertura(true);
			break;
		case MOD_NUEVO_NOTA_TECNICA:
			setAnioSiniestro(true);
			setObservacion(true);
			setIdSiniestro(true);
			setBoton(true);
			break;
		case MOD_REPORTE_USUARIO:
			setFechaInicio(true);
			setFechaFin(true);
			break;
		case MOD_CARTA_AVAL_EGRESAR:
			setId(true);
			setObservacion(true);
			setAnioSiniestro(true);
			setTituloItems(true);
			setDetalleSiniestroEgreso(true);
			setFechaIngreso(true);
			setCitaPostOperatorio(true);
			// setMontoPresupuestado(true);
			setMontoNegociado(true);
			setMontoAmparado(true);
			setFechaEgreso(true);
			break;
		case MOD_CUENTA_NOMINA:
			setCedula(true);
			setBoton(true);
			break;
		case MOD_APS_CAMBIO_ESTATUS:
			// setMontoAmparado(true);
			setObservacion(true);
			setEstatus(true);
			setBoton(true);
			break;
		case MOD_APS_LIQUIDAR:
			setAnioSiniestro(true);
			setNumeroFactura(true);
			setControlFactura(true);
			setPorcentajeIva(true);
			setMontoFactura(true);
			setFechaFactura(true);
			setFechaRecepcionFactura(true);
			setBoton(true);
			break;
		case MOD_CARGAR_FACTURA:
			setAnioSiniestro(true);
			setNumeroFactura(true);
			setControlFactura(true);
			setListIva(true);
			setMontoFactura(true);
			setFechaFactura(true);
			setFechaRecepcionFactura(true);
			setBoton(true);
			break;
		case MOD_REEMBOLSO_LIQUIDAR:
			setNumeroFactura(true);
			// setControlFactura(true);
			setMontoFactura(true);
			setFechaFactura(true);
			setFechaRecepcionFactura(true);
			setBoton(true);
			break;
		case MOD_DETALLE_FACTURA:
			setMonto(true);
			setTipoGasto(true);
			setBoton(true);
			break;
		case MOD_INGRESA_COBERTURA:
			setCobertura(true);
			setDescripcion(true);
			setBoton(false);
			break;
		case MOD_ADD_NOTAMEDICA:
			setNotaMedica(true);
			setBoton(false);
			break;
		case MOD_CARTA_AVAL_REVERSO:
			// setMontoAmparado(true);
			setObservacion(true);
			// setEstatus(true);
			setBoton(true);
			break;
		case MOD_CARGA_FACTURA_BUSCASINIESTRO:
			setAnioSiniestro(true);
			break;
		case MOD_CONSULTA_COBERTURA:
			//setAnioSiniestro(true);
			setPoliza(true);
			setBoton(true);
			break;
		default:
			break;
		}
	}


	public Entorno() {
	}

	public void setFilterReport(String argIds) {
		String[] ids = argIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			int intID = Integer.parseInt(ids[i]);
			switch (intID) {
			case 1:
				setCedula(true);
			case 2:
				setNombres(true);
				break;
			case 3:
				setApellidos(true);
				break;
			default:
				break;
			}
		}
	}

	public int counter() {
		counter = 0;
		if (cedula)
			counter++;// ok
		if (nombres)
			counter++;
		if (apellidos)
			counter++;
		return counter;
	}

	public boolean isDetalleServMedico() {
		return detalleServMedico;
	}

	public void setDetalleServMedico(boolean detalleServMedico) {
		this.detalleServMedico = detalleServMedico;
	}

	public boolean isSuma() {
		return suma;
	}

	public void setSuma(boolean suma) {
		this.suma = suma;
	}

	public boolean isFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(boolean fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public boolean isIdFactura() {
		return idFactura;
	}

	public void setIdFactura(boolean idFactura) {
		this.idFactura = idFactura;
	}

	public boolean isIdSiniestro() {
		return idSiniestro;
	}

	public void setIdSiniestro(boolean idSiniestro) {
		this.idSiniestro = idSiniestro;
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isMontoFactura() {
		return montoFactura;
	}

	public void setMontoFactura(boolean montoFactura) {
		this.montoFactura = montoFactura;
	}

	public boolean isFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(boolean fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public boolean isTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(boolean tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public boolean isEdad() {
		return edad;
	}

	public void setEdad(boolean edad) {
		this.edad = edad;
	}

	public boolean isFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(boolean fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isParentesco() {
		return parentesco;
	}

	public void setParentesco(boolean parentesco) {
		this.parentesco = parentesco;
	}

	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public boolean isTipoEnfermedad2() {
		return tipoEnfermedad2;
	}

	public void setTipoEnfermedad2(boolean tipoEnfermedad2) {
		this.tipoEnfermedad2 = tipoEnfermedad2;
	}

	public boolean isMontoAmparado() {
		return montoAmparado;
	}

	public void setMontoAmparado(boolean montoAmparado) {
		this.montoAmparado = montoAmparado;
	}

	public boolean isMontoNoAmparado() {
		return montoNoAmparado;
	}

	public boolean isControlFactura() {
		return controlFactura;
	}

	public void setControlFactura(boolean controlFactura) {
		this.controlFactura = controlFactura;
	}

	public void setOrdenPago(boolean ordenPago) {
		OrdenPago = ordenPago;
	}

	public void setMontoNoAmparado(boolean montoNoAmparado) {
		this.montoNoAmparado = montoNoAmparado;
	}

	public boolean isCedula() {
		return cedula;
	}

	public boolean isTotalAliquidar() {
		return totalAliquidar;
	}

	public void setTotalAliquidar(boolean totalAliquidar) {
		counter++;
		this.totalAliquidar = totalAliquidar;
	}

	public void setCedula(boolean cedula) {
		counter++;
		this.cedula = cedula;
	}

	public boolean isnumeroOrdenPago() {
		return numeroOrdenPago;
	}

	public void setnumeroOrdenPago(boolean numeroOrdenPago) {
		counter++;
		this.numeroOrdenPago = numeroOrdenPago;
	}

	public boolean isNombres() {
		return nombres;
	}

	public boolean isTipoCobertura() {
		return tipoCobertura;
	}

	public void setTipoCobertura(boolean tipoCobertura) {
		counter++;
		this.tipoCobertura = tipoCobertura;
	}

	public void setNombres(boolean nombres) {
		this.nombres = nombres;
	}

	public boolean isApellidos() {
		return apellidos;
	}

	public void setApellidos(boolean apellidos) {
		this.apellidos = apellidos;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isTgastosClinicos() {
		return tgastosClinicos;
	}

	public void setTgastosClinicos(boolean tgastosClinicos) {
		this.tgastosClinicos = tgastosClinicos;
	}

	public boolean isCantFacturas() {
		return cantFacturas;
	}

	public void setCantFacturas(boolean cantFacturas) {
		this.cantFacturas = cantFacturas;
	}

	public boolean isTotalFacturado() {
		return totalFacturado;
	}

	public void setTotalFacturado(boolean totalFacturado) {
		this.totalFacturado = totalFacturado;
	}

	public boolean isBoton() {
		return boton;
	}

	public void setBoton(boolean boton) {
		this.boton = boton;
	}

	public boolean isFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(boolean fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean isFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(boolean fechaFin) {
		this.fechaFin = fechaFin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPresupInicial() {
		return presupInicial;
	}

	public void setPresupInicial(boolean presupInicial) {
		this.presupInicial = presupInicial;
	}

	public static int getModAll() {
		return MOD_ALL;
	}

	public static int getModNinguno() {
		return MOD_NINGUNO;
	}

	public static int getModBuscaBeneficiario() {
		return MOD_BUSCA_BENEFICIARIO;
	}

	public boolean isFechaOcurrencia() {
		return fechaOcurrencia;
	}

	public void setFechaOcurrencia(boolean fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}

	public boolean isFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(boolean fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public boolean isTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(boolean tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public static int getModConsultaSiniestro() {
		return MOD_CONSULTA_SINIESTRO;
	}

	public static int getModBuscaPago() {
		return MOD_BUSCA_PAGO;
	}

	public boolean isCausaIngreso() {
		return causaIngreso;
	}

	public void setCausaIngreso(boolean causaIngreso) {
		this.causaIngreso = causaIngreso;
	}

	public boolean isTipoEnfermedad() {
		return tipoEnfermedad;
	}

	public void setTipoEnfermedad(boolean tipoEnfermedad) {
		this.tipoEnfermedad = tipoEnfermedad;
	}

	public boolean isTipoSiniestro() {
		return tipoSiniestro;
	}

	public void setTipoSiniestro(boolean tipoSiniestro) {
		this.tipoSiniestro = tipoSiniestro;
	}

	public boolean isObservacion() {
		return observacion;
	}

	public void setObservacion(boolean observacion) {
		this.observacion = observacion;
	}

	public static int getModApsNuevo() {
		return MOD_APS_NUEVO;
	}

	public static int getModPagoNuevo() {
		return MOD_PAGO_NUEVO;
	}

	public boolean isOrgano() {
		return organo;
	}

	public void setOrgano(boolean organo) {
		this.organo = organo;
	}

	public void setmontoHonorariosMedicos(boolean montoHonorariosMedicos) {
		this.montoHonorariosMedicos = montoHonorariosMedicos;
	}

	public boolean isPatologias() {
		return patologias;
	}

	public void setPatologias(boolean patologias) {
		this.patologias = patologias;
	}

	public boolean isTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(boolean tratamiento) {
		this.tratamiento = tratamiento;
	}

	public boolean isEspecialidad() {
		return especialidad;
	}

	public boolean isOrdenPago() {
		return OrdenPago;
	}

	public boolean isTotalMontoNoAmparado() {
		return totalMontoNoAmparado;
	}

	public void setTotalMontoNoAmparado(boolean totalMontoNoAmparado) {
		this.totalMontoNoAmparado = totalMontoNoAmparado;
	}

	public void setEspecialidad(boolean especialidad) {
		this.especialidad = especialidad;
	}

	public void setmontoGastosClinicos(boolean montoGastosClinicos) {
		this.montoGastosClinicos = montoGastosClinicos;
	}

	public static int getModMedicinasNuevo() {
		return MOD_MEDICINAS_NUEVO;
	}

	public boolean isMontoLiquidado() {
		return montoGastosClinicos;
	}

	/**
	 * @return the ubicacionFisica
	 */
	public boolean isUbicacionFisica() {
		return ubicacionFisica;
	}

	/**
	 * @param ubicacionFisica
	 *            the ubicacionFisica to set
	 */
	public void setUbicacionFisica(boolean ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	/**
	 * @return the montoPagado
	 */
	public boolean isMontoPagado() {
		return montoPagado;
	}

	/**
	 * @param montoPagado
	 *            the montoPagado to set
	 */
	public void setMontoPagado(boolean montoPagado) {
		this.montoPagado = montoPagado;
	}

	/**
	 * @return the montoProntoPago
	 */
	public boolean isMontoProntoPago() {
		return montoProntoPago;
	}

	/**
	 * @param montoProntoPago
	 *            the montoProntoPago to set
	 */
	public void setMontoProntoPago(boolean montoProntoPago) {
		this.montoProntoPago = montoProntoPago;
	}

	/**
	 * @return the numeroOrdenPago
	 */
	public boolean isNumeroOrdenPago() {
		return numeroOrdenPago;
	}

	/**
	 * @param numeroOrdenPago
	 *            the numeroOrdenPago to set
	 */
	public void setNumeroOrdenPago(boolean numeroOrdenPago) {
		this.numeroOrdenPago = numeroOrdenPago;
	}

	/**
	 * @return the numeroFactura
	 */
	public boolean isNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * @param numeroFactura
	 *            the numeroFactura to set
	 */
	public void setNumeroFactura(boolean numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * @return the numeroRemesa
	 */
	public boolean isNumeroRemesa() {
		return numeroRemesa;
	}

	/**
	 * @param numeroRemesa
	 *            the numeroRemesa to set
	 */
	public void setNumeroRemesa(boolean numeroRemesa) {
		this.numeroRemesa = numeroRemesa;
	}

	public static int getModReembolsoNuevo() {
		return MOD_REEMBOLSO_NUEVO;
	}

	public static int getModCausaIngreso() {
		return MOD_CAUSA_INGRESO;
	}

	public static int getModReembolso() {
		return MOD_REEMBOLSO;
	}

	public static int getModMedicinasConsulta() {
		return MOD_MEDICINAS_CONSULTA;
	}

	public static int getModCargaFactura() {
		return MOD_CARGA_FACTURA;
	}

	public static int getModBuscarProveedor() {
		return MOD_BUSCAR_PROVEEDOR;
	}

	/**
	 * @return the descuentoDeducible
	 */
	public boolean isDescuentoDeducible() {
		return descuentoDeducible;
	}

	/**
	 * @param descuentoDeducible
	 *            the descuentoDeducible to set
	 */
	public void setDescuentoDeducible(boolean descuentoDeducible) {
		this.descuentoDeducible = descuentoDeducible;
	}

	/**
	 * @return the fechaCreado
	 */
	public boolean isFechaCreado() {
		return fechaCreado;
	}

	/**
	 * @param fechaCreado
	 *            the fechaCreado to set
	 */
	public void setFechaCreado(boolean fechaCreado) {
		this.fechaCreado = fechaCreado;
	}

	/**
	 * @return the fechaLiquidado
	 */
	public boolean isFechaLiquidado() {
		return fechaLiquidado;
	}

	public boolean isThonoMedicos() {
		return thonoMedicos;
	}

	public void setDescripcion(boolean descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDescripcion() {
		return descripcion;
	}

	public void setThonoMedicos(boolean thonoMedicos) {
		this.thonoMedicos = thonoMedicos;
	}

	/**
	 * @param fechaLiquidado
	 *            the fechaLiquidado to set
	 */
	public void setFechaLiquidado(boolean fechaLiquidado) {
		this.fechaLiquidado = fechaLiquidado;
	}

	/**
	 * @return the fechaFactura
	 */
	public boolean isFechaFactura() {
		return fechaFactura;
	}

	/**
	 * @param fechaFactura
	 *            the fechaFactura to set
	 */
	public void setFechaFactura(boolean fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	/**
	 * @return the fechaAprobado
	 */
	public boolean isFechaAprobado() {
		return fechaAprobado;
	}

	/**
	 * @param fechaAprobado
	 *            the fechaAprobado to set
	 */
	public void setFechaAprobado(boolean fechaAprobado) {
		this.fechaAprobado = fechaAprobado;
	}

	/**
	 * @return the fechaRecepcionFactura
	 */
	public boolean isFechaRecepcionFactura() {
		return fechaRecepcionFactura;
	}

	/**
	 * @param fechaRecepcionFactura
	 *            the fechaRecepcionFactura to set
	 */
	public void setFechaRecepcionFactura(boolean fechaRecepcionFactura) {
		this.fechaRecepcionFactura = fechaRecepcionFactura;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public boolean isUltimaModificacion() {
		return UltimaModificacion;
	}

	/**
	 * @param ultimaModificacion
	 *            the ultimaModificacion to set
	 */
	public void setUltimaModificacion(boolean ultimaModificacion) {
		UltimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the fechaRemesa
	 */
	public boolean isFechaRemesa() {
		return fechaRemesa;
	}

	/**
	 * @param fechaRemesa
	 *            the fechaRemesa to set
	 */
	public void setFechaRemesa(boolean fechaRemesa) {
		this.fechaRemesa = fechaRemesa;
	}

	/**
	 * @return the fechaCheque
	 */
	public boolean isFechaCheque() {
		return fechaCheque;
	}

	/**
	 * @param fechaCheque
	 *            the fechaCheque to set
	 */
	public void setFechaCheque(boolean fechaCheque) {
		this.fechaCheque = fechaCheque;
	}

	/**
	 * @return the numeroCheque
	 */
	public boolean isNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * @param numeroCheque
	 *            the numeroCheque to set
	 */
	public void setNumeroCheque(boolean numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * @return the tipoDocumento
	 */
	public boolean isTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(boolean tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public boolean isObservacionMedicinas() {
		return observacionMedicinas;
	}

	public void setObservacionMedicinas(boolean observacionMedicinas) {
		this.observacionMedicinas = observacionMedicinas;
	}

	public boolean isMontoGastosClinicos() {
		return montoGastosClinicos;
	}

	public void setMontoGastosClinicos(boolean montoGastosClinicos) {
		this.montoGastosClinicos = montoGastosClinicos;
	}

	public void setMontoExamenesPreoperatorios(boolean montoExamenesPreoperatorios) {
		this.montoExamenesPreoperatorios = montoExamenesPreoperatorios;
	}

	public void setMontoMaterialMedicoConIva(boolean montoMaterialMedicoConIva) {
		this.montoMaterialMedicoConIva = montoMaterialMedicoConIva;
	}

	public boolean isMontoMaterialMedicoConIva() {
		return montoMaterialMedicoConIva;
	}

	public void setMontoMaterialMedicoSinIva(boolean montoMaterialMedicoSinIva) {
		this.montoMaterialMedicoSinIva = montoMaterialMedicoSinIva;
	}

	public void setMontoCalculado(boolean montoCalculado) {
		this.montoCalculado = montoCalculado;
	}

	public boolean isMontoCalculado() {
		return montoCalculado;
	}

	public boolean isMontoMaterialMedicoSinIva() {
		return montoMaterialMedicoSinIva;
	}

	public boolean isMontoExamenesPreoperatorios() {
		return montoExamenesPreoperatorios;
	}

	public boolean isMontoHonorariosMedicos() {
		return montoHonorariosMedicos;
	}

	public void setMontoHonorariosMedicos(boolean montoHonorariosMedicos) {
		this.montoHonorariosMedicos = montoHonorariosMedicos;
	}

	public boolean isTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(boolean tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public boolean isDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(boolean datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	public boolean isDatosSiniestro() {
		return datosSiniestro;
	}

	public void setDatosSiniestro(boolean datosSiniestro) {
		this.datosSiniestro = datosSiniestro;
	}
	
	
	/*seteo el combo
	  */
	

	public boolean isProveedor() {
		return proveedor;
	}

	public boolean isProveedor_combo() {
		return proveedor_combo;
	}

	public void setProveedor_combo(boolean proveedor_combo) {
		this.proveedor_combo = proveedor_combo;
	}

	public void setProveedor(boolean proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the numeroSiniestro
	 */
	public boolean isNumeroSiniestro() {
		return numeroSiniestro;
	}

	/**
	 * @param numeroSiniestro
	 *            the numeroSiniestro to set
	 */
	public void setNumeroSiniestro(boolean numeroSiniestro) {
		counter++;
		this.numeroSiniestro = numeroSiniestro;
	}

	/**
	 * @return the subCodigo
	 */
	public boolean isSubCodigo() {
		return subCodigo;
	}

	/**
	 * @param subCodigo
	 *            the subCodigo to set
	 */
	public void setSubCodigo(boolean subCodigo) {
		this.subCodigo = subCodigo;
	}

	/**
	 * @return the codigo
	 */
	public boolean isCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(boolean codigo) {
		counter++;
		this.codigo = codigo;
	}

	/**
	 * @return the idOrdenPago
	 */
	public boolean isIdOrdenPago() {
		return idOrdenPago;
	}

	/**
	 * @param idOrdenPago
	 *            the idOrdenPago to set
	 */
	public void setIdOrdenPago(boolean idOrdenPago) {
		this.idOrdenPago = idOrdenPago;
	}

	public boolean ismontoLiquidado() {
		return montoLiquidado;
	}

	/**
	 * @param montoLiquidado
	 *            the montoLiquidado to set
	 */
	public void setMontoLiquidado(boolean montoLiquidado) {
		this.montoLiquidado = montoLiquidado;
	}

	/**
	 * @return the montoApagar
	 */
	public boolean isMontoApagar() {
		return montoApagar;
	}

	/**
	 * @param montoApagar
	 *            the montoApagar to set
	 */
	public void setMontoApagar(boolean montoApagar) {
		this.montoApagar = montoApagar;
	}

	/**
	 * @return the idProveedor
	 */
	public boolean isIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor
	 *            the idProveedor to set
	 */
	public void setIdProveedor(boolean idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * @return the nombreApellido
	 */
	public boolean isNombreApellido() {
		return nombreApellido;
	}

	/**
	 * @param nombreApellido
	 *            the nombreApellido to set
	 */
	public void setNombreApellido(boolean nombreApellido) {
		counter++;
		this.nombreApellido = nombreApellido;
	}

	/**
	 * @return the idTipoProveedor
	 */
	public boolean isIdTipoProveedor() {
		return idTipoProveedor;
	}

	/**
	 * @param idTipoProveedor
	 *            the idTipoProveedor to set
	 */
	public void setIdTipoProveedor(boolean idTipoProveedor) {
		this.idTipoProveedor = idTipoProveedor;
	}

	public boolean isMonto() {
		return monto;
	}

	public void setMonto(boolean monto) {
		this.monto = monto;
	}

	/**
	 * @return the codigoPreOrden
	 */
	public boolean isCodigoPreOrden() {
		return codigoPreOrden;
	}

	public void setCodigoPreOrden(boolean codigoPreOrden) {
		this.codigoPreOrden = codigoPreOrden;
	}

	public boolean isNumeroConceptoRetencion() {
		return numeroConceptoRetencion;
	}

	public void setNumeroConceptoRetencion(boolean numeroConceptoRetencion) {
		this.numeroConceptoRetencion = numeroConceptoRetencion;
	}

	public boolean isDiasTerapiaIntensiva() {
		return diasTerapiaIntensiva;
	}

	public void setDiasTerapiaIntensiva(boolean diasTerapiaIntensiva) {
		this.diasTerapiaIntensiva = diasTerapiaIntensiva;
	}

	public boolean isCobertura() {
		return cobertura;
	}

	public void setCobertura(boolean cobertura) {
		this.cobertura = cobertura;
	}

	/**
	 * @return the archivos
	 */
	public boolean isArchivos() {
		return archivos;
	}

	/**
	 * @param archivos
	 *            the archivos to set
	 */
	public void setArchivos(boolean archivos) {
		this.archivos = archivos;
	}

	/**
	 * @return the modPagoNuevoAsegurado
	 */
	public static int getModPagoNuevoAsegurado() {
		return MOD_PAGO_NUEVO_ASEGURADO;
	}

	/**
	 * @return the modMedicinasConsultaLiquidacion
	 */
	public static int getModMedicinasConsultaLiquidacion() {
		return MOD_MEDICINAS_CONSULTA_LIQUIDACION;
	}

	/**
	 * @return the modMedicinasLiquidacion
	 */
	public static int getModMedicinasLiquidacion() {
		return MOD_MEDICINAS_LIQUIDACION;
	}

	/**
	 * @return the modMedicinasConsultaEdita
	 */
	public static int getModMedicinasConsultaEdita() {
		return MOD_MEDICINAS_CONSULTA_EDITA;
	}

	/**
	 * @return the modMedicinasEdita
	 */
	public static int getModMedicinasEdita() {
		return MOD_MEDICINAS_EDITA;
	}

	/**
	 * @return the modReembolsoLiquidacion
	 */
	public static int getModReembolsoLiquidacion() {
		return MOD_REEMBOLSO_LIQUIDACION;
	}

	/**
	 * @return the modReembolsoLiquidacionResult
	 */
	public static int getModReembolsoLiquidacionResult() {
		return MOD_REEMBOLSO_LIQUIDACION_RESULT;
	}

	/**
	 * @return the modApsConsulta
	 */
	public static int getModApsConsulta() {
		return MOD_APS_CONSULTA;
	}

	/**
	 * @return the modCargaPreorden
	 */
	public static int getModCargaPreorden() {
		return MOD_CARGA_PREORDEN;
	}

	/**
	 * @return the modBuscarSiniestro
	 */
	public static int getModBuscarSiniestro() {
		return MOD_BUSCAR_SINIESTRO;
	}

	/**
	 * @return the modApsEdicion
	 */
	public static int getModApsEdicion() {
		return MOD_APS_EDICION;
	}

	/**
	 * @return the modEditaPago
	 */
	public static int getModEditaPago() {
		return MOD_EDITA_PAGO;
	}

	/**
	 * @return the modEditaPagoPreorden
	 */
	public static int getModEditaPagoPreorden() {
		return MOD_EDITA_PAGO_PREORDEN;
	}

	/**
	 * @return the codigoetiqueta
	 */
	public boolean isCodigoetiqueta() {
		return codigoetiqueta;
	}

	/**
	 * @param codigoetiqueta
	 *            the codigoetiqueta to set
	 */
	public void setCodigoetiqueta(boolean codigoetiqueta) {
		this.codigoetiqueta = codigoetiqueta;
	}

	/**
	 * @return the adjuntos
	 */
	public boolean isAdjuntos() {
		return adjuntos;
	}

	/**
	 * @param adjuntos
	 *            the adjuntos to set
	 */
	public void setAdjuntos(boolean adjuntos) {
		this.adjuntos = adjuntos;
	}

	public boolean isDetalleCobertura() {
		return detalleCobertura;
	}

	public void setDetalleCobertura(boolean detalleCobertura) {
		this.detalleCobertura = detalleCobertura;
	}

	public boolean isMontoPresupuestado() {
		return montoPresupuestado;
	}

	public void setMontoPresupuestado(boolean montoPresupuestado) {
		this.montoPresupuestado = montoPresupuestado;
	}

	public boolean isMontoNegociado() {
		return montoNegociado;
	}

	public void setMontoNegociado(boolean montoNegociado) {
		this.montoNegociado = montoNegociado;
	}

	public boolean isFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(boolean fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public boolean isFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(boolean fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public boolean isMontoFuneraria() {
		return montoFuneraria;
	}

	public void setMontoFuneraria(boolean montoFuneraria) {
		this.montoFuneraria = montoFuneraria;
	}

	public boolean isMontoFunerariaNoAmparado() {
		return montoFunerariaNoAmparado;
	}

	public void setMontoFunerariaNoAmparado(boolean montoFunerariaNoAmparado) {
		this.montoFunerariaNoAmparado = montoFunerariaNoAmparado;
	}

	public boolean isMontoExamenesEspeciales() {
		return montoExamenesEspeciales;
	}

	public void setMontoExamenesEspeciales(boolean montoExamenesEspeciales) {
		this.montoExamenesEspeciales = montoExamenesEspeciales;
	}

	public boolean isMontoExamenesEspecialesNoAmparado() {
		return montoExamenesEspecialesNoAmparado;
	}

	public void setMontoExamenesEspecialesNoAmparado(boolean montoExamenesEspecialesNoAmparado) {
		this.montoExamenesEspecialesNoAmparado = montoExamenesEspecialesNoAmparado;
	}

	public boolean isMontoAmbulancia() {
		return montoAmbulancia;
	}

	public void setMontoAmbulancia(boolean montoAmbulancia) {
		this.montoAmbulancia = montoAmbulancia;
	}

	public boolean isMontoAmbulanciaNoAmparado() {
		return montoAmbulanciaNoAmparado;
	}

	public void setMontoAmbulanciaNoAmparado(boolean montoAmbulanciaNoAmparado) {
		this.montoAmbulanciaNoAmparado = montoAmbulanciaNoAmparado;
	}

	public boolean isVidaNoAmparado() {
		return vidaNoAmparado;
	}

	public void setVidaNoAmparado(boolean vidaNoAmparado) {
		this.vidaNoAmparado = vidaNoAmparado;
	}

	public boolean isCitaPostOperatorio() {
		return citaPostOperatorio;
	}

	public void setCitaPostOperatorio(boolean citaPostOperatorio) {
		this.citaPostOperatorio = citaPostOperatorio;
	}

	public boolean isVida() {
		return vida;
	}

	public void setVida(boolean vida) {
		this.vida = vida;
	}

	public boolean isMontoCalculadoNoAmparado() {
		return montoCalculadoNoAmparado;
	}

	public void setMontoCalculadoNoAmparado(boolean montoCalculadoNoAmparado) {
		this.montoCalculadoNoAmparado = montoCalculadoNoAmparado;
	}

	public boolean isMontoHonorariosMedicosNoAmparado() {
		return montoHonorariosMedicosNoAmparado;
	}

	public void setMontoHonorariosMedicosNoAmparado(boolean montoHonorariosMedicosNoAmparado) {
		this.montoHonorariosMedicosNoAmparado = montoHonorariosMedicosNoAmparado;
	}

	public boolean isMontoGastosClinicosNoAmparado() {
		return montoGastosClinicosNoAmparado;
	}

	public void setMontoGastosClinicosNoAmparado(boolean montoGastosClinicosNoAmparado) {
		this.montoGastosClinicosNoAmparado = montoGastosClinicosNoAmparado;
	}

	public boolean isTipoTratamiento() {
		return tipoTratamiento;
	}

	public void setTipoTratamiento(boolean tipoTratamiento) {
		this.tipoTratamiento = tipoTratamiento;
	}

	public void setCriterioBusqueda(boolean criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public boolean isCriterioBusqueda() {
		return criterioBusqueda;
	}

	/**
	 * @return the numeroControl
	 */
	public boolean isNumeroControl() {
		return numeroControl;
	}

	/**
	 * @return the rif
	 */
	public boolean isRif() {
		return rif;
	}

	/**
	 * @param rif
	 *            the rif to set
	 */
	public void setRif(boolean rif) {
		counter++;
		this.rif = rif;
	}

	/**
	 * @param numeroControl
	 *            the numeroControl to set
	 */
	public void setNumeroControl(boolean numeroControl) {
		this.numeroControl = numeroControl;
	}

	public boolean isMontoHonorariosMedicosAmparado() {
		return montoHonorariosMedicosAmparado;
	}

	public void setMontoHonorariosMedicosAmparado(boolean montoHonorariosMedicosAmparado) {
		this.montoHonorariosMedicosAmparado = montoHonorariosMedicosAmparado;
	}

	public boolean isMontoGastosClinicosAmparado() {
		return montoGastosClinicosAmparado;
	}

	public void setMontoGastosClinicosAmparado(boolean montoGastosClinicosAmparado) {
		this.montoGastosClinicosAmparado = montoGastosClinicosAmparado;
	}

	public boolean isMontoCalculadoAmparado() {
		return montoCalculadoAmparado;
	}

	public void setMontoCalculadoAmparado(boolean montoCalculadoAmparado) {
		this.montoCalculadoAmparado = montoCalculadoAmparado;
	}

	public void setDetallePresupuesto(boolean detallePresupuesto) {
		this.detallePresupuesto = detallePresupuesto;
	}

	public boolean isDetallePresupuesto() {
		return detallePresupuesto;
	}

	public void setCitaPreOperatorio(boolean citaPreOperatorio) {
		this.citaPreOperatorio = citaPreOperatorio;
	}

	public boolean isCitaPreOperatorio() {
		return citaPreOperatorio;
	}

	public void setDetallePresupuestoEdit(boolean detallePresupuestoEdit) {
		this.detallePresupuestoEdit = detallePresupuestoEdit;
	}

	public boolean isDetallePresupuestoEdit() {
		return detallePresupuestoEdit;
	}

	public void setAnioSiniestro(boolean anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}

	public boolean isAnioSiniestro() {
		return anioSiniestro;
	}

	public boolean isDetallePresupuestoEmergencia() {
		return detallePresupuestoEmergencia;
	}

	public void setDetallePresupuestoEmergencia(boolean detallePresupuestoEmergencia) {
		this.detallePresupuestoEmergencia = detallePresupuestoEmergencia;
	}

	public boolean isDetallePresupuestoEditEmergencia() {
		return detallePresupuestoEditEmergencia;
	}

	public void setDetallePresupuestoEditEmergencia(boolean detallePresupuestoEditEmergencia) {
		this.detallePresupuestoEditEmergencia = detallePresupuestoEditEmergencia;
	}

	public void setTituloItems(boolean tituloItems) {
		this.tituloItems = tituloItems;
	}

	public boolean isTituloItems() {
		return tituloItems;
	}

	public boolean isAnio() {
		return anio;
	}

	public void setAnio(boolean anio) {
		this.anio = anio;
	}

	public void setDetalleSiniestroEgreso(boolean detalleSiniestroEgreso) {
		this.detalleSiniestroEgreso = detalleSiniestroEgreso;
	}

	public boolean isDetalleSiniestroEgreso() {
		return detalleSiniestroEgreso;
	}

	public void setFechaEgresoRequerida(boolean fechaEgresoRequerida) {
		this.fechaEgresoRequerida = fechaEgresoRequerida;
	}

	public boolean isFechaEgresoRequerida() {
		return fechaEgresoRequerida;
	}

	public void setDetalleSiniestroPadre(boolean detalleSiniestroPadre) {
		this.detalleSiniestroPadre = detalleSiniestroPadre;
	}

	public boolean isDetalleSiniestroPadre() {
		return detalleSiniestroPadre;
	}

	public void setObservacionNoRequerida(boolean observacionNoRequerida) {
		this.observacionNoRequerida = observacionNoRequerida;
	}

	public boolean isObservacionNoRequerida() {
		return observacionNoRequerida;
	}

	public boolean isListTipoTramite() {
		return listTipoTramite;
	}

	public void setListTipoTramite(boolean listTipoTramite) {
		counter++;
		this.listTipoTramite = listTipoTramite;
	}

	public boolean isMontosPresupuesto() {
		return montosPresupuesto;
	}

	public void setMontosPresupuesto(boolean montosPresupuesto) {
		this.montosPresupuesto = montosPresupuesto;
	}

	/**
	 * @return the notaTecnica
	 */
	public boolean isNotaTecnica() {
		return notaTecnica;
	}

	/**
	 * @param notaTecnica
	 *            the notaTecnica to set
	 */
	public void setNotaTecnica(boolean notaTecnica) {
		this.notaTecnica = notaTecnica;
	}

	/**
	 * @return the printNotaTecnica
	 */
	public boolean isPrintNotaTecnica() {
		return printNotaTecnica;
	}

	/**
	 * @param printNotaTecnica
	 *            the printNotaTecnica to set
	 */
	public void setPrintNotaTecnica(boolean printNotaTecnica) {
		this.printNotaTecnica = printNotaTecnica;
	}

	/**
	 * @return the notaMedica
	 */
	public boolean isNotaMedica() {
		return notaMedica;
	}

	/**
	 * @param notaMedica
	 *            the notaMedica to set
	 */
	public void setNotaMedica(boolean notaMedica) {
		this.notaMedica = notaMedica;
	}

	/**
	 * @return the verNotaMedica
	 */
	public boolean isVerNotaMedica() {
		return verNotaMedica;
	}

	/**
	 * @param verNotaMedica
	 *            the verNotaMedica to set
	 */
	public void setVerNotaMedica(boolean verNotaMedica) {
		this.verNotaMedica = verNotaMedica;
	}

	public boolean isRechazado() {
		return rechazado;
	}

	public void setRechazado(boolean rechazado) {
		this.rechazado = rechazado;
	}

	public boolean isCoberturaRangoFecha() {
		return coberturaRangoFecha;
	}

	public void setCoberturaRangoFecha(boolean coberturaRangoFecha) {
		this.coberturaRangoFecha = coberturaRangoFecha;
	}

	public boolean isAnioBusqueda() {
		return anioBusqueda;
	}

	public void setAnioBusqueda(boolean anioBusqueda) {
		//counter++;
		this.anioBusqueda = anioBusqueda;
	}

	public boolean isPoliza() {
		return poliza;
	}

	public void setPoliza(boolean poliza) {
		this.poliza = poliza;
	}

	/**
	 * @return the fechaProveedor
	 */
	public boolean isFechaProveedor() {
		return fechaProveedor;
	}

	/**
	 * @param fechaProveedor
	 *            the fechaProveedor to set
	 */
	public void setFechaProveedor(boolean fechaProveedor) {
		//counter++;
		this.fechaProveedor = fechaProveedor;
	}

	/**
	 * @return the alMenosDos
	 */
	public boolean isAlMenosDos() {
		return alMenosDos;
	}

	/**
	 * @param alMenosDos
	 *            the alMenosDos to set
	 */
	public void setAlMenosDos(boolean alMenosDos) {
		this.alMenosDos = alMenosDos;
	}

	public boolean isTipoProveedorP() {
		return tipoProveedorP;
	}

	public void setTipoProveedorP(boolean tipoProveedorP) {
		this.tipoProveedorP = tipoProveedorP;
	}

	public boolean isPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(boolean porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public boolean isListIva() {
		return listIva;
	}

	public void setListIva(boolean listIva) {
		this.listIva = listIva;
	}

}

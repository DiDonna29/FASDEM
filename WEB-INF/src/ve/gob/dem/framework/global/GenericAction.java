/**
 * 
 */
package ve.gob.dem.framework.global;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.AnioBusqueda;
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Medicamento;
import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.fasdem.bean.PatologiaOrganoTratamiento;
import ve.gob.dem.fasdem.bean.Patologias;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Recipe;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerConsulta;
import ve.gob.dem.fasdem.per.PerEspecialidad;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerImpuesto;
import ve.gob.dem.fasdem.per.PerOrdenPago;
import ve.gob.dem.fasdem.per.PerOrgano;
import ve.gob.dem.fasdem.per.PerPatologiaOrganoTratamiento;
import ve.gob.dem.fasdem.per.PerPatologias;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerProveedor;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerTipoEmpleado;
import ve.gob.dem.fasdem.per.PerTipoGasto;
import ve.gob.dem.fasdem.per.PerTipoCobertura;
import ve.gob.dem.fasdem.per.PerTipoSiniestro;
import ve.gob.dem.fasdem.per.PerTipoTramite;
import ve.gob.dem.fasdem.per.PerTipoTratamiento;
import ve.gob.dem.fasdem.per.PerCuenta;
import ve.gob.dem.fasdem.per.PerTipoEnfermedad;
import ve.gob.dem.fasdem.per.PerTipoProveedor;
import ve.gob.dem.fasdem.per.PerTratamiento;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalLockedException;
import ve.gob.dem.framework.exception.PersonalNotEditableException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.exception.SiniestroNotPermittedException;
import ve.gob.dem.framework.recursos.Parametros;
import ve.gob.dem.framework.recursos.SingletonNodos;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Nodo;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpAccion;

@SuppressWarnings({ "rawtypes", "unused" })
public class GenericAction extends Action {

	protected static HashMap expedientesLocked = new HashMap();
	protected static HashMap actuacionesLocked = new HashMap();
	protected static Logger log;
	//
	// ALERTS
	public static final String ALERT_VALIDACION = "msjValidaciones";
	public static final String ALERT_AVISOS = "msjAvisos";
	// KEYS
	public static final String KEY_USUARIO = "usuario";
	public static final String KEY_ANIO_BUSQUEDA = "anioBusqueda";
	public static final String KEY_LOGIN = "login";
	public static final String KEY_INSERTA = "insertar";
	public static final String KEY_REPORTE = "report";
	public static final String KEY_MENSAJE = "mensaje";
	public static final String KEY_ENTORNO = "entorno";
	public static final String KEY_URLDINA = "url_dinamica";
	public static final String KEY_PROYECTO = "proyecto.id";
	public static final String KEY_APLICACION = "aplicacion.id";
	public static final String KEY_TITULAR = "titular";
	public static final String KEY_BENEFIC = "beneficiario";
	public static final String KEY_PERSONA = "persona";
	public static final String KEY_SINIESTROS = "siniestros";
	public static final String KEY_MEDICINAS = "siniestro";
	public static final String KEY_COBERTURA = "cobertura";
	public static final String KEY_TIPO_COBERTURA = "tipoCobertura";
	public static final String KEY_TIPO_TRAMITE = "tipoTramite";
	public static final String KEY_LIST_TIPO_TRAMITE = "listTipoTramite";
	public static final String KEY_URL_DYNAMIC = "url_dynamic";
	public static final int DETALLE_DEPENDENCIA = 6;
	public static final int DETALLE_TRIBUNAL = 11;
	// Errores
	public static final String KEY_NOTFOUND = "general.bean.notfound";
	public static final String KEY_NOSESSION = "session.expired";
	public static final String KEY_NOACCESS = "access.denied";
	public static final String KEY_LOGIN_EXCEPTION = "login.exception";
	public static final String KEY_DUPLICATE_EXCEPTION = "value.duplicate";
	public static final String KEY_INSERT_SUCCESS = "insert.success";
	public static final String KEY_UPDATE_SUCCESS = "update.success";
	// fowards
	public static final String FWD_INPUT = "input";
	public static final String FWD_RETURN = "return";
	public static final String FWD_SUCCESS = "success";
	public static final String FWD_NOTFOUND = "notfound";
	public static final String FWD_PRINT = "global.print";
	public static final String FWD_PRINT_EXCEL = "global.print.excel";
	public static final String FWD_INDEX = "index";
	public static final String FWD_EXCEPTION = "exception.page";
	// LIST
	public static final String LIST_TIPO_PROVEEDOR = "listTipoProveedor";
	public static final String LIST_TIPO_PROVEEDOR_COMBO = "listTipoProveedorCombo";
	public static final String LIST_TIPO_SINIESTRO = "listTipoSiniestro";
	public static final String LIST_ORGANO = "listOrgano";
	public static final String LIST_ESPECIALIDAD = "listEspecialidad";
	public static final String LIST_PATOLOGIAS = "listPatologias";
	public static final String LIST_TIPO_ENFERMEDAD = "listTipoEnfermedad";
	public static final String LIST_ESTATUS = "listEstatus";
	public static final String LIST_TRATAMIENTO = "listTratamiento";
	public static final String LIST_TIPO_GASTO = "listTipoGasto";
	public static final String LIST_TIPO_COBERTURA = "listTipoCobertura";
	public static final String LIST_PORCENTAJE_IVA = "listIva";
	public static final String LIST_PROVEEDOR = "listProveedor";
	public static final String LIST_ANIO_BUSQUEDA = "listAnioBusqueda";
	// public static final String LIST_TIPO_PROVEEDORRMBLS =
	// "listTipoProveedorRmbls";
	public static final String LIST_TIPO_PROVEEDORMBLS = "listTipoProveedoRmbls";
	public static final String LIST_EMPLEADO = "listEmpleado";
	public static final String LIST_POLIZA = "listPoliza";
	public static final String LIST_COBERTURA = "listCobertura";
	public static final String LIST_TIPO_TRATAMIENTO = "listTratamiento";
	public static final String LIST_TIPO_TRAMITE = "listTramite";
	public static final String LIST_TIPO_LIST_TRAMITE = "listTipoTramite";
	// URL
	public static final String URL_NOACCESS = "/noaccess.do";
	public static final String URL_NOSESSION = "/nosession.do";
	public static final String URL_NOVIGENTE = "/novigente.do";
	public static final String URL_INDEX = "/login.do";
	// ///////////////////////// T R A Z A S ///////////////////////////
	// //
	// ///////////////////////// T R A Z A S ///////////////////////////
	// #APS
	public static final String TR_APS_CARGAR = "694";
	public static final String TR_APS_MODIFICAR = "695";
	// #CARTA AVAL
	public static final String TR_CARTAAVAL_DECLARAR = "694";
	public static final String TR_CARTAAVAL_MODIFICAR = "695";
	public static final String TR_CARTAAVAL_ACTIVAR = "698";
	public static final String TR_CARTAAVAL_EGRESAR = "699";
	public static final String TR_CARTAAVAL_ANULAR = "700";
	public static final String TR_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS = "728";
	public static final String TR_CARTAAVAL_FINALIZAR_CAMBIO_ESTATUS = "729";
	// #CARTA AVAL DESCRIPCIONES
	public static final String TRDESC_CARTAAVAL_DECLARAR = "Declarar una carta aval";
	public static final String TRDESC_CARTAAVAL_MODIFICAR = "Modificar una carta aval";
	public static final String TRDESC_CARTAAVAL_ACTIVAR = "Activar una carta aval";
	public static final String TRDESC_CARTAAVAL_EGRESAR = "Egresar una carta aval";
	public static final String TRDESC_CARTAAVAL_ANULAR = "Anular una carta aval";
	public static final String TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS = "Justificar Estatus";
	public static final String TRDESC_CARTAAVAL_FINALIZAR_CAMBIO_ESTATUS = "Finalizar Estatus";
	// #LIQUIDACION
	public static final String TR_LIQUIDACION_CARGARFACTURA = "701";
	public static final String TR_LIQUIDACION_CARGAR_DETALLEFACTURA = "702";
	public static final String TR_LIQUIDACION_ELIMINAR_DETALLEFACTURA = "703";
	public static final String TR_LIQUIDACION_ELIMINAR_FACTURA = "704";
	public static final String TR_LIQUIDACION_LIQUIDAR_SINIESTRO = "705";
	public static final String TR_LIQUIDACION_ASIGNAR_ESTATUS = "768";
	// #CONSULTA
	public static final String TR_CONSULTA_SINIESTRO = "706";
	// #NOTA MEDICA
	public static final String TR_NOTAMEDICA_CARGAR = "707";
	public static final String TR_NOTAMEDICA_IMPRIMIR = "708";
	// #NOTA TÉCNICA
	public static final String TR_NOTATECNICA_CARGAR = "709";
	public static final String TR_NOTATECNICA_PRINTUSUARIOACTIVO = "710";
	public static final String TR_NOTATECNICA_PRINTSINIESTRO = "711";
	// #ADJUNTOS
	public static final String TR_ADJUNTO_CARGAR = "712";
	public static final String TR_ADJUNTO_ELIMINAR = "713";
	// #MODULO DE PAGOS
	public static final String TR_PAGO_CREAR_PREORDEN = "714";
	public static final String TR_PAGO_CONSULTAR_PREORDEN = "715";
	public static final String TR_PAGO_CONSULTA_ESTATUS_SINIESTRO = "716";
	public static final String TR_PAGO_AUTORIZAR_PREORDEN = "717";
	public static final String TR_PAGO_CAMBIAR_PREORDEN_ONT = "718";
	public static final String TR_PAGO_CAMBIAR_PREORDEN_PAGADA = "719";
	public static final String TR_PAGO_AGREGAR_FACTURA_PREORDEN = "720";
	public static final String TR_PAGO_ELIMINAR_FACTURA_PREORDEN = "721";
	public static final String TR_PAGO_ANULAR_PREORDEN = "722";
	public static final String TR_PAGO_CREAR_HOJA_RUTA = "795";
	public static final String TR_PAGO_CAMBIAR_ESTATUS_HOJA_RUTA = "798";
	public static final String TR_PAGO_IMPRIMIR_HOJA_RUTA = "799";
	public static final String TR_PAGO_AGREGAR_PAGO_HOJA = "800";
	public static final String TR_PAGO_ELIMINAR_PAGO_HOJA = "801";
	public static final String TR_PAGO_RECIBIR_EXP_DEVUELTO = "802";
	public static final String TR_PAGO_LIBERAR_EXPEDIENTE_ONT = "804";
	// #MODULO DE AUTORIZACIÓN DE EXTENSIÓN
	public static final String TR_EXTENSION_CREAR_EXTENSION_COBERTURA = "723";
	public static final String TR_EXTENSION_ELIMINAR_REGISTRO_DE_EXTENSION = "724";
	// #PORTAL FASDEM
	public static final String TR_PAGO_CONSULTA_BENEFICIARIO = "725";
	public static final String TR_PAGO_IMPRESION_PLANILLA_DECLARACION = "726";
	public static final String TR_PAGO_IMPRESION_PLANILLA_DECLARACION_CARTAAVAL = "727"; // (ACTIVACIÓN
	// #ADMINISTRACION																						// CARTA
	public static final String TR_ADMINISTRADOR_CAMBIO_ESTATUS = "907";																						// AVAL)
	// REMMBOLSOS
	public static final String TR_REEMBOLSO_INSERTAR = "694";
	public static final String TR_REEMBOLSO_IMPRIMIR_RESERVA = "737";
	// public static final String TR_REEMBOLSO_ACTUALIZAR_SINIESTRO = "";
	public static final String TR_REEMBOLSO_GUARDAR_FACTURA = "701";
	public static final String TR_REEMBOLSO_MODIFICAR_FACTURA = "733";
	public static final String TR_REEMBOLSO_ELIMINAR_FACTURA = "704";
	public static final String TR_REEMBOLSO_LIQUIDAR_SINIESTRO = "705";
	// #MEDICINA
	public static final String TR_MEDICINAS_DECLARAR = "694";
	public static final String TR_MEDICINAS_MODIFICAR = "695";
	public static final String TR_MEDICINAS_ANULAR = "728";
	public static final String TR_MEDICINAS_RECHAZAR = "728";
	public static final String TR_MEDICINAS_LIQUIDAR = "705";
	public static final String TR_MEDICINAS_IMPRIMIR_ORDEN = "736";
	// EMERGENCIA
	public static final String TR_EMERGENCIA_DECLARAR = "694";
	public static final String TR_EMERGENCIA_MODIFICAR = "695";
	public static final String TR_EMERGENCIA_LIQUIDAR = "705";
	public static final String TR_EMERGENCIA_INSERTAR_CAMBIO_ESTATUS = "728";
	public static final String TR_EMERGENCIA_FINALIZAR_CAMBIO_ESTATUS = "729";
	// ## ADMINISTRADORES TODOS!!!!!
	// POLIZA
	public static final String TR_ADMPOLIZA_CREAR = "738";
	public static final String TR_ADMPOLIZA_MODIFICAR = "739";
	public static final String TR_ADMPOLIZA_CAMBIAR_ESTATUS = "740";
	// COBERETURA
	public static final String TR_ADMCOBERTURA_CREAR = "741";
	public static final String TR_ADMCOBERTURA_MODIFICAR = "742";
	public static final String TR_ADMCOBERTURA_CAMBIO_ESTATUS = "743";
	public static final String TR_ADMCOBERTURA_DESASOCIAR_DE_TIPOTRAMITE = "744";
	// ESPECIALIDAD
	public static final String TR_ADMESPECIALIDAD_CREAR = "745";
	public static final String TR_ADMESPECIALIDAD_MODIFICAR = "746";
	// ORGANO
	public static final String TR_ADMORGANO_CREAR = "747";
	public static final String TR_ADMORGANO_MODIFICAR = "748";
	// PATOLOGIA
	public static final String TR_ADMPATOLOGIA_CREAR = "749";
	public static final String TR_ADMPATOLOGIA_MODIFICAR = "750";
	// TRATAMIENTO
	public static final String TR_ADMTRATAMIENTO_CREAR = "751";
	public static final String TR_ADMTRATAMIENTO_MODIFICAR = "752";
	// CAUSA DE INGRESO
	public static final String TR_ADMCAUSAINGRESO_CREAR = "753";
	public static final String TR_ADMCAUSAINGRESO__ESTATUS = "754";
	// CAMBIO DE ESTATUS
	public static final String TR_ADMESTATUS_CAMBIAR_ESTATUS_SINIESTRO = "755";
	// CUENTA NOMINA
	public static final String TR_ADMCUENTANOMINA_CREAR_CUENTA_BENEBICIARIOS = "756";
	public static final String TR_ADMCUENTANOMINA_MODIFICAR_CUENTA_BENEBICIARIOS = "757";
	// CUENTA PROVEEDOR
	public static final String TR_ADMCUENTAPROVEDDOR_CREAR_CUENTAPROVEEDOR = "758";
	public static final String TR_ADMCUENTAPROVEDDOR_MODIFICAR_CUENTAPROVEEDOR = "759";
	// PROVEEDORES
	public static final String TR_ADMPROVEEDOR_CREAR = "760";
	public static final String TR_ADMPROVEEDOR_MODIFICAR = "761";
	public static final String TR_ADMPROVEEDOR_CAMBIAR_ESTATUS = "762";
	public static final String TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITE = "763";
	public static final String TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITEPORTAL = "764";
	// GRUPO RECEPCION DE FACTURAS
	// CARGAR
	public static final String TR_ADMRECEPCIONFACT_CARGAR = "701";
	public static final String TR_ADMRECEPCIONFACT_ELIMINAR = "704";
	// IMPRIMIR
	public static final String TR_ADMRECEPCIONFACT_IMPRIMIR_CARGA = "765";
	public static final String TR_IMPRIMIR_CARTA_ASEGURABILIDAD = "809";
	public static final String TR_IMPRIMIR_FINIQUITO_COBERTURA = "808";
	// GRUPO REPORTES
	// CARGA POR USUARIO
	public static final String TR_REPORTE_CARGA_SINIESTROS = "766";
	// ESTADISTICA GENERAL
	public static final String TR_REPORTE_IMPRIMIR_ESTADISTICA = "767";
	// ///////////////////////// F I N T R A Z A S ///////////////////////////
	public static final String TIPO_PERSONA_EMPLE = "T";
	public static final String TIPO_PERSONA_BENEF = "B";
	// CONSTANTES
	public static final int COD_PROVEEDOR_LOCATEL = 762;
	public static final int COD_TIPO_PROVEEDOR_FARMACIA = 13;
	public static final int COD_PROVEEDOR_BENEFICIARIO = 44;
	public static final int COD_PROVEEDOR_TERCERO = 45;
	public static final int COD_TIPO_PROVEEDOR_BENEFICIARIO = 2;
	public static final int COD_ESTATUS_ANALIZAR = 1;
	public static final int COD_ESTATUS_RECAUDOS = 2;
	public static final int COD_ESTATUS_LIQUIDADO = 4;
	public static final int COD_ESTATUS_CARTA_COMPROMISO = 5;
	public static final int COD_ESTATUS_ENADMINISTRACION = 7;
	public static final int COD_ESTATUS_INGRESADO = 8;
	public static final int COD_ESTATUS_EGRESADO = 9;
	public static final int COD_ESTATUS_PAGADO = 21;
	public static final int COD_ESTATUS_PAGADO_RECHAZADO = 22;
	public static final int COD_ESTATUS_PAGO_GRACIA = 23;
	public static final int COD_ESTATUS_PAGADO_COMPLEMENTO = 24;
	public static final int COD_ESTATUS_ANULADO_ERROR = 24;
	public static final int COD_ESTATUS_ANULADO_NOPROCEDENTE = 24;
	public static final int COD_ESTATUS_RECHAZADO = 35;
	public static final int COD_ESTATUS_ANULADO = 34;
  	public static final int COD_ESTATUS_ANULADO_P_ERROR = 31;
	public static final int COD_ESTATUS_ANULADO_NO_PROCEDENTE = 32;
	public static final int COD_ESTATUS_ANULADO_RECHAZADO = 33;	
	public static final int COD_ESTATUS_EVALUACION = 37;
	public static final int COD_ESTATUS_DECLINADO = 38;
	public static final int COD_TIPO_TRAMITE_REEMBOLSO = 3;
	public static final int COD_SINIESTRO_MEDICINAS = 9;
	public static final int COD_TIPO_TRAMITE_CARTAAVAL = 2;
	public static final int COD_TIPO_EMERGENCIA = 1;
	public static final int COD_TIPO_MEDICINAS = 6;
	public static final int COD_TIPO_TRAMITE_APS = 4;
	public static final int COD_TIPO_TRAMITE_MEDICINAS = 6;
	public static final int COD_TIPO_TRATAMIENTO_MEDICO = 1;
	public static final int COD_TIPO_GASTO_HONORARIOS = 1;
	public static final int COD_TIPO_GASTO_GASTOS_CLINICOS = 2;
	public static final int COD_TIPO_GASTO_EXAMENES_PRE_OPE = 3;
	public static final int COD_TIPO_GASTO_MATERIAL_MED_C_IVA = 4;
	public static final int COD_TIPO_GASTO_MATERIAL_MED_S_IVA = 5;
	public static final int COD_TIPO_GASTO_MEDICINAS = 6;
	public static final int COD_TIPO_GASTO_SERVICIOS_FARMACIA = 31;
	public static final int COD_TIPO_GASTO_AMBULANCIA = 13;
	public static final int COD_TIPO_GASTO_FUNERARIOS = 11;
	public static final int COD_TIPO_GASTO_NO_AMPARADO = 35;
	public static final int COD_TIPO_GASTO_IVA = 34;
	public static final int COD_TIPO_GASTO_EXAMENES_ESPECIALES = 8;
	public static final int TIPO_PROVEEDOR_FARMACIA = 12;
	public static final int ID_PROVEEDOR_FARMACIA_DESCONOCIDO = 1275;
	public static final double MONTO_PRESUPUESTADO_AGUDA = 500.00;
	public static final double MONTO_PRESUPUESTADO_CRONICA = 3000.00;
	public static final String COD_SUB_CODIGO_PADRE = "001";
	public static final String TIPO_TRAMITE_EMERGENCIA = "1";
	public static final String TIPO_TRAMITE_CARTAAVAL = "2";
	public static final String TIPO_TRAMITE_REEMBOLSO = "3";
	public static final String TIPO_TRAMITE_APS = "4";
	public static final String TIPO_TRAMITE_MEDICINAS = "6";
	public static final String COD_TIPO_REPORTE_CARTA_AVAL = "5";
	public static final String COD_TIPO_REPORTE_REEMBOLSO = "6";
	public static final String COD_TIPO_REPORTE_ORDEN_MEDICINA = "1";
	public static final String COD_TIPO_REPORTE_ESTA_REEMBOLSO = "7";
	public static final String COD_TIPO_REPORTE_USUARIO = "8";
	public static final String COD_TIPO_ESTA_GENERAL = "9";
	public static final String COD_TIPO_REPORTE_NOTA_COBERTURA = "10";
	public static final String COD_TIPO_REPORTE_NOTA_TECNICA_USUARIO_ACTIVO = "11";
	public static final String COD_TIPO_REPORTE_NOTA_TECNICA_SINIESTRO = "12";
	public static final String COD_TIPO_REPORTE_NOTA_COBERTURA_EGRESO = "13";
	public static final String COD_TIPO_REPORTE_NOTA_TECNICA_SINIESTRORMBLS = "14";
	public static final String COD_TIPO_REPORTE_LIQUIDACION = "15";
	public static final String COD_TIPO_REPORTE_NOTAMEDICA = "16";
	public static final String COD_TIPO_REPORTE_NOTA_COBERTURA_REEMBOLSO = "17";
	public static final String COD_TIPO_REPORTE_RECHAZO_REEMBOLSO = "18";
	public static final String COD_TIPO_REPORTE_RECAUDOS_REEMBOLSO = "19";
	public static final String COD_TIPO_REPORTE_CARTA_ASEGURABILIDAD = "20";
	public static final String COD_TIPO_REPORTE_FINIQUITO_COBERTURA = "21";
	public static final String COD_TIPO_REPORTE_LIQUIDACION_POR_FACTURA = "22";
	public static final String COD_SEXO_MASCULINO = "M";
	public static final String COD_SEXO_FEMENINO = "F";
	public static final int COD_TIPO_ENFERMEDAD_AGUDA = 1;
	public static final int COD_TIPO_ENFERMEDAD_CRONICA_R = 2;
	public static final String COD_TIPO_ENFERMEDAD_CRONICA = "2";
	public static final int COD_IVA = 12;
	public static final double COD_TIMBRE = 0.1;
	public static final int COD_ISLR = 5;
	public static final int COD_TIPO_COBERTURA_AMBULATORIO = 1;
	public static final int COD_TIPO_COBERTURA_ACCIDENTES_PERSONALES = 2;
	public static final int COD_TIPO_COBERTURA_ENFERMEDADES_MENTALES = 3;
	public static final int COD_TIPO_COBERTURA_FUNERARIOS = 4;
	public static final int COD_TIPO_COBERTURA_MATERNIDAD = 5;
	public static final int COD_TIPO_COBERTURA_VIDA = 6;
	public static final int COD_TIPO_COBERTURA_VIH = 7;
	public static final int COD_TIPO_COBERTURA_EMERGENCIA = 8;
	public static final int COD_TIPO_PARENTESCO_TITULAR = 0;
	public static final int COD_TIPO_PARENTESCO_CONYUGE = 2;
	public static final int VIGENCIA_CARTA_AVAL = 30;
	public static final String COD_TIPO_SIN_TRATAMIENTO_MED_AMB = "7";
	public static final String COD_TIPO_PROVEEDOR = "2";
	public static final String COD_ESTATUS_ANALIZARR = "1";

	public Usuario usuarioSession(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario = (Usuario) request.getSession().getAttribute(KEY_USUARIO);
		if (usuario == null) {
			return null;
		}
		return usuario;
	}

	public void setLogger(Class clase) {
		log = Logger.getLogger(clase);
	}

	public static String getListTipoProveedormbls() {
		return LIST_TIPO_PROVEEDORMBLS;
	}

	public static String getListTipoProveedorCombo() {
		return LIST_TIPO_PROVEEDOR_COMBO;
	}

	public static int getAnioBusqueda(HttpServletRequest request) {
		log =Logger.getLogger(GenericAction.class);
		if (request.getParameter("anioSiniestro") != null && !"".equals(request.getParameter("anioSiniestro"))) {
			try {

				return Integer.parseInt(request.getParameter("anioSiniestro"));
			} catch (Exception e) {
				log.info("info ",e);
				return 0;
			}
		} else {
			try {
				return Integer.parseInt(request.getParameter(KEY_ANIO_BUSQUEDA));
			} catch (Exception e) {
				log.info("info ",e);
				log.info("request.getParameter(fechaOcurrencia) "+request.getParameter("fechaOcurrencia"));
				if (request.getParameter("fechaOcurrencia") != null) {
					try {
						return Integer.parseInt(Utilidad.DateToString(Utilidad.StringToDate(request.getParameter("fechaOcurrencia"), "dd/MM/yyyy"), "yyyy"));
					} catch (Exception e1) {
						log.info("info ",e1);
					}
				}
				try {
					return Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy"));
				} catch (PersonalException e3) {
					log.info("info ",e3);
					return 0;
				}
			}
		}
	}

	public static void incluirTraza(String accion, String identificador, String descripcion, Usuario usu) {
		try {
			ExpAccion.incluirAccion(accion, descripcion, identificador, usu);
		} catch (Exception e) {
			log.error("Error incluyendo traza ", e);
		}
	}

	public boolean isPermitido(ArrayList nodos, String clase) throws IOException {
		String nombreRecurso = clase;
		Properties p = SingletonNodos.getSingleton();
		String values = "";
		String permisos[];
		values = p.getProperty(nombreRecurso);
		try {
			permisos = values.split(",");
		} catch (NullPointerException e) {
			return false;
		}
		for (int i = 0; i < permisos.length; i++) {
			String idNodo = permisos[i];
			for (int e = 0; e < nodos.size(); e++) {
				Nodo nodo = (Nodo) nodos.get(e);
				if (idNodo.equals("*")) {
					return true;
				} else if (new Integer(nodo.getId()).toString().equals(idNodo)) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	protected ActionMessages validarFiltros(HttpServletRequest request, ActionForm form, Entorno env, ActionMessages am) throws PersonalNotFillItems, PersonalNotFoundException, SQLException {
		DynaActionForm dForm = (DynaActionForm) form;
		if (dForm != null) {
			
			log.info("lista tipo tra "+dForm.get("tipoProveedor"));
			log.info("GENERICaction ");
			if (env.isListTipoTramite()) {
				String param = (String) dForm.get("listTipoTramite");
				if ("-1".equals(param)||"".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.listTipoTramite.requerido"));
				}
			}
			if (env.isCedula()) {
				String param = (String) dForm.get("cedula");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.cedula.requerido"));
				}
			}
			if (env.isNombreApellido()) {
				String param = (String) dForm.get("nombres");
				if ("".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.nombreApellido.requerido"));
				}
			}
			if (env.isCodigo()) {
				String param = (String) dForm.get("codigo");
				if (param == null || "".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.codigo.requerido"));
				}
			}
			if (env.isSubCodigo()) {
				String param = (String) dForm.get("subCodigo");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.subCodigo.requerido"));
				}
			}
			if (env.isCodigoPreOrden()) {
				String param = (String) dForm.get("codigoPreOrden");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.codigoPreOrden.requerido"));
				}
			}
			if (env.isnumeroOrdenPago()) {
				String param = (String) dForm.get("numeroOrdenPago");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.numeroOrdenPago.requerido"));
				}
			}
			if (env.isRif()) {
				String param = (String) dForm.get("rif");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.rif.requerido"));
				}else{
					if (env.isFechaProveedor()) {
						String param2 = (String) dForm.get("fechaInicio");
						if ("".equals(param2.trim())) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.fecha.requerido"));
						}
					}
				}
			}

			if (env.isFechaOcurrencia()) {
				String param = (String) dForm.get("fechaOcurrencia");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaocurrencia.requerido"));
				} else {
					try {
						Date ocu = Utilidad.StringToDate(param, "dd/MM/yyyy");
						Date hoy = new Date();
						if (ocu.after(hoy)) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.fechaocurrencia.mayorhoy"));
						}
					} catch (ParseException e) {
					}
				}
			}
			if (env.isFechaNotificacion()) {
				String param = (String) dForm.get("fechaNotificacion");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechanotificacion.requerido"));
				} else {
					if (env.isFechaOcurrencia()) {
						String param2 = (String) dForm.get("fechaOcurrencia");
						if (!"".equals(param.trim())) {
							try {
								Date ocu = Utilidad.StringToDate(param2, "dd/MM/yyyy");
								Date not = Utilidad.StringToDate(param, "dd/MM/yyyy");
								if (not.before(ocu)) {
									am.add(ALERT_VALIDACION, new ActionMessage("env.fechanotif.menorocurrencia"));
								}
							} catch (ParseException e) {
							}
						}
					}
				}
			}
			if (env.isNumeroFactura()) {
				String param = (String) dForm.get("numeroFactura");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.numeroFactura.requerido"));
				}
			}
			if (env.isControlFactura()) {
				String param = (String) dForm.get("controlFactura");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.controlfactura.requerido"));
				}
			}
			if (env.isPorcentajeIva()) {
				String param = (String) dForm.get("porcentajeIva");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.porcentajeiva.requerido"));
				}
			}
			if (env.isListIva()) {
				String param = (String) dForm.get("listIva");
				if ("-1".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.porcentajeiva.requerido"));
				}
			}
			if (env.isFechaFactura()) {
				String param = (String) dForm.get("fechaFactura");
				log.info("fechaFactura" + (String) dForm.get("fechaFactura"));
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaFactura.requerido"));
				}
			}
			if (env.isFechaRecepcionFactura()) {
				String param = (String) dForm.get("fechaRecepcionFactura");
				log.info("fechaRecepcionFactura" + (String) dForm.get("fechaRecepcionFactura"));
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaRecepcionFactura.requerido"));
				}
			}
			if (env.isFechaInicio()) {
				String param = (String) dForm.get("fechaInicio");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaInicio.requerido"));
				}
			}
			if (env.isFechaIngreso()) {
				String param = (String) dForm.get("fechaIngreso");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaIngreso.requerido"));
				}
			}
			if (env.isFechaEgresoRequerida()) {
				String param = (String) dForm.get("fechaEgreso");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaEgreso.requerido"));
				}
			}
			if (env.isFechaEgreso()) {
				String param = (String) dForm.get("fechaEgreso");
			}
			if (env.isFechaFin()) {
				String param = (String) dForm.get("fechaFin");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.fechaFin.requerido"));
				}
			}
			if (env.isCausaIngreso()) {
				String param = (String) dForm.get("idCausaIngreso");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.causaingreso.requerido"));
				}
			}
			if (env.isMontoPagado()) {
				String param = (String) dForm.get("montoPagado");
				boolean cumple = true;
				String numeros = "0123456789.";
				double monto = 0;
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montopagado.requerido"));
				} else {
					if (!"0.00".equals(param.trim())) {
						for (int i = 0; i < param.length(); i++) {
							char c = param.charAt(i);
							if (numeros.indexOf(c) == -1) {
								cumple = false;
							}
						}
						if (cumple == false) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
						} else {
							monto = Double.parseDouble(param);
						}
						if (monto == 0) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.montopagado.requerido"));
						}
					} else {
						am.add(ALERT_VALIDACION, new ActionMessage("env.montopagado.requerido"));
					}
				}
			}
			if (env.isMontoPresupuestado()) {
				String param = (String) dForm.get("montoPresupuestado");
				boolean cumple = true;
				String numeros = "0123456789.";
				double monto = 0;
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoPresupuestado.requerido"));
				} else {
					if (!"0.00".equals(param.trim())) {
						for (int i = 0; i < param.length(); i++) {
							char c = param.charAt(i);
							if (numeros.indexOf(c) == -1) {
								cumple = false;
							}
						}
						if (cumple == false) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
						} else {
							monto = Double.parseDouble(param);
						}
						if (monto == 0) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.montoPresupuestado.requerido"));
						}
					} else {
						am.add(ALERT_VALIDACION, new ActionMessage("env.montoPresupuestado.requerido"));
					}
				}
			}
			if (env.isMontoNegociado()) {
				String param = (String) dForm.get("montoNegociado");
				boolean cumple = true;
				String numeros = "0123456789.";
				double monto = 0;
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoNegociado.requerido"));
				} else {
					if (!"0.00".equals(param.trim())) {
						for (int i = 0; i < param.length(); i++) {
							char c = param.charAt(i);
							if (numeros.indexOf(c) == -1) {
								cumple = false;
							}
						}
						if (cumple == false) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
						} else {
							monto = Double.parseDouble(param);
						}
						if (monto == 0) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.montoNegociado.requerido"));
						}
					} else {
						am.add(ALERT_VALIDACION, new ActionMessage("env.montoNegociado.requerido"));
					}
				}
			}
			if (env.isMontoFactura()) {
				String param = (String) dForm.get("montoFactura");
				boolean cumple = true;
				String numeros = "0123456789.";
				double monto = 0;
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoFactura.requerido"));
				} else {
					if (!"0.00".equals(param.trim())) {
						for (int i = 0; i < param.length(); i++) {
							char c = param.charAt(i);
							if (numeros.indexOf(c) == -1) {
								cumple = false;
							}
						}
						if (cumple == false) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
						} else {
							monto = Double.parseDouble(param);
						}
						if (monto == 0 && (COD_ESTATUS_RECHAZADO != Integer.parseInt(dForm.getString("estatus")))) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.montoFactura.requerido"));
						}
					} else {
						am.add(ALERT_VALIDACION, new ActionMessage("env.montoFactura.requerido"));
					}
				}
			}
			if (env.isMontoAmparado()) {
				String param = (String) dForm.get("montoAmparado");
				boolean cumple = true;
				String numeros = "0123456789.";
				double monto = 0;
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoamparado.requerido"));
				} else {
					if (!"0.00".equals(param.trim())) {
						for (int i = 0; i < param.length(); i++) {
							char c = param.charAt(i);
							if (numeros.indexOf(c) == -1) {
								cumple = false;
							}
						}
						if (cumple == false) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
						} else {
							monto = Double.parseDouble(param);
						}
						if (monto == 0) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.montoamparado.requerido"));
						}
					} else {
						am.add(ALERT_VALIDACION, new ActionMessage("env.montoamparado.requerido"));
					}
				}
			}
			if (env.isTipoGasto()) {
				log.info("tipo gasto " + dForm.get("tipoGasto"));
				String param = (String) dForm.get("tipoGasto");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.tipoGasto.requerido"));
				}
			}
			if (env.isAnioBusqueda()) {
				String param = (String) dForm.get("anioBusqueda");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.anioBusqueda.requerido"));
				}
			}
			if (env.isTipoProveedor()) {
				String param = (String) dForm.get("tipoProveedor");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.idTipoProveedor.requerido"));
				}
			}
			
			
			if (env.isProveedor_combo()) {
				String param = (String) dForm.get("proveedorCombo");
				if ("".equals(param) || "-1".equals(param) ||"0".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.proveedor.requerido"));
				}
			}
			
			
			if (env.isPoliza()) {
				String param = (String) dForm.get("poliza");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.poliza.requerido"));
				}
			}
			if (env.isProveedor()) {
				String param = (String) dForm.get("idProveedor");
				if ("".equals(param) || "-1".equals(param) ||"0".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.proveedor.requerido"));
				}
			}
			if (env.isTipoEmpleado()) {
				String param = (String) dForm.get("tipoEmpleado");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.tipoempleado.requerido"));
				}
			}
			if (env.isObservacionMedicinas()) {
				Persona t = buscarTitularBeneficiario(request);// (Persona)
				// request.getSession().getAttribute(KEY_TITULAR);
				String cedulaop = "";
				List listConsultas = new ArrayList();
				Mapa mapa = new Mapa();
				Recipe recipe = new Recipe();
				Medicamento medicamento = new Medicamento();
				PerConsulta perConsulta = new PerConsulta();
				if (t == null) {
					cedulaop = (String) request.getAttribute("cedulaop");
				} else {
					cedulaop = t.getBeneficiario().getCedula();
				}
				mapa.setCedulaBeneficiario(cedulaop);
				try {
					listConsultas = perConsulta.searchFuncionario(t.getBeneficiario().getCedula());
				} catch (Exception e) {
					try {
						listConsultas = perConsulta.searchFuncionario(t.getBeneficiario().getCedula());
					} catch (Exception e1) {
						try {
							listConsultas = perConsulta.searchBeneficiario(t.getBeneficiario().getCedula());
						} catch (Exception e2) {
							listConsultas = new ArrayList();
						}
					}
				}
				for (int i = 0; listConsultas.size() <= i; i++) {
					recipe = (Recipe) listConsultas.get(i);
				}
				if (request.getParameter("consignables" + recipe.getIdRecipe()) != null) {
					request.setAttribute("chequeado", recipe.getIdRecipe());
				} else {
					request.setAttribute("listConsultas", listConsultas);
				}
				if (env.isDetalleCobertura()) {
					String param = (String) dForm.get("cobertura");
					/*
					 * if ("editar".equals(request.getParameter("formulario")))
					 * { dForm.set("observacion",
					 * request.getParameter("observacion")); } else {
					 * dForm.set("observacion", ""); }
					 */
				}
			}
			if (env.isCobertura() || env.isCoberturaRangoFecha()) {
				PerCobertura pc = new PerCobertura();
				Cobertura tmpCobertura = new Cobertura();
				String param = (String) dForm.get("cobertura");
				int idEstatus = 0;
				if (env.isEstatus()) {
					idEstatus = Integer.parseInt(dForm.getString("estatus"));
				}
				String cedulaop = "";
				if (!("-1".equals(param) && !"".equals(param))) {
					Persona t = buscarTitularBeneficiario(request);
					if (t == null) {
						cedulaop = (String) request.getAttribute("cedulaop");
					} else {
						cedulaop = t.getBeneficiario().getCedula();
					}
					PerCobertura ps = new PerCobertura();
					Cobertura cob = new Cobertura();
					List cobertura = new ArrayList();
					List desgloseCobertura = new ArrayList();
					Mapa mapa = new Mapa();
					mapa.setCedula(t.getCedula());
					mapa.setCedulaBeneficiario(cedulaop);
					mapa.setIdCobertura(Integer.parseInt(param));
					try {
						tmpCobertura = pc.searchById(Integer.parseInt(param));
						mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(tmpCobertura.getPoliza().getFechaInicio(), "yyyy")));
					} catch (Exception e) {
						mapa.setAnioSiniestro(getAnioBusqueda(request));
						log.error("error", e);
					}
					log.info("valor del tiular " + t);
					if (t != null) {
						cob = ps.searchById(mapa.getIdCobertura());
						if (idEstatus == 0 || idEstatus != COD_ESTATUS_RECHAZADO) {
							if (cob.isPorPatologia()) {
								try {
									desgloseCobertura = ps.listDesgloseCobertura(mapa);
									cob.setDesgloseCobertura(desgloseCobertura);
								} catch (Exception e) {
									log.info("exception ", e);
									List tmpCob = new ArrayList();
									Cobertura objCob = new Cobertura();
									try {
										objCob = ps.searchById(cob.getId());
										objCob.setPatologia(objCob.getTipoCobertura().getDescripcion());
									} catch (Exception e1) {
										log.info("no Encontrado", e1);
									}
									tmpCob.add(objCob);
									cob.setDesgloseCobertura(tmpCob);
								}
								request.setAttribute("desgloseCobertura", desgloseCobertura);
							} else {
								try {
									cobertura = ps.listByCedula(mapa);
								} catch (Exception e) {
									log.info(e);
								}
								request.setAttribute("detalleMontoCobertura", cobertura);
							}
						} else {
							// Si es del pote
							log.info("en el catch" + mapa);
							cobertura = ps.listByCedula(mapa);
							log.info("el mapa" + mapa);
							request.setAttribute("detalleMontoCobertura", cobertura);
						}
					}
				}
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.cobertura.requerido"));
				}
			}
			if (env.isCuentas()) {
				log.info("es cuenta");
				String param = (String) dForm.get("tipoProveedor");
				log.info("es tipo prov" + param);
				// primero verifico si ya seleccionò un tipo de provedor
				if (!"-1".equals(param)) {
					// Verifico si el tipo de provedor selecccionado es un
					// asegurado
					log.info("es vacio diferente de vacio -1" + param);
					if (String.valueOf(COD_TIPO_PROVEEDOR_BENEFICIARIO).equals(param)) {
						// busco la cuenta
						log.info("es titular " + param);
						String cedulaop = "";
						Persona t = buscarTitularBeneficiario(request);// (Persona)
						// request.getSession().getAttribute(KEY_TITULAR);
						log.info("persona " + t.getCedula());
						if (t == null) {
							cedulaop = (String) request.getAttribute("cedulaop");
							log.info("nuloooo " + t.getCedula());
						} else {
							cedulaop = t.getCedula();
							log.info("persona " + cedulaop);
						}
						PerCuenta cb = new PerCuenta();
						Cuenta cob = new Cuenta();
						if (t != null) {
							try {
								try {
									log.info("cuentaliderantes3" + cob);									
									cob = ExpCuenta.buscarCuentaBeneficiario(cedulaop);									
									log.info("cuentaliderdespues" + cob);
									request.setAttribute("cuenta", cob);
									
								} catch (PersonalNotFoundException e) {
									// busco cta_beneficiario
									CuentaBenef cub = new CuentaBenef();
									cub = cb.searchNoSgfrh(cedulaop);
									request.setAttribute("cuenta", cub);
								} catch (SQLException e) {								
								}
							} catch (Exception e) {
									am.add(ALERT_VALIDACION, new ActionMessage("env.cuentaTitular.requerido"));
									request.setAttribute("cuenta", null);
									request.setAttribute("cedula", null);
							}
						}
					}
				} else {
					}
			}
			if (env.isTipoSiniestro()) {
				String param = (String) dForm.get("tipoSiniestro");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.tiposiniestro.requerido"));
				}
			}
			if (env.isTipoEnfermedad()) {
				String param = (String) dForm.get("tipoEnfermedad");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.tipoenfermedad.requerido"));
				}
			}
			if (env.isTipoTratamiento()) {
				String param = (String) dForm.get("tipoTratamiento");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.tipoTratamiento.requerido"));
				}
			}

			if (env.isEstatus()) {
				String param = (String) dForm.get("estatus");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.estatus.requerido"));
				} else {
					PerEstatus perEst = new PerEstatus();
					Estatus est = new Estatus();
					try {
						est = perEst.buscar(Integer.parseInt(param));
					} catch (Exception e) {
					}
					if (request.getParameter("justificar") == null) {
						if (est.isJustificacion()) {
							request.setAttribute("justificar", "justificar");
						} else {
							request.setAttribute("justificar", null);
						}
					} else {
						if (est.isJustificacion()) {
							request.setAttribute("justificar", "justificar");
						} else {
							request.setAttribute("justificar", null);
						}
						if ("".equals(((String) dForm.get("justificacion")).trim())) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.jutificacion.requerido"));
						}
					}
				}
			}
			if (env.isObservacion()) {
				String param = (String) dForm.get("observacion");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.observacion.requerido"));
				}
			}
			if (env.isEspecialidad()) {
				String param = (String) dForm.get("especialidad");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.especialidad.requerido"));
				}
			}
			if (env.isPatologias()) {
				String param = (String) dForm.get("patologias");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.patologia.requerido"));
				}
			}
			if (env.isOrgano()) {
				String param = (String) dForm.get("organo");
				if ("-1".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.organo.requerido"));
				}
			}
			if (env.isNombres()) {
				String param = (String) dForm.get("nombres");
				if ("".equals(param)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.nombres.requerido"));
				}
			}

			if (env.isMonto()) {
				String param = (String) dForm.get("monto");
				// Parametros.getIntParameter(form, "0", 0.0);
				log.info("param " + param);
				boolean cumple = true;
				String numeros = "0123456789.";
				double monto = 0;
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.monto.requerido"));
				} else {
					if (!"0.00".equals(param.trim())) {
						for (int i = 0; i < param.length(); i++) {
							char c = param.charAt(i);
							if (numeros.indexOf(c) == -1) {
								cumple = false;
							}
						}
						if (cumple == false) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
						} else {
							monto = Double.parseDouble(param);
						}
						if (monto == 0) {
							am.add(ALERT_VALIDACION, new ActionMessage("env.monto.requerido"));
						}
					} else {
						am.add(ALERT_VALIDACION, new ActionMessage("env.monto.requerido"));
					}
				}
			}
			if (env.isDescripcion()) {
				String param = (String) dForm.get("descripcion");
				if ("".equals(param.trim())) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.descripcion.requerido"));
				}
			}
			if (env.isMontoCalculado()) {
				boolean cumple = true;
				String numeros = "0123456789.";
				String montoHonorarios = (String) dForm.get("montoHonorariosMedicos");
				String montoGastosClinicos = (String) dForm.get("montoGastosClinicos");
				String montoExamenesPreoperatorios = (String) dForm.get("montoExamenesPreoperatorios");
				String montoMaterialMedicoConIva = (String) dForm.get("montoMaterialMedicoConIva");
				String montoMaterialMedicoSinIva = (String) dForm.get("montoMaterialMedicoSinIva");
				String montoAmbulancia = (String) dForm.get("montoAmbulancia");
				String montoFuneraria = (String) dForm.get("montoFuneraria");
				String montoExamenesEspeciales = (String) dForm.get("montoExamenesEspeciales");
				double montoCalculado = 0;
				if (!"".equals(montoHonorarios) && !"0.00".equals(montoHonorarios)) {
					for (int i = 0; i < montoHonorarios.length(); i++) {
						char c = montoHonorarios.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoHonorarios);
					}
				}
				if (cumple != false && !"".equals(montoGastosClinicos) && !"0.00".equals(montoGastosClinicos)) {
					for (int i = 0; i < montoGastosClinicos.length(); i++) {
						char c = montoGastosClinicos.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoGastosClinicos);
					}
				}
				if (cumple != false && !"".equals(montoExamenesPreoperatorios) && !"0.00".equals(montoExamenesPreoperatorios)) {
					for (int i = 0; i < montoExamenesPreoperatorios.length(); i++) {
						char c = montoExamenesPreoperatorios.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoExamenesPreoperatorios);
					}
				}
				if (cumple != false && !"".equals(montoMaterialMedicoConIva) && !"0.00".equals(montoMaterialMedicoConIva)) {
					for (int i = 0; i < montoMaterialMedicoConIva.length(); i++) {
						char c = montoMaterialMedicoConIva.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoMaterialMedicoConIva);
					}
				}
				if (cumple != false && !"".equals(montoMaterialMedicoSinIva) && !"0.00".equals(montoMaterialMedicoSinIva)) {
					for (int i = 0; i < montoMaterialMedicoSinIva.length(); i++) {
						char c = montoMaterialMedicoSinIva.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoMaterialMedicoSinIva);
					}
				}
				if (cumple != false && !"".equals(montoAmbulancia) && !"0.00".equals(montoAmbulancia)) {
					for (int i = 0; i < montoAmbulancia.length(); i++) {
						char c = montoAmbulancia.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoAmbulancia);
					}
				}
				if (cumple != false && !"".equals(montoFuneraria) && !"0.00".equals(montoFuneraria)) {
					for (int i = 0; i < montoFuneraria.length(); i++) {
						char c = montoFuneraria.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoFuneraria);
					}
				}
				if (cumple != false && !"".equals(montoExamenesEspeciales) && !"0.00".equals(montoExamenesEspeciales)) {
					for (int i = 0; i < montoExamenesEspeciales.length(); i++) {
						char c = montoExamenesEspeciales.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoCalculado = montoCalculado + Double.parseDouble(montoExamenesEspeciales);
					}
				}
				if (cumple == false) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
				}
				if (montoCalculado == 0) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoCalculado.requerido"));
				} else {
					request.setAttribute("montoCalculado", montoCalculado);
				}
			}
			
			/*if(env.isDetalleSiniestroPadre())
			{
				Siniestro sin = new Siniestro();
				sin = (Siniestro) request.getSession().getAttribute("siniestroPadre");
				if (sin != null) {
					request.getSession().setAttribute("siniestroPadre", sin);
					request.setAttribute("idSiniPadre", sin.getId());
				}
			}*/
			
			if (env.isDetallePresupuesto() || env.isDetallePresupuestoEdit()) {
				boolean cumple = true;
				String numeros = "0123456789.";
				String montoHonorariosPresupuestado = "";
				String montoGastosPresupuestado = "";
				montoHonorariosPresupuestado = (String) dForm.get("montoHonorariosPresupuestado");
				montoGastosPresupuestado = (String) dForm.get("montoGastosPresupuestado");
				String montoHonorariosNegociado = (String) dForm.get("montoHonorariosNegociado");
				String montoGastosNegociado = (String) dForm.get("montoGastosNegociado");
				String montoHonorariosMedicos = (String) dForm.get("montoHonorariosMedicos");
				String montoGastosClinicos = (String) dForm.get("montoGastosClinicos");
				double montoPresupuestado = 0;
				double montoNegociado = 0;
				double montoAmparado = 0;
				double montoHonorariosMedicosNoAmparado = 0;
				double montoGastosClinicosNoAmparado = 0;
				double montoNoAmparado = 0;
				double montoHNegociado = 0;
				double montoGNegociado = 0;
				double montoHAmparado = 0;
				double montoGAmparado = 0;
				// CALCULAR LO PRESUPUESTADO
				if (!"".equals(montoHonorariosPresupuestado)) {
					for (int i = 0; i < montoHonorariosPresupuestado.length(); i++) {
						char c = montoHonorariosPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoHonorariosPresupuestado);
					}
				}
				if (cumple != false && !"".equals(montoGastosPresupuestado)) {
					for (int i = 0; i < montoGastosPresupuestado.length(); i++) {
						char c = montoGastosPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoGastosPresupuestado);
					}
				}
				// CALCULAR LO AJUSTADO
				if (!"".equals(montoHonorariosNegociado)) {
					for (int i = 0; i < montoHonorariosNegociado.length(); i++) {
						char c = montoHonorariosNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoHonorariosNegociado);
						montoHNegociado = Double.parseDouble(montoHonorariosNegociado);
					}
				}
				if (cumple != false && !"".equals(montoGastosNegociado)) {
					for (int i = 0; i < montoGastosNegociado.length(); i++) {
						char c = montoGastosNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoGastosNegociado);
						montoGNegociado = Double.parseDouble(montoGastosNegociado);
					}
				}
				// AMPARADO
				if (!"".equals(montoHonorariosMedicos)) {
					for (int i = 0; i < montoHonorariosMedicos.length(); i++) {
						char c = montoHonorariosMedicos.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoHonorariosMedicos);
						montoHAmparado = Double.parseDouble(montoHonorariosMedicos);
					}
				}
				if (cumple != false && !"".equals(montoGastosClinicos)) {
					for (int i = 0; i < montoGastosClinicos.length(); i++) {
						char c = montoGastosClinicos.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoGastosClinicos);
						montoGAmparado = Double.parseDouble(montoGastosClinicos);
					}
				}
				if (cumple == false) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
				}
				if (montoPresupuestado == 0) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoCalculado.requerido"));
				} else {
					montoNoAmparado = montoNegociado - montoAmparado;
					montoHonorariosMedicosNoAmparado = montoHNegociado - montoHAmparado;
					montoGastosClinicosNoAmparado = montoGNegociado - montoGAmparado;
					dForm.set("montoPresupuestado", String.valueOf(montoPresupuestado));
					dForm.set("montoNegociado", String.valueOf(montoNegociado));
					dForm.set("montoAmparado", String.valueOf(montoAmparado));
					dForm.set("montoNoAmparado", String.valueOf(montoNoAmparado));
					dForm.set("montoHonorariosMedicosNoAmparado", String.valueOf(montoHonorariosMedicosNoAmparado));
					dForm.set("montoGastosClinicosNoAmparado", String.valueOf(montoGastosClinicosNoAmparado));
					request.setAttribute("montoPresupuestado", montoPresupuestado);
					request.setAttribute("montoNegociado", montoNegociado);
					request.setAttribute("montoAmparado", montoAmparado);
					if ("".equals(montoHonorariosNegociado) || "0.00".equals(montoHonorariosNegociado)) {
						dForm.set("montoHonorariosNegociado", String.valueOf(montoHonorariosPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoHonorariosMedicos) || "0.00".equals(montoHonorariosMedicos)) {
						dForm.set("montoHonorariosMedicos", String.valueOf(montoHonorariosPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
					if ("".equals(montoGastosNegociado) || "0.00".equals(montoGastosNegociado)) {
						dForm.set("montoGastosNegociado", String.valueOf(montoGastosPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoGastosClinicos) || "0.00".equals(montoGastosClinicos)) {
						dForm.set("montoGastosClinicos", String.valueOf(montoGastosPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
				}
				Siniestro sin = new Siniestro();
				sin = (Siniestro) request.getSession().getAttribute("siniestroPadre");
				if (sin != null) {
					request.getSession().setAttribute("siniestroPadre", sin);
					request.setAttribute("idSiniPadre", sin.getId());
				}
				request.setAttribute("montoHonorariosPresupuestado", montoHonorariosPresupuestado);
				request.setAttribute("montoGastosPresupuestado", montoGastosPresupuestado);
				request.setAttribute("montoNoAmparado", montoNoAmparado);
				request.setAttribute("montoHonorariosMedicosNoAmparado", montoHonorariosMedicosNoAmparado);
				request.setAttribute("montoGastosClinicosNoAmparado", montoGastosClinicosNoAmparado);
			}
			// /////////////////////////////////////////////////////////////////
			if (env.isDetallePresupuestoEmergencia() || env.isDetallePresupuestoEditEmergencia()) {
				boolean cumple = true;
				boolean calculado = false;
				request.setAttribute("citaPreOperatorio", request.getParameter("citaPreOperatorio"));
				request.setAttribute("citaPostOperatorio", request.getParameter("citaPostOperatorio"));
				String numeros = "0123456789.";
				String montoHonorariosPresupuestado = (String) dForm.get("montoHonorariosPresupuestado");
				String montoGastosPresupuestado = (String) dForm.get("montoGastosPresupuestado");
				String montoAmbulanciaPresupuestado = (String) dForm.get("montoAmbulanciaPresupuestado");
				String montoFunerariaPresupuestado = (String) dForm.get("montoFunerariaPresupuestado");
				String montoExamenesEspecialesPresupuestado = (String) dForm.get("montoExamenesEspecialesPresupuestado");
				String montoHonorariosNegociado = (String) dForm.get("montoHonorariosNegociado");
				String montoGastosNegociado = (String) dForm.get("montoGastosNegociado");
				String montoAmbulanciaNegociado = (String) dForm.get("montoAmbulanciaNegociado");
				String montoExamenesEspecialesNegociado = (String) dForm.get("montoExamenesEspecialesNegociado");
				String montoFunerariaNegociado = (String) dForm.get("montoFunerariaNegociado");
				String montoHonorariosMedicos = (String) dForm.get("montoHonorariosMedicos");
				String montoGastosClinicos = (String) dForm.get("montoGastosClinicos");
				String montoAmbulancia = (String) dForm.get("montoAmbulancia");
				String montoFuneraria = (String) dForm.get("montoFuneraria");
				String montoExamenesEspeciales = (String) dForm.get("montoExamenesEspeciales");
				double montoPresupuestado = 0;
				double montoNegociado = 0;
				double montoAmparado = 0;
				double montoNoAmparado = 0;
				double montoHonorariosMedicosNoAmparado = 0;
				double montoGastosClinicosNoAmparado = 0;
				double montoAmbulanciaNoAmparado = 0;
				double montoFunerariaNoAmparado = 0;
				double montoExamenesEspecialesNoAmparado = 0;
				double montoHNegociado = 0;
				double montoGNegociado = 0;
				double montoANegociado = 0;
				double montoFNegociado = 0;
				double montoEENegociado = 0;
				double montoHAmparado = 0;
				double montoGAmparado = 0;
				double montoAAmparado = 0;
				double montoFAmparado = 0;
				double montoEEAmparado = 0;
				// CALCULAR LO PRESUPUESTADO
				if (!"".equals(montoHonorariosPresupuestado) && !"0.00".equals(montoHonorariosPresupuestado)) {
					for (int i = 0; i < montoHonorariosPresupuestado.length(); i++) {
						char c = montoHonorariosPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoHonorariosPresupuestado);
					}
				}
				if (cumple != false && !"".equals(montoGastosPresupuestado) && !"0.00".equals(montoGastosPresupuestado)) {
					for (int i = 0; i < montoGastosPresupuestado.length(); i++) {
						char c = montoGastosPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoGastosPresupuestado);
					}
				}
				if (cumple != false && !"".equals(montoAmbulanciaPresupuestado) && !"0.00".equals(montoAmbulanciaPresupuestado)) {
					for (int i = 0; i < montoAmbulanciaPresupuestado.length(); i++) {
						char c = montoAmbulanciaPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoAmbulanciaPresupuestado);
					}
				}
				if (cumple != false && !"".equals(montoFunerariaPresupuestado) && !"0.00".equals(montoFunerariaPresupuestado)) {
					for (int i = 0; i < montoFunerariaPresupuestado.length(); i++) {
						char c = montoFunerariaPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoFunerariaPresupuestado);
					}
				}
				if (cumple != false && !"".equals(montoExamenesEspecialesPresupuestado) && !"0.00".equals(montoExamenesEspecialesPresupuestado)) {
					for (int i = 0; i < montoExamenesEspecialesPresupuestado.length(); i++) {
						char c = montoExamenesEspecialesPresupuestado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoPresupuestado = montoPresupuestado + Double.parseDouble(montoExamenesEspecialesPresupuestado);
					}
				}
				// CALCULAR LO AJUSTADO
				if (!"".equals(montoHonorariosNegociado) && !"0.00".equals(montoHonorariosNegociado)) {
					for (int i = 0; i < montoHonorariosNegociado.length(); i++) {
						char c = montoHonorariosNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoHonorariosNegociado);
						montoHNegociado = Double.parseDouble(montoHonorariosNegociado);
					}
				}
				if (cumple != false && !"".equals(montoGastosNegociado) && !"0.00".equals(montoGastosNegociado)) {
					for (int i = 0; i < montoGastosNegociado.length(); i++) {
						char c = montoGastosNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoGastosNegociado);
						montoGNegociado = Double.parseDouble(montoGastosNegociado);
					}
				}
				if (cumple != false && !"".equals(montoAmbulanciaNegociado) && !"0.00".equals(montoAmbulanciaNegociado)) {
					for (int i = 0; i < montoAmbulanciaNegociado.length(); i++) {
						char c = montoAmbulanciaNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoAmbulanciaNegociado);
						montoANegociado = Double.parseDouble(montoAmbulanciaNegociado);
					}
				}
				if (cumple != false && !"".equals(montoFunerariaNegociado) && !"0.00".equals(montoFunerariaNegociado)) {
					for (int i = 0; i < montoFunerariaNegociado.length(); i++) {
						char c = montoFunerariaNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoFunerariaNegociado);
						montoFNegociado = Double.parseDouble(montoFunerariaNegociado);
					}
				}
				if (cumple != false && !"".equals(montoExamenesEspecialesNegociado) && !"0.00".equals(montoExamenesEspecialesNegociado)) {
					for (int i = 0; i < montoExamenesEspecialesNegociado.length(); i++) {
						char c = montoExamenesEspecialesNegociado.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoNegociado = montoNegociado + Double.parseDouble(montoExamenesEspecialesNegociado);
						montoEENegociado = Double.parseDouble(montoExamenesEspecialesNegociado);
					}
				}
				// AMPARADO
				if (!"".equals(montoHonorariosMedicos) && !"0.00".equals(montoHonorariosMedicos)) {
					for (int i = 0; i < montoHonorariosMedicos.length(); i++) {
						char c = montoHonorariosMedicos.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoHonorariosMedicos);
						montoHAmparado = Double.parseDouble(montoHonorariosMedicos);
					}
				}
				if (cumple != false && !"".equals(montoGastosClinicos) && !"0.00".equals(montoGastosClinicos)) {
					for (int i = 0; i < montoGastosClinicos.length(); i++) {
						char c = montoGastosClinicos.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoGastosClinicos);
						montoGAmparado = Double.parseDouble(montoGastosClinicos);
					}
				}
				if (cumple != false && !"".equals(montoAmbulancia) && !"0.00".equals(montoAmbulancia)) {
					for (int i = 0; i < montoAmbulancia.length(); i++) {
						char c = montoAmbulancia.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoAmbulancia);
						montoAAmparado = Double.parseDouble(montoAmbulancia);
					}
				}
				if (cumple != false && !"".equals(montoFuneraria) && !"0.00".equals(montoFuneraria)) {
					for (int i = 0; i < montoFuneraria.length(); i++) {
						char c = montoFuneraria.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoFuneraria);
						montoFAmparado = Double.parseDouble(montoFuneraria);
					}
				}
				if (cumple != false && !"".equals(montoExamenesEspeciales) && !"0.00".equals(montoExamenesEspeciales)) {
					for (int i = 0; i < montoExamenesEspeciales.length(); i++) {
						char c = montoExamenesEspeciales.charAt(i);
						if (numeros.indexOf(c) == -1) {
							cumple = false;
						}
					}
					if (cumple != false) {
						montoAmparado = montoAmparado + Double.parseDouble(montoExamenesEspeciales);
						montoEEAmparado = Double.parseDouble(montoExamenesEspeciales);
					}
				}
				if (cumple == false) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.general.monto.invalido"));
				}
				if (montoPresupuestado == 0) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.montoCalculado.requerido"));
				} else {
					montoNoAmparado = montoNegociado - montoAmparado;
					montoHonorariosMedicosNoAmparado = montoHNegociado - montoHAmparado;
					montoGastosClinicosNoAmparado = montoGNegociado - montoGAmparado;
					montoAmbulanciaNoAmparado = montoANegociado - montoAAmparado;
					montoFunerariaNoAmparado = montoFNegociado - montoFAmparado;
					montoExamenesEspecialesNoAmparado = montoEENegociado - montoEEAmparado;
					dForm.set("montoPresupuestado", String.valueOf(montoPresupuestado));
					dForm.set("montoNegociado", String.valueOf(montoNegociado));
					dForm.set("montoAmparado", String.valueOf(montoAmparado));
					dForm.set("montoNoAmparado", String.valueOf(montoNoAmparado));
					dForm.set("montoHonorariosMedicosNoAmparado", String.valueOf(montoHonorariosMedicosNoAmparado));
					dForm.set("montoGastosClinicosNoAmparado", String.valueOf(montoGastosClinicosNoAmparado));
					dForm.set("montoAmbulanciaNoAmparado", String.valueOf(montoAmbulanciaNoAmparado));
					dForm.set("montoFunerariaNoAmparado", String.valueOf(montoFunerariaNoAmparado));
					dForm.set("montoExamenesEspecialesNoAmparado", String.valueOf(montoExamenesEspecialesNoAmparado));
					request.setAttribute("montoPresupuestado", montoPresupuestado);
					request.setAttribute("montoNegociado", montoNegociado);
					request.setAttribute("montoAmparado", montoAmparado);
					if ("".equals(montoHonorariosNegociado) || "0.00".equals(montoHonorariosNegociado)) {
						dForm.set("montoHonorariosNegociado", String.valueOf(montoHonorariosPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoAmbulanciaNegociado) || "0.00".equals(montoAmbulanciaNegociado)) {
						dForm.set("montoAmbulanciaNegociado", String.valueOf(montoAmbulanciaPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoFunerariaNegociado) || "0.00".equals(montoFunerariaNegociado)) {
						dForm.set("montoFunerariaNegociado", String.valueOf(montoFunerariaPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoExamenesEspecialesNegociado) || "0.00".equals(montoExamenesEspecialesNegociado)) {
						dForm.set("montoExamenesEspecialesNegociado", String.valueOf(montoExamenesEspecialesPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoGastosNegociado) || "0.00".equals(montoGastosNegociado)) {
						dForm.set("montoGastosNegociado", String.valueOf(montoGastosPresupuestado));
						dForm.set("montoNegociado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoNegociado", montoPresupuestado);
					}
					if ("".equals(montoHonorariosMedicos) || "0.00".equals(montoHonorariosMedicos)) {
						dForm.set("montoHonorariosMedicos", String.valueOf(montoHonorariosPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
					if ("".equals(montoAmbulancia) || "0.00".equals(montoAmbulancia)) {
						dForm.set("montoAmbulancia", String.valueOf(montoAmbulanciaPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
					if ("".equals(montoFuneraria) || "0.00".equals(montoFuneraria)) {
						dForm.set("montoFuneraria", String.valueOf(montoFunerariaPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
					if ("".equals(montoExamenesEspeciales) || "0.00".equals(montoExamenesEspeciales)) {
						dForm.set("montoExamenesEspeciales", String.valueOf(montoExamenesEspecialesPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
					if ("".equals(montoGastosClinicos) || "0.00".equals(montoGastosClinicos)) {
						dForm.set("montoGastosClinicos", String.valueOf(montoGastosPresupuestado));
						dForm.set("montoAmparado", String.valueOf(montoPresupuestado));
						request.setAttribute("montoAmparado", montoPresupuestado);
					}
				}
				request.setAttribute("montoHonorariosPresupuestado", montoHonorariosPresupuestado);
				request.setAttribute("montoGastosPresupuestado", montoGastosPresupuestado);
				request.setAttribute("montoAmbulanciaPresupuestado", montoAmbulanciaPresupuestado);
				request.setAttribute("montoFunerariaPresupuestado", montoFunerariaPresupuestado);
				request.setAttribute("montoExamenesEspecialesPresupuestado", montoExamenesEspecialesPresupuestado);
				request.setAttribute("montoHonorariosMedicosNoAmparado", montoHonorariosMedicosNoAmparado);
				request.setAttribute("montoGastosClinicosNoAmparado", montoGastosClinicosNoAmparado);
				request.setAttribute("montoAmbulanciaNoAmparado", montoAmbulanciaNoAmparado);
				request.setAttribute("montoFunerariaNoAmparado", montoFunerariaNoAmparado);
				request.setAttribute("montoExamenesEspecialesNoAmparado", montoExamenesEspecialesNoAmparado);
				request.setAttribute("montoNoAmparado", montoNoAmparado);
			}
			// ////////////////////////////////////////////////////////////////
			if (env.isCriterioBusqueda()) {
				String cedula = (String) dForm.get("cedula");
				String nombres = (String) dForm.get("nombres");
				String codigo = (String) dForm.get("codigo");
				if (cedula == null) {
					cedula = "";
				}
				if (nombres == null) {
					nombres = "";
				}
				if (codigo == null) {
					codigo = "";
				}
				if (("".equals(cedula) ) && "".equals(codigo)) {
					am.add(ALERT_VALIDACION, new ActionMessage("env.criterio.requerido"));
				}
				if (!"".equals(codigo)) {
					if (codigo.replace("-", "").length() < 4) {
						am.add(ALERT_VALIDACION, new ActionMessage("general.minimo.digitos"));
					}
				}
			}
			
			if (env.isAlMenosDos()){
				log.info("am.size() " + am.size());
				log.info("env.counter() " + env.getCounter());
				if ((env.getCounter() - am.size())<1){
					am.add(ALERT_AVISOS, new ActionMessage("general.almenos",2));
				}else{
					am.clear();
				}

			}
			if (!am.isEmpty()) {
				saveMessages(request, am);
				throw new PersonalNotFillItems();
			}
		} else {
			am.add(ALERT_AVISOS, new ActionMessage("env.llamadoilegal"));
		}
		return am;
	}

	protected void setEntorno(HttpServletRequest request, ActionForm form, Entorno ent) {
		request.setAttribute(KEY_ENTORNO, ent);
		if (ent.isTipoProveedor()) {
			setTipoProveedor(request);
		}
		if (ent.isTipoProveedorP()) {
			setTipoProveedorP(request);
		}
		if (ent.isEspecialidad()) {
			setEspecialidad(request);
		}
		if (ent.isOrgano()) {
			setOrgano(request);
		}
		if (ent.isProveedor()) {
			setProveedor(request);
		}
	
		if (ent.isTratamiento()) {
			setTratamiento(request);
		}
		if (ent.isPatologias()) {
			setPatologias(request);
		}
		if (ent.isTipoEnfermedad()) {
			setTipoEnfermedad(request);
		}
		if (ent.isTipoTratamiento()) {
			setTipoTratamiento(request);
		}
		if (ent.isListTipoTramite()) {
			setListTipoTramite(request);
		}
		
		if (ent.isTipoProveedorRmbls()) {
			setListTipoProveedormbls(request);
		}
		
		if (ent.isProveedor_combo()) {
			setTipoProveedorCombo(request);
		}
		
		if (ent.isTipoEnfermedad2()) {
			setTipoEnfermedad2(request);
		}
		if (ent.isTipoSiniestro()) {
			setTipoSiniestro(request);
		}
		if (ent.isTipoGasto()) {
			setTipoGasto(request);
		}
		if (ent.isListIva()) {
			log.info("psa para cargar menu");
			setListIva(request);
		}
		
		if (ent.isTipoCobertura()) {
			setTipoCobertura(request);
		}
		if (ent.isTipoEmpleado()) {
			setTipoEmpleado(request);
		}
		if (ent.isCobertura()) {
			setCobertura(request);
		}
		if (ent.isPoliza()) {
			setPoliza(request);
		}
		if (ent.isEstatus()) {
			setEstatus(request);
		}
		if (ent.isCoberturaRangoFecha()) {
			Date fOcurreancia = new Date();
			if (ent.isFechaOcurrencia()) {
				try {
					fOcurreancia = Utilidad.StringToDate(((DynaActionForm) form).getString("fechaOcurrencia"), "dd/MM/yyyy");
				} catch (Exception e) {
					log.error("error", e);
					fOcurreancia = new Date();
				}
			}
			setCoberturaRangoFecha(request, fOcurreancia);
		}
		if (ent.isAnioBusqueda()) {
			setAnioBusqueda(request);
		}
	}

	private void setProveedor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	protected Mapa getDForm(HttpServletRequest request, ActionForm form, Entorno ent) throws PersonalNotFoundException, SQLException {
		DynaActionForm dForm = (DynaActionForm) form;
		Mapa map = new Mapa();
		map.setCitaPostOperatorio(false);
		map.setCitaPreOperatorio(false);
		map.setIdDependencia(this.usuarioSession(request).getIdDependencia());
		map.setIdUsuario(this.usuarioSession(request).getLogin());
		try {
			map.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy")));
		} catch (Exception e) {
		}
		if (ent.isCedula()) {
			if ("".equals(dForm.getString("cedula"))) {
				map.setCedula("");
			} else {
				map.setCedula(dForm.getString("cedula"));
			}
		}
		if (ent.isId()) {
			try {
				if (Integer.parseInt(dForm.getString("id")) == 0) {
					map.setId(-1);
				} else {
					map.setId(Integer.parseInt(dForm.getString("id")));
				}
			} catch (NumberFormatException e) {
				map.setId(-1);
			}
		}
		if (ent.isIdSiniestro()) {
			try {
				if (Integer.parseInt(dForm.getString("idSiniestro")) == 0) {
					map.setIdSiniestro(-1);
				} else {
					map.setIdSiniestro(Integer.parseInt(dForm.getString("idSiniestro")));
				}
			} catch (NumberFormatException e) {
				map.setIdSiniestro(-1);
			}
		}
		if (ent.isIdFactura()) {
			try {
				if (Integer.parseInt(dForm.getString("idFactura")) == 0) {
					map.setIdFactura(-1);
				} else {
					map.setIdFactura(Integer.parseInt(dForm.getString("idFactura")));
				}
			} catch (NumberFormatException e) {
				map.setIdFactura(-1);
			}
		}
		if (ent.isCodigo()) {
			if ("".equals(dForm.getString("codigo"))) {
				map.setCodigo("");
			} else {
				map.setCodigo(dForm.getString("codigo"));
			}
		}
		if (ent.isCodigoPreOrden()) {
			try {
				if ("".equals(dForm.getString("codigoPreOrden"))) {
					map.setCodigoPreOrden("");
				} else {
					map.setCodigoPreOrden(dForm.getString("codigoPreOrden"));
				}
			} catch (NumberFormatException e) {
				map.setCodigoPreOrden("");
			}
		}
		if (ent.isDescripcion()) {
			try {
				if ("".equals(dForm.getString("descripcion"))) {
					map.setDescripcion("");
				} else {
					map.setDescripcion(dForm.getString("descripcion"));
				}
			} catch (NumberFormatException e) {
				map.setDescripcion("");
			}
		}
		if (ent.isSubCodigo()) {
			try {
				if ("".equals(dForm.getString("subCodigo"))) {
					map.setSubCodigo("");
				} else {
					map.setSubCodigo(dForm.getString("subCodigo"));
				}
			} catch (NumberFormatException e) {
				map.setSubCodigo("");
			}
		}
		if (ent.isNumeroOrdenPago()) {
			map.setNumeroOrdenPago(Integer.parseInt(dForm.getString("numeroOrdenPago")));
		}
		if (ent.isObservacionMedicinas()) {
			if ("".equals(dForm.getString("observacion"))) {
				map.setObservacion("");
			} else {
				map.setObservacion(dForm.getString("observacion"));
			}
		}
		if (dForm.get("justificacion") != null) {
			map.setJustificacion((String) dForm.get("justificacion"));
		}
		if (ent.isObservacionNoRequerida()) {
			if ("".equals(dForm.getString("observacion"))) {
				map.setObservacion("");
			} else {
				map.setObservacion(dForm.getString("observacion"));
			}
		}
		if (ent.isCausaIngreso()) {
			try {
				if (Integer.parseInt(dForm.getString("idCausaIngreso")) == 0) {
					map.setPatologiaOrganoTratamiento(-1);
				} else {
					map.setPatologiaOrganoTratamiento(Integer.parseInt(dForm.getString("idCausaIngreso")));
				}
			} catch (NumberFormatException e) {
				map.setPatologiaOrganoTratamiento(-1);
			}
		}
		if (ent.isTipoEnfermedad()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoEnfermedad")) == 0) {
					map.setIdTipoEnfermedad(-1);
				} else {
					map.setIdTipoEnfermedad(Integer.parseInt(dForm.getString("tipoEnfermedad")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoEnfermedad(-1);
			}
		}

		if (ent.isAnioBusqueda()) {
			try {
				if (Integer.parseInt(dForm.getString("anioBusqueda")) == 0) {
					map.setAnioBusqueda(-1);
				} else {
					map.setAnioBusqueda(Integer.parseInt(dForm.getString("anioBusqueda")));
				}
			} catch (NumberFormatException e) {
				map.setAnioBusqueda(-1);
			}
		}
		if (ent.isTipoTratamiento()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoTratamiento")) == 0) {
					map.setIdTipoTratamiento(-1);
				} else {
					map.setIdTipoTratamiento(Integer.parseInt(dForm.getString("tipoTratamiento")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoTratamiento(-1);
			}
		}
		if (ent.isListTipoTramite()) {
			try {
				if (Integer.parseInt(dForm.getString("listTipoTramite")) == 0) {
					map.setIdListTipoTramite(-1);
				} else {
					map.setIdListTipoTramite(Integer.parseInt(dForm.getString("listTipoTramite")));
				}
			} catch (NumberFormatException e) {
				map.setIdListTipoTramite(-1);
			}
		}
		if (ent.isTipoCobertura()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoCobertura")) == 0) {
					map.setIdTipoCobertura(-1);
				} else {
					map.setIdTipoCobertura(Integer.parseInt(dForm.getString("tipoCobertura")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoCobertura(-1);
			}
		}
		if (ent.isTipoEmpleado()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoEmpleado")) == 0) {
					map.setIdTipoEmpleado(-1);
				} else {
					map.setIdTipoEmpleado(Integer.parseInt(dForm.getString("tipoEmpleado")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoEmpleado(-1);
			}
		}
		if (ent.isEstatus()) {
			try {
				if (Integer.parseInt(dForm.getString("estatus")) == 0) {
					map.setIdEstatus(-1);
				} else {
					map.setIdEstatus(Integer.parseInt(dForm.getString("estatus")));
				}
			} catch (NumberFormatException e) {
				map.setIdEstatus(-1);
			}
		}
		if (ent.isTipoProveedor()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoProveedor")) == 0) {
					map.setIdTipoProveedor(-1);
				} else {
					map.setIdTipoProveedor(Integer.parseInt(dForm.getString("tipoProveedor")));
//					map.setIdListTipoTramite(Integer.parseInt(dForm.getString("listTipoTramite")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoProveedor(-1);
			}
		}
		if (ent.isTipoProveedorP()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoProveedor")) == 0) {
					map.setIdTipoProveedor(-1);
				} else {
					map.setIdTipoProveedor(Integer.parseInt(dForm.getString("tipoProveedor")));
					map.setIdListTipoTramite(Integer.parseInt(dForm.getString("listTipoTramite")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoProveedor(-1);
			}
		}
		if (ent.isNombres()) {
			try {
				map.setNombres(dForm.getString("nombres"));
			} catch (NumberFormatException e) {
				map.setNombres(null);
			}
		}
		if (ent.isParentesco()) {
			try {
				map.setParentesco(dForm.getString("parentesco"));
			} catch (NumberFormatException e) {
				map.setParentesco(null);
			}
		}
		if (ent.isApellidos()) {
			try {
				map.setApellidos(dForm.getString("apellidos"));
			} catch (NumberFormatException e) {
				map.setApellidos(null);
			}
		}
		if (ent.isSexo()) {
			try {
				map.setSexo(dForm.getString("sexo"));
			} catch (NumberFormatException e) {
				map.setSexo(null);
			}
		}
		if (ent.isFechaNacimiento()) {
			try {
				map.setFechaNacimiento(Utilidad.StringToDate(dForm.getString("fechaNacimiento"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaNacimiento(null);
			} catch (ParseException e) {
				map.setFechaNacimiento(null);
			}
		}
		if (ent.isFechaOcurrencia()) {
			try {
				map.setFechaOcurrencia(Utilidad.StringToDate(dForm.getString("fechaOcurrencia"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaOcurrencia(new Date());
			} catch (ParseException e) {
				map.setFechaOcurrencia(new Date());
			}
		}
		if (ent.isFechaNotificacion()) {
			try {
				map.setFechaNotificacion(Utilidad.StringToDate(dForm.getString("fechaNotificacion"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaNotificacion(new Date());
			} catch (ParseException e) {
				map.setFechaNotificacion(new Date());
			}
		}
		if (ent.isMontoFactura()) {
			try {
				map.setMontoFactura(Double.parseDouble(dForm.getString("montoFactura")));
			} catch (NumberFormatException e) {
				map.setMontoFactura(0.0);
			}
		}
		if (ent.isSuma()) {
			try {
				map.setSuma(Double.parseDouble(dForm.getString("suma")));
			} catch (NumberFormatException e) {
				map.setSuma(0.0);
			}
		}
		if (ent.isMontoPresupuestado()) {
			try {
				map.setMontoPresupuestado(Double.parseDouble(dForm.getString("montoPresupuestado")));
			} catch (NumberFormatException e) {
				map.setMontoPresupuestado(0.0);
			}
		}
		if (ent.isMontoNegociado()) {
			try {
				map.setMontoNegociado(Double.parseDouble(dForm.getString("montoNegociado")));
			} catch (NumberFormatException e) {
				map.setMontoNegociado(0.0);
			}
		}
		if (ent.isMontoAmparado()) {
			try {
				map.setMontoAmparado(Double.parseDouble(dForm.getString("montoAmparado")));
			} catch (NumberFormatException e) {
				map.setMontoAmparado(0.0);
			}
		}
		if (ent.isFechaRecepcionFactura()) {
			try {
				map.setFechaRecepcionFactura(Utilidad.StringToDate(dForm.getString("fechaRecepcionFactura"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaRecepcionDeFactura(null);
			} catch (ParseException e) {
				map.setFechaRecepcionDeFactura(null);
			}
		}
		if (ent.isFechaFactura()) {
			try {
				map.setFechaFactura(Utilidad.StringToDate(dForm.getString("fechaFactura"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaFactura(null);
			} catch (ParseException e) {
				map.setFechaFactura(null);
			}
		}
		if (ent.isFechaLiquidacion()) {
			try {
				map.setFechaLiquidacion(Utilidad.StringToDate(dForm.getString("fechaLiquidacion"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaLiquidacion(null);
			} catch (ParseException e) {
				map.setFechaLiquidacion(null);
			}
		}
		if (ent.isFechaUltimaModificacion()) {
			try {
				map.setFechaUltimaModificacion(Utilidad.StringToDate(dForm.getString("fechaUltimaModificacion"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaUltimaModificacion(null);
			} catch (ParseException e) {
				map.setFechaUltimaModificacion(null);
			}
		}
		if (ent.isNumeroFactura()) {
			map.setNumeroFactura(dForm.getString("numeroFactura"));
		}
		if (ent.isPorcentajeIva()) {
			try {
				if (Integer.parseInt(dForm.getString("porcentajeIva")) == 0) {
					map.setPorcentajeIva(Double.parseDouble("0.0"));
				} else {
					map.setPorcentajeIva(Double.parseDouble(dForm.getString("porcentajeIva")));
				}
			} catch (NumberFormatException e) {
				map.setPorcentajeIva(Double.parseDouble(dForm.getString("porcentajeIva")));
			}			
		}
		if (ent.isListIva()) {
			map.setPorcentajeIva(Double.parseDouble(dForm.getString("listIva")));				
		}
		if (ent.isControlFactura()) {
			map.setControlFactura((dForm.getString("controlFactura")));
			if (ent.isMontoPagado()) {
				try {
					map.setmontopagado(Double.parseDouble(dForm.getString("montoPagado")));
				} catch (NumberFormatException e) {
					map.setmontopagado(0.0);
				}
			}
			if (ent.isMontoNoAmparado()) {
				try {
					map.setMontoNoAmparado(Double.parseDouble(dForm.getString("montoNoAmparado")));
				} catch (NumberFormatException e) {
					map.setMontoNoAmparado(0.0);
				}
			}
		}
		if (ent.isTipoGasto()) {
			try {
				if (Integer.parseInt(dForm.getString("tipoGasto")) == 0) {
					map.setIdTipoGasto(-1);
				} else {
					map.setIdTipoGasto(Integer.parseInt(dForm.getString("tipoGasto")));
				}
			} catch (NumberFormatException e) {
				map.setIdTipoGasto(-1);
			}
		}
		if (ent.isEspecialidad()) {
			try {
				if (Integer.parseInt(dForm.getString("especialidad")) == 0) {
					map.setIdEspecialidad(-1);
				} else {
					map.setIdEspecialidad(Integer.parseInt(dForm.getString("especialidad")));
				}
			} catch (NumberFormatException e) {
				map.setIdEspecialidad(-1);
			}
		}
		if (ent.isTipoSiniestro()) {
			try {
				map.setIdTipoSiniestro(Integer.parseInt(dForm.getString("tipoSiniestro")));
			} catch (NumberFormatException e) {
				map.setIdTipoSiniestro(-1);
			}
		}
		if (ent.isObservacion()) {
			map.setObservacion(dForm.getString("observacion"));
		}
		if (ent.isObservacionMedicinas()) {
			map.setObservacion(dForm.getString("observacion"));
		}
		if (ent.isDatosPersonales()) {
			Persona t = buscarTitularBeneficiario(request);// (Persona)
			// request.getSession().getAttribute(KEY_TITULAR);
			if (t != null) {
				map.setCedula(t.getCedula());
				map.setNombres(t.getNombres());
				map.setApellidos(t.getApellidos());
				map.setFechaNacimiento(t.getFechaNacimiento());
				map.setSexo(t.getSexo());
				// map.setEdad(t.getEdad());
				map.setCedulaBeneficiario(t.getBeneficiario().getCedula());
				map.setNombresBeneficiario(t.getBeneficiario().getNombres());
				map.setApellidosBeneficiario(t.getBeneficiario().getApellidos());
				map.setFechaNacimientoBeneficiario(t.getBeneficiario().getFechaNacimiento());
				map.setSexoBeneficiario(t.getBeneficiario().getSexo());
				// map.setEdadBeneficiario(t.getBeneficiario().getEdad());
				map.setParentesco(t.getBeneficiario().getParentesco());
				map.setIdParentesco(t.getBeneficiario().getId_parentesco());
				map.setIdTipoEmpleado(t.getIdTipoEmpleado());
			}
		}
		if (ent.isDetalleCobertura()) {
			Persona t = buscarTitularBeneficiario(request);// (Persona)
			// request.getSession().getAttribute(KEY_TITULAR);
			PerCobertura ps = new PerCobertura();
			List cobertura = new ArrayList();
			Cobertura cob = new Cobertura();
			List desgloseCobertura = new ArrayList();
			Mapa mapa = new Mapa();
			mapa.setCedula(t.getBeneficiario().getCedula());
			mapa.setCedulaBeneficiario(t.getCedula());
			mapa.getIdCobertura();
			if (t != null) {
				try {
					cobertura = ps.listByCedula(mapa);
					request.setAttribute("detalleMontoCobertura", cobertura);
					try {
						cob = ps.searchById(mapa.getIdCobertura());
						if (cob.isPorPatologia()) {
							desgloseCobertura = ps.listDesgloseCobertura(mapa);
							cob.setDesgloseCobertura(desgloseCobertura);
							request.setAttribute("desgloseCobertura", desgloseCobertura);
						}
					} catch (PersonalNotFoundException e) {
						log.error("error", e);
					} catch (SQLException e) {
						log.error("error", e);
					}
				} catch (Exception e) {
					log.error("error", e);
					request.setAttribute("detalleMontoCobertura", null);
				}
			}
		}
		if (ent.isMonto()) {
			map.setMonto(Double.parseDouble(dForm.getString("monto")));
		}
		
		/*if (ent.isProveedor()) {
			
			map.setIdProveedor(Integer.parseInt(dForm.getString("idProveedor")));
			
		}*/
		if (ent.isMontoCalculado()) {
			if (!"".equals(dForm.getString("montoCalculado"))) {
				map.setMontoPresupuestado(Double.parseDouble(dForm.getString("montoCalculado")));
			} else {
				map.setMontoPresupuestado(0);
			}
		}
		if (ent.isAnioSiniestro()) {
			map.setAnioSiniestro(getAnioBusqueda(request));
			if (request.getParameter("anioSiniestro") != null && !"".equals(dForm.getString("anioSiniestro"))) {
				map.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
			}
		}
		if (ent.isDetallePresupuesto() || ent.isDetallePresupuestoEdit()) {
			if (!"".equals(dForm.getString("montoHonorariosMedicos"))) {
				map.setMontoHonorariosMedicos(Double.parseDouble(dForm.getString("montoHonorariosMedicos")));
			} else {
				map.setMontoHonorariosMedicos(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosMedicosNoAmparado"))) {
				map.setMontoHonorariosMedicosNoAmparado(Double.parseDouble(dForm.getString("montoHonorariosMedicosNoAmparado")));
			} else {
				map.setMontoHonorariosMedicosNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosPresupuestado"))) {
				map.setMontoHonorariosMedicosPresupuestado(Double.parseDouble(dForm.getString("montoHonorariosPresupuestado")));
			} else {
				map.setMontoHonorariosMedicosPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosNegociado"))) {
				map.setMontoHonorariosNegociado(Double.parseDouble(dForm.getString("montoHonorariosNegociado")));
			} else {
				map.setMontoHonorariosNegociado(0);
			}
			if (!"".equals(dForm.getString("montoGastosClinicos"))) {
				map.setMontoGastosClinicos(Double.parseDouble(dForm.getString("montoGastosClinicos")));
			} else {
				map.setMontoGastosClinicos(0);
			}
			if (!"".equals(dForm.getString("montoGastosClinicosNoAmparado"))) {
				map.setMontoGastosClinicosNoAmparado(Double.parseDouble(dForm.getString("montoGastosClinicosNoAmparado")));
			} else {
				map.setMontoGastosClinicosNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoGastosPresupuestado"))) {
				map.setMontoGastosPresupuestado(Double.parseDouble(dForm.getString("montoGastosPresupuestado")));
			} else {
				map.setMontoGastosPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoGastosNegociado"))) {
				map.setMontoGastosNegociado(Double.parseDouble(dForm.getString("montoGastosNegociado")));
			} else {
				map.setMontoGastosNegociado(0);
			}
			if (!"".equals(dForm.getString("montoAmparado"))) {
				map.setMontoAmparado(Double.parseDouble(dForm.getString("montoAmparado")));
			} else {
				map.setMontoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoNoAmparado"))) {
				map.setMontoNoAmparado(Double.parseDouble(dForm.getString("montoNoAmparado")));
			} else {
				map.setMontoNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoPresupuestado"))) {
				map.setMontoPresupuestado(Double.parseDouble(dForm.getString("montoPresupuestado")));
			} else {
				map.setMontoPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoNegociado"))) {
				map.setMontoNegociado(Double.parseDouble(dForm.getString("montoNegociado")));
			} else {
				map.setMontoNegociado(0);
			}
		}
		if (ent.isDetallePresupuestoEmergencia() || ent.isDetallePresupuestoEditEmergencia()) {
			if (!"".equals(dForm.getString("montoExamenesEspeciales"))) {
				map.setMontoExamenesEspeciales(Double.parseDouble(dForm.getString("montoExamenesEspeciales")));
			} else {
				map.setMontoExamenesEspeciales(0);
			}
			if (!"".equals(dForm.getString("montoExamenesEspecialesNoAmparado"))) {
				map.setMontoExamenesEspecialesNoAmparado(Double.parseDouble(dForm.getString("montoExamenesEspecialesNoAmparado")));
			} else {
				map.setMontoExamenesEspecialesNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoExamenesEspecialesNegociado"))) {
				map.setMontoExamenesEspecialesNegociado(Double.parseDouble(dForm.getString("montoExamenesEspecialesNegociado")));
			} else {
				map.setMontoExamenesEspecialesNegociado(0);
			}
			if (!"".equals(dForm.getString("montoExamenesEspecialesPresupuestado"))) {
				map.setMontoExamenesEspecialesPresupuestado(Double.parseDouble(dForm.getString("montoExamenesEspecialesPresupuestado")));
			} else {
				map.setMontoExamenesEspecialesPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoFuneraria"))) {
				map.setMontoFuneraria(Double.parseDouble(dForm.getString("montoFuneraria")));
			} else {
				map.setMontoFuneraria(0);
			}
			if (!"".equals(dForm.getString("montoFunerariaNoAmparado"))) {
				map.setMontoFunerariaNoAmparado(Double.parseDouble(dForm.getString("montoFunerariaNoAmparado")));
			} else {
				map.setMontoFunerariaNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoFunerariaNegociado"))) {
				map.setMontoFunerariaNegociado(Double.parseDouble(dForm.getString("montoFunerariaNegociado")));
			} else {
				map.setMontoFunerariaNegociado(0);
			}
			if (!"".equals(dForm.getString("montoFunerariaPresupuestado"))) {
				map.setMontoFunerariaPresupuestado(Double.parseDouble(dForm.getString("montoFunerariaPresupuestado")));
			} else {
				map.setMontoFunerariaPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoAmbulancia"))) {
				map.setMontoAmbulancia(Double.parseDouble(dForm.getString("montoAmbulancia")));
			} else {
				map.setMontoAmbulancia(0);
			}
			if (!"".equals(dForm.getString("montoAmbulanciaNoAmparado"))) {
				map.setMontoAmbulanciaNoAmparado(Double.parseDouble(dForm.getString("montoAmbulanciaNoAmparado")));
			} else {
				map.setMontoAmbulanciaNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoAmbulanciaNegociado"))) {
				map.setMontoAmbulanciaNegociado(Double.parseDouble(dForm.getString("montoAmbulanciaNegociado")));
			} else {
				map.setMontoAmbulanciaNegociado(0);
			}
			if (!"".equals(dForm.getString("montoAmbulanciaPresupuestado"))) {
				map.setMontoAmbulanciaPresupuestado(Double.parseDouble(dForm.getString("montoAmbulanciaPresupuestado")));
			} else {
				map.setMontoAmbulanciaPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosMedicos"))) {
				map.setMontoHonorariosMedicos(Double.parseDouble(dForm.getString("montoHonorariosMedicos")));
			} else {
				map.setMontoHonorariosMedicos(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosMedicosNoAmparado"))) {
				map.setMontoHonorariosMedicosNoAmparado(Double.parseDouble(dForm.getString("montoHonorariosMedicosNoAmparado")));
			} else {
				map.setMontoHonorariosMedicosNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosPresupuestado"))) {
				map.setMontoHonorariosMedicosPresupuestado(Double.parseDouble(dForm.getString("montoHonorariosPresupuestado")));
			} else {
				map.setMontoHonorariosMedicosPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoHonorariosNegociado"))) {
				map.setMontoHonorariosNegociado(Double.parseDouble(dForm.getString("montoHonorariosNegociado")));
			} else {
				map.setMontoHonorariosNegociado(0);
			}
			if (!"".equals(dForm.getString("montoGastosClinicos"))) {
				map.setMontoGastosClinicos(Double.parseDouble(dForm.getString("montoGastosClinicos")));
			} else {
				map.setMontoGastosClinicos(0);
			}
			if (!"".equals(dForm.getString("montoGastosClinicosNoAmparado"))) {
				map.setMontoGastosClinicosNoAmparado(Double.parseDouble(dForm.getString("montoGastosClinicosNoAmparado")));
			} else {
				map.setMontoGastosClinicosNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoGastosPresupuestado"))) {
				map.setMontoGastosPresupuestado(Double.parseDouble(dForm.getString("montoGastosPresupuestado")));
			} else {
				map.setMontoGastosPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoGastosNegociado"))) {
				map.setMontoGastosNegociado(Double.parseDouble(dForm.getString("montoGastosNegociado")));
			} else {
				map.setMontoGastosNegociado(0);
			}
			if (!"".equals(dForm.getString("montoAmparado"))) {
				map.setMontoAmparado(Double.parseDouble(dForm.getString("montoAmparado")));
			} else {
				map.setMontoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoNoAmparado"))) {
				map.setMontoNoAmparado(Double.parseDouble(dForm.getString("montoNoAmparado")));
			} else {
				map.setMontoNoAmparado(0);
			}
			if (!"".equals(dForm.getString("montoPresupuestado"))) {
				map.setMontoPresupuestado(Double.parseDouble(dForm.getString("montoPresupuestado")));
			} else {
				map.setMontoPresupuestado(0);
			}
			if (!"".equals(dForm.getString("montoNegociado"))) {
				map.setMontoNegociado(Double.parseDouble(dForm.getString("montoNegociado")));
			} else {
				map.setMontoNegociado(0);
			}
		}
		if (ent.isMontoGastosClinicos()) {
			if (!"".equals(dForm.getString("montoGastosClinicos"))) {
				map.setMontoGastoClinico(Double.parseDouble(dForm.getString("montoGastosClinicos")));
			} else {
				map.setMontoGastoClinico(0);
			}
		}
		if (ent.isMontoHonorariosMedicos()) {
			if (!"".equals(dForm.getString("montoHonorariosMedicos"))) {
				map.setMontoHonorarioMedico(Double.parseDouble(dForm.getString("montoHonorariosMedicos")));
			} else {
				map.setMontoHonorarioMedico(0);
			}
		}
		if (ent.isMontoAmbulancia()) {
			if (!"".equals(dForm.getString("montoAmbulancia"))) {
				map.setMontoAmbulancia(Double.parseDouble(dForm.getString("montoAmbulancia")));
			} else {
				map.setMontoAmbulancia(0);
			}
		}
		if (ent.isMontoFuneraria()) {
			if (!"".equals(dForm.getString("montoFuneraria"))) {
				map.setMontoFuneraria(Double.parseDouble(dForm.getString("montoFuneraria")));
			} else {
				map.setMontoFuneraria(0);
			}
		}
		if (ent.isMontoExamenesEspeciales()) {
			if (!"".equals(dForm.getString("montoExamenesEspeciales"))) {
				map.setMontoExamenesEspeciales(Double.parseDouble(dForm.getString("montoExamenesEspeciales")));
			} else {
				map.setMontoExamenesEspeciales(0);
			}
		}
		if (ent.isMontoExamenesPreoperatorios()) {
			if (!"".equals(dForm.getString("montoExamenesPreoperatorios"))) {
				map.setMontoExamenesPreoperatorios(Double.parseDouble(dForm.getString("montoExamenesPreoperatorios")));
			} else {
				map.setMontoExamenesPreoperatorios(0);
			}
		}
		if (ent.isMontoMaterialMedicoConIva()) {
			if (!"".equals(dForm.getString("montoMaterialMedicoConIva"))) {
				map.setMontoMaterialMedicoConIva(Double.parseDouble(dForm.getString("montoMaterialMedicoConIva")));
			} else {
				map.setMontoMaterialMedicoConIva(0);
			}
		}
		if (ent.isMontoMaterialMedicoSinIva()) {
			if (!"".equals(dForm.getString("montoMaterialMedicoSinIva"))) {
				map.setMontoMaterialMedicoSinIva(Double.parseDouble(dForm.getString("montoMaterialMedicoSinIva")));
			} else {
				map.setMontoMaterialMedicoSinIva(0);
			}
		}
		if (ent.isCobertura() || ent.isCoberturaRangoFecha()) {
			map.setIdCobertura(Integer.parseInt(dForm.getString("cobertura")));
		}
		if (ent.isCriterioBusqueda()) {
			if (!"".equals(dForm.getString("nombres"))) {
				map.setNombres(dForm.getString("nombres"));
			}
			if (!"".equals(dForm.getString("cedula"))) {
				map.setCedula(dForm.getString("cedula"));
			}
			if (!"".equals(dForm.getString("codigo"))) {
				map.setCodigo(dForm.getString("codigo"));
			}
		}
		if (ent.isFechaEgreso()) {
			try {
				map.setFechaEgreso(Utilidad.StringToDate(dForm.getString("fechaEgreso"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaEgreso(null);
			} catch (ParseException e) {
				map.setFechaEgreso(null);
			}
		}
		if (ent.isFechaIngreso()) {
			try {
				map.setFechaIngreso(Utilidad.StringToDate(dForm.getString("fechaIngreso"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaIngreso(null);
			} catch (ParseException e) {
				map.setFechaIngreso(null);
			}
		}
		if (ent.isFechaEgresoRequerida()) {
			try {
				map.setFechaEgreso(Utilidad.StringToDate(dForm.getString("fechaEgreso"), "dd/MM/yyyy"));
			} catch (NumberFormatException e) {
				map.setFechaEgreso(null);
			} catch (ParseException e) {
				map.setFechaEgreso(null);
			}
		}
		if (ent.isCitaPreOperatorio()) {
			if (!"".equals(dForm.getString("citaPreOperatorio"))) {
				map.setCitaPreOperatorio(Boolean.parseBoolean(request.getParameter("citaPreOperatorio")));
			}
		}
		if (ent.isCitaPostOperatorio()) {
			if (!"".equals(dForm.getString("citaPostOperatorio"))) {
				map.setCitaPostOperatorio(Boolean.parseBoolean(request.getParameter("citaPostOperatorio")));
			}
		}
		if (ent.isRechazado()) {
			map.setRechazo((Boolean) dForm.get("rechazo"));
		}
		return map;
	}

	protected void validarAction(HttpServletRequest request, ActionForm form, Entorno ent, ActionMessages am, Class clase) throws PersonalNotFillItems, PersonalNotFoundException, SQLException {
		request.setAttribute("form_action", request.getServletPath());
		setLogger(clase.getClass());
		setEntorno(request, form, ent);
		validarFiltros(request, form, ent, am);
	}

	protected void setTipoProveedor(HttpServletRequest request) {
		String idtt = (String) request.getAttribute(KEY_TIPO_TRAMITE);		
		
		String list = LIST_TIPO_PROVEEDOR;
		PerTipoProveedor per = new PerTipoProveedor();
		try {
			if (idtt != null) {
				request.setAttribute(list, per.listByTipoTramite(Integer.parseInt(idtt)));
				request.setAttribute("idtt", idtt);
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}
	
	protected void setTipoProveedorP(HttpServletRequest request) {
		String idtt = (String) request.getAttribute(KEY_TIPO_TRAMITE);		
		
		String list = LIST_TIPO_PROVEEDOR;
		PerTipoProveedor per = new PerTipoProveedor();
		try {
			if (idtt != null) {
				request.setAttribute(list, per.listByTipoTramite(Integer.parseInt(idtt)));
				request.setAttribute("idtt", idtt);
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}
	
	
	
	/*Metodo para buscar los proveedores de farmacia*/
	
	protected void setTipoProveedorCombo(HttpServletRequest request) {
		int idtt = TIPO_PROVEEDOR_FARMACIA;
		HashMap hm = new HashMap();
		String list = LIST_TIPO_PROVEEDOR_COMBO;
		
		PerProveedor per = new PerProveedor();
		try {
			if (idtt != 0) {
				request.setAttribute(list, per.porCombo(idtt));
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}
	/*************************************************/

	protected void setListTipoProveedormbls(HttpServletRequest request) {
		String idtt = (String) request.getAttribute(KEY_TIPO_TRAMITE);
		String list = LIST_TIPO_PROVEEDORMBLS;
		PerTipoProveedor per = new PerTipoProveedor();
		try {
			if (idtt != null) {
				request.setAttribute(list, per.listByTipoTramiteR(Integer.parseInt(idtt)));
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setTipoSiniestro(HttpServletRequest request) {
		String idtt = (String) request.getAttribute(KEY_TIPO_TRAMITE);
		String list = LIST_TIPO_SINIESTRO;
		PerTipoSiniestro per = new PerTipoSiniestro();
		try {
			request.setAttribute(list, per.listByTipoTramite(Integer.parseInt(idtt)));
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setEspecialidad(HttpServletRequest request) {
		String list = LIST_ESPECIALIDAD;
		PerEspecialidad per = new PerEspecialidad();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setOrgano(HttpServletRequest request) {
		String list = LIST_ORGANO;
		String esp = null;
		try {
			esp = request.getParameter("especialidad");
		} catch (Exception e) {
		}
		PerOrgano per = new PerOrgano();
		try {
			if (esp != null) {
				request.setAttribute(list, per.porEspecialidad(Integer.parseInt(esp)));
			} else {
				request.setAttribute(list, new ArrayList());
			}
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	@SuppressWarnings("unchecked")
	protected void setPatologias(HttpServletRequest request) {
		String list = LIST_PATOLOGIAS;
		HashMap hm = new HashMap();
		String org = null;
		String esp = null;
		
	
		try {
			org = request.getParameter("organo");
			esp = request.getParameter("especialidad");
		} catch (Exception e) {
		}
		PerPatologias per = new PerPatologias();
		try {
			if ((esp != null) && (org != null)) {
				hm.put("idOrgano", org);
				hm.put("idEspecialidad", esp);
				request.setAttribute(list, per.porOrganoEspecialidad(hm));
			} else {
				request.setAttribute(list, new ArrayList());
			}
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	@SuppressWarnings("unchecked")
	protected void setTratamiento(HttpServletRequest request) {
		String list = LIST_TRATAMIENTO;
		HashMap hm = new HashMap();
		String esp = null;
		String org = null;
		String pat = null;
		try {
			esp = request.getParameter("especialidad");
			org = request.getParameter("organo");
			pat = request.getParameter("patologias");
			PerTratamiento per = new PerTratamiento();
			try {
				if (esp != null && org != null && pat != null) {
					hm.put("idEspecialidad", Integer.parseInt(esp));
					hm.put("idOrgano", Integer.parseInt(org));
					hm.put("idPatologia", Integer.parseInt(pat));
					request.setAttribute(list, per.porEspOrgPat(hm));
				} else {
					request.setAttribute(list, new ArrayList());
				}
			} catch (PersonalNotFoundException e) {
				request.setAttribute(list, new ArrayList());
			} catch (SQLException e) {
				request.setAttribute(list, new ArrayList());
			}
		} catch (Exception e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	@SuppressWarnings("unchecked")
	protected void setTratamiento2(HttpServletRequest request) {
		String list = LIST_TRATAMIENTO;
		HashMap hm = new HashMap();
		String esp = null;
		String org = null;
		String pat = null;
		try {
			esp = request.getParameter("especialidad");
			org = request.getParameter("organo");
			pat = request.getParameter("patologias");
			PerTratamiento per = new PerTratamiento();
			try {
				if ((esp != null && !"-1".equals(esp)) && (org != null && !"-1".equals(org)) && (pat != null && !"-1".equals(pat))) {
					hm.put("idEspecialidad", Integer.parseInt(esp));
					hm.put("idOrgano", Integer.parseInt(org));
					hm.put("idPatologia", Integer.parseInt(pat));
					request.setAttribute(list, per.porEspOrgPat2(hm));
				} else {
					request.setAttribute(list, new ArrayList());
				}
			} catch (PersonalNotFoundException e) {
				request.setAttribute(list, new ArrayList());
			} catch (SQLException e) {
				request.setAttribute(list, new ArrayList());
			}
		} catch (Exception e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setTipoEnfermedad(HttpServletRequest request) {
		String list = LIST_TIPO_ENFERMEDAD;
		PerTipoEnfermedad per = new PerTipoEnfermedad();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setTipoTratamiento(HttpServletRequest request) {
		String list = LIST_TIPO_TRATAMIENTO;
		PerTipoTratamiento per = new PerTipoTratamiento();
		try {
			request.setAttribute(list, per.searchTipoTratamiento());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setListTipoTramite(HttpServletRequest request) {
		String list = LIST_TIPO_LIST_TRAMITE;
		PerTipoTramite per = new PerTipoTramite();
		try {
			request.setAttribute(list, per.searchTipoTramite());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	/*
	 * protected void setListTipoProveedorrmbls(HttpServletRequest request) {
	 * String list = LIST_TIPO_PROVEEDORRMBLS; PerTipoProveedorrmbls per = new
	 * PerTipoProveedorrmbls(); try { request.setAttribute(list,
	 * per.searchTipoProveedorrmbls()); } catch (PersonalNotFoundException e) {
	 * request.setAttribute(list, new ArrayList()); } catch (SQLException e) {
	 * request.setAttribute(list, new ArrayList()); } }
	 */
	protected void setEstatus(HttpServletRequest request) {
		/*
		 * String list = LIST_ESTATUS; PerEstatus per = new PerEstatus(); try {
		 * //request.setAttribute(list, per.listEditables()); } catch
		 * (PersonalNotFoundException e) { request.setAttribute(list, new
		 * ArrayList()); } catch (SQLException e) { log.error("error", e);
		 * request.setAttribute(list, new ArrayList()); }
		 */
		String idtt = (String) request.getAttribute(KEY_TIPO_TRAMITE);
		String list = LIST_ESTATUS;
		PerEstatus per = new PerEstatus();
		try {
			request.setAttribute(list, per.listByTipoTramite(Integer.parseInt(idtt)));
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setAnioBusqueda(HttpServletRequest request) {
		Calendar c = Calendar.getInstance();
		List<AnioBusqueda> list = new ArrayList<AnioBusqueda>();
		int anioInicial = 2010;
		int anioActual = c.get(Calendar.YEAR);

		for (int i = anioActual; i >=anioInicial ; i--) {
			AnioBusqueda ab = new AnioBusqueda();
			ab.setId(String.valueOf(i));
			ab.setDescripcion(String.valueOf(i));
			list.add(ab);
		}
		request.setAttribute(LIST_ANIO_BUSQUEDA, list);
	}

	protected void setTipoEnfermedad2(HttpServletRequest request) {
		String list = LIST_TIPO_ENFERMEDAD;
		PerTipoEnfermedad per = new PerTipoEnfermedad();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setTipoGasto(HttpServletRequest request) {
		String list = LIST_TIPO_GASTO;
		PerTipoGasto per = new PerTipoGasto();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}
	
	protected void setListIva(HttpServletRequest request) {
				
		String list = LIST_PORCENTAJE_IVA;				
		PerImpuesto per = new PerImpuesto();		
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}			
	}	

	protected void setTipoCobertura(HttpServletRequest request) {
		String list = LIST_TIPO_COBERTURA;
		PerTipoCobertura per = new PerTipoCobertura();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setTipoEmpleado(HttpServletRequest request) {
		String list = LIST_EMPLEADO;
		PerTipoEmpleado per = new PerTipoEmpleado();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setPoliza(HttpServletRequest request) {
		String list = LIST_POLIZA;
		PerPoliza per = new PerPoliza();
		try {
			request.setAttribute(list, per.list());
		} catch (PersonalNotFoundException e) {
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setCobertura(HttpServletRequest request) {
		String list = LIST_COBERTURA;
		int idtt = -1;
		try {
			idtt = Integer.parseInt((String) request.getAttribute(KEY_TIPO_TRAMITE));
		} catch (Exception e) {
			log.error("error", e);
		}
		PerCobertura per = new PerCobertura();
		try {
			if (idtt != -1) {
				request.setAttribute(list, per.listByTipoTramite(idtt));
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			log.info("no encontrado", e);
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			log.error("error", e);
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void setCoberturaRangoFecha(HttpServletRequest request, Date fOcurrencia) {
		String list = LIST_COBERTURA;
		HashMap<String, Object> hm = new HashMap<String, Object>();
		PerCobertura per = new PerCobertura();
		int idtt = -1;
		try {
			idtt = Integer.parseInt((String) request.getAttribute(KEY_TIPO_TRAMITE));
		} catch (Exception e) {
			log.error("error", e);
		}
		hm.put("idTipoTramite", idtt);
		hm.put("fechaOcurrencia", fOcurrencia);
		try {
			if (idtt != -1) {
				request.setAttribute(list, per.listByTipoTramiteRangoFecha(hm));
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			log.info("no encontrado", e);
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			log.error("error", e);
			request.setAttribute(list, new ArrayList());
		}
	}

	protected void isEditable(Mapa mapa) throws PersonalNotEditableException {
		// metodo creado para verificar qué siniestros se pueden editar, según
		// la reglas de negocio
		log.info("verifico si es editable entre en la funci�n");
		switch (mapa.getIdTipoTramite()) {
		case COD_TIPO_TRAMITE_CARTAAVAL:
			// hago validaciones
			if (mapa.getIdEstatus() == COD_ESTATUS_LIQUIDADO || mapa.getIdEstatus() == COD_ESTATUS_INGRESADO || mapa.getIdEstatus() == COD_ESTATUS_RECHAZADO || mapa.getIdEstatus() == COD_ESTATUS_ANULADO || mapa.getIdEstatus() == COD_ESTATUS_EGRESADO) {
				throw new PersonalNotEditableException();
			}
			break;
		default:
			break;
		}
	}

	protected void findDisponible(Mapa mapa, Date fechaSiniestro) throws CoberturaNotDisponibleException, PersonalNotFoundException, SQLException {
		PerCobertura perCob = new PerCobertura();
		PatologiaOrganoTratamiento pot = new PatologiaOrganoTratamiento();
		PerPatologiaOrganoTratamiento perPot = new PerPatologiaOrganoTratamiento();
		Organo org = new Organo();
		PerOrgano perOrg = new PerOrgano();
		Cobertura cob = new Cobertura();
		double coberturaAsegurado = 0.0;
		double coberturaTipo = 0.0;
		double coberturaPatologia = 0.0;
		double montoSiniestro = 0.0;
		boolean disponible;
		// Aplica cuando se est� editando
		if (fechaSiniestro != null) {
			try {
				mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(fechaSiniestro, "yyyy")));
			} catch (NumberFormatException e) {
				log.error("error", e);
			} catch (PersonalException e) {
				log.error("error", e);
			}
		}
		if (mapa.getMontoSiniestro() != 0) {
			montoSiniestro = mapa.getMontoSiniestro();
		}
		// Busco la cobertura
		cob = perCob.searchById(mapa.getIdCobertura());
		// Busco el tope de la cobertua por el tipo seleccionado
		coberturaTipo = perCob.search(mapa.getIdCobertura());
		// Si la cobertura es por tipo de cobertura y no por orgagano, hacer lo
		// siguiente
		log.info(((coberturaAsegurado - montoSiniestro) + mapa.getMontoAmparado()) > (Double.valueOf(coberturaTipo)));
		if (cob.isPorPatologia() == false) {
			coberturaAsegurado = perCob.coberturaAsegurado(mapa);
			log.info("anioSiniestro " + mapa.getAnioSiniestro());
			log.info("anioSiniestro " + mapa.getCedulaBeneficiario());
			log.info("anioSiniestro " + mapa.getIdCobertura());

			log.info("coberturaTipo " + coberturaTipo);
			log.info("coberturaAsegurado " + coberturaAsegurado);
			log.info("mapa.getMontoAmparado() " + mapa.getMontoAmparado());
			log.info("montoSiniestro " + mapa.getMontoSiniestro());
			if ((coberturaTipo - ((coberturaAsegurado + mapa.getMontoAmparado()) - montoSiniestro)) < 0) {

				log.info("error 1");
				throw new CoberturaNotDisponibleException();
			}
		}
		// si es por patologia hacer lo siguiente
		else {
			// Busco cual es la patologia
			pot = perPot.search(mapa.getPatologiaOrganoTratamiento());
			mapa.setIdPatologia(pot.getPatologia().getId());
			// busco la cobertura para el organ seleccionado
			coberturaPatologia = perCob.CoberturaPatologia(mapa);
			if ((coberturaPatologia - montoSiniestro) + mapa.getMontoAmparado() > coberturaTipo) {
				throw new CoberturaNotDisponibleException();
			}
		}
	}

	protected void permitido(Mapa mapa) throws SiniestroNotPermittedException, PersonalNotFoundException, SQLException {
		if (mapa.getIdCobertura() == COD_TIPO_COBERTURA_MATERNIDAD) {
			if (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_TITULAR) {
				if (COD_SEXO_MASCULINO.equals(mapa.getSexo())) {
					throw new SiniestroNotPermittedException();
				}
			} else {
				if (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_CONYUGE) {
					if (COD_SEXO_MASCULINO.equals(mapa.getSexoBeneficiario())) {
						throw new SiniestroNotPermittedException();
					}
				} else {
					throw new SiniestroNotPermittedException();
				}
			}
		}
		if (mapa.getIdCobertura() == COD_TIPO_COBERTURA_ACCIDENTES_PERSONALES) {
			if (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_TITULAR) {
			} else {
				throw new SiniestroNotPermittedException();
			}
		}
		if (mapa.getIdCobertura() == COD_TIPO_COBERTURA_ENFERMEDADES_MENTALES) {
			if (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_TITULAR) {
			} else {
				throw new SiniestroNotPermittedException();
			}
		}
		if (mapa.getIdCobertura() == COD_TIPO_COBERTURA_VIH) {
			if (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_TITULAR) {
			} else {
				throw new SiniestroNotPermittedException();
			}
		}
		/*
		 * if (mapa.getIdCobertura() == COD_TIPO_COBERTURA_FUNERARIOS) { if
		 * (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_TITULAR) { } else {
		 * throw new SiniestroNotPermittedException(); } }
		 */
		if (mapa.getIdCobertura() == COD_TIPO_COBERTURA_VIDA) {
			if (mapa.getIdParentesco() == COD_TIPO_PARENTESCO_TITULAR) {
			} else {
				throw new SiniestroNotPermittedException();
			}
		}
	}

	protected Persona buscarTitularBeneficiario(HttpServletRequest request) throws PersonalNotFoundException, SQLException {
		PerPersona perp = new PerPersona();
		Persona t = null;
		String ttt="";
		String bb="";
		int valida=0;
		String validaa="";
		String telefonon="no";
		HashMap<String, String> hm = new HashMap<String, String>();
		try{
		validaa=String.valueOf(request.getAttribute("valida_emergencia"));
		valida=Integer.parseInt(validaa);
		telefonon=String.valueOf(request.getAttribute("telefonon"));

		}
		catch(Exception e){
			
		}
		if ("null".equals(telefonon)||telefonon=="null"){
			
			hm.put("cedulaT", request.getParameter("requestCedTitular"));
			hm.put("cedula", request.getParameter("requestCedBeneficiario"));
			t = perp.buscar(request.getParameter("requestCedTitular"));
		}
		else {
		if (valida==1){
			
			ttt=String.valueOf(request.getAttribute("requestCedTitular"));
			bb= String.valueOf(request.getAttribute("requestCedBeneficiario"));
			
		hm.put("cedulaT", ttt);
		hm.put("cedula", bb);
		t = perp.buscar(ttt);
		}else {

			hm.put("cedulaT", request.getParameter("requestCedTitular"));
			hm.put("cedula", request.getParameter("requestCedBeneficiario"));
			t = perp.buscar(request.getParameter("requestCedTitular"));
		}}
		// (Persona)
		// request.getSession().getAttribute(KEY_TITULAR);
	
		t.setBeneficiario(perp.buscarBeneficiario(hm));
		request.setAttribute(KEY_TITULAR, t);
		return t;
	}
	
	
	protected void setCoberturaParaModificar(HttpServletRequest request, int idPolizaActual, int idPolizaVieja) {
		String list = LIST_COBERTURA;
		HashMap<String, Object> hm = new HashMap<String, Object>();
		int polizasViejas[] = new int[2];
		// polizasViejas[0] = idPolizaActual;
		polizasViejas[1] = idPolizaVieja;
		int idtt = -1;
		try {
			idtt = Integer.parseInt((String) request.getAttribute(KEY_TIPO_TRAMITE));
		} catch (Exception e) {
			log.error("error", e);
		}
		hm.put("idTipoTramite", idtt);
		hm.put("polizaVieja", polizasViejas);
		PerCobertura per = new PerCobertura();
		try {
			if (idtt != -1) {
				request.setAttribute(list, per.listByTipoTramiteModificar(hm));
			} else {
				request.setAttribute(list, per.list());
			}
		} catch (PersonalNotFoundException e) {
			log.info("no encontrado", e);
			request.setAttribute(list, new ArrayList());
		} catch (SQLException e) {
			log.error("error", e);
			request.setAttribute(list, new ArrayList());
		}
	}

	protected boolean validaAnioOcurrenciaPoliza(Date fOcurrencia, Date fPoliza) {
		int anioOcurrencia = 0;
		int anioPoliza = 0;
		try {
			anioOcurrencia = Integer.parseInt(Utilidad.DateToString(fOcurrencia, "yyyy"));
			anioPoliza = Integer.parseInt(Utilidad.DateToString(fPoliza, "yyyy"));
		} catch (Exception e) {
			log.error("error ", e);
			return false;
		}
		if (anioOcurrencia != anioPoliza) {
			return false;
		} else {
			return true;
		}
	}
}

package ve.gob.dem.fasdem.action.liquidacion;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.action.aps.liquidacion.LiquidarAps;
import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.valores.CargaValores;
import ve.gob.dem.fasdem.valores.Valores;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class LiquidaMedicinas extends GenericAction {
	   static protected Logger log = Logger.getLogger(LiquidaMedicinas.class);
	   @Override
	   public ActionForward execute(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
	       ActionMessages am = new ActionMessages();
	       Valores v = CargaValores.getInstance().getValores();
	   		DynaActionForm dForm = (DynaActionForm) form;
	       Entorno ent = new Entorno(Entorno.MOD_MEDICINAS_LIQUIDACION);
	       request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_MEDICINAS);
	       Mapa mapa = new Mapa();
	       PerSiniestro perSiniestro = new PerSiniestro();
	       PerFactura perFactura = new PerFactura();
	       // PerFactura pdf = new PerFactura();
	       Date fechahoy = new Date();
	       Calendar f = Calendar.getInstance();
	       Factura factura = new Factura();
	       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	       f.setTime(fechahoy);
	       Siniestro s = new Siniestro();
	       // Factura siniestrofactura = new Factura();
	       Factura detalleFactura = new Factura();
	       // List listFactura = new ArrayList();
	       // List listDetalle = new ArrayList();
	       formato.format(f.getTime());
	       String fecha = formato.format(f.getTime());
	       // int idSiniestroFactura = 0;
	       int idFactura = 0;
	       int idDetalleFactura = 0;
	       PerMotivoEstatus perMotEst = new PerMotivoEstatus();
	       MotivoEstatus motEst = new MotivoEstatus();
	       int idSiniestro = Integer.parseInt(request.getParameter("id"));
	       Mapa m = new Mapa();
	       
	       m = getDForm(request, form, ent);
	 dForm.set("tipoProveedor",(String.valueOf(TIPO_PROVEEDOR_FARMACIA)));
	       //mapa.setIdSiniestro(idSiniestro);
	       m.setIdSiniestro(Integer.parseInt(request.getParameter("id")));
	 //mapa.setIdSiniestro(Integer.parseInt(request.getParameter("id")));
	       //m.setAnioSiniestro(getAnioBusqueda(request));
	       
	       //Se debe buscar con el aÃ±o del siniestro y no que el actual
	       log.info("anioSini request " + 
	request.getParameter("anioSiniestro"));
	       log.info("anioSini sessiom " + 
	request.getSession().getAttribute("anioSiniestro"));
	       
	       if(request.getParameter("anioSiniestro")!=null && 
	!"".equals(request.getParameter("anioSiniestro")))
	       {
	           log.info("anioSini request " + 
	request.getParameter("anioSiniestro"));
	           request.getSession().setAttribute("anioSiniestro", 
	request.getParameter("anioSiniestro"));
	          

	m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")
));
	       }
	       
	       
	 if(request.getSession().getAttribute("anioSiniestro")!=null && 

	!"".equals(String.valueOf(request.getSession().getAttribute("anioSiniestro"))))
	       {
	          

	m.setAnioSiniestro(Integer.parseInt(String.valueOf(request.getSession().getAttribute("anioSiniestro"))));
	       }
	       
	       
	       
	       log.info("anio en map " + m.getAnioSiniestro());
	       log.info("anio en map333 " + m.getAnioSiniestro());
	       Siniestro Op = perSiniestro.search(m);
	       request.setAttribute("Op", Op);
	       // double montopresupuestado = 0.0;
	       // double montoamparado = 0.00;
	       // montopresupuestado = Op.getMontoPresupuestado();
	      // DynaActionForm dForm = (DynaActionForm) form;
	       try {
	           factura = perFactura.searchIdSiniestro(m);
	           mapa.setId(factura.getId());
	           mapa.setIdFactura(factura.getId());
	          
	           if (factura.getPreOrden() != null) {
	               am.add(ALERT_AVISOS, new 
	ActionMessage("env.general.enpreorden"));
	               saveMessages(request, am);
	    
	               
	               return mapping.findForward(FWD_INPUT);
	           }
	           log.info("dform" );
	           dForm.set("numeroFactura", 
	String.valueOf(factura.getNumeroFactura()));
	           dForm.set("controlFactura", 
	String.valueOf(factura.getControlFactura()));
	           dForm.set("fechaFactura", 
	Utilidad.DateToString(factura.getFechaFactura(), "dd/MM/yyyy"));
	           dForm.set("fechaRecepcionFactura", 
	Utilidad.DateToString(factura.getFechaRecepcionFactura(), "dd/MM/yyyy"));
	           dForm.set("montoFactura", 
	String.valueOf(factura.getMontoFactura()));
	           dForm.set("tipoProveedor",TIPO_PROVEEDOR_FARMACIA);
	           log.info("heyyyyyyyyyyyyyyyy"+TIPO_PROVEEDOR_FARMACIA);
	           dForm.set("idProveedor", new 
	Integer(Op.getProveedor().getId()).toString());
	           dForm.set("proveedor", Op.getProveedor().getDescripcion());
	       } catch (Exception e) {
	    	   if ("".equals(request.getParameter("numeroFactura"))) {
	               am.add(ALERT_AVISOS, new 
	            		   ActionMessage("env.general.norecepcion"));
	               saveMessages(request, am);
	               factura = new Factura();
	           }
	    	  
	       }
	       dForm.set("observacion", 
	String.valueOf(Op.getObservacionReporte()));
	       try {
	           validarAction(request, form, ent, am, this.getClass());
	       } catch (PersonalNotFillItems e) {
	           return mapping.findForward(FWD_INPUT);
	       }
	       mapa = getDForm(request, form, ent);
	       mapa.setId(idSiniestro);
	       mapa.setIdSiniestro(idSiniestro);
	       mapa.setFechaLiquidacion(Utilidad.StringToDate(fecha, 
	"dd/MM/yyyy"));
	       mapa.setIssFactura(true);
	       //AÃ±o del siniestro, en ves de actual
	      

	mapa.setAnioSiniestro(Integer.parseInt(String.valueOf(request.getSession().getAttribute("anioSiniestro")))); 
 
	    
	       //mapa.setAnioSiniestro(getAnioBusqueda(request));
	       mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
	       mapa.setIdTipoGasto(COD_TIPO_GASTO_SERVICIOS_FARMACIA);
	       mapa.setPorcentajeIva(v.getIva());
	       mapa.setPorcentajeTimbre(v.getTimbreFiscal());
	       mapa.setPorcentajeIsrl(v.getIslr());
	       mapa.setMontoPresupuestado(Op.getMontoPresupuestado());
	       mapa.setSiniestro(Op.getSiniestro());
	       PerEstatus perEst = new PerEstatus();
	       Estatus est = new Estatus();
	       try {
	           est = perEst.buscar(COD_ESTATUS_LIQUIDADO);
	           mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
	           mapa.setIdSiniestro(mapa.getId());
	           try {
	               motEst = perMotEst.searchByEstatus(mapa);
	               // Si consigo un estatus de ese tipo lo finalizo y creo
	               // otro
	               perMotEst.finalizaTodosEstatus(motEst.getId());
	               // ******INSERTAR TRAZA
	           } catch (PersonalNotFoundException e) {
	           }
	           motEst.setDescripcion(request.getParameter("observacion"));
	           motEst.setIdSiniestro(mapa.getId());
	          
	motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
	           motEst.setIdUsuario(usuarioSession(request).getCedula());
	           motEst.setIdEstatus(mapa.getIdEstatus());
	           perMotEst.insert(motEst);
	           // ******INSERTAR TRAZA
	       } catch (Exception e) {
	       }
	      

	mapa.setMontoNegociado(Double.parseDouble(request.getParameter("montoFactura")));
	      

	mapa.setMontoFactura(Double.parseDouble(request.getParameter("montoFactura")));
	      

	mapa.setMontoAmparado(Double.parseDouble(request.getParameter("montoAmparado")));
	       mapa.setNumeroFactura(request.getParameter("numeroFactura"));
	       mapa.setControlFactura(request.getParameter("controlFactura"));
	      

	mapa.setFechaFactura(Utilidad.StringToDate(request.getParameter("fechaFactura"), 

	"dd/MM/yyyy"));
	      

	mapa.setFechaRecepcionFactura(Utilidad.StringToDate(request.getParameter("fechaRecepcionFactura"), 

	"dd/MM/yyyy"));
	 log.info("blgik aqui "+request.getParameter("idProveedor"));
	      
/*AQUI CAPTURO EL ID DEL PROVEEDOR*/
	//mapa.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
	 mapa.setIdProveedor(Integer.parseInt(request.getParameter("proveedorCombo")));
	 log.info("aqui es la validacion duplicidad"+request.getParameter("proveedorCombo"));
	       double montoNoAmparado = mapa.getMontoFactura() - 
	mapa.getMontoAmparado();
	       mapa.setMontoNoAmparado(montoNoAmparado);
	       double amparado = mapa.getMontoAmparado();
	       double presupuestado = Op.getMontoPresupuestado();
	       if (amparado > presupuestado) {
	           am.add(ALERT_AVISOS, new 
	ActionMessage("env.general.validacionliquidacion"));
	           saveMessages(request, am);
	           return mapping.findForward(FWD_INPUT);
	       }
	       try {
	           s = perSiniestro.search(m);
	           log.info("aqui es la validacion duplicidad"+factura.getId());
	           log.info("aqui es la validacion duplicidad"+(m));
	           if (factura.getId() != 0) {
	               mapa.setId(factura.getId());
	               mapa.setIdFactura(factura.getId());
	               factura = perFactura.searchIdSiniestro(m);
	               perFactura.updateFactura(mapa);
	               incluirTraza(TR_LIQUIDACION_CARGARFACTURA, 
	String.valueOf(factura.getId()), "Insertando Factura", 
	usuarioSession(request));
	           } else {
	               idFactura = perFactura.insert(mapa);
	               incluirTraza(TR_LIQUIDACION_CARGARFACTURA, 
	String.valueOf(idFactura), "Insertando Factura", usuarioSession(request));
	               mapa.setId(idFactura);
	               mapa.setIdFactura(idFactura);
	           }
	           //se toma el anio del siniestro
	           
	           //mapa.setAnioSiniestro(getAnioBusqueda(request));
	           Factura fact = perFactura.search(mapa);
	           request.setAttribute("fechaFactura", fact.getFechaFactura());
	           mapa.setIdSiniestro(idSiniestro);
	           mapa.setIdFactura(fact.getId());
	           mapa.setId(fact.getId());
	           //se toma el anio del siniestro
	           log.info("dupliiiiiiiiii antes aqui");
	           //mapa.setAnioSiniestro(getAnioBusqueda(request));
	           
	           
	           // /////////////cambios //////////////5899415684478517
	           try {
	        	   log.info("antes aqui try");
	               detalleFactura = perFactura.searchDetalle(mapa);
	               mapa.setIdDetalle(detalleFactura.getIdDetalle());
	               perFactura.updateDetalleFactura(mapa);
	               incluirTraza(TR_LIQUIDACION_CARGAR_DETALLEFACTURA, 
	String.valueOf(detalleFactura.getIdDetalle()), "Insertando Detalle de Factura", usuarioSession(request));
	               log.info("aqui es la validacion fghgh"+mapa.getIdProveedor());
	               mapa.setIdProveedor(Integer.parseInt(request.getParameter("proveedorCombo")));
	               perSiniestro.updateLiquidacion(mapa);
	               log.info("aqui es la validacion fghghghfg2"+mapa.getIdProveedor());
	               s = perSiniestro.search(m);
	               incluirTraza(TR_MEDICINAS_LIQUIDAR, 
	String.valueOf(Op.getId()), "Liquidando Medicinas", 
	usuarioSession(request));
	           } catch (Exception e) {
	        	   log.info("despues antes aqui try");
	               idDetalleFactura = perFactura.insertDetalle(mapa);
	               incluirTraza(TR_LIQUIDACION_CARGARFACTURA, 
	String.valueOf(idDetalleFactura), "Insertando Detalle de Factura", 
	usuarioSession(request));
	               log.info("aqui es la validacion fghghghfg3"+mapa.getIdProveedor());
	               mapa.setIdProveedor(Integer.parseInt(request.getParameter("proveedorCombo")));
	               log.info("aqui es la validacion f"+mapa.getIdProveedor());
	               perSiniestro.updateLiquidacion(mapa);
	               log.info("aqui es la validacion fghghghfg4"+mapa.getIdProveedor());
	             
	               s = perSiniestro.search(m);
	               incluirTraza(TR_MEDICINAS_LIQUIDAR, 
	String.valueOf(Op.getId()), "Liquidando Medicinas", 
	usuarioSession(request));
	           }
	           // //// cambios //////////////////
	       } catch (Exception e) {
	           am.add(ALERT_AVISOS, new 
	ActionMessage("env.general.operacionerronea"));
	           saveMessages(request, am);
	           return mapping.findForward(FWD_INPUT);
	       }
	       if (s.getEstatus().getId() == 4) {
	           try {
	               mapa.setIdEstatus(est.getId());
	               mapa.setIdSiniestro(s.getId());
	               motEst = perMotEst.searchByEstatus(mapa);
	               request.setAttribute("motEst", motEst);
	           } catch (Exception e) {
	               motEst = new MotivoEstatus();
	           }
	       }
	       try {
	           mapa.setMontoNegociado(mapa.getMontoFactura());
	           mapa.setIdSiniestro(idSiniestro);
	           mapa.setAnioSiniestro(Op.getAnioSiniestro());
	           mapa.setMontoNoAmparado(montoNoAmparado);
	           mapa.setMontoPresupuestado(Op.getMontoPresupuestado());
	       
	           mapa.setIdTipoGasto(COD_TIPO_GASTO_SERVICIOS_FARMACIA);
	     am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
	           saveMessages(request, am);
	           request.setAttribute("siniestro", s);
	           return mapping.findForward(FWD_SUCCESS);
	       } catch (Exception e) {
	           am.add(ALERT_AVISOS, new ActionMessage("env.general.eeee"));
	           saveMessages(request, am);
	           return mapping.findForward(FWD_INPUT);
	       }

	   }}

	




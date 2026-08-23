package ve.gob.dem.fasdem.action.emergencia;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.NotaTecnica;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.ServicioEnLinea;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerNotaTecnica;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.SiniestroNotPermittedException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class EditarEmergencia extends GenericAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484824758110166412L;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_EMERGENCIA_EDICION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
		Mapa mapa = new Mapa();
		Siniestro sin = new Siniestro();
		PerSiniestro perSin = new PerSiniestro();
		Date today = new Date();
		PerPoliza perPoliza = new PerPoliza();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		mapa = getDForm(request, form, ent);
		mapa.setIdSiniestro(mapa.getId());
		sin = perSin.search(mapa);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/**************************************** INICIO *****************************************************/
			/***************************************************************************************************/
			if (ent.isCobertura()) {
				int idPoliza = 0;
				try {
					idPoliza = sin.getCobertura().getPoliza().getId();
				} catch (Exception e1) {
					log.info("infot", e);
				}
				Poliza poliza = perPoliza.searchActivo();
				if (poliza.getId() != idPoliza) {
					setCoberturaParaModificar(request, poliza.getId(), idPoliza);
				}
			}
			/****************************************** FIN ******************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			return mapping.findForward(FWD_INPUT);
		}
		if (ent.isCobertura()) {
			int idPoliza = 0;
			try {
				idPoliza = sin.getCobertura().getPoliza().getId();
			} catch (Exception e1) {
				log.info("infot", e1);
			}
			Poliza poliza = perPoliza.searchActivo();
			if (poliza.getId() != idPoliza) {
				setCoberturaParaModificar(request, poliza.getId(), idPoliza);
			}
		}
		/****************************************** FIN ******************************************************/
		/***************************************************************************************************/
		/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
		/***************************************************************************************************/
		/***************************************************************************************************/
		/***************************************************************************************************/

		if ("editar".equals(request.getParameter("accion"))) {
			// Recogo los datos del formulario
			mapa = getDForm(request, form, ent);
			mapa.setIdSiniestro(mapa.getId());
			// Verifico si tiene disponibilidad para el monto presupuestado
			// busco el siniestro que voy a editar
			mapa.setMontoPresupuestado(sin.getMontoPresupuestado());
			log.info("monto maximo " + sin.getMontoMaximoAutorizado());
			if (sin.getMontoMaximoAutorizado() == 0) {
				// Verifico si tiene disponibilidad para el monto presupuestado
				try {
					mapa.setMontoSiniestro(sin.getMontoAmparado());
					findDisponible(mapa, sin.getFechaOcurrencia());
				} catch (CoberturaNotDisponibleException e) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
			} else {
				if (mapa.getMontoAmparado() > sin.getMontoMaximoAutorizado()) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.montomaximoagotado", sin.getMontoMaximoAutorizado()));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
			}
			try {
				permitido(mapa);
			} catch (SiniestroNotPermittedException e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.notpermitted"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			// edito los datos del siniestro
			mapa.setFechaUltimaModificacion(today);
			mapa.setFechaNotificacion(sin.getFechaNotificacion());
			mapa.setFechaOcurrencia(mapa.getFechaIngreso());
			mapa.setIdTipoEnfermedad(COD_TIPO_ENFERMEDAD_AGUDA);
			double montoNoAmparado = Double.parseDouble(request.getParameter("montoNegociado")) - Double.parseDouble(request.getParameter("montoAmparado"));
			mapa.setMontoNoAmparado(montoNoAmparado);
			mapa.setMontoPresupuestado(sin.getMontoPresupuestado());
			if (mapa.getIdEstatus() == COD_ESTATUS_ANULADO || mapa.getIdEstatus() == COD_ESTATUS_DECLINADO || mapa.getIdEstatus() == COD_ESTATUS_RECHAZADO) {
				double blanqueo = 0;
				mapa.setMontoAmparado(blanqueo);
			}
			mapa.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
			log.info("******"+mapa.getIdProveedor());
			perSin.update(mapa);
			incluirTraza(TR_EMERGENCIA_MODIFICAR, String.valueOf(sin.getId()), "Modificando Emergencia", usuarioSession(request));
			
			
			
			
			
			NotaTecnica nt = new NotaTecnica();
			PerNotaTecnica pnt = new PerNotaTecnica();	
			nt.setAnioSiniestro(mapa.getAnioSiniestro());
			nt.setIdSiniestro(mapa.getId());
			nt.setObservacion(mapa.getObservacion());
			nt.setLoginUsuario(usuarioSession(request).getCedula());
			nt.setIdDependencia(usuarioSession(request).getIdDependencia());
			//nt.setDesUsuario(usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido());
			log.info("nota "+ nt.getAnioSiniestro());
			int idNota = pnt.insert(nt);
			
			
			
			
			
			// ******INSERTAR TRAZA
			/*
			 * // busco la factura del siniestro mapa.setIssFactura(false); fact
			 * = perFact.searchIdSiniestro(mapa);
			 * 
			 * mapa.setIdFactura(fact.getId()); // edito ls datos de la factura
			 * perFact.updateTotalFactura(mapa); // ******INSERTAR TRAZA
			 * 
			 * // edito los montos de los honorarios
			 * 
			 * mapa.setMontoPresupuestado(mapa.
			 * getMontoHonorariosMedicosPresupuestado());
			 * mapa.setMontoAmparado(mapa.getMontoHonorariosMedicos());
			 * mapa.setMontoNegociado(mapa.getMontoHonorariosNegociado());
			 * mapa.setMontoNoAmparado
			 * (mapa.getMontoHonorariosMedicosNoAmparado());
			 * mapa.setIdTipoGasto(COD_TIPO_GASTO_HONORARIOS); try { fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * // si consigue lo modifico
			 * mapa.setIdDetalle(fact.getIdDetalle());
			 * perFact.updateDetalleFactura(mapa); // ******INSERTAR TRAZA }
			 * catch (PersonalNotFoundException e) { if
			 * (mapa.getMontoHonorariosMedicos() != 0) {
			 * 
			 * // inserto el registro pdf.insert(mapa); // ******INSERTAR TRAZA
			 * } } // edito los montos de ambulancia
			 * 
			 * mapa.setMontoPresupuestado(mapa.getMontoAmbulanciaPresupuestado())
			 * ; mapa.setMontoAmparado(mapa.getMontoAmbulancia());
			 * mapa.setMontoNegociado(mapa.getMontoAmbulanciaNegociado());
			 * mapa.setMontoNoAmparado(mapa.getMontoAmbulanciaNoAmparado());
			 * mapa.setIdTipoGasto(COD_TIPO_GASTO_AMBULANCIA); try { fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * // si consigue lo modifico
			 * mapa.setIdDetalle(fact.getIdDetalle());
			 * perFact.updateDetalleFactura(mapa); // ******INSERTAR TRAZA }
			 * catch (PersonalNotFoundException e) { if
			 * (mapa.getMontoAmbulancia() != 0) {
			 * 
			 * // inserto el registro pdf.insert(mapa); // ******INSERTAR TRAZA
			 * } } // edito los montos de funeraria
			 * 
			 * mapa.setMontoPresupuestado(mapa.getMontoFunerariaPresupuestado());
			 * mapa.setMontoAmparado(mapa.getMontoFuneraria());
			 * mapa.setMontoNegociado(mapa.getMontoFunerariaNegociado());
			 * mapa.setMontoNoAmparado(mapa.getMontoFunerariaNoAmparado());
			 * mapa.setIdTipoGasto(COD_TIPO_GASTO_FUNERARIOS); try { fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * // si consigue lo modifico
			 * mapa.setIdDetalle(fact.getIdDetalle());
			 * perFact.updateDetalleFactura(mapa); // ******INSERTAR TRAZA }
			 * catch (PersonalNotFoundException e) { if
			 * (mapa.getMontoFuneraria() != 0) {
			 * 
			 * // inserto el registro pdf.insert(mapa); // ******INSERTAR TRAZA
			 * } } // edito los montos de examenes
			 * 
			 * mapa.setMontoPresupuestado(mapa.
			 * getMontoExamenesEspecialesPresupuestado());
			 * mapa.setMontoAmparado(mapa.getMontoExamenesEspeciales());
			 * mapa.setMontoNegociado
			 * (mapa.getMontoExamenesEspecialesNegociado());
			 * mapa.setMontoNoAmparado
			 * (mapa.getMontoExamenesEspecialesNoAmparado());
			 * mapa.setIdTipoGasto(COD_TIPO_GASTO_EXAMENES_ESPECIALES); try {
			 * fact = perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * // si consigue lo modifico
			 * mapa.setIdDetalle(fact.getIdDetalle());
			 * perFact.updateDetalleFactura(mapa); // ******INSERTAR TRAZA }
			 * catch (PersonalNotFoundException e) { if
			 * (mapa.getMontoExamenesEspeciales() != 0) {
			 * 
			 * // inserto el registro pdf.insert(mapa); // ******INSERTAR TRAZA
			 * } } // edito monto de los gastos
			 * mapa.setMontoPresupuestado(mapa.getMontoGastosPresupuestado());
			 * mapa.setMontoAmparado(mapa.getMontoGastosClinicos());
			 * mapa.setMontoNegociado(mapa.getMontoGastosNegociado());
			 * mapa.setMontoNoAmparado(mapa.getMontoGastosClinicosNoAmparado());
			 * mapa.setIdTipoGasto(COD_TIPO_GASTO_GASTOS_CLINICOS); try { fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * // si consigue lo modifico
			 * mapa.setIdDetalle(fact.getIdDetalle());
			 * perFact.updateDetalleFactura(mapa); // ******INSERTAR TRAZA }
			 * catch (PersonalNotFoundException e) { if
			 * (mapa.getMontoGastoClinico() != 0) {
			 * 
			 * // inserto el registro pdf.insert(mapa); // ******INSERTAR TRAZA
			 * } }
			 */
			// Verifico si según su estatus genera reporte
			mapa.setIdEstatus(Integer.parseInt(request.getParameter("estatus")));
			mapa.setIdTipoTramite(sin.getTipoTramite().getId());
			PerEstatus perEst = new PerEstatus();
			Estatus est = new Estatus();
			try {
				est = perEst.buscar(Integer.parseInt(request.getParameter("estatus")));
				if (est.isJustificacion()) {
					mapa.setIdEstatus(est.getId());
					mapa.setIdSiniestro(sin.getId());
					motEst.setDescripcion(mapa.getJustificacion());
					motEst.setIdSiniestro(sin.getId());
					motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
					motEst.setIdUsuario(usuarioSession(request).getCedula());
					motEst.setIdEstatus(Integer.parseInt(request.getParameter("estatus")));
					perMotEst.insert(motEst);
					incluirTraza(TR_EMERGENCIA_INSERTAR_CAMBIO_ESTATUS, String.valueOf(sin.getId()), TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, usuarioSession(request));
					// ******INSERTAR TRAZA
				}
			} catch (Exception e) {
				log.info("error", e);
			}
			
			sin = perSin.search(mapa);
			request.setAttribute("siniestro", sin);
			mapa.setId(mapa.getIdSiniestro());
			request.getSession().setAttribute("siniestroPadre", null);
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/**************************************** INICIO *****************************************************/
			/***************************************************************************************************/
			if (ent.isCobertura()) {
				int idPoliza = sin.getCobertura().getPoliza().getId();
				Poliza poliza = perPoliza.searchActivo();
				if (poliza.getId() != idPoliza) {
					setCoberturaParaModificar(request, poliza.getId(), idPoliza);
				}
			}
			/****************************************** FIN ******************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			
			log.info("2entre aqui en puenteeeeeeeeeeeeeeee**** "+request.getParameter("sms"));
			try{
				log.info("2entre aqui en puenteeeeeeeeeeeeeeee**** "+request.getParameter("tlf"));
			if ("on".equals(request.getParameter("sms"))) {
				try {log.info("entre aqui en puente "+request.getParameter("tlf"));
				    Properties props = new Properties();

			    
				    props.setProperty("mail.smtp.host","smtp3.tsj-dem.gob.ve");
				  
				    props.setProperty("mail.smtp.port", "25");
				    Session session1 = Session.getDefaultInstance(props);
				    MimeMessage messagee = new MimeMessage(session1);
				    log.info("antes de  "+sin.getEstatus().getId());
				    messagee.setFrom(new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
				    log.info("despues de "+sin.getEstatus().getId());
				    if ("8".equals(sin.getEstatus().getId())||"9".equals(sin.getEstatus().getId())||sin.getEstatus().getId()==8||sin.getEstatus().getId()==9){
				    	 log.info("2entre aqui en puente "+sin.getEstatus().getId());
				    messagee.setText("Emerg - fasdem informa su solicitud fue APROBADA "+sin.getAniomesCodigo()+"-"+sin.getCodigo()+"-"+sin.getSubCodigo()+" en la Clinica: "+sin.getProveedor().getDescripcion()+ " pcte:" +mapa.getNombresBeneficiario()+" "+mapa.getApellidosBeneficiario()+ " Fecha:"+Utilidad.DateToString(
				    		sin.getFechaOcurrencia(), "dd/MM/yyyy"));}
				    if ("35".equals(sin.getEstatus().getId())||sin.getEstatus().getId()==35){
				    	 log.info("3entre aqui en puente "+sin.getEstatus());
					    messagee.setText("Emerg - fasdem informa su solicitud fue RECHAZADA "+sin.getAniomesCodigo()+"-"+sin.getCodigo()+"-"+sin.getSubCodigo()+" en la Clinica: "+sin.getProveedor().getDescripcion()+ " pcte:" +mapa.getNombresBeneficiario()+" "+mapa.getApellidosBeneficiario()+ " Fecha:"+Utilidad.DateToString(
					    		sin.getFechaOcurrencia(), "dd/MM/yyyy"));}
				    String tlf=request.getParameter("tlf");
				    tlf=tlf.replace("-", "");
				    log.info("1entre aqui en puente "+tlf);
				    //messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
				    messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
				    // Lo enviamos.
					Transport tt = session1.getTransport("smtp");
					log.info("1entre aqui en puente "+tlf);
					tt.send(messagee, messagee.getAllRecipients());
					log.info("2entre aqui en puente "+tlf);
					tt.close();
				} catch (Exception e) {log.info("72errooooentre aqui en puenteeeeeeeeeeeeeeee**** ",e);}
					/*ServicioEnLinea sel = (ServicioEnLinea) request.getSession().getAttribute("objPuenteOnline");
					ExpSiniestroBandeja.CambiarEstatusSiniestro(sel.getIdSolicitud(), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido(), sel.getObservacion());
					request.getSession().setAttribute("objPuenteOnline", null);*/
					//enviarCorreo(sel.getTelefono(), codigoBandeja);
				}
			}catch (Exception e) {
				log.info("2errooooentre aqui en puenteeeeeeeeeeeeeeee**** ",e);
			}
			/*
			 * detallesPresupuesto = pf.listDetallesFactura(mapa);
			 * request.setAttribute("listDetalle", detallesPresupuesto);
			 * mapa.setIssFactura(true); facturas=pf.listDetallesFactura(mapa);
			 * request.setAttribute("listFactura", facturas);
			 */
		am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		} else {
			return mapping.findForward(FWD_INPUT);
		}
	}
}

package ve.gob.dem.fasdem.action.emergencia;

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

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class CreaEmergencia extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_EMERGENCIA_NUEVO_DECLARAR);
		Mapa mapa = new Mapa();
		PerPoliza pp = new PerPoliza();
		PerEstatus perEst = new PerEstatus();
		Estatus est = new Estatus();
		Siniestro siniPadre = null;
		PerCobertura perCob = new PerCobertura();
		PerSiniestro ps = new PerSiniestro();
		EstatusTipoTramite estTipTra = new EstatusTipoTramite();
		PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		Cobertura cober = new Cobertura();
		Poliza poliza = new Poliza();
		String paramSiniPadre = "";
		double blanqueo = 0;
		int idSiniestro;
		Date today = new Date();
		
		
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		mapa.setIdTipoTramite(COD_TIPO_EMERGENCIA);
		
		if (request.getSession().getAttribute("idSiniPadre") != null) {
			paramSiniPadre = (String) request.getSession().getAttribute("idSiniPadre");
			mapa.setIdSiniestro(Integer.parseInt(paramSiniPadre));
			siniPadre = ps.search(mapa);
		}
		if (request.getParameter("idSiniPadre") != null) {
			paramSiniPadre = request.getParameter("idSiniPadre");
		}
		if (request.getParameter("paramNewSini") != null) {
			request.getSession().removeAttribute("siniestroPadre");
			paramSiniPadre = null;
		}
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		log.info("paramSiniPadre# #: " + paramSiniPadre);
		mapa = getDForm(request, form, ent);
		mapa.setMontoAmparado(mapa.getMontoAmparado());
		mapa.setCitaPostOperatorio(false);
		
		mapa.setMontoPresupuestado(mapa.getMontoPresupuestado());
		mapa.setMontoNegociado(mapa.getMontoNegociado());
		mapa.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
		try {
			log.info("cober " + request.getParameter("cobertura"));
			cober = perCob.searchById(Integer.parseInt(request.getParameter("cobertura")));
			mapa.setIdPoliza(cober.getPoliza().getId());
			log.info("poliza " + cober.getPoliza().getId());
			poliza = pp.search(cober.getPoliza().getId());
		} catch (Exception e) {
			poliza = pp.searchActivo();
			mapa.setIdPoliza(poliza.getId());
			log.error("error", e);
		}
		mapa.setIdTipoTratamiento(COD_TIPO_TRATAMIENTO_MEDICO);
		mapa.setIdTipoEnfermedad(mapa.getIdTipoEnfermedad());

		if (mapa.getMontoPresupuestado() == 0) {
			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.montosvacios"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		if (mapa.getMontoAmparado() == 0) {
			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.montosvacios"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		if (mapa.getMontoNegociado() == 0) {
			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.montosvacios"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		double montoNoAmparado = mapa.getMontoNegociado()
				- mapa.getMontoAmparado();
		mapa.setMontoNoAmparado(montoNoAmparado);
		// coberturaTipo = perCob.search(mapa.getIdCobertura());
		log.info("fechaOcurrencia " + mapa.getFechaOcurrencia());
		log.info("getFechaFin " + poliza.getFechaFin());
		if (!validaAnioOcurrenciaPoliza(mapa.getFechaOcurrencia(), poliza.getFechaFin())) {
			am.add(ALERT_AVISOS, new ActionMessage("env.fechaocurrencia.discordante"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		Date fechaMenoss = today;

		
		mapa.setFechaIngreso(mapa.getFechaOcurrencia());
		mapa.setFechaEgreso(mapa.getFechaEgreso());
		mapa.setFechaNotificacion(today);
		mapa.setIdEstatus(9);
		mapa.setIdTipoEnfermedad(COD_TIPO_ENFERMEDAD_AGUDA);
		mapa.setIdTipoTramite(COD_TIPO_EMERGENCIA);
		log.info("estatus " + mapa.getIdEstatus());
		try {
			if (mapa.getIdEstatus() == COD_ESTATUS_ANULADO || mapa.getIdEstatus() == COD_ESTATUS_RECHAZADO) {
				if (siniPadre != null) {
					mapa.setAnioMesCodigo(siniPadre.getAniomesCodigo());
					mapa.setCodigo(siniPadre.getCodigo());
					mapa.setIdSiniestroPadre(siniPadre.getId());
				}
				mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(mapa.getFechaOcurrencia(), "yyyy")));
				mapa.setMontoAmparado(blanqueo);
				mapa.setIdTipoTramite(COD_TIPO_EMERGENCIA);
				log.info("campo orsevacion" + mapa.getObservacion() + " justificacuión " + mapa.getJustificacion());
				if (mapa.getObservacion().length() > 950 || mapa.getJustificacion().length() > 950) {
					am.add(ALERT_AVISOS, new ActionMessage("env.maximocaracteres"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				} else {
					idSiniestro = ps.insert(mapa);
					incluirTraza(TR_EMERGENCIA_DECLARAR, String.valueOf(idSiniestro), "Cargando Emergencia ", usuarioSession(request));
					try {
						est = perEst.buscar(mapa.getIdEstatus());
						if (est.isJustificacion()) {
							motEst.setDescripcion(mapa.getJustificacion());
							motEst.setIdSiniestro(idSiniestro);
							motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
							motEst.setIdUsuario(usuarioSession(request).getCedula());
							motEst.setIdEstatus(mapa.getIdEstatus());
							perMotEst.insert(motEst);
							// ******INSERTAR TRAZA
							incluirTraza(TR_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, String.valueOf(idSiniestro), TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, usuarioSession(request));
						}
					} catch (Exception e) {
						log.error("error", e);
					}
					mapa.setId(idSiniestro);
					mapa.setIdSiniestro(idSiniestro);
					mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(mapa.getFechaOcurrencia(), "yyyy")));
					Siniestro s = ps.search(mapa);
					// Verifico si según su estatus genera reporte
					mapa.setIdEstatus(s.getEstatus().getId());
					mapa.setIdTipoTramite(s.getTipoTramite().getId());
					try {
						estTipTra = perEstTipTra.searchByEstatusTipoTramite(mapa);
						if (estTipTra.getReporte() != null) {
							request.setAttribute("tipoImpresion", COD_TIPO_REPORTE_REEMBOLSO);
						}
					} catch (PersonalNotFoundException e) {
						log.info("info", e);
					}
					request.setAttribute("s", s);
					am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
					saveMessages(request, am);
					request.setAttribute("siniestro", s);
					request.getSession().setAttribute("siniestroPadre", null);
					return mapping.findForward(FWD_SUCCESS);
				}
			} else {
				try {
					log.info("evaluarrrrrrrrrrrrrrrrrrr1 " + mapa.getIdEstatus());
					findDisponible(mapa, mapa.getFechaOcurrencia());
				} catch (CoberturaNotDisponibleException e) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
				log.info("evaluarrrrrrrrrrrrrrrrrrr " + mapa.getIdEstatus());
				// if (coberturaAsegurado + mapa.getMontoAmparado() <=
				// coberturaTipo ) {
				if (siniPadre != null) {
					mapa.setAnioMesCodigo(siniPadre.getAniomesCodigo());
					mapa.setCodigo(siniPadre.getCodigo());
					mapa.setIdSiniestroPadre(siniPadre.getId());
				}
				if (mapa.getIdEstatus() == COD_ESTATUS_ANULADO || mapa.getIdEstatus() == COD_ESTATUS_RECHAZADO) {
					mapa.setMontoAmparado(blanqueo);
				}
				mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(mapa.getFechaOcurrencia(), "yyyy")));
				if (mapa.getObservacion().length() > 950 || mapa.getJustificacion().length() > 950) {
					am.add(ALERT_AVISOS, new ActionMessage("env.maximocaracteres"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				} else {
					idSiniestro = ps.insert(mapa);
					incluirTraza(TR_EMERGENCIA_DECLARAR, String.valueOf(idSiniestro), "INSERCION DE EMERGENCIA ", usuarioSession(request));
					try {
						est = perEst.buscar(mapa.getIdEstatus());
						if (est.isJustificacion()) {
							motEst.setDescripcion(mapa.getJustificacion());
							motEst.setIdSiniestro(idSiniestro);
							motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
							motEst.setIdUsuario(usuarioSession(request).getCedula());
							motEst.setIdEstatus(mapa.getIdEstatus());
							perMotEst.insert(motEst);
							// ******INSERTAR TRAZA
							incluirTraza(TR_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, String.valueOf(idSiniestro), TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, usuarioSession(request));
						}
					} catch (Exception e) {
						log.error("error", e);
					}
					mapa.setId(idSiniestro);
					mapa.setIdSiniestro(idSiniestro);
					mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(mapa.getFechaOcurrencia(), "yyyy")));
					Siniestro s = ps.search(mapa);
					// Verifico si según su estatus genera reporte
					mapa.setIdEstatus(s.getEstatus().getId());
					mapa.setIdTipoTramite(s.getTipoTramite().getId());
					try {
						estTipTra = perEstTipTra.searchByEstatusTipoTramite(mapa);
						if (estTipTra.getReporte() != null) {
							request.setAttribute("tipoImpresion", COD_TIPO_REPORTE_REEMBOLSO);
						}
					} catch (PersonalNotFoundException e) {
						log.info("info", e);
					}
					request.setAttribute("s", s);
					am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
					saveMessages(request, am);
					request.setAttribute("siniestro", s);
					request.getSession().setAttribute("siniestroPadre", null);
				}
			}
		} catch (Exception e) {
			log.error("error", e);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.operacionerronea"));
			saveMessages(request, am);
			request.getSession().setAttribute("siniestroPadre", null);
			return mapping.findForward(FWD_SUCCESS);
		}
		log.info("ANTES DEL TRY**** "+request.getParameter("sms"));
        try{
            log.info("ENTRE AL TRY AQUI EL NUMERO --> "+request.getParameter("tlf"));
        if ("on".equals(request.getParameter("sms"))) {
            try {log.info("entre aqui en puente "+request.getParameter("tlf"));
                Properties props = new Properties();

            
                props.setProperty("mail.smtp.host","smtp3.tsj-dem.gob.ve");
              
                props.setProperty("mail.smtp.port", "25");
                Session session1 = Session.getDefaultInstance(props);
                MimeMessage messagee = new MimeMessage(session1);
                
                messagee.setFrom(new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
     
              
              /////mensajeeeee //////////////////////////
              messagee.setText("Emerg - fasdem informa su solicitud fue procesada "+siniPadre.getAniomesCodigo()+"-"+siniPadre.getCodigo()+"-"+siniPadre.getSubCodigo()+" en: "+siniPadre.getProveedor().getDescripcion()+ ", pcte:" +mapa.getNombresBeneficiario()+" "+mapa.getApellidosBeneficiario()+ " Fecha:"+Utilidad.DateToString(
            		  siniPadre.getFechaOcurrencia(), "dd/MM/yyyy"));

              log.info("mensaje sms --> Emerg - fasdem informa su solicitud fue procesada "+siniPadre.getAniomesCodigo()+"-"+siniPadre.getCodigo()+"-"+siniPadre.getSubCodigo()+" en: "+siniPadre.getProveedor().getDescripcion()+ ", pcte:" +mapa.getNombresBeneficiario()+" "+mapa.getApellidosBeneficiario()+ " Fecha:"+Utilidad.DateToString(
            		  siniPadre.getFechaOcurrencia(), "dd/MM/yyyy"));
          

                String tlf=request.getParameter("tlf");
                tlf=tlf.replace("-", "");
                log.info("1entre aqui en puente "+tlf);
                //messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
                messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
                // Lo enviamos.
                Transport tt = session1.getTransport("smtp");
                log.info("1er entre aqui en puente "+tlf);
                tt.send(messagee, messagee.getAllRecipients());
                log.info("2do entre aqui en puente "+tlf);
                tt.close();
            } catch (Exception e) {log.info("catch  aqui en puenteeeeeeeeeeeeeeee**** ",e);}
                /*ServicioEnLinea sel = (ServicioEnLinea) request.getSession().getAttribute("objPuenteOnline");
                ExpSiniestroBandeja.CambiarEstatusSiniestro(sel.getIdSolicitud(), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido(), sel.getObservacion());
                request.getSession().setAttribute("objPuenteOnline", null);*/
                //enviarCorreo(sel.getTelefono(), codigoBandeja);
            }
        }

        catch (Exception e) {
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
     
	
	}
}

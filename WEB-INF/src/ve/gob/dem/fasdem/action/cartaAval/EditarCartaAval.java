package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.exception.SiniestroNotPermittedException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class EditarCartaAval extends GenericAction implements Serializable {
	/**
	 * figumare
	 */
	private static final long serialVersionUID = -1484824758110166412L;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_EDICION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_CARTAAVAL);
		Mapa mapa = new Mapa();
		Siniestro sin = new Siniestro();
		PerSiniestro perSin = new PerSiniestro();
		PerPoliza perPoliza = new PerPoliza();
		
		
		Date today = new Date();
		EstatusTipoTramite estTipTra = new EstatusTipoTramite();
		PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
		Date fn = new Date();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		mapa = getDForm(request, form, ent);
		mapa.setIdSiniestro(mapa.getId());
		String paramSiniPadre = request.getParameter("idSiniPadre");
		String paramAnioSiniPadre = request.getParameter("anioSiniPadre");
		Siniestro siniPadre = null;
		PerSiniestro ps = new PerSiniestro();
		DynaActionForm dForm = (DynaActionForm) form;
		
		
		//no borrar esto es para que se refleje los datos del siniestro padre
		log.info("id y anio "+paramSiniPadre +" anio "+paramAnioSiniPadre);
		if (paramSiniPadre != null) {
			int idSiniPadre = Integer.parseInt(paramSiniPadre);
			mapa.setAnioSiniestro(Integer.parseInt(paramAnioSiniPadre));
			mapa.setIdSiniestro(idSiniPadre);
			siniPadre = ps.search(mapa);
			request.setAttribute("idSiniPadre", request.getParameter("idSiniPadre"));
			request.setAttribute("anioSiniPadre", request.getParameter("anioSiniPadre"));
			request.setAttribute("siniestroPadre", siniPadre);
			
		
		} else {
			siniPadre = null;
		}
		
		
		
		
		
		// busco el siniestro que voy a editar
		sin = perSin.search(mapa);
		
		
		try {
			validarAction(request, form, ent, am, this.getClass());
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
		} catch (PersonalNotFillItems e) {
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
			return mapping.findForward(FWD_INPUT);
		}
		if ("editar".equals(request.getParameter("accion"))) {
			// Recogo los datos del formulario
			mapa = getDForm(request, form, ent);
			mapa.setMontoNoAmparado(mapa.getMontoNegociado() - mapa.getMontoAmparado());
			// Verifico que la fecha de notificaciòn no puede ser mayor al dìa
			// actual
			fn = Utilidad.StringToDate(Utilidad.DateToString(mapa.getFechaNotificacion(), "dd/MM/yyyy"), "dd/MM/yyyy");
			today = Utilidad.StringToDate(Utilidad.DateToString(today, "dd/MM/yyyy"), "dd/MM/yyyy");
			if (today.compareTo(fn) > 0) {
				am.add(ALERT_AVISOS, new ActionMessage("env.fechanotificacion.mayorhoy"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			// Si es estatus es evaluaci�n m�dica tiene preoperatoria
			if (mapa.getIdEstatus() == COD_ESTATUS_EVALUACION) {
				mapa.setCitaPreOperatorio(true);
			}
			// Si es estatus rechazado, anulado o declinado, ultimo Rn
			if (mapa.getIdEstatus() == COD_ESTATUS_ANULADO || mapa.getIdEstatus() == COD_ESTATUS_DECLINADO) {
				double blanqueo = 0;
				mapa.setMontoHonorariosMedicosNoAmparado(mapa.getMontoHonorariosMedicos() + mapa.getMontoHonorariosMedicosNoAmparado());
				mapa.setMontoHonorariosMedicos(blanqueo);
				mapa.setMontoGastosClinicosNoAmparado(mapa.getMontoGastosClinicosNoAmparado() + mapa.getMontoGastosClinicos());
				mapa.setMontoGastosClinicos(blanqueo);
				mapa.setMontoNoAmparado(mapa.getMontoNoAmparado() + mapa.getMontoAmparado());
				mapa.setMontoAmparado(blanqueo);
			}
			// busco el estatus
			// jutifico
			PerEstatus perEst = new PerEstatus();
			Estatus est = new Estatus();
			try {
				est = perEst.buscar(mapa.getIdEstatus());
				if (est.isJustificacion()) {
					mapa.setIdEstatus(est.getId());
					mapa.setIdSiniestro(mapa.getId());
					try {
						motEst = perMotEst.searchByEstatus(mapa);
						// Si consigo un estatus de ese tipo lo finalizo y creo
						// otro
						perMotEst.finalizaTodosEstatus(motEst.getId());
						// ******INSERTAR TRAZA
						incluirTraza(TR_CARTAAVAL_FINALIZAR_CAMBIO_ESTATUS, String.valueOf(mapa.getId()), TRDESC_CARTAAVAL_FINALIZAR_CAMBIO_ESTATUS, usuarioSession(request));
					} catch (PersonalNotFoundException e) {
					}
					motEst.setDescripcion(mapa.getJustificacion());
					motEst.setIdSiniestro(mapa.getId());
					motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
					motEst.setIdUsuario(usuarioSession(request).getCedula());
					motEst.setIdEstatus(mapa.getIdEstatus());
					perMotEst.insert(motEst);
					// ******INSERTAR TRAZA
					incluirTraza(TR_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, String.valueOf(mapa.getId()), TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, usuarioSession(request));
				}
			} catch (Exception e) {
				
				log.error("error", e);
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
			}
			mapa.setIdSiniestro(mapa.getId());
			// busco el siniestro que voy a editar
			sin = perSin.search(mapa);
			mapa.setMontoPresupuestado(sin.getMontoPresupuestado());
			mapa.setMontoSiniestro((sin.getMontoAmparado()));
			if (sin.getMontoMaximoAutorizado() == 0) {
				// Verifico si tiene disponibilidad para el monto presupuestado
				try {
					mapa.setMontoSiniestro(sin.getMontoAmparado());
					findDisponible(mapa,sin.getFechaOcurrencia());
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
			// Verifico si cumple con los parametros requeridos
			//
			try {
				permitido(mapa);
			} catch (SiniestroNotPermittedException e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.notpermitted"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			// edito los datos del siniestro
			mapa.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));	
			mapa.setFechaUltimaModificacion(today);
			mapa.setIdTipoEnfermedad(COD_TIPO_ENFERMEDAD_AGUDA);
			perSin.update(mapa);
			// ******INSERTAR TRAZA
			incluirTraza(TR_CARTAAVAL_MODIFICAR, String.valueOf(mapa.getIdSiniestro()), TRDESC_CARTAAVAL_MODIFICAR, usuarioSession(request));
			sin = perSin.search(mapa);
			// Verifico si según su estatus genera reporte 
			mapa.setIdEstatus(sin.getEstatus().getId());
			mapa.setIdTipoTramite(sin.getTipoTramite().getId());
			try {
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(mapa);
				if (estTipTra.getReporte() != null) {
					request.setAttribute("tipoImpresion", COD_TIPO_REPORTE_CARTA_AVAL);
				}
			} catch (PersonalNotFoundException e) {
			}
			request.setAttribute("siniestro", sin);
			request.getSession().setAttribute("siniestroPadre", null);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		} else {
			return mapping.findForward(FWD_INPUT);
		}
	}
}
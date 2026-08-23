package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.exception.SiniestroNotPermittedException;
import ve.gob.dem.framework.global.GenericAction;

public class NuevoCartaAval extends GenericAction implements Serializable {
	private static final long serialVersionUID = 8977367766848541959L;

	/**
	 * figumare
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_CARTAAVAL);
		PerPoliza pp = new PerPoliza();
		PerSiniestro ps = new PerSiniestro();
		Siniestro siniPadre = null;
		Mapa mapa = new Mapa();
		int idSini = -1;
		List siniestros = new ArrayList();
		Siniestro s = null;
		Date today = new Date();
		PerEstatus perEst = new PerEstatus();
		Estatus est = new Estatus();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		DynaActionForm dForm = (DynaActionForm) form;
		String paramSiniPadre = request.getParameter("idSiniPadre");
		String paramAnioSiniPadre = request.getParameter("anioSiniPadre");
		Persona bene = new Persona();
		bene = buscarTitularBeneficiario(request);
		SiniestroBandeja siniestroBandeja = new SiniestroBandeja();
		
		// log.info("sesion persona cedula "+bene.getCedula());
		if (bene.getBeneficiario().getEstatus().equals("ACTIVO")) {
		} else {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.inactivo"));
			saveMessages(request, am);
			mapa.setAnioSiniestro(getAnioBusqueda(request));
			mapa.setCedula(bene.getBeneficiario().getCedula());
			mapa.setCedulaBeneficiario(bene.getCedula());
			try {
				siniestros = ps.searchCedulaBeneficiario(mapa);
				request.setAttribute(KEY_SINIESTROS, siniestros);
			} catch (PersonalNotFoundException e) {
			}
			request.setAttribute("myhref", mapping.getParameter());
			return mapping.findForward(FWD_RETURN);
		}
		if (request.getParameter("paramNewSini") != null) {
			request.getSession().removeAttribute("siniestroPadre");
			paramSiniPadre = null;
		}
		if (paramSiniPadre != null) {
			int idSiniPadre = Integer.parseInt(paramSiniPadre);
			mapa.setAnioSiniestro(Integer.parseInt(paramAnioSiniPadre));
			mapa.setIdSiniestro(idSiniPadre);
			siniPadre = ps.search(mapa);
			request.setAttribute("idSiniPadre", request.getParameter("idSiniPadre"));
			request.setAttribute("anioSiniPadre", request.getParameter("anioSiniPadre"));
			request.setAttribute("siniestroPadre", siniPadre);
			request.getSession().setAttribute("siniestroPadre", siniPadre);
			
			// Verifico si puede generar secuelas
			if ((siniPadre.getTipoTramite().getId() == COD_TIPO_EMERGENCIA || siniPadre.getTipoTramite().getId() == COD_TIPO_TRAMITE_CARTAAVAL) && siniPadre.getEstatus().getId() != COD_ESTATUS_ANULADO) {
				// Valores del padre
				setEntorno(request, form, ent);
				dForm.set("idCausaIngreso", new Integer(siniPadre.getPatologiaOrganoTratamiento().getId()).toString());
				dForm.set("causaIngreso", siniPadre.getPatologiaOrganoTratamiento().getDescripcion());
				dForm.set("id", String.valueOf(siniPadre.getId()));
				dForm.set("cobertura", String.valueOf(siniPadre.getCobertura().getId()));
				
				mapa.setIdCobertura(siniPadre.getCobertura().getId());
				mapa.setCedulaBeneficiario(siniPadre.getCedulaBeneficiario());
				mapa.setCedula(siniPadre.getCedula());
				mapa.setAnioSiniestro(siniPadre.getAnioSiniestro());
				cob = pc.searchById(mapa.getIdCobertura());
				// Si es por patolog�a
				if (cob.isPorPatologia()) {
					try {
						desgloseCobertura = pc.listDesgloseCobertura(mapa);
						cob.setDesgloseCobertura(desgloseCobertura);
						request.setAttribute("desgloseCobertura", desgloseCobertura);
					} catch (Exception e) {
					}
				}
				// Si es por tipo de cobertura
				else {
					cobertura = pc.listByCedula(mapa);
					request.setAttribute("detalleMontoCobertura", cobertura);
				}
			} else {
				request.getSession().setAttribute("siniestroPadre", null);
				am.add(ALERT_AVISOS, new ActionMessage("env.general.hijo.notpermitted"));
				saveMessages(request, am);
				mapa.setCedula(siniPadre.getCedulaBeneficiario());
				mapa.setAnioSiniestro(siniPadre.getAnioSiniestro());
				mapa.setCedulaBeneficiario(siniPadre.getCedula());
				log.info("mapa " + mapa);
				try {
					siniestros = ps.searchCedulaBeneficiario(mapa);
					log.info("siniestros " + siniestros.size());
					request.setAttribute(KEY_SINIESTROS, siniestros);
				} catch (PersonalNotFoundException e) {
					log.info("no tiene " + e);
				}
				request.setAttribute("myhref", mapping.getParameter());
				return mapping.findForward(FWD_RETURN);
			}
		} else {
			siniPadre = null;
		}
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		// si el usuario no le ha dado clic a procesar es porque esta validando
		// y lo retorna al formulario
		if (!"procesar".equals(request.getParameter("accion"))) {
			return mapping.findForward(FWD_INPUT);
		} else {
			mapa = getDForm(request, form, ent);
			mapa.setMontoNoAmparado(mapa.getMontoNegociado() - mapa.getMontoAmparado());
			// Despues de haber capturado los datos del formulario verifico si
			// tiene disponible en la cobertura seleccionada
			try {
				findDisponible(mapa,null);
			} catch (CoberturaNotDisponibleException e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
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
			// Lo primero que debe hacer es cargar el siniestro
			// Como es una carta aval las fechas de notificación y ocurrencia
			// corresponden al día de la carga
			mapa.setFechaNotificacion(today);
			mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
			mapa.setIdTipoEnfermedad(COD_TIPO_ENFERMEDAD_AGUDA);
			mapa.setIdPoliza(pp.searchActivo().getId());
			if (siniPadre != null) {
				mapa.setAnioMesCodigo(siniPadre.getAniomesCodigo());
				mapa.setCodigo(siniPadre.getCodigo());
				mapa.setIdSiniestroPadre(siniPadre.getId());
			}
			if (request.getSession().getAttribute("siniestroPadre") != null) {
				siniPadre = (Siniestro) request.getSession().getAttribute("siniestroPadre");
				mapa.setAnioMesCodigo(siniPadre.getAniomesCodigo());
				mapa.setCodigo(siniPadre.getCodigo());
				mapa.setIdSiniestroPadre(siniPadre.getId());
				
			}
			// idSini = ps.insert(mapa);
			log.info("insert estoy despues de insertar " + idSini + " es mapa " + mapa);
			mapa.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));	
			log.info("o essstee"+ Integer.parseInt(request.getParameter("idProveedor")));
			mapa.setFechaOcurrencia(new Date());
			idSini = ps.insert(mapa);
			log.info("estoy despues de insertar " + idSini + " es mapa " + mapa);
			// ******INSERTAR TRAZA
			incluirTraza(TR_CARTAAVAL_DECLARAR, String.valueOf(idSini), TRDESC_CARTAAVAL_DECLARAR, usuarioSession(request));
			// busco el estatus y si requiere justificación inserto el motivo
			try {
				est = perEst.buscar(mapa.getIdEstatus());
				if (est.isJustificacion()) {
					motEst.setDescripcion(mapa.getJustificacion());
					motEst.setIdSiniestro(idSini);
					motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
					motEst.setIdUsuario(usuarioSession(request).getCedula());
					motEst.setIdEstatus(mapa.getIdEstatus());
					perMotEst.insert(motEst);
					// ******INSERTAR TRAZA
					incluirTraza(TR_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, String.valueOf(idSini), TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, usuarioSession(request));
				}
			} catch (Exception e) {
			}
			mapa.setIdSiniestro(idSini);
			log.info("hola es belgica " + mapa);
			s = ps.search(mapa);
			siniestroBandeja.setId_siniestro(idSini);
			siniestroBandeja.setAnio_siniestro(s.getAnioSiniestro());
			siniestroBandeja.setId_estatus(1);
			siniestroBandeja.setAnio_siniestro(s.getAnioSiniestro());
			ExpSiniestroBandeja.InsertarSiniestroBandejaMedicos(siniestroBandeja);
			request.setAttribute("siniestro", s);
			request.getSession().setAttribute("siniestroPadre", null);
			mapa.setIdSiniestro(idSini);
			request.setAttribute("tipoImpresion", COD_TIPO_REPORTE_CARTA_AVAL);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		}
	}
}
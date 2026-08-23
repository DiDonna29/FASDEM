package ve.gob.dem.fasdem.action.reembolsos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerTipoEnfermedad;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class EditaReembolsos extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSOS_EDITA);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		PerSiniestro perSiniestro = new PerSiniestro();
		PerPoliza 			pp 				= new PerPoliza();
		Date fechahoy = new Date();
		Calendar f = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setTime(fechahoy);
		PerCobertura perCob = new PerCobertura();
		formato.format(f.getTime());
		DynaActionForm dForm = (DynaActionForm) form;
		int idSiniestro = Integer.parseInt(request.getParameter("id"));
		mapa = getDForm(request, dForm, ent);
		mapa.setIdSiniestro(idSiniestro);
		Cobertura 			cober 			= new Cobertura();
		Poliza 				poliza 			= new Poliza();
		// mapa.setAnioSiniestro(getAnioBusqueda(request));
		Siniestro Op = perSiniestro.search(mapa);
		request.setAttribute("cedulaop", Op.getCedulaBeneficiario());
		request.setAttribute("Op", Op);
		mapa.setCodigo(Op.getAniomesCodigo() + Op.getCodigo() + Op.getSubCodigo());
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_REEMBOLSO);
		Siniestro sss = perSiniestro.searchByCodigo(mapa);
		double coberturaTipo = 0.0;
		int estatus = 0;
		double coberturaAsegurado = 0.0;
		int aguda = 0;
		;
		Siniestro s = new Siniestro();
		TipoEnfermedad tipoEnfermedad = new TipoEnfermedad();
		PerTipoEnfermedad perTipoEnfermedad = new PerTipoEnfermedad();
		request.setAttribute("cedulaop", Op.getCedulaBeneficiario());
		mapa.setCedula(Op.getCedulaBeneficiario());
		mapa.setCedulaBeneficiario(Op.getCedulaBeneficiario());
		log.info("formularioooo " + request.getParameter("formulario"));
		dForm.set("estatus", String.valueOf(sss.getEstatus().getId()));
		dForm.set("cobertura", String.valueOf(Op.getCobertura().getId()));
		dForm.set("tipoSiniestro", new Integer(sss.getTipoSiniestro().getId()).toString());
		dForm.set("tipoEnfermedad", String.valueOf(sss.getTipoEnfermedad().getId()));
		dForm.set("idCausaIngreso", new Integer(sss.getPatologiaOrganoTratamiento().getId()).toString());
		dForm.set("causaIngreso", sss.getPatologiaOrganoTratamiento().getDescripcion());
		dForm.set("estatus", new Integer(sss.getEstatus().getId()).toString());
		dForm.set("fechaOcurrencia", Utilidad.DateToString(sss.getFechaOcurrencia(), "dd/MM/yyyy"));
		dForm.set("fechaNotificacion", Utilidad.DateToString(sss.getFechaNotificacion(), "dd/MM/yyyy"));
		dForm.set("monto", String.valueOf(sss.getMontoPresupuestado()));
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		// int idSiniestro = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("cedulaop", Op.getCedulaBeneficiario());
		request.setAttribute("Op", Op);
		if ("editar".equals(request.getParameter("formulario"))) {
			dForm.set("estatus", String.valueOf(Op.getEstatus().getId()));
			dForm.set("tipoEnfermedad", String.valueOf(Op.getTipoEnfermedad().getId()));
			dForm.set("cobertura", String.valueOf(Op.getCobertura().getId()));
			dForm.set("observacion", Op.getObservacion());
		}
		mapa = getDForm(request, form, ent);
		mapa.setCedula(Op.getCedula());
		mapa.setCedulaBeneficiario(Op.getCedulaBeneficiario());
		mapa.setObservacion(request.getParameter("observacion"));
		estatus = mapa.getIdEstatus();
		if (Op.getTipoEnfermedad().getId() != (Integer.parseInt(request.getParameter("tipoEnfermedad")))) {
			if ("1".equals(request.getParameter("tipoEnfermedad"))) {
				mapa.setIdMedicinas(COD_TIPO_ENFERMEDAD_AGUDA);
				tipoEnfermedad = perTipoEnfermedad.searchMedicinas(mapa.getIdMedicinas());
				mapa.setDias(tipoEnfermedad.getDias());
				mapa.setContador(tipoEnfermedad.getContador());
				if ("1".equals(Op.getTipoEnfermedad())) {
					mapa.setMontoAmparado(Op.getMontoAmparado());
					mapa.setMontoPresupuestado(Op.getMontoPresupuestado());
				}
				aguda = perTipoEnfermedad.searchAguda(mapa);
				if (aguda < tipoEnfermedad.getContador()) {
					mapa.setMontoAmparado(tipoEnfermedad.getMonto());
					mapa.setMontoPresupuestado(tipoEnfermedad.getMonto());
				} else {
					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.aguda"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
			}
		} else {
			mapa.setMontoAmparado(Op.getMontoAmparado());
			mapa.setMontoPresupuestado(Op.getMontoPresupuestado());
			mapa.setIdCobertura(Integer.parseInt(request.getParameter("cobertura")));
		}
		if (COD_ESTATUS_RECHAZADO == estatus || COD_ESTATUS_ANULADO == estatus) {
			mapa.setMontoAmparado(0);
		}
		mapa.setId(idSiniestro);
		mapa.setIdSiniestro(idSiniestro);
		mapa.setCitaPostOperatorio(false);
		mapa.setFechaUltimaModificacion(new Date());
		mapa.setFechaNotificacion(new Date());
		mapa.setFechaOcurrencia(new Date());
		mapa.setIdCobertura(Integer.parseInt(request.getParameter("cobertura")));
		mapa.setObservacion(request.getParameter("observacion"));
		mapa.setIdProveedor(Op.getProveedor().getId());
		mapa.setIdTipoSiniestro(Op.getTipoSiniestro().getId());
		mapa.setIdTipoTratamiento(Op.getTipoTratamiento().getIdTipoTratamiento());
		mapa.setIdTipoEnfermedad(Integer.parseInt(request.getParameter("tipoEnfermedad")));
		if ("1".equals(request.getParameter("tipoEnfermedad"))) {
			mapa.setIdMedicinas(COD_TIPO_ENFERMEDAD_AGUDA);
			tipoEnfermedad = perTipoEnfermedad.searchMedicinas(mapa.getIdMedicinas());
			aguda = perTipoEnfermedad.searchAguda(mapa);
			mapa.setObservacion(request.getParameter("observacion"));
			mapa.setObservacionReporte(tipoEnfermedad.getTextoInicio() + "\r" + request.getParameter("observacion") + "\r" + tipoEnfermedad.getTextoFin());
		}
		coberturaTipo = perCob.search(mapa.getIdCobertura());
		coberturaAsegurado = perCob.coberturaAsegurado(mapa) + mapa.getMontoAmparado() - Op.getMontoAmparado();
		try {
			cober = perCob.searchById(mapa.getIdCobertura());
			mapa.setIdPoliza(cober.getPoliza().getId());
			poliza = pp.search(cober.getPoliza().getId());
		} catch (Exception e) {
			poliza = pp.searchActivo();
			mapa.setIdPoliza(poliza.getId());
			log.error("error", e);
		}

		if (!validaAnioOcurrenciaPoliza(mapa.getFechaOcurrencia(), poliza.getFechaFin())) {
			am.add(ALERT_AVISOS, new ActionMessage("env.fechaocurrencia.discordante"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		log.info("cober " + cober.getId());
		log.info("poliza " + poliza.getId());
		
		
		
		if (Op.getCobertura().getId() != mapa.getIdCobertura()) {
			try {
				if (coberturaAsegurado <= coberturaTipo) {
					mapa.setCitaPostOperatorio(false);
					
					if (mapa.getObservacion().length() > 950 || mapa.getJustificacion().length() > 950) {
						am.add(ALERT_AVISOS, new ActionMessage("env.maximocaracteres"));
						saveMessages(request, am);
						return mapping.findForward(FWD_INPUT);
					} else {
					perSiniestro.update(mapa);
					incluirTraza(TR_APS_MODIFICAR, String.valueOf(idSiniestro), "MODIFICACION DE SINIESTRO ", usuarioSession(request));
					s = perSiniestro.search(mapa);
					request.setAttribute("s", s);
					am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
					saveMessages(request, am);
					}
				} else {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
			} catch (Exception e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.operacionerronea"));
				saveMessages(request, am);
				return mapping.findForward(FWD_SUCCESS);
			}
		} else {

			try {
				
				if (mapa.getObservacion().length() > 950 || mapa.getJustificacion().length() > 950) {
					am.add(ALERT_AVISOS, new ActionMessage("env.maximocaracteres"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				} else {
				perSiniestro.update(mapa);
				s = perSiniestro.search(mapa);
				request.setAttribute("s", s);
				am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
				saveMessages(request, am);
				}
			} catch (Exception e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.operacionerronea"));
				saveMessages(request, am);
				return mapping.findForward(FWD_SUCCESS);
			}
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

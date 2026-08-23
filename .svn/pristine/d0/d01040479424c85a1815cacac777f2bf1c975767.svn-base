package ve.gob.dem.fasdem.action.aps;

import java.util.ArrayList;
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
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class SetupEditSiniestro extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCAR_SINIESTRO);
		
		if (request.getSession().getAttribute("apsUpdate")!=null){
			
			am.add(ALERT_AVISOS, new ActionMessage("update.success"));
			saveMessages(request, am);
			request.getSession().removeAttribute("apsUpdate");
			
		}
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Mapa mapa = getDForm(request, form, ent);
		PerSiniestro ps = new PerSiniestro();
		Persona t = null;
		PerPersona pp = new PerPersona();
		PerPoliza perPoliza = new PerPoliza();
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_APS);
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		int i = 0;
		try {
			Siniestro s = new Siniestro();
			try {
				s = ps.searchByCodigo(mapa);
			} catch (PersonalNotFoundException e) {
				log.info("No encontrado ", e);
				am.add(ALERT_VALIDACION, new ActionMessage("env.general.notfound"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_APS) {
				t = pp.buscar(s.getCedula());
				t.setBeneficiario(pp.buscar(s.getCedulaBeneficiario()));
				if (s.getEstatus().getId() == 1) {
					i = 1;
				}
				if (s.getEstatus().getId() == 9) {
					i = 1;
				}
				if (i == 0) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
				try {
					if (!ps.estatusEditable(s.getId())) {
						am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
						saveMessages(request, am);
						return mapping.findForward(FWD_INPUT);
					}
					DynaActionForm dForm = (DynaActionForm) form;
					request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
					dForm.set("id", String.valueOf(s.getId()));
					// log.info("info fino fino " + s.getCobertura().getId());
					dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
					dForm.set("id", String.valueOf(s.getId()));
					dForm.set("cobertura", String.valueOf(s.getCobertura().getId()));
					mapa.setIdCobertura(s.getCobertura().getId());
					mapa.setCedula(s.getCedula());
					mapa.setCedulaBeneficiario(s.getCedulaBeneficiario());
					cob = pc.searchById(mapa.getIdCobertura());
					// Si es por patolog�a
					if (cob.isPorPatologia()) {
						try {
							desgloseCobertura = pc.listDesgloseCobertura(mapa);
							cob.setDesgloseCobertura(desgloseCobertura);
							request.setAttribute("desgloseCobertura", desgloseCobertura);
						} catch (Exception e) {
						}
					} else {
						// Si es por tipo de cobertura
						log.info("mi mapa es " + mapa);
						cobertura = pc.listByCedula(mapa);
						request.setAttribute("detalleMontoCobertura", cobertura);
					}
					dForm.set("fechaOcurrencia", Utilidad.DateToString(s.getFechaOcurrencia(), "dd/MM/yyyy"));
					dForm.set("fechaNotificacion", Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
					dForm.set("tipoSiniestro", new Integer(s.getTipoSiniestro().getId()).toString());
					dForm.set("tipoEnfermedad", new Integer(s.getTipoEnfermedad().getId()).toString());
					dForm.set("tipoProveedor", new Integer(s.getProveedor().getTipoProveedor().getId()).toString());
					dForm.set("idProveedor", new Integer(s.getProveedor().getId()).toString());
					dForm.set("proveedor", s.getProveedor().getDescripcion());
					dForm.set("idCausaIngreso", new Integer(s.getPatologiaOrganoTratamiento().getId()).toString());
					dForm.set("causaIngreso", s.getPatologiaOrganoTratamiento().getDescripcion());
					dForm.set("observacion", s.getObservacion());
					dForm.set("codigo", s.getAniomesCodigo() + s.getCodigo() + s.getSubCodigo());
					dForm.set("monto", String.valueOf(s.getMontoPresupuestado()));
					ent = new Entorno(Entorno.MOD_APS_EDICION); 
					setEntorno(request, form, ent);					
					
/***************************************************************************************************/
/***************************************************************************************************/
/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS  *********************/
/***************************************************************************************************/
/****************************************INICIO*****************************************************/
/***************************************************************************************************/
					if (ent.isCobertura()) {
						int idPoliza = s.getCobertura().getPoliza().getId();
						Poliza poliza = perPoliza.searchActivo();
						if (poliza.getId() != idPoliza) {
							
							setCoberturaParaModificar(request, poliza.getId(), idPoliza);
						}
					}
/******************************************FIN******************************************************/
/***************************************************************************************************/
/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS  *********************/
/***************************************************************************************************/
/***************************************************************************************************/
/***************************************************************************************************/
					request.setAttribute("form_action", mapping.getParameter());
					request.setAttribute(KEY_TITULAR, t);
				} catch (PersonalNotFoundException e) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
			} else {
				am.add(ALERT_AVISOS, new ActionMessage("env.noaps"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		} catch (Exception e) {
			log.error("error ", e);
			am.add(ALERT_VALIDACION, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

package ve.gob.dem.fasdem.action.aps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class EditarAps extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_APS_EDICION);
		PerSiniestro ps = new PerSiniestro();
		PerPoliza perPoliza = new PerPoliza();
		Siniestro s = new Siniestro();
		Mapa m = new Mapa();
		Persona p = buscarTitularBeneficiario(request);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		m = getDForm(request, form, ent);
		m.setIdSiniestro(Integer.parseInt(request.getParameter("id")));
		s = ps.search(m);
		try {
			validarAction(request, form, ent, am, this.getClass());
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/**************************************** INICIO *****************************************************/
			/***************************************************************************************************/
			if (ent.isCobertura()) {
				int idPoliza = s.getCobertura().getPoliza().getId();
				Poliza poliza = perPoliza.searchActivo();
				if (poliza.getId() != idPoliza) {
					setCoberturaParaModificar(request, poliza.getId(), idPoliza);
				}
			}
			/***************************************************************************************************/
			/****************************************** FIN ******************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
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
				int idPoliza = s.getCobertura().getPoliza().getId();
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
		try {
			 m = getDForm(request, form, ent);
			if (!ps.estatusEditable(m.getId())) {
				am.add(ALERT_AVISOS, new ActionMessage("update.estatusnoeditable"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			try {
				permitido(m);
			} catch (Exception e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.nocumple"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			m.setMontoPresupuestado(m.getMonto());
			m.setMontoSiniestro(s.getMontoAmparado());
			m.setMontoAmparado(m.getMonto());
			m.setMontoNegociado(m.getMonto());
			m.setIdSiniestro(Integer.parseInt(request.getParameter("id")));
			m.setCedulaBeneficiario(p.getBeneficiario().getCedula());
			m.setIdSiniestro(s.getId()); 
			m.setIdProveedor(s.getProveedor().getId());
			log.info("proveedorrrrrrrr"+ s.getProveedor().getId());
			try {
				log.info("entrando findDisponible");
				findDisponible(m, null);
			} catch (CoberturaNotDisponibleException e) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			log.info("proveedorrrrrrrrantesupdate"+ s.getProveedor().getId());
			
			log.info("mapaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ m);
			ps.updateAps(m);

		
			incluirTraza(TR_APS_MODIFICAR, String.valueOf(m.getIdSiniestro()), "Incluir APS", usuarioSession(request));

			request.getSession().setAttribute("apsUpdate", "update");
			return mapping.findForward(FWD_SUCCESS);
		} catch (Exception e) {
			log.error("error ", e);
			am.add(ALERT_AVISOS, new ActionMessage("update.unsuccess"));
			saveMessages(request, am);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

package ve.gob.dem.fasdem.action.notaMedica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class SetupAddNotaMedica extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCAR_SINIESTRO);

		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Mapa mapa = getDForm(request, form, ent);
		PerSiniestro ps = new PerSiniestro();
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_APS);

		try {
			Siniestro s;
			s = ps.searchByCodigo(mapa);
			DynaActionForm dForm = (DynaActionForm) form;
			dForm.set("id", String.valueOf(s.getId()));
			dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
			request.setAttribute("siniestro", s);
			ent = new Entorno(Entorno.MOD_ADD_NOTAMEDICA);
			setEntorno(request, form, ent);
			request.setAttribute("form_action", mapping.getParameter());
		} catch (Exception e) {
			log.error("error ", e);
			am.add(ALERT_VALIDACION, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

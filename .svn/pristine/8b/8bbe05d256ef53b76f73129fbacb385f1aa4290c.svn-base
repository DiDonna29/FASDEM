package ve.gob.dem.fasdem.action.reembolsos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class ConsultaEditaReembolsos extends GenericAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSO_CONSULTA_EDITA);
		request.setAttribute(KEY_TIPO_COBERTURA, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		PerSiniestro op = new PerSiniestro();
		Siniestro Op = null;
		int estatus = 0;
		log.info("EDITAR REEMBOLSO 1 ");
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		try {
			Op = op.searchNumeroR(mapa.getSubCodigo());
			Op.getId();
			estatus = Op.getEstatus().getId();
			request.setAttribute("Op", Op);
		} catch (PersonalNotFoundException e1) {
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		mapa.setCedulaBeneficiario(String.valueOf(Op.getCedulaBeneficiario()));
		if (1 == estatus) {
			request.setAttribute("Op", Op);
		} else {
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.noeditable"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

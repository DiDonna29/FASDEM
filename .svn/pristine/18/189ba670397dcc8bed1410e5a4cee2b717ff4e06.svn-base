package ve.gob.dem.fasdem.action.liquidacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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

public class ConsultaLiquidacionEmergencia extends GenericAction {
	Logger log = Logger.getLogger(ConsultaLiquidacionEmergencia.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_EMERGENCIA_CONSULTA_LIQUIDACION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
		Mapa mapa = new Mapa();
		PerSiniestro op = new PerSiniestro();
		Siniestro Op = null;
		int estatus = 0;
		request.setAttribute("myhref", mapping.getParameter());
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		mapa = getDForm(request, form, ent);
		String reemplazo=mapa.getSubCodigo();
		reemplazo=reemplazo.replace("-", "");
		mapa.setSubCodigo(reemplazo);
		try {

			Op = op.searchEmergencia(mapa.getSubCodigo());
			Op.getId();
			estatus = Op.getEstatus().getId();
			request.setAttribute("Op", Op);

		} catch (PersonalNotFoundException e1) {

			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		
		
		if (COD_ESTATUS_EGRESADO == estatus) {

			request.setAttribute("Op", Op);
			
		}

		else {

			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.noestatus"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}

}

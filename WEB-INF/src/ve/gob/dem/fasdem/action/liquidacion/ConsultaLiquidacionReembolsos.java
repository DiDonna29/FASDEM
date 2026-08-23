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

public class ConsultaLiquidacionReembolsos extends GenericAction {
	Logger log = Logger.getLogger(ConsultaLiquidacionReembolsos.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSO_LIQUIDACION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		PerSiniestro op = new PerSiniestro();
		Siniestro Op = null;
		int estatus = 0;
		request.setAttribute("myhref", mapping.getParameter());
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		mapa = getDForm(request, form, ent);
		log.info("mapa1111111111111 "+mapa);
		
		try {

			Op = op.searchNumeroR(mapa.getSubCodigo());
			log.info("mapa22222222222222222222222"+mapa);
			//Op=(Siniestro)request.getSession().getAttribute("Op");
			log.info("Op de sesion"+Op);
			Op.getId();
			estatus = Op.getEstatus().getId();
			log.info("ESTATUSSSSSSSSSSSSSSSSSSSSSS"+estatus);
			request.setAttribute("Op", Op);

		} catch (PersonalNotFoundException e1) {

			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}

		if (1 == estatus) {

			request.setAttribute("Op", Op);
			return mapping.findForward(FWD_SUCCESS);
		}

		else {

			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.noestatus"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}

	}

}

package ve.gob.dem.fasdem.action.liquidacion;

import java.util.ArrayList;
import java.util.List;

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

public class ConsultaLiquidacionCartaAval extends GenericAction {
	Logger log = Logger.getLogger(ConsultaLiquidacionCartaAval.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA_LIQUIDACION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_CARTAAVAL);
		Mapa mapa = new Mapa();
		PerSiniestro op = new PerSiniestro();
		Siniestro Op = null;
		int estatus = 0;
		List list=new ArrayList();
		log.info("snnernenr");
		request.setAttribute("myhref", mapping.getParameter());
		
		//Es la primera vez que entra y por eso le presento un listado para facilitar
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
		mapa.setIdEstatus(COD_ESTATUS_EGRESADO);
		list=op.searchMultipleByStatus(mapa);
		request.setAttribute("list", list);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		mapa = getDForm(request, form, ent);

		try {

			Op = op.searchAval(mapa.getSubCodigo());
			Op.getId();
			estatus = Op.getEstatus().getId();
			request.setAttribute("Op", Op);

		} catch (PersonalNotFoundException e1) {

			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}

		if (COD_ESTATUS_EGRESADO == estatus) {

			request.setAttribute("Op", Op);

		}

		else {

			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.noestatus"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}

}

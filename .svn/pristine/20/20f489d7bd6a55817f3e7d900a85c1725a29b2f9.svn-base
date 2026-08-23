package ve.gob.dem.fasdem.action.cartaAval.liquidacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BandejaPorLiquidar extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_LIQUIDACION_SINIESTROS);
		DynaActionForm dForm = (DynaActionForm) form;
		dForm.set("anioBusqueda", new Integer(getAnioBusqueda(request)).toString());
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_SUCCESS);
		}
		Mapa m = new Mapa();
		m = getDForm(request, form, ent);
		PerSiniestro ps = new PerSiniestro();
		m.setIdEstatus(COD_ESTATUS_EGRESADO);
		m.setIdTipoTramite(Integer.parseInt(mapping.getParameter()));
		m.setAnioSiniestro(getAnioBusqueda(request));
		if (request.getParameter("codigo") != null && !"".equals(request.getParameter("codigo"))) {
			m.setCodigo(request.getParameter("codigo"));
		}
		try {
			request.setAttribute("lsSiniestros", ps.listarPorEstatus(m));
		} catch (PersonalNotFoundException e) {
			am.add(ALERT_VALIDACION, new ActionMessage("list.notfound"));
			saveMessages(request, am);
			request.setAttribute("lsSiniestros", new ArrayList());
		} catch (Exception e) {
			request.setAttribute("lsSiniestros", new ArrayList());
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

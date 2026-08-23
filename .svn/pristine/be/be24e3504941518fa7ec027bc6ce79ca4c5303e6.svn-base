package ve.gob.dem.fasdem.action.aps;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Parametros;
import ve.gob.dem.framework.recursos.Utilidad;

public class BandejaAnalizar extends GenericAction {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		DynaActionForm dForm = (DynaActionForm) form;
		dForm.set("anio", Utilidad.DateToString(new Date(), "yyyy"));
		dForm.set("anioBusqueda", Utilidad.DateToString(new Date(), "yyyy"));
		
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Mapa m = new Mapa();
		PerSiniestro ps = new PerSiniestro();
		if (request.getParameter("anioBusqueda") == null) {
			m.put("ano", Utilidad.DateToString(new Date(), "yyyy"));
		} else {
			m.put("ano", request.getParameter("anio"));
		}
		m.setAnioSiniestro(getAnioBusqueda(request));
		m.setIdTipoTramite(COD_TIPO_TRAMITE_APS);
		m.setIdEstatus(COD_ESTATUS_ANULADO_NO_PROCEDENTE);
		if (request.getParameter("codigo") != null || !"".equals(request.getParameter("codigo"))) {
			m.setCodigo(Parametros.getStrParameter(request, "codigo", ""));
		}
		try {
			request.setAttribute("lsSiniestros", ps.listarPorEstatus(m));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("lsSiniestros", new ArrayList());
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("lsSiniestros", new ArrayList());
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

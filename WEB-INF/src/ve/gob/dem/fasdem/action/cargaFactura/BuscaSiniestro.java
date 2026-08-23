package ve.gob.dem.fasdem.action.cargaFactura;

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

public class BuscaSiniestro extends GenericAction {
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARGA_FACTURA_BUSCASINIESTRO);
		DynaActionForm dForm = (DynaActionForm) form;

		dForm.set("anioBusqueda", new Integer(getAnioBusqueda(request)).toString());
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		} 
		Mapa m = new Mapa();
		PerSiniestro ps = new PerSiniestro();
		//m.setIdTipoTramite(COD_TIPO_TRAMITE_APS);
		m.setIdEstatus(COD_ESTATUS_EGRESADO);
		m.setAnioSiniestro(getAnioBusqueda(request));
		if (request.getParameter("codigo") != null && !"".equals(request.getParameter("codigo"))) {
			m.put("codigo", request.getParameter("codigo"));
			try {
				request.setAttribute("lsSiniestros", ps.listarPorRecepcionFactura(m));
			} catch (PersonalNotFoundException e) {
				am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
				saveMessages(request, am);
				request.setAttribute("lsSiniestros", null);
			} catch (Exception e) {
				log.error("error", e);
				request.setAttribute("lsSiniestros", null);
			}
		} 
		return mapping.findForward(FWD_SUCCESS);
	}
}

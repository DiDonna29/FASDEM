package ve.gob.dem.fasdem.action.comunes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerTratamiento;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarCausaIngreso extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CAUSA_INGRESO);

		PerTratamiento pt = new PerTratamiento();
		List list = null;
		if (request.getParameter("descripcion") != null && !"".equals(request.getParameter("descripcion"))) {
			request.setAttribute("form_action", request.getServletPath());
			setLogger(BuscarCausaIngreso.class);
			setEntorno(request, form, ent);
			try {
				list = pt.porDescripcion(request.getParameter("descripcion"));
			} catch (Exception e1) {
				list = new ArrayList();
			}
			request.setAttribute(LIST_TRATAMIENTO, list);
			return mapping.findForward(FWD_INPUT);
		}else{
			try {
				validarAction(request, form, ent, am, this.getClass());
			} catch (PersonalNotFillItems e) {

				return mapping.findForward(FWD_INPUT);
			}			
		}

		return mapping.findForward(FWD_INPUT);
	}
}

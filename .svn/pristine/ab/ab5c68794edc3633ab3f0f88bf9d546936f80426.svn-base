package ve.gob.dem.fasdem.action.estadisticas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.framework.global.GenericAction;

public class Generar extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REPORTE_ESTADISTICAS);
		try {
			validarAction(request, form, ent, am, this.getClass());
			return mapping.findForward("pantalla");
		} catch (Exception e) {
			return null;
		}
	}
}

/**
 * 14/04/2011 09:37:06
 * marcenrl
 * 2011
 */
package ve.gob.dem.cpd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class HealthCheck extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Instancias
		ActionMessages am = new ActionMessages();
		PerSiniestro ps = null;
		ps = new PerSiniestro();
		Siniestro s = new Siniestro();
		s = ps.cpdHeathcheckIntance();
		request.setAttribute("healthCheckSiniestro", s);
		request.getSession().setAttribute("healthCheckSiniestro", s);
		// Base de Datos
		ps = new PerSiniestro();
		List list1 = ps.cpdHeathcheckBdEstatus();
		List list2 = ps.cpdHeathcheckMemory();
		if (am.isEmpty()) {
			limpiar(request);
			return mapping.findForward("ok");
		} else {
			limpiar(request);
			return mapping.findForward("unsuccess");
		}
	}

	private static void limpiar(HttpServletRequest request) {
		request.setAttribute("healthCheckSiniestro", null);
		request.getSession().setAttribute("healthCheckSiniestro", null);
	}
}

/**
 * 11/03/2011 11:19:52
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.administradores.causaIngreso;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerPatologiaOrganoTratamiento;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class NuevaCausaIngreso extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		PerPatologiaOrganoTratamiento perEOPT = new PerPatologiaOrganoTratamiento();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String esp = request.getParameter("especialidad");
		String org = request.getParameter("organo");
		String pat = request.getParameter("patologias");
		String tra = request.getParameter("tratamiento");
		if ("-1".equals(esp)) {
			esp = null;
		}
		if ("-1".equals(org)) {
			org = null;
		}
		if ("-1".equals(pat)) {
			pat = null;
		}
		if ("-1".equals(tra)) {
			tra = null;
		}
		setEspecialidad(request);
		setOrgano(request);
		setPatologias(request);
		setTratamiento2(request);
		if (esp != null || org != null || pat != null || tra != null) {
			HashMap m = new HashMap();
			/* if (!"-1".equals(esp)) */m.put("idEspecialidad", esp);
			/* if (!"-1".equals(org)) */m.put("idOrgano", org);
			/* if (!"-1".equals(pat)) */m.put("idPatologia", pat);
			/* if (!"-1".equals(tra)) */m.put("idTratamiento", tra);
			request.setAttribute("listEspPatOrgTra", perEOPT.list(m));
		}
		return mapping.findForward(FWD_INPUT);
	}
}

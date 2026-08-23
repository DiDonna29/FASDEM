package ve.gob.dem.fasdem.action.cargaFactura;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class procesaReporte extends GenericAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("ced", usuarioSession(request).getLogin());
			return mapping.findForward(FWD_INPUT);
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

	}

}
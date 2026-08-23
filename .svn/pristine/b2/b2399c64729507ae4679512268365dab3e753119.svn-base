package ve.gob.dem.fasdem.action.reembolsos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.per.PerCuenta;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class consultaCuentas extends GenericAction {
	Logger log = Logger.getLogger(consultaCuentas.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();


		PerCuenta cb= new PerCuenta();
		Cuenta Cb= null;
		Siniestro Op = null;

		

		try {

			Cb = cb.search(request.getParameter("cedula"));
			Cb.getCuenta();
			
			
			request.setAttribute("Op", Op);

		} catch (PersonalNotFoundException e1) {
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"env.general.notfound"));
			saveMessages(request, am);
		}
			return mapping.findForward(FWD_INPUT);
	}}

		
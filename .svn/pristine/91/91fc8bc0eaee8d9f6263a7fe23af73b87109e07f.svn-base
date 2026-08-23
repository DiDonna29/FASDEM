package ve.gob.dem.fasdem.action.administradores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class CuentasNomina extends GenericAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionMessages am = new ActionMessages();
	Entorno ent = new Entorno(Entorno.MOD_CUENTA_NOMINA);

	Mapa mapa = new Mapa();

	//CuentaSf P = null;
	
	
	try {
		validarAction(request, form, ent, am, this.getClass());
		request.setAttribute("myhref", mapping.getParameter());
		log.info("entre en el try");
		
	} catch (PersonalNotFillItems e) {
		log.info("entre en el catch");
		return mapping.findForward(FWD_INPUT);
	}
	
	mapa = getDForm(request, form, ent);
	
	mapa.setCedula(request.getParameter("cedula"));
	
	//P = p.searchCuenta(request.getParameter("cedula"));

	
	return mapping.findForward(FWD_INPUT);
	
}
}

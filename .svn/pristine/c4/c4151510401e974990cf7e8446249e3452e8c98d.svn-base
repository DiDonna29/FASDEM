/**
 * 27/02/2011 17:35:48
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.notaMedica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.ServicioEnLinea;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerServicioEnLinea;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class PuenteOnline extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String siniestro = request.getParameter("siniestro");
		ServicioEnLinea sel = new ServicioEnLinea();
		PerServicioEnLinea psel = new PerServicioEnLinea();
		sel = psel.search(Integer.parseInt(siniestro));
		Persona t = new Persona();
		// Modifica por metodo que se adapte al anio
		request.getSession().setAttribute(KEY_TITULAR, t);
		request.getSession().setAttribute("objPuenteOnline", sel);
		ent = new Entorno(Entorno.MOD_APS_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		setEntorno(request, form, ent);
		request.setAttribute("form_action", mapping.getParameter());
		// calcularCobertura(request, dForm);
		return mapping.findForward(FWD_INPUT);
	}
}

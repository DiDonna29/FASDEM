/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.notaMedica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerNotaTecnica;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class VerNotaMedica extends GenericAction {
	static protected Logger log = Logger.getLogger(VerNotaMedica.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("INICIANDO");
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		PerNotaTecnica pnt = new PerNotaTecnica();
		DynaActionForm dForm = (DynaActionForm) form;
		Mapa m = new Mapa();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			try {
				m.setIdSiniestro(Integer.parseInt(request.getParameter("idSiniestro")));
				m.setAnioSiniestro(Integer.parseInt(request.getParameter("anio")));
				dForm.set("anio", request.getParameter("anio"));
				request.setAttribute("listNotaMedica", pnt.listBySiniestroNotaMedica(m));
			} catch (PersonalNotFoundException ex) {
				log.error("info ", ex);
			} catch (Exception ex1) {
				log.error("error ", ex1);
			}
			return mapping.findForward(FWD_INPUT);
		}
		m = getDForm(request, form, ent);
		try {
			m.setIdSiniestro(Integer.parseInt(request.getParameter("idSiniestro")));
			m.setAnioSiniestro(Integer.parseInt(request.getParameter("anio")));
			dForm.set("anioSiniestro", request.getParameter("anio"));
			request.setAttribute("listNotaMedica", pnt.listBySiniestroNotaMedica(m));
		} catch (PersonalNotFoundException e) {
		} catch (Exception e) {
			log.error("error ", e);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

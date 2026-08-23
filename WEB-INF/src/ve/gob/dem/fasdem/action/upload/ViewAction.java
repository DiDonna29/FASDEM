/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerUpload;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class ViewAction extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PerUpload pu = new PerUpload();
		PerSiniestro ps = new PerSiniestro ();
		Siniestro s = new Siniestro();
		String idSiniestro = "";
		Mapa mapa= new Mapa();
		
		mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anio")));
		
		if (request.getParameter("idSiniestro") == null) {
			idSiniestro = (String) request.getAttribute("idSiniestro");
		} else {
			idSiniestro = request.getParameter("idSiniestro");
		}
		mapa.setIdSiniestro(Integer.parseInt(idSiniestro));
		s = ps.search(mapa);
		request.setAttribute("idSiniestro", idSiniestro);
		request.setAttribute("anio", s.getAnioSiniestro());
		mapa.setId(Integer.parseInt(idSiniestro));
		ps.search(mapa);
		
		try {
			request.setAttribute("adjuntos", pu.listBySiniestro(mapa));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("adjuntos", null);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

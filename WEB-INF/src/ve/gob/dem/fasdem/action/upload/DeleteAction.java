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
import ve.gob.dem.fasdem.bean.Upload;
import ve.gob.dem.fasdem.per.PerUpload;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class DeleteAction extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PerUpload pu = new PerUpload();
		Mapa mapa= new Mapa();
		
		///log.info("--------------   " + request.getParameter("idA"));
		mapa.setId(Integer.parseInt(request.getParameter("idUpload")));
		//mapa.setId(Integer.parseInt(request.getParameter("idA")));
		mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anio")));
		
		Upload u = pu.search(mapa);
		pu.delete(mapa);
		
		//log.info("ENTRO A INCLUIR TRAZA DE ELININACION DE ARCHIVO " + request.getParameter("idUpload"));
		incluirTraza(TR_ADJUNTO_ELIMINAR, String.valueOf(u.getId()), "Eliminar Adjunto" , usuarioSession(request));
		request.setAttribute("idSiniestro", String.valueOf((u.getIdSiniestro())));

		return mapping.findForward(FWD_SUCCESS);
	}

}

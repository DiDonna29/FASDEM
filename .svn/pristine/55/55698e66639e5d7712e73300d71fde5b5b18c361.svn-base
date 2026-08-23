/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.foto;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ve.gob.dem.fasdem.bean.Foto;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.per.PerFoto;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class DownloadFotoAction extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PerFoto pf = new PerFoto();
			Mapa mapa= new Mapa();
		
			log.info("en foto es "+request.getParameter("tipoPersona"));
			
			if("T".equals(request.getParameter("tipoPersona"))){
			
			Foto f = pf.search(Integer.parseInt(request.getParameter("cedula")));
			
			ServletOutputStream o = response.getOutputStream();
			response.setContentType("image/jpeg");
			response.setHeader("Content-disposition", "inline; filename=\"fotoFuncionario\"");
			o.write(f.getData());
			o.close();
			}
		} catch (PersonalNotFoundException e) {
			//log.info("No encontrado ", e);
		} catch (Exception e) {
			log.error("error", e);
		}
		return null;
	}
}

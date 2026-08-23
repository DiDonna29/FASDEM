/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.upload;

import javax.servlet.ServletOutputStream;
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
public class DownloadAction extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PerUpload pu = new PerUpload();
		Mapa mapa= new Mapa();
		mapa.setId(Integer.parseInt(request.getParameter("idUpload")));
		mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anio")));
		Upload u = pu.search(mapa);
		ServletOutputStream o = response.getOutputStream();
		response.setContentType(u.getContentType());
		response.setContentLength(u.getData().length);
		response.setHeader("Content-disposition", "inline; filename=\"" + u.getFileName() + "\"");
		o.write(u.getData());
		o.close();
		return null;
	}
}

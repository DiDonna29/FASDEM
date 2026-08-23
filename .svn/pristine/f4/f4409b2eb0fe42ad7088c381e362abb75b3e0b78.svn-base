/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.administrador.firma;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import ve.gob.dem.fasdem.action.upload.formbeam.UploadForm;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.Upload;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerUpload;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class CargaArchivoFirmaAction extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		UploadForm myForm = (UploadForm) form;
		FormFile myFile = myForm.getTheFile();
		Upload up = new Upload();
		PerUpload pu = new PerUpload();
		PerSiniestro ps = new PerSiniestro();
		Siniestro s = new Siniestro();
		Mapa mapa= new Mapa();
		
		mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anio")));
		String idSiniestro ="";
		if (request.getParameter("idSiniestro")==null){
			idSiniestro = (String)request.getAttribute("idSiniestro");
		}else{
			idSiniestro = request.getParameter("idSiniestro");
			
		}
		
		request.setAttribute("idSiniestro", idSiniestro);
		mapa.setIdSiniestro(Integer.parseInt(idSiniestro));
		s =ps.search(mapa);
		myForm.setAnioSiniestro(String.valueOf(s.getAnioSiniestro()));
		request.setAttribute("anio", s.getAnioSiniestro());
		if (myFile == null || myFile.getFileSize() == 0) {
			am.add(ALERT_AVISOS, new ActionMessage("env.file.requerido"));
			saveMessages(request, am);
		} else {
			up.setContentType(myFile.getContentType());
			up.setFileName(myFile.getFileName());
			up.setIdSiniestro(Integer.parseInt(idSiniestro));
			up.setData(myFile.getFileData());
			up.setUploadLenght(myFile.getFileSize());
			up.setDescripcion(myForm.getDescripcion());
			up.setIdDependencia(usuarioSession(request).getIdDependencia());
			up.setIdUsuario(usuarioSession(request).getLogin());
			up.setAnioSiniestro(getAnioBusqueda(request));
			int idUpload = 0;
			try{
			idUpload = pu.insert(up);
			}catch (com.ibatis.common.jdbc.exception.NestedSQLException e){
				am.add(ALERT_AVISOS, new ActionMessage("upload.acento"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			incluirTraza(TR_ADJUNTO_CARGAR, String.valueOf(idUpload), "Cargar Adjunto", usuarioSession(request));
			form.reset(mapping, request);
			am.add(ALERT_AVISOS, new ActionMessage("insert.success"));
			saveMessages(request, am);

		}
		try {
			request.setAttribute("adjuntos", pu.listBySiniestro(mapa));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("adjuntos", null);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

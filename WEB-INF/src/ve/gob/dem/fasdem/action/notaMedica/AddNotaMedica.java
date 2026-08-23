/**
 * 18/02/2011
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
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.NotaTecnica;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.per.PerNotaTecnica;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class AddNotaMedica extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NUEVO_NOTA_TECNICA);
		PerNotaTecnica pnt = new PerNotaTecnica();	
		Mapa m = new Mapa(); 
		DynaActionForm dForm = (DynaActionForm)form; 
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			try{
				m = getDForm(request, dForm, ent);
				m.setIdSiniestro(Integer.parseInt(request.getParameter("idSiniestro")));
				if (request.getParameter("id")!=null && !request.getParameter("id").equals("null")){
					request.setAttribute("id",request.getParameter("id"));
					
				}
				//m.setAnioSiniestro(getAnioBusqueda(request));
				request.setAttribute("listNotaMedica", pnt.listBySiniestroNotaMedica(m));
				}catch(PersonalNotFoundException ex){
					log.info("info ", ex);
				}catch(Exception ex1){
					log.error("error ", ex1);
				}
			return mapping.findForward(FWD_INPUT);
		} 
		 
		 m = getDForm(request, form, ent); 

		NotaTecnica nt = new NotaTecnica();
		nt.setAnioSiniestro(m.getAnioSiniestro());
		nt.setIdSiniestro(m.getIdSiniestro());
		nt.setObservacion(m.getObservacion());
		nt.setLoginUsuario(usuarioSession(request).getCedula());
		nt.setIdDependencia(usuarioSession(request).getIdDependencia());
		//nt.setDesUsuario(usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido());
		int idNota = pnt.insertMedica(nt);
		
		if (request.getParameter("id")!=null && !request.getParameter("id").equals("null")){
			
			request.setAttribute("id",request.getParameter("id"));
			ExpSiniestroBandeja
			.CambiarEstatusSiniestroMedico(
					Integer.parseInt(request
							.getParameter("id")), 2,
					usuarioSession(request).getLogin(),
					usuarioSession(request).getNombre()
							+ " "
							+ usuarioSession(request)
									.getApellido());
		}
		incluirTraza(TR_NOTAMEDICA_CARGAR, String.valueOf(idNota), "Cargar Nota Medica", usuarioSession(request));
		dForm.set("observacion", "");
		try{
		
			request.setAttribute("listNotaMedica", pnt.listBySiniestroNotaMedica(m));
		}catch(PersonalNotFoundException e){
		}catch(Exception e){
			log.error("error ", e);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

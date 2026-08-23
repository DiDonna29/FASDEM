/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.notaTecnica;

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
import ve.gob.dem.fasdem.per.PerNotaTecnica;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class SaveNotaTecnica extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NUEVO_NOTA_TECNICA);
		PerNotaTecnica pnt = new PerNotaTecnica();	
		Mapa m = new Mapa();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			try{
				
				log.info("anio en save nota "+ request.getParameter("anioSiniestro"));
				m.setIdSiniestro(Integer.parseInt(request.getParameter("idSiniestro")));
				m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
				request.getSession().setAttribute("anioSiniestro", request.getParameter("anioSiniestro"));
				request.setAttribute("listNotaTecnica", pnt.listBySiniestro(m));
				}catch(PersonalNotFoundException ex){
				}catch(Exception ex1){
					log.error("error ", e);
				}
			return mapping.findForward(FWD_INPUT);
		} 
		 
		 m = getDForm(request, form, ent);
		 
		//Se toma el año del request, en vez del mapa
			m.setAnioSiniestro(Integer.parseInt(String.valueOf(request.getSession().getAttribute("anioSiniestro"))));
		 
		 log.info("mapa antes nota "+ m);
		
		 
		 NotaTecnica nt = new NotaTecnica();
		
		nt.setAnioSiniestro(m.getAnioSiniestro());
		nt.setIdSiniestro(m.getIdSiniestro());
		nt.setObservacion(m.getObservacion());
		nt.setLoginUsuario(usuarioSession(request).getCedula());
		nt.setIdDependencia(usuarioSession(request).getIdDependencia());
		//nt.setDesUsuario(usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido());
		log.info("nota "+ nt.getAnioSiniestro());
		int idNota = pnt.insert(nt);
		incluirTraza(TR_NOTATECNICA_CARGAR, String.valueOf(idNota), "Cargar Nota Medica", usuarioSession(request));
		DynaActionForm dForm = (DynaActionForm)form; 
		dForm.set("observacion", "");
		try{
		
			request.setAttribute("listNotaTecnica", pnt.listBySiniestro(m));
		}catch(PersonalNotFoundException e){
		}catch(Exception e){
			log.error("error ", e);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

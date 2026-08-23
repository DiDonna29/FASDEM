package ve.gob.dem.fasdem.action.administradores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Patologias;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerPatologias;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class AdminPatologias extends GenericAction{

	
	Logger log = Logger.getLogger(AdminEspecialidad.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
				
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.info("entre a AdminPatologias");	
		ActionMessages am = new ActionMessages();
		Entorno ent1 = new Entorno(Entorno.MOD_EDITA_PATOLOGIA);
		Entorno ent2 = new Entorno(Entorno.MOD_INGRESA_PATOLOGIA);
		DynaActionForm dForm = (DynaActionForm) form;
		Mapa mapa = new Mapa();
		PerPatologias p = new PerPatologias();
		Patologias P = null;

		if ("editar".equals(request.getParameter("accion"))) {
			log.info("entre a editar");	
			
				try {
					log.info("valido 2 do entorno");
				validarAction(request, form, ent2, am, this.getClass());
				}catch (PersonalNotFillItems e) {
					log.info("entre en el catch de editar");
				}
			
			
				log.info("voy a recoger del formulario");	
			mapa = getDForm(request, form, ent2);
			log.info("MAPA: " + mapa);
			log.info("idPatologia: " + request.getParameter("idPatologia"));
			mapa.setId(Integer.parseInt(request.getParameter("idPatologia")));
			p.updatePatologia(mapa);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"env.general.sms"));
			saveMessages(request, am);
			P = p.search(mapa.getId());
			
			setEntorno(request, form, ent1);
			dForm.set("idPatologia", P.getId());

		} else {

			try {
				log.info("voy a validar");
				log.info("request" +request);
				log.info("form" +form);
				log.info("ent1" +ent1);
				validarAction(request, form, ent1, am, this.getClass());
				request.setAttribute("myhref", mapping.getParameter());
				log.info("entre en el try");
				log.info("Construyes el otro entorno");
				// Construyes el otro entorno

				mapa = getDForm(request, form, ent1);
				mapa.setIdPatologias(Integer.parseInt(request.getParameter("patologia")));
				mapa.setId(mapa.getIdPatologias());
				log.info("MAPA: " + mapa);

				log.info("patologia "+request.getParameter("patologia"));

				log.info("Obtiene ID: " + mapa.getIdPatologias());
				P = p.search(mapa.getId());
				
				request.setAttribute("idPatologia", P.getId());
				dForm.set("descripcion", P.getDescripcion());
				setEntorno(request, form, ent2);
				

			} catch (PersonalNotFillItems e) {

				log.info("entre en el catch "+ e );
				

			}

		}
		return mapping.findForward(FWD_INPUT);


	}
	
}

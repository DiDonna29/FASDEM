package ve.gob.dem.fasdem.action.administradores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class AdminCobertura extends GenericAction {



	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent1 = new Entorno(Entorno.MOD_EDITA_COBERTURA);
		Entorno ent2 = new Entorno(Entorno.MOD_INGRESA_COBERTURA);
		DynaActionForm dForm = (DynaActionForm) form;
		Mapa mapa = new Mapa();
		PerCobertura p = new PerCobertura();
		Cobertura P = null;
		if ("editar".equals(request.getParameter("accion"))) {

			
				try {
					log.info("valido 2 do entorno");
				validarAction(request, form, ent2, am, this.getClass());
				}catch (PersonalNotFillItems e) {
					log.info("entre en el catch");
				}
			
			
			
			mapa = getDForm(request, form, ent2);
			log.info("MAPA: " + mapa);
			log.info("idCobertura: " + request.getParameter("idCobertura"));
			mapa.setId(Integer.parseInt(request.getParameter("idCobertura")));
			p.updateCobertura(mapa);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"env.general.sms"));
			saveMessages(request, am);
			P = p.searchCobertura1(mapa.getId());
			
			setEntorno(request, form, ent1);
			dForm.set("idCobertura", P.getId());

		} else {

			try {
				validarAction(request, form, ent1, am, this.getClass());
				request.setAttribute("myhref", mapping.getParameter());
				log.info("entre en el try");
				log.info("Construyes el otro entorno");
				// Construyes el otro entorno

				mapa = getDForm(request, form, ent1);
				mapa.setIdCobertura(Integer.parseInt(request.getParameter("idCobertura")));
				mapa.setId(mapa.getIdCobertura());
				log.info("MAPA: " + mapa);

				log.info("idCobertura "+request.getParameter("idCobertura"));

				log.info("Obtiene ID: " + mapa.getIdCobertura());
				P = p.searchCobertura1(mapa.getId());
				
				request.setAttribute("idCobertura", P.getId());
				dForm.set("monto", P.getMonto());
				setEntorno(request, form, ent2);
				

			} catch (PersonalNotFillItems e) {

				log.info("entre en el catch");

			}

		}
		return mapping.findForward(FWD_INPUT);


	}
}

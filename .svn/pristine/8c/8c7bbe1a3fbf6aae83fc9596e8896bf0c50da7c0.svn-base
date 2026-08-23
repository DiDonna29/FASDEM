package ve.gob.dem.fasdem.action.administradores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerTipoGasto;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class AgregarTipoGasto extends GenericAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_AGREGA_TIPO_GASTO);

		Mapa mapa = new Mapa();
		PerTipoGasto p = new PerTipoGasto();


		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
		return mapping.findForward(FWD_INPUT);
		}	
		
		mapa = getDForm(request, form, ent);
		log.info("MAPA: " + mapa);
		log.info("Descripcion: " + request.getParameter("descripcion"));
		mapa.setDescripcion(request.getParameter("descripcion"));
		
		p.insert(mapa);
		am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"env.general.sms"));
		saveMessages(request, am);	
		
		
		return mapping.findForward(FWD_INPUT);
		
		
		
		
	}
	
	
}

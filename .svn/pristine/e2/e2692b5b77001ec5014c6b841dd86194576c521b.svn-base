


package ve.gob.dem.fasdem.action.reembolsos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class ReembolsoFirst extends GenericAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSO);
		request.setAttribute(KEY_TIPO_COBERTURA, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		PerSiniestro perSin = new PerSiniestro();
 
	
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		try {
		
		perSin.insert(mapa);
		} catch (Exception e) {
			return mapping.findForward(FWD_SUCCESS);
		
		}
		return mapping.findForward(FWD_INPUT);
	}
}
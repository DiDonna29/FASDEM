package ve.gob.dem.fasdem.action.comunes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarProveedorP extends GenericAction {

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCAR_PROVEEDOR_P);
		
		PerProveedor pp = new PerProveedor();
		
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		
		Mapa m = new Mapa();
		m = getDForm(request, form, ent);
		List list = null;
		
		try {

			list = pp.porNombreMod(m);

		} catch (PersonalNotFoundException e) {
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			list = new ArrayList();
		}
		request.setAttribute(LIST_PROVEEDOR, list);
		return mapping.findForward(FWD_INPUT);
	}
}

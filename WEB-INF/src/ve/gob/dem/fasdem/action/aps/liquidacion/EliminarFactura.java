/**
 * 02/03/2011 16:25:35
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.aps.liquidacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;



import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class EliminarFactura extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Mapa mapa = new Mapa();
		int idSini = Integer.parseInt(request.getParameter("idSini"));
		int idFact = Integer.parseInt(request.getParameter("idFact"));
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Siniestro s = null;
		DynaActionForm dForm = (DynaActionForm)form;
		PerSiniestro ps = new PerSiniestro();
		PerFactura pf = new PerFactura();
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		mapa.setIdSiniestro(idSini);
		mapa.setIdFactura(idFact);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		s = ps.search(mapa);
		request.setAttribute("siniestro", s);
		if (s.getEstatus().isEditable()) {
			try {
				pf.delFactura(mapa);
				incluirTraza(TR_LIQUIDACION_ELIMINAR_FACTURA, String.valueOf(idFact), "Eliminar Factura", usuarioSession(request));
			} catch (Exception e) {
				log.error("error", e);
			}
		} else {
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.noeditable"));
			saveMessages(request, am);
		}
		try {
			Mapa m = new Mapa();
			m.setIdSiniestro(s.getId());
			m.setAnioSiniestro(getAnioBusqueda(request));
			request.setAttribute("facturas", pf.listSearchIdSiniestro(m));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("facturas", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("facturas", null);
		}
		ent = new Entorno(Entorno.MOD_APS_LIQUIDAR);
		request.setAttribute(KEY_ENTORNO, ent);
		dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
		request.setAttribute("form_action", "/security/aps/liquidacion/liquidarAps.do");
		return mapping.findForward(FWD_INPUT);
	}
}

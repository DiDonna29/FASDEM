/**
 * 
 */
package ve.gob.dem.fasdem.action.administrador.estatusSiniestro;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import ve.gob.dem.fasdem.bean.DetalleFacturaPago;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.pagos.ExpDetalleFacturaPago;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * @date 20/01/2012 fasdem
 */
public class ListarSiniestros extends GenericAction {
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCAR_SINIESTRO);
		Mapa m = new Mapa();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		m = getDForm(request, form, ent);
		ArrayList<DetalleFacturaPago> list = new ArrayList<DetalleFacturaPago>();
		try {
			list = ExpDetalleFacturaPago.buscarListaCodigoSiniestroEstatus(m.getCodigo(), String.valueOf(m.getAnioBusqueda()));
		} catch (Exception e) {
			log.error("error", e);
		}
		if (list.size() > 0) {
			request.setAttribute("lista", list);
		} else {
			request.setAttribute("lista", null);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

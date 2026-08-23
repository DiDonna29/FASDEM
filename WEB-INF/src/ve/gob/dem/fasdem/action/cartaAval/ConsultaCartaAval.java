package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
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
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class ConsultaCartaAval extends GenericAction implements Serializable {
	/**
	 * figumare
	 */
	private static final long serialVersionUID = 3704522348829258003L;

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
		Mapa m = new Mapa();
		PerSiniestro ps = new PerSiniestro();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		m = getDForm(request, form, ent);
		m.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
		try {
			List resultado = ps.searchMultiple(m);
			request.setAttribute("resultado", resultado);
		} catch (PersonalNotFoundException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
		}
		return mapping.findForward(FWD_INPUT);
	}
}
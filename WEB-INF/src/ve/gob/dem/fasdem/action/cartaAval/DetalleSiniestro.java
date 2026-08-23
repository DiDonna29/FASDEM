package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class DetalleSiniestro extends GenericAction implements Serializable {

	/**
	 * figumare
	 */
	private static final long serialVersionUID = -8289729677454183548L;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		PerSiniestro ps = new PerSiniestro();
		String idSini = "";
		String anioSini = "";
		Siniestro s = null;
		EstatusTipoTramite estTipTra = new EstatusTipoTramite();
		PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
		Mapa mapa = new Mapa();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		idSini = request.getParameter("idSini");
		anioSini = request.getParameter("anioSini");
		if (idSini == null) {
			idSini = "";
			anioSini = "";
		}
		if ("".equals(idSini)) {
			return mapping.findForward(FWD_INPUT);
		}
		try {
			mapa.setAnioSiniestro(Integer.parseInt(anioSini));
			mapa.setIdSiniestro(Integer.parseInt(idSini));
			s = ps.search(mapa);
			// Verifico si según su estatus genera reporte
			mapa.setIdEstatus(s.getEstatus().getId());
			mapa.setIdTipoTramite(s.getTipoTramite().getId());
			try {
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(mapa);
				if (estTipTra.getReporte() != null) {
					request.setAttribute("tipoImpresion",
							COD_TIPO_REPORTE_CARTA_AVAL);
				}
			} catch (PersonalNotFoundException e) {
			}
			request.setAttribute("siniestro", s);
		} catch (PersonalNotFoundException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
		}
		return mapping.findForward(FWD_INPUT);
	}
}
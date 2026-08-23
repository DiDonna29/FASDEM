package ve.gob.dem.fasdem.action.reembolsos.liquidacion;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class CambioEstatusLiquidado extends GenericAction {

	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

		Mapa mapa = new Mapa();
		int idSini = Integer.parseInt(request.getParameter("id"));
		Siniestro s = null;
		PerSiniestro ps = new PerSiniestro();
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		mapa.setIdSiniestro(idSini);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		s= ps.search(mapa);
		request.setAttribute("siniestro", s);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		mapa = getDForm(request, form, ent);
		mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
		mapa.setFechaLiquidacion(new Date());
		mapa.setIdSiniestro(s.getId());
		mapa.setMontoAmparado(s.getMontoAmparado());
		ps.updateLiquidacion(mapa);
		incluirTraza(TR_REEMBOLSO_LIQUIDAR_SINIESTRO,
				String.valueOf(idSini),
				"LIQUIDACION DE SINIESTRO ", usuarioSession(request));
		am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
		saveMessages(request, am);
		return mapping.findForward(FWD_SUCCESS);
	}
}

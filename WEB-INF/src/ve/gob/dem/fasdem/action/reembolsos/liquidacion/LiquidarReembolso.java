/**
 * 02/03/2011 14:57:46
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.reembolsos.liquidacion;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class LiquidarReembolso extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSO_LIQUIDAR);
		Mapa mapa = new Mapa();
		int idSini = Integer.parseInt(request.getParameter("id"));
		Siniestro s = null;
		PerSiniestro ps = new PerSiniestro();
		PerFactura pf = new PerFactura();
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		mapa.setIdSiniestro(idSini);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		s = ps.search(mapa); 
		request.setAttribute("siniestro", s);
		DynaActionForm dForm = (DynaActionForm) form; 
		dForm.set("anioBusqueda", Utilidad.DateToString(new Date(), "yyyy"));
		dForm.set("fechaRecepcionFactura",Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
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
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setIdSiniestro(s.getId());
		mapa.setPorcentajeIsrl(1);
		mapa.setPorcentajeTimbre(1);
		mapa.setPorcentajeIva(1);
		mapa.setMontoAmparado(s.getMontoAmparado());
		pf.insert(mapa);
		try {
			Mapa m = new Mapa();
			m.setIdSiniestro(s.getId());
			m.setAnioSiniestro(getAnioBusqueda(request));
			request.setAttribute("facturas", pf.listSearchIdSiniestro(m));
			dForm.set("fechaRecepcionFactura",Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
			dForm.set("numeroFactura","");
			dForm.set("montoFactura","");
			dForm.set("fechaFactura","");
			
			
		} catch (PersonalNotFoundException e) {
			request.setAttribute("facturas", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("facturas", null);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

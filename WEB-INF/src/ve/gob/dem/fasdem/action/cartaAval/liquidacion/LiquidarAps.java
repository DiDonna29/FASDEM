/**
 * 02/03/2011 14:57:46
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.cartaAval.liquidacion;

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
import ve.gob.dem.fasdem.valores.CargaValores;
import ve.gob.dem.fasdem.valores.Valores;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class LiquidarAps extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Valores v = CargaValores.getInstance().getValores();
		Entorno ent = new Entorno(Entorno.MOD_APS_LIQUIDAR);
		Mapa mapa = new Mapa();
		int idSini = 0;
		if (request.getParameter("id") != null) {
			idSini = Integer.parseInt(request.getParameter("id"));
		} else {
			idSini = (Integer) request.getAttribute("id");
		}
		Siniestro s = null;
		PerSiniestro ps = new PerSiniestro();
		PerFactura pf = new PerFactura();
		// request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		mapa.setIdSiniestro(idSini);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		s = ps.search(mapa);
		request.setAttribute("siniestro", s);
		DynaActionForm dForm = (DynaActionForm) form;
		dForm.set("idSiniestro", String.valueOf(s.getId()));
		dForm.set("anioBusqueda", Utilidad.DateToString(new Date(), "yyyy"));
		try {
			Mapa m = new Mapa();
			m.setIdSiniestro(s.getId());
			m.setAnioSiniestro(getAnioBusqueda(request));
			m.setIssFactura(true);
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
		mapa.setPorcentajeIsrl(v.getIslr());
		mapa.setPorcentajeTimbre(v.getTimbreFiscal());
		mapa.setPorcentajeIva(v.getIva());
		mapa.setMontoAmparado(s.getMontoAmparado());
		pf.insert(mapa);
		limíarDform(dForm);
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
		return mapping.findForward(FWD_INPUT);
	}

	private void limíarDform(DynaActionForm dForm) {
		dForm.set("numeroFactura", " ");
		dForm.set("controlFactura", " ");
		dForm.set("fechaFactura", " ");
		dForm.set("fechaRecepcionFactura", " ");
		dForm.set("montoFactura", " ");
	}
}

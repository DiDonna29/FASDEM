/**
 * 02/03/2011 14:57:46
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.reembolsos.liquidacion;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.valores.CargaValores;
import ve.gob.dem.fasdem.valores.Valores;
import ve.gob.dem.framework.exception.PersonalFacturaNoFillException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class LiquidarReembolsos extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Valores v = CargaValores.getInstance().getValores();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSO_LIQUIDAR);
		Mapa mapa = new Mapa();
		List facturas = null;
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
		log.info("info recepcion tramite " +s.getTipoTramite().getId());
		if (s.getTipoTramite().getId() == 4) {
			request.setAttribute("fechaFactura", s.getFechaNotificacion());
		}
		if (s.getTipoTramite().getId() == 1) {
			request.setAttribute("fechaFactura", s.getFechaIngreso());
		}
		if (s.getTipoTramite().getId() == 2) {
			request.setAttribute("fechaFactura", s.getFechaIngreso());
		}
		if (s.getTipoTramite().getId() == 3) {
			log.info("info recepcion fact 1005 " +s.getFechaNotificacion());
			request.setAttribute("fechaRecepcionFactura",Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
		}
		
		try {
			mapa.setIssFactura(true);
			facturas = pf.listSearchIdSiniestro(mapa);
		} catch (PersonalNotFoundException e) {
			request.setAttribute("facturas", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("facturas", null);
		}
		request.setAttribute("siniestro", s);
		DynaActionForm dForm = (DynaActionForm) form;

		dForm.set("id", 
				String.valueOf(s.getId()));
		dForm.set("anioBusqueda", Utilidad.DateToString(new Date(), "yyyy"));
		//dForm.set("anioBusqueda", Utilidad.DateToString(new Date(), "yyyy"));
		//dForm.set("fechaRecepcionFactura",Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
		request.setAttribute("facturas", facturas);
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
		if (calcularDisponibleFacturas(facturas, mapa.getMontoFactura(), s.getMontoAmparado())) {
			pf.insert(mapa);
			limpiarDform(dForm);
			dForm.set("fechaRecepcionFactura",Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
		} else {
			am.add(ALERT_AVISOS, new ActionMessage("general.monto.excedesiniestro", mapa.getMontoFactura()));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		try {
			mapa.setIssFactura(true);
			facturas = pf.listSearchIdSiniestro(mapa);
		} catch (PersonalNotFoundException e) {
			request.setAttribute("facturas", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("facturas", null);
		}
		request.setAttribute("facturas", facturas);
		return mapping.findForward(FWD_INPUT);
	}

	private void limpiarDform(DynaActionForm dForm) {
		dForm.set("numeroFactura", "");
		//dForm.set("controlFactura", "");
		dForm.set("fechaFactura", "");
		dForm.set("montoFactura", "");
	}

	@SuppressWarnings("rawtypes")
	private boolean calcularDisponibleFacturas(List facturas, double nuevoMonto, double montoSiniestro) throws PersonalFacturaNoFillException, SQLException {
		Factura f = null;
		double subTotal = 0.0;
		if (facturas != null) {
			for (int i = 0; i < facturas.size(); i++) {
				f = (Factura) facturas.get(i);
				subTotal = subTotal + f.getMontoFactura();
			}
		}
		if (montoSiniestro < (subTotal + nuevoMonto)) {
			return false;
		}
		return true;
	}
}

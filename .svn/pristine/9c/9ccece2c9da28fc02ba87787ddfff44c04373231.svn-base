package ve.gob.dem.fasdem.action.cargaFactura;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.recursos.Utilidad;

public class SetupEditFactura extends CargarDetalleFactura {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		DynaActionForm dForm = (DynaActionForm) form;
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Factura f = new Factura();

		PerFactura pf = new PerFactura();

		Mapa m = new Mapa();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		getValores(m, request);
		f = pf.search(m);
		dForm.set("numeroFactura", f.getNumeroFactura());
		dForm.set("controlFactura", f.getControlFactura());
		try {
			dForm.set("fechaFactura", Utilidad.DateToString(f.getFechaFactura(), "dd/MM/yyyy"));
		} catch (PersonalException e) {
			dForm.set("fechaFactura", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));
			log.info("informativo ", e);
		}
		try {
			dForm.set("fechaRecepcionFactura", Utilidad.DateToString(f.getFechaRecepcionFactura(), "dd/MM/yyyy"));
		} catch (PersonalException e) {
			dForm.set("fechaRecepcionFactura", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));
			log.info("informativo ", e);
		}
		dForm.set("montoFactura", String.valueOf(f.getMontoFactura()));
		dForm.set("idFactura", String.valueOf(m.getIdFactura()));
		dForm.set("idSiniestro", String.valueOf(m.getIdSiniestro()));
		dForm.set("anioSiniestro", String.valueOf(f.getAnioSiniestro()));
		ent = new Entorno(Entorno.MOD_APS_LIQUIDAR);
		request.setAttribute(KEY_ENTORNO, ent);
		request.setAttribute("form_action", mapping.getParameter());
		return mapping.findForward(FWD_INPUT);
	}
	

}

package ve.gob.dem.fasdem.action.comunes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerDetalleFactura;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class CargadeFactura extends GenericAction {

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARGA_FACTURA);
		Mapa mapa = new Mapa();
		PerFactura perFac = new PerFactura();
		PerDetalleFactura pdf = new PerDetalleFactura();

		int idFactura;

		Date fechahoy = new Date();
		int idSin = 0;
		int anioSin = 0;
		double montoPre = 0.0;
		List listaFactura = new ArrayList();
		if (!"".equals(request.getParameter("idS")) && request.getParameter("idS") != null) {
			idSin = Integer.parseInt(request.getParameter("idS"));
			montoPre = Double.parseDouble(request.getParameter("montop"));
			anioSin = Integer.parseInt(request.getParameter("anioS"));
		}
		try {
			mapa.setIdSiniestro(idSin);
			mapa.setAnioSiniestro(anioSin);
			listaFactura = perFac.listDetallesFactura(mapa);
			request.setAttribute("listaFactura", listaFactura);
		} catch (PersonalNotFoundException e) {

		}

		request.setAttribute("nroSin", request.getParameter("nroSin"));
		request.setAttribute("idS", idSin);
		request.setAttribute("anioSin", anioSin);
		request.setAttribute("montop", request.getParameter("montop"));
		DynaActionForm dForm = (DynaActionForm) form;
		try {

			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setPorcentajeIva(COD_IVA);
		mapa.setPorcentajeTimbre(COD_TIMBRE);
		mapa.setPorcentajeIsrl(COD_ISLR);
		mapa.setMontoPresupuestado(montoPre);
		mapa.setIdSiniestro(idSin);
		mapa.setAnioSiniestro(anioSin);
		mapa.setFechaRecepcionFactura(fechahoy);
		idFactura = perFac.insert(mapa);
		mapa.setIdFactura(idFactura);
		pdf.insert(mapa);

		dForm.set("fechaFactura", null);
		dForm.set("numeroFactura", null);
		dForm.set("controlFactura", null);
		dForm.set("montoFactura", null);
		dForm.set("montoAmparado", null);
		dForm.set("montoNoAmparado", null);
		dForm.set("tipoGasto", "");

		listaFactura = perFac.listDetallesFactura(mapa);
		request.setAttribute("listaFactura", listaFactura);
		request.setAttribute("idS", mapa.getIdSiniestro());
		request.setAttribute("anioSin", mapa.getAnioSiniestro());

		return mapping.findForward(FWD_INPUT);

	}
}

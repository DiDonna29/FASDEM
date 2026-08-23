package ve.gob.dem.fasdem.action.reembolsos;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class ConsultaReembolsos extends GenericAction {
	Logger log = Logger.getLogger(ConsultaReembolsos.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSOS_CONSULTA);
		request.setAttribute(KEY_TIPO_COBERTURA, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		Factura factura = new Factura();
		Factura siniestrofactura = new Factura();
		Factura detalleFactura = new Factura();
		PerFactura perFactura = new PerFactura();
		List listaFactura=new ArrayList();

		
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		
		PerSiniestro op = new PerSiniestro();
		Siniestro Op = null;

		

		try {

			Op = op.searchNumeroR(mapa.getSubCodigo());
			Op.getId();
			
			request.setAttribute("Op", Op);

		} catch (PersonalNotFoundException e1) {
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}

		try {

			//siniestrofactura = listaFacturacr.listByFacturaRembolso(Op.getId());
			siniestrofactura.getIdFactura();
			factura = perFactura.search(mapa);
			factura.getId();
			//listaFactura=perFactura.listFacturaRembolso(mapa);
			request.setAttribute("listaFactura", listaFactura);
			detalleFactura=perFactura.searchDetalle(mapa);
			request.setAttribute("siniestrofactura", siniestrofactura);
			request.setAttribute("factura", factura);
			request.setAttribute("detalleFactura", detalleFactura);

		} catch (PersonalNotFoundException e1) {

		}
	
		return mapping.findForward(FWD_SUCCESS);

	}

}

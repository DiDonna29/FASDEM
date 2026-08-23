package ve.gob.dem.fasdem.action.liquidacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class UnofLiquidaCartaAval extends GenericAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_LIQUIDACION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_CARTAAVAL);
		Mapa mapa = new Mapa();
		PerSiniestro perSiniestro = new PerSiniestro();
		PerFactura perFactura = new PerFactura();
		Date fechahoy = new Date();
		Calendar f = Calendar.getInstance();
		Factura factura = new Factura();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setTime(fechahoy);
		formato.format(f.getTime());
		String fecha = formato.format(f.getTime());

		int idSiniestro = Integer.parseInt(request.getParameter("id"));
		Siniestro Op = perSiniestro.search(mapa);
		request.setAttribute("Op", Op);

		

		try {

			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {

			return mapping.findForward(FWD_INPUT);
		}

		mapa = getDForm(request, form, ent);
		mapa.setId(idSiniestro);
		mapa.setIdSiniestro(idSiniestro);

	
		
		mapa.setFechaLiquidacion(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
		mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
		mapa.setIssFactura(true);
		log.info("mi factura es esta mi id e3dfffffs "+idSiniestro);
		factura= perFactura.searchIdSiniestro(mapa);
		mapa.setIdFactura(factura.getId());
		mapa.setId(factura.getId());
		log.info("mi factura es esta mi id es "+mapa.getId());
		try {

			perSiniestro.updateLiquidacion(mapa);
			Siniestro s = perSiniestro.search(mapa);
			request.setAttribute("s", s);
			log.info("mi factura es esta mi id e222s "+mapa.getId());
			perFactura.updateFactura(mapa);

			log.info("mi factura es esta mi id e3333s "+mapa.getId());
			request.setAttribute("factura", factura);
			
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"env.general.sms"));
			saveMessages(request, am);

			

		} catch (Exception e) {
			log.error("mi errorororor ",e);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"env.general.operacionerronea"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);

		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

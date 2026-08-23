package ve.gob.dem.fasdem.action.liquidacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class LiquidaEmergencia extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_EMERGENCIA_LIQUIDACION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
		Mapa mapa = new Mapa();
		PerSiniestro perSiniestro = new PerSiniestro();
		PerFactura perFactura = new PerFactura();
		Date fechahoy = new Date();
		Calendar f = Calendar.getInstance();
		Factura factura = new Factura();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setTime(fechahoy);
		Siniestro s = new Siniestro();
		Factura detalleFactura = new Factura();
		List listFactura = new ArrayList();
		List listDetalle = new ArrayList();
		formato.format(f.getTime());
		String fecha = formato.format(f.getTime());
		int idSiniestro = Integer.parseInt(request.getParameter("id"));
		Mapa m = new Mapa();
		m.setIdSiniestro(Integer.parseInt(request.getParameter("id")));
		m.setAnioSiniestro(getAnioBusqueda(request));
		Siniestro Op = perSiniestro.search(m);
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
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		factura = perFactura.searchIdSiniestro(mapa);
		mapa.setIdFactura(factura.getId());
		mapa.setId(factura.getId());
		try {
			perSiniestro.updateLiquidacionEmergencia(mapa);
			s = perSiniestro.search(mapa);
			request.setAttribute("siniestro", s);
			perFactura.updateFactura(mapa);
			request.setAttribute("factura", factura);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
		} catch (Exception e) {
			log.error("mi errorororor ", e);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.operacionerronea"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		}
		try {
			mapa.setIdSiniestro(s.getId());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			log.info("mi mapa es el " + mapa);
			listFactura = perFactura.listSearchIdSiniestro(mapa);
			log.info("mi size es el " + listFactura.size());
			for (int r = 0; r <= listFactura.size(); r++) {
				log.info("1mi size es el " + listFactura.size());
				factura = (Factura) listFactura.get(r);
				factura.getId();
				mapa.setIdFactura(factura.getId());
				mapa.setAnioSiniestro(s.getAnioSiniestro());
				listDetalle = perFactura.listDetallesFactura(mapa);
				for (int i = 0; i <= listDetalle.size(); i++) {
					detalleFactura = (Factura) listDetalle.get(i);
					request.setAttribute("factura", factura);
					request.setAttribute("detalleFactura", detalleFactura);
					request.setAttribute("listFactura", listFactura);
					request.setAttribute("listDetalle", listDetalle);
				}
			}
		} catch (Exception e1) {
			factura = new Factura();
			detalleFactura = new Factura();
			log.error("eeeeeee", e1);
			request.setAttribute("factura", factura);
			request.setAttribute("detalleFactura", detalleFactura);
			request.setAttribute("listFactura", listFactura);
			request.setAttribute("listDetalle", listDetalle);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

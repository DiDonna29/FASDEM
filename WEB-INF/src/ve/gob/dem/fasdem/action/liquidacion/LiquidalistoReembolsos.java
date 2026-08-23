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

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class LiquidalistoReembolsos extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REEMBOLSO_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		Siniestro Op = null;
		PerSiniestro perSiniestro = new PerSiniestro();
		Date fechahoy = new Date();
		Calendar f = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setTime(fechahoy);
		formato.format(f.getTime());
		String fecha = formato.format(f.getTime());
		int idSin = 0;
		int anioSin = 0;
		int idSiniestro = Integer.parseInt(request.getParameter("id"));
		log.info("aniooooooo" + request.getParameter("anioS"));
		log.info("montooooooooooooooo" + Integer.parseInt(request.getParameter("id")));
		anioSin = Integer.parseInt(request.getParameter("anioS"));
		idSin = Integer.parseInt(request.getParameter("id"));
		mapa.setAnioSiniestro(anioSin);
		mapa.setIdSiniestro(idSin);
		Op = perSiniestro.search(mapa);
		Op.getId();
		request.setAttribute("Op", Op);
		request.setAttribute("siniestro", Op);
		request.setAttribute("myhref", mapping.getParameter());
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setId(idSiniestro);
		mapa.setIdSiniestro(idSiniestro);
		mapa.setAnioSiniestro(anioSin);
		mapa.setFechaLiquidacion(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
		mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
		mapa.setMontoGastosClinicos(Double.parseDouble(request.getParameter("tgastosClinicos")));
		mapa.setMontoHonorariosMedicos(Double.parseDouble(request.getParameter("thonoMedicos")));
		mapa.setMontoFactura(Double.parseDouble(request.getParameter("totalFacturado")));
		mapa.setMontoNoAmparado(Double.parseDouble(request.getParameter("totalMontoNoAmparado")));
		mapa.setMontoLiquidado(Double.parseDouble(request.getParameter("totalAliquidar")));
		mapa.setMontoAmparado(Double.parseDouble(request.getParameter("totalAliquidar")));
		log.info("mi id es " + mapa.getId());
		try {
			mapa.setIdSiniestro(idSin);
			mapa.setAnioSiniestro(anioSin);
			log.info("aniooooooo" + request.getParameter("anioS"));
			perSiniestro.updateLiquidacion(mapa);
			Siniestro s = perSiniestro.search(mapa);
			request.setAttribute("s", s);
			request.setAttribute("Op", Op);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
		} catch (Exception e) {
			log.error("mi errorororor ", e);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.operacionerronea"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

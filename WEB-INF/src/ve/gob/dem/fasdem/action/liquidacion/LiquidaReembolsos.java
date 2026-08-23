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
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class LiquidaReembolsos extends GenericAction {
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
		Factura factura = new Factura();
		int idSiniestro = Integer.parseInt(request.getParameter("id"));
		mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy")));
		mapa.setIdSiniestro(idSiniestro);
		Op = perSiniestro.search(mapa);
		Op.getId();
		request.setAttribute("Op", Op);
		request.setAttribute("siniestro", Op);
		request.setAttribute("myhref", mapping.getParameter());
		try {
			/*
			 * Siniestro s = ps.searchByCodigo(mapa); dForm.set("idSiniestro",
			 * new Integer(s.getProveedor().getId()).toString());
			 * dForm.set("proveedor", s.getProveedor().getDescripcion());
			 */
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		log.info("mapa rn" + mapa);
		// dForm.set("totalFacturado", Siniestro.getMontoPresupuestado());
		/*
		 * mapa.setId(idSiniestro); mapa.setIdSiniestro(idSiniestro);
		 * mapa.setFechaLiquidacion(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
		 * mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
		 */
		log.info("mi id de reembolso " + idSiniestro);
		log.info("mi id es " + mapa.getId());
		try {
			/*
			 * perSiniestro.updateLiquidacion(mapa); Siniestro s =
			 * perSiniestro.search(mapa.getIdSiniestro());
			 * request.setAttribute("s", s); log.info("id e222s "+mapa.getId());
			 */
			log.info("e3333s " + mapa.getId());
			request.setAttribute("factura", factura);
			request.setAttribute("Op", Op);
			log.info("MIVARIABLEEEEEEEEEEE" + Op);
			log.info("a donde va " + mapping.getParameter());
			return mapping.findForward(FWD_SUCCESS);
		} catch (Exception e) {
			log.error("mi error ", e);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.operacionerronea"));
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		}
	}
}

package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class EgresarCartaAval extends GenericAction implements Serializable {
	/**
	 * figumare
	 */
	private static final long serialVersionUID = 8451079994794023120L;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_EGRESAR);
		Mapa m = new Mapa();
		PerSiniestro ps = new PerSiniestro();
		Date today = new Date();
		Date fi = new Date();
		Date fe = new Date();
		Siniestro s = new Siniestro();
		DynaActionForm dForm = (DynaActionForm) form;
		EstatusTipoTramite estTipTra = new EstatusTipoTramite();
		PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
		// es la primera vez
		if (request.getParameter("accion") == null) {
			m.setIdSiniestro(Integer.parseInt((String) request.getSession()
					.getAttribute("idSini")));
			m.setAnioSiniestro(Integer.parseInt((String) request.getSession()
					.getAttribute("anioSiniestro")));
			// busco el siniestro que quiere egrear
			s = ps.search(m);
			ent = new Entorno(Entorno.MOD_CARTA_AVAL_EGRESAR);
			setEntorno(request, form, ent);
			request.setAttribute("siniestro", s);
			dForm.set("citaPreOperatorio",
					String.valueOf(s.isCitaPreOperatorio()));
			dForm.set("citaPostOperatorio",
					String.valueOf(s.isCitaPostOperatorio()));
			dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
			dForm.set("id", String.valueOf(s.getId()));
			dForm.set("montoPresupuestado",
					String.valueOf(s.getMontoPresupuestado()));
			dForm.set("montoNegociado", String.valueOf(s.getMontoNegociado()));
			dForm.set("montoAmparado", String.valueOf(s.getMontoAmparado()));
			if (s.getFechaIngreso() != null) {
				dForm.set("fechaIngreso", Utilidad.DateToString(
						s.getFechaIngreso(), "dd/MM/yyyy"));
			}
			if (s.getFechaEgreso() != null) {
				dForm.set("fechaEgreso",
						Utilidad.DateToString(s.getFechaEgreso(), "dd/MM/yyyy"));
			}
			request.getSession().setAttribute("anioSiniestro",
					request.getAttribute("anioSiniestro"));
			request.getSession().setAttribute("idSini",
					request.getAttribute("idSini"));
			return mapping.findForward(FWD_INPUT);
		}
		if ("".equals(request.getParameter("accion"))) {
			ent = new Entorno(Entorno.MOD_CARTA_AVAL_EGRESAR);
			setEntorno(request, form, ent);
			try {
				validarAction(request, form, ent, am, this.getClass());
			} catch (PersonalNotFillItems e) {
			}
			m.setIdSiniestro(Integer.parseInt((String) request.getSession()
					.getAttribute("idSini")));
			m.setAnioSiniestro(Integer.parseInt((String) request.getSession()
					.getAttribute("anioSiniestro")));
			// busco el siniestro que quiere egrear
			s = ps.search(m);
			request.setAttribute("siniestro", s);
			request.getSession().setAttribute("anioSiniestro",
					request.getAttribute("anioSiniestro"));
			request.getSession().setAttribute("idSini",
					request.getAttribute("idSini"));

			return mapping.findForward(FWD_INPUT);
		}
		if ("egresar".equals(request.getParameter("accion"))) {
			ent = new Entorno(Entorno.MOD_CARTA_AVAL_EGRESAR);
			setEntorno(request, form, ent);
			try {
				validarAction(request, form, ent, am, this.getClass());
			} catch (PersonalNotFillItems e) {
				m.setIdSiniestro(Integer.parseInt(request
						.getParameter("idSini")));
				m.setAnioSiniestro(Integer.parseInt(request
						.getParameter("anioSiniestro")));
				// busco el siniestro que quiere egresar
				s = ps.search(m);
				request.setAttribute("siniestro", s);
				return mapping.findForward(FWD_INPUT);
			}
			m = getDForm(request, form, ent);
			m.setMontoNoAmparado(m.getMontoNegociado() - m.getMontoAmparado());
			m.setIdSiniestro(m.getId());
			s = ps.search(m);
			m.setMontoPresupuestado(s.getMontoPresupuestado());
			if (m.getFechaEgreso() != null) {
				// Verifico que la fecha de egreso no puede ser mayor a la fecha
				// de
				// ingreso
				fe = Utilidad
						.StringToDate(Utilidad.DateToString(m.getFechaEgreso(),
								"dd/MM/yyyy"), "dd/MM/yyyy");
				
				fi = Utilidad.StringToDate(Utilidad.DateToString(
						m.getFechaIngreso(), "dd/MM/yyyy"), "dd/MM/yyyy");
				if (fi.compareTo(fe) > 0) {
					am.add(ALERT_AVISOS, new ActionMessage(
							"env.fechaEgreso.menorIngreso"));
					saveMessages(request, am);
					request.setAttribute("siniestro", s);
					return mapping.findForward(FWD_INPUT);
				}
				m.setIdEstatus(COD_ESTATUS_EGRESADO);
			} else {
				m.setIdEstatus(COD_ESTATUS_INGRESADO);
			}
			// edito los datos del siniestro
			m.setFechaUltimaModificacion(today);
			ps.egresarCartaAval(m);
			// ******INSERTAR TRAZA
			incluirTraza(TR_CARTAAVAL_EGRESAR,
					String.valueOf(m.getIdSiniestro()),
					TRDESC_CARTAAVAL_EGRESAR, usuarioSession(request));
			// Verifico si según su estatus genera reporte
			m.setIdEstatus(m.getIdEstatus());
			m.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
			try {
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(m);
				if (!"".equals(estTipTra.getReporte())) {
					request.setAttribute("tipoImpresion",
							COD_TIPO_REPORTE_CARTA_AVAL);
				}
			} catch (PersonalNotFoundException e) {
			}
			s = ps.search(m);
			request.setAttribute("siniestro", s);
			request.getSession().setAttribute("siniestroPadre", null);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
			request.getSession().setAttribute("anioSiniestro",
					request.getParameter("anioSiniestro"));
			request.getSession().setAttribute("idSini",
					request.getParameter("idSini"));
			return mapping.findForward(FWD_SUCCESS);
		}
		return mapping.findForward(FWD_INPUT);
	}
}
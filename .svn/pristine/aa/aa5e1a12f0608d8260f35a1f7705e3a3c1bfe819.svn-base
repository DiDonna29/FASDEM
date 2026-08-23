/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.notaMedica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.action.comunes.BuscarSiniestros;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Medicamento;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.bean.SiniestroPortal;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.exp.ExpSiniestroPortal;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unused")
public class Bandeja extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(Bandeja.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Mapa mapa = new Mapa();
		PerSiniestro perSiniestro = new PerSiniestro();
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		if (request.getSession().getAttribute("APSGUARDADO") != null) {
			am.add(ALERT_AVISOS, new ActionMessage("insert.medico.success"));
			saveMessages(request, am);
			request.getSession().removeAttribute("APSGUARDADO");
		}
		ArrayList lista = null;
		;
		String CantidadAtencion = "0";
		String CantidadPendientes = "0";
		String CantidadAtendidos = "0";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha1 = new Date();
		try {
			try {
				fecha1 = Utilidad.StringToDate(request.getParameter("fecha"), "dd/MM/yyyy");
			} catch (Exception e) {
				fecha1 = new Date();
			}
			String Msn = "";
			if (request.getParameter("accion") != null) {
				if (request.getParameter("accion").equals("1")) {
					perSiniestro.updateBandeja(mapa);
					ExpSiniestroBandeja.CambiarEstatusSiniestroMedico(Integer.parseInt(request.getParameter("siniestro")), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido());
				}
				if (request.getParameter("accion").equals("2")) {
					log.info("entro a la accion 2");
					SiniestroBandeja sin = ExpSiniestroBandeja.BuscarpoIdMedico(Integer.parseInt(request.getParameter("siniestro")), formato.format(fecha1));
					if (sin.getId_estatus() == 1) { // Disponible
						ExpSiniestroBandeja.TomarSiniestroMedico(Integer.parseInt(request.getParameter("siniestro")), 0, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido());
					} else {
						if (sin.getId_estatus() == 0) { // / en Atencion
							Msn = "La solicitud se encuentra siendo atendido por el analista " + sin.getDatosProcesa();
							request.setAttribute("mensaje", Msn);
						}
					}
				}
			}
			if (request.getParameter("fecha") == null || request.getParameter("fecha").equals("")) {
				try {
					lista = ExpSiniestroBandeja.BuscarListaPendientesyAtencionMedico(Utilidad.DateToString(new Date(), "yyyy-MM-dd"));
				} catch (Exception e) {
					log.info("xxxxxx", e);
				}
			} else {
				try {
					request.setAttribute("fecha", request.getParameter("fecha"));
					lista = ExpSiniestroBandeja.BuscarListaPendientesyAtencionMedico(formato.format(fecha1));
				} catch (Exception e) {
					log.info("1xxxxxx", e);
				}
			}
			try {
				CantidadAtencion = ExpSiniestroBandeja.BuscarCantidadEstatusTipoMedico(Constantes.SolicitudEnAtencion, formato.format(fecha1));
			} catch (Exception e) {
				log.info("2xxxxxx", e);
			}
			try {
				CantidadPendientes = ExpSiniestroBandeja.BuscarCantidadEstatusTipoMedico(Constantes.SolicitudPendiente, formato.format(fecha1));
			} catch (Exception e) {
				log.info("3xxxxxx", e);
			}
			try {
				CantidadAtendidos = ExpSiniestroBandeja.BuscarCantidadEstatusTipoFechaMedico(Constantes.SolicitudAtendida, formato.format(fecha1));
			} catch (Exception e) {
				log.info("4xxxxxx", e);
			}
		} catch (Exception e) {
			log.info("sefewferf", e);
			String dateHoy = Utilidad.DateToString(new Date(), "yyyy-MM-dd");
			try {
				CantidadAtencion = ExpSiniestroBandeja.BuscarCantidadEstatusTipoMedico(Constantes.SolicitudEnAtencion, dateHoy);
			} catch (Exception e1) {
				log.info("1sefewferf", e1);
			}
			try {
				CantidadPendientes = ExpSiniestroBandeja.BuscarCantidadEstatusTipoMedico(Constantes.SolicitudPendiente, dateHoy);
			} catch (Exception e2) {
				log.info("2sefewferf", e2);
			}
			try {
				CantidadAtendidos = ExpSiniestroBandeja.BuscarCantidadEstatusTipoFechaMedico(Constantes.SolicitudAtendida, dateHoy);
			} catch (Exception e3) {
				log.info("3sefewferf", e3);
			}
			return mapping.findForward("h1");
		}
		request.setAttribute("usuario_bandeja", usuarioSession(request).getLogin());
		request.setAttribute("lista", lista);
		log.info("C_Atencion:" + CantidadAtencion);
		log.info("C_Pendientes:" + CantidadPendientes);
		log.info("C_Atendidos:" + CantidadAtendidos);
		request.setAttribute("C_Atencion", CantidadAtencion);
		request.setAttribute("C_Pendientes", CantidadPendientes);
		request.setAttribute("C_Atendidos", CantidadAtendidos);
		return mapping.findForward("h1");
	}
}

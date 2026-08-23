package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
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

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class activarCartaAval extends GenericAction implements Serializable {
	/**
	 * figumare
	 */
	private static final long serialVersionUID = 3704522348829258003L;

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Declaro las variables que utilizar�
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
		Mapa m = new Mapa();
		PerSiniestro ps = new PerSiniestro();
		Siniestro s = new Siniestro();
		Date today = new Date();
		List resultado = new ArrayList();
		Calendar fn = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		String reemplazo = "";
		// Si la acci�n es activar, significa que el usuario ya hizo clic en
		// activar y procedo a la activaci�n
		if ("activar".equals(request.getParameter("accion"))) {
			// Capturo los parametros del formulario
			m.setIdSiniestro(Integer.parseInt(request.getParameter("idSini")));
			m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
			// Busco la carta aval
			s = ps.search(m);
			// Seteo los valores
			m.setFechaOcurrencia(today);
			m.setFechaIngreso(today);
			m.setIdEstatus(COD_ESTATUS_INGRESADO);
			m.setIdSiniestro(s.getId());
			// Verifico si est� vigente
			today = s.getFechaNotificacion();
			fn.setTime(today);
			fn.add(Calendar.DAY_OF_MONTH, VIGENCIA_CARTA_AVAL);
			// si est� vigente, la activo
			if (hoy.getTime().before(fn.getTime())) {
				ps.activarCartaAval(m);
				am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
				// ******INSERTAR TRAZA
				incluirTraza(TR_CARTAAVAL_ACTIVAR, String.valueOf(m.getIdSiniestro()), TRDESC_CARTAAVAL_ACTIVAR, usuarioSession(request));
			}// si no est� vigente, le muestro un mensaje
			else {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.carta.vencida"));
			}
			m.setIdSiniestro(Integer.parseInt(request.getParameter("idSini")));
			m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
			// consulto la carta luego de la activaci�n para mostrarla en la
			// vista previa
			s = ps.search(m);
			request.setAttribute("siniestro", s);
			saveMessages(request, am);
			return mapping.findForward(FWD_SUCCESS);
		}// Si no es activar la acci�n significa que el usuario no le ha dado
			// clic a activar, es decir, est� ingresando por primera vez y va a
			// buscafr los siniestros para el listado
		else {
			// Valido si coloco los criterios requeridos para la b�squeda
			try {
				validarAction(request, form, ent, am, this.getClass());
			} catch (PersonalNotFillItems e) {
				return mapping.findForward(FWD_INPUT);
			}
			m = getDForm(request, form, ent);
			if (m.getCodigo() != null) {
				reemplazo = m.getCodigo();
				reemplazo = reemplazo.replace("-", "");
				m.setCodigo(reemplazo);
			}
			m.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
			m.setIdEstatus(COD_ESTATUS_CARTA_COMPROMISO);
			// Busca el listado
			try {
				resultado = ps.searchMultipleByStatus(m);
				request.setAttribute("resultado", resultado);
			} catch (PersonalNotFoundException e) {
				am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		}
		return mapping.findForward(FWD_INPUT);
	}
}
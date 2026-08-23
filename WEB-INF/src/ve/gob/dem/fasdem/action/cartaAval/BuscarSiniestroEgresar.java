package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
import java.util.ArrayList;
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
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarSiniestroEgresar extends GenericAction implements
		Serializable {
	private static final long serialVersionUID = 5861083469142874907L;
	/**
	 * figumare
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PerSiniestro ps = new PerSiniestro();
		Siniestro s = new Siniestro();
		Mapa mapa = new Mapa();
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
		@SuppressWarnings("rawtypes")
		List listado = new ArrayList();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		String reemplazo = "";
		mapa.setIdEstatus(COD_ESTATUS_INGRESADO);
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
		mapa.setAnioSiniestro(getAnioBusqueda(request));

		// Ya seleccionó uno
		if ("buscar".equals(request.getParameter("accion"))) {
			// busco el siniestro que quiere egresar
			mapa.setAnioSiniestro(Integer.parseInt(request
					.getParameter("anioSiniestro")));
			mapa.setIdSiniestro(Integer.parseInt(request.getParameter("idSini")));
			s = ps.search(mapa);
			// Verifico que el estatus sea ingresado o egresado
			if (s.getEstatus().getId() == COD_ESTATUS_INGRESADO
					|| s.getEstatus().getId() == COD_ESTATUS_EGRESADO) {
				ent = new Entorno(Entorno.MOD_CARTA_AVAL_EGRESAR);
				request.setAttribute("siniestro", s);			
				request.getSession().setAttribute("anioSiniestro",
						request.getParameter("anioSiniestro"));
				request.getSession().setAttribute("idSini",
						request.getParameter("idSini"));
				return mapping.findForward(FWD_SUCCESS);
			} else {
				ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
				setEntorno(request, form, ent);
				am.add(ALERT_AVISOS,
						new ActionMessage("env.general.noeditable"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		}
		// Si la acci�n es anular
		if ("anular".equals(request.getParameter("accion"))) {
			mapa.setIdSiniestro(Integer.parseInt((String) request
					.getParameter("idSini")));
			mapa.setAnioSiniestro(Integer.parseInt((String) request
					.getParameter("anioSiniestro")));
			// busco el siniestro que quiere egrear
			s = ps.search(mapa);
			mapa.setIdSiniestro(Integer.parseInt((String) request
					.getParameter("idSini")));
			mapa.setMontoNoAmparado(s.getMontoNoAmparado()
					+ s.getMontoAmparado());
			ps.anularCartaAval(mapa);
			// ******INSERTAR TRAZA
			incluirTraza(TR_CARTAAVAL_ANULAR,
					String.valueOf(mapa.getIdSiniestro()),
					TRDESC_CARTAAVAL_ANULAR, usuarioSession(request));
			motEst.setDescripcion("Anulada por el modulo de Egreso de Carta Aval");
			motEst.setIdSiniestro(mapa.getIdSiniestro());
			motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
			motEst.setIdUsuario(usuarioSession(request).getCedula());
			motEst.setIdEstatus(COD_ESTATUS_ANULADO);
			motEst.setFechaInicio(new Date());
			perMotEst.insert(motEst);
			// ******INSERTAR TRAZA
			incluirTraza(TR_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, String.valueOf(mapa.getIdSiniestro()),
					TRDESC_CARTAAVAL_INSERTAR_CAMBIO_ESTATUS, usuarioSession(request));
			mapa.setIdSiniestro(Integer.parseInt((String) request
					.getParameter("idSini")));
			mapa.setAnioSiniestro(Integer.parseInt((String) request
					.getParameter("anioSiniestro")));
			// busco el siniestro que quiere egrear
			s = ps.search(mapa);
			request.setAttribute("siniestro", s);
			request.getSession().setAttribute("siniestroPadre", null);
			am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
			request.getSession().setAttribute("anioSiniestro",
					request.getParameter("anioSiniestro"));
			request.getSession().setAttribute("idSini",
					request.getParameter("idSini"));
			return mapping.findForward("egresar");

		}
		// Si es primera vez
		else {
			try {
				validarAction(request, form, ent, am, this.getClass());
				// si estan todos los filtros busco los siniestros con los
				// parametros suministrados
				mapa = getDForm(request, form, ent);
				if (mapa.getCodigo() != null) {
					reemplazo = mapa.getCodigo();
					reemplazo = reemplazo.replace("-", "");
					mapa.setCodigo(reemplazo);
				}
				mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
				try {
					listado = ps.searchMultipleByMultipleStatus(mapa);
					request.setAttribute("listado", listado);
				} catch (PersonalNotFoundException e1) {
					am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
					saveMessages(request, am);
				}
			} catch (PersonalNotFillItems e) {
			}
			return mapping.findForward(FWD_INPUT);
		}
	}
}
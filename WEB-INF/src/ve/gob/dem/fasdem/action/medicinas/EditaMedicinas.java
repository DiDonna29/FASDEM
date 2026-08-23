package ve.gob.dem.fasdem.action.medicinas;

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

import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerTipoEnfermedad;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.SiniestroNotPermittedException;
import ve.gob.dem.framework.global.GenericAction;

public class EditaMedicinas extends GenericAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_MEDICINAS_EDITA);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_MEDICINAS);
		Mapa mapa = new Mapa();
		PerSiniestro perSiniestro = new PerSiniestro();
		PerPoliza perPoliza = new PerPoliza();
		Date fechahoy = new Date();
		Calendar f = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setTime(fechahoy);
		PerCobertura perCob = new PerCobertura();
		formato.format(f.getTime());

		Siniestro sin = new Siniestro();
		PerSiniestro perSin = new PerSiniestro();
		
		mapa = getDForm(request, form, ent);
		mapa.setIdSiniestro(mapa.getId());
		sin = perSin.search(mapa);
		
		
		
		double coberturaTipo = 0.0;
		int estatus = 0;
		double coberturaAsegurado = 0.0;
		int aguda = 0;
		int cronica = 0;
		TipoEnfermedad tipoEnfermedad = new TipoEnfermedad();
		PerTipoEnfermedad perTipoEnfermedad = new PerTipoEnfermedad();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
	
		try {
			validarAction(request, form, ent, am, this.getClass());
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/**************************************** INICIO *****************************************************/
			/***************************************************************************************************/
			if (ent.isCobertura()) {
				int idPoliza = sin.getCobertura().getPoliza().getId();
				Poliza poliza = perPoliza.searchActivo();
				if (poliza.getId() != idPoliza) {
					setCoberturaParaModificar(request, poliza.getId(), idPoliza);
				}
			}
			/****************************************** FIN ******************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			/***************************************************************************************************/
		} catch (PersonalNotFillItems e) {
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/**************************************** INICIO *****************************************************/
			/***************************************************************************************************/
			if (ent.isCobertura()) {
				int idPoliza = sin.getCobertura().getPoliza().getId();
				Poliza poliza = perPoliza.searchActivo();
				if (poliza.getId() != idPoliza) {
					setCoberturaParaModificar(request, poliza.getId(), idPoliza);
				}
			}
			/****************************************** FIN ******************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			return mapping.findForward(FWD_INPUT);
		}
		if ("editar".equals(request.getParameter("accion"))) {
			mapa = getDForm(request, form, ent);
			mapa.setIdSiniestro(mapa.getId());
			sin = perSin.search(mapa);
			mapa.setFechaCreado(sin.getFechaCreado());
			mapa.setCedula(sin.getCedula());
			mapa.setCedulaBeneficiario(sin.getCedulaBeneficiario());
			try {
				mapa.setMontoSiniestro(sin.getMontoAmparado());
				findDisponible(mapa, sin.getFechaOcurrencia());
			} catch (CoberturaNotDisponibleException e) {
				am.add(ALERT_AVISOS, new ActionMessage(
						"env.general.coberuragotada"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			try {
				permitido(mapa);
			} catch (SiniestroNotPermittedException e) {
				am.add(ALERT_AVISOS, new ActionMessage(
						"env.general.notpermitted"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			
			
			mapa.setObservacion(request.getParameter("observacion"));
			estatus = mapa.getIdEstatus();
			if (sin.getTipoEnfermedad().getId() != (Integer.parseInt(request
					.getParameter("tipoEnfermedad")))) {

				if ("1".equals(request.getParameter("tipoEnfermedad"))) {
					mapa.setIdMedicinas(COD_TIPO_ENFERMEDAD_AGUDA);
					tipoEnfermedad = perTipoEnfermedad.searchMedicinasTexto(mapa);
					mapa.setDias(tipoEnfermedad.getDias());
					mapa.setContador(tipoEnfermedad.getContador());

					if ("1".equals(sin.getTipoEnfermedad())) {

						mapa.setMontoAmparado(sin.getMontoAmparado());
						mapa.setMontoPresupuestado(sin.getMontoPresupuestado());

					}

					aguda = perTipoEnfermedad.searchAguda(mapa);
					if (aguda < tipoEnfermedad.getContador()) {

						mapa.setMontoAmparado(tipoEnfermedad.getMonto());
						mapa.setMontoPresupuestado(tipoEnfermedad.getMonto());

					} else {

						am.add(ALERT_AVISOS, new ActionMessage(
								"env.general.aguda"));
						saveMessages(request, am);
						return mapping.findForward(FWD_INPUT);
					}

				} else {
					mapa.setIdMedicinas(Integer
							.parseInt(COD_TIPO_ENFERMEDAD_CRONICA));
					tipoEnfermedad = perTipoEnfermedad.searchMedicinasTexto(mapa);
					mapa.setDias(tipoEnfermedad.getDias());
					mapa.setContador(tipoEnfermedad.getContador());

					if ("2".equals(sin.getTipoEnfermedad())) {
						mapa.setMontoAmparado(sin.getMontoAmparado());
						mapa.setMontoPresupuestado(sin.getMontoPresupuestado());

					}
					cronica = perTipoEnfermedad.searchCronica(mapa);
					if (cronica < tipoEnfermedad.getContador()) {
						mapa.setMontoAmparado(tipoEnfermedad.getMonto());
						mapa.setMontoPresupuestado(tipoEnfermedad.getMonto());

					} else {
						am.add(ALERT_AVISOS, new ActionMessage(
								"env.general.cronica"));
						saveMessages(request, am);
						return mapping.findForward(FWD_INPUT);
					}
				}
			} else {
				mapa.setMontoAmparado(sin.getMontoAmparado());
				mapa.setMontoPresupuestado(sin.getMontoPresupuestado());

				mapa.setIdCobertura(Integer.parseInt(request
						.getParameter("cobertura")));
			}
			if (COD_ESTATUS_RECHAZADO == estatus
					|| COD_ESTATUS_ANULADO == estatus) {
				mapa.setMontoAmparado(0);
			}

			mapa.setId(mapa.getIdSiniestro());
			PerEstatus perEst = new PerEstatus();
			Estatus est = new Estatus();
			try {
				est = perEst.buscar(mapa.getIdEstatus());
				if (est.isJustificacion()) {
					mapa.setIdEstatus(est.getId());
					mapa.setIdSiniestro(mapa.getId());
					
					motEst.setDescripcion(mapa.getJustificacion());
					motEst.setIdSiniestro(mapa.getId());
					motEst.setIdDependencia(usuarioSession(request)
							.getIdDependencia());
					motEst.setIdUsuario(usuarioSession(request).getCedula());
					motEst.setIdEstatus(mapa.getIdEstatus());
					perMotEst.insert(motEst);
					// ******INSERTAR TRAZA
				}
			} catch (Exception e) {
			}
			mapa.setIdSiniestro(mapa.getIdSiniestro());
			mapa.setCitaPostOperatorio(false);
			mapa.setFechaUltimaModificacion(new Date());
			mapa.setFechaNotificacion(new Date());
			mapa.setFechaOcurrencia(new Date());
			mapa.setIdCobertura(Integer.parseInt(request
					.getParameter("cobertura")));
			mapa.setObservacion(request.getParameter("observacion"));
			mapa.setTipoProveedor(TIPO_PROVEEDOR_FARMACIA);
			mapa.setIdProveedor(ID_PROVEEDOR_FARMACIA_DESCONOCIDO);
			mapa.setIdTipoSiniestro(sin.getTipoSiniestro().getId());
			mapa.setIdTipoTratamiento(sin.getTipoTratamiento()
					.getIdTipoTratamiento());

			mapa.setIdTipoEnfermedad(Integer.parseInt(request
					.getParameter("tipoEnfermedad")));
			mapa.setFechaCreado(sin.getFechaCreado());
			
			if ("1".equals(request.getParameter("tipoEnfermedad"))) {
				mapa.setIdMedicinas(COD_TIPO_ENFERMEDAD_AGUDA);
				
				tipoEnfermedad = perTipoEnfermedad.searchMedicinasTexto(mapa);
				aguda = perTipoEnfermedad.searchAguda(mapa);
				mapa.setObservacion(request.getParameter("observacion"));
				
				mapa.setObservacionReporte(tipoEnfermedad.getTextoInicio()
						+ "\r" + request.getParameter("observacion") + "\r"
						+ tipoEnfermedad.getTextoFin());
				
				
			} else {
				mapa.setIdMedicinas(Integer
						.parseInt(COD_TIPO_ENFERMEDAD_CRONICA));
				tipoEnfermedad = perTipoEnfermedad.searchMedicinasTexto(mapa);
				cronica = perTipoEnfermedad.searchCronica(mapa);
				mapa.setObservacion(request.getParameter("observacion"));
				
				
				mapa.setObservacionReporte(tipoEnfermedad.getTextoInicio()
						+ "\r" + request.getParameter("observacion") + "\r");
				

			}
			mapa.setTipoProveedor(TIPO_PROVEEDOR_FARMACIA);
			mapa.setIdProveedor(ID_PROVEEDOR_FARMACIA_DESCONOCIDO);
			mapa.setMontoNegociado(mapa.getMontoPresupuestado());
			coberturaTipo = perCob.search(mapa.getIdCobertura());

			coberturaAsegurado = perCob.coberturaAsegurado(mapa)
					+ mapa.getMontoAmparado() - sin.getMontoAmparado();

			if (sin.getCobertura().getId() != mapa.getIdCobertura()) {
				try {
					if (coberturaAsegurado <= coberturaTipo) {
						mapa.setCitaPostOperatorio(false);
						try {
						perSiniestro.update(mapa);
						} catch (Exception e) {
							log.error("errrrror",e);
						}
						incluirTraza(TR_MEDICINAS_MODIFICAR,
								String.valueOf(sin.getId()),
								"Modificando Medicinas", usuarioSession(request));
						mapa.setAnioSiniestro(getAnioBusqueda(request));
						sin = perSiniestro.search(mapa);
						request.setAttribute("siniestro", sin);

						am.add(ALERT_AVISOS, new ActionMessage(
								"env.general.sms"));
						saveMessages(request, am);

					} else {
						am.add(ALERT_AVISOS, new ActionMessage(
								"env.general.coberuragotada"));
						saveMessages(request, am);
						return mapping.findForward(FWD_INPUT);
					}
				} catch (Exception e) {

					am.add(ALERT_AVISOS, new ActionMessage(
							"env.general.operacionerronea"));
					saveMessages(request, am);
					return mapping.findForward(FWD_SUCCESS);

				}
			} else {
				try {
					
					perSiniestro.update(mapa);
					try {
						mapa.setAnioSiniestro(sin.getAnioSiniestro());
					sin = perSiniestro.search(mapa);
					} catch (Exception e) {}
					request.setAttribute("siniestro", sin);

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"env.general.sms"));
					saveMessages(request, am);

				} catch (Exception e) {

					am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"env.general.operacionerronea"));
					saveMessages(request, am);
					return mapping.findForward(FWD_SUCCESS);

				}
			}
			return mapping.findForward(FWD_SUCCESS);
		} else {
			return mapping.findForward(FWD_INPUT);
		}

	}
}

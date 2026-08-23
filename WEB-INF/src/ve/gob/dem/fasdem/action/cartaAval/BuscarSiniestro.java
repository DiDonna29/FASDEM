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
import org.apache.struts.action.DynaActionForm;
import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotEditableException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class BuscarSiniestro extends GenericAction implements Serializable {
	/**
	 * figumare
	 */
	private static final long serialVersionUID = 5861083469142874907L;

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
		PerSiniestro ps = new PerSiniestro();
		PerPoliza perPoliza = new PerPoliza();
		DynaActionForm dForm = (DynaActionForm) form;
		String reemplazo = "";
		Mapa mapa = new Mapa();
		List listado = new ArrayList();
		Siniestro s = new Siniestro();
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		Date today = new Date();
		PerPersona pp = new PerPersona();
		Persona t = new Persona();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		if (request.getParameter("accion") == null || "".equals(request.getParameter("accion"))) {
			ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
			setEntorno(request, form, ent);
			// valido que haya colocado los criterios de b�squeda requeridos
			try {
				validarAction(request, form, ent, am, this.getClass());
				mapa = getDForm(request, form, ent);
				mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
				if (mapa.getCodigo() != null) {
					reemplazo = mapa.getCodigo();
					reemplazo = reemplazo.replace("-", "");
					mapa.setCodigo(reemplazo);
					int anioSiniestro2 = Integer.parseInt(mapa.getCodigo().substring(0, 2));
					int anioSiniestro1 = 20;
					String val = String.valueOf(anioSiniestro1) + String.valueOf(anioSiniestro2);
					int anioSiniestro = Integer.parseInt(val);
					mapa.setAnioSiniestro(anioSiniestro);
					// mapa.setAnioSiniestro(anioSiniestro);
				}
				// Busca el listado de siniestros
				try {
					listado = ps.searchMultiple(mapa);
					request.setAttribute("resultado", listado);
				} catch (PersonalNotFoundException e) {
					am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
					saveMessages(request, am);
				}
			} catch (PersonalNotFillItems e) {
				/***************************************************************************************************/
				/***************************************************************************************************/
				/*************
				 * PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS
				 * ACTIVAS
				 *********************/
				/***************************************************************************************************/
				/**************************************** INICIO *****************************************************/
				/***************************************************************************************************/
				if (ent.isCobertura()) {
					int idPoliza = s.getCobertura().getPoliza().getId();
					Poliza poliza = perPoliza.searchActivo();
					if (poliza.getId() != idPoliza) {
						setCoberturaParaModificar(request, poliza.getId(), idPoliza);
					}
				}
				/****************************************** FIN ******************************************************/
				/***************************************************************************************************/
				/*************
				 * PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS
				 * ACTIVAS
				 *********************/
				/***************************************************************************************************/
				/***************************************************************************************************/
				/***************************************************************************************************/
			}
		}
		if ("buscar".equals(request.getParameter("accion"))) {
			// busco el siniestro que quiere modificar
			mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
			mapa.setIdSiniestro(Integer.parseInt(request.getParameter("idSini")));
			s = ps.search(mapa);
			if (s.getMontoMaximoAutorizado() != 0) {
				request.setAttribute("montoMaximoAutorizado", s.getMontoMaximoAutorizado());
			}
			if (!"001".equals(s.getSubCodigo())) {
				// significa que tiene un padre}
				// buscar padrpor codigo
				Siniestro sp = new Siniestro();
				mapa.setAnioSiniestro(s.getAnioSiniestro());
				mapa.setSubCodigo(s.getAniomesCodigo() + s.getCodigo() + "001");
				try {
					sp = ps.searchPadre(mapa);
					request.setAttribute("siniestroPadre", sp);
				} catch (PersonalNotFoundException e) {
					log.info("no se encontró padre");
					request.setAttribute("siniestroPadre", null);
				}
			}
			mapa.setIdSiniestro(s.getId());
			mapa.setIdEstatus(s.getEstatus().getId());
			mapa.setIdTipoTramite(s.getTipoTramite().getId());
			// Verifico si es una carta aval
			if (s.getTipoTramite().getId() != COD_TIPO_TRAMITE_CARTAAVAL) {
				ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
				setEntorno(request, form, ent);
				/***************************************************************************************************/
				/***************************************************************************************************/
				/*************
				 * PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS
				 * ACTIVAS
				 *********************/
				/***************************************************************************************************/
				/**************************************** INICIO *****************************************************/
				/***************************************************************************************************/
				if (ent.isCobertura()) {
					int idPoliza = s.getCobertura().getPoliza().getId();
					Poliza poliza = perPoliza.searchActivo();
					if (poliza.getId() != idPoliza) {
						setCoberturaParaModificar(request, poliza.getId(), idPoliza);
					}
				}
				/****************************************** FIN ******************************************************/
				/***************************************************************************************************/
				/*************
				 * PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS
				 * ACTIVAS
				 *********************/
				/***************************************************************************************************/
				/***************************************************************************************************/
				/***************************************************************************************************/
				am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			// Verifico si se puede editar, seg�n su estatus
			try {
				isEditable(mapa);
			} catch (PersonalNotEditableException e) {
				ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
				setEntorno(request, form, ent);
				/***************************************************************************************************/
				/***************************************************************************************************/
				/*************
				 * PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS
				 * ACTIVAS
				 *********************/
				/***************************************************************************************************/
				/**************************************** INICIO *****************************************************/
				/***************************************************************************************************/
				if (ent.isCobertura()) {
					int idPoliza = s.getCobertura().getPoliza().getId();
					Poliza poliza = perPoliza.searchActivo();
					if (poliza.getId() != idPoliza) {
						setCoberturaParaModificar(request, poliza.getId(), idPoliza);
					}
				}
				/****************************************** FIN ******************************************************/
				/***************************************************************************************************/
				/*************
				 * PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS
				 * ACTIVAS
				 *********************/
				/***************************************************************************************************/
				/***************************************************************************************************/
				/***************************************************************************************************/
				am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			mapa.setIdSiniestro(s.getId());
			request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_CARTAAVAL);
			dForm.set("id", String.valueOf(s.getId()));
			dForm.set("cobertura", String.valueOf(s.getCobertura().getId()));
			mapa.setIdCobertura(s.getCobertura().getId());
			mapa.setCedulaBeneficiario(s.getCedulaBeneficiario());
			mapa.setCedula(s.getCedula());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			cob = pc.searchById(mapa.getIdCobertura());
			if (cob.isPorPatologia()) {
				try {
					desgloseCobertura = pc.listDesgloseCobertura(mapa);
					cob.setDesgloseCobertura(desgloseCobertura);
					request.setAttribute("desgloseCobertura", desgloseCobertura);
				} catch (Exception e) {
				}
			}
			// Si es por tipo de cobertura
			else {
				cobertura = pc.listByCedula(mapa);
				request.setAttribute("detalleMontoCobertura", cobertura);
			}
			t = pp.buscar(s.getCedula());
			t.setBeneficiario(pp.buscar(s.getCedulaBeneficiario()));
			request.getSession().setAttribute(KEY_TITULAR, t);
			dForm.set("fechaNotificacion", Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
			dForm.set("tipoSiniestro", new Integer(s.getTipoSiniestro().getId()).toString());
			dForm.set("tipoEnfermedad", new Integer(s.getTipoEnfermedad().getId()).toString());
			dForm.set("tipoTratamiento", new Integer(s.getTipoTratamiento().getIdTipoTratamiento()).toString());
			dForm.set("tipoProveedor", new Integer(s.getProveedor().getTipoProveedor().getId()).toString());
			dForm.set("idProveedor", new Integer(s.getProveedor().getId()).toString());
			dForm.set("proveedor", s.getProveedor().getDescripcion());
			dForm.set("idCausaIngreso", new Integer(s.getPatologiaOrganoTratamiento().getId()).toString());
			dForm.set("causaIngreso", s.getPatologiaOrganoTratamiento().getDescripcion());
			dForm.set("observacion", s.getObservacion());
			dForm.set("codigo", s.getAniomesCodigo() + s.getCodigo() + s.getSubCodigo());
			dForm.set("estatus", new Integer(s.getEstatus().getId()).toString());
			dForm.set("citaPreOperatorio", String.valueOf(s.isCitaPreOperatorio()));
			dForm.set("citaPostOperatorio", String.valueOf(s.isCitaPostOperatorio()));
			dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
			dForm.set("montoNegociado", String.valueOf(s.getMontoNegociado()));
			dForm.set("montoAmparado", String.valueOf(s.getMontoAmparado()));
			if (s.getEstatus().isJustificacion() == true) {
				mapa.setIdEstatus(s.getEstatus().getId());
				mapa.setIdSiniestro(s.getId());
				try {
					motEst = perMotEst.searchByEstatus(mapa);
					dForm.set("justificacion", String.valueOf(motEst.getDescripcion()));
					request.setAttribute("justificar", "justificar");
				} catch (PersonalNotFoundException e) {
					dForm.set("justificacion", "");
					request.setAttribute("justificar", "justificar");
				}
			}
			ent = new Entorno(Entorno.MOD_CARTA_AVAL_EDICION);
			setEntorno(request, form, ent);
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS *********************/
			/***************************************************************************************************/
			/**************************************** INICIO *****************************************************/
			/***************************************************************************************************/
			if (ent.isCobertura()) {
				int idPoliza = s.getCobertura().getPoliza().getId();
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
			request.setAttribute("form_action", mapping.getParameter());
			return mapping.findForward(FWD_SUCCESS);
		}
		return mapping.findForward(FWD_INPUT);
	}
}
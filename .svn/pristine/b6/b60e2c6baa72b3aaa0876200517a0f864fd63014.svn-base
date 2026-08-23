package ve.gob.dem.fasdem.action.emergencia;

import java.io.Serializable;
import java.util.ArrayList;
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
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class BuscarSiniestro extends GenericAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5861083469142874907L;

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCAR_SINIESTRO);
		Mapa mapa = getDForm(request, form, ent);
		PerSiniestro ps = new PerSiniestro();
		PerPoliza perPoliza = new PerPoliza();
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		PerPersona pp = new PerPersona();
		Persona t = new Persona();
		int i = 0;
		DynaActionForm dForm = (DynaActionForm) form;
		mapa.setIdTipoTramite(COD_TIPO_EMERGENCIA);
		int anioSiniestro2 = 0;
		int anioSiniestro1 = 0;
		int anioSiniestro =0;
		String val = "";
		try {
			anioSiniestro2 = Integer.parseInt(mapa.getCodigo().substring(0, 2));
			anioSiniestro1 = 20;
			val = String.valueOf(anioSiniestro1) + String.valueOf(anioSiniestro2);
			anioSiniestro = Integer.parseInt(val);
		} catch (Exception e) {
		}
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			
			return mapping.findForward(FWD_INPUT);
		}
		mapa.setAnioSiniestro(anioSiniestro);

		try {
			log.info("este es mi mampa a eeee " + mapa);
			Siniestro s = ps.searchByCodigo(mapa);
			if (s.getMontoMaximoAutorizado() != 0) {
				request.setAttribute("montoMaximoAutorizado", s.getMontoMaximoAutorizado());
			}
			if (!"001".equals(s.getSubCodigo())) {
				request.setAttribute("siniestroPadre", s);
			}
			if (s.getEstatus().getId() == 9) {
				i = 1;
			}
			if (s.getEstatus().getId() == 8) {
				i = 1;
			}
			if (i == 0) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			mapa.setIdSiniestro(s.getId());
			mapa.setIdSiniestro(s.getId());
			request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
			mapa.setCedulaBeneficiario(s.getCedulaBeneficiario());
			mapa.setCedula(s.getCedula());
			dForm.set("id", String.valueOf(s.getId()));
			dForm.set("cobertura", String.valueOf(s.getCobertura().getId()));
			mapa.setIdCobertura(s.getCobertura().getId());
			cob = pc.searchById(mapa.getIdCobertura());
			// Si es por tipo de cobertura
			// Si es por patolog�a
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
				log.info("mi mapa es " + mapa);
				cobertura = pc.listByCedula(mapa);
				request.setAttribute("detalleMontoCobertura", cobertura);
			}
			t = pp.buscar(s.getCedula());
			t.setBeneficiario(pp.buscar(s.getCedulaBeneficiario()));
			request.getSession().setAttribute(KEY_TITULAR, t);
			dForm.set("fechaIngreso", Utilidad.DateToString(s.getFechaIngreso(), "dd/MM/yyyy"));
			if (s.getFechaEgreso() == null) {
			} else {
				dForm.set("fechaEgreso", Utilidad.DateToString(s.getFechaEgreso(), "dd/MM/yyyy"));
			}
			dForm.set("fechaNotificacion", Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
			dForm.set("tipoSiniestro", new Integer(s.getTipoSiniestro().getId()).toString());
			dForm.set("tipoEnfermedad", new Integer(s.getTipoEnfermedad().getId()).toString());
			dForm.set("tipoTratamiento", new Integer(s.getTipoTratamiento().getIdTipoTratamiento()).toString());
			dForm.set("tipoProveedor", new Integer(s.getProveedor().getTipoProveedor().getId()).toString());
			dForm.set("idProveedor", new Integer(s.getProveedor().getId()).toString());
			dForm.set("proveedor", s.getProveedor().getDescripcion());
			dForm.set("idCausaIngreso", new Integer(s.getPatologiaOrganoTratamiento().getId()).toString());
			dForm.set("causaIngreso", s.getPatologiaOrganoTratamiento().getDescripcion());
			dForm.set("codigo", s.getAniomesCodigo() + s.getCodigo() + s.getSubCodigo());
			dForm.set("estatus", new Integer(s.getEstatus().getId()).toString());
			dForm.set("citaPostOperatorio", String.valueOf(s.isCitaPostOperatorio()));
			dForm.set("montoAmparado", String.valueOf(s.getMontoAmparado()));
			dForm.set("montoNegociado", String.valueOf(s.getMontoNegociado()));
			dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
			//dForm.set("observacion", s.getObservacion());
			mapa.setIdSiniestro(s.getId());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			// montos de los honorarios
			/*
			 * try { mapa.setIdTipoGasto(COD_TIPO_GASTO_HONORARIOS); fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * 
			 * request.setAttribute("montoHonorariosPresupuestado",
			 * String.valueOf(fact.getMontoPresupuestado()));
			 * dForm.set("montoHonorariosNegociado",
			 * String.valueOf(fact.getMontoNegociado()));
			 * dForm.set("montoHonorariosMedicos", 
			 * String.valueOf(fact.getMontoAmparado()));
			 * request.setAttribute("montoHonorariosMedicosNoAmparado",
			 * String.valueOf(fact.getMontoNoAmparado()));
			 * 
			 * } catch (PersonalNotFoundException e) {
			 * 
			 * } // montos de funeraria
			 * 
			 * try { mapa.setIdTipoGasto(COD_TIPO_GASTO_FUNERARIOS); fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa); log.info("25facttt" +
			 * fact); request.setAttribute("montoFunerariaPresupuestado",
			 * String.valueOf(fact.getMontoPresupuestado()));
			 * dForm.set("montoFunerariaNegociado",
			 * String.valueOf(fact.getMontoNegociado()));
			 * dForm.set("montoFuneraria",
			 * String.valueOf(fact.getMontoAmparado()));
			 * request.setAttribute("montoFunerariaNoAmparado",
			 * String.valueOf(fact.getMontoNoAmparado()));
			 * 
			 * } catch (PersonalNotFoundException e1) {
			 * 
			 * } // montos de ambulancia
			 * 
			 * try { mapa.setIdTipoGasto(COD_TIPO_GASTO_AMBULANCIA); fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * request.setAttribute("montoAmbulanciaPresupuestado",
			 * String.valueOf(fact.getMontoPresupuestado()));
			 * dForm.set("montoAmbulanciaNegociado",
			 * String.valueOf(fact.getMontoNegociado()));
			 * dForm.set("montoAmbulancia",
			 * String.valueOf(fact.getMontoAmparado()));
			 * request.setAttribute("montoAmbulanciaNoAmparado",
			 * String.valueOf(fact.getMontoNoAmparado()));
			 * 
			 * } catch (PersonalNotFoundException e2) {
			 * 
			 * } // montos de examenes especiales
			 * 
			 * try { mapa.setIdTipoGasto(COD_TIPO_GASTO_EXAMENES_ESPECIALES);
			 * fact = perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * 
			 * request.setAttribute("montoExamenesEspecialesPresupuestado",
			 * String.valueOf(fact.getMontoPresupuestado()));
			 * dForm.set("montoExamenesEspecialesNegociado",
			 * String.valueOf(fact.getMontoNegociado()));
			 * dForm.set("montoExamenesEspeciales",
			 * String.valueOf(fact.getMontoAmparado()));
			 * request.setAttribute("montoExamenesEspecialesNoAmparado",
			 * String.valueOf(fact.getMontoNoAmparado()));
			 * 
			 * } catch (PersonalNotFoundException e3) {
			 * log.error("errorrrrrrrrr 4444 ", e3); } // montos de los gastos
			 * clinico try {
			 * mapa.setIdTipoGasto(COD_TIPO_GASTO_GASTOS_CLINICOS); fact =
			 * perFact.searchIdSiniestroIdTipoGasto(mapa);
			 * 
			 * request.setAttribute("montoGastosPresupuestado",
			 * String.valueOf(fact.getMontoPresupuestado()));
			 * dForm.set("montoGastosPresupuestado",
			 * String.valueOf(fact.getMontoPresupuestado()));
			 * dForm.set("montoGastosNegociado",
			 * String.valueOf(fact.getMontoNegociado()));
			 * dForm.set("montoGastosClinicos",
			 * String.valueOf(fact.getMontoAmparado()));
			 * request.setAttribute("montoGastosClinicosNoAmparado",
			 * String.valueOf(fact.getMontoNoAmparado()));
			 * 
			 * } catch (PersonalNotFoundException e4) {
			 * 
			 * }
			 */
			ent = new Entorno(Entorno.MOD_EMERGENCIA_EDICION);
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
		} catch (PersonalNotFoundException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.notfound"));
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

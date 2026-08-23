package ve.gob.dem.fasdem.action.medicinas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Medicamento;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Proveedor;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerConsulta;
import ve.gob.dem.fasdem.per.PerMedicamento;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerProveedor;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerTipoEnfermedad;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.SiniestroNotPermittedException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class NuevoMedicinas extends GenericAction {
	static protected Logger log = Logger.getLogger(NuevoMedicinas.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_MEDICINAS_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_MEDICINAS);
		Mapa mapa = new Mapa();
		PerPoliza ppp = new PerPoliza();
		PerSiniestro ps = new PerSiniestro();
		PerSiniestro perSiniestro = new PerSiniestro();
		Siniestro siniPadre = null;
		double coberturaAsegurado = 0.0;
		double coberturaTipo = 0.0;
		int aguda = 0;
		int cronica = 0;
		int idSiniestro = 0;
		Persona bene = new Persona();
		Proveedor proveedor = new Proveedor();
		PerProveedor perProveedor = new PerProveedor();
		PerCobertura perCob = new PerCobertura();
		PerConsulta perConsulta = new PerConsulta();
		TipoEnfermedad tipoEnfermedad = new TipoEnfermedad();
		PerTipoEnfermedad perTipoEnfermedad = new PerTipoEnfermedad();
		List listConsultas = null;
		PerMedicamento perMedicamento = new PerMedicamento();
		List lisMedicinas = new ArrayList();
		String miFecha = "";
		String misMedicinas = "";
		TipoEnfermedad mesMedicinasCronicas = new TipoEnfermedad();
		String paramSiniPadre = request.getParameter("idSiniPadre");
		DynaActionForm dForm = (DynaActionForm) form;
	
		
		
		log.info("anio es "+getAnioBusqueda(request));
		log.info("2anio es ");
		int anio= 0;
		anio=getAnioBusqueda(request);
		mapa.setAnioSiniestro(anio);
		if (request.getParameter("paramNewSini") != null) {
			request.getSession().removeAttribute("siniestroPadre");
			paramSiniPadre = null;
		}
		if (paramSiniPadre != null) {
			mapa.setIdSiniestro(Integer.parseInt(paramSiniPadre));
			siniPadre = ps.search(mapa);
			if (siniPadre.getTipoTramite().getId() != 3 && siniPadre.getTipoTramite().getId() != 4 && siniPadre.getTipoTramite().getId() != 6) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.padreerroneo"));
				saveMessages(request, am);
				return mapping.findForward("inicio");
			} else {
				if (siniPadre.getEstatus().getId() == 34) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.padreerroneo"));
					saveMessages(request, am);
					return mapping.findForward("inicio");
				}
				if (siniPadre.getCobertura().getId() == 8) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.coberturasecuela"));
					saveMessages(request, am);
					return mapping.findForward("inicio");
				}
				if (siniPadre.getEstatus().getId() == 35) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.padreerroneo"));
					saveMessages(request, am);
					return mapping.findForward("inicio");
				}
			}
			request.getSession().setAttribute("siniestroPadre", siniPadre);
			dForm.set("idCausaIngreso", new Integer(siniPadre.getPatologiaOrganoTratamiento().getId()).toString());
			dForm.set("causaIngreso", siniPadre.getPatologiaOrganoTratamiento().getDescripcion());
			dForm.set("cobertura", String.valueOf(siniPadre.getCobertura().getId()));
		} else {
			siniPadre = (Siniestro) request.getSession().getAttribute("siniestroPadre");
		}
		/*dForm.set("tipoProveedor", String.valueOf(TIPO_PROVEEDOR_FARMACIA));
		if ("".equals(dForm.get("idProveedor"))) {
			dForm.set("idProveedor", String.valueOf(COD_PROVEEDOR_LOCATEL));
			mapa.setIdProveedor(COD_PROVEEDOR_LOCATEL);
			proveedor = perProveedor.buscar(COD_PROVEEDOR_LOCATEL);
			dForm.set("proveedor", proveedor.getDescripcion());
		}*/
		bene = buscarTitularBeneficiario(request);
		if (bene.getBeneficiario().getEstatus().equals("ACTIVO")) {
		} else {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.inactivo"));
			saveMessages(request, am);
			return mapping.findForward("inicio");
		}
		try {
			listConsultas = perConsulta.searchFuncionario(bene.getBeneficiario().getCedula());
			// consulta = perConsulta.searchFunc(bene.getBeneficiario()
			// .getCedula());
		} catch (Exception e) {
			log.info("error", e);
			try {
				listConsultas = perConsulta.searchFuncionario(bene.getBeneficiario().getCedula());
				// consulta = perConsulta.searchFunc(bene.getBeneficiario()
				// .getCedula());
			} catch (Exception e1) {
				log.info("error", e);
				try {
					listConsultas = perConsulta.searchBeneficiario(bene.getBeneficiario().getCedula());
					// consulta = perConsulta.searchBene(bene.getBeneficiario()
					// .getCedula());
				} catch (Exception e2) {
					log.info("error", e);
					listConsultas = null;
				}
			}
		}
		request.setAttribute("listConsultas", listConsultas);
		// request.setAttribute("consulta", consulta.getIdConsulta());
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			// /////////////////////////////////////////////////////////////////////////////////////
			String[] consignaciones = request.getParameterValues("consignables");
			if (consignaciones != null) {
				for (int i = 0; i != consignaciones.length; i++) {
					lisMedicinas = perMedicamento.searchListMedicamentos(consignaciones[i]);
					for (int r = 0; r != lisMedicinas.size(); r++) {
						Medicamento med = new Medicamento();
						med = (Medicamento) lisMedicinas.get(r);
						misMedicinas = misMedicinas + "\r" + "-" + med.getMedicamento();
					}
				}
			}
			if (request.getParameter("observacion") == null) {
				dForm.set("observacion", misMedicinas);
			} else
				dForm.set("observacion", "\r" + misMedicinas + request.getParameter("observacion"));
			// /////////////////////////////////////////////////////////////////////////////////////////
			try {
				listConsultas = perConsulta.searchFuncionario(bene.getBeneficiario().getCedula());
			} catch (Exception e3) {
				try {
					listConsultas = perConsulta.searchFuncionario(bene.getBeneficiario().getCedula());
				} catch (Exception e1) {
					try {
						listConsultas = perConsulta.searchBeneficiario(bene.getBeneficiario().getCedula());
					} catch (Exception e2) {
						listConsultas = new ArrayList();
					}
				}
			}
			return mapping.findForward(FWD_INPUT);
		}
		request.setAttribute("listConsultas", listConsultas);
		dForm.set("observacion", request.getParameter("observacion"));
		mapa = getDForm(request, form, ent);
		request.setAttribute("listConsultas", listConsultas);
		if (String.valueOf(COD_TIPO_ENFERMEDAD_AGUDA).equals(request.getParameter("tipoEnfermedad"))) {
			mapa.setIdMedicinas(COD_TIPO_ENFERMEDAD_AGUDA);
			tipoEnfermedad = perTipoEnfermedad.searchMedicinas(mapa.getIdMedicinas());
			mapa.setDias(tipoEnfermedad.getDias());
			mapa.setContador(tipoEnfermedad.getContador());
			aguda = perTipoEnfermedad.searchAguda(mapa);
			if (aguda < tipoEnfermedad.getContador()) {
				mapa.setMontoAmparado(tipoEnfermedad.getMonto());
				mapa.setMontoPresupuestado(tipoEnfermedad.getMonto());
				mapa.setObservacionReporte(tipoEnfermedad.getTextoInicio() + "\r" + request.getParameter("observacion") + "\r" + tipoEnfermedad.getTextoFin());
			} else {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.aguda"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		} else {
			mapa.setIdMedicinas(Integer.parseInt(COD_TIPO_ENFERMEDAD_CRONICA));
			tipoEnfermedad = perTipoEnfermedad.searchMedicinas(mapa.getIdMedicinas());
			mapa.setDias(tipoEnfermedad.getDias());
			mapa.setContador(tipoEnfermedad.getContador());
			cronica = perTipoEnfermedad.searchCronica(mapa);
			if (cronica < tipoEnfermedad.getContador()) {
				
				Date ahora = new Date();
			    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			    formateador.format(ahora);
			    log.info("----------------------->>>> "+formateador.format(ahora));
			    miFecha=formateador.format(ahora);
			    log.info("fecha de hoyyyyyyyyyyy " + miFecha);
				mesMedicinasCronicas= perTipoEnfermedad.searchmesCronica(miFecha);
				log.info("meeeeeeeeeeeeeeeeeeeeeessssssssssssss "+mesMedicinasCronicas.getMesmedicina());
				mapa.setMontoAmparado(tipoEnfermedad.getMonto());
				mapa.setMontoPresupuestado(tipoEnfermedad.getMonto());
				//mapa.setObservacionReporte(tipoEnfermedad.getTextoInicio() + "\r" + request.getParameter("observacion") + "\r" + tipoEnfermedad.getTextoFin());
				mapa.setObservacionReporte(tipoEnfermedad.getTextoInicio() + "\r" + request.getParameter("observacion") + "\r");
			} else {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.cronica"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		}
		mapa.setObservacion(request.getParameter("observacion"));
		mapa.setIdTipoSiniestro(COD_SINIESTRO_MEDICINAS);
		mapa.setIdEstatus(COD_ESTATUS_EGRESADO);
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_MEDICINAS);
		mapa.setFechaNotificacion(new Date());
		mapa.setFechaOcurrencia(new Date());
		mapa.setIdPoliza(ppp.searchActivo().getId());
		mapa.setIdCobertura(Integer.parseInt(request.getParameter("cobertura")));
		mapa.setCedula(bene.getCedula());
		mapa.setCedulaBeneficiario(bene.getBeneficiario().getCedula());
		mapa.setSexo(bene.getSexo());
		mapa.setSexoBeneficiario(bene.getBeneficiario().getSexo());
		mapa.setNombres(bene.getNombres());
		mapa.setNombresBeneficiario(bene.getBeneficiario().getNombres());
		mapa.setApellidos(bene.getApellidos());
		mapa.setApellidosBeneficiario(bene.getBeneficiario().getApellidos());
		mapa.setFechaNacimiento(bene.getFechaNacimiento());
		mapa.setFechaNacimientoBeneficiario(bene.getBeneficiario().getFechaNacimiento());
		mapa.setIdTipoTratamiento(COD_TIPO_TRATAMIENTO_MEDICO);
		mapa.setIdBeneficiario(Integer.parseInt(bene.getBeneficiario().getCedula()));
		mapa.setMontoNegociado(mapa.getMontoPresupuestado());
		mapa.setTipoProveedor(TIPO_PROVEEDOR_FARMACIA);
		mapa.setIdProveedor(ID_PROVEEDOR_FARMACIA_DESCONOCIDO);
		/*
		 * mapa.setCitaPostOperatorio(false); mapa.setCitaPreOperatorio(false);
		 */
		if (siniPadre != null) {
			mapa.setAnioMesCodigo(siniPadre.getAniomesCodigo());
			mapa.setCodigo(siniPadre.getCodigo());
			mapa.setIdSiniestroPadre(siniPadre.getId());
		}
		coberturaTipo = perCob.search(mapa.getIdCobertura());
		coberturaAsegurado = perCob.coberturaAsegurado(mapa);
		try {
			findDisponible(mapa,null);
		} catch (CoberturaNotDisponibleException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		try {
			permitido(mapa);
		} catch (SiniestroNotPermittedException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.notpermitted"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		try {
			if (coberturaAsegurado + mapa.getMontoAmparado() <= coberturaTipo) {
				idSiniestro = perSiniestro.insert(mapa);
				incluirTraza(TR_MEDICINAS_DECLARAR, String.valueOf(idSiniestro), "Cargando Medicinas", usuarioSession(request));
				mapa.setId(idSiniestro);
				mapa.setIdSiniestro(idSiniestro);
				mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy")));
				Siniestro s = perSiniestro.search(mapa);
				request.setAttribute("s", s);
				am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
				saveMessages(request, am);
				request.getSession().setAttribute("siniestroPadre", null);
			} else {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		} catch (Exception e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.operacionerronea"));
			saveMessages(request, am);
			request.getSession().setAttribute("siniestroPadre", null);
			return mapping.findForward(FWD_SUCCESS);
		}
		am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
		saveMessages(request, am);
		return mapping.findForward(FWD_SUCCESS);
	}
}

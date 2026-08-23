package ve.gob.dem.fasdem.action.emergencia;

import java.sql.SQLException;
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
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerCuenta;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class PreNuevoEmerg extends GenericAction {
	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setLogger(PreNuevoEmerg.class);
		Entorno ent = new Entorno(Entorno.MOD_EMERGENCIA_NUEVO_DECLARAR);
		ActionMessages am = new ActionMessages();
		Mapa mapa = new Mapa();
		Siniestro siniPadre = null;
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		PerSiniestro ps = new PerSiniestro();
		Persona t = buscarTitularBeneficiario(request);
		
		if ("INACTIVO".equals(t.getBeneficiario().getEstatus())) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.inactivo"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INDEX);
		}
		
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
		log.info("aqui ruta");
		request.setAttribute("form_action", "/security/emergencia/nuevaEmergencia.do");
		log.info("aqui ruta 2");
		String paramSiniPadre = request.getParameter("idSiniPadre");
		log.info("paramSiniPadre " + paramSiniPadre);
		request.getSession().setAttribute("idSiniPadre", paramSiniPadre);
		log.info("getAnioBusqueda(request) " + getAnioBusqueda(request));
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		DynaActionForm dForm = (DynaActionForm) form;
		if (request.getParameter("paramNewSini") != null) {
			request.getSession().removeAttribute("siniestroPadre");
			paramSiniPadre = null;
		}
		
		if (paramSiniPadre != null) {
			mapa.setIdSiniestro(Integer.parseInt(paramSiniPadre));
			siniPadre = ps.search(mapa);
			if (siniPadre.getEstatus().getId() == COD_ESTATUS_RECHAZADO || siniPadre.getEstatus().getId() == COD_ESTATUS_ANULADO) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.padreerroneo"));
				saveMessages(request, am);
				request.setAttribute("idSiniPadre", null);
				return mapping.findForward(FWD_INPUT);
			} else {
				if (!COD_SUB_CODIGO_PADRE.equals(siniPadre.getSubCodigo())) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.hijo.notpermitted"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
			}
			log.info("cobertura##01 " + dForm.getString("cobertura"));
			dForm.set("cobertura", String.valueOf(siniPadre.getCobertura().getId()));
			log.info("cobertura##02 " + dForm.getString("cobertura"));
			dForm.set("fechaOcurrencia", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));
			dForm.set("fechaNotificacion", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));
			dForm.set("tipoTratamiento", String.valueOf(siniPadre.getTipoTratamiento().getIdTipoTratamiento()));
			dForm.set("tipoEnfermedad", String.valueOf(siniPadre.getTipoEnfermedad().getId()));
			dForm.set("tipoProveedor", String.valueOf(siniPadre.getProveedor().getTipoProveedor().getId()));
			dForm.set("idProveedor", String.valueOf(siniPadre.getProveedor().getId()));
			dForm.set("proveedor", siniPadre.getProveedor().getDescripcion());
			dForm.set("idCausaIngreso", String.valueOf(siniPadre.getPatologiaOrganoTratamiento().getId()));
			dForm.set("causaIngreso", siniPadre.getPatologiaOrganoTratamiento().getDescripcion());
			dForm.set("tipoSiniestro", String.valueOf(siniPadre.getTipoSiniestro().getId()));
			request.setAttribute("idSiniPadre", paramSiniPadre);
			request.setAttribute("anioSiniPadre", siniPadre);
			request.setAttribute("siniestroPadre", siniPadre);
			request.getSession().setAttribute("siniestroPadre", siniPadre);
			mapa.setIdCobertura(siniPadre.getCobertura().getId());
			mapa.setCedulaBeneficiario(siniPadre.getCedulaBeneficiario());
			mapa.setAnioSiniestro(siniPadre.getAnioSiniestro());
			cob = pc.searchById(mapa.getIdCobertura());
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
				cobertura = pc.listByCedula(mapa);
				request.setAttribute("detalleMontoCobertura", cobertura);
			}
		} else {
			//siniPadre = (Siniestro) request.getSession().getAttribute("siniestroPadre");
			//dForm.set("cobertura", String.valueOf(COD_TIPO_COBERTURA_AMBULATORIO));
			/*mapa.setIdCobertura(COD_TIPO_COBERTURA_AMBULATORIO);
			mapa.setAnioSiniestro(getAnioBusqueda(request));
			mapa.setCedulaBeneficiario(t.getBeneficiario().getCedula());
			cob = pc.searchById(mapa.getIdCobertura());
			// Si es por patolog�a
			// Modifica por metodo que se adapte al anio*/
			Cobertura c = new Cobertura();
			
			PerPoliza pp = new PerPoliza();
			c.setPoliza(pp.searchActivo());
			
			c.getTipoCobertura().setId(COD_TIPO_COBERTURA_AMBULATORIO);
			c =pc.searchByPolizaTipoCobertura(c);
			dForm.set("cobertura", String.valueOf(c.getId()));
			
			
			mapa.setCedulaBeneficiario(t.getBeneficiario().getCedula());
			mapa.setCedula(t.getCedula());
			//dForm.set("id", String.valueOf(1));
			mapa.setIdCobertura(c.getId());
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

			dForm.set("tipoSiniestro", COD_TIPO_SIN_TRATAMIENTO_MED_AMB);
			dForm.set("tipoEnfermedad", COD_TIPO_ENFERMEDAD_CRONICA);
			dForm.set("estatus", COD_ESTATUS_ANALIZARR);
			dForm.set("tipoProveedor", COD_TIPO_PROVEEDOR);
		}
		setEntorno(request, form, ent);
		log.info("cobertura##03 " + dForm.getString("cobertura"));
		return mapping.findForward(FWD_INPUT);
	}
}

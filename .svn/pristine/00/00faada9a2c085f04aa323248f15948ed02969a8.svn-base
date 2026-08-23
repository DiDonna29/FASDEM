package ve.gob.dem.fasdem.action.medicinas;

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
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Mapa mapa = getDForm(request, form, ent);
		PerSiniestro ps = new PerSiniestro();
		DynaActionForm dForm = (DynaActionForm) form;
		int anioSiniestro2 = Integer.parseInt(mapa.getCodigo().substring(0, 2));
		int anioSiniestro1 = 20;
		String val = String.valueOf(anioSiniestro1) + String.valueOf(anioSiniestro2);
		int anioSiniestro = Integer.parseInt(val);
		mapa.setAnioSiniestro(anioSiniestro);
		mapa.setIdTipoTramite(COD_TIPO_MEDICINAS);
		Cobertura cob = new Cobertura();
		PerPoliza perPoliza = new PerPoliza();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		PerPersona pp = new PerPersona();
		Persona t = new Persona();
		try {
			Siniestro s = ps.searchByCodigo(mapa);
			if (s.getEstatus().getId() != 9) {
				am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
			mapa.setIdSiniestro(s.getId());
			mapa.setIssFactura(true);
			mapa.setIdSiniestro(s.getId());
			mapa.setCedulaBeneficiario(s.getCedulaBeneficiario());
			mapa.setCedula(s.getCedula());
			request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_MEDICINAS);
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
				cobertura = pc.listByCedula(mapa);
				request.setAttribute("detalleMontoCobertura", cobertura);
			}
			t = pp.buscar(s.getCedula());
			t.setBeneficiario(pp.buscar(s.getCedulaBeneficiario()));
			request.getSession().setAttribute(KEY_TITULAR, t);
			dForm.set("tipoEnfermedad", new Integer(s.getTipoEnfermedad().getId()).toString());
			dForm.set("tipoProveedor", new Integer(s.getProveedor().getTipoProveedor().getId()).toString());
			dForm.set("idProveedor", new Integer(s.getProveedor().getId()).toString());
			dForm.set("proveedor", s.getProveedor().getDescripcion());
			dForm.set("idCausaIngreso", new Integer(s.getPatologiaOrganoTratamiento().getId()).toString());
			dForm.set("causaIngreso", s.getPatologiaOrganoTratamiento().getDescripcion());
			dForm.set("codigo", s.getAniomesCodigo() + s.getCodigo() + s.getSubCodigo());
			dForm.set("estatus", new Integer(s.getEstatus().getId()).toString());
			dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
			dForm.set("observacion", s.getObservacion());
			mapa.setIdSiniestro(s.getId());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			ent = new Entorno(Entorno.MOD_MEDICINAS_EDITA);
			setEntorno(request, form, ent);
			request.setAttribute("form_action", mapping.getParameter());
			/***************************************************************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS  *********************/
			/***************************************************************************************************/
			/****************************************INICIO*****************************************************/
			/***************************************************************************************************/
								if (ent.isCobertura()) {
									int idPoliza =  s.getCobertura().getPoliza().getId();
									Poliza poliza = perPoliza.searchActivo();
									if (poliza.getId() != idPoliza) {
										
										setCoberturaParaModificar(request, poliza.getId(), idPoliza);
									}
								}
			/******************************************FIN******************************************************/
			/***************************************************************************************************/
			/************* PARA EDICION DE SINIESTROS DE POLIZAS ANTERIORES A LAS ACTIVAS  *********************/
			/***************************************************************************************************/
			/***************************************************************************************************/
			/***************************************************************************************************/
		} catch (PersonalNotFoundException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

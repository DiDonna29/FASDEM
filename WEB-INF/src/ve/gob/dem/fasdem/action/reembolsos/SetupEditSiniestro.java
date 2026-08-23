package ve.gob.dem.fasdem.action.reembolsos;

import java.sql.SQLException;
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
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerCuenta;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class SetupEditSiniestro extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCAR_SINIESTRO);

		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Mapa mapa = getDForm(request, form, ent);
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_REEMBOLSO);
		PerSiniestro ps = new PerSiniestro();
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		PerPersona pp = new PerPersona();
		Persona t = new Persona();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_REEMBOLSO);

		try {
			Siniestro s;
			s = ps.searchByCodigo(mapa);
			// COD_ESTATUS_LIQUIDADO
			// if (factura.getPreOrden() != null &&
			// !"".equals(factura.getPreOrden()))
			if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_REEMBOLSO) {
				/*
				 * if (s.getEstatus().getId()== COD_ESTATUS_LIQUIDADO ) {
				 * log.info(" paso recaudo1 " ); am.add(ALERT_AVISOS, new
				 * ActionMessage("env.general.noeditable"));
				 * saveMessages(request, am); return
				 * mapping.findForward(FWD_INPUT); }
				 */
				if (s.getEstatus().getId() == COD_ESTATUS_LIQUIDADO || s.getEstatus().getId() == COD_ESTATUS_RECHAZADO || s.getEstatus().getId() == COD_ESTATUS_ANULADO) {
					log.info(" paso recaudo1 ");
					am.add(ALERT_AVISOS, new ActionMessage("env.general.noeditable"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
				/*
				 * if(!ps.estatusEditable(s.getId())){
				 * log.info(" paso recaudo2 "+ s.getId()); am.add(ALERT_AVISOS,
				 * new ActionMessage("env.general.noeditable"));
				 * saveMessages(request, am); return
				 * mapping.findForward(FWD_INPUT); }
				 */
				DynaActionForm dForm = (DynaActionForm) form;
				request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
				dForm.set("id", String.valueOf(s.getId()));
				dForm.set("cobertura", new Integer(s.getCobertura().getId()).toString());
				mapa.setIdCobertura(s.getCobertura().getId());
				mapa.setCedula(s.getCedula());
				mapa.setCedulaBeneficiario(s.getCedulaBeneficiario());
				log.info(" paso mapa " + mapa);
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
				dForm.set("fechaOcurrencia", Utilidad.DateToString(s.getFechaOcurrencia(), "dd/MM/yyyy"));
				dForm.set("fechaNotificacion", Utilidad.DateToString(s.getFechaNotificacion(), "dd/MM/yyyy"));
				dForm.set("tipoSiniestro", new Integer(s.getTipoSiniestro().getId()).toString());
				ExpCuenta cb = new ExpCuenta();
				Cuenta cta = new Cuenta();
				if (t != null) {
					try {
						try {
							log.info("cuentaliderantes1" + cta);
							cta = cb.buscarCuentaBeneficiario(t.getCedula());
							log.info("cuentaliderdespues" + cta.getCuenta());
							request.setAttribute("cuenta", cta);
						} catch (PersonalNotFoundException e) {
						} catch (SQLException e) {
						}
					} catch (Exception e) {
						request.setAttribute("cuenta", null);
						request.setAttribute("cedula", null);
					}
				}
				dForm.set("tipoEnfermedad", new Integer(s.getTipoEnfermedad().getId()).toString());
				try {
					dForm.set("tipoProveedor", new Integer(s.getProveedor().getTipoProveedor().getId()).toString());
				} catch (Exception e) {
					log.info("error", e);
				}
				try{
				dForm.set("idProveedor", new Integer(s.getProveedor().getId()).toString());
				}catch(Exception e){log.info("error", e);}
				try{
				dForm.set("proveedor", s.getProveedor().getDescripcion());
				}catch(Exception e){log.info("error", e);}
				dForm.set("estatus", new Integer(s.getEstatus().getId()).toString());
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
				dForm.set("idCausaIngreso", new Integer(s.getPatologiaOrganoTratamiento().getId()).toString());
				dForm.set("causaIngreso", s.getPatologiaOrganoTratamiento().getDescripcion());
				dForm.set("anioSiniestro", String.valueOf(s.getAnioSiniestro()));
				dForm.set("observacion", s.getObservacion());
				dForm.set("codigo", s.getAniomesCodigo() + s.getCodigo() + s.getSubCodigo());
				dForm.set("monto", String.valueOf(s.getMontoPresupuestado()));
				ent = new Entorno(Entorno.MOD_REEMBOLSO_EDICION);
				setEntorno(request, form, ent);
				request.setAttribute("form_action", mapping.getParameter());
				request.setAttribute("siniestro", s);
			} else {
				am.add(ALERT_AVISOS, new ActionMessage("env.noreembolso"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		} catch (Exception e) {
			log.error("error ", e);
			am.add(ALERT_VALIDACION, new ActionMessage("env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}
}

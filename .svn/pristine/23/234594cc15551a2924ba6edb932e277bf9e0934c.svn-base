package ve.gob.dem.fasdem.action.comunes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerCuenta;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class BuscarSiniestros extends GenericAction {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		List sin = new ArrayList();
		Mapa mapa = new Mapa();
		Persona t = new Persona();
		Persona tmp = new Persona();
		Cobertura cob = new Cobertura();
		PerPersona pp = new PerPersona();
		PerCuenta pcta = new PerCuenta();
		List cobertura = new ArrayList();
		PerSiniestro ps = new PerSiniestro();
		PerCobertura pc = new PerCobertura();
		PerPoliza ppol = new PerPoliza();
		
		List desgloseCobertura = new ArrayList();
		Cuenta cuenta = null;
		String id = "";
		String cedula ="";
		
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		cedula = request.getParameter("cedTitular") + "." + request.getParameter("id");
		mapa.setCedula(request.getParameter("id"));
		mapa.setCedulaBeneficiario(request.getParameter("cedTitular"));

		try {
			id = request.getParameter("id");
			//tmp = pp.buscarPorTitular(id);
			tmp = pp.buscarBeneficiario1(mapa);
			log.info("1en el catch");
			if (tmp.getEstatus().equals("EGRESADO")) {
				tmp = pp.buscar(id);
			}
		} catch (NumberFormatException e) {
			log.error("error", e);
			return mapping.findForward(FWD_INPUT);
		} catch (PersonalNotFoundException e) {
			tmp = pp.buscar1(cedula);

		} catch (Exception e) {
			tmp = pp.buscarPorTitular(id);
			
		}
		try {
			cuenta = pcta.search(mapa.getCedula());
			tmp.setCuentaNomina(cuenta);
		} catch (PersonalNotFoundException e) {
		} catch (Exception e) {
		}
		if ("B".equals(tmp.getTipoPersona())) {
			try {
				t = pp.buscar2(mapa);
				t.setBeneficiario(tmp);
			} catch (Exception e) {
				t = pp.buscarPorTitular(tmp.getCedulaTitular());
				t.setBeneficiario(tmp);
			}
		} else {
			t = tmp;
			t.setBeneficiario(tmp);
		}
		try {
			try{
			mapa.setAnioSiniestro(Integer.parseInt(Utilidad.DateToString(ppol.searchActivo().getFechaFin(),"yyyy")));
			}catch (Exception e){
				mapa.setAnioSiniestro(getAnioBusqueda(request));
			}
			mapa.setIdTipoTramite(COD_TIPO_TRAMITE_APS);
			mapa.setCedulaBeneficiario(request.getParameter("cedTitular"));
			mapa.setCedula(request.getParameter("id"));
			// sin = ps.listarPorEstatus(mapa);
			sin = ps.searchCedulaBeneficiario(mapa);
		} catch (PersonalNotFoundException e) {
			log.info("notFound ", e);
		}
		mapa.setCedulaBeneficiario(request.getParameter("id"));
		mapa.setCedula(request.getParameter("cedTitular"));
		// Buscar siniestro
		try {
			cobertura = pc.listByCedula(mapa);
			// Verificar
			for (int i = 0; i < cobertura.size(); i++) {
				cob = (Cobertura) cobertura.get(i);
				if (cob.isPorPatologia()) {
					try {
						mapa.setIdCobertura(cob.getId());
						desgloseCobertura = pc.listDesgloseCobertura(mapa);
						cob.setDesgloseCobertura(desgloseCobertura);
					} catch (PersonalNotFoundException ignored) {
						List tmpCob = new ArrayList();
						Cobertura objCob = new Cobertura();
						try {
							objCob = pc.searchById(cob.getId());
							objCob.setPatologia(objCob.getTipoCobertura().getDescripcion());
							objCob.setMontoDisponible(objCob.getMonto());
						} catch (Exception ignored2) {
							log.info("no Encontrado", ignored2);
						}
						tmpCob.add(objCob);
						cob.setDesgloseCobertura(tmpCob);
						log.info("no Encontrado", ignored);
					}
				}
			}
			request.setAttribute(KEY_COBERTURA, cobertura);
		} catch (PersonalNotFoundException e) {
			log.info("notFound ", e);
		} catch (Exception e) {
			log.error("error ", e);
			request.setAttribute(KEY_COBERTURA, cobertura);
		}
		request.setAttribute(KEY_TITULAR, t);
		request.setAttribute(KEY_SINIESTROS, sin);
		request.setAttribute("cedBenefic", request.getParameter("id"));
		request.setAttribute("cedTitular", request.getParameter("cedTitular"));
		
		// request.setAttribute(KEY_TITULAR, t);
		return mapping.findForward(FWD_INPUT);
	}
}

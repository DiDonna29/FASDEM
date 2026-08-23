package ve.gob.dem.fasdem.action.cartaAval;

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
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarSiniestroNuevo extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		String id = "";
		PerPersona pp = new PerPersona();
		Persona tmp = new Persona();
		Persona t = new Persona();
		PerSiniestro ps = new PerSiniestro();
		PerCobertura pc = new PerCobertura();
		List sin = new ArrayList();
		List cobertura = new ArrayList();
		Mapa mapa = new Mapa();
		PerCuenta pcta = new PerCuenta();
		Cuenta cuenta = null;
		Cobertura cob = new Cobertura();
		List desgloseCobertura = new ArrayList();
		String cedula = request.getParameter("cedTitular") + "."
				+ request.getParameter("id");
		
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		try {
			id = request.getParameter("id");
			tmp = pp.buscar1(cedula);
			
		} catch (NumberFormatException e) {
			return mapping.findForward(FWD_INPUT);
		} catch (Exception e) {
			tmp = pp.buscarPorTitular(id);
		}
		try {
			cuenta = pcta.search(mapa.getCedula());
			tmp.setCuentaNomina(cuenta);
		} catch (PersonalNotFoundException e) {
			log.info("notFound ", e);
		} catch (Exception e1) {
			log.error("error buscando cuentas ", e1);
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
			mapa.setAnioSiniestro(getAnioBusqueda(request));
			mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
			mapa.setCedulaBeneficiario(request.getParameter("cedTitular"));
			mapa.setCedula(request.getParameter("id"));
			// sin = ps.listarPorEstatus(mapa);
			
			sin = ps.searchCedulaBeneficiario(mapa);
		} catch (PersonalNotFoundException e) {
			
		}
		mapa.setCedulaBeneficiario(request.getParameter("cedTitular"));
		mapa.setCedula(request.getParameter("id"));
		// Buscar siniestro
		try {
			cobertura = pc.listByCedula(mapa);
			// Verificar
			for (int i = 0; i <= cobertura.size(); i++) {
				cob = (Cobertura) cobertura.get(i);
				if (cob.isPorPatologia()) {
					mapa.setIdCobertura(cob.getId());
					desgloseCobertura = pc.listDesgloseCobertura(mapa);
					cob.setDesgloseCobertura(desgloseCobertura);
				}
			}
			request.setAttribute(KEY_COBERTURA, cobertura);
		} catch (Exception e) {
			request.setAttribute(KEY_COBERTURA, cobertura);
		}
		
		request.getSession().setAttribute(KEY_TITULAR, t);
		request.setAttribute(KEY_SINIESTROS, sin);
		return mapping.findForward(FWD_INPUT);
	}
}
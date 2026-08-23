/**
 * 
 */
package ve.gob.dem.fasdem.action.cobertura;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * @date 27/01/2012 fasdem
 */
public class ConsultaCobertura extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	Logger log = Logger.getLogger(ConsultaCobertura.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CONSULTA_COBERTURA);
		PerPoliza pp = new PerPoliza();
		PerCobertura pc = new PerCobertura();
		Persona t = null;
		Poliza p = new Poliza();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		if (request.getParameter("id") == null && request.getParameter("cedTitular") == null) {
			t = buscarTitularBeneficiario(request);
		} else {
			t = buscarTitularBeneficiarioPrivado(request);
		}
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_SUCCESS);
		}
		
		
		p = pp.search(Integer.parseInt(request.getParameter("poliza")));
		hm.put("id_poliza", p.getId());
		try {
			hm.put("anio_siniestro", Integer.parseInt(Utilidad.DateToString(p.getFechaFin(), "yyyy")));
		} catch (Exception e) {
			log.error("error", e);
		}
		hm.put("cedula_beneficiario", t.getBeneficiario().getCedula());
		hm.put("cedula_titular", t.getCedula());

		try {
			request.setAttribute("detalleCobertura", pc.listCoberturaByCedula(hm));
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("detalleCobertura", null);
		}
		try {
			request.setAttribute("detalleCoberturaPorPatologia", pc.listCoberturaByPatologia(hm));
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("detalleCoberturaPorPatologia", null);
		}
		request.setAttribute("detallePoliza", p);
		return mapping.findForward(FWD_SUCCESS);
	}

	private Persona buscarTitularBeneficiarioPrivado(HttpServletRequest request) throws PersonalNotFoundException, SQLException {
		PerPersona perp = new PerPersona();
		Persona t = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("cedulaT", request.getParameter("cedTitular"));
		hm.put("cedula", request.getParameter("id"));
		// (Persona)
		// request.getSession().getAttribute(KEY_TITULAR);
		t = perp.buscar(request.getParameter("cedTitular"));
		t.setBeneficiario(perp.buscarBeneficiario(hm));
		request.setAttribute(KEY_TITULAR, t);
		return t;
	}
}

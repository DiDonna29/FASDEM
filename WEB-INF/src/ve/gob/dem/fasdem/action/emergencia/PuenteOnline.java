/**
 * 27/02/2011 17:35:48
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.emergencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.Proveedor;
import ve.gob.dem.fasdem.bean.ServicioEnLinea;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerProveedor;
import ve.gob.dem.fasdem.per.PerServicioEnLinea;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class PuenteOnline extends GenericAction {
	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String siniestro = request.getParameter("siniestro");
		String telefonon= request.getParameter("telefonon");
		String bb= request.getParameter("beneficiario");
		String tt= request.getParameter("titular");
		ServicioEnLinea sel = new ServicioEnLinea();
		PerServicioEnLinea psel = new PerServicioEnLinea();
		sel = psel.search(Integer.parseInt(siniestro));
		String id = "";
		Mapa mapa = getDForm(request, form, ent);
		Persona tmp = new Persona();
		Persona t = new Persona();
		PerPersona pp = new PerPersona();
		Proveedor prov = new Proveedor();
		PerProveedor pprov = new PerProveedor();
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		PerPersona ppp = new PerPersona();
		
		try {
			id = sel.getCedula();
			tmp = pp.buscar(id);
		} catch (NumberFormatException e) {
			return mapping.findForward(FWD_INPUT);
		} catch (Exception e) {
			tmp = pp.buscarPorTitular(id);
		}
		if ("B".equals(tmp.getTipoPersona())) {
			try {
				t = pp.buscar(tmp.getCedulaTitular());
				t.setBeneficiario(tmp);
			} catch (Exception e) {
				t = pp.buscarPorTitular(tmp.getCedulaTitular());
				t.setBeneficiario(tmp);
			}
		} else {
			t = tmp;
			t.setBeneficiario(tmp);
		}
		prov = pprov.porRif(sel.getRif());
		DynaActionForm dForm = (DynaActionForm) form;
		mapa.setCedulaBeneficiario(bb);
		mapa.setCedula(tt);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		
		
		dForm.set("proveedor", prov.getDescripcion());
		dForm.set("idProveedor", new Integer(prov.getId()).toString());
		dForm.set("idProveedor", new Integer(prov.getId()).toString());
		dForm.set("tipoProveedor", new Integer(prov.getTipoProveedor().getId()).toString());
		
		
		// Modifica por metodo que se adapte al anio
		dForm.set("cobertura", "8");
		mapa.setCedulaBeneficiario(bb);
		mapa.setCedula(tt);
		dForm.set("id", String.valueOf(8));
		
		mapa.setIdCobertura(8);

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
		t = pp.buscar(String.valueOf(id));
		t.setBeneficiario(ppp.buscar(bb));
		request.getSession().setAttribute(KEY_TITULAR, t);
		// Modifica por metodo que se adapte al anio
		dForm.set("fechaIngreso", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));

		
		
		request.getSession().setAttribute(KEY_TITULAR, t);
		request.getSession().setAttribute("objPuenteOnline", sel);
		request.getSession().setAttribute("telefonon", telefonon);
		request.getSession().setAttribute("bb", bb);
		request.getSession().setAttribute("tt", tt);
		request.setAttribute("telefonon", telefonon);
		log.info("aqui paso el lider "+t.getCedula()+",,,,, "+t.getCedulaTitular());
		ent = new Entorno(Entorno.MOD_EMERGENCIA_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_EMERGENCIA);
		request.setAttribute("telefonon", telefonon);
		request.setAttribute("bb", bb);
		request.setAttribute("tt", tt);
		setEntorno(request, form, ent);
		request.setAttribute("form_action", mapping.getParameter());
		log.info("aqui paso el lider44 "+ tt + ",,,," + bb);
		return mapping.findForward(FWD_INPUT);
	}
}

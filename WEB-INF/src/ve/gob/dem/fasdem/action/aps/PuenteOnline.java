/**
 * 27/02/2011 17:35:48
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.aps;

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
import ve.gob.dem.fasdem.per.PerSiniestro;
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
		ServicioEnLinea sel = new ServicioEnLinea();
		PerServicioEnLinea psel = new PerServicioEnLinea();
		sel = psel.search(Integer.parseInt(siniestro));
		String id = "";
		Persona tmp = new Persona();
		Persona t = new Persona();
		PerPersona pp = new PerPersona();
		Proveedor prov = new Proveedor();
		PerProveedor pprov = new PerProveedor();
		Mapa mapa = getDForm(request, form, ent);
		PerSiniestro ps = new PerSiniestro();
		Cobertura cob = new Cobertura();
		List cobertura = new ArrayList();
		List desgloseCobertura = new ArrayList();
		PerCobertura pc = new PerCobertura();
		int aps =0;
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
		
		mapa.setCedulaBeneficiario(t.getCedula());
		mapa.setCedula(t.getBeneficiario().getCedula());
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		
		try {
		aps=ps.searchCedulaBeneficiarioAps(mapa);
		request.setAttribute("aps",aps);
		} catch(Exception e){
			aps=0;
			request.setAttribute("aps",aps);
		}
		prov = pprov.porRif(sel.getRif());
		DynaActionForm dForm = (DynaActionForm) form;
		log.info("Prueba 1 Belgica: "+request.getParameter("aps"));
		if (request.getParameter("aps")!=null && !"null".equals(request.getParameter("aps"))){
			request.setAttribute("aps", request.getParameter("aps"));
		}
	
		
		dForm.set("observacion", sel.getCausaIngreso());
		dForm.set("proveedor", prov.getDescripcion());
		dForm.set("idProveedor", new Integer(prov.getId()).toString());
		dForm.set("idProveedor", new Integer(prov.getId()).toString());
		dForm.set("tipoProveedor", new Integer(prov.getTipoProveedor().getId()).toString());
		dForm.set("monto", new Double(sel.getMonto()).toString());
		
		// Modifica por metodo que se adapte al anio
		dForm.set("cobertura", "1");
		
		//mapa.setCedulaBeneficiario(String.valueOf(id));
		mapa.setCedulaBeneficiario(t.getBeneficiario().getCedula());
		mapa.setCedula(t.getCedula());
		dForm.set("id", String.valueOf(1));
		
		mapa.setIdCobertura(1);

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
			log.info("mi mapa es " + mapa);
			cobertura = pc.listByCedula(mapa);
			request.setAttribute("detalleMontoCobertura", cobertura);
		}
		// Modifica por metodo que se adapte al anio
		dForm.set("fechaOcurrencia", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));
		dForm.set("fechaNotificacion", Utilidad.DateToString(new Date(), "dd/MM/yyyy"));
		dForm.set("tipoTratamiento", "1");
		dForm.set("tipoEnfermedad", "2");
		
		
		request.getSession().setAttribute(KEY_TITULAR, t);
		request.getSession().setAttribute("objPuenteOnline", sel);
		request.getSession().setAttribute("telefonon", telefonon);
		request.setAttribute("telefonon", telefonon);
		ent = new Entorno(Entorno.MOD_APS_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		request.setAttribute("telefonon", telefonon);
	
		setEntorno(request, form, ent);
		request.setAttribute("form_action", mapping.getParameter());
		//calcularCobertura(request, dForm);
		return mapping.findForward(FWD_INPUT);
	}
}

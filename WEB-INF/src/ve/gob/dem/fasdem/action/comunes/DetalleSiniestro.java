package ve.gob.dem.fasdem.action.comunes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class DetalleSiniestro extends GenericAction {


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Factura factura = new Factura();

		Factura detalleFactura = new Factura();
		PerFactura perFactura = new PerFactura();
		PerSiniestro ps = new PerSiniestro();
		Mapa mapa = new Mapa();

		String idSini = "";
		Siniestro s = null;
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		idSini = request.getParameter("idSini");
		if (idSini ==null){
			idSini = "";
		}

		if ("".equals(idSini)) {
			return mapping.findForward(FWD_INPUT);
		}
		try { 
			mapa.setIdSiniestro(Integer.parseInt(idSini));
			mapa.setAnioSiniestro(getAnioBusqueda(request));
			s= ps.search(mapa);
			request.setAttribute("siniestro", s);
		} catch (Exception e) {
			log.error("error", e);
		}
		try {
			Mapa m = new Mapa();
			m.setIdSiniestro(s.getId());
			m.setAnioSiniestro(getAnioBusqueda(request));
			factura = perFactura.searchIdSiniestro(m);
			factura.getIdFactura();
			mapa.setIdFactura(factura.getIdFactura());
			//detalleFactura=perFactura.searchDetalle(factura.getId());
			request.setAttribute("factura", factura);
			request.setAttribute("detalleFactura", detalleFactura);
			

		} catch (PersonalNotFoundException e1) {
			
			factura = new Factura();
			detalleFactura = new Factura();
		}
	

		return mapping.findForward(FWD_INPUT);
	}
}

package ve.gob.dem.fasdem.action.medicinas;

import java.util.ArrayList;
import java.util.List;

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

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Mapa mapa = new Mapa();
		Factura factura = new Factura();

		Factura detalleFactura = new Factura();
		PerFactura perFactura = new PerFactura();
		PerSiniestro ps = new PerSiniestro();
		List listFactura = new ArrayList(); 
		List listDetalle = new ArrayList(); 

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
		mapa.setIdSiniestro(Integer.parseInt(idSini));
		mapa.setId(Integer.parseInt(idSini));
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		try {
			s= ps.search(mapa);
			request.setAttribute("siniestro", s);
		} catch (Exception e) {
			
		}
		try {
			mapa.setIdSiniestro(s.getId());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			listFactura = perFactura.listSearchIdSiniestro(mapa);
			for (int r = 0; r != listFactura.size(); r++) {
				factura = (Factura) listFactura.get(r);
				
				factura.getIdFactura();
				mapa.setIdFactura(factura.getId());
				listDetalle=perFactura.listSearchDetalle(mapa);
			for (int i = 0; i != listDetalle.size(); i++) {
				detalleFactura=(Factura) listDetalle.get(i);
				
					request.setAttribute("factura", factura);
					request.setAttribute("detalleFactura", detalleFactura);
					request.setAttribute("listFactura", listFactura);
					request.setAttribute("listDetalle", listDetalle);
				}
				
			}
			
			request.setAttribute("factura", factura);
			request.setAttribute("detalleFactura", detalleFactura);
			request.setAttribute("listFactura", listFactura);
			request.setAttribute("listDetalle", listDetalle);
			

		} catch (PersonalNotFoundException e1) {
			
			factura = new Factura();
			detalleFactura = new Factura();
		}
		return mapping.findForward(FWD_INPUT);
	}
}

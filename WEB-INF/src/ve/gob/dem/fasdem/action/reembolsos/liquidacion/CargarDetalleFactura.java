/**
 * 02/03/2011 17:58:51
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.reembolsos.liquidacion;

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
import ve.gob.dem.fasdem.per.PerDetalleFactura;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class CargarDetalleFactura extends GenericAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_DETALLE_FACTURA);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		PerSiniestro ps = new PerSiniestro();
		PerFactura pf = new PerFactura();
		PerDetalleFactura pdf = new PerDetalleFactura();
		Siniestro s = null;
		Factura f = null;
		int idSini = Integer.parseInt(request.getParameter("idSini"));
		int idFact = Integer.parseInt(request.getParameter("idFact"));
		

		
		mapa.setIdSiniestro(idSini);
		mapa.setIdFactura(idFact);
		
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		s = ps.search(mapa);
		mapa.setIdFactura(idFact);
		// mapa.setAnioSiniestro(s.getAnioSiniestro()) ;
		f = pf.search(mapa);
		
		f.getMontoAmparado();
		request.setAttribute("idSini", idSini);
		request.setAttribute("idFact", idFact);
		request.setAttribute("siniestro", s);
		mapa.setIdFactura(idFact);
		try {

			mapa.setAnioSiniestro(getAnioBusqueda(request));
			request.setAttribute("detalleFactura", pdf.searchByFactura(mapa));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("detalleFactura", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("detalleFactura", null);
		}
		try { 
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setIdFactura(idFact);
		mapa.setMontoNegociado(mapa.getMontoFactura());
		mapa.setIdTipoGasto(Integer.parseInt(request.getParameter("tipoGasto")));
		pdf.insert(mapa);
		try {

			mapa.setAnioSiniestro(getAnioBusqueda(request));
			request.setAttribute("detalleFactura", pdf.searchByFactura(mapa));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("detalleFactura", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("detalleFactura", null);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

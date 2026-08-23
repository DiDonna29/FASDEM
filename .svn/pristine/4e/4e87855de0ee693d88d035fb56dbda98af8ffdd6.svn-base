/**
 * 02/03/2011 16:25:35
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.emergencia.liquidacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerDetalleFactura;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.valores.CargaValores;
import ve.gob.dem.framework.exception.PersonalNotFillItems;

/**
 * @author marcenrl
 * 
 */
public class EliminarDetalleFactura extends CargarDetalleFactura {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Mapa mapa = new Mapa();
		PerDetalleFactura pdf = new PerDetalleFactura();
		PerFactura pf = new PerFactura();
		getValores(mapa, request);
		int idDetaFact = Integer.parseInt(request.getParameter("idDetalleFact"));
		request.setAttribute("idSini", mapa.getIdSiniestro());
		request.setAttribute("idFact", mapa.getIdFactura());
		Factura f = pf.search(mapa);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		try {
			pdf.delete(idDetaFact);
			mapa.setIdTipoGasto(CargaValores.getInstance().getValores().getTipoGastoIva());
			mapa.setMonto(calcularIva(pdf.searchByFactura(mapa), f.getPorcentajeIva() ));
			pdf.insertIva(mapa);
		} catch (Exception e) {
			log.error("error", e);
		}
		return mapping.findForward(FWD_INPUT);
	}
}

/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.pago;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.pagos.ExpDetalleFacturaPago;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpUnidadTributaria;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class ConsultaSiniestro extends GenericAction {
	static protected Logger log = Logger.getLogger(ConsultaSiniestro.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String accion = request.getParameter("accionPago");
		log.info("ACCION " + accion);
		if (accion == null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
			return mapping.findForward("H1");
		} else {
			if (accion.equals("1")) {
				ArrayList list = ExpDetalleFacturaPago.buscarListaCodigoSiniestro(request.getParameter("cod_sin").replace("-", "").trim(), (!request.getParameter("cod_sin").trim().equals("") ? Integer.parseInt("20" + request.getParameter("cod_sin").substring(0, 2)) : Integer.parseInt(Utilidad.DateToString(new Date(), "YYYY"))));
				request.setAttribute("lista", list);
				request.setAttribute("listaUnidad", ExpUnidadTributaria.buscarLista());
				request.setAttribute("primera", "no");
			}
			if (accion.equals("3")) {
				ArrayList list = ExpDetalleFacturaPago.buscarListaCodigoFactura(request.getParameter("numero_factura"), Integer.parseInt(request.getParameter("anio_factura")));
				request.setAttribute("lista", list);
				request.setAttribute("listaUnidad", ExpUnidadTributaria.buscarLista());
				request.setAttribute("primera", "no");
			}
			if (accion.equals("2")) {
				int anio = Integer.parseInt("20" + request.getParameter("cod1").substring(0, 2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"), anio);
				DetallePreOrdenPago det ;
				
				//preorden segun el tipo
				det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("cod1"), anio);
				
				
			   
				
				
				request.setAttribute("preorden", pre);
				request.setAttribute("detalle", det);
				return mapping.findForward("H2");
			}
			return mapping.findForward("H1");
		}
	}
}

/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.pago;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.pagos.ExpEstatusPreorden;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class ConsultaPreorden extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(ConsultaPreorden.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			log.info("ENTRO A LA ACCION NULA");
			request.setAttribute("listaEstatus", ExpEstatusPreorden.BuscarListaEstatusPreorden());
			request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
			return mapping.findForward("H1");
		} else {
			log.info("ENTRO A LA ACCION NO NULA");
			if (accion.equals("1")) {
				int anio = Integer.parseInt(request.getParameter("fechaInicio").substring(6, 10));
				ArrayList list = new ArrayList();
				try {
					list = ExpPreOrdenPago.buscarListaProveedorFechaPreOrden(Integer.parseInt(request.getParameter("idProveedor")), request.getParameter("fechaInicio"), request.getParameter("fechaFin"), Integer.parseInt(request.getParameter("estatus")), anio);
				} catch (NumberFormatException e) {
					log.info("formato de numero", e);
				}
				request.setAttribute("prov", ExpClinica.BuscarPorid(Integer.parseInt(request.getParameter("idProveedor"))));
				request.setAttribute("dselect", request.getParameter("fechaInicio"));
				request.setAttribute("hselect", request.getParameter("fechaFin"));
				request.setAttribute("lista", list);
				request.setAttribute("primera", "no");
			}
			if (accion.equals("2")) {
				HashMap mapa = new HashMap();
				JasperPrint jasperprint;
				String realPath = getServlet().getServletContext().getRealPath("/");
				String path;
				String rptfilename = "";
				ServletOutputStream outstream2;
				byte[] pdfasbytes;
				mapa.put("ruta", realPath + "images/");
				mapa.put("REPORT_LOCALE", new Locale("es", "VE"));
				String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
				Connection db = null;
				mapa.put("preorden", request.getParameter("pre"));
				try {
					db = Conexion.getConexion();
					rptfilename = "/jasper/FASDEM/FiniquitoDePagoClinicas.jasper";
					path = realPath + rptfilename;
					// LLENAR EL INFORME
					jasperprint = JasperFillManager.fillReport(path, mapa, db);
					// EXPORTAR EL INFORME A FORMATO PDF.
					pdfasbytes = JasperExportManager.exportReportToPdf(jasperprint);
					outstream2 = response.getOutputStream();
					response.setContentType("application/pdf");
					response.setContentLength(pdfasbytes.length);
					response.setHeader("Pragma", "no-cache");
					response.setDateHeader("Expires", 0);
					response.setHeader("Content-disposition", "inline; filename=\"Report" + date + ".pdf\"");
					outstream2.write(pdfasbytes);
				} finally {
					Conexion.closeConexion(db);
				}
			}
			if (accion.equals("3")) {
				int anio = Integer.parseInt("20" + request.getParameter("codigo1").substring(0, 2));
				ArrayList list = ExpPreOrdenPago.buscarporCodigo(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"), anio);
				try {
					PreOrdenPago pre = (PreOrdenPago) list.get(0);
					request.setAttribute("prov", pre.getProveedor());
				} catch (Exception e) {
				}
				request.setAttribute("lista", list);
				request.setAttribute("primera", "no");
			}
			if (accion.equals("4")) { // ir a la pagina de detalle desde la
										// lista principal
				int anio = Integer.parseInt("20" + request.getParameter("cod1").substring(0, 2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"), anio);
				DetallePreOrdenPago det;
				log.info("Tipo de Preorden " + pre.getTipo_preorden());
				det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("cod1"), anio);
				
				
				
				
				 


				request.setAttribute("preorden", pre);
				request.setAttribute("detalle", det);
				return mapping.findForward("H2");
			}
			request.setAttribute("listaEstatus", ExpEstatusPreorden.BuscarListaEstatusPreorden());
			request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
			return mapping.findForward("H1");
		}
	}
}

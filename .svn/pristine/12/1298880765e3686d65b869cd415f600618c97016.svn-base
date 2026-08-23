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
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.pagos.ExpEstatusPreorden;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;

public class RegistroOrden extends GenericAction {
	static protected Logger log = Logger.getLogger(RegistroOrden.class);

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
			request.setAttribute("listaEstatus", ExpEstatusPreorden.BuscarListaEstatusPreorden());
			request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
			return mapping.findForward("H1");
		} else {
			if (accion.equals("1")) {
				int anio = Integer.parseInt(request.getParameter("fechaInicio").substring(6, 10));
				ArrayList list = ExpPreOrdenPago.buscarListaProveedorFechaPreOrden(Integer.parseInt(request.getParameter("idProveedor")), request.getParameter("fechaInicio"), request.getParameter("fechaFin"), Integer.parseInt(request.getParameter("estatus")), anio);
				request.setAttribute("prov", ExpClinica.BuscarPorid(Integer.parseInt(request.getParameter("idProveedor"))));
				request.setAttribute("dselect", request.getParameter("fechaInicio"));
				request.setAttribute("hselect", request.getParameter("fechaFin"));
				request.setAttribute("ProvSelect", request.getParameter("idProveedor"));
				request.setAttribute("AccSelect", accion);
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
					rptfilename = "/jasper/FASDEM/FiniquitoDePago.jasper";
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
				ArrayList list = ExpPreOrdenPago.buscarporCodigoEstatus(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"), anio, Constantes.StatusEditarOnt);
				try {
					PreOrdenPago pre = (PreOrdenPago) list.get(0);
					request.setAttribute("prov", pre.getProveedor());
				} catch (Exception e) {
				}
				request.setAttribute("AccSelect", accion);
				request.setAttribute("CodSelect", request.getParameter("codigo1") + "-" + request.getParameter("codigo2"));
				request.setAttribute("lista", list);
				request.setAttribute("primera", "no");
			}
			if (accion.equals("4")) { // ir a la pagina de detalle desde la
										// lista principal
				int anio = Integer.parseInt("20" + request.getParameter("cod1").substring(0, 2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"), anio);
				DetallePreOrdenPago det ;
				
				//preorden segun el tipo
				det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("cod1"), anio);
				
				
			    
				
				
				request.setAttribute("preorden", pre);
				request.setAttribute("detalle", det);
				return mapping.findForward("H2");
			}
			if (accion.equals("5")) {
				int anio;
				ArrayList list;
				if (request.getParameter("AccSelect1").equals("1")) {
					anio = Integer.parseInt(request.getParameter("dselect1").substring(6, 10));
					list = ExpPreOrdenPago.buscarListaProveedorFechaPreOrden(Integer.parseInt(request.getParameter("ProvSelect1")), request.getParameter("dselect1"), request.getParameter("hselect1"), Constantes.StatusAutorizado, anio);
				} else {
					anio = Integer.parseInt("20" + request.getParameter("CodSelect1").substring(0, 2));
					list = ExpPreOrdenPago.buscarporCodigoEstatus(request.getParameter("CodSelect1"), anio, Constantes.StatusEditarOnt);
				}
				int contador = 0;
				for (int i = 0; i < list.size(); i++) {
					PreOrdenPago pre = (PreOrdenPago) list.get(i);
					if (!request.getParameter("fechaOrden" + pre.getId()).equals("")) {
						if (!request.getParameter("nro" + pre.getId()).equals("")) {
							ExpPreOrdenPago.cambiarEstatus(pre.getCod_completo(), request.getParameter("nro" + pre.getId()), Constantes.StatusONT, Utilidad.StringToDate(request.getParameter("fechaOrden" + pre.getId()), "dd/MM/yyyy"), null, Integer.parseInt("20" + pre.getCod_completo().substring(0, 2)));
							incluirTraza(TR_PAGO_CAMBIAR_PREORDEN_ONT, pre.getCod_completo(), "Cambiar PreOrden a ONT", usuarioSession(request));
							contador = contador + 1;
						}
					}
				}
				if (contador == 0) {
					request.setAttribute("mensaje", "No se realizarón actualizaciones");
				} else {
					request.setAttribute("mensaje", "Se actualizaron " + contador + " Pre Ordenen(s)");
				}
			}
			request.setAttribute("listaEstatus", ExpEstatusPreorden.BuscarListaEstatusPreorden());
			request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
			return mapping.findForward("H1");
		}
	}
}

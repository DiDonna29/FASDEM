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
import ve.gob.dem.fasdem.exp.pagos.ExpEstatusPreorden;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;

public class AutorizaPreorden extends GenericAction {
	static protected Logger log = Logger.getLogger(AutorizaPreorden.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
			log.info("ENTRO A LA ACCION NO NULA");
			if (accion.equals("1")) {
				int anio = Integer.parseInt("20" + request.getParameter("codigo1").substring(0, 2));

				ArrayList list = ExpPreOrdenPago.buscarporCodigo(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"), anio);
				request.setAttribute("lista", list);
				request.setAttribute("primera", "no");
			}
			if (accion.equals("5")) {
				int anio = Integer.parseInt("20" + request.getParameter("cod1").substring(0, 2));
				ExpPreOrdenPago.autorizarEstatusPreOrden(request.getParameter("cod1"), Constantes.StatusAutorizado, anio);
				incluirTraza(TR_PAGO_AUTORIZAR_PREORDEN, request.getParameter("cod1"), "Autorizacion de PreOrden de Pago", usuarioSession(request));

				ArrayList list = ExpPreOrdenPago.buscarporCodigo(request.getParameter("cod1"), anio);
				request.setAttribute("lista", list);
				request.setAttribute("primera", "no");
				request.setAttribute("mensaje", "Pre-Orden de Pago Nro " + request.getParameter("cod1") + " ha sido autorizada satisfactoriamente");
			}
			if (accion.equals("6")) {
				int anio = Integer.parseInt(request.getParameter("anio_pre"));

				ArrayList list = ExpPreOrdenPago.buscarporanioEstatus(anio, Constantes.StatusEnAdministracion);
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
				int anio = Integer.parseInt("20" + request.getParameter("cod1").substring(0, 2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"), anio);
				DetallePreOrdenPago det ;
				
				//preorden segun el tipo
				det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("cod1"), anio);
				
				
			   
				
				
				
				request.setAttribute("preorden", pre);
				request.setAttribute("detalle", det);
				return mapping.findForward("H2");
			}
			if (accion.equals("4")) {
				PreOrdenPago pre = new PreOrdenPago();
				if (request.getParameter("fechaCheque").equals("")) {
					pre.setFecha_pagado(null);
				} else {
					pre.setFecha_pagado(Utilidad.StringToDate(request.getParameter("fechaCheque"), "dd/mm/yyyy"));
				}
				if (request.getParameter("fechaPago").equals("")) {
					pre.setFecha_orden(null);
				} else {
					pre.setFecha_orden(Utilidad.StringToDate(request.getParameter("fechaPago"), "dd/mm/yyyy"));
				}
				// ExpPreOrdenPago.cambiarEstatus(request.getParameter("codigoPreo"),
				// request.getParameter("orden"),
				// Integer.parseInt(request.getParameter("estatus")),
				// pre.getFecha_orden(), pre.getFecha_pagado());
				request.setAttribute("mensaje", "Pre-Orden de Pago Nro " + request.getParameter("codigoPreo") + " ha sido actualizada satisfactoriamente");
				return mapping.findForward("H1");
			}
			request.setAttribute("listaEstatus", ExpEstatusPreorden.BuscarListaEstatusPreorden());
			request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
			return mapping.findForward("H1");
		}
	}
}

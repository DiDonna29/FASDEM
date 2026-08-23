package ve.gob.dem.fasdem.action.comunes;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.PdfOrdenMedicina;
import ve.gob.dem.fasdem.bean.Reporte;
import ve.gob.dem.fasdem.per.PerFirmaRegistro;
import ve.gob.dem.fasdem.per.PerPdfOrdenMedicina;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Encriptar;
import ve.gob.dem.framework.recursos.Utilidad;

//Esta clase se utiliza para generar los reportes de la aplicaci�n.
public class PintaReporte extends GenericAction {
	static Logger log = Logger.getLogger(PintaReporte.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("prueba 1");

		ActionMessages am = new ActionMessages();
		if (isCancelled(request)) {
			request.getSession().removeAttribute(mapping.getAttribute());
			return mapping.findForward("cancel");
		}


		Connection conn = null;

		try {

			//si es la carta de asegurabilidad, pasar conexcion a sys_bienestar
			// Conseguir una conexi�n de JDBC.
			Context initContext = new InitialContext();
			DataSource dataSource=(DataSource) initContext.lookup("java:comp/env/jdbc/fasdem");	
			
			if (GenericAction.COD_TIPO_REPORTE_CARTA_ASEGURABILIDAD.equals(request.getAttribute("id_reporte"))) {
			
				dataSource =  (DataSource) initContext.lookup("java:comp/env/jdbc/bienestar");	
				
			}
			
			
			conn = dataSource.getConnection();

			Reporte rep = (Reporte) request.getAttribute("reporte");

			String rptfilename = rep.getArchivoReporte();
			HashMap params = rep.getMapa();
			Mapa mp = (Mapa) request.getAttribute("mp");
			params.put("RUTA", getServlet().getServletContext().getRealPath("/"));
			String token = "no token";

			if (GenericAction.COD_TIPO_REPORTE_NOTAMEDICA.equals(request.getAttribute("id_reporte"))) {

				try {
					token = Encriptar.encriptar(mp.getIdUsuario() + Utilidad.DateToString(new Date(), "ddMMyyyyHHmmss"));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					log.error(e);
					// e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					log.error(e);
					// e.printStackTrace();
				}
				params.put("token", token);

			}

			log.info("voy a tratar de hacer imprimir el reporte ");
			JasperPrint jasperprint = JasperFillManager.fillReport(getServlet().getServletContext().getRealPath("/") + "/jasper/" + rptfilename, params, conn);
			log.info("nro paginas " + jasperprint.getPages().size());

			byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperprint);

			log.info("paso parte -1");

			if (GenericAction.COD_TIPO_REPORTE_NOTAMEDICA.equals(request.getAttribute("id_reporte"))) {

				PerFirmaRegistro fr = new PerFirmaRegistro();
				log.info("paso parte 0");
				fr.insert(pdfasbytes, mp, token);

			}

			ServletOutputStream outstream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(pdfasbytes.length);
			if ("1".equals(request.getAttribute("id_reporte"))) {
				PerPdfOrdenMedicina ppom = new PerPdfOrdenMedicina();
				PdfOrdenMedicina pom = new PdfOrdenMedicina();
				pom.setPdf(pdfasbytes);
				pom.setCodigoValidacion((String)params.get("codigo"));
				ppom.insert(pom);
				response.setHeader("Content-disposition", "inline; filename=\"OrdenDeMedicinas.pdf\"");
			}
			if ("2".equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"Usuarios.pdf\"");
			}
			if ("3".equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"Estadistica_reembolso.pdf\"");
			}
			if ("4".equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"Estadistica.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_CARTA_AVAL.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"CartaAval.pdf\"");
			}

			if ("6".equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"ResRmbls.pdf\"");
				incluirTraza(GenericAction.TR_REEMBOLSO_IMPRIMIR_RESERVA, String.valueOf(request.getAttribute("id")), "REPORTE RESERVA DE SINIESTRO ", usuarioSession(request));
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_COBERTURA.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"NotaCobertura.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_COBERTURA_REEMBOLSO.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"NotaCobertura.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_RECHAZO_REEMBOLSO.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"RechazoRmbls.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_RECAUDOS_REEMBOLSO.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"SolicitudRecaudoRmbls.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_TECNICA_USUARIO_ACTIVO.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"NotaTecnicaUsuarioActivo.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_TECNICA_SINIESTRO.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"NotaTecnicaSiniestro.pdf\"");
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_TECNICA_SINIESTRORMBLS.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"NotaTecnicaSiniestroRmbls.pdf\"");
				incluirTraza(GenericAction.TR_REEMBOLSO_IMPRIMIR_RESERVA, String.valueOf(request.getAttribute("id")), "REPORTE DE NOTA TÉCNICA ", usuarioSession(request));

			}
			if (GenericAction.COD_TIPO_REPORTE_CARTA_ASEGURABILIDAD.equals(request.getAttribute("id_reporte"))) {
				response.setHeader("Content-disposition", "inline; filename=\"CartaAsegurabilidad.pdf\"");
				incluirTraza(GenericAction.TR_IMPRIMIR_CARTA_ASEGURABILIDAD, mp.getCedula(), "CARTA DE ASEGURABILIDAD CÓDIGO: "+params.get("token"), usuarioSession(request));

			}
			if (GenericAction.COD_TIPO_REPORTE_FINIQUITO_COBERTURA.equals(request.getAttribute("id_reporte"))) {
				
				response.setHeader("Content-disposition", "inline; filename=\"FiniquitoCobertura.pdf\"");
				incluirTraza(GenericAction.TR_IMPRIMIR_FINIQUITO_COBERTURA, String.valueOf(request.getAttribute("id")), "REPORTE DE FINIQUITO DE COBERTURA CÓDIGO: "+params.get("token"), usuarioSession(request));

			}
			outstream.write(pdfasbytes);

			if (jasperprint.getPages().size() == 0) {
				am.add(GenericAction.ALERT_AVISOS, new ActionMessage("env.general.reporte.noData"));
				saveMessages(request, am);
			}

		} finally {
			conn.close();
			conn = null;
		}

		return null;
	}
}

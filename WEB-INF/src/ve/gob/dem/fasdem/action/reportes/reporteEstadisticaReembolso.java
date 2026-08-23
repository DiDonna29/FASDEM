package ve.gob.dem.fasdem.action.reportes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class reporteEstadisticaReembolso extends GenericAction {
	Logger log = Logger.getLogger(reporteEstadisticaReembolso.class);
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_REPORTE_USUARIO);

		
		
		try {
			
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			
		}
		return mapping.findForward(FWD_INPUT);
		
		/*PrintWriter out = null;
		Connection conn = null;
		mapa = getDForm(request, form, ent);
		request.getParameter("fechaInicio");
		request.getParameter("fechaFin");
		log.info("fechaInicio: "+request.getParameter("fechaInicio"));
		log.info("fechaFin: "+request.getParameter("fechaFin"));
		mapa.setFechaInicio(request.getParameter("fechaInicio"));
		mapa.setFechaFin(request.getParameter("fechaFin"));
		
		
		JasperPrint jasperprint;
		String realPath = getServlet().getServletContext().getRealPath("/");
		String path;
		String rptfilename="";	
		ServletOutputStream outstream2;
		byte[] pdfasbytes;
		ServletOutputStream outstream;
		HashMap params = new HashMap();
				
		
		String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
		mapa.put("RUTA", realPath);
		params.put("REPORT_LOCALE",  new Locale ("es"));	
		params.put("fechaInicio",request.getParameter("fechaInicio"));
		 params.put("fechaFin",request.getParameter("fechaFin"));
		log.info("MAPA: "+ mapa);
		   Connection db = null;
			 try {	
			      
				   db =   Conexion.getConexion();
				   log.info("DB:"+ db);
				   rptfilename ="/jasper/Estadistica_reembolso.jasper";
				   path = realPath + rptfilename;
				   log.info("path:"+ path);
				   // LLENAR EL INFORME
				   jasperprint = JasperFillManager.fillReport(path, mapa, db);
				   log.info("jasperprint:"+ jasperprint);
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
		return mapping.findForward(FWD_INPUT);*/

	}

	
	
}

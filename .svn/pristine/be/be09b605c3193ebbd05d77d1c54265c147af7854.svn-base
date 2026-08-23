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

public class reporteUsuario extends GenericAction {
	Logger log = Logger.getLogger(reporteUsuario.class);
	
	
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
		log.info("fechaInicio: "+Utilidad.StringToDate(request.getParameter("fechaInicio"), "dd/MM/yyyy"));
		log.info("fechaFin: "+Utilidad.StringToDate(request.getParameter("fechaFin"), "dd/MM/yyyy"));
		mapa.setFechaInicio(request.getParameter("fechaInicio"));
		mapa.setFechaFin(request.getParameter("fechaFin"));
		
		JasperPrint jasperprint;
		String realPath = getServlet().getServletContext().getRealPath("/");
		String path;
		String rptfilename="";	
		ServletOutputStream outstream2;
		byte[] pdfasbytes;
		ServletOutputStream outstream;
		String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
		mapa.put("RUTA", realPath + "images/");
		   Connection db = null;
			 try {	
			      
				   db =   Conexion.getConexionGlobal();
				   rptfilename ="/jasper/Usuarios.jasper";
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
		return mapping.findForward(FWD_INPUT);*/

	}

	
	
}

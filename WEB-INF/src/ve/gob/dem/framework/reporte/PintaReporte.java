package ve.gob.dem.framework.reporte;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class PintaReporte extends HttpServlet {


/**
	 * 
	 */
	private static final long serialVersionUID = -123255653841355073L;

public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

	this.handleSubmit(request, response);
 
     }

     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         this.handleSubmit(request, response);
     }
 
     @SuppressWarnings("rawtypes")
	protected void handleSubmit(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

 
         Connection conn = null;
         try {
             // Get a JDBC Connection
             Context initContext = new InitialContext();
             DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/sica");
             conn = dataSource.getConnection();
 
 			Reporte rep = (Reporte) request.getAttribute("reporte");
			String rptfilename = rep.getArchivoReporte();
			HashMap params = rep.getMapa();
 			
 
           // fill the report
		   JasperPrint jasperprint = JasperFillManager.fillReport(getServletContext().getRealPath("/")+ "jasper/" + rptfilename, params,conn);
             // export report to pdf and stream back to browser
			byte[] pdfasbytes =  JasperExportManager.exportReportToPdf(jasperprint);
              ServletOutputStream outstream = response.getOutputStream();
             response.setContentType("application/pdf");
             response.setContentLength(pdfasbytes.length);
              response.setHeader("Content-disposition","inline; filename=\"Report.pdf\"");
             outstream.write(pdfasbytes);
 
         } catch (Exception ne) {
			principal(request,response);
         } finally {
             try {
                 // We're done here, so clean up the connection
                 conn.close();
             } catch (SQLException sqle) {
                 // We don't actually care, we were just trying to clean up an
                 // expensive DB connection
             }
         }
     }

	protected void redireccion(HttpServletRequest request, HttpServletResponse response, String uri)
	throws ServletException, java.io.IOException
	{
	String jspPresentacion = uri;
	getServletConfig().getServletContext().getRequestDispatcher(jspPresentacion).forward(request, response);
	}

	protected void principal(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, java.io.IOException
	{
		request.setAttribute("Msg","17");
		redireccion(request,response,"/jsp/Carga_Mensajes_Reportes.jsp");
	
	}

 } 
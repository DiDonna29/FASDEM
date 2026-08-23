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

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.ResumenPreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;


public class PrintHojaRutaPagoNominaxls extends GenericAction {


    static protected Logger log = Logger.getLogger(PrintHojaRutaPagoNominaxls.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	HttpSession session = request.getSession();
    	
    	Usuario usuario = null;
    	Usuario usuario1 = null;
    	
    	usuario = (Usuario) session.getAttribute("usuario");
    	usuario1=ExpUsuario.buscarDatosP(usuario.getLogin());
    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

				
    	
				    	HashMap mapa =new HashMap();
						JasperPrint jasperprint;
						String realPath = getServlet().getServletContext().getRealPath("/");
						String path;
						String rptfilename="";	
						ServletOutputStream outstream2;
						

						mapa.put("ruta", realPath + "images/");
						mapa.put("REPORT_LOCALE",  new Locale("es", "VE"));
						String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
						
					
				     Connection db = null;
					 try {	
					      
						   db =   Conexion.getConexion();
						   
						
						  rptfilename ="/jasper/HojaRutaPagoNominaXLS.jasper";
						
						  mapa.put("HOJA",request.getParameter("hr"));
						  
						 
						   
						   path = realPath + rptfilename;
						   // LLENAR EL INFORME
						   jasperprint = JasperFillManager.fillReport(path, mapa, db);
						   // EXPORTAR EL INFORME A FORMATO PDF.
						   //pdfasbytes = JasperExportManager.e(jasperprint);
						   outstream2 = response.getOutputStream();
						   response.setContentType("application/vnd.ms-excel");
						   //response.setContentLength(pdfasbytes.length);
						   response.setHeader("Pragma", "no-cache");
						   response.setDateHeader("Expires", 0);
						   response.setHeader("Content-disposition", "inline; filename=\"Report" + date + ".xls\"");
						   //outstream2.write(pdfasbytes);
						   
						   
						    JRXlsExporter exporter = new JRXlsExporter();
							exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
							exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
							exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
							exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
							exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
							exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outstream2);
							exporter.exportReport();
   
						
					} finally {
					    Conexion.closeConexion(db);
					}
					return null;
    }

}
/*
 * Created on 01/02/2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.reporte;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.framework.exception.InterruptTimerTask;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author enmarcano
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PrintReporteExcel extends Action {
	static protected Logger log = Logger.getLogger(PrintReporteExcel.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			request.getSession().removeAttribute(mapping.getAttribute());
			return mapping.findForward("cancel");
		}
		Connection conn = null;
		ActionMessages am = new ActionMessages();
		try {
			JasperPrint jasperPrint = null;
			Context initContext = new InitialContext();
			DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/fasdem");
			conn = dataSource.getConnection();
			Reporte rep = (Reporte) request.getAttribute("reporte");
			// Cargamos la definicion del reporte *.jasper
			Timer timer = null;
			try {
				timer = new Timer(true);
				InterruptTimerTask interruptTimerTask = new InterruptTimerTask(Thread.currentThread());
				timer.schedule(interruptTimerTask, 240000);
				File reportFile = new java.io.File(getServlet().getServletContext().getRealPath("/") + "/jasper/" + rep.getArchivoReporte());
				// cargamos parametros del reporte (si tiene).
				// Map parametros = new HashMap();
				// Generar XLS.
				// Preparacion del reporte (en esta etapa se inserta el valor
				// del
				// query en el reporte).
				
				jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), rep.getMapa(), conn);
				
				// Nombre archivo resultado.
				// Sugerencia: traten de generar un nombre dinamico concatenando
				// fecha y hora para evitar se pisen los reportes al tener el
				// mismo
				// nombre.
				ServletOutputStream outstream = response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				// response.setContentLength();
				response.setHeader("Content-disposition", "inline; filename=\"Reporte.xls\"");
				// Creacion del XLS
				//log.info("valoressssssss", rep.getMapa());
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outstream);
				exporter.exportReport();
			} catch (JRException e) {
				log.error("timeout exeeded", e);
				am.add(GenericAction.ALERT_AVISOS, new ActionMessage("env.time.exceed"));
				saveMessages(request, am);
				throw e;
			} finally {
				conn.close();
				timer.cancel();
			}
		} finally {
			try {
				// We're done here, so clean up the connection
				conn.close();
			} catch (SQLException sqle) {
				log.info("error", sqle);
			}
		}
		return null;
	}
}

package ve.gob.dem.framework.reporte;

import java.sql.Connection;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PintaReporte2 extends Action {
	static Logger log = Logger.getLogger(PintaReporte2.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			request.getSession().removeAttribute(mapping.getAttribute());
			return mapping.findForward("cancel");
		}

		Connection conn = null;
		HttpSession httpsc = request.getSession();
		if (httpsc.getAttribute("usuario") == null) {
			request.setAttribute("mensaje", "Su session ha expirado");
			request.setAttribute("direccion", "http://intranet.dem.gob.ve");
			return (mapping.findForward("Intra"));
		}
		try {
			// Get a JDBC Connection
			Context initContext = new InitialContext();
			DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/fasdem");
			conn = dataSource.getConnection();
			Reporte rep = (Reporte) request.getAttribute("reporte");
			String rptfilename = rep.getArchivoReporte();
			HashMap params = rep.getMapa();
			JasperPrint jasperprint = JasperFillManager.fillReport(getServlet().getServletContext().getRealPath("/") + "/jasper/" + rptfilename, params, conn);
			// export report to pdf and stream back to browser
			byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperprint);
			ServletOutputStream outstream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(pdfasbytes.length);
			response.setHeader("Content-disposition", "inline; filename=\"ListadoClinicas.xls\"");
			// ExpAccion.incluirAccion((String)httpsc.getAttribute("traza"),valorafectado,usuario);
			// httpsc.removeAttribute("traza");
			outstream.write(pdfasbytes);
		} finally {
			conn.close();
			conn = null;
		}
		return null;
	}
}

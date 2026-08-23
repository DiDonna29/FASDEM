package ve.gob.dem.fasdem.action.reportes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.reporte.ExpReporte;
import ve.gob.dem.framework.reporte.Reporte;

/**
 * @author marcenrl
 * 
 */
public class reporteClinicaImprimir extends GenericAction {
			 
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(reporteClinicaImprimir.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		String accion = request.getParameter("tipo");

			if (accion.equals("4")) {//**** Reporte Clinicas GC
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),"");
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("5")) {//**** Reporte APS GC
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),"");
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("6")) {//**** Reporte Clinicas Costo Razonable GC
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),"");
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("7")) {//**** Reporte Clinicas Estados
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),"");
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("8")) {//**** Reporte APS Estados
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),"");
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("9")) {//**** Reporte Clinicas Costo Razonable Estados
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),"");
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			return mapping.findForward("input");
			
		}

	

}
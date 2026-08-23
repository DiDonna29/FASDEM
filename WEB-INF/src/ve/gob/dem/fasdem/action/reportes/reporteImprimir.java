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
import ve.gob.dem.framework.reporte.ExpReporte;
import ve.gob.dem.framework.reporte.Reporte;

/**
 * @author marcenrl
 * 
 */
public class reporteImprimir extends GenericAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(reporteImprimir.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		String accion = request.getParameter("tipo");

			if (accion.equals("1")) {//**** Reporte de Facturas cargadas por Usuario
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				//incluirTraza(TR_ADM, request.getParameter("fecha"), "IMPRIMIO REPORTE DE LA FECHA", usuarioSession(request));
				request.setAttribute("reporte", reporte);
				log.info("PASO 1"+request.getParameter("ced")+request.getParameter("fecha"));
				return mapping.findForward("success");
			}
			if (accion.equals("2")) {//**** Reporte de Siniestros cargados por Usuario
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				log.info("PASO 2");
				incluirTraza(TR_ADMRECEPCIONFACT_IMPRIMIR_CARGA, request.getParameter("fecha"), "IMPRIMIO REPORTE DE LA FECHA", usuarioSession(request));
				return mapping.findForward("success");
			}
			if (accion.equals("3")) {//**** Reporte Estadistica general
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("mes"), getServlet().getServletContext().getRealPath("/"),request.getParameter("tipotramite"));
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("4")) {//**** Reporte Clinicas GC
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("5")) {//**** Reporte APS GC
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("6")) {//**** Reporte Clinicas Costo Razonable GC
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("7")) {//**** Reporte Clinicas Estados
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("8")) {//**** Reporte APS Estados
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("9")) {//**** Reporte Clinicas Costo Razonable Estados
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("10")) {//**** Reporte Relacion de liquidacion
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("anio"), request.getParameter("ced"), request.getParameter("fecha"), getServlet().getServletContext().getRealPath("/"),usuarioSession(request).getNombre()+" "+usuarioSession(request).getApellido());
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			if (accion.equals("11")) {//**** Reporte Estadística
				Reporte reporte =ExpReporte.pintaReporte(Integer.valueOf(request.getParameter("tipo")), request.getParameter("desde"), request.getParameter("hasta"), getServlet().getServletContext().getRealPath("/"));
				request.setAttribute("reporte", reporte);
				return mapping.findForward("success");
			}
			return mapping.findForward("input");
		}
}
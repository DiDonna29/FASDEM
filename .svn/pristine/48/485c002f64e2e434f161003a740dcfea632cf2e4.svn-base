/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.reportes;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoEmpleado;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class ReportesRetencion extends GenericAction {


    static protected Logger log = Logger.getLogger(ReportesRetencion.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {




    	
    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
    	boolean valido = true;
    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	

    	String accion = request.getParameter("accionPago");
    	log.info("ACCION " + accion);

       
       if (accion==null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   
    	   request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
    	   request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
           return mapping.findForward("H1");
    	   
    	}else{
    		
    		if ("procesado".equals(request.getParameter("procesar"))) {
    	    	/////////////////////////////////////////////////////////
    	    		if (valido == false) {

    	    			am.add(ALERT_AVISOS, new ActionMessage("env.requerido"));
    					saveMessages(request, am);
    					return mapping.findForward(FWD_INPUT);
    				}
    	    		String periodo = request.getParameter("periodo");
    				String mes = request.getParameter("mes");
    				String idProveedor = request.getParameter("idProveedor");
    				String tipoProveedor = request.getParameter("tipoProveedor");
    	    		
    				if (periodo == null) {
    					periodo = "";
    				}
    				if (mes == null || "-1".equals(mes)) {
    					am.add(ALERT_AVISOS, new ActionMessage("env.mes.requerido"));
    					saveMessages(request, am);
    					request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
    			    	request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
    					return mapping.findForward("H1");
    				}
    				
    				if ("".equals(mes) && "".equals(periodo)) {
    					am.add(ALERT_AVISOS, new ActionMessage("env.mes.requerido"));
    					saveMessages(request, am);
    					request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
    			    	request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
    					return mapping.findForward("H1");
    				} else {
    				
    				}
    	    	/////////////////////////////////////////////////////////
    	    	}
		
    		
			if(accion.equals("1")){ //// 
				
				request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
		    	request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
		        log.info("periodo  " + request.getParameter("periodo"));
		        log.info("proveedor  " + request.getParameter("idProveedor"));
		    	request.setAttribute("cli_select", request.getParameter("idProveedor"));
		    	request.setAttribute("mes", request.getParameter("mes"));
		    	request.setAttribute("totales", request.getParameter("totales"));
		    	request.setAttribute("per_select", request.getParameter("periodo"));
		    	Clinica p = ExpClinica.BuscarPorid(Integer.parseInt(request.getParameter("idProveedor")));
		    	request.setAttribute("clinica_seleccionada", p);
		    	
		    	
		    	
		    	
		    	
		    	
		    	return mapping.findForward("H1");

			}

			 return mapping.findForward("H1");
			  
		}
       

       
       

    }
    
}

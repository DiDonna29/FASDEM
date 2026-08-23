/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.bandeja;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.exp.ExpUsuarioEstadistica;
import ve.gob.dem.framework.recursos.Constantes;


/**
 * @author marcenrl
 * 
 */
public class ConsultaEstadisticasEmergencia extends Action {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(ConsultaEstadisticasEmergencia.class);

    @SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	

	 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	 ArrayList lista;
	 String fch ="";
	 String fch2 ="";
	 String analist ="";
	 
if(request.getParameter("accion")!=null){	
	
	log.info("pasa aqui acc0 " + request.getParameter("accion"));
	
	 if(request.getParameter("accion").equals("1")){
		 
		 log.info("pasa aqui acc1 " + request.getParameter("accion"));
	    	
    	 try {
	
			   fch = request.getParameter("fecha");
			   fch2 = request.getParameter("fecha2");
			   analist = request.getParameter("analista");
			    
			   lista = ExpSiniestroBandeja.BuscarListaAtendidosyAtencion(Constantes.IdentificadorEmergencia, analist,fch,fch2);
				
				} catch (Exception e) {
					
					log.info("ERROR " + e);
					request.setAttribute("f_select",fch);
					request.setAttribute("f_select2",fch2);
				    return mapping.findForward("H2");
				}
			
				  
				    request.setAttribute("analist",analist);
					request.setAttribute("f_select",fch);
					request.setAttribute("f_select2",fch2);
					request.setAttribute("lista",lista);
					return mapping.findForward("H2");

	}
}	 

log.info("pasa aqui acc2 " + request.getParameter("accion"));
		 
		 try {
				
			 
			 
			   if(request.getParameter("fecha")==null){
				fch = formato.format(new Date());
			   }else{
				fch = request.getParameter("fecha");
			    }
			   
			   
			   if(request.getParameter("fecha2")==null){
					fch2 = formato.format(new Date());
			   }else{
					fch2 = request.getParameter("fecha2");
			   }

			   lista = ExpUsuarioEstadistica.buscarListaPorTipoFechaEstadistica(Constantes.SolicitudAtendida ,Constantes.IdentificadorEmergencia, fch,fch2);
				
			   
				} catch (Exception e) {
					
					log.info("ERROR " + e);
					request.setAttribute("f_select",fch);
					request.setAttribute("f_select2",fch2);
				    return mapping.findForward("H1");
				}
			
					request.setAttribute("f_select",fch);
					request.setAttribute("f_select2",fch2);
					request.setAttribute("lista",lista);
					return mapping.findForward("H1");
	 
		 
	
			
	
	
	
    }
}

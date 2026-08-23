package ve.gob.dem.fasdem.action.administrador.cobertura;




import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpCobertura;
import ve.gob.dem.fasdem.exp.administrador.ExpPoliza;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class adminCobertura extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(adminCobertura.class);

    @SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	

    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("accionProveedor");
    	
       
       if (accion==null || "".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   
    	   
    	   //request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
           request.setAttribute("resp", "paso0");
           request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
           try{
           request.setAttribute("listaCobertura",ExpCobertura.BuscarListaPoliza(ExpPoliza.buscarActiva().getId()));
           }catch (Exception e){
        	   request.setAttribute("listaCobertura",new ArrayList());
           }
           request.setAttribute("poliza",String.valueOf(ExpPoliza.buscarActiva().getId()));
    	   return mapping.findForward("input");
    	   
    	   
    	}else{
    		
            request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
			
			if ("1".equals(accion)){// creo cobertura
				request.setAttribute("poliza",request.getParameter("poliza"));
		        try{   
					request.setAttribute("listaCobertura",ExpCobertura.BuscarListaPoliza(Integer.valueOf(request.getParameter("poliza"))));
			        }catch(PersonalNotFoundException e){
			        	ArrayList list = new ArrayList(0); 
						request.setAttribute("listaCobertura",list);		        	
			        }
			    request.setAttribute("ejecuta","1");
				return mapping.findForward("input");
			}	

			
			if ("2".equals(accion)){// modifico cobertura
	    		   Cobertura cobertura = new Cobertura();
	    		   cobertura.setId(Integer.valueOf(request.getParameter("id_cobertura")));
	    		   cobertura.setIsActivo(Boolean.valueOf(request.getParameter("activar")));
				ExpCobertura.modificarEstatus(cobertura);
				incluirTraza(TR_ADMCOBERTURA_CAMBIO_ESTATUS, request.getParameter("id_cobertura"),"ID_COVERTURA A ESTATUS ACTIVO:"+Boolean.valueOf(request.getParameter("activar")), usuarioSession(request));

				request.setAttribute("poliza",request.getParameter("poliza"));
		        try{   
					request.setAttribute("listaCobertura",ExpCobertura.BuscarListaPoliza(Integer.valueOf(request.getParameter("poliza"))));
			        }catch(PersonalNotFoundException e){
			        	ArrayList list = new ArrayList(0); 
						request.setAttribute("listaCobertura",list);		        	
			        }
			    request.setAttribute("ejecuta","1");
				
				return mapping.findForward("input");		
    		}
    		
			if ("3".equals(accion) && !request.getParameter("poliza").equals("-1")){// busco coberturas
				request.setAttribute("poliza",request.getParameter("poliza"));
		        try{   
				request.setAttribute("listaCobertura",ExpCobertura.BuscarListaPoliza(Integer.valueOf(request.getParameter("poliza"))));
		        }catch(PersonalNotFoundException e){
		        	ArrayList list = new ArrayList(0); 
					request.setAttribute("listaCobertura",list);		        	
		        }
				return mapping.findForward("input");
			}	

		
			
    		
			
				
            	request.setAttribute("resp", "paso"+accion +""+ request.getParameter("busca"));
				return mapping.findForward("input");
		}
       

       
       

    }
    
}
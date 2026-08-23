package ve.gob.dem.fasdem.action.administrador.proveedores;

 


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpEstado;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramite;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramitePortal;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class adminProveedor extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(adminProveedor.class);

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
    	
    	log.info("ACCION " + accion);
			log.info("ESTA PRUEBA JC:");
			
			if("1".equals(request.getParameter("accionProveedor"))|| request.getParameter("accionProveedor")==null){
				request.setAttribute("selec1", "Checked");
				request.setAttribute("selec2", "");
			}else{
				request.setAttribute("selec2", "Checked");
				request.setAttribute("selec1", "");				
			} 
       
       if (accion==null || "".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   
    	   
    	   //request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
           request.setAttribute("resp", "paso0");
    	   return mapping.findForward("input");
    	   
    	   
    	}else{
			request.setAttribute("busca", request.getParameter("busca"));
			ArrayList listVacia = new ArrayList(0);
			 
			if (request.getParameter("crea")!=null && !"".equals(request.getParameter("crea"))){// BUSCO PROVEEDOR POR RIF
				request.setAttribute("listaEstado",ExpEstado.ListarBuscar());
				request.setAttribute("estado","");
				request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
				request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
				request.setAttribute("listaTipoTramitePortal", ExpTipoTramitePortal.BuscarLista());

				request.setAttribute("tipoProveedor","");
				if("1".equals(request.getParameter("accionProveedor"))){
					request.setAttribute("rif",request.getParameter("busca"));
					return mapping.findForward("success");
				}else{
					request.setAttribute("descripcion",request.getParameter("busca"));
					return mapping.findForward("success");
				}
			}			
			
			
			
			
			if ("1".equals(accion)){// BUSCO PROVEEDOR POR RIF
				try{
    			request.setAttribute("listProveedores",ExpClinica.BuscarPorRifParcial(request.getParameter("busca")));
    		
				}catch(PersonalNotFoundException e){
	    			request.setAttribute("listProveedores",listVacia);
		
				}
			}	
    		if ("2".equals(accion)){// BUSCO PROVEEDOR POR NOMBRE
    			try{
    			    			request.setAttribute("listProveedores",ExpClinica.BuscarPorNombreParcial(request.getParameter("busca")));    			

				}catch(PersonalNotFoundException e){
	    			request.setAttribute("listProveedores",listVacia);
		
				}
		
    		}
    		
		
			
    		
			
				
            	request.setAttribute("resp", "paso"+accion +""+ request.getParameter("busca"));
				return mapping.findForward("input");
		}
       

       
       

    }
    
}
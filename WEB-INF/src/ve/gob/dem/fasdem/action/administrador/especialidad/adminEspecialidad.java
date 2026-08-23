package ve.gob.dem.fasdem.action.administrador.especialidad;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpEspecialidad;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class adminEspecialidad extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(adminEspecialidad.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	

    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("funcion");
    	
//			request.setAttribute("botonera","1");
       
		
		
		
       if (accion==null || "".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   
  			request.setAttribute("listEspecialidades",ExpEspecialidad.BuscarLista());
  			request.setAttribute("botonera","1");
    	   return mapping.findForward("input");
    	   
    	   
    	}else{
    		
    		if (accion.equals("3")){
    			request.setAttribute("id_especialidad", request.getParameter("crea"));
      			request.setAttribute("botonera","2");
    			request.setAttribute("busca", ExpEspecialidad.buscarporID(Integer.valueOf(request.getParameter("crea"))).getDescripcion());
    		}
    		if (accion.equals("2")){
    			Especialidad esp = new Especialidad();
    			esp.setId(Integer.valueOf(request.getParameter("crea")));
    			esp.setDescripcion(request.getParameter("busca"));
      			request.setAttribute("botonera","1");
    			try{
    			ExpEspecialidad.modificarEspecialidad(esp);
    	    	  incluirTraza( TR_ADMESPECIALIDAD_MODIFICAR, request.getParameter("crea"),"ID DE LA ESPECIALIDAD A LA CUAL SE LE MODIFICO DESPCRIP:"+request.getParameter("busca"), usuarioSession(request));
    			
    			}catch(Exception e){
    				log.info(e);
    			}   			
    		}
    		if (accion.equals("1")){
       			Especialidad esp = new Especialidad();
    			esp.setDescripcion(request.getParameter("busca"));
      			request.setAttribute("botonera","1");
    			try{
    			ExpEspecialidad.crearEspecialidad(esp);

  	    	  	incluirTraza( TR_ADMESPECIALIDAD_CREAR, String.valueOf(ExpEspecialidad.buscarPorDescripcion(request.getParameter("busca")).getId()),"ID DE LA ESPECIALIDAD QUE SE CREO", usuarioSession(request));
    			}catch(Exception e){
    				log.info(e);
    			}   			
    		}
    		request.setAttribute("listEspecialidades",ExpEspecialidad.BuscarLista());
            	request.setAttribute("resp", "paso"+accion +""+ request.getParameter("busca"));
				return mapping.findForward("input");
		}
       

       
       

    }
    
}
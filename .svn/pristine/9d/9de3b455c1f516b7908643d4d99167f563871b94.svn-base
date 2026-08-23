package ve.gob.dem.fasdem.action.administrador.causaIngreso;

 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.PatologiaOrganoTratamiento;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpCausaIngreso;
import ve.gob.dem.fasdem.exp.administrador.ExpEspecialidad;
import ve.gob.dem.fasdem.exp.administrador.ExpOrgano;
import ve.gob.dem.fasdem.exp.administrador.ExpPatologia;
import ve.gob.dem.fasdem.exp.administrador.ExpTratamiento;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class adminCausaIngreso extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(adminCausaIngreso.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
 
    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("busca");
    	
    	log.info("cuenta " + accion);
			log.info("ESTA PRUEBA JC:");
	    	   request.setAttribute("listaEspecialidad",ExpEspecialidad.BuscarLista());
	    	   request.setAttribute("listaOrgano",ExpOrgano.BuscarLista());
	    	   request.setAttribute("listaPatologia",ExpPatologia.BuscarLista());
	    	   request.setAttribute("listaTratamiento",ExpTratamiento.BuscarLista());
	    	   request.setAttribute("funcion","1");
	    	   if(request.getParameter("especialidad")!=null && request.getParameter("organo")!=null && request.getParameter("patologia")!=null && request.getParameter("tratamiento")!=null){
					

	    		   if(request.getParameter("funcion").equals("2")){
					if(!ExpCausaIngreso.verificarPorEspOrgPatTrat(Integer.valueOf(request.getParameter("especialidad")),Integer.valueOf(request.getParameter("organo")),Integer.valueOf(request.getParameter("patologia")),Integer.valueOf(request.getParameter("tratamiento")))){
						ExpCausaIngreso.crearCausaIngreso(Integer.valueOf(request.getParameter("especialidad")),Integer.valueOf(request.getParameter("organo")),Integer.valueOf(request.getParameter("patologia")),Integer.valueOf(request.getParameter("tratamiento")));
						PatologiaOrganoTratamiento causa = (PatologiaOrganoTratamiento)ExpCausaIngreso.buscarPorEspOrgPatTrat(Integer.valueOf(request.getParameter("especialidad")),Integer.valueOf(request.getParameter("organo")),Integer.valueOf(request.getParameter("patologia")),Integer.valueOf(request.getParameter("tratamiento"))).get(0);
						incluirTraza(TR_ADMCAUSAINGRESO_CREAR,String.valueOf(causa.getId()) ,"ID_CAUSA_INGRESO AL QUE SE CREO", usuarioSession(request));
						request.setAttribute("mensaje", "La causa de ingreso fue creada con exito.");
					}	
				}
				
				if(request.getParameter("funcion").equals("3")){
						ExpCausaIngreso.modificarCausaIngreso(Integer.valueOf(request.getParameter("crea")));
						incluirTraza(TR_ADMCAUSAINGRESO__ESTATUS, request.getParameter("crea"),"ID_CAUSA_INGRESO AL QUE SE CAMBIO ESTATUS", usuarioSession(request));
						request.setAttribute("mensaje", "La causa de ingreso fue modificada con exito.");
						
				}
					  
				   request.setAttribute("especialidad",request.getParameter("especialidad"));
		    	   request.setAttribute("organo",request.getParameter("organo"));
		    	   request.setAttribute("patologia",request.getParameter("patologia"));
		    	   request.setAttribute("tratamiento",request.getParameter("tratamiento"));
		    	   if(request.getParameter("busca").equals("1")){   
		    		   request.setAttribute("listaCausas", ExpCausaIngreso.buscarPorEspOrgPatTrat(Integer.valueOf(request.getParameter("especialidad")),Integer.valueOf(request.getParameter("organo")),Integer.valueOf(request.getParameter("patologia")),Integer.valueOf(request.getParameter("tratamiento"))));	    		   
		    	   }	    		   
	    	   }
				
	    	   return mapping.findForward("input");

  
    }
    
}
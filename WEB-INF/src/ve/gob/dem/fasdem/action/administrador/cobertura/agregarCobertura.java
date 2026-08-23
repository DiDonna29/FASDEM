package ve.gob.dem.fasdem.action.administrador.cobertura;

//******/security/administradores/agregarCobertura
//******ve.gob.dem.fasdem.action.administrador.cobertura.agregarCobertura
//input 

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
import ve.gob.dem.fasdem.exp.administrador.ExpTipoCobertura;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramite;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class agregarCobertura extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(agregarCobertura.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	

    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("accionProveedor");
    	
    	log.info("ESTA PRUEBA JC: " + accion);
	//		log.info("ESTA PRUEBA JC:");
			
        request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
		request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
		request.setAttribute("listaTipoCobertura", ExpTipoCobertura.BuscarLista());
		request.setAttribute("poliza",request.getParameter("poliza"));
       if (accion==null || "".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   

           request.setAttribute("resp", "paso0");
    	   return mapping.findForward("input");
    	   
    	   
    	}else{  
    		
    		if(!ExpCobertura.existeCobertura(request.getParameter("tipoCobertura"),request.getParameter("poliza"))){
 			   String [] listTipoTra = request.getParameterValues("listTipoTra");
 			   if(request.getParameterValues("listTipoTra")==null){
 			        request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
 					request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
 					request.setAttribute("listaTipoCobertura", ExpTipoCobertura.BuscarLista());

 				   request.setAttribute("poliza",request.getParameter("poliza"));
 				  request.setAttribute("monto",request.getParameter("monto"));
 				  request.setAttribute("activo",request.getParameter("activo"));
 				  request.setAttribute("porPatologia",request.getParameter("porPatologia"));
 				  request.setAttribute("tipoCobertura",request.getParameter("tipoCobertura"));
 				  request.setAttribute("mensaje","Favor seleccionar al menos un tipo de tramite al cual asociar esta cobertura.");
 				  return mapping.findForward("input");
 			   }
    		   Cobertura cobertura = new Cobertura();
    		   cobertura.setMonto(Double.parseDouble(request.getParameter("monto")));
    		   cobertura.setIsActivo(Boolean.valueOf(request.getParameter("activo")));
    		   cobertura.setPorPatologia(Boolean.valueOf(request.getParameter("porPatologia")));
    		   cobertura.setPoliza(ExpPoliza.buscarporID(Integer.valueOf(request.getParameter("poliza"))));
    		   cobertura.setTipoCobertura(ExpTipoCobertura.buscarPorId(Integer.valueOf(request.getParameter("tipoCobertura"))));
    		  
    		   ExpCobertura.creaCobertura(cobertura);
    		   Cobertura cob = ExpCobertura.buscarporPolizaTipoCobertura(Integer.valueOf(request.getParameter("poliza")), Integer.valueOf(request.getParameter("tipoCobertura")));
			   incluirTraza(TR_ADMCOBERTURA_CREAR, String.valueOf(cob.getId()),"ID_COVERTURA CREADA", usuarioSession(request));
			   String listra="";
			   for (int j=0;j!=listTipoTra.length;j++){
				   if(j==0){
				   listra = listTipoTra[j];
				   }else{
					   listra = listra +","+ listTipoTra[j];					   
				   }
				   
			   }
			 
    		   //ExpCobertura.buscarporPolizaTipoCobertura(cobertura.getPoliza().getId(),cobertura.getTipoCobertura().getId());
    		   ExpCobertura.crearCoberturaTipoProvCre(ExpCobertura.buscarporPolizaTipoCobertura(cobertura.getPoliza().getId(),cobertura.getTipoCobertura().getId()).getId(),listTipoTra);

			   incluirTraza(TR_ADMCOBERTURA_DESASOCIAR_DE_TIPOTRAMITE, String.valueOf(cob.getId()),"ID_TIPO_TAMITES QUE ASOCIO:"+listra, usuarioSession(request));
			   request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
               request.setAttribute("listaCobertura",ExpCobertura.BuscarListaPoliza(Integer.valueOf(request.getParameter("poliza"))));
               request.setAttribute("poliza",request.getParameter("poliza"));
    		   
    		   request.setAttribute("mensaje","La creación se realizo con exito.");
		  
				return mapping.findForward("success"); 
    		}else{
			        request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
 					request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
 					request.setAttribute("listaTipoCobertura", ExpTipoCobertura.BuscarLista());


    			request.setAttribute("poliza",request.getParameter("poliza"));
 				  request.setAttribute("monto",request.getParameter("monto"));
 				  request.setAttribute("activo",request.getParameter("activo"));
 				  request.setAttribute("porPatologia",request.getParameter("porPatologia"));
 				  request.setAttribute("tipoCobertura",request.getParameter("tipoCobertura"));
 				  request.setAttribute("mensaje","Ya existe una Cobertura de este Tipo para esta Poliza.");

    			return mapping.findForward("input");    			
    		}
			   
		}
       

       
       

    }
    
}
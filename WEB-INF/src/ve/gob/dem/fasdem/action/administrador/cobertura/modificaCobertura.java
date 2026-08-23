package ve.gob.dem.fasdem.action.administrador.cobertura;

//******/security/administradores/modificaCobertura
//******ve.gob.dem.fasdem.action.administrador.cobertura.modificaCobertura
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
public class modificaCobertura extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(modificaCobertura.class);

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
		request.setAttribute("id_cobertura",request.getParameter("id_cobertura"));
       if ("1".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   log.info("id_cobertura: "+request.getParameter("id_cobertura"));
    	   Cobertura cob = new Cobertura();
    	   cob = ExpCobertura.buscarporCoberturaID(Integer.valueOf(request.getParameter("id_cobertura")));
			//request.setAttribute("poliza",String.valueOf(cob.getPoliza().getId()));
			request.setAttribute("monto",cob.getMonto());

			request.setAttribute("porPatologia",String.valueOf(cob.isPorPatologia()));
			request.setAttribute("listTipoTra",ExpCobertura.buscarCoberturaTipoProvID(Integer.valueOf(request.getParameter("id_cobertura"))));

    	   request.setAttribute("resp", "paso0");
    	   return mapping.findForward("input");
    	   
    	   
    	}else{  
    		
    		if(!ExpCobertura.existeCobertura(request.getParameter("tipoCobertura"),request.getParameter("poliza"))){
 			   String [] listTipoTra = request.getParameterValues("listTipoTra");
 			   if(request.getParameterValues("listTipoTra")==null){
 			        request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
 					request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
 					request.setAttribute("listaTipoCobertura", ExpTipoCobertura.BuscarLista());
 					request.setAttribute("listTipoTra",ExpCobertura.buscarCoberturaTipoProvID(Integer.valueOf(request.getParameter("id_cobertura"))));

 				   request.setAttribute("poliza",request.getParameter("poliza"));
 				  request.setAttribute("monto",request.getParameter("monto"));
 				  request.setAttribute("porPatologia",request.getParameter("porPatologia"));
 				  request.setAttribute("mensaje","Favor seleccionar al menos un tipo de tramite al cual asociar esta cobertura.");
 				  return mapping.findForward("input");
 			   } 
    		   Cobertura cobertura = new Cobertura();
    		   cobertura.setMonto(Double.parseDouble(request.getParameter("monto")));
    		   cobertura.setPorPatologia(Boolean.valueOf(request.getParameter("porPatologia")));
    		   cobertura.setId(Integer.valueOf(request.getParameter("id_cobertura")));
    		   //cobertura.setPoliza(ExpPoliza.buscarporID(Integer.valueOf(request.getParameter("poliza"))));
    		  
    		   ExpCobertura.modificarCobertura(cobertura);
				incluirTraza(TR_ADMCOBERTURA_MODIFICAR,request.getParameter("id_cobertura") ,"ID_COBERTURA QUE SE MODIFICO A VALOR MONTO:"+request.getParameter("monto")+",POR PATOLOGIA:"+request.getParameter("porPatologia"), usuarioSession(request));
				String listra="";
				   for (int j=0;j!=listTipoTra.length;j++){
					   if(j==0){
					   listra = listTipoTra[j];
					   }else{
						   listra = listra +","+ listTipoTra[j];					   
					   }
					   
				   }

				
    		   //ExpCobertura.buscarporPolizaTipoCobertura(cobertura.getPoliza().getId(),cobertura.getTipoCobertura().getId());
    		   ExpCobertura.crearCoberturaTipoProv(Integer.valueOf(request.getParameter("id_cobertura")),listTipoTra);
			   incluirTraza(TR_ADMCOBERTURA_DESASOCIAR_DE_TIPOTRAMITE, request.getParameter("id_cobertura"),"ID_TIPO_TAMITES QUE ASOCIO:"+listra, usuarioSession(request));
               request.setAttribute("listaPoliza",ExpPoliza.BuscarLista());
               //request.setAttribute("listaCobertura",ExpCobertura.BuscarListaPoliza(Integer.valueOf(request.getParameter("poliza"))));
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
 				  request.setAttribute("porPatologia",request.getParameter("porPatologia"));
 				  request.setAttribute("mensaje","Ya existe una Cobertura de este Tipo para esta Poliza.");

    			return mapping.findForward("input");    			
    		}
			   
		}
       

       
       

    }
    
}
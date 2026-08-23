package ve.gob.dem.fasdem.action.administrador.proveedores;

 


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpCiudad;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpEstado;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramite;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramitePortal;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class agregarProveedor extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(agregarProveedor.class);

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
    	
    	log.info("ESTA PRUEBA JC: " + accion);
	//		log.info("ESTA PRUEBA JC:");
			
		request.setAttribute("listaEstado",ExpEstado.ListarBuscar());
		request.setAttribute("estado","");
		request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
		request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
		request.setAttribute("listaTipoTramitePortal", ExpTipoTramitePortal.BuscarLista());
		ArrayList ciu = new ArrayList(0);
		request.setAttribute("listaCiudad",ciu);
		if ("1".equals(request.getParameter("buscaCiudad"))){

			request.setAttribute("listaCiudad",ExpCiudad.ListarBuscarporEstado(Integer.valueOf(request.getParameter("estado"))));
			//request.setAttribute("listaCiudad",ciu);
			request.setAttribute("rif",request.getParameter("rif"));
				  request.setAttribute("descripcion",request.getParameter("descripcion"));
				  request.setAttribute("direccion",request.getParameter("direccion"));
				  request.setAttribute("telefono",request.getParameter("telefono"));
				  request.setAttribute("contacto",request.getParameter("contacto"));
				  request.setAttribute("estado",request.getParameter("estado"));
				  request.setAttribute("tipoProveedor",request.getParameter("tipoProveedor"));
				  
				  request.setAttribute("ciudad","-1"); 
				  request.setAttribute("razonable",null);
			  
				  request.setAttribute("servicio",request.getParameter("servicio"));
				  return mapping.findForward("input");
		}
		
		
		
		
		
		
		
       if (accion==null || "".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   

           request.setAttribute("resp", "paso0");
    	   return mapping.findForward("input");
    	   
    	   
    	}else{  
    		request.setAttribute("listaTipoTramitePortal", ExpTipoTramitePortal.BuscarLista());

    		//log.info(ExpClinica.BuscarPorRif(request.getParameter("rif")));
    		if(!ExpClinica.ExisteRif(request.getParameter("rif"))){
 			   String [] listTipoTra = request.getParameterValues("listTipoTra");
 			  String [] listTipoTraPortal = request.getParameterValues("listTipoTraPortal");
			   if(request.getParameterValues("listTipoTra")==null){
 				  request.setAttribute("rif",request.getParameter("rif"));
 				  request.setAttribute("descripcion",request.getParameter("descripcion"));
 				  request.setAttribute("direccion",request.getParameter("direccion"));
 				  request.setAttribute("telefono",request.getParameter("telefono"));
 				  request.setAttribute("contacto",request.getParameter("contacto"));
 				  request.setAttribute("estado",request.getParameter("estado"));
 				  request.setAttribute("ciudad",request.getParameter("ciudad"));
 				  request.setAttribute("servicio",request.getParameter("servicio"));
				  request.setAttribute("tipoProveedor",request.getParameter("tipoProveedor"));
				  request.setAttribute("mensaje","Favor seleccionar al menos un tipo de proveedor.");
 				  return mapping.findForward("input");
 			   }
 			   					  
 			   
 			   
    		   Clinica prov = new Clinica();
			   prov.setRif(request.getParameter("rif"));
			   prov.setNombre(request.getParameter("descripcion"));
			   prov.setDireccion(request.getParameter("direccion"));
			   prov.setTelefono(request.getParameter("telefono"));
			   prov.setContacto(request.getParameter("contacto"));
			   prov.setId_estado(Integer.valueOf(request.getParameter("estado")));
			   prov.setTipoProveedor(ExpTipoProveedor.buscarporID(Integer.valueOf(request.getParameter("tipoProveedor"))));
			   prov.setRazonable(Boolean.valueOf(request.getParameter("activo")));
			   prov.setServicio(request.getParameter("servicio"));
			   prov.setId_ciudad(Integer.valueOf(request.getParameter("ciudad")));
			   int id_pro = ExpClinica.crearProveedor(prov);
			   log.info("error111:"+id_pro);
				incluirTraza(TR_ADMPROVEEDOR_CREAR, String.valueOf(id_pro),"ID_PROVEEDOR CREADO", usuarioSession(request));


			   ExpClinica.crearProveedorTipoProvCre(id_pro, listTipoTra);
				String listra="";
				   for (int j=0;j!=listTipoTra.length;j++){
					   if(j==0){
					   listra = listTipoTra[j];
					   }else{
						   listra = listra +","+ listTipoTra[j];					   
					   }
					   
				   }
		
			   incluirTraza(TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITE, String.valueOf(id_pro),"ASOCIO LOS SERVICIOS PORTAL ID_SERVICIOS="+listTipoTra, usuarioSession(request));

			   if(request.getParameterValues("listTipoTraPortal")!=null){
					String listraPort="";
					   for (int j=0;j!=listTipoTraPortal.length;j++){
						   if(j==0){
							   listraPort = listTipoTraPortal[j];
						   }else{
							   listraPort = listraPort +","+ listTipoTraPortal[j];					   
						   }
						   
					   }

			   ExpClinica.crearTipoTramitePortal(id_pro, listTipoTraPortal);
				incluirTraza(TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITEPORTAL, String.valueOf(id_pro),"ASOCIO LOS SERVICIOS PORTAL ID_SERVICIOS="+listraPort, usuarioSession(request));
			   }
			   request.setAttribute("selec1", "Checked");
					request.setAttribute("selec2", "");

			   request.setAttribute("mensaje","La creación se realizo con exito.");
		 
				return mapping.findForward("success");
    		}else{
 			   log.info("ESTA PRUEBA JC: ya existe");
    			return mapping.findForward("input");    			
    		}
			   
		}
       

       
       

    }
    
}
package ve.gob.dem.fasdem.action.administrador.cobertura;

 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpEstado;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramite;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class modificarCobertura extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(modificarCobertura.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	

    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("modifica");
    	 
    	log.info("MODIFICA JC: " + request.getParameter("modifica"));
	//		log.info("ESTA PRUEBA JC:");
        if (accion==null || "".equals(accion)) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{

				Clinica proveedor = ExpClinica.BuscarPorid(Integer.valueOf(request.getParameter("crea")));
				
				request.setAttribute("rif",proveedor.getRif());
				request.setAttribute("descripcion",proveedor.getNombre());
				request.setAttribute("direccion",proveedor.getDireccion());
				request.setAttribute("telefono",proveedor.getTelefono());
				request.setAttribute("contacto",proveedor.getContacto());
				request.setAttribute("estado",proveedor.getId_estado());
				request.setAttribute("activo",proveedor.getIsActivo());
				request.setAttribute("id_proveedor",proveedor.getId());
				request.setAttribute("tipoProveedor",proveedor.getTipoProveedor().getId());
				request.setAttribute("listaTipoTramite",ExpTipoTramite.BuscarLista());
				request.setAttribute("listTipoTra",ExpClinica.buscarProvTipoProvID(proveedor.getId()));
				request.setAttribute("razonable",proveedor.getRazonable());
				request.setAttribute("tipoProveedor",proveedor.getTipoProveedor().getId());
				
    	
		request.setAttribute("listaEstado",ExpEstado.ListarBuscar());
		request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
		return mapping.findForward("input");
		}
		if (accion.equals("2")) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
			   if(request.getParameterValues("listTipoTra")==null){
					Clinica proveedor = ExpClinica.BuscarPorid(Integer.valueOf(request.getParameter("id_proveedor")));					
					request.setAttribute("rif",proveedor.getRif());
					request.setAttribute("descripcion",proveedor.getNombre());
					request.setAttribute("direccion",proveedor.getDireccion());
					request.setAttribute("telefono",proveedor.getTelefono());
					request.setAttribute("contacto",proveedor.getContacto());
					request.setAttribute("estado",proveedor.getId_estado());
					request.setAttribute("activo",proveedor.getIsActivo());
					request.setAttribute("listaTipoTramite",ExpClinica.buscarProvTipoProvID(proveedor.getId()));
					request.setAttribute("listaEstado",ExpEstado.ListarBuscar());
					request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
					request.setAttribute("razonable",proveedor.getRazonable());
					request.setAttribute("mensaje","Favor seleccionar al menos un tipo de tramite.");
					return mapping.findForward("input");

			   }
			   
    		   Clinica prov = new Clinica();
    		   prov.setRif(request.getParameter("rif"));
			   prov.setNombre(request.getParameter("descripcion"));
			   prov.setDireccion(request.getParameter("direccion"));
			   prov.setTelefono(request.getParameter("telefono"));
			   prov.setContacto(request.getParameter("contacto"));
			   prov.setIsActivo(Boolean.valueOf(request.getParameter("activo")));
			   prov.setId_estado(Integer.valueOf(request.getParameter("estado")));
			   prov.setTipoProveedor(ExpTipoProveedor.buscarporID(Integer.valueOf(request.getParameter("tipoProveedor"))));
			   prov.setRazonable(Boolean.valueOf(request.getParameter("razonable")));
			   log.info("paso a modificar");
 			   String [] listTipoTra = request.getParameterValues("listTipoTra"); 
 			  prov.setId(Integer.valueOf(request.getParameter("id_proveedor")));
 			   ExpClinica.modificarProveedor(prov);
			   ExpClinica.crearProveedorTipoProv(Integer.valueOf(request.getParameter("id_proveedor")), listTipoTra);
					request.setAttribute("selec1", "Checked");
					request.setAttribute("selec2", "");
			
			   request.setAttribute("mensaje","La actualización se realizo con exito.");

							   
				return mapping.findForward("success");
			   
		}
       
		return mapping.findForward("input");
       
       

    }
    
}
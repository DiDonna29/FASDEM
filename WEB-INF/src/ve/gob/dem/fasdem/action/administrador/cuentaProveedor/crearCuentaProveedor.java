package ve.gob.dem.fasdem.action.administrador.cuentaProveedor;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpBanco;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class crearCuentaProveedor extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(crearCuentaProveedor.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	

    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("funcion");
    	
    	log.info("cuenta " + accion);
			log.info("ESTA PRUEBA JC:");
			
      if ("2".equals(accion)){ // INGRESO A LA PAGINA POR PRIMERA VEZ
   		
    	  Cuenta cuenta = new Cuenta();
    	  cuenta.setCodBanco(request.getParameter("banco"));
    	  cuenta.setCuenta(request.getParameter("numeroCuenta"));
    	  cuenta.setTipoCuenta(request.getParameter("tipoCuenta"));
    	  int id = Integer.valueOf(request.getParameter("crea"));
    	  ExpCuenta.crearCuentaProveedor(cuenta, id);
    	  incluirTraza(TR_ADMCUENTAPROVEDDOR_CREAR_CUENTAPROVEEDOR, request.getParameter("crea"),"ID DEL PROVEEDOR AL QUE SE LE ASOCIO CUENTA NUMERO:"+request.getParameter("numeroCuenta")+", BANCO:"+ExpBanco.BuscarPorId(request.getParameter("banco")).getDescripcion()+", TIPO CUENTA:"+request.getParameter("tipoCuenta"), usuarioSession(request));
    	  request.setAttribute("mensaje", "Se registro con exito la Cuenta del Proveedor");
    	  request.setAttribute("selec1", "Checked");
    	  request.setAttribute("selec2", "");
    	  return mapping.findForward("input");
    	   
    	    
    	}else{
      	  Cuenta cuenta = new Cuenta();
    	  cuenta.setCodBanco(request.getParameter("banco"));
    	  cuenta.setCuenta(request.getParameter("numeroCuenta"));
    	  cuenta.setTipoCuenta(request.getParameter("tipoCuenta"));
    	  int id = Integer.valueOf(request.getParameter("crea"));
    	  ExpCuenta.modificarCuentaProveedor(ExpCuenta.buscarCuentaProveedor(Integer.valueOf(request.getParameter("crea"))).getId());
    	  incluirTraza(TR_ADMCUENTAPROVEDDOR_MODIFICAR_CUENTAPROVEEDOR, request.getParameter("crea"),"ID DEL PROVEEDOR AL QUE SE LE INACTIVO LA CUENTA PROVEEDOR CON ID:"+ExpCuenta.buscarIdProveedor((Integer.valueOf(request.getParameter("crea")))), usuarioSession(request));
    	  ExpCuenta.crearCuentaProveedor(cuenta, id);
    	  incluirTraza( TR_ADMCUENTAPROVEDDOR_CREAR_CUENTAPROVEEDOR, request.getParameter("crea"),"ID DEL PROVEEDOR AL QUE SE LE ASOCIO CUENTA NUMERO:"+request.getParameter("numeroCuenta")+", BANCO:"+ExpBanco.BuscarPorId(request.getParameter("banco")).getDescripcion()+", TIPO CUENTA:"+request.getParameter("tipoCuenta"), usuarioSession(request));
    	  request.setAttribute("selec1", "Checked");
    	  request.setAttribute("selec2", "");
    	  request.setAttribute("mensaje", "Se registro con exito la Cuenta del Proveedor");
    	  return mapping.findForward("input");
		}
       

       
      

    }
    
}
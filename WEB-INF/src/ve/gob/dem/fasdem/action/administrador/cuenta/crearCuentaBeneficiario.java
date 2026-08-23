package ve.gob.dem.fasdem.action.administrador.cuenta;

 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpBanco;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class crearCuentaBeneficiario extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(crearCuentaBeneficiario.class);

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
   		
    	  CuentaBenef cuenta = new CuentaBenef();
    	  cuenta.setIdBanco(request.getParameter("banco"));
    	  cuenta.setNumero_cuenta(request.getParameter("numeroCuenta"));
    	  cuenta.setTipo_cuenta(request.getParameter("tipoCuenta"));
    	  String cedula = request.getParameter("crea");
    	  ExpCuenta.crearCuentaBeneficiarioN(cuenta, cedula);
    	  incluirTraza(TR_ADMCUENTANOMINA_CREAR_CUENTA_BENEBICIARIOS, cedula,"CEDULA A LA CUAL SE LE ASOCIO CUENTA NUMERO:"+request.getParameter("numeroCuenta")+", BANCO:"+ExpBanco.BuscarPorId(request.getParameter("banco")).getDescripcion()+", TIPO CUENTA:"+request.getParameter("tipoCuenta"), usuarioSession(request));
    	  request.setAttribute("mensaje", "Se registro con exito la Cuenta del Beneficiario");
    	  return mapping.findForward("input");
    	   
    	   
    	}else{
      	  CuentaBenef cuenta = new CuentaBenef();
    	  cuenta.setIdBanco(request.getParameter("banco"));
    	  cuenta.setNumero_cuenta(request.getParameter("numeroCuenta"));
    	  cuenta.setTipo_cuenta(request.getParameter("tipoCuenta"));
    	  String cedula = request.getParameter("crea");
    	  incluirTraza(TR_ADMCUENTANOMINA_MODIFICAR_CUENTA_BENEBICIARIOS, cedula,"CEDULA A LA CUAL SE LE INACTIVO LA CUENTA CON ID:"+ExpCuenta.buscarCuentaBeneficiario(request.getParameter("crea")).getId(), usuarioSession(request));
    	  ExpCuenta.modificarCuenta(ExpCuenta.buscarCuentaBeneficiario(request.getParameter("crea")).getId());
    	  ExpCuenta.crearCuentaBeneficiarioN(cuenta, cedula);
    	  incluirTraza(TR_ADMCUENTANOMINA_CREAR_CUENTA_BENEBICIARIOS, cedula,"CEDULA A LA CUAL SE LE ASOCIO CUENTA NUMERO:"+request.getParameter("numeroCuenta")+", BANCO:"+ExpBanco.BuscarPorId(request.getParameter("banco")).getDescripcion()+", TIPO CUENTA:"+request.getParameter("tipoCuenta"), usuarioSession(request));
    	  request.setAttribute("mensaje", "Se registro con exito la Cuenta del Beneficiario");
    	  return mapping.findForward("input");
		}
       

       
      

    }
    
}
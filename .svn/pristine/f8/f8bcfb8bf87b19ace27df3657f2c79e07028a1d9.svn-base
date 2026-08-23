/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.pago;



import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.DetalleFacturaPago;
import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.ResumenPreOrdenPago;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.ExpPersona;

import ve.gob.dem.fasdem.exp.pagos.ExpDetalleFacturaPago;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoEmpleado;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.fasdem.exp.pagos.ExpUnidadTributaria;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;



/**
 * @author marcenrl
 * 
 */
public class AnulacionHojaRutaONT extends GenericAction {


    static protected Logger log = Logger.getLogger(AnulacionHojaRutaONT.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	HttpSession session = request.getSession();

    	Usuario usuario = null;
    	
    	usuario = (Usuario) session.getAttribute("usuario");
    	
    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

    	
    	String accion = request.getParameter("accionPago");
    	log.info("ACCION " + accion);

       
       if (accion==null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	 
    	   return mapping.findForward("H1");
    	   
    	}else{
		

			
			if(accion.equals("3")){ //// BUSQUEDA POR CODIGO DE PREORDEN
			
				int anio = Integer.parseInt("20"+request.getParameter("codigo1").substring(0,2));
			    ArrayList list = ExpPreOrdenPago.buscarPorCodigoPreOrdenHojaRutaAnulaONT(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"),anio);
				request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("lista",list);
		    	return mapping.findForward("H1");

			}
			

			
			if(accion.equals("5")){ //// ELIMINAR
				
				log.info("1");
			    ExpHojaRuta.DesAsociarPreOrdenHoja(request.getParameter("pre"),Integer.parseInt("20"+request.getParameter("pre").substring(0,2)),13);
			    log.info("2");
			    ExpPreOrdenPago.SacarHojaPreOrden(request.getParameter("pre"),Integer.parseInt("20"+request.getParameter("pre").substring(0,2)));
			    log.info("3");
			    ExpHojaRuta.DesAsociarPreOrdenHojaTesoreria(request.getParameter("idpre"),Integer.parseInt("20"+request.getParameter("pre").substring(0,2)),13);
			    log.info("4");
				incluirTraza(TR_PAGO_RECIBIR_EXP_DEVUELTO,
						request.getParameter("pre"),
							"Se Recibio la Preorden  " + request.getParameter("pre") + " Devuelto por tesoreria dem " , usuarioSession(request));
					
				
				
				
				return mapping.findForward("H1");
		    	
			}

			 return mapping.findForward("H1");
			  
		}
       

       
       

    }
    
}

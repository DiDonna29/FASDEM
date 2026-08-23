/**04/05/2015
 * barrjime
 */
package ve.gob.dem.fasdem.action.pago;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.DetalleFacturaPago;
import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpDetalleFacturaPago;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoEmpleado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;


public class cambioEstatus extends GenericAction {


    static protected Logger log = Logger.getLogger(cambioEstatus.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	
    	
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
			
    		
			if(accion.equals("1")){
				
			        HojaRuta list = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("codigo2")));
					request.setAttribute("lista",list);
			    	request.setAttribute("primera","no");
		    	
			}
			
			
			
			if(accion.equals("2")){ // Cambiar Status
				
				log.info("aqui" + request.getParameter("codigo2"));
				
				ExpPreOrdenPago.buscarporCodigoCambioStatus(Integer.parseInt(request.getParameter("codigo2")),Constantes.StatusPagado);

				
				HojaRuta list = ExpHojaRuta.buscarporIdStatus(Integer.parseInt(request.getParameter("codigo2")));
				request.setAttribute("lista",list);
		    	request.setAttribute("primera","no");
		    	request.setAttribute("mensaje", "Cambio de Status fue realizado con Exito");
		    	
			}
			
				return mapping.findForward("H1");
		}
       
  
       
 

    }
    
}

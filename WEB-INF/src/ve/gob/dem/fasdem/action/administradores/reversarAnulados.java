/**04/05/2015
 * barrjime
 */
package ve.gob.dem.fasdem.action.administradores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
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
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.fasdem.exp.pagos.ExpUnidadTributaria;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;


public class reversarAnulados extends GenericAction {


    static protected Logger log = Logger.getLogger(reversarAnulados.class);

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
			
			
			
			if(accion.equals("2")){ // buscar
				
				int anio=0;
					
				if (Integer.parseInt(request.getParameter("anio_busca"))==0){
					
					anio = Integer.parseInt("20" + request.getParameter("cod_sin").substring(0, 2));
					
				}else{
					
					anio =Integer.parseInt(request.getParameter("anio_busca"));
							
				} 
				
				
				ArrayList list = ExpDetalleFacturaPago.buscarListaBuscaFacPorSiniestroAnu(request.getParameter("cod_sin").replace("-", "").trim(), (!request.getParameter("cod_sin").trim().equals("") ? anio : Integer.parseInt(Utilidad.DateToString(new Date(), "YYYY"))));

				try {
					DetalleFacturaPago det = (DetalleFacturaPago) list.get(0);
					request.setAttribute("prov", det.getProveedor());
					request.setAttribute("tipselect", det.getTipoEmpleado().getId());
					request.setAttribute("tipProvSelect", request.getParameter("tipoProveedor"));
					request.setAttribute("idEstatus", det.getId_estatus());					
					String des_esta = ExpDetalleFacturaPago.buscarDescEstatus(det.getId_estatus());
					request.setAttribute("des_esta", des_esta);
				} catch (Exception e) {
				}
				
				request.setAttribute("lista", list);
				request.setAttribute("listaUnidad", ExpUnidadTributaria.buscarLista());
				request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
				request.setAttribute("primera", "no");
				request.setAttribute("anio_pre", anio);
				request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
				
				return mapping.findForward("H1");
       
			}
			
			if(accion.equals("3")){ // Cambiar Status
				
			log.info("aqui" + request.getParameter("cod1"));
			log.info("aqui2" + request.getParameter("cod2"));
			
			ExpPreOrdenPago.buscarporCodigoCambioStatusSiniestroAnu(Integer.parseInt(request.getParameter("cod1")),Integer.parseInt(Constantes.EtapaSiniestroEgresado));
			incluirTraza(TR_ADMINISTRADOR_CAMBIO_ESTATUS, request.getParameter("cod2"), "Cambio estatus anulado", usuarioSession(request));

			
			Calendar fecha = Calendar.getInstance();
			int anio = fecha.get(Calendar.YEAR);
			ArrayList list = ExpDetalleFacturaPago.buscarListaBuscaFacPorSiniestroAnu(request.getParameter("cod2"), anio);

	    	request.setAttribute("mensaje", "Cambio de Status fue realizado con Exito");
	    	try {
				DetalleFacturaPago det = (DetalleFacturaPago) list.get(0);
				request.setAttribute("prov", det.getProveedor());
				request.setAttribute("tipselect", det.getTipoEmpleado().getId());
				request.setAttribute("tipProvSelect", request.getParameter("tipoProveedor"));
				request.setAttribute("idEstatus", det.getId_estatus());
				String des_esta = ExpDetalleFacturaPago.buscarDescEstatus(det.getId_estatus());
				request.setAttribute("des_esta", des_esta);
			} catch (Exception e) {
			}
			
			request.setAttribute("lista", list);
			request.setAttribute("listaUnidad", ExpUnidadTributaria.buscarLista());
			request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
			request.setAttribute("primera", "no");
			request.setAttribute("anio_pre", anio);
			request.setAttribute("listaTipoProv", ExpTipoProveedor.BuscarLista());
			
			return mapping.findForward("H1");
			}
    	}
       
       return mapping.findForward("H1");
       
 

    }
    
}

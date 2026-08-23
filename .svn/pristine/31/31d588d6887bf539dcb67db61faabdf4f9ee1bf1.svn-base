/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.pago;

import java.text.SimpleDateFormat;
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
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoHojaRuta;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;




public class MisHojaRuta extends GenericAction {


    static protected Logger log = Logger.getLogger(MisHojaRuta.class);

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
    	SimpleDateFormat formato_ma = new SimpleDateFormat("MM/yyyy");
    	SimpleDateFormat formato_a = new SimpleDateFormat("yyyy");
    	SimpleDateFormat formato_m = new SimpleDateFormat("MM");
       
       if (accion==null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   
    	   
    	   
    	   ArrayList h = ExpHojaRuta.BuscarListaUsuario(Integer.parseInt(usuario.getCedula()),formato_ma.format(new Date()));
    	   
    	   request.setAttribute("m_actual",formato_m.format(new Date()));
    	   request.setAttribute("a_actual",formato_a.format(new Date()));
    	   request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
    	   log.info("LISTA DE TIPO DE HOJA  " + ExpTipoHojaRuta.buscarLista());
    	   request.setAttribute("lista",h);
    	   return mapping.findForward("H1");
    	   
    	}else{
		
    		
			if(accion.equals("1")){ //// BUSQUEDA DE hojas mes
				
				   String anio = request.getParameter("anio_h");
				   String mes = request.getParameter("mes");
				   Date fecha = Utilidad.StringToDate("01/" + mes + "/" +  anio, "dd/MM/yyyy");
				   log.info("RANGO DE FECHA  " +  fecha);
				   ArrayList h = ExpHojaRuta.BuscarListaUsuario(Integer.parseInt(usuario.getCedula()),formato_ma.format(fecha));
		    	   
		    	   request.setAttribute("m_actual",formato_m.format(fecha));
		    	   request.setAttribute("a_actual",formato_a.format(fecha));
		    	   request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
		    	   request.setAttribute("primera","no");
		    	   request.setAttribute("lista",h);
		    	   return mapping.findForward("H1");
		    	
			}
			
			
			if(accion.equals("2")){ ////
				String anio1 = request.getParameter("anio_h");
				String mes = request.getParameter("mes");
				Date fecha = Utilidad.StringToDate("01/" + mes + "/" +  anio1, "dd/MM/yyyy");
				request.setAttribute("m_actual",formato_m.format(fecha));
		    	request.setAttribute("a_actual",formato_a.format(fecha));   
		    	request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
				   
				String f = request.getParameter("cod"+request.getParameter("id_hoja"));
				String anio = String.valueOf(formato_a.format(Utilidad.StringToDate(f, "yyyy")));
				log.info("anio hoja  " + anio);
				ExpHojaRuta.CambiarEstatusHoja(Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(anio));
				
				incluirTraza(TR_PAGO_CAMBIAR_ESTATUS_HOJA_RUTA,
						request.getParameter("id_hoja"),
						"Se Cambio el Estatus de la Hoja de Ruta N° " + request.getParameter("id_hoja") + " a ESTATUS " +  request.getParameter("status"), usuarioSession(request));
				
				
				ArrayList h = ExpHojaRuta.BuscarListaUsuario(Integer.parseInt(usuario.getCedula()),formato_ma.format(fecha));
				 request.setAttribute("primera","no");
				request.setAttribute("lista",h);
		    	return mapping.findForward("H1");
		    	
			}
			
			if(accion.equals("3")){ //// BUSQUEDA POR CODIGO 
				
				String anio1 = request.getParameter("anio_h");
				String mes = request.getParameter("mes");
				Date fecha = Utilidad.StringToDate("01/" + mes + "/" +  anio1, "dd/MM/yyyy");
				request.setAttribute("m_actual",formato_m.format(fecha));
		    	request.setAttribute("a_actual",formato_a.format(fecha));   
		    	request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
				
				
				int anio = Integer.parseInt("20"+request.getParameter("codigo1").substring(0,2));
			    ArrayList list = ExpHojaRuta.BuscarPorNumeroUsuario(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"),anio,Integer.parseInt(usuario.getCedula()));
				request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("lista",list);
		    	return mapping.findForward("H1");
				

				
				
			}
			
			if(accion.equals("4")){ //// BUSQUEDA DE hojas mes
				
				
				String dateOut;
		     	
				int anio_hoja = Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy"));	
			    dateOut = Utilidad.DateToString(new Date(), "yyMM");
			
				
			    
				int codigo_hoja=0;
				int periodo_fiscal=Integer.parseInt(request.getParameter("periodo"));
				int tipo=Integer.parseInt(request.getParameter("tipohojar"));
			    String cod="";
				
				codigo_hoja = ExpHojaRuta.buscarCodigoNuevo(anio_hoja,dateOut)+1;
				String DIGITOS = "00000";
				cod =((DIGITOS + String.valueOf(codigo_hoja)).substring((DIGITOS + String.valueOf(codigo_hoja)).length() - DIGITOS.length()));

				
			      
				
				
				   ExpHojaRuta.CrearHojaRuta(dateOut+"-"+cod, Integer.parseInt(usuario.getCedula()), Integer.parseInt(formato_a.format(new Date())),codigo_hoja,dateOut,periodo_fiscal,tipo);
		
				   
				   incluirTraza(TR_PAGO_CREAR_HOJA_RUTA,
							dateOut+"-"+cod,
							"Creacion de Hoja de Ruta N° " +  dateOut+"-"+cod, usuarioSession(request));
				   
				   
				   ArrayList h = ExpHojaRuta.BuscarListaUsuario(Integer.parseInt(usuario.getCedula()),formato_ma.format(new Date()));
		    	   
		    	   request.setAttribute("m_actual",formato_m.format(new Date()));
		    	   request.setAttribute("a_actual",formato_a.format(new Date()));
		    	   request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
		    	   
		    	   request.setAttribute("lista",h);
		    	   return mapping.findForward("H1");
		    	
			}
		

		
			 return mapping.findForward("H1");
			  
		}
       

       
       

    }
    
}

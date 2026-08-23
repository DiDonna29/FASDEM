/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.pago;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;









import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.ExpPersona;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoHojaRuta;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;

/**
 * @author barrjime
 * 
 */
public class causado extends GenericAction {
	static protected Logger log = Logger.getLogger(causado.class);

	
	public static String remove(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	    return output;
	}//remove
	
	
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
		log.info("ACCION123 " + accion);
		SimpleDateFormat formato_ma = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat formato_a = new SimpleDateFormat("yyyy");
		SimpleDateFormat formato_m = new SimpleDateFormat("MM");
		if (accion == null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
			ArrayList h = ExpHojaRuta.BuscarListaGeneralR(formato_ma.format(new Date()));
			request.setAttribute("m_actual", formato_m.format(new Date()));
			request.setAttribute("a_actual", formato_a.format(new Date()));
			request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
			request.setAttribute("lista", h);
			return mapping.findForward("H1");
		} else {
			if (accion.equals("1")) { // // BUSQUEDA DE hojas mes
				String anio = request.getParameter("anio_h");
				String mes = request.getParameter("mes");
				Date fecha = Utilidad.StringToDate("01/" + mes + "/" + anio, "dd/MM/yyyy");
				log.info("RANGO DE FECHA  " + fecha);
				ArrayList h = ExpHojaRuta.BuscarListaGeneralR(formato_ma.format(fecha));
				request.setAttribute("m_actual", formato_m.format(fecha));
				request.setAttribute("a_actual", formato_a.format(fecha));
				request.setAttribute("primera", "no");
				request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
				request.setAttribute("lista", h);
				return mapping.findForward("H1");
			}
			if (accion.equals("2")) { // //
				String anio1 = request.getParameter("anio_h");
				String mes = request.getParameter("mes");
				Date fecha = Utilidad.StringToDate("01/" + mes + "/" + anio1, "dd/MM/yyyy");
				request.setAttribute("m_actual", formato_m.format(fecha));
				request.setAttribute("a_actual", formato_a.format(fecha));
				String f = request.getParameter("cod" + request.getParameter("id_hoja"));
				String anio = String.valueOf(formato_a.format(Utilidad.StringToDate(f, "yyyy")));
				log.info("anio hoja  " + anio);
				ExpHojaRuta.CambiarEstatusHoja(Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(anio));
				incluirTraza(TR_PAGO_CAMBIAR_ESTATUS_HOJA_RUTA, request.getParameter("id_hoja"), "Se Cambio el Estatus de la Hoja de Ruta N° " + request.getParameter("id_hoja") + " a ESTATUS " + request.getParameter("status"), usuarioSession(request));
				ArrayList h = ExpHojaRuta.BuscarListaGeneralR(formato_ma.format(fecha));
				request.setAttribute("primera", "no");
				request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
				request.setAttribute("lista", h);
				return mapping.findForward("H1");
			}
			
			if (accion.equals("4")){ //// GENERAR .TXT
				
				String realPath = getServlet().getServletContext().getRealPath("/");

			    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
			    
			    int org = Integer.parseInt(request.getParameter("causado"));
			    String tit = String.format("%08d",101349)+"."+String.format("%03d",001)+"."+String.format("%03d",10);

//				String nombreArchivo= "/usr/share/tomcat7-admin/fasdem/archivos/"+tit+".txt"; // Aqui se le asigna el nombre y la ruta de creacion Desarrollo"
				String nombreArchivo= realPath + "/archivos/"+tit+".txt"; // Aqui se le asigna el nombre y la ruta de creacion archivo plano"
				
				
				FileWriter fw = null;
				try {
				fw = new FileWriter(nombreArchivo);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter salArch = new PrintWriter(bw);
				double total=0.00;
//				int cant=0;
				
				for (int i=0;i!=list.size();i++){ // total de monto 
					PreOrdenPago carga = new PreOrdenPago();
					carga = (PreOrdenPago) list.get(i);									
					total = total + carga.getMonto();
				}
				
				salArch.printf("%1s%08d%02d%08d%04d%016.0f\n","H",101349,0,org,h.getCantidad(),total*100);; // imprimir encabezado

				for (int i=0;i!=list.size();i++){
					PreOrdenPago carga = new PreOrdenPago();
					
					carga = (PreOrdenPago) list.get(i);
					Cuenta bb = ExpCuenta.buscarCuentaBeneficiario(carga.getTitular().getCedula());
					 
					String nom =carga.getTitular().getNombres();
					String ape =carga.getTitular().getApellidos();
					String expReg = "[,;\\.:]"; 
					
					log.info("pasa aqui2" + nom.split(expReg)[0]);
					
					salArch.printf("%-33s%-35.35s%015.0f\n",bb.getCuenta()+"NV"+carga.getTitular().getCedula(),causado.remove(nom.split(expReg)[0]+ " " + ape.split(expReg)[0]).toUpperCase(),carga.getMonto()*100);					
					
					// imprimir contenido 
				}
				salArch.close(); // imprimir contenido fin
				}
				catch (IOException ex) {
					request.setAttribute("mensaje", "ERROR");
				}
				
				log.info("Genera descarga" );
				
				try{  // GENERAL DESCARGA "OJO"
			        String nomFile = ""+tit+".txt";
			        FileInputStream archivo = new FileInputStream(realPath +"/archivos/"+nomFile); /// ruta de salida
			        int longitud = archivo.available();
			        byte[] datos = new byte[longitud];
			        archivo.read(datos);
			        archivo.close();
			        response.setContentType("application/octet-stream");
			        response.setHeader("Content-Disposition","attachment;filename="+nomFile);
			        ServletOutputStream ouputStream = response.getOutputStream();
			        ouputStream.write(datos);
			        ouputStream.flush();
			        ouputStream.close();
			      }catch(IllegalStateException e){ 
			    	  e.printStackTrace(); 
			      }  
				
				
				request.setAttribute("primera", "no");
				request.setAttribute("hoja",h);
				request.setAttribute("lista", list);
				request.setAttribute("mensaje", "Archivo Plano Generado");
		    	return mapping.findForward("H2");
		    	
			}
			
			if(accion.equals("5")){ //// PAGINA ARCHIVO TXT
				
				
			    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				log.info("h.getId() " + h.getId());
				
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
			    
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);

		    	return mapping.findForward("H2");
		    	
			}
			
			if(accion.equals("6")){ //// CREACION DE LA HOJA DE PAGO
				
				
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

				log.info("pasa aki " + dateOut);
			      
				
				
				   ExpHojaRuta.CrearHojaRuta(dateOut+"-"+cod, Integer.parseInt(usuario.getCedula()), Integer.parseInt(formato_a.format(new Date())),codigo_hoja,dateOut,periodo_fiscal,tipo);
		
				   
				   incluirTraza(TR_PAGO_CREAR_HOJA_RUTA,
							dateOut+"-"+cod,
							"Creacion de Hoja de Ruta N° " +  dateOut+"-"+cod, usuarioSession(request));
				   
				   
//				   ArrayList h = ExpHojaRuta.BuscarListaUsuario(Integer.parseInt(usuario.getCedula()),formato_ma.format(new Date()));
					ArrayList h = ExpHojaRuta.BuscarListaGeneralR(formato_ma.format(new Date()));

				   
		    	   request.setAttribute("m_actual",formato_m.format(new Date()));
		    	   request.setAttribute("a_actual",formato_a.format(new Date()));
		    	   request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
		    	   
		    	   request.setAttribute("lista",h);
		    	   return mapping.findForward("H1");
		    	
			}
			
			if(accion.equals("7")){ //// ABRIR Y CERRAR OPERACION
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
				
				
//				ArrayList h = ExpHojaRuta.BuscarListaUsuario(Integer.parseInt(usuario.getCedula()),formato_ma.format(fecha));
				ArrayList h = ExpHojaRuta.BuscarListaGeneralR(formato_ma.format(fecha));

				request.setAttribute("primera","no");
				request.setAttribute("tipo_hoja",ExpTipoHojaRuta.buscarLista());
				request.setAttribute("lista",h);
		    	return mapping.findForward("H1");
		    	
			}
			
				if (accion.equals("8")){ //// GENERAR .TXT
				
				String realPath = getServlet().getServletContext().getRealPath("/");

			    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
			    
			    int org = Integer.parseInt(request.getParameter("causado"));
			    String tit = String.format("%08d",101349)+"."+String.format("%03d",001)+"."+String.format("%03d",10);

				String nombreArchivo= realPath + "/archivos/"+tit+".txt"; // Aqui se le asigna el nombre y la ruta de creacion archivo plano"
								
				FileWriter fw = null;
				try {
				fw = new FileWriter(nombreArchivo);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter salArch = new PrintWriter(bw);
				double total=0.00;
				
				for (int i=0;i!=list.size();i++){ // total de monto 
					PreOrdenPago carga = new PreOrdenPago();
					carga = (PreOrdenPago) list.get(i);									
					total = total + carga.getMonto();
				}
				
				salArch.printf("%1s%08d%02d%08d%04d%016.0f\n","H",101349,0,org,h.getCantidad(),total*100);; // imprimir encabezado

				for (int i=0;i!=list.size();i++){ 					// imprimir contenido 
					PreOrdenPago carga = new PreOrdenPago();
					
					carga = (PreOrdenPago) list.get(i);
					Cuenta bb = ExpCuenta.buscarCuentaProveedor(carga.getProveedor().getId());
					 
					String expReg = "[,;\\.:]"; 
										
					salArch.printf("%-33s%-35.35s%015.0f\n",bb.getCuenta()+carga.getProveedor().getRif(),causado.remove(carga.getProveedor().getNombre().split(expReg)[0]).toUpperCase(),carga.getMonto()*100);					
					
				}
				salArch.close(); // imprimir contenido fin
				}
				catch (IOException ex) {
					request.setAttribute("mensaje", "ERROR");
				}
				
				log.info("Genera descarga" );
				
				try{  // GENERAL DESCARGA "OJO"
			        String nomFile = ""+tit+".txt";
			        FileInputStream archivo = new FileInputStream(realPath +"/archivos/"+nomFile); /// ruta de salida
			        int longitud = archivo.available();
			        byte[] datos = new byte[longitud];
			        archivo.read(datos);
			        archivo.close();
			        response.setContentType("application/octet-stream");
			        response.setHeader("Content-Disposition","attachment;filename="+nomFile);
			        ServletOutputStream ouputStream = response.getOutputStream();
			        ouputStream.write(datos);
			        ouputStream.flush();
			        ouputStream.close();
			      }catch(IllegalStateException e){ 
			    	  e.printStackTrace(); 
			      }  
				
				
				request.setAttribute("primera", "no");
				request.setAttribute("hoja",h);
				request.setAttribute("lista", list);
				request.setAttribute("mensaje", "Archivo Plano Generado");
		    	return mapping.findForward("H2");
		    	
			}
			
			return mapping.findForward("H1");
		}
	}
}



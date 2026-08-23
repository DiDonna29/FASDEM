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
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.pagos.ExpDetalleFacturaPago;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoEmpleado;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;


public class rechazoPreOrden extends GenericAction {


    static protected Logger log = Logger.getLogger(rechazoPreOrden.class);

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
				
				    int anio = Integer.parseInt("20"+request.getParameter("codigo1").substring(0,2));
			        ArrayList list = ExpPreOrdenPago.buscarporCodigo(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"),anio );
					request.setAttribute("lista",list);
			    	request.setAttribute("primera","no");
		    	
			}
			
			
			if(accion.equals("5")){ /// ELIMINAR FACTURAS DE LA PREORDEN
				
				
			    int anio = Integer.parseInt("20"+request.getParameter("cod1").substring(0,2));
			    ArrayList list = ExpDetalleFacturaPago.buscarListaBuscaFacPorPreOrden(request.getParameter("cod1"), anio);
			    PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"), anio);
			    request.setAttribute("preorden",pre);
			    request.setAttribute("lista",list);
			    return mapping.findForward("H3");
			    
		    }
			
			
			
			
			if(accion.equals("51")){ /// PROCESO DE ELIMINACION DE FACTURA
				
				 DetallePreOrdenPago det;
			    int anio = Integer.parseInt("20"+request.getParameter("codpre").substring(0,2));
			    ExpDetalleFacturaPago.quitarFacturaPreorden(Integer.parseInt(request.getParameter("id_fact")), anio);
			    
			    incluirTraza(TR_PAGO_ELIMINAR_FACTURA_PREORDEN,
			    		request.getParameter("id_fact"),
						"Id Factura Eliminada de PreOrden", usuarioSession(request));
			    
			    
			    
			    
			    PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("codpre"), anio);
			    
			    
			    //preorden segun el tipo
			    det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("codpre"), anio);
			    
			    
			    
			   
				
				
				Double tope = pre.getUnidad_tributaria()* Constantes.CantidadUnidadesPagoTimbre ;
				int aplica = 0;
				
				if (det.getMonto_Liquidado()>tope){
					aplica=1;
				}
			
				ExpPreOrdenPago.cambiarTopePreOrden(request.getParameter("codpre"), aplica, anio);
			
			    ArrayList list = ExpDetalleFacturaPago.buscarListaBuscaFacPorPreOrden(request.getParameter("codpre"), anio);
			    request.setAttribute("preorden",pre);
			    request.setAttribute("lista",list);
			    return mapping.findForward("H3");
			    
		    }
			
			
			
			
			if(accion.equals("52")){ /// resgresar a la lista despues de la eliminacion de facturas
				
				
				int anio = Integer.parseInt("20"+request.getParameter("codpre").substring(0,2));
		        ArrayList list = ExpPreOrdenPago.buscarporCodigo(request.getParameter("codpre"),anio );
				request.setAttribute("lista",list);
		    	request.setAttribute("primera","no");
			    return mapping.findForward("H1");
			    
		    }
			
			if(accion.equals("3")){ // ir a la pagina de detalle desde la lista principal 
				
				
				int anio = Integer.parseInt("20"+request.getParameter("cod1").substring(0,2));
				
				PreOrdenPago pre=ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"),anio);
				DetallePreOrdenPago det ;
				
				
				//preorden segun el tipo
				det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("cod1"), anio);
				
				
			    
				
				request.setAttribute("preorden",pre);
				request.setAttribute("detalle",det);
				return mapping.findForward("H2");
		    	
			}
			
			
			
			if(accion.equals("2")){ // imprimir reporte desde la lista de la preorden
				
				
		    	HashMap mapa =new HashMap();
				JasperPrint jasperprint;
				String realPath = getServlet().getServletContext().getRealPath("/");
				String path;
				String rptfilename="";	
				ServletOutputStream outstream2;
				byte[] pdfasbytes;
				mapa.put("ruta", realPath + "images/");
				mapa.put("REPORT_LOCALE",  new Locale("es", "VE"));
				String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
				Connection db = null;
				
			    mapa.put("preorden", request.getParameter("pre"));
				//mapa.put("codigo", codigo);
				
				
				
			 try {	
			      
				   db =   Conexion.getConexion();
				   rptfilename ="/jasper/FASDEM/FiniquitoDePago.jasper";
				   path = realPath + rptfilename;
				   // LLENAR EL INFORME
				   jasperprint = JasperFillManager.fillReport(path, mapa, db);
				   // EXPORTAR EL INFORME A FORMATO PDF.
				   pdfasbytes = JasperExportManager.exportReportToPdf(jasperprint);
				   outstream2 = response.getOutputStream();
				   response.setContentType("application/pdf");
				   response.setContentLength(pdfasbytes.length);
				   response.setHeader("Pragma", "no-cache");
				   response.setDateHeader("Expires", 0);
				   response.setHeader("Content-disposition", "inline; filename=\"Report" + date + ".pdf\"");
				   outstream2.write(pdfasbytes);
				   
				
			} finally {
			    Conexion.closeConexion(db);
			}
		

    	
	}
			

			
			if(accion.equals("6")){ /// AGREGAR FACTURAS DE LA PREORDEN
				
				
				int anio = Integer.parseInt("20"+request.getParameter("cod1").substring(0,2));
			    PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod1"), anio);
			    request.setAttribute("preorden",pre);
			    request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
			    return mapping.findForward("H4");
	    	
		     }
			
			
			if(accion.equals("7")){ /// AGREGAR FACTURAS DE LA PREORDEN
			
				
				int anio1 = Integer.parseInt("20"+request.getParameter("codpre").substring(0,2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("codpre"), anio1);
			    request.setAttribute("preorden",pre);
				
				
				int anio = Integer.parseInt(request.getParameter("fechaInicio").substring(6, 10));
			    ArrayList list = ExpDetalleFacturaPago.buscarListaProveedorFechaSiniestro(Integer.parseInt(request.getParameter("tipoempleado")),Integer.parseInt(request.getParameter("idProveedor")), request.getParameter("fechaInicio"), request.getParameter("fechaFin"),anio);
				request.setAttribute("prov",ExpClinica.BuscarPorid(Integer.parseInt(request.getParameter("idProveedor"))));
				request.setAttribute("dselect",request.getParameter("fechaInicio"));
				request.setAttribute("hselect",request.getParameter("fechaFin"));
				request.setAttribute("lista",list);
				request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
		    	return mapping.findForward("H4");
		    	
		    	
		    	
			
			 }
			
			
			if(accion.equals("8")){ //// BUSQUEDA DE FACTURAS CODIGO DE SINIESTRO
				
				int anio1 = Integer.parseInt("20"+request.getParameter("codpre").substring(0,2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("codpre"), anio1);
			    request.setAttribute("preorden",pre);
			    int anio = Integer.parseInt("20"+request.getParameter("cod_sin").substring(0,2));
			    DetalleFacturaPago det = new DetalleFacturaPago();
			    ArrayList list;
			    
			  if(pre.getTipo_preorden()==2){
				  
				list = ExpDetalleFacturaPago.buscarListaBuscaFacPorSiniestroTitularReemb(request.getParameter("cod_sin").replace("-","").trim(),(!request.getParameter("cod_sin").trim().equals("")?Integer.parseInt("20"+request.getParameter("cod_sin").substring(0,2)):Integer.parseInt(Utilidad.DateToString(new Date(), "YYYY"))),pre.getTitular().getCedula());
				try {
					det = (DetalleFacturaPago)list.get(0);
					request.setAttribute("prov",det.getProveedor());
					request.setAttribute("tipselect",det.getTipoEmpleado().getId());
		    	} catch (Exception e) {}	
				
				  
			  }else{
				 
				list = ExpDetalleFacturaPago.buscarListaBuscaFacPorSiniestroProv(request.getParameter("cod_sin").replace("-","").trim(),(!request.getParameter("cod_sin").trim().equals("")?Integer.parseInt("20"+request.getParameter("cod_sin").substring(0,2)):Integer.parseInt(Utilidad.DateToString(new Date(), "YYYY"))),pre.getProveedor().getId());
				try {
					det = (DetalleFacturaPago)list.get(0);
					request.setAttribute("prov",det.getProveedor());
					request.setAttribute("tipselect",det.getTipoEmpleado().getId());
		    	} catch (Exception e) {}
		    	
			  }
		    	
			    


				
				request.setAttribute("lista",list);
				request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	 request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
		    	 return mapping.findForward("H4");
			}
			
			
			
			
			
			if(accion.equals("9")){ //// BUSQUEDA DE FACTURAS CODIGO DE FACTURA
				
				int anio1 = Integer.parseInt("20"+request.getParameter("codpre").substring(0,2));
				PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("codpre"), anio1);
			    request.setAttribute("preorden",pre);
							
				int anio = Integer.parseInt(request.getParameter("anio_factura"));
				DetalleFacturaPago det = new DetalleFacturaPago();
			    ArrayList list;
				
				
				
			    if(pre.getTipo_preorden()==2){
					  
					list = ExpDetalleFacturaPago.buscarListaBuscaFacPorCodigoYTitular(request.getParameter("numero_factura"),anio,pre.getTitular().getCedula());
					try {
						det = (DetalleFacturaPago)list.get(0);
						request.setAttribute("prov",det.getProveedor());
						request.setAttribute("tipselect",det.getTipoEmpleado().getId());
			    	} catch (Exception e) {}	
					
					  
				  }else{
					 
					  list = ExpDetalleFacturaPago.buscarListaBuscaFacPorCodigoYProv(request.getParameter("numero_factura"),anio,pre.getProveedor().getId());
					try {
						det = (DetalleFacturaPago)list.get(0);
						request.setAttribute("prov",det.getProveedor());
						request.setAttribute("tipselect",det.getTipoEmpleado().getId());
			    	} catch (Exception e) {}
			    	
				  }
			    

			
				
			
				request.setAttribute("lista",list);
				request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	 request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
		    	 return mapping.findForward("H4");
		    	
			}
			
			
			
				if(accion.equals("10")){
				
					String [] listFact = request.getParameterValues("listfact");
					String Cadena_Valores="";
					
					for(int i = 0 ; i<listFact.length;i++){
						Cadena_Valores= Cadena_Valores + listFact[i] + ",";
					}
					
					Cadena_Valores=Cadena_Valores.substring(0, Cadena_Valores.length()-1);
		     	
					int anio1 = Integer.parseInt("20"+request.getParameter("codpre").substring(0,2));
					PreOrdenPago pre = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("codpre"), anio1);
				    request.setAttribute("preorden",pre);
			
				ExpPreOrdenPago.agregarFacturasOrden(Cadena_Valores,request.getParameter("codpre"),anio1);
				
				incluirTraza(TR_PAGO_AGREGAR_FACTURA_PREORDEN,
						 request.getParameter("codpre") ,
						"PreOrden Actualizada", usuarioSession(request));
				
				DetallePreOrdenPago det ;
				
				//preorden segun el tipo
				det = ExpPreOrdenPago.buscarDetallePreOrdenPorCodigo(request.getParameter("codpre"), anio1);
				
			    
				
				
				
				
				Double tope = pre.getUnidad_tributaria()* Constantes.CantidadUnidadesPagoTimbre ;
				int aplica = 0;
				
				if (det.getMonto_Liquidado()>tope){
					aplica=1;
				}
			
				ExpPreOrdenPago.cambiarTopePreOrden(request.getParameter("codpre"), aplica, anio1);
				request.setAttribute("mensaje","Pre-Orden de Pago Nro " + request.getParameter("codpre") +  " ha sido actualizada satisfactoriamente");
				 request.setAttribute("tipoEmpleado", ExpTipoEmpleado.BuscarLista());
				return mapping.findForward("H4");
						    	
			}
				
				
				
				
				if(accion.equals("12")){ /// ANULAR PREORDEN
					
					
					int anio1 = Integer.parseInt("20"+request.getParameter("cod1").substring(0,2));
					String pre = request.getParameter("cod1");
					ExpPreOrdenPago.RechazoPreorden(pre, anio1);
					log.info("pre " + pre);
				    
				    
				    ExpPreOrdenPago.anularPreOrden(pre, Constantes.StatusAutorizado, anio1);
					ArrayList list = ExpPreOrdenPago.buscarporCodigo(pre,anio1 );
					request.setAttribute("lista",list);
			    	request.setAttribute("primera","no");
					
			
			    	
				
				 }
				
				
				
				
				
				
				
				
		


				return mapping.findForward("H1");
		}
       
  
       
 

    }
    
}

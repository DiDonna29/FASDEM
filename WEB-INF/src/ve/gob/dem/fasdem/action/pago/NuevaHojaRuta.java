/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.pago;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Cuenta;
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
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
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
public class NuevaHojaRuta extends GenericAction {


    static protected Logger log = Logger.getLogger(NuevaHojaRuta.class);

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
    	log.info("ACCION4567 " + accion);
		SimpleDateFormat formato_a = new SimpleDateFormat("yyyy");
		SimpleDateFormat formato_m = new SimpleDateFormat("MM");
       
       if (accion==null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
    	   HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
    	   request.setAttribute("hoja",h);
    	   return mapping.findForward("H1");
    	   
    	}else{
		
    		
			if(accion.equals("1")){ //// BUSQUEDA DE PAGOS POR FECHA
				
				int anio = Integer.parseInt(request.getParameter("fechaInicio").substring(6, 10));
			    ArrayList list = ExpPreOrdenPago.buscarListaFechaPreOrdenHojaRuta(request.getParameter("fechaInicio"), request.getParameter("fechaFin"),anio,2);
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("dselect",request.getParameter("fechaInicio"));
				request.setAttribute("hselect",request.getParameter("fechaFin"));
				request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
		    	return mapping.findForward("H1");
		    	
			}
			

			if(accion.equals("2")){ 
				
				log.info("pasa aki " + accion);
				
				Double Emp_Cont = 0.00;
				Double Emp_Fijo = 0.00;
				Double Obreros = 0.00;
				Double Pensionados = 0.00;
				Double Jubilados = 0.00;

				int anio = Integer.parseInt("20"+request.getParameter("cod"+request.getParameter("pre")).substring(0,2));
				ArrayList RESUMEN = ExpPreOrdenPago.buscarResumenPreOrden(request.getParameter("cod"+request.getParameter("pre")), anio);
                ResumenPreOrdenPago r = new ResumenPreOrdenPago();
                PreOrdenPago p = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod"+request.getParameter("pre")),anio);
              
                
                Double iva =0.00;
                Double isrl =0.00;
                Double timbre =0.00;
                Double liquidado =0.00;
                Double retencion =0.00;
                Double pagar =0.00;
                
                
                
               
				for (int j=0;j!=RESUMEN.size();j++){
					
					Double iva_detalle =0.00;
	                Double isrl_detalle =0.00;
	                Double timbre_detalle =0.00;
	                Double retencion_detalle =0.00;	
					
				r = (ResumenPreOrdenPago) RESUMEN.get(j);
				
				
						if(p.getAplicaTimbre()==1){
							timbre_detalle = r.getMONTO_TIMBRE();
						   }else{
							   timbre_detalle = 0.00; 
						 }
						
						 if(p.getTipo_preorden()== Constantes.TipoPreOrdenFarmacia ){
							 iva_detalle = 0.00;
							 isrl_detalle = 0.00;
							 retencion_detalle = 0.00;
							   
						 }else{
							 
							 if(p.getTipo_preorden()== Constantes.TipoPreOrdenReembolsos ){
								 iva_detalle = 0.00;
								 isrl_detalle = 0.00;
								 timbre_detalle = 0.00; 
								 retencion_detalle =0.00;
							 }else{	 
								 iva_detalle = r.getMONTO_IVA();
								 isrl_detalle = r.getMONTO_ISRL();
								 retencion_detalle = iva_detalle * p.getRetencion_iva()/100;
							 }
							 
						 }
						
						 int aplica_detalle;
						   
						   if (p.isAplica_isrl()==true){
							   aplica_detalle=1;
						   }else{
							   aplica_detalle=0;
						   }

				///---------------DISTRIBUIR POR TIPO DE PERSONAL---------------------------
								
							if (r.getTIPO_EMPLEADO() == 1  || r.getTIPO_EMPLEADO() == 9 ) { //EMPLEADO FIJO- -- LIBRE NOMBRAMIENTO
								Emp_Fijo = Emp_Fijo + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle);   
							}
							if (r.getTIPO_EMPLEADO() == 3 ) { // / EMPLEADO CONTRATADO
								Emp_Cont = Emp_Cont + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 2) { // / OBRERO FIJO---
								Obreros = Obreros + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 4) { // / OBRERO CONTRATADO--
								Obreros = Obreros + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 7) { // / JUBILADOS--
								Jubilados = Jubilados + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							
							}
							if (r.getTIPO_EMPLEADO() == 5) { // / EMPLEADO SUPLETE--
								Emp_Cont = Emp_Cont + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 8 || r.getTIPO_EMPLEADO() == 0) { // / SIN DEFINIR--
								Emp_Cont = Emp_Cont + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 6) { // / INCAPACITADO--
								Pensionados = Pensionados + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
			
							
							
							
				///-------------------------------------------------------------------------
				

					 if(p.getAplicaTimbre()==1){
						 timbre = timbre + r.getMONTO_TIMBRE();
					   }else{
						 timbre = 0.00; 
					 }
					
					 if(p.getTipo_preorden()== Constantes.TipoPreOrdenFarmacia ){
						 iva = 0.00;
						 isrl = 0.00;
						 retencion=0.00;
						   
					 }else{
						 
						 if(p.getTipo_preorden()== Constantes.TipoPreOrdenReembolsos ){
							 iva = 0.00;
							 isrl = 0.00;
							 timbre = 0.00; 
							 retencion=0.00;
						 }else{	 
							 iva = iva + r.getMONTO_IVA();
							 isrl = isrl + r.getMONTO_ISRL();
							 retencion=iva * p.getRetencion_iva()/100;
						 }
						 
					 }
					
					liquidado = liquidado + r.getMONTO_LIQUIDADO() ;
      		 		
				}
				
				log.info("Empleado Fijo " + Emp_Fijo);
				log.info("Empleado Contratado " + Emp_Cont);
				log.info("Obreros " + Obreros);
				log.info("jubilados " + Jubilados);
				log.info("Incapacitados " + Pensionados);
				

			   
			   int aplica;
			   
			   if (p.isAplica_isrl()==true){
				  aplica=1;
			   }else{
				  aplica=0;
			   }
			   
			   
			   pagar = liquidado - (isrl*aplica) - timbre - retencion + iva ;
			   log.info("Liquidado " + liquidado);
			   log.info("Pagar " + pagar);
			   
			   if(p.getProveedor().getRif().substring(0,1).equals("V")){
				   
				   Double Comparador = p.getUnidad_tributaria()*1000/12;
				   Double Sustraendo = p.getUnidad_tributaria()*(0.03)*(83.3334);
				   Double isrl_nat=0.0;
			
				   
				   if (liquidado>Comparador){
					   isrl_nat=(liquidado*0.03)-Sustraendo;
					   //timbre=0.0;
					   isrl=isrl_nat;
					   pagar = liquidado - (isrl*aplica) - timbre - retencion;
					}
				   
				   //timbre=0.0;
				
			   }
			   
			   log.info("liquidado " + liquidado);
			   log.info("isrl " + (isrl*aplica));
			   log.info("timbre " + timbre);
			   log.info("iva " + retencion);
			   
			   double pagar2 = pagar + (isrl*aplica) + timbre + retencion;
			   int rechazo=1; /// 2 rechazo de pago
				
				ExpHojaRuta.InsertarPreOrdenHoja(Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("pre")),request.getParameter("nro"+request.getParameter("pre")),pagar,anio,p.getCod_completo(),Emp_Fijo,Emp_Cont,Obreros,Jubilados,Pensionados,pagar2,(isrl*aplica),timbre,retencion,rechazo,r.getMONTO_IVA_PAGAR(),r.getMONTO_IVA());
				ExpPreOrdenPago.asignarHojaPreOrden(Integer.parseInt(request.getParameter("pre")), Integer.parseInt(request.getParameter("id_hoja")),anio); 
				
				
				 incluirTraza(TR_PAGO_AGREGAR_PAGO_HOJA,
						 request.getParameter("id_hoja"),
							"En la  Hoja de Ruta N° " + request.getParameter("id_hoja") + " Se Agrego la Pre Orden N° " + request.getParameter("pre"), usuarioSession(request));
					
				
				
				
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    request.setAttribute("anio_pre",anio);
		    	request.setAttribute("hoja",h);
		    	
		    	
		    	
		    	
		    	 if(h.getTipo().getId()!=5){
		    		 log.info("pasa aki 1");
		    		   return mapping.findForward("H1");
		    	   }else{
		    		   HojaRuta h1 = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
						
					    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
						request.setAttribute("primera","no");
				    	request.setAttribute("hoja",h1);
						request.setAttribute("lista",list);
				    	return mapping.findForward("H2");
		    	   }
		    	
			}
			
			if(accion.equals("14")){ 
				
				log.info("pasa aki " + accion);
				
				Double Emp_Cont = 0.00;
				Double Emp_Fijo = 0.00;
				Double Obreros = 0.00;
				Double Pensionados = 0.00;
				Double Jubilados = 0.00;

				int anio = Integer.parseInt("20"+request.getParameter("cod"+request.getParameter("pre")).substring(0,2));
				ArrayList RESUMEN = ExpPreOrdenPago.buscarResumenPreOrden(request.getParameter("cod"+request.getParameter("pre")), anio);
                ResumenPreOrdenPago r = new ResumenPreOrdenPago();
                PreOrdenPago p = ExpPreOrdenPago.buscarUnicoporCodigo(request.getParameter("cod"+request.getParameter("pre")),anio);
              
                
                Double iva =0.00;
                Double isrl =0.00;
                Double timbre =0.00;
                Double liquidado =0.00;
                Double retencion =0.00;
                Double pagar =0.00;
                
                
                
               
				for (int j=0;j!=RESUMEN.size();j++){
					
					Double iva_detalle =0.00;
	                Double isrl_detalle =0.00;
	                Double timbre_detalle =0.00;
	                Double retencion_detalle =0.00;	
					
				r = (ResumenPreOrdenPago) RESUMEN.get(j);
				
				
						if(p.getAplicaTimbre()==1){
							timbre_detalle = r.getMONTO_TIMBRE();
						   }else{
							   timbre_detalle = 0.00; 
						 }
						
						 if(p.getTipo_preorden()== Constantes.TipoPreOrdenFarmacia ){
							 iva_detalle = 0.00;
							 isrl_detalle = 0.00;
							 retencion_detalle = 0.00;
							   
						 }else{
							 
							 if(p.getTipo_preorden()== Constantes.TipoPreOrdenReembolsos ){
								 iva_detalle = 0.00;
								 isrl_detalle = 0.00;
								 timbre_detalle = 0.00; 
								 retencion_detalle =0.00;
							 }else{	 
								 iva_detalle = r.getMONTO_IVA();
								 isrl_detalle = r.getMONTO_ISRL();
								 retencion_detalle = iva_detalle * p.getRetencion_iva()/100;
							 }
							 
						 }
						
						 int aplica_detalle;
						   
						   if (p.isAplica_isrl()==true){
							   aplica_detalle=1;
						   }else{
							   aplica_detalle=0;
						   }

				///---------------DISTRIBUIR POR TIPO DE PERSONAL---------------------------
								
							if (r.getTIPO_EMPLEADO() == 1  || r.getTIPO_EMPLEADO() == 9 ) { //EMPLEADO FIJO- -- LIBRE NOMBRAMIENTO
								Emp_Fijo = Emp_Fijo + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle);   
							}
							if (r.getTIPO_EMPLEADO() == 3 ) { // / EMPLEADO CONTRATADO
								Emp_Cont = Emp_Cont + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 2) { // / OBRERO FIJO---
								Obreros = Obreros + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 4) { // / OBRERO CONTRATADO--
								Obreros = Obreros + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 7) { // / JUBILADOS--
								Jubilados = Jubilados + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							
							}
							if (r.getTIPO_EMPLEADO() == 5) { // / EMPLEADO SUPLETE--
								Emp_Cont = Emp_Cont + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 8 || r.getTIPO_EMPLEADO() == 0) { // / SIN DEFINIR--
								Emp_Cont = Emp_Cont + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
							if (r.getTIPO_EMPLEADO() == 6) { // / INCAPACITADO--
								Pensionados = Pensionados + (r.getMONTO_LIQUIDADO() - (isrl_detalle*aplica_detalle) - timbre_detalle - retencion_detalle + iva_detalle); 
							}
			
							
							
							
				///-------------------------------------------------------------------------
				

					 if(p.getAplicaTimbre()==1){
						 timbre = timbre + r.getMONTO_TIMBRE();
					   }else{
						 timbre = 0.00; 
					 }
					
					 if(p.getTipo_preorden()== Constantes.TipoPreOrdenFarmacia ){
						 iva = 0.00;
						 isrl = 0.00;
						 retencion=0.00;
						   
					 }else{
						 
						 if(p.getTipo_preorden()== Constantes.TipoPreOrdenReembolsos ){
							 iva = 0.00;
							 isrl = 0.00;
							 timbre = 0.00; 
							 retencion=0.00;
						 }else{	 
							 iva = iva + r.getMONTO_IVA();
							 isrl = isrl + r.getMONTO_ISRL();
							 retencion=iva * p.getRetencion_iva()/100;
						 }
						 
					 }
					
					liquidado = liquidado + r.getMONTO_LIQUIDADO() ;
      		 		
				}
				

			   
			   int aplica;
			   
			   if (p.isAplica_isrl()==true){
				  aplica=1;
			   }else{
				  aplica=0;
			   }
			   
			   
			   pagar = liquidado - (isrl*aplica) - timbre - retencion + iva ;

			   
			   if(p.getProveedor().getRif().substring(0,1).equals("V")){
				   
				   Double Comparador = p.getUnidad_tributaria()*1000/12;
				   Double Sustraendo = p.getUnidad_tributaria()*(0.03)*(83.3334);
				   Double isrl_nat=0.0;
			
				   
				   if (liquidado>Comparador){
					   isrl_nat=(liquidado*0.03)-Sustraendo;
					   //timbre=0.0;
					   isrl=isrl_nat;
					   pagar = liquidado - (isrl*aplica) - timbre - retencion;
					}
				   
				   //timbre=0.0;
				
			   }
			   
			   double pagar2 = pagar + (isrl*aplica) + timbre + retencion;
			   
			   boolean id_cuen=ExpCuenta.ExisteCuentaProveedor(p.getProveedor().getId());
			   
			   log.info("Cuenta " + id_cuen);
			   
			   int rechazo=1; /// id 2 rechazo en el pago pre_orden
			  			   
			   if(p.getTipo_preorden()== Constantes.TipoPreOrdenClinica || p.getTipo_preorden()== Constantes.TipoPreOrdenGenerica){
				/// verfica que sea clinica 
				   // verificacion de monto
			   if(pagar<=499999){   
				   /// si hay cuenta 
			   if(id_cuen==true){
				   
				ExpHojaRuta.InsertarPreOrdenHoja(Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("pre")),request.getParameter("nro"+request.getParameter("pre")),pagar,anio,p.getCod_completo(),Emp_Fijo,Emp_Cont,Obreros,Jubilados,
						Pensionados,pagar2,(isrl*aplica),timbre,retencion,rechazo,r.getMONTO_IVA_PAGAR(),r.getMONTO_IVA());
				ExpPreOrdenPago.asignarHojaPreOrden(Integer.parseInt(request.getParameter("pre")), Integer.parseInt(request.getParameter("id_hoja")),anio); 
				
				
				 incluirTraza(TR_PAGO_AGREGAR_PAGO_HOJA,
						 request.getParameter("id_hoja"),
							"En la  Hoja de Ruta N° " + request.getParameter("id_hoja") + " Se Agrego la Pre Orden N° " + request.getParameter("pre"), usuarioSession(request));
					
				
				ExpHojaRuta.UpdateRechazo(p.getCod_completo());
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    request.setAttribute("anio_pre",anio);
		    	request.setAttribute("hoja",h);
		    	request.setAttribute("rec",1);

		    		   return mapping.findForward("H5");
 
			   }else{
				   
				   log.info("Cuenta NO");
				   
				    request.setAttribute("mensaje", "Clinica No posee Numero de Cuenta.");
				   
				    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				    request.setAttribute("anio_pre",anio);
			    	request.setAttribute("hoja",h);
			    	request.setAttribute("rec",0);

			    	return mapping.findForward("H5");
			   }
			   }else{
				   				   
				    request.setAttribute("mensaje", "Monto de Pre-Orden excedido.");
				   
				    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				    request.setAttribute("anio_pre",anio);
			    	request.setAttribute("hoja",h);
			    	request.setAttribute("rec",0);

			    	return mapping.findForward("H5");
			   }
			   }else{
				   
				   ExpHojaRuta.InsertarPreOrdenHoja(Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("pre")),request.getParameter("nro"+request.getParameter("pre")),pagar,anio,p.getCod_completo(),Emp_Fijo,Emp_Cont,Obreros,
						   Jubilados,Pensionados,pagar2,(isrl*aplica),timbre,retencion,rechazo,r.getMONTO_IVA_PAGAR(),r.getMONTO_IVA());
				   ExpPreOrdenPago.asignarHojaPreOrden(Integer.parseInt(request.getParameter("pre")), Integer.parseInt(request.getParameter("id_hoja")),anio); 
					
					
					 incluirTraza(TR_PAGO_AGREGAR_PAGO_HOJA,
							 request.getParameter("id_hoja"),
								"En la  Hoja de Ruta N° " + request.getParameter("id_hoja") + " Se Agrego la Pre Orden N° " + request.getParameter("pre"), usuarioSession(request));
						
					
					ExpHojaRuta.UpdateRechazo(p.getCod_completo());
					HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				    request.setAttribute("anio_pre",anio);
			    	request.setAttribute("hoja",h);
			    	request.setAttribute("rec",1);
			    	
			    	
			    	
			    	
			    	 if(h.getTipo().getId()!=5){
			    		   return mapping.findForward("H5");
			    	   }else{
			    		   
			    		    HojaRuta h1 = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));						
						    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
							request.setAttribute("primera","no");
					    	request.setAttribute("hoja",h1);
							request.setAttribute("lista",list);
					    	return mapping.findForward("H4");
			    	   }
				   
			   }
		    	
			}
			
			if(accion.equals("3")){ //// BUSQUEDA POR CODIGO DE PREORDEN

				int anio = Integer.parseInt("20"+request.getParameter("codigo1").substring(0,2));
				ArrayList list = ExpPreOrdenPago.buscarPorCodigoPreOrdenHojaRuta(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"),anio,2);
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);

				
				return mapping.findForward("H1");

			}
			
			if(accion.equals("13")){ //// BUSQUEDA POR CODIGO DE PREORDEN

				int anio = Integer.parseInt("20"+request.getParameter("codigo1").substring(0,2));
				ArrayList list = ExpPreOrdenPago.buscarPorCodigoPreOrdenHojaRuta(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"),anio,2);
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				int rec = ExpHojaRuta.BuscarRechazo(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"));
				
			    request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
				request.setAttribute("rec",rec);

				
				
				return mapping.findForward("H5");
				
			}
			
			if(accion.equals("9")){ //// Agregar Causado

				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));

				ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
				
				ExpHojaRuta.GuardarCausado(h.getId(), request.getParameter("causado"));
				
				Double contratados = ExpHojaRuta.contratados(h.getId());
			    Double fijos = ExpHojaRuta.fijos(h.getId());
			    Double obreros = ExpHojaRuta.obreros(h.getId());
			    Double jubilados = ExpHojaRuta.jubilados(h.getId());
			    Double incapacitados = ExpHojaRuta.incapacitados(h.getId());

				   	
				Double monto407 = (double) 0;
				Double monto401 = (double) 0;
				
				
				monto401 = fijos + contratados + obreros;
				monto407 = jubilados + incapacitados;
			    
			    
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
				request.setAttribute("monto401",monto401);
				request.setAttribute("monto407",monto407);
				request.setAttribute("mensaje", "Actualizando el Causado");
				
				return mapping.findForward("H4");

			}
			
			if(accion.equals("7")){ //// BUSQUEDA POR CODIGO DE PREORDEN

				int anio = Integer.parseInt("20"+request.getParameter("codigo1").substring(0,2));
			    ArrayList list = ExpPreOrdenPago.buscarPorCodigoPreOrdenHojaRuta(request.getParameter("codigo1") + "-" + request.getParameter("codigo2"),anio,2);
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
			    request.setAttribute("primera","no");
		    	request.setAttribute("anio_pre",anio);
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
				
				return mapping.findForward("H3");

			}
			
			
			if(accion.equals("4")){ //// DETALLE
				
				
			    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
		    	return mapping.findForward("H2");
		    	
			}
			
			if(accion.equals("15")){ //// DETALLE
				
				
			    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
			    
			    Double contratados = ExpHojaRuta.contratados(h.getId());
			    Double fijos = ExpHojaRuta.fijos(h.getId());
			    Double obreros = ExpHojaRuta.obreros(h.getId());
			    Double jubilados = ExpHojaRuta.jubilados(h.getId());
			    Double incapacitados = ExpHojaRuta.incapacitados(h.getId());
				   	
				Double monto407 = (double) 0;
				Double monto401 = (double) 0;
				
				
				monto401 = fijos + contratados + obreros;
				monto407 = jubilados + incapacitados;

			    
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
				request.setAttribute("monto401",monto401);
				request.setAttribute("monto407",monto407);
		    	return mapping.findForward("H4");
		    	
			}
			
			
			if(accion.equals("8")){ //// DETALLE CAUSADO
				
				
			    HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));

			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
			    Double contratados = ExpHojaRuta.contratados(h.getId());
			    Double fijos = ExpHojaRuta.fijos(h.getId());
			    Double obreros = ExpHojaRuta.obreros(h.getId());
			    Double jubilados = ExpHojaRuta.jubilados(h.getId());
			    Double incapacitados = ExpHojaRuta.incapacitados(h.getId());
			    
				   	
				Double monto407 = (double) 0;
				Double monto401 = (double) 0;
				
				
				monto401 = fijos + contratados + obreros;
				monto407 = jubilados + incapacitados;
			    
			    
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
				request.setAttribute("monto401",monto401);
				request.setAttribute("monto407",monto407);
		    	return mapping.findForward("H4");
		    	
			}
			
			if(accion.equals("5")){ //// ELIMINAR
				
				
				log.info("ENTRO A ELIMINAR " );
				
				ExpHojaRuta.EliminarPreOrdenHoja(Integer.parseInt(request.getParameter("pre")), Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("anio"+request.getParameter("pre"))));
				ExpPreOrdenPago.SacarHojaPreOrden(Integer.parseInt(request.getParameter("pre")),Integer.parseInt(request.getParameter("anio"+Integer.parseInt(request.getParameter("pre")))));
				
				
				incluirTraza(TR_PAGO_ELIMINAR_PAGO_HOJA,
						 request.getParameter("id_hoja"),
							"En la  Hoja de Ruta N° " + request.getParameter("id_hoja") + " Se Elimino la Pre Orden N° " + request.getParameter("pre"), usuarioSession(request));
					
				
				
				
				
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				log.info("ID_HOJA " + h.getId() );
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
		    	return mapping.findForward("H2");
		    	
			}
			
			
			if(accion.equals("6")){ //// NUEVOS
				
				   HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
		    	   request.setAttribute("hoja",h);
		    	   
		    	   request.setAttribute("tot",request.getParameter("total"));
		    	   
		    	   log.info("tot " + request.getParameter("total"));
		    	   log.info("tot2 " + h.getTipo().getId());
		    	   
		    	   if(h.getTipo().getId()!=5){
		    		   return mapping.findForward("H1");
		    	   }else{
		    		   return mapping.findForward("H3");
		    	   }		    	   
		    	
			}
			
			if(accion.equals("12")){ //// incluir en pre-orden
				
				   HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
		    	   request.setAttribute("hoja",h);		    	   
		    	   request.setAttribute("tot",request.getParameter("total"));
		    	   request.setAttribute("rec",0);
		    	   
		    	   if(h.getTipo().getId()!=5){
		    		   log.info("ENTRO A h5 " + h.getTipo().getId());
		    		   return mapping.findForward("H5");
		    	   }else{
		    		   log.info("ENTRO A h3 " + h.getTipo().getId());
		    		   return mapping.findForward("H3");
		    	   }
		    	  		    	
			}

			
			if(accion.equals("11")){ //// ELIMINAR
				
				
				log.info("ENTRO A ELIMINAR " );
				
				ExpHojaRuta.EliminarPreOrdenHoja(Integer.parseInt(request.getParameter("pre")), Integer.parseInt(request.getParameter("id_hoja")), Integer.parseInt(request.getParameter("anio"+request.getParameter("pre"))));
				ExpPreOrdenPago.SacarHojaPreOrden(Integer.parseInt(request.getParameter("pre")),Integer.parseInt(request.getParameter("anio"+Integer.parseInt(request.getParameter("pre")))));
				
				
				incluirTraza(TR_PAGO_ELIMINAR_PAGO_HOJA,
						 request.getParameter("id_hoja"),
							"En la  Hoja de Ruta N° " + request.getParameter("id_hoja") + " Se Elimino la Pre Orden N° " + request.getParameter("pre"), usuarioSession(request));
					
				
				
				
				
				HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("id_hoja")));
				
			    ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
			    Double contratados = ExpHojaRuta.contratados(h.getId());
			    Double fijos = ExpHojaRuta.fijos(h.getId());
			    Double obreros = ExpHojaRuta.obreros(h.getId());
			    Double jubilados = ExpHojaRuta.jubilados(h.getId());
			    Double incapacitados = ExpHojaRuta.incapacitados(h.getId());
			    
				   	
				Double monto407 = (double) 0;
				Double monto401 = (double) 0;
				
				
				monto401 = fijos + contratados + obreros;
				monto407 = jubilados + incapacitados;
			    
			    
				request.setAttribute("primera","no");
		    	request.setAttribute("hoja",h);
				request.setAttribute("lista",list);
				request.setAttribute("monto401",monto401);
				request.setAttribute("monto407",monto407);
		    	return mapping.findForward("H4");
		    	
			}
		
			 return mapping.findForward("H1");
			  
		}
       
       

       
       

    }
    
}

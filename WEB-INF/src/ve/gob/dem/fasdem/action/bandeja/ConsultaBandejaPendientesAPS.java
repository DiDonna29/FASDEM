/**
 * 15/07/2010 marcenrl
 */
package ve.gob.dem.fasdem.action.bandeja;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.bean.SiniestroPortal;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.exp.ExpSiniestroPortal;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 */
public class ConsultaBandejaPendientesAPS extends GenericAction {
	/*
	 * (non-Javadoc)
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(ConsultaBandejaPendientesAPS.class);

	@SuppressWarnings({ "static-access", "rawtypes" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		log.info("Entro a el accion de la bandeja");
		log.info("LOG DE ACCION " + request.getParameter("accion"));
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		if (request.getSession().getAttribute("APSGUARDADO") != null) {
			am.add(ALERT_AVISOS, new ActionMessage("insert.aps.success", request.getSession().getAttribute("APSGUARDADO")));
			saveMessages(request, am);
			request.getSession().removeAttribute("APSGUARDADO");
		}
		log.info("MENSAJE A ENVIAR0000");
		ArrayList lista = null;
		;
		String CantidadAtencion = "0";
		String CantidadPendientes = "0";
		String CantidadAtendidos = "0";
		try {
			String Msn = "";
			if (request.getParameter("accion") != null) {
				log.info("LOG DE ACCION " + request.getParameter("accion")+" SI ES QUE VIENE DE BANDEJA "+request.getParameter("debandeja"));
				//if (request.getParameter("accion").equals("1")) {
				if (request.getParameter("accion").equals("1")) {
					String obs = request.getParameter(request.getParameter("siniestro"));
					ExpSiniestroBandeja.CambiarEstatusSiniestro(Integer.parseInt(request.getParameter("siniestro")), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido(), obs);
					SiniestroBandeja sin = ExpSiniestroBandeja.BuscarpoId(Integer.parseInt(request.getParameter("siniestro")));
					String telefono = sin.getTlf().replace("-", "");
					int log_telef = telefono.length();
					if (log_telef == 11) {
						String RifFasdem = "";
						String fechaCaso = Utilidad.DateToString(sin.getFechaProcesa(), "dd/MM/yyyy");
						try {
							String cirif = sin.getRifClinica().substring(0, 1);
							int restorif = Integer.parseInt(sin.getRifClinica().substring(1));
							RifFasdem = cirif + restorif;
						} catch (Exception e) {
							RifFasdem = sin.getRifClinica();
						}
						SiniestroPortal etpsin = null;
						log.info("MENSAJE A BELGICA AAAAA "+etpsin );
						try {
							etpsin = ExpSiniestroPortal.buscarClavePorClinicaFechaBenef(fechaCaso, RifFasdem, sin.getCedBeneficiario());
						} catch (Exception e) {
						}
						log.info("CLAVE " + etpsin);
						if (etpsin != null) {
							log.info("CLAVE " + etpsin.getNroSiniestro() + " " + etpsin.getNroPago());
							try {

						    	log.info(" MENSAJE ENTRO A ENVIAR EL CORREO ////////////////////////////////////////////////////////////// ");
								
							    Properties props = new Properties();

						    
							    props.setProperty("mail.smtp.host","smtp3.tsj-dem.gob.ve");
							  
							    props.setProperty("mail.smtp.port", "25");
							  
							    //props.setProperty("mail.smtp.user", "sms-fasdem@tsj-dem.gob.ve");
							    
							    // Preparamos la sesion
							    Session session1 = Session.getDefaultInstance(props);
							
							    // Construimos el mensaje
							    
							    MimeMessage message = new MimeMessage(session1);
							    message.setFrom(new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
							   //message.addRecipient(Message.RecipientType.TO,new InternetAddress(ca+tl+"@sms.tsj-dem.gob.ve"));
							   message.addRecipient(Message.RecipientType.TO,new InternetAddress(request.getParameter("telefonon")+"@sms.tsj-dem.gob.ve")); 
							    log.info("MENSAJE A ENVIAR1" );
							    // para pruebas (CARLOS CEL) este -->	message.addRecipient(Message.RecipientType.TO, new InternetAddress("04242062608@sms.tsj-dem.gob.ve"));
							    
							    /*message.addRecipient(Message.RecipientType.TO, new InternetAddress("fasdem@dem.telemo.com.ve"));
								
								log.info("MENSAJE A ENVIAR2" );
								message.addRecipient(Message.RecipientType.CC,new InternetAddress("04269178621@sms.tsj-dem.gob.ve"));
								log.info("MENSAJE A ENVIAR3" );*/
								message.setSubject(telefono);
								String Mensaje = "FASDEM|" + etpsin.getNroSiniestro() + " " + etpsin.getNroPago();
								log.info("MENSAJE A ENVIAddddddR"  + Mensaje);
								//message.setText("FASDEM: "+p.getNombre()+" "+p.getApellido()+" ha ingresado por APS a: "+fs.getClinica().getNombre()+". Fecha: "+date2+". Monto: "+request.getParameter("monto")+" BsF.");
								// Lo enviamos.
								Transport t = session1.getTransport("smtp");
								t.send(message, message.getAllRecipients());
								t.close();
							} catch (Exception e3) {
								log.info(e3);
							}
						}
					}
				}
				if (request.getParameter("accion").equals("2")) {
					log.info("entro a la accion 2");
					SiniestroBandeja sin = ExpSiniestroBandeja.BuscarpoId(Integer.parseInt(request.getParameter("siniestro")));
					if (sin.getId_estatus() == 1) {  // Disponible
						ExpSiniestroBandeja.TomarSiniestro(Integer.parseInt(request.getParameter("siniestro")), 0, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido());
					} else {
						if (sin.getId_estatus() == 0) {  /// en Atencion
							Msn = "La solicitud se encuentra siendo atendido por el analista " + sin.getDatosProcesa();
							request.setAttribute("mensaje", Msn);
						}
					}
				}
				if (request.getParameter("accion").equals("3")) {
					
					String obs = request.getParameter(request.getParameter("siniestro"));
					ExpSiniestroBandeja.CambiarEstatusSiniestro(Integer.parseInt(request.getParameter("siniestro")), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido(), obs);
					log.info("Entre a la accion-->3, el sms-->"+request.getParameter("sms")+" telef"+request.getParameter("tlf"));
					//desde aqui va lo del sms	
					request.getParameter("sms");
					request.getParameter("tlf");
					request.getParameter("clinica");
					request.getParameter("beneficiario");
				}
			String fechaCaso = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
			 log.info("ANTES DEL TRY**** "+request.getParameter("sms")+"siguiente valor paciente");
			 log.info("APS-FASDEM informa su solicitud fecha:"+ fechaCaso+"pcte:"+request.getParameter("beneficiario")+" en:"+request.getParameter("clinica")+"fue rechazada por:"+request.getParameter("siniestro2"));
					
					 log.info("ANTES DEL TRY**** "+request.getParameter("sms"));
			          try{
			              log.info("ENTRE AL TRY AQUI EL NUMERO --> "+request.getParameter("tlf"));
			          if ("on".equals(request.getParameter("sms"))) {
			              try {log.info("entre aqui en puente "+request.getParameter("tlf"));
			                  Properties props = new Properties();

			              
			                  props.setProperty("mail.smtp.host","smtp3.tsj-dem.gob.ve");

			                  props.setProperty("mail.smtp.port", "25");
			                  Session session1 = Session.getDefaultInstance(props);
			                  MimeMessage messagee = new MimeMessage(session1);
			                  
			                  messagee.setFrom(new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
			       
			                
			               ///////////////////////mensajeeeee //////////////////////////
			                
			                messagee.setText("APS-FASDEM informa su solicitud fecha:"+ fechaCaso+"pcte:"+request.getParameter("beneficiario")+" en:"+request.getParameter("clinica")+"fue rechazada por:"+request.getParameter("siniestro2"));

			                log.info("mensaje sms --> APS - fasdem informa su solicitud fue rechazada  por:"+request.getParameter("siniestro2"));
			            

			                  String tlf=request.getParameter("tlf");
			                  tlf=tlf.replace("-", "");
			                  log.info("1entre aqui en puente "+tlf);
			                  //messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
			                  messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
			                  // Lo enviamos.
			                  Transport tt = session1.getTransport("smtp");
			                  log.info("1er entre aqui en puente "+tlf);
			                  tt.send(messagee, messagee.getAllRecipients());
			                  log.info("2do entre aqui en puente "+tlf);
			                  tt.close();
			              } catch (Exception e) {log.info("catch  aqui en puenteeeeeeeeeeeeeeee**** ",e);}
			                  /*ServicioEnLinea sel = (ServicioEnLinea) request.getSession().getAttribute("objPuenteOnline");
			                  ExpSiniestroBandeja.CambiarEstatusSiniestro(sel.getIdSolicitud(), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido(), sel.getObservacion());
			                  request.getSession().setAttribute("objPuenteOnline", null);*/
			                  //enviarCorreo(sel.getTelefono(), codigoBandeja);
			            }
			          }

			          catch (Exception e) {
			              log.info("2errooooentre aqui en puenteeeeeeeeeeeeeeee**** ",e);
			          }
			          /*
			           * detallesPresupuesto = pf.listDetallesFactura(mapa);
			           * request.setAttribute("listDetalle", detallesPresupuesto);
			           * mapa.setIssFactura(true); facturas=pf.listDetallesFactura(mapa);
			           * request.setAttribute("listFactura", facturas);
			           */
			    am.add(ALERT_AVISOS, new ActionMessage("env.general.sms"));
			          saveMessages(request, am);
			         // return mapping.findForward(FWD_SUCCESS);
			          
					//y aqui termina sms
					
				}
			
			String dateHoy = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
			log.info("paramAnioBandeja " + request.getParameter("paramAnioBandeja"));
			Calendar c = Calendar.getInstance();
			String paramAnioBandeja = String.valueOf(c.get(Calendar.YEAR));
			if (request.getParameter("paramAnioBandeja")!=null){
				paramAnioBandeja =request.getParameter("paramAnioBandeja");
			}
			request.setAttribute("paramAnioBandeja", paramAnioBandeja);
			try {
				lista = ExpSiniestroBandeja.BuscarListaPendientesyAtencion(Constantes.IdentificadorAPS, paramAnioBandeja);
			} catch (Exception e) {
			}
			try {
				CantidadAtencion = ExpSiniestroBandeja.BuscarCantidadEstatusTipo(Constantes.SolicitudEnAtencion, Constantes.IdentificadorAPS);
			} catch (Exception e) {
			}
			try {
				CantidadPendientes = ExpSiniestroBandeja.BuscarCantidadEstatusTipo(Constantes.SolicitudPendiente, Constantes.IdentificadorAPS);
			} catch (Exception e) {
			}
			try {
				CantidadAtendidos = ExpSiniestroBandeja.BuscarCantidadEstatusTipoFecha(Constantes.SolicitudAtendida, Constantes.IdentificadorAPS, dateHoy);
			} catch (Exception e) {
			}
		} catch (Exception e) {
			log.info("ERROR ", e);
			return mapping.findForward("h1");
		}
		request.setAttribute("usuario_bandeja", usuarioSession(request).getLogin());
		request.setAttribute("lista", lista);
		request.setAttribute("C_Atencion", CantidadAtencion);
		request.setAttribute("C_Pendientes", CantidadPendientes);
		request.setAttribute("C_Atendidos", CantidadAtendidos);
		return mapping.findForward("h1");
	}
}

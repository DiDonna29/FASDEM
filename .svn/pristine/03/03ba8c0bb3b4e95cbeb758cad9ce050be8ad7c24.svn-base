/**15/07/2010
 * marcenrl
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
 * 
 */
public class ConsultaBandejaPendientesEmergencia extends GenericAction {

	
    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    static protected Logger log = Logger.getLogger(ConsultaBandejaPendientesEmergencia.class);

    @SuppressWarnings({ "rawtypes", "static-access" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

	
	
	ActionMessages am = new ActionMessages();
	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

	
	try {
		validarAction(request, form, ent, am, this.getClass());
	} catch (PersonalNotFillItems e) {
		return mapping.findForward(FWD_INPUT);
	}


	ArrayList lista = null;;
	String CantidadAtencion="0";
	String CantidadPendientes="0";
	String CantidadAtendidos="0";
	log.info("Entro a el accion de la bandeja");
	try {
		
		String Msn="";
		
	if(request.getParameter("accion")!=null){	
		
		log.info("Entro a el accdfgsdfsdfsdfsdfsdf"+request.getParameter("accion"));
	
		
		if(request.getParameter("accion").equals("1")){
		
			String obs= request.getParameter(request.getParameter("siniestro"));
			ExpSiniestroBandeja.CambiarEstatusSiniestro(Integer.parseInt(request.getParameter("siniestro")), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre()+ " " + usuarioSession(request).getApellido(), obs);
			SiniestroBandeja sin =ExpSiniestroBandeja.BuscarpoId(Integer.parseInt(request.getParameter("siniestro")));
			

			String telefono = sin.getTlf().replace("-", "");
			int log_telef = telefono.length();

	
			if (log_telef==11){
				
						String RifFasdem ="";
						String fechaCaso = Utilidad.DateToString(sin.getFechaProcesa(), "dd/MM/yyyy");
						
						
						try {
							String cirif=sin.getRifClinica().substring(0,1);
							int restorif=Integer.parseInt(sin.getRifClinica().substring(1));
							RifFasdem=cirif+restorif;
						} catch (Exception e) {
							RifFasdem=sin.getRifClinica();
					    }
						SiniestroPortal etpsin =null;
						try {
							
						etpsin = ExpSiniestroPortal.buscarClavePorClinicaFechaBenef(fechaCaso, RifFasdem,sin.getCedBeneficiario());
						} catch (Exception e) {}
				
					
						log.info("belgica aqui probando1   "  +etpsin);
						
						
			if (etpsin!=null){
				
				log.info("belgica aqui probando"  );
		
					try{
						
					    Properties props = new Properties();
					    props.setProperty("mail.smtp.host", "smtp.tsj-dem.gob.ve");
					    props.setProperty("mail.smtp.port", "25");
					    
					    	// Preparamos la sesion
					    Session session1 = Session.getDefaultInstance(props);
		
					    	// Construimos el mensaje
					    
					    MimeMessage message = new MimeMessage(session1);
					    message.setFrom(new InternetAddress("PortalFASDEM@tsj-dem.gob.ve"));
						message.addRecipient(Message.RecipientType.CC,new InternetAddress("04269178621@sms.tsj-dem.gob.ve"));
						message.addRecipient(Message.RecipientType.CC,new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
					    message.addRecipient(Message.RecipientType.TO,new InternetAddress("fasdem@dem.telemo.com.ve"));
					  
						message.addRecipient(Message.RecipientType.CC,new InternetAddress("04269178621@sms.tsj-dem.gob.ve"));
						message.addRecipient(Message.RecipientType.CC,new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
					    	//message.addRecipient(Message.RecipientType.CC,new InternetAddress("lmarin@tsj-dem.gob.ve"));
		               
					    message.setSubject(telefono);
					    
					   
					    String Mensaje = "FASDEM|" + etpsin.getNroSiniestro()+ " " +  etpsin.getNroPago();
					    
					    	
					    
					    message.setText(Mensaje,"ISO-8859-1","html");
					    
					   
					    // Lo enviamos.
					    Transport t = session1.getTransport("smtp");
					    t.send(message, message.getAllRecipients());
					    t.close();
						
					} catch (Exception e3) {log.info(e3);}		
		
					
			}	
		
		}
	
		
		}
		
		if(request.getParameter("accion").equals("2")){
			log.info("entro a la accion 2");
			SiniestroBandeja sin = ExpSiniestroBandeja.BuscarpoId(Integer.parseInt(request.getParameter("siniestro")));
			
			if (sin.getId_estatus()==1){  // Disponible
				ExpSiniestroBandeja.TomarSiniestro(Integer.parseInt(request.getParameter("siniestro")), 0,  usuarioSession(request).getLogin(), usuarioSession(request).getNombre()+ " " + usuarioSession(request).getApellido());
			}else{
				
				if (sin.getId_estatus()==0){  /// en Atencion
					Msn="La solicitud se encuentra siendo atendido por el analista " + sin.getDatosProcesa();
					request.setAttribute("mensaje",Msn);
				}
			}
			
		
		}
		
		if(request.getParameter("accion").equals("3")){
			

			String obs= request.getParameter(request.getParameter("siniestro"));
			ExpSiniestroBandeja.CambiarEstatusSiniestro(Integer.parseInt(request.getParameter("siniestro")), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre()+ " " + usuarioSession(request).getApellido(), obs);
		
			
		}
		
		
		
		
		
		
	}	
	

	String dateHoy = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
	
	Calendar c = Calendar.getInstance();
	String paramAnioBandeja = String.valueOf(c.get(Calendar.YEAR));
	if (request.getParameter("paramAnioBandeja")!=null){
		paramAnioBandeja =request.getParameter("paramAnioBandeja");
	}
	request.setAttribute("paramAnioBandeja", paramAnioBandeja);
		try {
			lista = ExpSiniestroBandeja.BuscarListaPendientesyAtencion(Constantes.IdentificadorEmergencia, paramAnioBandeja);
		} catch (Exception e) {
		}
	
	    try {
	    	CantidadAtencion = ExpSiniestroBandeja.BuscarCantidadEstatusTipo(Constantes.SolicitudEnAtencion ,Constantes.IdentificadorEmergencia);
		} catch (Exception e) {
		}
		
		try {
			CantidadPendientes = ExpSiniestroBandeja.BuscarCantidadEstatusTipo(Constantes.SolicitudPendiente ,Constantes.IdentificadorEmergencia);
		} catch (Exception e) {
		}
		
		
		try {
		CantidadAtendidos = ExpSiniestroBandeja.BuscarCantidadEstatusTipoFecha(Constantes.SolicitudAtendida ,Constantes.IdentificadorEmergencia,dateHoy);
		} catch (Exception e) {
		}
	
	
	
	
	
	
	
	} catch (Exception e) {
		
		log.info("ERROR " + e);
	    return mapping.findForward("h1");
	}
	

	
	request.setAttribute("usuario_bandeja",usuarioSession(request).getLogin());
	request.setAttribute("lista",lista);
	request.setAttribute("C_Atencion",CantidadAtencion);
	request.setAttribute("C_Pendientes",CantidadPendientes);
	request.setAttribute("C_Atendidos",CantidadAtendidos);
	return mapping.findForward("h1");
		
	
	
	
    }
}

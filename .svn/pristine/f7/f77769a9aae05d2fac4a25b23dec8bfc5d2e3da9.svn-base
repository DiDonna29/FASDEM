package ve.gob.dem.fasdem.action.aps;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.ServicioEnLinea;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpSiniestroBandeja;
import ve.gob.dem.fasdem.per.PerPoliza;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class NuevoAps extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_APS_NUEVO);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		Persona t = buscarTitularBeneficiario(request);
		request.setAttribute("telefonon", request.getParameter("telefonon"));
		
		if (request.getParameter("aps")!=null && !"null".equals(request.getParameter("aps"))){
			request.setAttribute("aps", request.getParameter("aps"));
		}

		if ("INACTIVO".equals(t.getBeneficiario().getEstatus())) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.inactivo"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INDEX);
		}
		
		PerPoliza pp = new PerPoliza();
		PerSiniestro ps = new PerSiniestro();
		Siniestro siniPadre = null;
		Mapa mapa = new Mapa();
		Siniestro s = null;
          
		
		
		
		log.info("aniosinisiiiii " + mapa.getAnioSiniestro());
		siniPadre = (Siniestro) request.getSession().getAttribute("siniestroPadre");
		//String codigoBandeja = "";
		try { 
		
			validarAction(request, form, ent, am, this.getClass());
			log.info("saliendo del calidar action");
		} catch (PersonalNotFillItems e) {
			log.info("PersonalNotFillItems ", e);
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		mapa.setMontoAmparado(mapa.getMonto());
		mapa.setIdTipoTramite(COD_TIPO_TRAMITE_APS);
		mapa.setIdEstatus(COD_ESTATUS_EGRESADO);
		mapa.setIdPoliza(pp.searchActivo().getId());
		log.info("anioSini " + mapa.getAnioSiniestro());
//		mapa.setIdProveedor(siniPadre.getProveedor().getId());	
//		log.info("esteeee"+ siniPadre.getProveedor().getId());
		mapa.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));	
		log.info("o esssteee"+ Integer.parseInt(request.getParameter("idProveedor")));
		try {
			findDisponible(mapa, null);
		} catch (CoberturaNotDisponibleException e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}

		try {
			permitido(mapa);
		} catch (Exception e) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.nocumple"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		if (siniPadre != null) {
			
			mapa.setAnioMesCodigo(siniPadre.getAniomesCodigo());
			mapa.setCodigo(siniPadre.getCodigo());
			mapa.setIdSiniestroPadre(siniPadre.getId());
			mapa.setCedula(siniPadre.getCedula());
			mapa.setCedulaBeneficiario(siniPadre.getCedulaBeneficiario());
			mapa.setNombres(siniPadre.getNombres());
			mapa.setApellidos(siniPadre.getApellidos());
			mapa.setNombresBeneficiario(siniPadre.getNombresBeneficiario());
			mapa.setApellidosBeneficiario(siniPadre.getApellidosBeneficiario());
			////ultimos cambios
			mapa.setFechaNacimiento(siniPadre.getFechaNacimiento());
			mapa.setFechaNacimientoBeneficiario(siniPadre.getFechaNacimientoBeneficiario());
			mapa.setSexo(siniPadre.getSexo());
			mapa.setSexoBeneficiario(siniPadre.getSexoBeneficiario());
			mapa.setParentesco(siniPadre.getParentesco());

			
		}
		log.info("valor Siniestro Padre [" + mapa.getIdSiniestroPadre() + "]");
		mapa.setCitaPostOperatorio(false);
		mapa.setMontoPresupuestado(mapa.getMonto());
		mapa.setMontoAmparado(mapa.getMonto());
		mapa.setMontoNegociado(mapa.getMonto());
		mapa.setIdSiniestro(ps.insert(mapa));
		incluirTraza(TR_APS_CARGAR, String.valueOf(mapa.getIdSiniestro()), "Incluir APS", usuarioSession(request));
		request.getSession().removeAttribute("siniestroPadre");
		s = ps.search(mapa);
		//codigoBandeja = s.getAniomesCodigo() + "-" + s.getCodigo() + "-" + s.getSubCodigo();
		request.getSession().setAttribute("APSGUARDADO", s.getAniomesCodigo() + "-" + s.getCodigo() + "-" + s.getSubCodigo());
		if (request.getSession().getAttribute("objPuenteOnline") != null) {
			try {
			log.info("entre aqui en puente "+request.getParameter("telefonon"));
		    Properties props = new Properties();

	    
		    props.setProperty("mail.smtp.host","smtp3.tsj-dem.gob.ve");
		  
		    props.setProperty("mail.smtp.port", "25");
		    Session session1 = Session.getDefaultInstance(props);
		    MimeMessage messagee = new MimeMessage(session1);
		    messagee.setFrom(new InternetAddress("sms-fasdem@tsj-dem.gob.ve"));
		    messagee.setText("APS - FASDEM informa que su solicitud procede por: "+s.getMontoAmparado()+ " Bsf. pcte: "+mapa.getNombresBeneficiario()+" "+mapa.getApellidosBeneficiario()+", en: "+s.getProveedor().getDescripcion()+" .Fecha:"+Utilidad.DateToString(
				    		s.getFechaOcurrencia(), "dd/MM/yyyy"));
		   /* messagee.setText("Emerg - fasdem informa su solicitud fue procesada "+s.getSubCodigo()+"-"+s.getCodigo()+"-"+s.getAniomesCodigo()+" en la Clinica: "+s.getProveedor().getDescripcion()+ ", pcte:" +mapa.getNombresBeneficiario()+" "+mapa.getApellidosBeneficiario()+ " Fecha:"+Utilidad.DateToString(
		    		s.getFechaOcurrencia(), "dd/MM/yyyy"));*/
		    String tlf=request.getParameter("telefonon");
		    tlf=tlf.replace("-", "");
		    log.info("1entre aqui en puente "+tlf);
		    messagee.addRecipient(Message.RecipientType.TO,new InternetAddress(tlf+"@sms.tsj-dem.gob.ve"));
		    // Lo enviamos.
			Transport tt = session1.getTransport("smtp");
			log.info("1entre aqui en puente "+tlf);
			tt.send(messagee, messagee.getAllRecipients());
			log.info("2entre aqui en puente "+tlf);
			tt.close();
			} catch (Exception e) {}
			ServicioEnLinea sel = (ServicioEnLinea) request.getSession().getAttribute("objPuenteOnline");
			ExpSiniestroBandeja.CambiarEstatusSiniestro(sel.getIdSolicitud(), 2, usuarioSession(request).getLogin(), usuarioSession(request).getNombre() + " " + usuarioSession(request).getApellido(), sel.getObservacion());
			request.getSession().setAttribute("objPuenteOnline", null);
			//enviarCorreo(sel.getTelefono(), codigoBandeja);
		}
		request.setAttribute("siniestro", s);
		request.getSession().setAttribute("siniestroPadre", null);
		return mapping.findForward(FWD_SUCCESS);
	}


}

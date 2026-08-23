package ve.gob.dem.fasdem.action.aps;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class CambioEstatus extends GenericAction {

	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_APS_CAMBIO_ESTATUS);

		Mapa mapa = new Mapa();
		int idSini = Integer.parseInt(request.getParameter("id"));
		int anioSiniestro = getAnioBusqueda(request);
						
		Siniestro s = null;
		PerSiniestro ps = new PerSiniestro();
		PerMotivoEstatus pma = new PerMotivoEstatus();
		MotivoEstatus ma = new MotivoEstatus();
		
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		mapa.setIdSiniestro(idSini);		
		mapa.setAnioSiniestro(anioSiniestro);
		
		try {
			s= ps.search(mapa);
		} catch(PersonalNotFoundException e) {
			am.add(ALERT_VALIDACION, new ActionMessage("general.bean.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);			
		}
		//DynaActionForm dForm = (DynaActionForm) form;
		//dForm.set("estatus", String.valueOf(s.getEstatus().getId()));
		request.setAttribute("anioSiniestro", anioSiniestro);
		request.setAttribute("siniestro", s);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		log.info( "justificar "  +  request.getAttribute("justificar "));
		mapa = getDForm(request, form, ent);
		log.info("getMontoAmparado " + mapa.getMontoAmparado());
		log.info("getIdEstatus " + mapa.getIdEstatus());
		if (mapa.getIdEstatus()!=COD_ESTATUS_EGRESADO){
			mapa.setMontoAmparado(0.0);
		}
		mapa.setFechaLiquidacion(new Date());
		mapa.setIdSiniestro(s.getId());
		ps.updateEstatus(mapa);
		ma.setDescripcion(mapa.getObservacion());
		ma.setIdSiniestro(mapa.getIdSiniestro());
		ma.setIdDependencia(usuarioSession(request).getIdDependencia());
		ma.setIdUsuario(usuarioSession(request).getCedula());
		ma.setIdEstatus(mapa.getIdEstatus());
		ma.setIdProveedor(s.getProveedor().getId());
		//log.info(message);
	 	log.info("----pro---------->"+s.getProveedor().getId());
	 	ma.setFechaInicio(new Date());
		pma.finalizaTodosEstatus(s.getId());
		pma.insert(ma);
		s = ps.search(mapa);
		request.setAttribute("siniestro", s);
		am.add(ALERT_AVISOS, new ActionMessage("update.success"));
		saveMessages(request, am);
		return mapping.findForward(FWD_SUCCESS);
	}
}

package ve.gob.dem.fasdem.action.cartaAval;

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

public class ReversarEstatus extends GenericAction {

	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_REVERSO);

		Mapa mapa = new Mapa();
		int idSini = Integer.parseInt(request.getParameter("idSini"));
		Siniestro s = null;
		PerSiniestro ps = new PerSiniestro();
		PerMotivoEstatus pma = new PerMotivoEstatus();
		MotivoEstatus ma = new MotivoEstatus();
		
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_CARTAAVAL);
		mapa.setIdSiniestro(idSini);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		try{
		s= ps.search(mapa);
		}catch(PersonalNotFoundException e){
			am.add(ALERT_VALIDACION, new ActionMessage("general.bean.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
			
		}
		//DynaActionForm dForm = (DynaActionForm) form;
		//dForm.set("estatus", String.valueOf(s.getEstatus().getId()));
		request.setAttribute("siniestro", s);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		/*if (mapa.getIdEstatus()!=COD_ESTATUS_EGRESADO){
			mapa.setMontoAmparado(0.0);
		}*/
		mapa.setFechaLiquidacion(new Date());
		mapa.setIdSiniestro(s.getId());
		mapa.setIdEstatus(COD_ESTATUS_CARTA_COMPROMISO);
		ma.setIdEstatus(COD_ESTATUS_CARTA_COMPROMISO);

		ma.setDescripcion(mapa.getObservacion());
		ma.setIdSiniestro(mapa.getIdSiniestro());
		ma.setIdDependencia(usuarioSession(request).getIdDependencia());
		ma.setIdUsuario(usuarioSession(request).getCedula());

		ma.setFechaInicio(new Date());
		pma.finalizaTodosEstatus(s.getId());
		ps.updateEstatus(mapa);
		pma.insert(ma);
		s = ps.search(mapa);
		request.setAttribute("siniestro", s);
		request.setAttribute("tipoImpresion",
				COD_TIPO_REPORTE_CARTA_AVAL);
		am.add(ALERT_AVISOS, new ActionMessage("update.success"));
		saveMessages(request, am);
		return mapping.findForward(FWD_SUCCESS);
	}
}

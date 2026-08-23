package ve.gob.dem.fasdem.action.liquidacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class ConsultaLiquidacionMedicinas extends GenericAction {
	Logger log = Logger.getLogger(ConsultaLiquidacionMedicinas.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_MEDICINAS_CONSULTA_LIQUIDACION);
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_MEDICINAS);
		Mapa mapa = new Mapa();
		PerSiniestro op = new PerSiniestro();
		Siniestro Op = null;
		int estatus = 0;
		request.setAttribute("myhref", mapping.getParameter());
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		
		mapa = getDForm(request, form, ent);
		DynaActionForm dForm = (DynaActionForm) form;
		String reemplazo=mapa.getSubCodigo();
		reemplazo=reemplazo.replace("-", "");
		mapa.setSubCodigo(reemplazo);
		try {
			Integer.parseInt(mapa.getSubCodigo().substring(0,2));
		}
		catch(Exception e){
			am.add(ALERT_AVISOS, new ActionMessage(
			"env.general.notfound"));
	saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		int anioSiniestro2=Integer.parseInt(mapa.getSubCodigo().substring(0,2));
		int anioSiniestro1=20;
		String val=String.valueOf(anioSiniestro1)+String.valueOf(anioSiniestro2);
		int anioSiniestro=Integer.parseInt(val);
		mapa.setAnioSiniestro(anioSiniestro);
		
		request.getSession().setAttribute("anioSiniestro", anioSiniestro);
		try {

			Op = op.searchNumero(mapa);
			Op.getId();
			estatus = Op.getEstatus().getId();
			request.setAttribute("Op", Op);

		} catch (PersonalNotFoundException e1) {

			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		dForm.set("tipoProveedor", new Integer(Op.getProveedor().getTipoProveedor().getId()).toString());
		dForm.set("idProveedor", new Integer(Op.getProveedor().getId()).toString());
		dForm.set("proveedor", Op.getProveedor().getDescripcion());
		
		if (COD_ESTATUS_EGRESADO == estatus ) {

			request.setAttribute("Op", Op);
			
		}

		else {

			am.add(ALERT_AVISOS, new ActionMessage(
					"env.general.noestatus"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		return mapping.findForward(FWD_SUCCESS);
	}

}

package ve.gob.dem.fasdem.action.consulta;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class ConsultaSiniestro extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CONSULTA_SINIESTRO);
		PerSiniestro ps = new PerSiniestro();
		DynaActionForm dForm = (DynaActionForm) form;
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String cedula = request.getParameter("cedula");
		String nombres = request.getParameter("nombres");
		String codigo = request.getParameter("codigo");
		String rif = request.getParameter("rif");
		String fecha = request.getParameter("fechaInicio");
		String tipoTramite = request.getParameter("listTipoTramite");
		Mapa m = new Mapa();
		m.setAnioSiniestro(getAnioBusqueda(request));
		m.setCedula(cedula);
		m.setNombres(nombres);
		m.setCodigo(codigo);
		m.setRif(rif);
		if (!"-1".equals(tipoTramite)) {
			m.setIdTipoTramite(tipoTramite);
		} 
		if (!"".equals(rif) && "".equals(fecha)) {
			am.add(ALERT_AVISOS, new ActionMessage("env.general.rifObligatorio"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		if (!"".equals(fecha)) {
			m.setFecha(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
		}
		if (rif != null) {
			try {
				am.add(ALERT_VALIDACION, new ActionMessage("list.limit200"));
				saveMessages(request, am);
				List resultado = ps.searchMultiple(m);
				request.setAttribute("resultado", resultado);
			} catch (Exception e) {
				log.error("este es el error ", e);
				am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
				saveMessages(request, am);
				return mapping.findForward(FWD_INPUT);
			}
		}
		return mapping.findForward(FWD_INPUT);
	}
}

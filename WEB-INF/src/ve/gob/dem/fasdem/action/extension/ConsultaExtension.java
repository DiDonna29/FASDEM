package ve.gob.dem.fasdem.action.extension;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class ConsultaExtension extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCA_BENEFICIARIO_CONSULTA);
		PerSiniestro ps = new PerSiniestro();
		
	
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String cedula = request.getParameter("cedula");
		String nombres = request.getParameter("nombres");
		String codigo = request.getParameter("codigo");
		
		if (cedula == null) {
			cedula = "";
		}
		if (nombres == null) {
			nombres = "";
		}
		if (codigo == null) {
			codigo = "";
		}
		if (("".equals(cedula) && "".equals(nombres)) && "".equals(codigo)) {
			am.add(ALERT_VALIDACION, new ActionMessage("env.criterio.requerido"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		} else {
			request.setAttribute("cedula", cedula);
			request.setAttribute("nombres", nombres);
			request.setAttribute("codigo", codigo);
		}
		Mapa m = new Mapa();
		m.setCedula(cedula);
		m.setNombres(nombres);
		m.setCodigo(codigo);
		m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioBusqueda")));
		try {
			List resultado = ps.searchMultipleConsulta(m);
			request.setAttribute("resultado", resultado);
		} catch (Exception e) {
			am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		
		return mapping.findForward(FWD_INPUT);
	}
}

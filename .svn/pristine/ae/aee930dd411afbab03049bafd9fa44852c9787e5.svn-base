package ve.gob.dem.fasdem.action.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class ProcesaReporteReLiq extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(ProcesaReporteReLiq.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		Date fecha = new Date();
		SimpleDateFormat dateformatMes = new SimpleDateFormat("MM");
		SimpleDateFormat dateformatAnio = new SimpleDateFormat("yyyy");
		int mesAct = Integer.valueOf(dateformatMes.format(fecha));
		int anioAct = Integer.valueOf(dateformatAnio.format(fecha));
		ArrayList listaAnio = new ArrayList(0);
		ArrayList listaMeses = new ArrayList(0);
		listaMeses.add(Integer.valueOf("1"));
		listaMeses.add("Enero");
		listaMeses.add(Integer.valueOf("2"));
		listaMeses.add("Febrero");
		listaMeses.add(Integer.valueOf("3"));
		listaMeses.add("Marzo");
		listaMeses.add(Integer.valueOf("4"));
		listaMeses.add("Abril");
		listaMeses.add(Integer.valueOf("5"));
		listaMeses.add("Mayo");
		listaMeses.add(Integer.valueOf("6"));
		listaMeses.add("Junio");
		listaMeses.add(Integer.valueOf("7"));
		listaMeses.add("Julio");
		listaMeses.add(Integer.valueOf("8"));
		listaMeses.add("Agosto");
		listaMeses.add(Integer.valueOf("9"));
		listaMeses.add("Septiembre");
		listaMeses.add(Integer.valueOf("10"));
		listaMeses.add("Octubre");
		listaMeses.add(Integer.valueOf("11"));
		listaMeses.add("Noviembre");
		listaMeses.add(Integer.valueOf("12"));
		listaMeses.add("Diciembre");
		for (int i = 2010; i != anioAct + 1; i++) {
			listaAnio.add(i);
		}
		request.setAttribute("listaAnio", listaAnio);
		request.setAttribute("listaMeses", listaMeses);
		/*
		 * request.setAttribute("listaTipoTramite",ExpTipoTramite.BuscarLista());
		 */
		request.setAttribute("anio", anioAct);
		request.setAttribute("mes", mesAct);
		request.setAttribute("ced", usuarioSession(request).getLogin());
		return mapping.findForward("input");
	}
}
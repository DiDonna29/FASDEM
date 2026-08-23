package ve.gob.dem.fasdem.action.administradores;

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
import ve.gob.dem.fasdem.bean.TipoGasto;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerTipoGasto;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class AdminTipoGasto extends GenericAction {
	Logger log = Logger.getLogger(AdminTipoGasto.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent1 = new Entorno(Entorno.MOD_EDITA_TIPO_GASTO);
		Entorno ent2 = new Entorno(Entorno.MOD_INGRESA_TIPO_GASTO);
		DynaActionForm dForm = (DynaActionForm) form;
		Mapa mapa = new Mapa();
		PerTipoGasto p = new PerTipoGasto();
		TipoGasto P = null;
		if ("editar".equals(request.getParameter("accion"))) {
			try {
				log.info("valido 2 do entorno");
				validarAction(request, form, ent2, am, this.getClass());
			} catch (PersonalNotFillItems e) {
				log.info("entre en el catch");
			}
			mapa = getDForm(request, form, ent2);
			log.info("MAPA: " + mapa);
			log.info("idtipoGasto: " + request.getParameter("idtipoGasto"));
			mapa.setId(Integer.parseInt(request.getParameter("idtipoGasto")));
			p.updateTipoGasto(mapa);
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("env.general.sms"));
			saveMessages(request, am);
			P = p.search(mapa.getId());
			setEntorno(request, form, ent1);
			dForm.set("idtipoGasto", P.getId());
		} else {
			try {
				validarAction(request, form, ent1, am, this.getClass());
				request.setAttribute("myhref", mapping.getParameter());
				log.info("entre en el try");
				log.info("Construyes el otro entorno");
				// Construyes el otro entorno
				mapa = getDForm(request, form, ent1);
				mapa.setIdTipoGasto(Integer.parseInt(request.getParameter("tipoGasto")));
				mapa.setId(mapa.getIdTipoGasto());
				log.info("MAPA: " + mapa);
				log.info("tipoGasto " + request.getParameter("tipoGasto"));
				log.info("Obtiene ID: " + mapa.getIdTipoGasto());
				P = p.search(mapa.getId());
				request.setAttribute("idtipoGasto", P.getId());
				dForm.set("descripcion", P.getDescripcion());
				setEntorno(request, form, ent2);
			} catch (PersonalNotFillItems e) {
				log.info("entre en el catch");
			}
		}
		return mapping.findForward(FWD_INPUT);
	}
}

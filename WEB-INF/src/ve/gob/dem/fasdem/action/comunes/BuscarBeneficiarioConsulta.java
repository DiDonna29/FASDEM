package ve.gob.dem.fasdem.action.comunes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarBeneficiarioConsulta extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_BUSCA_BENEFICIARIO_CONSULTA);
		Mapa mapa = new Mapa();
		
		log.info("prueba nueva");
		request.getSession().setAttribute("bene", null);
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setCitaPostOperatorio(false);
		PerPersona pb = new PerPersona();
		Persona bene = null;
		try {
			bene = pb.buscar(mapa.getCedula());
			if (TIPO_PERSONA_BENEF.equals(bene.getTipoPersona())) {
				bene = pb.buscar(bene.getCedulaTitular());
			}
		} catch (PersonalNotFoundException e1) {
			am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
			saveMessages(request, am);
		} catch (Exception e) {
			try {
				bene = pb.buscarPorTitular(mapa.getCedula());
			} catch (Exception e1) {
				throw e1;
			}
		}
		
		request.getSession().setAttribute("bene", bene);
		return mapping.findForward(FWD_INPUT);
	}
}

package ve.gob.dem.fasdem.action.comunes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarSiniestrosConsulta extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		String id = "";
		PerPersona pp = new PerPersona();
		Persona tmp = new Persona();
		Persona t = new Persona();
		PerSiniestro ps = new PerSiniestro();
		List sin = new ArrayList();
		Mapa mapa = new Mapa();
		try {
			validarAction(request, form, ent, am, this.getClass());
			request.setAttribute("myhref", mapping.getParameter());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		
		try {
			id = request.getParameter("id");
			tmp = pp.buscar(id);
		} catch (NumberFormatException e) {
			log.error("error", e);
			return mapping.findForward(FWD_INPUT);
		}catch(Exception e){
			tmp = pp.buscarPorTitular(id);
		}
		
	
		
		if ("B".equals(tmp.getTipoPersona())){
			try{
			t = pp.buscar(tmp.getCedulaTitular());
			t.setBeneficiario (tmp);
			}catch(Exception e){
				t = pp.buscarPorTitular(tmp.getCedulaTitular());
				t.setBeneficiario (tmp);
			}
		}else{
			t = tmp;
			t.setBeneficiario (tmp);
		}
		try {
			sin = ps.searchCedulaBeneficiario(mapa);
			
			
		} catch (PersonalNotFoundException e) {
			log.info("notFound ", e);
		}
		mapa.setCedulaBeneficiario(t.getBeneficiario().getCedula());
		mapa.setCedula(t.getCedula());
	
		
	
		request.getSession().setAttribute(KEY_TITULAR, t);
		request.setAttribute(KEY_SINIESTROS, sin);
		//request.setAttribute(KEY_TITULAR, t);

		return mapping.findForward(FWD_INPUT);
	}
}

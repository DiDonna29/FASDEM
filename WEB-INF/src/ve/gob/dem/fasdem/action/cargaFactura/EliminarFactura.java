/**
 * 02/03/2011 16:25:35
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.cargaFactura;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class EliminarFactura extends GenericAction {
	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARGA_FACTURA_BUSCASINIESTRO);
		Mapa mapa = new Mapa();
		log.info("id_siniestro="+request.getParameter("idSini"));
		log.info("id_factura="+request.getParameter("idFact"));
		int idSini = Integer.parseInt(request.getParameter("idSini"));
		int idFact = Integer.parseInt(request.getParameter("idFact"));
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			log.info("PersonalNotFillItems: "+e);
			return mapping.findForward(FWD_INPUT);
		}
		Siniestro s = null;
		PerSiniestro ps = new PerSiniestro();
		PerFactura pf = new PerFactura();
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		mapa.setIdSiniestro(idSini);
		mapa.setIdFactura(idFact);
		mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
		s = ps.search(mapa);
		List ff = null;
		try {
		ff = pf.listSearchDetalle(mapa);
		} catch (PersonalNotFoundException e) { 
			ff = null;
		}
		request.setAttribute("siniestro", s);
		if (ff!= null && ff.size()!=0) {

			am.add(ALERT_AVISOS, new ActionMessage("env.factura.condetalle"));
			saveMessages(request, am);
		}else{
			
			try {
				pf.delFactura(mapa);
				incluirTraza(TR_ADMRECEPCIONFACT_ELIMINAR, String.valueOf(idFact), "Eliminar Factura", usuarioSession(request));
			} catch (Exception e) { 
				log.error("error", e);
			}
		}
		try {
			Mapa m = new Mapa();
			m.setIdSiniestro(s.getId());
			m.setAnioSiniestro(getAnioBusqueda(request));
			request.setAttribute("facturas", pf.listSearchIdSiniestro(m));
		} catch (PersonalNotFoundException e) {
			request.setAttribute("facturas", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("facturas", null);
		}
		ent = new Entorno(Entorno.MOD_APS_LIQUIDAR);
		request.setAttribute(KEY_ENTORNO, ent);
		request.setAttribute("form_action", "/security/carga/cargaFacturas.do");
		return mapping.findForward(FWD_INPUT);
	}
}

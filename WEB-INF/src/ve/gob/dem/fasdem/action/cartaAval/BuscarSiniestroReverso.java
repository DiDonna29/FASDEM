package ve.gob.dem.fasdem.action.cartaAval;

import java.io.Serializable;
import java.util.ArrayList;
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
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class BuscarSiniestroReverso extends GenericAction implements Serializable {
	/**
	 * figumare
	 */
	private static final long serialVersionUID = 5861083469142874907L;

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
		PerSiniestro ps = new PerSiniestro();
		String reemplazo = "";
		Mapa mapa = new Mapa();
		List listado = new ArrayList();
		ent = new Entorno(Entorno.MOD_CARTA_AVAL_CONSULTA);
		
		setEntorno(request, form, ent);
		// valido que haya colocado los criterios de b�squeda requeridos
		try {
			validarAction(request, form, ent, am, this.getClass());
			mapa = getDForm(request, form, ent);
			mapa.setIdTipoTramite(COD_TIPO_TRAMITE_CARTAAVAL);
			mapa.setIdEstatus(COD_ESTATUS_INGRESADO);
			if (mapa.getCodigo() != null) {
				reemplazo = mapa.getCodigo();
				reemplazo = reemplazo.replace("-", "");
				mapa.setCodigo(reemplazo);

			}
			// Busca el listado de siniestros
			try {
				listado = ps.searchMultiple(mapa);
				request.setAttribute("resultado", listado);
				return mapping.findForward(FWD_INPUT);
			} catch (PersonalNotFoundException e) {
				am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
				saveMessages(request, am);
			}
		} catch (PersonalNotFillItems e) {
		}
		return mapping.findForward(FWD_INPUT);
	}
}

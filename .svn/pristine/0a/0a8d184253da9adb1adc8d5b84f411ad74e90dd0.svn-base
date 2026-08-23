package ve.gob.dem.fasdem.action.comunes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

public class NuevoFacturas extends GenericAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_CARGA_FACTURA);
		request.setAttribute(KEY_TIPO_COBERTURA, TIPO_TRAMITE_REEMBOLSO);
		Mapa mapa = new Mapa();
		PerFactura perFac = new PerFactura();
		Factura   fact = new Factura();
		int idFactura;
		Date fechahoy = new Date();
		Calendar f = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		f.setTime(fechahoy);
		formato.format(f.getTime());

		log.info("estoy en nuevo factura" );
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}

		mapa = getDForm(request, form, ent);
		
		log.info("marinellys"+mapa );
		idFactura=perFac.insert(mapa);
		log.info("id_fact"+ idFactura);
		fact=perFac.search(mapa);
		request.setAttribute("fact", fact);
		return mapping.findForward(FWD_INPUT);
		/*mapa.setNumeroFactura(fact.getNumeroFactura());
		mapa.setControlFactura(fact.getControlFactura());
		mapa.setNombres(persona.getNombres());
		mapa.setApellidos(persona.getApellidos());
		mapa.setNombresBeneficiario(persona.getNombres());
		mapa.setApellidosBeneficiario(persona.getApellidos());
		mapa.setParentesco(persona.getParentesco());
		mapa.setSexo(persona.getSexo());
		mapa.setSexoBeneficiario(persona.getSexoBeneficiario());
		mapa.setEdad(persona.getEdad());
		mapa.setFechaFactura(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
		mapa.setFechaRecepcionFactura(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
		
		try {

			idSiniestro = perSiniestro.insert(mapa);
			mapa.setId(idSiniestro);
			Siniestro s = perSiniestro.search(mapa.getId());
			request.setAttribute("s", s);
		

			return mapping.findForward(FWD_SUCCESS);

		} catch (Exception e) {
			
			return mapping.findForward(FWD_SUCCESS);

		}
*/
	}
}

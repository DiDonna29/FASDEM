package ve.gob.dem.fasdem.action.extension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.extension.ExpExtension;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class DetalleSiniestro extends GenericAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

		//DetalleFactura detalleFactura = new DetalleFactura();

		PerSiniestro ps = new PerSiniestro();
		Mapa mapa = new Mapa();

		//List listDetalle = new ArrayList();
		EstatusTipoTramite estTipTra = new EstatusTipoTramite();
		PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
		String idSini = "";
		Siniestro s = null; 
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		
		
		idSini = request.getParameter("idSini");
		if (idSini == null) {
			idSini = "";
		}
		if ("".equals(idSini)) {
			return mapping.findForward(FWD_INPUT);
		}
		
	
		try {
			mapa.setIdSiniestro(Integer.parseInt(idSini));
			mapa.setAnioSiniestro(getAnioBusqueda(request));
			s = ps.search(mapa);
			
			if (s.getTipoTramite().getId()==4){request.setAttribute("fechaFactura", s.getFechaNotificacion());} 
			if (s.getTipoTramite().getId()==1){request.setAttribute("fechaFactura", s.getFechaIngreso());} 
			if (s.getTipoTramite().getId()==2){request.setAttribute("fechaFactura", s.getFechaIngreso());} 
			if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_CARTAAVAL) {
				// Verifico si según su estatus genera reporte
				mapa.setIdEstatus(s.getEstatus().getId());
				mapa.setIdTipoTramite(s.getTipoTramite().getId());
				try {
					estTipTra = perEstTipTra.searchByEstatusTipoTramite(mapa);
					if (estTipTra.getReporte() != null) {
						request.setAttribute("tipoImpresion", COD_TIPO_REPORTE_CARTA_AVAL);
					}
				} catch (PersonalNotFoundException e) {
				}
			}
			request.setAttribute("siniestro", s);
		} catch (Exception e) {
		}
		PerEstatus perEst = new PerEstatus();
		Estatus est = new Estatus();
		try {
			est = perEst.buscar(s.getEstatus().getId());
			if (est.isJustificacion()) {
				mapa.setIdEstatus(est.getId());
				mapa.setIdSiniestro(s.getId());
				motEst.setDescripcion(mapa.getJustificacion());
				motEst.setIdSiniestro(s.getId());
				motEst.setIdDependencia(usuarioSession(request).getIdDependencia());
				motEst.setIdUsuario(usuarioSession(request).getCedula());
				motEst.setIdEstatus(mapa.getIdEstatus());
				motEst = perMotEst.searchByEstatus(mapa);
				request.setAttribute("motEst", motEst);
				log.info("BUSCANDO MI ESTATUS QUE NO ENCUENTRO      " + motEst.getDescripcion());
				// ******INSERTAR TRAZA
			}
		} catch (Exception e) {
		}
		request.setAttribute("cod_sin", s.getAniomesCodigo() + "-" + s.getCodigo() + "-" + s.getSubCodigo() );
		log.info("AÑOOOOOOO DEL SINIESTRO   " + s.getAnioSiniestro() );
		request.setAttribute("id_sin", Integer.parseInt(idSini));
		request.setAttribute("listAutorizacion", ExpExtension.BuscarListaPorSiniestro(s.getId()));
		request.setAttribute("a_sin", s.getAnioSiniestro());
		
		return mapping.findForward(FWD_INPUT);
	}
}

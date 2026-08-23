package ve.gob.dem.fasdem.action.aps;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class DetalleSiniestro extends GenericAction {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Factura factura;
		//DetalleFactura detalleFactura = new DetalleFactura();
		PerFactura perFactura = new PerFactura();
		PerSiniestro ps = new PerSiniestro();
		Mapa mapa = new Mapa();
		List listFactura = new ArrayList();
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
		//anioSini = request.getParameter("anioSini");
		if (idSini == null) {
			idSini = "";
		}
		if ("".equals(idSini)) {
			return mapping.findForward(FWD_INPUT);
		}
		
	
		try {
			mapa.setIdSiniestro(Integer.parseInt(idSini));
			//mapa.setAnioSiniestro(Integer.parseInt(anioSini));
			//ESTO NO PUEDE IR AKI PQ SE CONSULTA CON EL ANÑO DEL SINIESTRO SELECCIONADOOOOOO
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
			if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_REEMBOLSO) {
				// Verifico si según su estatus genera reporte
				mapa.setIdEstatus(s.getEstatus().getId());
				mapa.setIdTipoTramite(s.getTipoTramite().getId());
				try {
					estTipTra = perEstTipTra.searchByEstatusTipoTramite(mapa);
					if (estTipTra.getReporte() != null) {
						request.setAttribute("tipoImpresion", COD_TIPO_REPORTE_REEMBOLSO);
					}
				} catch (PersonalNotFoundException e) {
				}
			}
			request.setAttribute("siniestro", s);
		} catch (Exception e) {
			log.error("error", e);
		}
		PerEstatus perEst = new PerEstatus();
		Estatus est = new Estatus();
		try {
			est = perEst.
			buscar(s.
					getEstatus().
					getId());
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
				
			
				// ******INSERTAR TRAZA
			}
		} catch (Exception e) {
			log.error("error", e);
		}
		if (s.getEstatus().getId()==4){
			try {
			mapa.setIdEstatus(est.getId());
			mapa.setIdSiniestro(s.getId());
			motEst = perMotEst.searchByEstatus(mapa);
			request.setAttribute("motEst", motEst);
			}
			catch(Exception e){
				motEst= new MotivoEstatus();
			}
			log.info("eskiekekekke "+motEst.getIdUsuario());
		}
		try {
			mapa.setIdSiniestro(s.getId());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			listFactura = perFactura.listSearchIdSiniestro(mapa);
			for (int r = 0; r <= listFactura.size(); r++) {
				factura = (Factura) listFactura.get(r);
				factura.getId();
				if (factura.getPreOrden() != null && !"".equals(factura.getPreOrden())) {
					try {
						factura.setEstatusPreOrden(ExpPreOrdenPago.estatusPreOrden(factura.getPreOrden()));
					} catch (Exception e) {
						log.error("error", e);
						factura.setEstatusPreOrden("");
					}
				}
				if (s.getTipoTramite().getId()==6){request.setAttribute("fechaFactura", factura.getFechaFactura());} 
				mapa.setIdFactura(factura.getId());
				mapa.setAnioSiniestro(s.getAnioSiniestro());


				listFactura.set(r, factura);
			}
		} catch (Exception e1) {
			factura = new Factura();
			request.setAttribute("factura", factura);
			request.setAttribute("listFactura", listFactura);
		}
		incluirTraza(TR_CONSULTA_SINIESTRO, String.valueOf(idSini), "Consultar Siniestro", usuarioSession(request));
		return mapping.findForward(FWD_INPUT);
	}
}

/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.extension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Autorizacion;
import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.extension.ExpAutoridades;
import ve.gob.dem.fasdem.exp.extension.ExpExtension;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class NuevaExtension extends GenericAction {
	static protected Logger log = Logger.getLogger(NuevaExtension.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		int anio_general=0;
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String accion = request.getParameter("accionPago");
		log.info("ACCION " + accion);
		if (accion == null) // INGRESO A LA PAGINA POR PRIMERA VEZ
		{
			return mapping.findForward("H1");
		} else {
			if (accion.equals("1")) {
				request.setAttribute("codigo", request.getParameter("cod"));
				request.setAttribute("id_siniestro", request.getParameter("id_s"));
				request.setAttribute("listaAuto", ExpAutoridades.BuscarLista());
				
				log.info("AÑOSSSSSSSSSSSSSSSSSSSSSS  SINIESTRO EN EL ACTION " + request.getParameter("a_sin2") );
				request.setAttribute("a_sin", request.getParameter("a_sin2"));
				return mapping.findForward("H1");
			}
			if (accion.equals("2") || accion.equals("3")) {
				String idSini = "";
				if (accion.equals("2")) {
					Autorizacion auto = new Autorizacion();
					auto.setId_autoridad(Integer.parseInt(request.getParameter("autoridad")));
					auto.setId_siniestro(Integer.parseInt(request.getParameter("sin")));
					auto.setMonto_maximo_autorizado(Double.parseDouble(request.getParameter("monto")));
					auto.setObservaciones(request.getParameter("observacion"));
					ExpExtension.crearAutorizacion(auto);
					incluirTraza(TR_EXTENSION_CREAR_EXTENSION_COBERTURA, request.getParameter("sin"), "Siniestro Autorizado", usuarioSession(request));
					idSini = request.getParameter("sin");
					anio_general=(Integer.parseInt(request.getParameter("sin1").toString()));
				}
				if (accion.equals("3"))/*Eliminar*/ {
					ExpExtension.eliminarAutorizacion(Integer.parseInt(request.getParameter("id_auto")));
					incluirTraza(TR_EXTENSION_ELIMINAR_REGISTRO_DE_EXTENSION, request.getParameter("id_auto"), "Extension Eliminada", usuarioSession(request));
					idSini = request.getParameter("id_s");
					anio_general=(Integer.parseInt(request.getParameter("a_sin2").toString()));
					
				}
				PerSiniestro ps = new PerSiniestro();
				Mapa mapa = new Mapa();
				EstatusTipoTramite estTipTra = new EstatusTipoTramite();
				PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
				Siniestro s = null;
				PerMotivoEstatus perMotEst = new PerMotivoEstatus();
				MotivoEstatus motEst = new MotivoEstatus();
				if (idSini == null) {
					idSini = "";
				}
				if ("".equals(idSini)) {
					return mapping.findForward(FWD_INPUT);
				}
				try {
					mapa.setIdSiniestro(Integer.parseInt(idSini));
					mapa.setAnioSiniestro(anio_general);
					
					
					s = ps.search(mapa);
					if (s.getTipoTramite().getId() == 4) {
						request.setAttribute("fechaFactura", s.getFechaNotificacion());
					}
					if (s.getTipoTramite().getId() == 1) {
						request.setAttribute("fechaFactura", s.getFechaIngreso());
					}
					if (s.getTipoTramite().getId() == 2) {
						request.setAttribute("fechaFactura", s.getFechaIngreso());
					}
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
					log.info("",e);
				}
				request.setAttribute("cod_sin", s.getAniomesCodigo() + "-" + s.getCodigo() + "-" + s.getSubCodigo());
				request.setAttribute("id_sin", Integer.parseInt(idSini));
				request.setAttribute("listAutorizacion", ExpExtension.BuscarListaPorSiniestro(s.getId()));
				request.setAttribute("a_sin", anio_general);
				return mapping.findForward(FWD_INPUT);
			}
			return mapping.findForward("H1");
		}
	}
}

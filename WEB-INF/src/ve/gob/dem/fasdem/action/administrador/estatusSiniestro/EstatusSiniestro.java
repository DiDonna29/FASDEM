/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.administrador.estatusSiniestro;

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
import ve.gob.dem.fasdem.exp.administrador.ExpSiniestro;
import ve.gob.dem.fasdem.exp.pagos.ExpDetalleFacturaPago;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class EstatusSiniestro extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(EstatusSiniestro.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExpSiniestro.modificarEstatus(request.getParameter("idsiniestro"), request.getParameter("anio"));
		incluirTraza(TR_ADMESTATUS_CAMBIAR_ESTATUS_SINIESTRO, request.getParameter("idsiniestro"), "ID DEL SINIESTRO AL CUAL SE LE CAMBIO ESTATUS", usuarioSession(request));
		return mapping.findForward("H1");
	}
}

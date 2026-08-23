/**07/05/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.maqueta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class MaquetaAction extends GenericAction {

    /*
     * (non-Javadoc)
     * 
     * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
     * ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	int nodo = Integer.parseInt(request.getParameter("nodo"));
	switch (nodo) {
	case 1:
	    return mapping.findForward("aps_carga");
	case 2:
	    return mapping.findForward("aps_consulta");
	case 3:
	    return mapping.findForward("aps_editar");
	case 4:
	    return mapping.findForward("medicinas_carga");
	case 5:
	    return mapping.findForward("medicinas_consulta");
	case 6:
	    return mapping.findForward("medicinas_editar");
	case 7:
	    return mapping.findForward("pago_carga");
	case 8:
	    return mapping.findForward("pago_consulta");
	case 9:
	    return mapping.findForward("pago_editar");
	case 10:
	    return mapping.findForward("reembolso_consulta");
	case 11:
	    return mapping.findForward("reembolso_crear");
	case 12:
	    return mapping.findForward("reembolso_editar");
	default:
	    return mapping.findForward("input_maqueta");
	}
    }
}

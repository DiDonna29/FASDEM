package ve.gob.dem.fasdem.action.administrador.cuenta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpPersona;
import ve.gob.dem.fasdem.exp.administrador.ExpBanco;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 */
public class adminCuentaBeneficiario extends GenericAction {
	/*
	 * (non-Javadoc)
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(adminCuentaBeneficiario.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String accion = request.getParameter("busca");
		log.info("cuenta " + accion);
		log.info("ESTA PRUEBA JC:");
		if (accion == null || "".equals(accion)) { // INGRESO A LA PAGINA POR PRIMERA VEZ
			//request.setAttribute("listaTipoProv",ExpTipoProveedor.BuscarLista());
			request.setAttribute("resp", "paso0");
			return mapping.findForward("input");
		} else {
			request.setAttribute("busca", request.getParameter("busca"));
			if (!ExpPersona.ExistePersonaCuentaSigefirrhh(request.getParameter("busca"))) {
//				if (ExpPersona.ExistePersona(request.getParameter("busca"), usuarioSession(request).getId_estado())) {// BUSCO PROVEEDOR POR RIF
					if (ExpCuenta.ExisteCuentaBeneficiario(request.getParameter("busca"))) {
						request.setAttribute("persona", ExpPersona.buscarporCedula(request.getParameter("busca")));
						request.setAttribute("cuenta", ExpCuenta.buscarCuentaBeneficiario(request.getParameter("busca")));						
						request.setAttribute("listaBanco", ExpBanco.BuscarLista());
						request.setAttribute("funcion", "1");
						return mapping.findForward("H1");
					} else {
						request.setAttribute("persona", ExpPersona.buscarporCedula(request.getParameter("busca")));
						request.setAttribute("descripcion", request.getParameter("busca"));
						request.setAttribute("listaBanco", ExpBanco.BuscarLista());
						request.setAttribute("funcion", "2");
						return mapping.findForward("H1");
					}
//				} else {
//					request.setAttribute("mensaje", "Esta Persona no se encuentra registrada como beneficiario de FASDEM o No tiene permiso para modificar cuentas en el estado al cual pertenece la misma");
//				}
			} else {
				request.setAttribute("mensaje", "Para Modificar el número de cuenta "+ExpCuenta.buscarCuentaBeneficiario(request.getParameter("busca")).getCuenta()+" de este funcionario dirijase al personal de Nomina (Sigefirrhh) de esa region");
			}
			request.setAttribute("resp", "paso" + accion + "" + request.getParameter("busca"));
			return mapping.findForward("input");
		}
	}
}
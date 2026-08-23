package ve.gob.dem.fasdem.action.administrador.proveedores;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpCiudad;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpEstado;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramite;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoTramitePortal;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class modificarProveedor extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(modificarProveedor.class);

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String accion = request.getParameter("modifica");
		log.info("MODIFICA JC: " + request.getParameter("modifica"));
		// log.info("ESTA PRUEBA JC:");
		if ("1".equals(request.getParameter("buscaCiudad"))) {
			request.setAttribute("listaCiudad", ExpCiudad.ListarBuscarporEstado(Integer.valueOf(request.getParameter("estado"))));
			Clinica proveedor = ExpClinica.BuscarPorid(Integer.valueOf(request.getParameter("crea")));
			request.setAttribute("rif", proveedor.getRif());
			request.setAttribute("descripcion", request.getParameter("descripcion"));
			request.setAttribute("direccion", request.getParameter("direccion"));
			request.setAttribute("telefono", request.getParameter("telefono"));
			request.setAttribute("contacto", request.getParameter("contacto"));
			request.setAttribute("estado", Integer.valueOf(request.getParameter("estado")));
			request.setAttribute("activo", request.getParameter("activo"));
			request.setAttribute("servicio", request.getParameter("servicio"));
			request.setAttribute("ciudad", Integer.valueOf("-1"));
			request.setAttribute("id_proveedor", proveedor.getId());
			request.setAttribute("tipoProveedor", Integer.valueOf(request.getParameter("tipoProveedor")));
			request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
			request.setAttribute("listaTipoTramitePortal", ExpTipoTramitePortal.BuscarLista());
			try {
				request.setAttribute("listTipoTra", ExpClinica.buscarProvTipoProvID(proveedor.getId()));
			} catch (PersonalNotFoundException e) {
				log.info("error", e);
			}
			request.setAttribute("listTipoTraPort", ExpClinica.buscarProvTipoServPortal(proveedor.getId()));
			request.setAttribute("razonable", Boolean.valueOf(request.getParameter("razonable")));
			request.setAttribute("activo", Boolean.valueOf(request.getParameter("activo")));
			request.setAttribute("listaEstado", ExpEstado.ListarBuscar());
			request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
			return mapping.findForward("input");
		}
		if (accion == null || "".equals(accion)) // INGRESO A LA PAGINA POR
													// PRIMERA VEZ
		{
			Clinica proveedor = ExpClinica.BuscarPorid(Integer.valueOf(request.getParameter("crea")));
			request.setAttribute("rif", proveedor.getRif());
			request.setAttribute("descripcion", proveedor.getNombre());
			request.setAttribute("direccion", proveedor.getDireccion());
			request.setAttribute("telefono", proveedor.getTelefono());
			request.setAttribute("contacto", proveedor.getContacto());
			request.setAttribute("estado", proveedor.getId_estado());
			request.setAttribute("activo", proveedor.getIsActivo());
			request.setAttribute("servicio", proveedor.getServicio());
			log.info("id_estado: " + proveedor.getId_estado());
			if (proveedor.getId_estado() != 0) {
				request.setAttribute("listaCiudad", ExpCiudad.ListarBuscarporEstado(proveedor.getId_estado()));
			} else {
				request.setAttribute("listaCiudad", new ArrayList());
			}
			request.setAttribute("ciudad", proveedor.getId_ciudad());
			request.setAttribute("id_proveedor", proveedor.getId());
			request.setAttribute("tipoProveedor", proveedor.getTipoProveedor().getId());
			request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
			request.setAttribute("listaTipoTramitePortal", ExpTipoTramitePortal.BuscarLista());
			try {
				request.setAttribute("listTipoTra", ExpClinica.buscarProvTipoProvID(proveedor.getId()));
			} catch (PersonalNotFoundException e) {
				request.setAttribute("listTipoTra", new ArrayList());
			}
			request.setAttribute("listTipoTraPort", ExpClinica.buscarProvTipoServPortal(proveedor.getId()));
			request.setAttribute("razonable", proveedor.getRazonable());
			request.setAttribute("tipoProveedor", proveedor.getTipoProveedor().getId());
			request.setAttribute("listaEstado", ExpEstado.ListarBuscar());
			request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
			request.setAttribute("listaCiudad", ExpCiudad.ListarBuscar());
			return mapping.findForward("input");
		}
		if (accion.equals("2")) {
			if (request.getParameterValues("listTipoTra") == null) {
				Clinica proveedor = ExpClinica.BuscarPorid(Integer.valueOf(request.getParameter("id_proveedor")));
				request.setAttribute("rif", proveedor.getRif());
				request.setAttribute("descripcion", proveedor.getNombre());
				request.setAttribute("direccion", proveedor.getDireccion());
				request.setAttribute("telefono", proveedor.getTelefono());
				request.setAttribute("contacto", proveedor.getContacto());
				request.setAttribute("estado", proveedor.getId_estado());
				request.setAttribute("activo", proveedor.getIsActivo());
				request.setAttribute("servicio", proveedor.getServicio());
				request.setAttribute("ciudad", proveedor.getId_ciudad());
				request.setAttribute("listaTipoTramitePortal", ExpTipoTramitePortal.BuscarLista());
				request.setAttribute("listTipoTraPort", ExpClinica.buscarProvTipoServPortal(proveedor.getId()));
				try {
					request.setAttribute("listaTipoTramite", ExpClinica.buscarProvTipoProvID(proveedor.getId()));
				} catch (Exception e) {
					request.setAttribute("listaTipoTramite", ExpTipoTramite.BuscarLista());
					log.error("error", e);
				}
				request.setAttribute("id_proveedor", proveedor.getId());
				request.setAttribute("listaEstado", ExpEstado.ListarBuscar());
				request.setAttribute("listaTipoProveedor", ExpTipoProveedor.BuscarLista());
				request.setAttribute("razonable", proveedor.getRazonable());
				request.setAttribute("mensaje", "Favor seleccionar al menos un tipo de tramite.");
				request.setAttribute("listaCiudad", ExpCiudad.ListarBuscarporEstado(Integer.valueOf(request.getParameter("estado"))));
				return mapping.findForward("input");
			}
			Clinica prov = new Clinica();
			prov.setRif(request.getParameter("rif"));
			prov.setNombre(request.getParameter("descripcion"));
			prov.setDireccion(request.getParameter("direccion"));
			prov.setServicio(request.getParameter("servicio"));
			prov.setTelefono(request.getParameter("telefono"));
			prov.setContacto(request.getParameter("contacto"));
			prov.setIsActivo(Boolean.valueOf(request.getParameter("activo")));
			prov.setId_estado(Integer.valueOf(request.getParameter("estado")));
			prov.setId_ciudad(Integer.valueOf(request.getParameter("ciudad")));
			prov.setTipoProveedor(ExpTipoProveedor.buscarporID(Integer.valueOf(request.getParameter("tipoProveedor"))));
			prov.setRazonable(Boolean.valueOf(request.getParameter("razonable")));
			log.info("paso a modificar");
			String[] listTipoTra = request.getParameterValues("listTipoTra");
			String[] listTipoTraPort = request.getParameterValues("listTipoTraPort");
			prov.setId(Integer.valueOf(request.getParameter("id_proveedor")));
			ExpClinica.modificarProveedor(prov);
			incluirTraza(TR_ADMPROVEEDOR_MODIFICAR, request.getParameter("id_proveedor"), "ID_PROVEEDOR MODIFICADO", usuarioSession(request));
			try {
				ExpClinica.crearProveedorTipoProv(Integer.valueOf(request.getParameter("id_proveedor")), listTipoTra);
			} catch (Exception e) {
				log.error("error", e);
			}
			String listra = "";
			for (int j = 0; j != listTipoTra.length; j++) {
				if (j == 0) {
					listra = listTipoTra[j];
				} else {
					listra = listra + "," + listTipoTra[j];
				}
			}
			incluirTraza(TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITE, request.getParameter("id_proveedor"), "ASOCIO LOS SERVICIOS ID_SERVICIOS=" + listTipoTra.toString(), usuarioSession(request));
			if (request.getParameterValues("listTipoTraPort") != null) {
				String listraPort = "";
				for (int j = 0; j != listTipoTraPort.length; j++) {
					if (j == 0) {
						listraPort = listTipoTraPort[j];
					} else {
						listraPort = listraPort + "," + listTipoTraPort[j];
					}
				}
				log.info("LISTA: " + listraPort);
				ExpClinica.modificarTipoTramitePortal(Integer.valueOf(request.getParameter("id_proveedor")), listTipoTraPort);
				incluirTraza(TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITEPORTAL, request.getParameter("id_proveedor"), "ASOCIO LOS SERVICIOS PORTAL ID_SERVICIOS=" + listraPort.toString(), usuarioSession(request));
			} else {
				ExpClinica.modificarTipoTramitePortalTodos(Integer.valueOf(request.getParameter("id_proveedor")));
				incluirTraza(TR_ADMPROVEEDOR_DESASOCIAR_A_TIPOTRAMITEPORTAL, request.getParameter("id_proveedor"), "ASOCIO LOS SERVICIOS PORTAL ID_SERVICIOS= todos", usuarioSession(request));
			}
			request.setAttribute("selec1", "Checked");
			request.setAttribute("selec2", "");
			am.add(ALERT_AVISOS, new ActionMessage("env.proveedor.actualizado"));
			saveMessages(request, am);
			// request.setAttribute("mensaje",
			// "La actualización se realizo con exito.");
			return mapping.findForward("success");
		}
		return mapping.findForward("input");
	}
}
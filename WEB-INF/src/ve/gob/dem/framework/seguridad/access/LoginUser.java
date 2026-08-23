package ve.gob.dem.framework.seguridad.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.exp.ExpEstado;
import ve.gob.dem.framework.exception.PersonalNotSessionException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;


public class LoginUser extends GenericAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	setLogger(LoginUser.class);
	ActionMessages messages = new ActionMessages();
	Usuario usuario = null;
	String reqlogin = "";
	String usuades = "";
	if (request.getParameter(KEY_LOGIN) != null) {
	    try {
		reqlogin = request.getParameter(KEY_LOGIN);
		usuades = Utilidad.desencriptar(reqlogin);
		usuario = ExpUsuario.IniciarSesion(usuades, request.getRemoteHost());
		log.info("loginusuario "+usuario.getIdDependencia());
		//*******************DESCOMENTAR ESTA LÍNEA ANTES DE SUBIR LA APLICACIÓN*********************
		usuario.setId_estado(ExpEstado.BuscarPoridDependencia(usuario.getIdDependencia()).getId());
		//*******************DESCOMENTAR ESTA LÍNEA ANTES DE SUBIR LA APLICACIÓN*********************//
		log.info("loginusuario2 "+usuario.getId_estado());
		request.getSession().setAttribute(KEY_USUARIO, usuario);
	    } catch (Exception e) {
		log.info("login ", e);

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(KEY_LOGIN_EXCEPTION));
		saveMessages(request, messages);
		throw e;
	    }
	} else if (request.getSession().getAttribute(KEY_USUARIO) != null) {
	    usuario = (Usuario) request.getSession().getAttribute(KEY_USUARIO);
	} else {
	    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(KEY_NOSESSION));
	    saveMessages(request, messages);
	    throw new PersonalNotSessionException(KEY_NOSESSION);
	}

	//log.info("UserRolApp="+usuario.getUserRolApp());
	
	request.setAttribute("form_action", "/login");
	return (mapping.findForward("logged"));
    }
}

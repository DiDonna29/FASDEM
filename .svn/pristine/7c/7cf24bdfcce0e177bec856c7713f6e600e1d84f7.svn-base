/**
 * 
 */
package ve.gob.dem.framework.seguridad.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.framework.global.GenericAction;



/**
 * @author marcenrl
 * 
 */
public class ExceptionAction extends GenericAction {

    static protected Logger log = Logger.getLogger(ExceptionAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionMessages messages = new ActionMessages();
	String strMessage = "";
	if (mapping.getPath().concat(".do").equals(URL_NOACCESS))
	    strMessage = KEY_NOACCESS;
	else
	    strMessage = KEY_NOSESSION;
	messages.add(GenericAction.ALERT_AVISOS, new ActionMessage(strMessage));
	saveMessages(request, messages);
	return mapping.findForward("exception.page");
    }
}
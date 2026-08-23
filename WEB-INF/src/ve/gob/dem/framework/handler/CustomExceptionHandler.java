/*
 * Created on 24/09/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import ve.gob.dem.framework.global.GenericAction;
 
/**
 * @author marcenrl
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CustomExceptionHandler extends ExceptionHandler {
	static protected Logger log = Logger.getLogger(CustomExceptionHandler.class);

	public ActionForward execute(Exception e, ExceptionConfig config, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(GenericAction.ALERT_AVISOS, e.getCause());
		log.error("handler", e);
		return (mapping.findForward("exception.page"));
	}
}

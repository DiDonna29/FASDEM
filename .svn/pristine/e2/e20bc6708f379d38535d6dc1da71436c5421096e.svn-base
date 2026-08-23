package ve.gob.dem.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import ve.gob.dem.framework.global.GenericAction;
/**
 * @author marcenrl
 *
 */
public class UserValidateFilter extends GenericAction implements Filter {

    static protected Logger log = Logger.getLogger(UserValidateFilter.class);
    FilterConfig fc;

    @Override
    public void destroy() {
	// TODO Auto-generated method stub
    }
 
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain fChain) throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) sRequest;
	HttpServletResponse response = (HttpServletResponse) sResponse;
	if (request.getSession().getAttribute(GenericAction.KEY_USUARIO) == null) {
	    response.sendRedirect(request.getContextPath() + URL_NOSESSION);
	    return; 
	} else {
	    if (!isPermitido(usuarioSession(request).getPermisos(), request.getServletPath())) {
		response.sendRedirect(request.getContextPath() + URL_NOACCESS);
		return;
	    }
	}
	request.setAttribute("form_action", request.getServletPath());
	fChain.doFilter(sRequest, sResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	this.fc = filterConfig;
    }
}

/*
 * Proyecto portal
 * Created on 20/08/2003
 */
package ve.gob.dem.framework.recursos;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;

/**
 * Manejo generales de parametro en el request, y en el ActionForm 
 * @author lmontanez
 * Creado el 20/08/2003
 */
public class Parametros {
	/**
	 * Retorna un atributo de tipo int.
	 * @param request el objeto request donde esta el atributo
	 * @param nombreAttrib el nombre del atributo
	 * @param numDefault el valor por defecto
	 * @return el número que tiene el atributo o el valor por defecto si ocurre un error en al conversión.
	 */
	public static int getIntAttribute( HttpServletRequest argRequest, String argNombreAttrib, int argNumDefault ) {
			String temp = (String)argRequest.getAttribute(argNombreAttrib);
			if( temp != null && !temp.trim().equals("") ) {
				int num = argNumDefault;
				try {
					num = Integer.parseInt(temp);
				} catch( Exception ignored ) {}
				return num;
			} else {
				return argNumDefault;
			}
	}
	
	/**
	 * Retorna un Parametro de tipo int.
	 * @param request el objeto request donde esta el atributo
	 * @param nombreParam el nombre del parametro
	 * @param numDefault el valor por defecto
	 * @return el número que tiene el atributo o el valor por defecto si ocurre un error en al conversión.
	 */
	public static int getIntParameter( HttpServletRequest argRequest, String argNombreParam, int argNumDefault ) {
			String temp = argRequest.getParameter(argNombreParam);
			if( temp != null && !temp.trim().equals("") ) {
				int num = argNumDefault;
				try {
					num = Integer.parseInt(temp);
				} catch( Exception ignored ) {}
				return num;
			} else {
				return argNumDefault;
			}
	}
	
	/**
	 * Retorna un Parametro de tipo double, el parametro se extrae del objeto ActionForm.
	 * @param argRequest
	 * @param argNombreParam
	 * @param argNumDefault
	 * @return
	 */
	public static double getDoubleParameter( HttpServletRequest argRequest, String argNombreParam, double argNumDefault ) {
			String temp = argRequest.getParameter(argNombreParam);
			if( temp != null && !temp.trim().equals("") ) {
				double num = argNumDefault;
				try {

					num = Double.parseDouble(temp.replace(',', '.'));
					
				} catch( Exception ignored ) {}
				return num;
			} else {
				return argNumDefault;
			}
	}
	/**
	 * Retorna un Parametro de tipo int, el parametro se extrae del objeto ActionForm.
	 * @param request el objeto request donde esta el atributo
	 * @param nombreParam el nombre del parametro
	 * @param numDefault el valor por defecto
	 * @return el número que tiene el atributo o el valor por defecto si ocurre un error en al conversión.
	 */
	public static int getIntParameter( ActionForm argForma, String argNombreParam, int argNumDefault ) {
		try{
			String temp = (String)PropertyUtils.getProperty(argForma, argNombreParam);
			if( temp != null && !temp.trim().equals("") ) {
				int num = argNumDefault;
				try {
					num = Integer.parseInt(temp);
				} catch( Exception ignored ) {}
				return num;
			} else {
				return argNumDefault;
			}
		} catch(Exception e){
			return argNumDefault;
		}
	}
	
	/**
	 * 
	 * @param argRequest
	 * @param argNombreParam
	 * @param argNumDefault
	 * @return
	 */
	public static String getStrParameter( HttpServletRequest argRequest, String argNombreParam, String argStrDefault ) {
			String temp = argRequest.getParameter(argNombreParam);
			if( temp != null) {
				return temp;
			} else {
				return argStrDefault;
			}
	}
	
	/**
	 * 
	 * @param argRequest
	 * @param argNombreParam
	 * @param argStrDefault
	 * @return
	 */
	public static String getStrAttribute(HttpServletRequest argRequest, String argNombreParam, String argStrDefault ) {
			String temp = (String)argRequest.getAttribute(argNombreParam);
			if( temp != null) {
				return temp;
			} else {
				return argStrDefault;
			}
	}
	
	/**
	 * Retorna un parametro tipo String del objeto session si existe
	 * @param argSession
	 * @param argNombreParam
	 * @param argStrDefault
	 * @return
	 */
	public static String getStrAttribute(HttpSession argSession, String argNombreParam, String argStrDefault ) {
			String temp = (String)argSession.getAttribute(argNombreParam);
			if( temp != null) {
				return temp;
			} else {
				return argStrDefault;
			}
	}
	
	/**
	 * Retorna un parametro tipo String del objeto session si existe
	 * @param argSession
	 * @param argNombreParam
	 * @param argStrDefault
	 * @return
	 */
	public static Object getStrAttributeObj(HttpSession argSession, String argNombreParam, String argStrDefault ) {
			Object temp = (Object)argSession.getAttribute(argNombreParam);
			if( temp != null) {
				return temp;
			} else {
				return argStrDefault;
			}
	}
	
}

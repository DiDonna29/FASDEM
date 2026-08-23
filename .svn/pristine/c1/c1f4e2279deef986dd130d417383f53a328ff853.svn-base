
package ve.gob.dem.framework.seguridad.exp;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;


import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalCriticalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.SingletonValores;
import ve.gob.dem.framework.seguridad.bean.Proyecto;
import ve.gob.dem.framework.seguridad.persistencia.PerProyecto;




/**
 * @author hvazquez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExpProyecto implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1170584188917110336L;

	public static Proyecto buscarPorId() throws NamingException, SQLException, PersonalCriticalException, PersonalNotFoundException, NumberFormatException, IOException{
		Connection cnx = null;
		try {
			cnx = Conexion.getConexionSec();
			PerProyecto perProyecto = new PerProyecto(cnx);
			return perProyecto.buscarPorId(Integer.valueOf(SingletonValores.getSingleton().getProperty(GenericAction.KEY_PROYECTO)));
		}finally{
			if (cnx!=null) Conexion.closeConexion(cnx);
		}
	}
}

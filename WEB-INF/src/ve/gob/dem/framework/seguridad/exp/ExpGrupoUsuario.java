/*
 * Created on 28/03/2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.seguridad.exp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalCriticalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.persistencia.PerGrupoUsuario;

/**
 * @author uhuru
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExpGrupoUsuario implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5895511783071463943L;

    public static boolean buscarNodo(String usu, int grupoId) throws NamingException, SQLException, PersonalCriticalException, PersonalNotFoundException {
	Connection cnx = null;
	try {
	    cnx = Conexion.getConexionSec();
	    PerGrupoUsuario perGrupoUsuario = new PerGrupoUsuario(cnx);
	    return perGrupoUsuario.buscarNodo(usu, grupoId);
	} finally {
	    if (cnx != null)
		Conexion.closeConexion(cnx);
	}
    }
}
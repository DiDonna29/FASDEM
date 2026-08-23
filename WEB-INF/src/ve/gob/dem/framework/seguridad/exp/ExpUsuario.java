package ve.gob.dem.framework.seguridad.exp;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.persistencia.PerUsuario;

/**
 * @author hvazquez
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExpUsuario implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7388776543324848652L;
    static protected Logger log = Logger.getLogger(ExpUsuario.class);

    public static Usuario buscarDatosUsuario(String usu) throws NumberFormatException, SQLException, IOException, PersonalNotFoundException, NamingException {
	Connection cnx = null;
	try {
	    cnx = Conexion.getConexionSec();
	    PerUsuario perUsuario = new PerUsuario(cnx);
	    return perUsuario.buscarDatosUsuario(usu);
	} finally {
	    if (cnx != null)
		try {
		    Conexion.closeConexion(cnx);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
    }
    

    public static Usuario buscarDatosUsuarioPorAcceso(String usu) throws NumberFormatException, SQLException, IOException, PersonalNotFoundException, NamingException {
	Connection cnx = null;
	try {
	    cnx = Conexion.getConexionSec();
	    PerUsuario perUsuario = new PerUsuario(cnx);
	    return perUsuario.buscarDatosUsuarioPorAcceso(usu);
	} finally {
	    if (cnx != null)
		try {
		    Conexion.closeConexion(cnx);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
    }
    public static Usuario buscarDatosP(String login) throws NumberFormatException, SQLException, IOException, PersonalNotFoundException, NamingException {
    	Connection cnx = null;
    	try {
    	    cnx = Conexion.getConexionSec();
    	    PerUsuario perUsuario = new PerUsuario(cnx);
    	    return perUsuario.buscarDatosP(login);
    	} finally {
    	    if (cnx != null)
    		try {
    		    Conexion.closeConexion(cnx);
    		} catch (Exception e) {
    		    // TODO Auto-generated catch block
    		    e.printStackTrace();
    		}
    	}
        }
    public static Usuario IniciarSesion(String login, String maquina) throws NamingException, SQLException, NumberFormatException, IOException, PersonalNotFoundException {
	Connection cnx = null;
	try {
	    cnx = Conexion.getConexionSec();
	    PerUsuario perUsuario = new PerUsuario(cnx);
	    return perUsuario.IniciarSesion(login, maquina);
	} finally {
	    if (cnx != null)
		try {
		    Conexion.closeConexion(cnx);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
    }
   
}

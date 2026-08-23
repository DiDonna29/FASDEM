
package ve.gob.dem.framework.seguridad.exp;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;



import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalCriticalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.SingletonValores;
import ve.gob.dem.framework.seguridad.persistencia.PerNodo;




/**
 * @author hvazquez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings("rawtypes")
public class ExpNodo implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -9046210659008986598L;


	public static ArrayList buscarTodos(String usu) throws NamingException, SQLException, PersonalCriticalException, PersonalNotFoundException, NumberFormatException, IOException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexionSec();
			PerNodo perNodo = new PerNodo(cnx);
			return perNodo.buscarTodos(usu,Integer.valueOf(SingletonValores.getSingleton().getProperty(GenericAction.KEY_PROYECTO)));
		}finally{
			if (cnx!=null) Conexion.closeConexion(cnx);
		}
	      
	}

	public static ArrayList buscarPrmisosPorIdPadre(int id_proy, String login, int id_nodo_padre) throws SQLException, PersonalNotFoundException, NamingException	   
	    {
		 Connection cnx = null;
			try {   
				cnx = Conexion.getConexionSec();
				PerNodo perNodo = new PerNodo(cnx);
	        return perNodo.buscarPrmisosPorIdPadre(id_proy, login, id_nodo_padre);
			}finally{
				if (cnx!=null) Conexion.closeConexion(cnx);
			}
	    }


}

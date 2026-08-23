/*
 * Created on 20-abr-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.seguridad.exp;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;


import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.bean.TipoAccion;
import ve.gob.dem.framework.seguridad.persistencia.PerTipoAccion;



/**
 * @author hvazquez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExpTipoAccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2437785638866872836L;

	public static TipoAccion buscarPorId(String id) throws NamingException, SQLException, PersonalNotFoundException, IOException {
		Connection cnx = null;
	 try {
		  cnx = Conexion.getConexionSec();
		PerTipoAccion perTipoAccion = new PerTipoAccion(cnx);
		  return perTipoAccion.buscarPorId(id);
	 }finally{
		   if (cnx!=null) Conexion.closeConexion(cnx);
	 }
    
	 }
}

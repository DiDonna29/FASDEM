/*
 * Created on 28/03/2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.seguridad.persistencia;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.log4j.Logger;

import ve.gob.dem.framework.exception.PersonalCriticalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;


/**
 * @author uhuru
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PerGrupoUsuario implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = -2414860816338495567L;
	static Logger log = Logger.getLogger(PerNodo.class);
	private Connection conexion = null;
   
	// Constructor que recibe la conexi�n
   public PerGrupoUsuario(Connection cnx){
		this.conexion = cnx;
   }
   	
	private boolean buscar(String args) throws PersonalCriticalException{

		Statement 	stmt	= null;
		ResultSet 	rs 		= null;

		String 		sql 	= "Select * from GRUPO_USUARIO ";
		
		if(args!=null){
			sql = sql + args;
		}
		
		try {
			stmt = conexion.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				return true;	
			}
			return false;
		} catch(SQLException e){
			throw new PersonalCriticalException(new Errores(Errores.ERR_COMUN_SERVERDATA, 
														Errores.buscarDescripcion(Errores.ERR_COMUN_SERVERDATA) + " [" + e.getMessage() + "]", 
														"PerNodo", 
														"buscar(String args)"));
		} finally {
			try{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				} 
			catch(SQLException e){}
		}
	}

   public boolean buscarNodo(String usu, int nodo) throws PersonalCriticalException, PersonalNotFoundException{
		String clausula = "where IGRUPID = " + nodo + " AND SUSUALOGIN = '" + usu + "'";
		return this.buscar(clausula);
	}
}

/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import ve.gob.dem.fasdem.bean.Opcion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;


@SuppressWarnings("unchecked")
public class PerOpcion {

    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerOpcion.class);

    public PerOpcion(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT SNODOIMAGEN,IGRUPID,NODO.INODOID,SNODOETIQUETA,SNODOPAGINA,INODOORDEN,IPROYID FROM ARBOL_GRUPO, NODO WHERE IGRUPID=(SELECT GRUPO.IGRUPID FROM GRUPO_USUARIO,GRUPO WHERE GRUPO.IPROYID=276 AND GRUPO.IGRUPID=GRUPO_USUARIO.IGRUPID AND ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
	log.info("OPCION SQL " + sql);
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	   
	    while (rs.next()) {
			
	    	Opcion o = new Opcion();
			o.setDescripcion(rs.getString("SNODOETIQUETA"));
			o.setImagen(rs.getString("SNODOIMAGEN"));
			o.setLink(rs.getString("SNODOPAGINA"));
			array.add(o);
	    }
	    
	    if (array.size() == 0) {
		throw new PersonalNotFoundException(new Errores(Errores.ERR_ELEMENTO_NOFOUND, Errores.buscarDescripcion(Errores.ERR_ELEMENTO_NOFOUND), "PerElemento", "buscar(argumento)"));
	    }
	    return array;
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (sentencia != null)
		    sentencia.close();
	    } catch (SQLException e) {
		log.error(e);
	    }
	}
    }
   

    public ArrayList buscarListaporUsuario(String usua) throws SQLException, PersonalNotFoundException, NamingException  {
    	return this.buscar(" GRUPO_USUARIO.SUSUALOGIN='" + usua + "') AND NODO.INODOID=ARBOL_GRUPO.INODOID");
        }
    
   
   
}

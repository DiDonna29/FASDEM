/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.extension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Autoridades;
import ve.gob.dem.fasdem.bean.Autorizacion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

@SuppressWarnings("unchecked")
public class PerAutoridad {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerAutoridad.class);

    public PerAutoridad(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM autoridades_extension ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Autoridades a = new Autoridades();
		
		a.setId(rs.getInt("id"));
		a.setNombres(rs.getString("nombres"));
		a.setApellidos(rs.getString("apellidos"));
	
		array.add(a);
		
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
    
    
    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	return (ArrayList) this.buscar("WHERE is_activo=TRUE");
    }

    public Autoridades buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
    	return (Autoridades) this.buscar("WHERE ID=" + id).get(0);
    }
}

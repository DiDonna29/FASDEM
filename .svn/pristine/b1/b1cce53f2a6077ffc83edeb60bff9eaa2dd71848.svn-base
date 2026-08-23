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

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Estado;
import ve.gob.dem.fasdem.bean.TipoHojaRuta;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerTipoHojaRuta {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerTipoHojaRuta.class);

    public PerTipoHojaRuta(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM tipo_hoja_ruta ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		TipoHojaRuta c = new TipoHojaRuta();
		c.setId(rs.getInt("id_tipo_hoja_ruta"));
		c.setDescripcion(rs.getString("descripcion"));
		array.add(c);
		
		
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
    

	 public ArrayList ListarBuscar() throws SQLException, PersonalNotFoundException, NamingException  {
	 	   return  this.buscar(" order by descripcion asc");
	 }
	
	    
	    public TipoHojaRuta buscarporId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	 	   return (TipoHojaRuta) this.buscar("  WHERE id_tipo_hoja_ruta=" + id +" ").get(0);
	 }

   
}

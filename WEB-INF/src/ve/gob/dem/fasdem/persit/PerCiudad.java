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

import ve.gob.dem.fasdem.bean.Ciudad;
import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Estado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerCiudad {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerCiudad.class);

    public PerCiudad(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM ciudad ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		log.info("BUSCANDO ciudad " + sql);
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Ciudad c = new Ciudad();
		c.setId(rs.getInt("id_ciudad"));
		c.setDescripcion(rs.getString("descripcion"));
		c.setId_estado(rs.getInt("id_estado"));
			
		
		
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

  
    public ArrayList ListarBuscarPorEstado(int id) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return  this.buscar(" where id_estado ="+id+" order by descripcion asc");
  }

    
    public Ciudad buscarporId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return (Ciudad) this.buscar("  WHERE id_ciudad=" + id +" ").get(0);
 }
   
    
    
    
   
}

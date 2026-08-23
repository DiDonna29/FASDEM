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
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerEstado {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerEstado.class);

    public PerEstado(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM estado ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		log.info("BUSCANDO estado " + sql);
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Estado c = new Estado();
		c.setId(rs.getInt("id_estado"));
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
    
    
    
    
    private ArrayList buscar2(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT estado.id_estado, estado.descripcion FROM dependencia, estado where dependencia.id_estado =  estado.id_estado";
//    	if (arg!=null){
    	    sql = sql + " " + arg;
//    	}
    	
    		log.info("BUSCANDO estado " + sql);
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		Estado c = new Estado();
    		c.setId(rs.getInt("id_estado"));
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

    
    public Estado buscarporId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return (Estado) this.buscar("  WHERE id_estado=" + id +" ").get(0);
 }
   
    
    public Estado buscarporIdDependencia(int id) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return (Estado) this.buscar2("  and dependencia.id=" + id +" ").get(0);
  }
  
    
   
}

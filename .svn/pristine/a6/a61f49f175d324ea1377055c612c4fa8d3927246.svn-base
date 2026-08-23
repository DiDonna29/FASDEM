/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.administrador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;
import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.fasdem.bean.Patologias;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.bean.Tratamiento;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerTratamiento {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerTratamiento.class);

    public PerTratamiento(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM tratamiento ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
	    Tratamiento e = new Tratamiento();
		e.setId(rs.getInt("id_tratamiento"));
		e.setDescripcion(rs.getString("descripcion"));
		

		array.add(e);
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
    
    


    public Tratamiento buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Tratamiento) this.buscar("  WHERE id_tratamiento=" + id ).get(0);
    }
    
    public Tratamiento buscarPorDescripcion(String descripcion) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Tratamiento) this.buscar("  WHERE descripcion='" + descripcion +"'").get(0);
 }

    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar(" order by DESCRIPCION asc");
    }
   
    public void crearTratamiento(Tratamiento tratamiento) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;

		  String sql="INSERT INTO tratamiento (DESCRIPCION) VALUES (UPPER(?))";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
	 
			    sentencia.setString(1,tratamiento.getDescripcion()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }

  public void modificarTratamiento(Tratamiento tratamiento) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;


		  String sql="UPDATE tratamiento set DESCRIPCION=?  where id_tratamiento = ?";

		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setString(1,tratamiento.getDescripcion()); 
			    sentencia.setInt(2,tratamiento.getId());
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }
   
    
    
    
   
}

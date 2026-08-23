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
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerEspecialidad {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerEspecialidad.class);

    public PerEspecialidad(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM especialidad ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Especialidad e = new Especialidad();
		e.setId(rs.getInt("id_especialidad"));
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
    
    


    public Especialidad buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Especialidad) this.buscar("  WHERE id_especialidad=" + id ).get(0);
    }
    
    public Especialidad buscarPorDescripcion(String descripcion) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Especialidad) this.buscar("  WHERE descripcion='" + descripcion+"'" ).get(0);
 }

    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar(" order by DESCRIPCION asc");
    }
   
    public void crearEspecialidad(Especialidad especialidad) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;

		  String sql="INSERT INTO especialidad (DESCRIPCION) VALUES (UPPER(?))";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
	 
			    sentencia.setString(1,especialidad.getDescripcion()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }

  public void modificarEspecialidad(Especialidad especialidad) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;


		  String sql="UPDATE Especialidad set DESCRIPCION=?  where id_especialidad = ?";

		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setString(1,especialidad.getDescripcion()); 
			    sentencia.setInt(2,especialidad.getId());
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

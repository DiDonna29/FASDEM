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
import ve.gob.dem.fasdem.bean.Autorizacion;
import ve.gob.dem.fasdem.exp.extension.ExpAutoridades;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

@SuppressWarnings("unchecked")
public class PerAutorizacion {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerAutorizacion.class);

    public PerAutorizacion(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM autorizacion ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Autorizacion e = new Autorizacion();
		
		e.setId(rs.getInt("id_autorizacion"));
		e.setId_siniestro(rs.getInt("id_siniestro"));
		e.setId_autoridad(rs.getInt("usuario"));
		e.setFecha_autorizacion(rs.getDate("fecha_autorizacion"));
		e.setMonto_maximo_autorizado(rs.getDouble("monto_maximo_autorizado"));
		e.setObservaciones(rs.getString("observaciones"));
		e.setAutoridad(ExpAutoridades.BuscarPorId(rs.getInt("usuario")));


		array.add(e);
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
    
    
    public ArrayList buscarListaSiniestro(int idsin) throws SQLException, PersonalNotFoundException, NamingException  {
    	return (ArrayList) this.buscar("WHERE id_siniestro= " + idsin + "order by  monto_maximo_autorizado desc");
    }
    
    
    
    public void InsertarAutorizacion(Autorizacion auto) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="INSERT INTO autorizacion (id_siniestro,usuario,fecha_autorizacion,monto_maximo_autorizado,observaciones) VALUES (?,?,?,?,?)";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);
			    
			    sentencia.setInt(1,auto.getId_siniestro()); 
			    sentencia.setInt(2,auto.getId_autoridad());
			    sentencia.setDate(3,new java.sql.Date(new Date().getTime()));
			    sentencia.setDouble(4,auto.getMonto_maximo_autorizado());
			    sentencia.setString(5,auto.getObservaciones());
			
			    
			    sentencia.executeUpdate();
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    
    
    public void EliminarAutorizacion(int id) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="DELETE from autorizacion WHERE id_autorizacion=?";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setInt(1,id); 
			    sentencia.executeUpdate();
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   
}

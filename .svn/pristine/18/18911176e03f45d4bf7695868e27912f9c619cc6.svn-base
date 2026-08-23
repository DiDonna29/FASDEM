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
import ve.gob.dem.fasdem.bean.PatologiaOrganoTratamiento;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerCausaIngreso {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerCausaIngreso.class);

    public PerCausaIngreso(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT id_pat_org_trat_esp as id, especialidad.descripcion as especialidad,"+
	"organo.descripcion as organo,tratamiento.descripcion as tratamiento,"+
	"patologias.descripcion as patologia, patologia_organo_tratamiento.is_activo "+
	"FROM patologia_organo_tratamiento, especialidad, organo, tratamiento, patologias where "+
	"patologia_organo_tratamiento.id_especialidad = especialidad.id_especialidad "+
	"and patologia_organo_tratamiento.id_organo = organo.id_organo "+
	"and patologia_organo_tratamiento.id_patologia = patologias.id_patologia "+
	"and patologia_organo_tratamiento.id_tratamiento = tratamiento.id_tratamiento ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		log.info(sql);
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
	    PatologiaOrganoTratamiento causaIngreso =new PatologiaOrganoTratamiento();
	    causaIngreso.setId(rs.getInt("id"));
	    causaIngreso.setStrEspecialidad(rs.getString("especialidad"));
	    causaIngreso.setStrOrgano(rs.getString("organo"));
	    causaIngreso.setStrPatologia(rs.getString("patologia"));
	    causaIngreso.setStrTratamiento(rs.getString("tratamiento"));
	    causaIngreso.setIsActivo(rs.getBoolean("is_activo"));


		array.add(causaIngreso);
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
    
    


    public ArrayList buscarPorEsp(int id_especialidad) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (ArrayList) this.buscar("  and patologia_organo_tratamiento.id_especialidad=" + id_especialidad );
    }

    public ArrayList buscarPorEspOrg(int id_especialidad,int id_organo) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (ArrayList) this.buscar("  and patologia_organo_tratamiento.id_especialidad=" + id_especialidad +" and patologia_organo_tratamiento.id_organo="+ id_organo);
    }

    public ArrayList buscarPorEspOrgPat(int id_especialidad,int id_organo, int id_patologia) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (ArrayList) this.buscar("  and patologia_organo_tratamiento.id_especialidad=" + id_especialidad +" and patologia_organo_tratamiento.id_organo="+ id_organo+" and patologia_organo_tratamiento.id_patologia="+id_patologia);
    }

    public boolean verificarPorEspOrgPatTrat(int id_especialidad,int id_organo, int id_patologia, int tratamiento) throws SQLException, PersonalNotFoundException, NamingException  {
	       if(this.buscar("  and patologia_organo_tratamiento.id_especialidad=" + id_especialidad +" and patologia_organo_tratamiento.id_organo="+ id_organo+" and patologia_organo_tratamiento.id_patologia="+id_patologia+" and patologia_organo_tratamiento.id_tratamiento="+tratamiento).size()==0){
	    	return false;   
	       }else{
	    	return true;   
	       }
 }
    
    public ArrayList buscarPorEspOrgPatTrat(int id_especialidad,int id_organo, int id_patologia, int tratamiento) throws SQLException, PersonalNotFoundException, NamingException  {
String cadena ="";
if(id_especialidad!=-1){
	cadena =cadena +"  and patologia_organo_tratamiento.id_especialidad=" + id_especialidad;
}
if(id_organo!=-1){
	cadena =cadena +" and patologia_organo_tratamiento.id_organo="+ id_organo;
}
if(id_patologia!=-1){
	cadena =cadena +" and patologia_organo_tratamiento.id_patologia="+id_patologia;
}
if(tratamiento!=-1){
	cadena =cadena +" and patologia_organo_tratamiento.id_tratamiento="+tratamiento;
}
    	return (ArrayList) this.buscar(cadena);
    }

    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar(" order by DESCRIPCION asc");
    }
   
    public void crearCausaIngreso(int id_especialidad,int id_organo, int id_patologia, int tratamiento) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;

		  String sql="INSERT INTO patologia_organo_tratamiento (id_especialidad, id_tratamiento, id_organo, id_patologia,is_activo) VALUES (?,?,?,?,true)";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
	 
			    sentencia.setInt(1,id_especialidad); 
			    sentencia.setInt(2,tratamiento); 
			    sentencia.setInt(3,id_organo); 
			    sentencia.setInt(4,id_patologia); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }

  public void modificarCausaIngreso(int id) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;


		  String sql="UPDATE patologia_organo_tratamiento set is_activo=(CASE patologia_organo_tratamiento.is_activo WHEN TRUE THEN FALSE ELSE TRUE END)  where id_pat_org_trat_esp = ?";

		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setInt(1,id); 
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

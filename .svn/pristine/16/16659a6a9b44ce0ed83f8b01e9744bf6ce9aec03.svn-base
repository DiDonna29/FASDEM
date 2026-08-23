package ve.gob.dem.fasdem.per;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.exception.PersonalCriticalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

public class PerReporte implements Serializable {

	private Connection conexion = null;
   	static Logger log=Logger.getLogger(PerReporte.class);
	// Constructor que recibe la conexi�n
   public PerReporte(Connection cnx){
  		this.conexion = cnx;
   }
   	//Realiza una busqueda para validar que existan datos para el reporte 1
	private ArrayList buscarReporte1(String args) throws PersonalCriticalException, PersonalNotFoundException {

		Statement stmt = null;
		ResultSet rs = null;
		ArrayList arreglo = new ArrayList(0);
		String nroproyecto="";

		// Aqui hago lo necesario para buscar la informaci�n
		String sql = " SELECT id ";
		if(args!=null){
			sql = sql + args;
			log.info("sql " + sql);
			}

		try {
				stmt = conexion.createStatement();
				rs = stmt.executeQuery(sql);
			while(rs.next()){
						
							nroproyecto=rs.getString("FDESCPLAN");
							arreglo.add(nroproyecto);
						}
			
				
			// no consiguio ninguno, no dio error pero no devolvio nada  
				//if (arreglo.size()==0){
					//throw new PersonalNotFoundException(new Errores(Errores.ERR_NODO_NOFOUND, 
															// Errores.buscarDescripcion(Errores.ERR_PROYECTO_NOFOUND) , 
															 //"PerProyecto", 
															 //"buscar(String args)"));
				//}
				
		} catch(SQLException e){
			throw new PersonalCriticalException(new Errores(Errores.ERR_COMUN_SERVERDATA, 
														Errores.buscarDescripcion(Errores.ERR_PROYECTO_NOFOUND) + " [" + e.getMessage() + "]", 
														"PerReporte", 
														"buscar(String args)"));
		} finally {
			try{
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				} 
			catch(SQLException e){}
		}
	 return arreglo;
	}
//	Realiza una busqueda para validar que existan datos para el reporte 100
	  private ArrayList buscarReporte(String args) throws PersonalCriticalException, PersonalNotFoundException {

		  Statement stmt = null;
		  ResultSet rs = null;
		  ArrayList arreglo = new ArrayList(0);
		  String nroproyecto="";

		  // Aqui hago lo necesario para buscar la informaci�n
		  String sql = "select * from inmueble ";
		  if(args!=null){
			  sql = sql + args;
			  log.info("sql " + sql);
			  }

		  try {
				  stmt = conexion.createStatement();
				  rs = stmt.executeQuery(sql);
			  while(rs.next()){
						
							  nroproyecto=rs.getString("IMETACOD");
							  arreglo.add(nroproyecto);
						  }
			
				
			  // no consiguio ninguno, no dio error pero no devolvio nada  
				  //if (arreglo.size()==0){
					  //throw new PersonalNotFoundException(new Errores(Errores.ERR_NODO_NOFOUND, 
															  // Errores.buscarDescripcion(Errores.ERR_PROYECTO_NOFOUND) , 
															   //"PerProyecto", 
															   //"buscar(String args)"));
				  //}
				
		  } catch(SQLException e){
			  throw new PersonalCriticalException(new Errores(Errores.ERR_COMUN_SERVERDATA, 
														  Errores.buscarDescripcion(Errores.ERR_PROYECTO_NOFOUND) + " [" + e.getMessage() + "]", 
														  "PerReporte", 
														  "buscar(String args)"));
		  } finally {
			  try{
				  if (rs != null) rs.close();
				  if (stmt != null) stmt.close();
				  } 
			  catch(SQLException e){}
		  }
	   return arreglo;
	  }
	
//Envia como condici�n el id del proyecto
   public ArrayList buscarDatosReporte1(int id) throws PersonalCriticalException, PersonalNotFoundException{
   		String clausula = "WHERE id=id";
		return this.buscarReporte(clausula);
	}
	
	public ArrayList buscarDatosReporte(int cod_meta) throws PersonalCriticalException, PersonalNotFoundException{
		   String clausula = "WHERE IMETACOD="+cod_meta;
		   return this.buscarReporte(clausula);
	   }
	

}

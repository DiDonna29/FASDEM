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
import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.exp.administrador.ExpPoliza;
import ve.gob.dem.fasdem.exp.administrador.ExpTipoCobertura;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerCobertura {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerCobertura.class);

    public PerCobertura(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM cobertura ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}

	log.info(sql);
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Cobertura c = new Cobertura();
		c.setId(rs.getInt("id_cobertura"));
		c.setMonto(rs.getDouble("monto"));
		c.setIsActivo(rs.getBoolean("is_activo"));
		c.setPorPatologia(rs.getBoolean("por_patologia"));
		c.setTipoCobertura(ExpTipoCobertura.buscarPorId(rs.getInt("id_tipo_cobertura")));
		c.setPoliza(ExpPoliza.buscarporID((rs.getInt("id_poliza"))));
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
    
  
    
    
   
    
    public void crearCobertura(Cobertura c) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
	
		  String sql="INSERT INTO Cobertura (monto,id_tipo_cobertura,por_patologia,is_activo,id_poliza) VALUES (?,?,?,?,?)";

		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setDouble(1,c.getMonto()); 
			    sentencia.setInt(2,c.getTipoCobertura().getId()); 
			    sentencia.setBoolean(3,c.isPorPatologia()); 
			    sentencia.setBoolean(4,c.getIsActivo()); 
			    sentencia.setInt(5,c.getPoliza().getId()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		
 }
  
    public void modificarCobertura(Cobertura c) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;


		  String sql="Update Cobertura set monto=?,por_patologia=?  where id_cobertura=?";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setDouble(1,c.getMonto()); 
				sentencia.setBoolean(2,c.isPorPatologia()); 
			    sentencia.setInt(3,c.getId()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  log.info("BUSCA JC:"+sql); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }
 

    public void modificarEstatus(Cobertura c) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;


		  String sql="Update Cobertura set is_activo=? where id_cobertura=?";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setBoolean(1,c.getIsActivo()); 
			    sentencia.setInt(2,c.getId()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }
    
    
    public Cobertura validar(String rif) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Cobertura) this.buscar("  WHERE RIF='" + rif +"'").get(0);
    }
    
    public void crearCoberturaTipoProv(int id_prov,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {
    		ArrayList ListaActual = buscarCoberturaTipoProvID(id_prov);
//			crearCoberturaTipoProv2(id_prov,listTipoProv,ListaActual);    	
//    		crearCoberturaTipoProv3(id_prov,listTipoProv,ListaActual);
    		eliminaCoberturaTipoProv2(id_prov);
    		crearCoberturaTipoProv4(id_prov,listTipoProv);
    }

    public void crearCoberturaTipoProvCre(int id_cobertura,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {   	
		crearCoberturaTipoProv4(id_cobertura,listTipoProv);
}

 
    
    public void eliminaCoberturaTipoProv2(int id_prov) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
			  sql="delete from cobertura_tipo_tramite WHERE id_cobertura = ?";
					try{
						sentencia = cnx.prepareStatement(sql);
					    sentencia.setInt(1,id_prov); 
					    sentencia.executeUpdate();			    
					}catch(Exception e){
						log.info("BUSCA JC:"+sql);
						log.info(e);
					}finally {
						if (sentencia != null) sentencia.close();
					}
			  
		  
		  	
}
    
    
    
    public void crearCoberturaTipoProv2(int id_prov,String [] listTipoProv,ArrayList ListaActual) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int j = 0 ; j<ListaActual.size();j++){
			  sql="delete from cobertura_tipo_tramite WHERE id_cobertura = ? AND ID_TIPO_tramite = ?";
			  for(int k = 0 ; k<listTipoProv.length;k++){
					if((Integer)ListaActual.get(j)==Integer.valueOf(listTipoProv[k])){
						sql="INSERT INTO cobertura_tipo_tramite (id_cobertura, id_tipo_tramite) VALUES (?,?)";
					}
					try{
						sentencia = cnx.prepareStatement(sql);
					    sentencia.setInt(1,id_prov); 
					    sentencia.setInt(2,(Integer)ListaActual.get(j)); 
					    sentencia.executeUpdate();			    
					}catch(Exception e){
						log.info("BUSCA JC:"+sql);
						log.info(e);
					}finally {
						if (sentencia != null) sentencia.close();
					}
			  }
		  }
		  	
  }
    
    
    

    public void crearCoberturaTipoProv3(int id_prov,String [] listTipoProv,ArrayList ListaActual) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int k = 0 ; k<listTipoProv.length;k++){
			  sql="";
			  for(int j = 0 ; j<ListaActual.size();j++){
					if((Integer)ListaActual.get(j)==Integer.valueOf(listTipoProv[k])){
						sql="INSERT INTO cobertura_tipo_tramite (id_cobertura, id_tipo_tramite) VALUES (?,?)";
					}
					if(sql.equals("")){
						  busc = this.buscarCoberturaTipoProv(id_prov,Integer.valueOf(listTipoProv[k]));
						  if(busc){
								sql="INSERT INTO cobertura_tipo_tramite (id_cobertura, id_tipo_tramite) VALUES (?,?)";
						  }else{
								sql="INSERT INTO cobertura_tipo_tramite (id_cobertura, id_tipo_tramite) VALUES (?,?)";
						  }						
					}
					try{
						sentencia = cnx.prepareStatement(sql);
					    sentencia.setInt(1,id_prov); 
					    sentencia.setInt(2,Integer.valueOf(listTipoProv[k])); 
					    sentencia.executeUpdate();			    
					}catch(Exception e){
						log.info("BUSCA JC:"+sql);
						log.info(e);
					}finally {
						if (sentencia != null) sentencia.close();
					}
			  }
		  }
		  	
}
    
    public void crearCoberturaTipoProv4(int id_cobertura,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int k = 0 ; k<listTipoProv.length;k++){
					sql="INSERT INTO cobertura_tipo_tramite (id_cobertura, id_tipo_tramite) VALUES (?,?)";
					try{
						sentencia = cnx.prepareStatement(sql);
					    sentencia.setInt(1,id_cobertura); 
					    sentencia.setInt(2,Integer.valueOf(listTipoProv[k])); 
					    sentencia.executeUpdate();			    
					}catch(Exception e){
						log.info("BUSCA JC:"+sql);
						log.info(e);
					}finally {
						if (sentencia != null) sentencia.close();
					}
			  }
		  
		  	
}
    
    private boolean buscarCoberturaTipoProv(int id_prov,int id_tipo_prov ) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT * FROM Cobertura_tipo_tramite where id_cobertura ="+id_prov+" and ID_TIPO_tramite="+id_tipo_prov;
    		log.info("BUSCANDO CLINICA " + sql);
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    	    	return true;
    	    }
    	    return false;
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
           

    
    public ArrayList buscarCoberturaTipoProvID(int id_prov) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	ArrayList list= new ArrayList();;
    	String sql = "SELECT * FROM cobertura_tipo_tramite where ID_cobertura ="+id_prov;
    		
    		try {
    		    sentencia = cnx.createStatement();
    		    rs = sentencia.executeQuery(sql);
    		    while (rs.next()) {
    			list.add(rs.getInt("id_tipo_tramite"));
    		    }
    		    if (list.size() == 0) {
    			throw new PersonalNotFoundException(new Errores(Errores.ERR_ELEMENTO_NOFOUND, Errores.buscarDescripcion(Errores.ERR_ELEMENTO_NOFOUND), "PerElemento", "buscar(argumento)"));
    		    }
    		    return list;
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
    
    
    public Clinica buscarporRif(String rif) throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (Clinica) this.buscar("  WHERE RIF='" + rif +"' ").get(0);
    }

    public boolean ExisteCobertura(String id_tipo_cobertura, String id_poliza) throws SQLException, PersonalNotFoundException, NamingException  {
    	try{
    	
 		   this.buscar("  WHERE id_tipo_cobertura=" + id_tipo_cobertura +" and id_poliza= "+id_poliza).get(0);
 		   return true;
 	   }catch(PersonalNotFoundException e){
 		  return false;	   
 	   }
    }
    
    public Cobertura buscarporPolizaTipoCobertura(int id_poliza, int id_tipo_cobertura) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return  (Cobertura)this.buscar("  WHERE id_poliza =" + id_poliza +" and id_tipo_cobertura="+ id_tipo_cobertura).get(0);
 }

    public Cobertura buscarCobertura( int id_cobertura) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return  (Cobertura)this.buscar("  WHERE id_cobertura="+ id_cobertura).get(0);
  }

    
    
    public ArrayList buscarPorIdPoliza(int id_poliza) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return  this.buscar("  WHERE id_poliza = " + id_poliza);
  }

    
    
    
    public ArrayList buscarporNombreParcial(String nombre) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return  this.buscar("  WHERE upper(descripcion) like '%" + nombre.toUpperCase() +"%' ");
  }
    
    public Clinica buscarporId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return (Clinica) this.buscar("  WHERE ID_PROVEEDOR=" + id +" ").get(0);
 }
   
    
    
    
   
}

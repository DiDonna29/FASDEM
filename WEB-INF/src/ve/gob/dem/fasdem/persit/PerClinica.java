/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit;

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
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerClinica {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerClinica.class);

    public PerClinica(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM proveedor ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}

	
	log.info(sql);
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		Clinica c = new Clinica();
		c.setRif(rs.getString("rif"));
		c.setNombre(rs.getString("descripcion"));
		c.setDireccion(rs.getString("direccion"));
		c.setTelefono(rs.getString("telefono"));
		c.setContacto(rs.getString("contacto"));

			c.setId_estado(rs.getInt("id_estado"));

		try{  
		c.setId_ciudad(rs.getInt("id_ciudad"));	
		}catch(Exception e2){}
		c.setIsActivo(rs.getBoolean("is_activo"));
		c.setId(rs.getInt("id_proveedor"));
		log.info("id_proveedor "+rs.getInt("id_tipo_proveedor"));
		c.setTipoProveedor(ExpTipoProveedor.buscarporID(rs.getInt("id_tipo_proveedor")));
		c.setRazonable(rs.getBoolean("is_costo_razonable"));
		c.setServicio(rs.getString("servicios"));

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
    
    private ArrayList buscarC(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT * FROM proveedor";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}

    	
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		Clinica c = new Clinica();
    		c.setRif(rs.getString("rif"));
    		c.setNombre(rs.getString("descripcion"));
    		c.setDireccion(rs.getString("direccion"));
    		c.setTelefono(rs.getString("telefono"));
    		c.setContacto(rs.getString("contacto"));
    		c.setId_estado(rs.getInt("id_estado"));
    		c.setIsActivo(rs.getBoolean("is_activo"));
    		try{
    		c.setObjcuenta(ExpCuenta.buscarCuentaProveedor(rs.getInt("id_proveedor")));
    		}catch(PersonalNotFoundException e2){
    			c.setObjcuenta(null);	
    		}
    		c.setId(rs.getInt("id_proveedor"));
    		

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
          
    
    
    private ArrayList buscarEstadistica(String arg,String fech) throws SQLException, PersonalNotFoundException, NamingException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT * FROM proveedor ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}

    	try {
    	    
    		
    		sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		Clinica c = new Clinica();
    		c.setRif(rs.getString("rif"));
    		c.setNombre(rs.getString("descripcion"));
    		c.setDireccion(rs.getString("direccion"));
    		c.setTipo(rs.getString("id_tipo_proveedor"));
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
    
    public int crearProveedor(Clinica prov) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;

		  String sql="INSERT INTO proveedor (RIF,DESCRIPCION,DIRECCION,TELEFONO,CONTACTO,ID_ESTADO,IS_ACTIVO,IDENTIFICADOR,id_tipo_proveedor,is_costo_razonable,servicios,id_ciudad) VALUES (upper(?),upper(?),upper(?),?,upper(?),?,TRUE,upper(?),?,?,upper(?),?)";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
			    
			    
			 
			    sentencia.setString(1,prov.getRif()); 
			    sentencia.setString(2,prov.getNombre()); 
			    sentencia.setString(3,prov.getDireccion()); 
			    sentencia.setString(4,prov.getTelefono()); 
			    sentencia.setString(5,prov.getContacto()); 
			    sentencia.setInt(6,prov.getId_estado()); 
			    sentencia.setString(7,prov.getRif());
			    sentencia.setInt(8,prov.getTipoProveedor().getId()); 
			    sentencia.setBoolean(9,prov.getRazonable()); 
			    sentencia.setString(10,prov.getServicio()); 
			    sentencia.setInt(11,prov.getId_ciudad()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		
	try {
		return this.buscarporRif(prov.getRif()).getId();
	} catch (PersonalNotFoundException e) {
		log.error(e);
		return id_proveedor;
	} catch (NamingException e) {
		log.error(e);
		return id_proveedor;
	}
	 }
  
    public void modificarProveedor(Clinica prov) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;

		  String sql="UPDATE proveedor set DESCRIPCION=upper(?) ,DIRECCION=upper(?) ,TELEFONO=? ,CONTACTO=upper(?) ,ID_ESTADO=? ,IS_ACTIVO=? ,IDENTIFICADOR=upper(?), id_tipo_proveedor =?, is_costo_razonable = ?, servicios = upper(?), id_ciudad = ? where id_proveedor = ?";
		  String sql2="UPDATE proveedor set DESCRIPCION="+prov.getNombre() +",DIRECCION="+prov.getDireccion()+" ,TELEFONO="+prov.getTelefono()+" ,CONTACTO="+prov.getContacto()+" ,ID_ESTADO="+prov.getId_estado()+" ,IS_ACTIVO="+prov.getIsActivo()+" ,IDENTIFICADOR="+prov.getRif()+" ,is_razonable="+prov.getRazonable()+", servicio = "+prov.getServicio()+", id_ciudad ="+prov.getId_ciudad()+" where id_proveedor = "+prov.getId()+"";

		  try{
			    sentencia = cnx.prepareStatement(sql);
			    
			    
			 
			    sentencia.setString(1,prov.getNombre()); 
			    sentencia.setString(2,prov.getDireccion()); 
			    sentencia.setString(3,prov.getTelefono()); 
			    sentencia.setString(4,prov.getContacto()); 
			    sentencia.setInt(5,prov.getId_estado()); 
			    sentencia.setBoolean(6,prov.getIsActivo());
			    sentencia.setString(7,prov.getRif());
			    sentencia.setInt(8,prov.getTipoProveedor().getId());
			    sentencia.setBoolean(9,prov.getRazonable());
			    sentencia.setString(10,prov.getServicio()); 
			    sentencia.setInt(11,prov.getId_ciudad()); 
			    sentencia.setInt(12,prov.getId());
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }
 

    public Clinica validar(String rif) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Clinica) this.buscar("  WHERE RIF='" + rif +"'").get(0);
    }
    
    public void crearProveedorTipoProv(int id_prov,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {
    		ArrayList ListaActual = buscarProvTipoProvID(id_prov);
			crearProveedorTipoProv2(id_prov,listTipoProv,ListaActual);    	
    		crearProveedorTipoProv3(id_prov,listTipoProv,ListaActual);
    }

    public void crearProveedorTipoProvCre(int id_prov,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {   	
		crearProveedorTipoProv4(id_prov,listTipoProv);
}

    
    public void crearProveedorTipoProv2(int id_prov,String [] listTipoProv,ArrayList ListaActual) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int j = 0 ; j<ListaActual.size();j++){
			  sql="UPDATE proveedor_tipo_tramite set IS_ACTIVO = FALSE WHERE ID_PROVEEDOR = ? AND ID_TIPO_tramite = ?";
			  for(int k = 0 ; k<listTipoProv.length;k++){
					if((Integer)ListaActual.get(j)==Integer.valueOf(listTipoProv[k])){
						sql="UPDATE proveedor_tipo_tramite set IS_ACTIVO = TRUE WHERE ID_PROVEEDOR = ? AND ID_TIPO_tramite = ?";						
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
    

    
    public void crearTipoTramitePortal(int id_proveedor,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {   	
    	crearTipoTramitePortal4(id_proveedor,listTipoProv);
}

    public void modificarTipoTramitePortal(int id_proveedor,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {   	
    	eliminaTipoTramitePortal(id_proveedor);
    	crearTipoTramitePortal4(id_proveedor,listTipoProv);
}
 
    public void modificarTipoTramitePortaltodos(int id_proveedor) throws SQLException, PersonalNotFoundException, NamingException  {   	
    	eliminaTipoTramitePortal(id_proveedor);
}

    
    public void crearTipoTramitePortal4(int id_proveedor,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int k = 0 ; k<listTipoProv.length;k++){
					sql="INSERT INTO servicio_portal_proveedor (id_proveedor, id_serpor,is_activo) VALUES (?,?,true)";
					try{
						sentencia = cnx.prepareStatement(sql);
					    sentencia.setInt(1,id_proveedor); 
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

    public void eliminaTipoTramitePortal(int id_prov) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
			  sql="delete from servicio_portal_proveedor WHERE id_proveedor = ?";
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
   
    
    

    public void crearProveedorTipoProv3(int id_prov,String [] listTipoProv,ArrayList ListaActual) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int k = 0 ; k<listTipoProv.length;k++){
			  sql="";
			  for(int j = 0 ; j<ListaActual.size();j++){
					if((Integer)ListaActual.get(j)==Integer.valueOf(listTipoProv[k])){
						sql="UPDATE proveedor_tipo_tramite set IS_ACTIVO = TRUE WHERE ID_PROVEEDOR = ? AND ID_TIPO_tramite = ?";						
					}
					if(sql.equals("")){
						  busc = this.buscarProvTipoProv(id_prov,Integer.valueOf(listTipoProv[k]));
						  if(busc){
							  sql="UPDATE proveedor_tipo_tramite set IS_ACTIVO = TRUE WHERE ID_PROVEEDOR = ? AND ID_TIPO_tramite = ?";						
						  }else{
							  sql="INSERT INTO proveedor_tipo_tramite (ID_PROVEEDOR, ID_TIPO_tramite) VALUES (?,?)";
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
    
    public void crearProveedorTipoProv4(int id_prov,String [] listTipoProv) throws SQLException, PersonalNotFoundException, NamingException  {
		  String sql="";
		  PreparedStatement sentencia = null;
		  boolean busc;

		  for(int k = 0 ; k<listTipoProv.length;k++){
					sql="INSERT INTO proveedor_tipo_tramite (ID_PROVEEDOR, ID_TIPO_tramite) VALUES (?,?)";
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
    
    private boolean buscarProvTipoProv(int id_prov,int id_tipo_prov ) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT * FROM proveedor_tipo_tramite where ID_PROVEEDOR ="+id_prov+" and ID_TIPO_tramite="+id_tipo_prov;
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
           

    
    public ArrayList buscarProvTipoProvID(int id_prov) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	ArrayList list= new ArrayList();;
    	String sql = "SELECT * FROM proveedor_tipo_tramite where is_activo = true and ID_PROVEEDOR ="+id_prov;
    		log.info("sql " + sql);
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
 
    
    public ArrayList buscarProvTipoServPortal(int id_prov) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	ArrayList list= new ArrayList();;
    	String sql = "SELECT * FROM servicio_portal_proveedor where ID_PROVEEDOR ="+id_prov;
    		log.info("SERVICIO PROV "+sql);
    		try {
    		    sentencia = cnx.createStatement();
    		    rs = sentencia.executeQuery(sql);
    		    while (rs.next()) {
    			list.add(rs.getInt("id_serpor"));
    		    }
    		   // if (list.size() == 0) {
    			//throw new PersonalNotFoundException(new Errores(Errores.ERR_ELEMENTO_NOFOUND, Errores.buscarDescripcion(Errores.ERR_ELEMENTO_NOFOUND), "PerElemento", "buscar(argumento)"));
    		    //}
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
    	   return (Clinica) this.buscar("  WHERE RIF=upper('" + rif +"') ").get(0);
    }

    public boolean ExisteRif(String rif) throws SQLException, PersonalNotFoundException, NamingException  {
    	try{
    	
 		   this.buscar("  WHERE RIF='" + rif +"' ").get(0);
 		   return true;
 	   }catch(PersonalNotFoundException e){
 		  return false;	   
 	   }
    }
    
    public ArrayList buscarporRifParcial(String rif) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return  this.buscar("  WHERE upper(RIF) like '%" + rif.toUpperCase() +"%' and ID_TIPO_PROVEEDOR!=35 and ID_TIPO_PROVEEDOR!=2 and ID_TIPO_PROVEEDOR!=17 and ID_TIPO_PROVEEDOR!=18 and ID_TIPO_PROVEEDOR!=22 and ID_TIPO_PROVEEDOR!=32 and ID_TIPO_PROVEEDOR!=0 ");
 }

    public ArrayList buscarporRifCuenta(String rif) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return  this.buscarC("  WHERE upper(RIF) like '%" + rif.toUpperCase() +"%' ");
  }

    public ArrayList buscarporNombreCuenta(String nombre) throws SQLException, PersonalNotFoundException, NamingException  {
   	   return  this.buscarC("  WHERE upper(descripcion) like '%" + nombre.toUpperCase() +"%' ");
   }
    
    
    public ArrayList buscarporNombreParcial(String nombre) throws SQLException, PersonalNotFoundException, NamingException  {
  	   return  this.buscar("  WHERE upper(descripcion) like '%" + nombre.toUpperCase() +"%' and ID_TIPO_PROVEEDOR!=35 and ID_TIPO_PROVEEDOR!=2 and ID_TIPO_PROVEEDOR!=17 and ID_TIPO_PROVEEDOR!=18 and ID_TIPO_PROVEEDOR!=22 and ID_TIPO_PROVEEDOR!=32 and ID_TIPO_PROVEEDOR!=0 ");
  }
    
    public Clinica buscarporId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return (Clinica) this.buscar("  WHERE ID_PROVEEDOR=" + id +" ").get(0);
 }
   
    
    
    
   
}

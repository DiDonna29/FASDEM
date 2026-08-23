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
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerCuenta {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerCuenta.class);

    public PerCuenta(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT id_cuenta_beneficiario,numero_cuenta,banco.id_banco, descripcion ,tipo_cuenta  FROM view_cuenta_beneficiario_f a, banco where a.id_banco = banco.id_banco";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
		
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
	    Cuenta e = new Cuenta();
		e.setId(rs.getInt("id_cuenta_beneficiario"));
		e.setCuenta(rs.getString("numero_cuenta"));
		e.setNombreBanco(rs.getString("descripcion"));
		e.setTipoCuenta(rs.getString("tipo_cuenta"));
		e.setCodBanco(rs.getString("id_banco"));

		array.add(e);
	    }
	    if (array.size() == 0) {
		JOptionPane.showMessageDialog(null,"Revisar relacion de pagos. Datos faltantes","Alerta",JOptionPane.PLAIN_MESSAGE);
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
  
    private ArrayList buscarProveedor(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT id_cuenta_proveedor,codigo_cuenta,banco.id_banco, descripcion ,tipo_cuenta  FROM cuenta_proveedor, banco where cuenta_proveedor.id_banco = banco.id_banco";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}
    	
    		
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    	    Cuenta e = new Cuenta();
    		e.setId(rs.getInt("id_cuenta_proveedor"));
    		e.setCuenta(rs.getString("codigo_cuenta"));
    		e.setNombreBanco(rs.getString("descripcion"));
    		e.setTipoCuenta(rs.getString("tipo_cuenta"));
    		e.setCodBanco(rs.getString("id_banco"));

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
    
    public boolean ExisteCuentaBeneficiario(String cedula) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT id_cuenta_beneficiario  FROM view_cuenta_beneficiario_f a, banco where a.id_banco = banco.id_banco and cedula ='"+cedula+"' and is_activo='true'";
    		
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    	    	rep =true;
     	    }
    	    return rep;
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

    public boolean ExisteCuentaProveedor(int id) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT id_cuenta_proveedor FROM cuenta_proveedor, banco where cuenta_proveedor.id_banco = banco.id_banco and id_proveedor ='"+id+"'";
    		
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    	    	rep =true;
     	    }
    	    return rep;
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

    public int BuscaidProveedor(int id_cuenta) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT id_cuenta_proveedor FROM cuenta_proveedor, banco where cuenta_proveedor.id_banco = banco.id_banco and id_proveedor ='"+id_cuenta+"' order by id_cuenta_proveedor desc limit 1 ";
    		
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    	    	return rs.getInt("id_cuenta_proveedor");
     	    }
    	    return 0;
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
    
    
    
    
    
    public Cuenta buscarCuentaProveedor(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Cuenta) this.buscarProveedor(" and  id_proveedor='" + id +"' and is_activo = true").get(0);
 }

    
    public int buscarIdProveedor(int id_cuenta) throws SQLException, PersonalNotFoundException, NamingException  {
	       return this.BuscaidProveedor(id_cuenta);
}

    
    
    public Cuenta buscarCuentaBeneficiario(String cedula) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (Cuenta) this.buscar(" and  cedula='" + cedula +"' and is_activo = true").get(0);
    }
    
    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar(" order by id_cuenta_beneficiario desc");
    }
   
    public void crearCuentaBeneficiario(Cuenta cuenta, String cedula) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;

		  String sql="INSERT INTO cuenta_beneficiario (numero_cuenta,id_banco,cedula,tipo_cuenta,is_activo) VALUES (?,?,?,?,true)";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
	 
			    sentencia.setString(1,cuenta.getCuenta()); 
			    sentencia.setString(2,cuenta.getCodBanco()); 
			    sentencia.setString(3,cedula); 
			    sentencia.setString(4,cuenta.getTipoCuenta()); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }
    
    public void crearCuentaBeneficiarioB(CuentaBenef cuenta, String cedula) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;

		  String sql="INSERT INTO cuenta_beneficiario (numero_cuenta,id_banco,cedula,tipo_cuenta,is_activo) VALUES (?,?,?,?,true)";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
	 
			    sentencia.setString(1,cuenta.getNumero_cuenta()); 
			    sentencia.setLong(2,Integer.parseInt(cuenta.getIdBanco()));
			    sentencia.setString(3,cedula); 
			    sentencia.setLong(4,Integer.parseInt(cuenta.getTipo_cuenta())); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  log.info("BUSCA JC:"+sql); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }

    public void crearCuentaProveedor(Cuenta cuenta, int id) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  int id_proveedor=0;
		  		
		  String sql="INSERT INTO cuenta_proveedor (codigo_cuenta,id_banco,id_proveedor,tipo_cuenta,is_activo,fecha_registro) VALUES (?,?,?,?,true,NOW())";
	
		  try{
			    sentencia = cnx.prepareStatement(sql);
	 
			    sentencia.setString(1,cuenta.getCuenta()); 
			    sentencia.setInt(2,Integer.parseInt(cuenta.getCodBanco())); 
			    sentencia.setInt(3,id); 
			    sentencia.setInt(4,Integer.parseInt(cuenta.getTipoCuenta())); 
			    sentencia.executeUpdate();
			    
			    
		  } catch (Exception e) {
			  //log.info("BUSCA JC:"+sql2); 
			log.info(e);
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
		

	 }    
    
  public void modificarCuenta(int id) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;


		  String sql="UPDATE cuenta_beneficiario set is_activo=FALSE  where id_cuenta_beneficiario = ?";

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
  
  public void modificarCuentaProveedor(int id) throws SQLException, ClassNotFoundException, IOException{
	  PreparedStatement sentencia = null;


	  String sql="UPDATE cuenta_proveedor set is_activo=FALSE  where id_cuenta_proveedor = ?";

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
